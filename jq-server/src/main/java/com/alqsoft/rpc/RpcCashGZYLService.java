package com.alqsoft.rpc;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alqsoft.entity.payandcash.TakeOut;
import com.alqsoft.entity.userinfo.UserBankCard;



/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年2月11日 下午1:27:06
 * 
 */
public interface RpcCashGZYLService {

    /**
     * 贵州银联提现
     * @author Yaowei
     * @param  
     * @return void
     * @Time 2017年3月15日
     */
	public void cashGZYLService(TakeOut takeOut, Map<String, Object> requestMap);
  
	
	
}
