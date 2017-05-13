package com.suitang.push;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;

public class PushHelper {

//	private static String MASTER_SECRET = "8b64b9cfb5d4c361ef2060b8";
//	private static String APP_KEY = "0d11775b0537c4f25187b5fc";
	private static String MASTER_SECRET = "e0bab82af6b5c1c6d04491ab";
	private static String APP_KEY = "6cd7410a5c896d5d6be3e628";

	/**
	 * 推送指定消息给指定用户(仅仅只有消息正文)
	 * 
	 * @param msgContent
	 *            消息内容
	 * @param name
	 *            用户别名(可以一次推送给多个用户)
	 * @return 是否推送成功
	 */
	public static Boolean pushMsgToAlias(String title, String msgContent,
			String... alias) {
		Boolean result = false;

		JPushClient client = new JPushClient(PushHelper.MASTER_SECRET,
				PushHelper.APP_KEY);
		PushPayload pushPayload = PushPayload
				.newBuilder()
				.setPlatform(Platform.android())
				.setAudience(Audience.alias(alias))
				.setMessage(
						Message.newBuilder().setTitle(title)
								.setMsgContent(msgContent).build()).build();

		try {
			client.sendPush(pushPayload);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 推送消息给指定Tag(分组)(仅仅只有消息正文)
	 * 
	 * @param msgContent
	 *            消息内容
	 * @param name
	 *            tag名字
	 * @return 是否推送成功
	 */
	public static Boolean pushMsgToTag(String title, String msgContent,
			String... tag) {
		Boolean result = false;
		JPushClient pushClient = new JPushClient(PushHelper.MASTER_SECRET,
				PushHelper.APP_KEY);
		PushPayload pushPayload = PushPayload
				.newBuilder()
				.setPlatform(Platform.android())
				.setAudience(Audience.tag(tag))
				.setMessage(
						Message.newBuilder().setTitle(title)
								.setMsgContent(msgContent).build()).build();

		try {
			pushClient.sendPush(pushPayload);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}