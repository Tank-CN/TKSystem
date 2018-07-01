package com.tk.util;

/**
 * 返回值编码
 * @author Administrator
 *
 */
public class ResultCode {

	public static final int SUCCESS = 1;

	public static final int ERROR = -100;// 错误,msg里封装具体错误信息

	public static final int METHOD_MUST_POST = 0;// 必须是POST方法

	public static final int PARAMETERS_MISSING = -1;// 请求参数异常(auth)

	public static final int PARAMETERS_EMPTY = -2;// 请求参数异有空值(业务参数)

	public static final int PARAMETERS_NOTLEGAL = -3;// 参数的值不符合规则

	public static final int SIGN_ERROR = -4;// 权限错误 SIGN规则不符合

	public static final int METHOD_FORBID = -5;// 用户没有该方法的权限

	public static final int AUTHORITY_FORBID = -6;// 用户没有使用这个模块的权限（第三方平台做记录及返回）

	public static final int BUS_NOT_SUPPORT = -7;// 不支持此业务

	public static final int PARAMETERS_PUSH_EMPTY = -10;// 消息推送参数有空值

	public static final int ACCOUNT_NOT_EXIST = -11;// 账号不存在

	public static final int REQCODE_DEVICE_DISAFFINITY = -500;// 登录设备不一样

	public static final int REQCODE_PASSWORD_CHANGED = -501;// 密码改变了

	public static final int REQCODE_TIME_EXPIRED = -202;// 时间失效了
}
