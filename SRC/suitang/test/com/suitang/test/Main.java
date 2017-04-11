package com.suitang.test;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Main {

	public static String cookie = "";

	public static void main(String[] args) throws Exception {
		 //dl_loginForward();
		// login_cxCheckYh();
		login_login();

		CXKB();

		// f("<title>\\346\\234\\254\\347\\247\\221\\347\\224\\237\\344\\277\\241\\346\\201\\257\\346\\234\\215\\345\\212\\241\\345\\271\\263\\345\\217\\260</title>");
		// f("\\346\\234\\254");
		// 灏哸pplication/x-www-form-urlencoded瀛楃涓�

		// 杞崲鎴愭櫘閫氬瓧绗︿覆

		// 蹇呴』寮鸿皟鐨勬槸缂栫爜鏂瑰紡蹇呴』姝ｇ‘锛屽baidu鐨勬槸gb2312锛岃�google鐨勬槸UTF-8

		// String keyWord =
		// URLDecoder.decode("%E5%AD%A6%E7%94%9F%E8%AF%BE%E8%A1%A8%E6%9F%A5%E8%AF%A2",
		// "utf-8");

		// System.out.println(keyWord);

		// 灏嗘櫘閫氬瓧绗︿覆杞崲鎴�

		// application/x-www-form-urlencoded瀛楃涓�

		// 蹇呴』寮鸿皟鐨勬槸缂栫爜鏂瑰紡蹇呴』姝ｇ‘锛屽baidu鐨勬槸gb2312锛岃�google鐨勬槸UTF-8

		// String urlStr = URLEncoder.encode("鏈�, "gb2312");

		//f("\\346\\234\\254");
		// 杈撳嚭涓�
		// utf-8:鏈�
		// gb2312:锟斤拷

		// System.out.println(urlStr);
		// urlStr= URLEncoder.encode("鏂囨。", "Unicode");
		// System.out.println(urlStr);
		// f("\\345\\205\\263\\344\\272\\216\\345\\255\\246\\347\\224\\237\\347\\205\\247\\347\\211\\207\\344\\270\\212\\344\\274\\240\\346\\263\\250\\346\\204\\217\\344\\272\\213\\351\\241\\271");
	}

	private static void CXKB() throws Exception {
		// &sessionUserKey=13150226 骞舵病鏈変粈涔堣蒋鐢�
		String url = "http://218.197.80.13/jwglxt/kbcx/xskbcx_cxXsKb.html?gnmkdmKey=N2151&sessionUserKey=13150226";
		URL u = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) u.openConnection();
		System.out.println("*****************************************");
		System.out.println("Cookie = " + cookie);
		System.out.println("*****************************************");
		conn.setRequestProperty("Cookie", cookie);
		conn.setDoOutput(true);
		OutputStream out = conn.getOutputStream();
		out.write("xnm=2016&xqm=3".getBytes());
		Map<String, List<String>> headerFields = conn.getHeaderFields();
		Set<Entry<String, List<String>>> entrySet = headerFields.entrySet();
		for (Entry<String, List<String>> entry : entrySet) {
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}
		System.out
				.println("-----------------------------------------------------");
		InputStream in = conn.getInputStream();
		// GZIPInputStream inputStream = new GZIPInputStream(in);
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));

		File file = new File("d:t.txt");
		FileOutputStream o = new FileOutputStream(file);
		String str = null;
		while ((str = reader.readLine()) != null) {
			System.out.print(str);
			// o.write(str.getBytes("utf-8"));
		}
		o.close();
		System.out.println("");
	}

