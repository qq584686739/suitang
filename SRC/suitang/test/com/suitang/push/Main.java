package com.suitang.push;

public class Main {
	public static void main(String args[]){
		PushHelper pushHelper = new PushHelper();
		boolean b = pushHelper.pushMsgToAlias("这是主题", "cb是个shabi，收到了吗？", "272");
		System.out.println("b = " + b);
	}
}
