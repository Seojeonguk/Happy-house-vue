package com.ssafy.happyhouse.Util;

public class Util {
	public static String NullCheck(String str, String repl) {
		String ret = "";
		if(str==null || str.equals("")) ret = repl;
		else ret = str;
		return ret;
	}
}
