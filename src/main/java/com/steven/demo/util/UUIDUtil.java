package com.steven.demo.util;

import java.util.UUID;

public class UUIDUtil {

	private static String str ;
	
	/**
	 * 鐢熸垚涓�涓�32浣�16杩涘埗鐨刄UID瀛楃涓�
	 * @return UUID.randomUUID().toString()
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString() ;
	}
	
	
	/**
	 * 
	 * @return 鑾峰彇鍘绘帀-鐨刄UID
	 */
	public static String getUUIDSTR(){	
		str = getUUID();	
		return str.replace("-", "");
	}
	
}
