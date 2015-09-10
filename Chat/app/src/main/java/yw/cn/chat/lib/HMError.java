package yw.cn.chat.lib;

public interface HMError {

	public static final int ERROR_SERVER = 1;
	public static final int ERROR_CLIENT_NET = 2;

	public interface Login {
		int PASSWORD_ERROR = 100;
		int ACCOUNT_MISS = 101;
	}

	public interface Register {
		int ACCOUNT_EXIST = 150;
	}
}
