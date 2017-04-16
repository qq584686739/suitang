package com.suitang.test;

import com.suitang.utils.DESUtils;

public class DESTest {
	public static void main(String[] args) {
		String string1 = DESUtils.encrypt("13150227", "123456781234567812345678");
		System.out.println(string1);
		//0C90C9C63F31D049AD6A88B4FA37833D
		//0C90C9C63F31D049AD6A88B4FA37833D
		//0C90C9C63F31D049AD6A88B4FA37833D
		
		String string2 = DESUtils.decrypt("D4EACC418D1F2DC5FEB959B7D4642FCB", "123456781234567812345678");
		System.out.println(string2);
	}
}
