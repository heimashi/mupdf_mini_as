package com.rong360.android.crypt;

import android.content.Context;

/**123
 * 软件安全模块，负责维护app的数据安全
 * 为保证代码的足够安全，所有的数据校验与加密都将在
 * native 层实现，实现方案采用c++以及自有的一些密钥计算规则，
 * 保证app的可信度、增加app行为被模拟的难度
 *
 * 该类仅属于jni接口层，不负责任何逻辑处理
 * */
public class Security {
	static {
		System.loadLibrary("crypto");
		System.loadLibrary("rong360");
	}

	/***
	 * 初始化数据安全模块，主要负责安全部件的资源创建工作
	 * */
	public native static void init(Context context, boolean debug);

	/**
	 * 加密数据
	 * @param content, 待加密的数据
	 * @param usePrivate, 是否使用私密密钥
	 * @return String, 加密后的数据格式
	 * */
	public native static String encode(String content, boolean usePrivate);

	/**
	 * 解密数据
	 * @param content, 密文
	 * @param usePrivate, 是否使用私密密钥
	 * @return String, 解密后的明文
	 * */
	public native static String decode(String content, boolean usePrivate);

	/**
	 * 获取app的设备证书，
	 * 用于生成不可伪造的设备ID，后可断可根据具体规则进行安全校验
	 * 输入参数
	 * */
	public native static String getDeviceToken(String content);

	/**
	 * 保存服务端获取到的密钥
	 * */
	public native static boolean savePassword(String content);
}
