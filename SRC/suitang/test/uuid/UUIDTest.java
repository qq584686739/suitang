package uuid;

import java.util.Date;
import java.util.UUID;

import org.junit.Test;

public class UUIDTest {
	@Test
	public void testUUID(){
		String uuid = UUID.randomUUID().toString();
		System.out.println(uuid);
	}
	
	@Test
	public void testDate_getTime(){
		Date date = new Date();
		System.out.println(date.getTime());
		/**
		 * 1492251182590
		 * 1492251202622
		 * 1492251210715
		 * 1492251218645
		 * */
	}
}


/**

b279305b-5fba-4c74-9165-236c60c60994
2b613a33-5c77-49d0-aa0a-aa045075877e
0f4bf2ed-fd70-4303-b03f-946fd42540eb
f98b81e5-7af3-4266-b7d4-5ad19ddf7c5d

 * */
