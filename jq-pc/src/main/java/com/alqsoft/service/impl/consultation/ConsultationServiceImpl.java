package com.alqsoft.service.impl.consultation;

import java.io.CharArrayReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.util.Arrays;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.alqsoft.dao.consultation.ConsultationDao;
import com.alqsoft.dao.customer.CustomerDao;
import com.alqsoft.rpc.RpcImDoctorServices;
import com.alqsoft.dao.doctor.DoctorDao;
import com.alqsoft.entity.Customer;
import com.alqsoft.entity.DoctorServiceTime;
import com.alqsoft.entity.doctor.Doctors;
import com.alqsoft.service.consultation.ConsultationService;
import com.alqsoft.utils.UtilDate;
import com.alqsoft.utils.base64_url;

@Service
public class ConsultationServiceImpl implements ConsultationService {

	@Autowired
	private ConsultationDao consultationDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private RpcImDoctorServices rpcImDoctorServices;
	@Autowired
	private DoctorDao doctorDao;
	
	@Override
	public Result verifyCustomer(Long doctorCustomerId, Long customerId, Model model) throws Exception {
		// TODO Auto-generated method stub
		Result results =new Result();
		Map<String,Object> param = new HashMap<String,Object>();
		Doctors doctors = doctorDao.getDoctorByCustomerId(doctorCustomerId);
		Long doctorId = doctors.getId();
		
		param.put("customerid", customerId);
		param.put("doctorid", doctorId);
		Map<String, Object> map = consultationDao.findCustomer(param);
		if(map ==null){
			results.setCode(1);
			results.setMsg("当前用户没有购买在线咨询服务包!");
			return results;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");
		
		
		Object object = map.get("pay_timeout");
		if(object ==null || "".equals(object)){
			results.setCode(1);
			results.setMsg("当前用户数据有误,无法通话!");
			return results;
		}
		long payTime = sdf.parse(map.get("pay_timeout") + "").getTime();// 毫秒
		long currentTime = new Date().getTime();// 当前时间
		if(payTime > currentTime){
			results.setCode(1);
			results.setMsg("当前用户服务包到期,无法通话!");
			return results;
		}
		
		results.setCode(2);
		return results;
	}
	
	@Override
	public Result verifyOrder(Long id, String doctorId, String phone, Model model) {
		// TODO Auto-generated method stub

		Result result = new Result();
		if (StringUtils.isBlank(doctorId)) {
			return ResultUtils.returnError("您尚未选择医生");
		}
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("customerId", id);
		param.put("doctorId", doctorId);
		Map<String, Object> map = consultationDao.findDoctor(param);
			if (map == null) {
				result.setCode(1);
				result.setMsg("请先购买服务包!");
				 return result;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");
			if (StringUtils.isBlank(map.get("payTime") + "")) {
				result.setCode(1);
				result.setMsg("请先购买服务包!");
				 return result;
			}

			try {
				long payTime = sdf.parse(map.get("payTimeOut") + "").getTime();// 毫秒
				long currentTime = new Date().getTime();
				if (currentTime < payTime) {
					result.setCode(1);
					result.setMsg("服务包到期了!");
					 return result;
				}
				if (StringUtils.isBlank((map.get("doctorName") + ""))) {
					result.setCode(1);
					result.setMsg("请选择医生!");
					 return result;
				}

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Integer type = Integer.parseInt((map.get("doctorType") + ""));
			if (!type.equals(0)) {
				result.setCode(1);
				result.setMsg("医生暂时无法服务!");
				 return result;
			}
			try {
				//购买时间
				String payTime = map.get("payTime") +"";
				//到期时间
				long payTime1 = sdf.parse(map.get("payTimeOut") + "").getTime();// 毫秒
				//当前时间
				long currentTime = new Date().getTime();
				//判断当前是否购买,是否有购买时间,是否到期,医生是否被禁用
				if(map !=null && payTime !=null && !payTime.equals("") && currentTime > payTime1 && type.equals(0)){
					result.setCode(2);
					result.setMsg("接通医生通话中.....");
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return result;
		}

	@Override
	public Result findDoctor(Long id, String doctorId, String phone, Model model) {
		Map<String,String> doctorMap =new HashMap<String,String>();
		 if (StringUtils.isBlank(doctorId)) {
			 return ResultUtils.returnError("您尚未选择医生");
		 }
		 Integer doctorServiceTime = consultationDao.getDoctorServiceTime(doctorId);
		 
		 
		 if(!doctorServiceTime.equals(0)){
			 return ResultUtils.returnError("当前医生未开启小时服务功能");
		 }
		 

		 
		 DoctorServiceTime doctorTime = consultationDao.getDoctorServiceDayTime(doctorId);
		 Integer howWeekend = UtilDate.getHowWeekend();
		 String doctorTimeType = null;
		 switch (howWeekend){
		 case 1 : doctorTimeType = doctorTime.getMonday(); break; 
		 case 2 : doctorTimeType = doctorTime.getTuesday(); break; 
		 case 3 : doctorTimeType = doctorTime.getWednesday(); break; 
		 case 4 : doctorTimeType = doctorTime.getThursday(); break; 
		 case 5 : doctorTimeType = doctorTime.getFriday(); break; 
		 case 6 : doctorTimeType = doctorTime.getSaturday(); break; 
		 case 7 : doctorTimeType = doctorTime.getSunday(); break; 
		 }
		 if(StringUtils.isBlank(doctorTimeType)||!"0".equals(doctorTimeType)){
			 return ResultUtils.returnError("今天医生休息，请择日再来咨询");
		 }
		 
		 try {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String format2 = simpleDateFormat.format(new Date());
				String startTime = doctorTime.getCanConsultStarttime();
				String format3 = format2+" "+startTime+":00";
				Date start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(format3);
				
				String endTime = doctorTime.getCanConsultEndtime();
				String formats = format2+" "+endTime+":00";
				Date end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse( formats);
			
				if(!(new Date().getTime()<end.getTime()&&new Date().getTime()>start.getTime())){
				return ResultUtils.returnError("当前医生不在服务范围时间内");
				}
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		 
		 
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("customerId", id);
		param.put("doctorId", doctorId);
		Map<String, Object> map = consultationDao.findDoctor(param);
		if (map == null) {
			return ResultUtils.returnError("您尚未购买此类商品，请购买后尝试");
		}
		
		
		
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");
		if (StringUtils.isBlank(map.get("payTime") + "")) {
			return ResultUtils.returnError("您尚未购买此类商品请稍后尝试");
		}

		try {
			long payTime = sdf.parse(map.get("payTimeOut") + "").getTime();// 毫秒
			long currentTime = new Date().getTime();
			if (currentTime < payTime) {
				return ResultUtils.returnError("您购买此商品已过期");
			}
			if (StringUtils.isBlank((map.get("doctorName") + ""))) {
				return ResultUtils.returnError("订单异常，未找到该医生");
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Integer type = Integer.parseInt((map.get("doctorType") + ""));
		if (!type.equals(0)) {
			return ResultUtils.returnError("该医生已被禁用");
		}
		model.addAttribute("mobile", phone);
		model.addAttribute("doctorName", map.get("customerPhone") + "");
		try {
			// Use pemfile keys to test
			String privStr = "-----BEGIN PRIVATE KEY-----\n"
					+ "MIGEAgEAMBAGByqGSM49AgEGBSuBBAAKBG0wawIBAQQgnZgncHteBrZz64Nt6Qxf\n"
					+ "68rTYRdjrIc8Z4Kbs8F4NKahRANCAAT5F+1ta2ORKIotZzbsHbhkIcIgyjjJcdRG\n"
					+ "tBWS/4EJPMcLA5CqXExoo8KlqdTrZ7GicJ7vONaIy0PAG74mZBlp\n" + "-----END PRIVATE KEY-----";

			// change public pem string to public string
			String pubStr = "-----BEGIN PUBLIC KEY-----\n"
					+ "MFYwEAYHKoZIzj0CAQYFK4EEAAoDQgAE+RftbWtjkSiKLWc27B24ZCHCIMo4yXHU\n"
					+ "RrQVkv+BCTzHCwOQqlxMaKPCpanU62exonCe7zjWiMtDwBu+JmQZaQ==\n" + "-----END PUBLIC KEY-----";

			// generate signature
			GenTLSSignatureResult result = GenTLSSignatureEx(1400026468, phone + "", privStr);
			if (0 == result.urlSig.length()) {
				System.out.println("GenTLSSignatureEx failed: " + result.errMessage);
				return ResultUtils.returnError("加密签名异常");
			}

			System.out.println("---\ngenerate sig:\n" + result.urlSig + "\n---\n");
			model.addAttribute("usersig", result.urlSig);
			// check signature
			CheckTLSSignatureResult checkResult = CheckTLSSignatureEx(result.urlSig, 1400026468, phone + "", pubStr);
			if (checkResult.verifyResult == false) {
				System.out.println("CheckTLSSignature failed: " + result.errMessage);
				return ResultUtils.returnError("加密签名异常");
			}

			System.out.println("\n---\ncheck sig ok -- expire time " + checkResult.expireTime + " -- init time "
					+ checkResult.initTime + "\n---\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String,Object> mapParam = new HashMap<String,Object>();
		mapParam.put("doctorName", map.get("customerPhone") + "");
		mapParam.put("customerName", phone);
		mapParam.put("doctorId", doctorId);
		mapParam.put("customerId", id);
		
		rpcImDoctorServices.saveImLog(mapParam);
		
		
		return ResultUtils.returnSuccess("请求成功");
	}

	public static class GenTLSSignatureResult {
		public String errMessage;
		public String urlSig;
		public int expireTime;
		public int initTime;

		public GenTLSSignatureResult() {
			errMessage = "";
			urlSig = "";
		}
	}

	public static class CheckTLSSignatureResult {
		public String errMessage;
		public boolean verifyResult;
		public int expireTime;
		public int initTime;

		public CheckTLSSignatureResult() {
			errMessage = "";
			verifyResult = false;
		}
	}

	/**
	 * @brief 生成 tls 票据
	 * @param expire
	 *            有效期，单位是秒，推荐一个月
	 * @param strAppid3rd
	 *            填写与 sdkAppid 一致字符串形式的值
	 * @param skdAppid
	 *            应用的 appid
	 * @param identifier
	 *            用户 id
	 * @param accountType
	 *            创建应用后在配置页面上展示的 acctype
	 * @param privStr
	 *            生成 tls 票据使用的私钥内容
	 * @return 如果出错，GenTLSSignatureResult 中的 urlSig为空，errMsg 为出错信息，成功返回有效的票据
	 * @throws IOException
	 */
	@Deprecated
	public static GenTLSSignatureResult GenTLSSignature(long expire, String strAppid3rd, long skdAppid,
			String identifier, long accountType, String privStr) throws IOException {

		GenTLSSignatureResult result = new GenTLSSignatureResult();

		Security.addProvider(new BouncyCastleProvider());
		Reader reader = new CharArrayReader(privStr.toCharArray());
		JcaPEMKeyConverter converter = new JcaPEMKeyConverter();
		PEMParser parser = new PEMParser(reader);
		Object obj = parser.readObject();
		parser.close();
		PrivateKey privKeyStruct = converter.getPrivateKey((PrivateKeyInfo) obj);

		// Create Json string and serialization String
		String jsonString = "{" + "\"TLS.account_type\":\"" + accountType + "\"," + "\"TLS.identifier\":\"" + identifier
				+ "\"," + "\"TLS.appid_at_3rd\":\"" + strAppid3rd + "\"," + "\"TLS.sdk_appid\":\"" + skdAppid + "\","
				+ "\"TLS.expire_after\":\"" + expire + "\"" + "}";
		// System.out.println("#jsonString : \n" + jsonString);

		String time = String.valueOf(System.currentTimeMillis() / 1000);
		String SerialString = "TLS.appid_at_3rd:" + strAppid3rd + "\n" + "TLS.account_type:" + accountType + "\n"
				+ "TLS.identifier:" + identifier + "\n" + "TLS.sdk_appid:" + skdAppid + "\n" + "TLS.time:" + time + "\n"
				+ "TLS.expire_after:" + expire + "\n";

		// System.out.println("#SerialString : \n" + SerialString);
		// System.out.println("#SerialString Hex: \n" +
		// Hex.encodeHexString(SerialString.getBytes()));

		try {
			// Create Signature by SerialString
			Signature signature = Signature.getInstance("SHA256withECDSA", "BC");
			signature.initSign(privKeyStruct);
			signature.update(SerialString.getBytes(Charset.forName("UTF-8")));
			byte[] signatureBytes = signature.sign();

			String sigTLS = Base64.encodeBase64String(signatureBytes);
			// System.out.println("#sigTLS : " + sigTLS);

			// Add TlsSig to jsonString
			JSONObject jsonObject = new JSONObject(jsonString);
			jsonObject.put("TLS.sig", (Object) sigTLS);
			jsonObject.put("TLS.time", (Object) time);
			jsonString = jsonObject.toString();

			// System.out.println("#jsonString : \n" + jsonString);

			// compression
			Deflater compresser = new Deflater();
			compresser.setInput(jsonString.getBytes(Charset.forName("UTF-8")));

			compresser.finish();
			byte[] compressBytes = new byte[512];
			int compressBytesLength = compresser.deflate(compressBytes);
			compresser.end();
			// System.out.println("#compressBytes "+ compressBytesLength+": " +
			// Hex.encodeHexString(Arrays.copyOfRange(compressBytes,0,compressBytesLength)));

			// String userSig =
			// Base64.encodeBase64URLSafeString(Arrays.copyOfRange(compressBytes,0,compressBytesLength));
			String userSig = new String(
					base64_url.base64EncodeUrl(Arrays.copyOfRange(compressBytes, 0, compressBytesLength)));

			result.urlSig = userSig;
			// System.out.println("urlSig: "+ userSig);
		} catch (Exception e) {
			e.printStackTrace();
			result.errMessage = "generate usersig failed";
		}

		return result;
	}

	/**
	 * @brief 校验 tls 票据
	 * @param urlSig
	 *            返回 tls 票据
	 * @param strAppid3rd
	 *            填写与 sdkAppid 一致的字符串形式的值
	 * @param skdAppid
	 *            应的 appid
	 * @param identifier
	 *            用户 id
	 * @param accountType
	 *            创建应用后在配置页面上展示的 acctype
	 * @param publicKey
	 *            用于校验 tls 票据的公钥内容，但是需要先将公钥文件转换为 java 原生 api 使用的格式，下面是推荐的命令
	 *            openssl pkcs8 -topk8 -in ec_key.pem -outform PEM -out
	 *            p8_priv.pem -nocrypt
	 * @return 如果出错 CheckTLSSignatureResult 中的 verifyResult 为 false，错误信息在
	 *         errMsg，校验成功为 true
	 * @throws DataFormatException
	 */
	@Deprecated
	public static CheckTLSSignatureResult CheckTLSSignature(String urlSig, String strAppid3rd, long skdAppid,
			String identifier, long accountType, String publicKey) throws DataFormatException {
		CheckTLSSignatureResult result = new CheckTLSSignatureResult();
		Security.addProvider(new BouncyCastleProvider());

		// DeBaseUrl64 urlSig to json
		Base64 decoder = new Base64();

		// byte [] compressBytes = decoder.decode(urlSig.getBytes());
		byte[] compressBytes = base64_url.base64DecodeUrl(urlSig.getBytes(Charset.forName("UTF-8")));

		// System.out.println("#compressBytes Passing in[" +
		// compressBytes.length + "] " + Hex.encodeHexString(compressBytes));

		// Decompression
		Inflater decompression = new Inflater();
		decompression.setInput(compressBytes, 0, compressBytes.length);
		byte[] decompressBytes = new byte[1024];
		int decompressLength = decompression.inflate(decompressBytes);
		decompression.end();

		String jsonString = new String(Arrays.copyOfRange(decompressBytes, 0, decompressLength));

		// System.out.println("#Json String passing in : \n" + jsonString);

		// Get TLS.Sig from json
		JSONObject jsonObject = new JSONObject(jsonString);
		String sigTLS = jsonObject.getString("TLS.sig");

		// debase64 TLS.Sig to get serailString
		byte[] signatureBytes = decoder.decode(sigTLS.getBytes(Charset.forName("UTF-8")));

		try {

			String sigTime = jsonObject.getString("TLS.time");
			String sigExpire = jsonObject.getString("TLS.expire_after");

			// checkTime
			// System.out.println("#time check: "+
			// System.currentTimeMillis()/1000 + "-"
			// + Long.parseLong(sigTime) + "-" + Long.parseLong(sigExpire));
			if (System.currentTimeMillis() / 1000 - Long.parseLong(sigTime) > Long.parseLong(sigExpire)) {
				result.errMessage = new String("TLS sig is out of date ");
				System.out.println("Timeout");
				return result;
			}

			// Get Serial String from json
			String SerialString = "TLS.appid_at_3rd:" + strAppid3rd + "\n" + "TLS.account_type:" + accountType + "\n"
					+ "TLS.identifier:" + identifier + "\n" + "TLS.sdk_appid:" + skdAppid + "\n" + "TLS.time:" + sigTime
					+ "\n" + "TLS.expire_after:" + sigExpire + "\n";

			// System.out.println("#SerialString : \n" + SerialString);

			Reader reader = new CharArrayReader(publicKey.toCharArray());
			PEMParser parser = new PEMParser(reader);
			JcaPEMKeyConverter converter = new JcaPEMKeyConverter();
			Object obj = parser.readObject();
			parser.close();
			PublicKey pubKeyStruct = converter.getPublicKey((SubjectPublicKeyInfo) obj);

			Signature signature = Signature.getInstance("SHA256withECDSA", "BC");
			signature.initVerify(pubKeyStruct);
			signature.update(SerialString.getBytes(Charset.forName("UTF-8")));
			boolean bool = signature.verify(signatureBytes);
			// System.out.println("#jdk ecdsa verify : " + bool);
			result.verifyResult = bool;
		} catch (Exception e) {
			e.printStackTrace();
			result.errMessage = "Failed in checking sig";
		}

		return result;
	}

	/**
	 * @brief 生成 tls 票据，精简参数列表，有效期默认为 180 天
	 * @param skdAppid
	 *            应用的 sdkappid
	 * @param identifier
	 *            用户 id
	 * @param privStr
	 *            私钥文件内容
	 * @return
	 * @throws IOException
	 */
	public static GenTLSSignatureResult GenTLSSignatureEx(long skdAppid, String identifier, String privStr)
			throws IOException {
		return GenTLSSignatureEx(skdAppid, identifier, privStr, 3600 * 24 * 180);
	}

	/**
	 * @brief 生成 tls 票据，精简参数列表
	 * @param skdAppid
	 *            应用的 sdkappid
	 * @param identifier
	 *            用户 id
	 * @param privStr
	 *            私钥文件内容
	 * @param expire
	 *            有效期，以秒为单位，推荐时长一个月
	 * @return
	 * @throws IOException
	 */
	public static GenTLSSignatureResult GenTLSSignatureEx(long skdAppid, String identifier, String privStr, long expire)
			throws IOException {

		GenTLSSignatureResult result = new GenTLSSignatureResult();

		Security.addProvider(new BouncyCastleProvider());
		Reader reader = new CharArrayReader(privStr.toCharArray());
		JcaPEMKeyConverter converter = new JcaPEMKeyConverter();
		PEMParser parser = new PEMParser(reader);
		Object obj = parser.readObject();
		parser.close();
		PrivateKey privKeyStruct = converter.getPrivateKey((PrivateKeyInfo) obj);

		String jsonString = "{" + "\"TLS.account_type\":\"" + 0 + "\"," + "\"TLS.identifier\":\"" + identifier + "\","
				+ "\"TLS.appid_at_3rd\":\"" + 0 + "\"," + "\"TLS.sdk_appid\":\"" + skdAppid + "\","
				+ "\"TLS.expire_after\":\"" + expire + "\"," + "\"TLS.version\": \"201512300000\"" + "}";

		String time = String.valueOf(System.currentTimeMillis() / 1000);
		String SerialString = "TLS.appid_at_3rd:" + 0 + "\n" + "TLS.account_type:" + 0 + "\n" + "TLS.identifier:"
				+ identifier + "\n" + "TLS.sdk_appid:" + skdAppid + "\n" + "TLS.time:" + time + "\n"
				+ "TLS.expire_after:" + expire + "\n";

		try {
			// Create Signature by SerialString
			Signature signature = Signature.getInstance("SHA256withECDSA", "BC");
			signature.initSign(privKeyStruct);
			signature.update(SerialString.getBytes(Charset.forName("UTF-8")));
			byte[] signatureBytes = signature.sign();

			String sigTLS = Base64.encodeBase64String(signatureBytes);

			// Add TlsSig to jsonString
			JSONObject jsonObject = new JSONObject(jsonString);
			jsonObject.put("TLS.sig", (Object) sigTLS);
			jsonObject.put("TLS.time", (Object) time);
			jsonString = jsonObject.toString();

			// compression
			Deflater compresser = new Deflater();
			compresser.setInput(jsonString.getBytes(Charset.forName("UTF-8")));

			compresser.finish();
			byte[] compressBytes = new byte[512];
			int compressBytesLength = compresser.deflate(compressBytes);
			compresser.end();
			String userSig = new String(
					base64_url.base64EncodeUrl(Arrays.copyOfRange(compressBytes, 0, compressBytesLength)));

			result.urlSig = userSig;
		} catch (Exception e) {
			e.printStackTrace();
			result.errMessage = "generate usersig failed";
		}

		return result;
	}

	public static CheckTLSSignatureResult CheckTLSSignatureEx(String urlSig, long sdkAppid, String identifier,
			String publicKey) throws DataFormatException {

		CheckTLSSignatureResult result = new CheckTLSSignatureResult();
		Security.addProvider(new BouncyCastleProvider());

		// DeBaseUrl64 urlSig to json
		Base64 decoder = new Base64();

		byte[] compressBytes = base64_url.base64DecodeUrl(urlSig.getBytes(Charset.forName("UTF-8")));

		// Decompression
		Inflater decompression = new Inflater();
		decompression.setInput(compressBytes, 0, compressBytes.length);
		byte[] decompressBytes = new byte[1024];
		int decompressLength = decompression.inflate(decompressBytes);
		decompression.end();

		String jsonString = new String(Arrays.copyOfRange(decompressBytes, 0, decompressLength));

		// Get TLS.Sig from json
		JSONObject jsonObject = new JSONObject(jsonString);
		String sigTLS = jsonObject.getString("TLS.sig");

		// debase64 TLS.Sig to get serailString
		byte[] signatureBytes = decoder.decode(sigTLS.getBytes(Charset.forName("UTF-8")));

		try {
			String strSdkAppid = jsonObject.getString("TLS.sdk_appid");
			String sigTime = jsonObject.getString("TLS.time");
			String sigExpire = jsonObject.getString("TLS.expire_after");

			if (Integer.parseInt(strSdkAppid) != sdkAppid) {
				result.errMessage = new String(
						"sdkappid " + strSdkAppid + " in tls sig not equal sdkappid " + sdkAppid + " in request");
				return result;
			}

			if (System.currentTimeMillis() / 1000 - Long.parseLong(sigTime) > Long.parseLong(sigExpire)) {
				result.errMessage = new String("TLS sig is out of date");
				return result;
			}

			// Get Serial String from json
			String SerialString = "TLS.appid_at_3rd:" + 0 + "\n" + "TLS.account_type:" + 0 + "\n" + "TLS.identifier:"
					+ identifier + "\n" + "TLS.sdk_appid:" + sdkAppid + "\n" + "TLS.time:" + sigTime + "\n"
					+ "TLS.expire_after:" + sigExpire + "\n";

			Reader reader = new CharArrayReader(publicKey.toCharArray());
			PEMParser parser = new PEMParser(reader);
			JcaPEMKeyConverter converter = new JcaPEMKeyConverter();
			Object obj = parser.readObject();
			parser.close();
			PublicKey pubKeyStruct = converter.getPublicKey((SubjectPublicKeyInfo) obj);

			Signature signature = Signature.getInstance("SHA256withECDSA", "BC");
			signature.initVerify(pubKeyStruct);
			signature.update(SerialString.getBytes(Charset.forName("UTF-8")));
			boolean bool = signature.verify(signatureBytes);
			result.expireTime = Integer.parseInt(sigExpire);
			result.initTime = Integer.parseInt(sigTime);
			result.verifyResult = bool;
		} catch (Exception e) {
			e.printStackTrace();
			result.errMessage = "Failed in checking sig";
		}

		return result;
	}

	@Override
	public Result findAdmin(String admin, String custmoer, Model model) {
		model.addAttribute("mobile", custmoer);
		model.addAttribute("doctorName", admin);
		try {
			// Use pemfile keys to test
			String privStr = "-----BEGIN PRIVATE KEY-----\n"
					+ "MIGEAgEAMBAGByqGSM49AgEGBSuBBAAKBG0wawIBAQQgnZgncHteBrZz64Nt6Qxf\n"
					+ "68rTYRdjrIc8Z4Kbs8F4NKahRANCAAT5F+1ta2ORKIotZzbsHbhkIcIgyjjJcdRG\n"
					+ "tBWS/4EJPMcLA5CqXExoo8KlqdTrZ7GicJ7vONaIy0PAG74mZBlp\n" + "-----END PRIVATE KEY-----";

			// change public pem string to public string
			String pubStr = "-----BEGIN PUBLIC KEY-----\n"
					+ "MFYwEAYHKoZIzj0CAQYFK4EEAAoDQgAE+RftbWtjkSiKLWc27B24ZCHCIMo4yXHU\n"
					+ "RrQVkv+BCTzHCwOQqlxMaKPCpanU62exonCe7zjWiMtDwBu+JmQZaQ==\n" + "-----END PUBLIC KEY-----";

			// generate signature
			GenTLSSignatureResult result = GenTLSSignatureEx(1400026468, custmoer + "", privStr);
			if (0 == result.urlSig.length()) {
				System.out.println("GenTLSSignatureEx failed: " + result.errMessage);
				return ResultUtils.returnError("加密签名异常");
			}

			System.out.println("---\ngenerate sig:\n" + result.urlSig + "\n---\n");
			model.addAttribute("usersig", result.urlSig);
			// check signature
			CheckTLSSignatureResult checkResult = CheckTLSSignatureEx(result.urlSig, 1400026468, custmoer + "", pubStr);
			if (checkResult.verifyResult == false) {
				System.out.println("CheckTLSSignature failed: " + result.errMessage);
				return ResultUtils.returnError("加密签名异常");
			}

			System.out.println("\n---\ncheck sig ok -- expire time " + checkResult.expireTime + " -- init time "
					+ checkResult.initTime + "\n---\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultUtils.returnSuccess("请求成功");
	}

	@Override
	public Result findCustomer(Long id, String customerName, String phone, Model model) throws Exception {
		// TODO Auto-generated method stub
		Result results =new Result();
		Map<String,Object> param = new HashMap<String,Object>();
		Doctors doctors = doctorDao.getDoctorByCustomerId(id);
		Long doctorId = doctors.getId();
		
		param.put("customerid", customerName);
		param.put("doctorid", doctorId);
		Map<String, Object> map = consultationDao.findCustomer(param);
		if(map ==null){
			results.setCode(1);
			results.setMsg("当前用户没有购买在线咨询服务包!");
			return results;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");
		Object object = map.get("pay_timeout");
		if(object ==null || "".equals(object)){
			results.setCode(1);
			results.setMsg("当前用户数据有误,无法通话!");
			return results;
		}
		long payTime = sdf.parse(map.get("pay_timeout") + "").getTime();// 毫秒
		//long payTime = sdf.parse(str).getTime();// 毫秒
		long currentTime = new Date().getTime();// 当前时间
		if(payTime > currentTime){
			results.setCode(1);
			results.setMsg("当前用户服务包到期,无法通话!");
			return results;
		}
		
		Customer customerById = customerDao.getCustomerById(Long.valueOf(customerName));
		
		model.addAttribute("mobile", phone);
		model.addAttribute("doctorName",customerById.getPhone());
		try {
			// Use pemfile keys to test
			String privStr = "-----BEGIN PRIVATE KEY-----\n"
					+ "MIGEAgEAMBAGByqGSM49AgEGBSuBBAAKBG0wawIBAQQgnZgncHteBrZz64Nt6Qxf\n"
					+ "68rTYRdjrIc8Z4Kbs8F4NKahRANCAAT5F+1ta2ORKIotZzbsHbhkIcIgyjjJcdRG\n"
					+ "tBWS/4EJPMcLA5CqXExoo8KlqdTrZ7GicJ7vONaIy0PAG74mZBlp\n" + "-----END PRIVATE KEY-----";

			// change public pem string to public string
			String pubStr = "-----BEGIN PUBLIC KEY-----\n"
					+ "MFYwEAYHKoZIzj0CAQYFK4EEAAoDQgAE+RftbWtjkSiKLWc27B24ZCHCIMo4yXHU\n"
					+ "RrQVkv+BCTzHCwOQqlxMaKPCpanU62exonCe7zjWiMtDwBu+JmQZaQ==\n" + "-----END PUBLIC KEY-----";

			// generate signature
			GenTLSSignatureResult result = GenTLSSignatureEx(1400026468, phone + "", privStr);
			if (0 == result.urlSig.length()) {
				System.out.println("GenTLSSignatureEx failed: " + result.errMessage);
				return ResultUtils.returnError("加密签名异常");
			}

			System.out.println("---\ngenerate sig:\n" + result.urlSig + "\n---\n");
			model.addAttribute("usersig", result.urlSig);
			// check signature
			CheckTLSSignatureResult checkResult = CheckTLSSignatureEx(result.urlSig, 1400026468, phone + "", pubStr);
			if (checkResult.verifyResult == false) {
				System.out.println("CheckTLSSignature failed: " + result.errMessage);
				return ResultUtils.returnError("加密签名异常");
			}

			System.out.println("\n---\ncheck sig ok -- expire time " + checkResult.expireTime + " -- init time "
					+ checkResult.initTime + "\n---\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String,Object> mapParam = new HashMap<String,Object>();
		mapParam.put("doctorName", phone);
		mapParam.put("customerName",doctors.getCustomerPhone());
		mapParam.put("doctorId", customerName);
		mapParam.put("customerId", id);
		
		rpcImDoctorServices.saveImLog(mapParam);
		return ResultUtils.returnSuccess("请求成功");
	}
		
		



}
