package com.suitang.test;

import com.suitang.utils.DESUtils;

public class DESTest {
	public static void main(String[] args) {
		String string1 = DESUtils.encrypt("14102413", "111111112222222233333333");
		System.out.println(string1);
		//0C90C9C63F31D049AD6A88B4FA37833D
		//0C90C9C63F31D049AD6A88B4FA37833D
		//0C90C9C63F31D049AD6A88B4FA37833D
		
		String string2 = DESUtils.decrypt("0C90C9C63F31D049AD6A88B4FA37833D", "111111112222222233333333");
		System.out.println(string2);
	}
}