private static void f(String hex) {
	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	// 杞寲涓哄瓧鑺傛祦
	ByteArrayInputStream inputStream = new ByteArrayInputStream(
			hex.getBytes());
	int read = -1;
	// UTF-8 3涓瓧鑺備唬琛ㄤ竴涓眽瀛�
	byte[] byte3 = new byte[3];
	while ((read = inputStream.read()) > -1) {
		// \\鏄痋鐨勮浆涔夛紝濡傛灉鏄痋浠ｈ〃鍚庨潰3浣嶆槸涓�釜鐢�杩涘埗琛ㄧず鐨�涓瓧鑺�
		if (read == '\\') {
			try {
				inputStream.read(byte3);
			} catch (IOException e) {
				e.printStackTrace();
			}
			// 灏�杩涘埗杞寲涓�0杩涘埗
			outputStream.write(Integer.parseInt(new String(byte3), 8));
		} else {
			outputStream.write(read);
		}
	}
	String decodeMessage = null;
	try {
		// 閲囩敤UTF缂栫爜
		decodeMessage = new String(outputStream.toByteArray(), "utf-8");
	} catch (UnsupportedEncodingException e) {
	}
	System.out.println(decodeMessage);
}

	public static void login_login() throws Exception {
		URL url = new URL("http://218.197.80.13/jwglxt/xtgl/login_login.html");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		//conn.setRequestProperty("Cookie", cookie);
		conn.setInstanceFollowRedirects(false);
		conn.setDoOutput(true);
		// 蹇呴』鍏堣缃姹傚ご鎵嶈兘寰�噷闈㈠啓
		OutputStream out = conn.getOutputStream();
		out.write("yhm=13150227&mm=CHENBING950821&yzm=".getBytes("utf-8"));
		Map<String, List<String>> headerFields = conn.getHeaderFields();
		Set<Entry<String, List<String>>> entrySet = headerFields.entrySet();
		for (Entry<String, List<String>> entry : entrySet) {
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}
		cookie = conn.getHeaderField("Set-Cookie");
		System.out
				.println("--------------*************--------------------------");
		// 302
		System.out.println(conn.getResponseCode());
		if (conn.getResponseCode() == HttpURLConnection.HTTP_MOVED_TEMP) {
			System.out.println(conn.getHeaderField("Location"));
			String uuu = conn.getHeaderField("Location");
			URL u = new URL("http://218.197.80.13"+uuu);
			URLConnection connection = u.openConnection();
			connection.setRequestProperty("Cookie", cookie);
			connection.connect();
			connection.getInputStream();
			// int indexOf = uuu.lastIndexOf("t=");
			// Calendar calendar = Calendar.getInstance();
			// calendar.setTimeInMillis(Long.parseLong(uuu.substring(indexOf +
			// "t=".length())));
			// System.out.println(System.currentTimeMillis());
			// System.out.println(calendar.getTime().toLocaleString());
		}
	}

	// 璇锋眰 http://218.197.80. 7/jwglxt/xtgl/login_cxCheckYh.html
	public static void login_cxCheckYh() throws Exception {
		URL url = new URL(
				"http://218.197.80.13/jwglxt/xtgl/login_cxCheckYh.html");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		// POST鍙傛暟
		// yhm=13150227&mm=CHENBING950821&yzm=

		conn.setRequestProperty("Cookie", cookie);
		// 蹇呴』鍏堣缃姹傚ご鎵嶈兘寰�噷闈㈠啓
		OutputStream out = conn.getOutputStream();
		out.write("yhm=13150227&mm=CHENBING950821&yzm=".getBytes("utf-8"));
		Map<String, List<String>> headerFields = conn.getHeaderFields();
		Set<Entry<String, List<String>>> entrySet = headerFields.entrySet();
		for (Entry<String, List<String>> entry : entrySet) {
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}
		System.out
				.println("-----------------------------------------------------");
		InputStream in = conn.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in,
				"utf-8"));

		String str = null;
		while ((str = reader.readLine()) != null) {
			System.out.print(str);
		}
		System.out.println("");

	}

	// 璇锋眰 http://218.197.80.13/jwglxt/xtgl/dl_loginForward.html
	public static void dl_loginForward() throws Exception {
		URL url = new URL(
				"http://218.197.80.13/jwglxt/xtgl/dl_loginForward.html");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		Map<String, List<String>> headerFields = conn.getHeaderFields();
		Set<Entry<String, List<String>>> entrySet = headerFields.entrySet();
		for (Entry<String, List<String>> entry : entrySet) {
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}
		// cookie = conn.getHeaderField("Set-Cookie");
		System.out
				.println("-----------------------------------------------------");
	}

}
