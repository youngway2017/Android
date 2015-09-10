package yw.cn.chat.lib;

public class HMURL {

	 public static String BASE_HTTP = "http://192.168.8.139:8888/ChatServer";
	 public static String BASE_HM_HOST = "192.168.8.139:8888";
//	 public static String BASE_HTTP = "http://172.20.10.41:8888/ChatServer";
//	 public static String BASE_HM_HOST = "172.20.10.41:8888";
	 public static int BASE_HM_PORT = 9090;
	 public static String BASE_QR = BASE_HTTP + "/QR/";

	/**
	 * 登录部分的url地址
	 */
	public final static String URL_HTTP_LOGIN = BASE_HTTP + "/login";
	public final static String URL_HTTP_REGISTER = BASE_HTTP + "/register";
	public final static String URL_HTTP_LOGOUT = BASE_HTTP + "/logout";

	/**
	 * 搜索用户
	 */
	public final static String URL_HTTP_SEARCH_CONTACT = BASE_HTTP
			+ "/user/search";

}
