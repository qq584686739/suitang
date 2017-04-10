package com.suitang.test;

import java.util.Date;

import org.junit.Test;

public class TimeTest {
	@Test
	public void testTime() throws Exception{
		long time = new Date().getTime();
		System.out.println(time);
		new Thread().sleep(3000);
		long time2 = new Date().getTime();
		System.out.println(time2);
		new Thread().sleep(3000);
		long time3 = new Date().getTime();
		System.out.println(time3);
		new Thread().sleep(3000);
		long time4 = new Date().getTime();
		System.out.println(time4);
	}
}

