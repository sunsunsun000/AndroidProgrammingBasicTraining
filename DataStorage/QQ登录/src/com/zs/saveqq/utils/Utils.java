package com.zs.saveqq.utils;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Utils {
	//保存QQ号码和登录密码到data.xml文件中
	public static boolean saveUserInfo(Context context,String number,String password){
		SharedPreferences sp = context.getSharedPreferences("data", Context.MODE_PRIVATE);
		Editor edit = sp.edit();
		edit.putString("userName", number);
		edit.putString("psw", password);
		edit.commit();
		return true;
	}
	//从data.xml文件中获取存储的QQ号和密码
	public static Map<String, String> getUserInfo(Context context){
		SharedPreferences sp =context.getSharedPreferences("data", Context.MODE_PRIVATE);
		String number = sp.getString("userName", null);
		String password =sp.getString("psw", null);
		Map<String, String> userMap = new HashMap<String, String>();
		userMap.put("number", number);
		userMap.put("password", password);
		return userMap;
	}
}
