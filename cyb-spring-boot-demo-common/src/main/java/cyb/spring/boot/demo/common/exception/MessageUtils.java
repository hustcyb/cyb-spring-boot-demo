package cyb.spring.boot.demo.common.exception;

import cyb.spring.boot.demo.common.json.JsonUtils;

/**
 * Json格式异常信息生成程序
 * 
 * @author Administrator
 *
 */
public final class MessageUtils {

	/**
	 * 生成Json格式的异常信息
	 * 
	 * @param message
	 *            异常信息
	 * @return Json格式异常信息
	 */
	public static String exception(String message) {
		ExceptionResponse response = new ExceptionResponse(message);
		return JsonUtils.bean2Json(response);
	}

	/**
	 * 生成Json格式的异常信息
	 * 
	 * @param status
	 *            状态码
	 * @param message
	 *            异常信息
	 * @return Json格式的异常信息
	 */
	public static String exception(int status, String message) {
		ExceptionResponse response = new ExceptionResponse(status, message);
		return JsonUtils.bean2Json(response);
	}
}
