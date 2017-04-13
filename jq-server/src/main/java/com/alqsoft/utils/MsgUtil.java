package com.alqsoft.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * MsgUtil.java - 2011-4-27
 *
 * Licensed Property to China UnionPay Co., Ltd.
 * 
 * (C) Copyright of China UnionPay Co., Ltd. 2011
 *     All Rights Reserved.
 *
 * Project Info: China UnionPay Internet Acquiring Project
 * 
 * Modification History:
 * =============================================================================
 *   Author         Date          Description
 *   ------------ ---------- ---------------------------------------------------
 * 
 * =============================================================================
 */

/**
 * Description:
 * 
 * (C) Copyright of China UnionPay Co., Ltd. 2011.
 * 
 */
public class MsgUtil {

	/**
	 * 压缩编码
	 * 
	 * @param inputByte
	 * @return
	 * @throws IOException
	 */
	public byte[] deflateEncode(byte[] inputByte) throws IOException {
		try {
			if (inputByte == null || inputByte.length == 0) {
				System.out.println("压缩编码异常:输入不能为空指针!");
				throw new IOException("压缩编码异常:输入不能为空指针!");
			}
			byte[] tmpByte = deflater(inputByte);
			return encode(tmpByte);
		} catch (IOException ioex) {
			System.out.println("压缩编码异常:IO异常!");
			throw ioex;
		}

	}

	/**
	 * 解码解压缩
	 * 
	 * @param inputByte
	 * @return
	 * @throws IOException
	 */
	public byte[] decodeInflate(byte[] inputByte) throws IOException {
		try {
			if (inputByte == null || inputByte.length == 0) {
				throw new IOException("解码解压缩异常:输入不能为空!");
			}
			byte[] tmpByte = decode(inputByte);
			return inflater(tmpByte);
		} catch (IOException ioex) {
			System.out.println("解码解压缩异常异常:IO异常!");
			throw ioex;
		}
	}

	/**
	 * 压缩方法
	 */
	public byte[] deflater(byte[] inputByte) throws IOException {
		int compressedDataLength = 0;
		Deflater compresser = new Deflater();
		compresser.setInput(inputByte);
		compresser.finish();
		ByteArrayOutputStream o = new ByteArrayOutputStream(inputByte.length);
		byte[] result = new byte[1024];
		try {
			while (!compresser.finished()) {
				compressedDataLength = compresser.deflate(result);
				o.write(result, 0, compressedDataLength);
			}
		} finally {
			o.close();
		}
		// System.out.println("compressed data length:"+compressedDataLength);
		compresser.end();

		return o.toByteArray();

	}

	static final int BUFFER = 128;

	/**
	 * 解压缩方法
	 * 
	 * @param inputByte
	 * @return
	 * @throws IOException
	 */
	public byte[] inflater(byte[] inputByte) throws IOException {
		int compressedDataLength = 0;
		Inflater compresser = new Inflater(false);
		compresser.setInput(inputByte, 0, inputByte.length);
		ByteArrayOutputStream o = new ByteArrayOutputStream(inputByte.length);
		byte[] result = new byte[1024];
		try {
			while (!compresser.finished()) {
				compressedDataLength = compresser.inflate(result);
				if (compressedDataLength == 0) {
					break;
				}
				o.write(result, 0, compressedDataLength);
			}
		} catch (Exception ex) {
			System.err.println("Data format error!\n");
			ex.printStackTrace();
		} finally {
			o.close();
		}
		compresser.end();
		// System.out.println("decompressed data length:"+compressedDataLength);
		return o.toByteArray();
	}

	/**
	 * BASE64解码
	 */
	public static byte[] decode(byte[] inputByte) throws IOException {
		return com.sun.mail.util.BASE64DecoderStream.decode(inputByte);
	}

	/**
	 * BASE64编码
	 */
	public static byte[] encode(byte[] inputByte) throws IOException {
		return com.sun.mail.util.BASE64EncoderStream.encode(inputByte);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String ss = "123456789abcdefghijklmnopqrstuvwxyz你好测试文件!@#$%^&*()_+-=<>?,./";
		System.out.println("原始字符串=[" + ss + "]");

		MsgUtil msgUtil = new MsgUtil();

		try {
			String st = new String(msgUtil.deflateEncode(ss.getBytes()));
			System.out.println("压缩编码后=[" + st + "]");
			String ts = new String(msgUtil.decodeInflate(st.getBytes()));
			System.out.println("解压缩解码后=[" + ts + "]");
		} catch (IOException ioex) {
			ioex.printStackTrace();
		}

	}

}
