package cyb.spring.boot.demo.common.exception;

/**
 * 异常响应
 * 
 * @author Administrator
 *
 */
public class ExceptionResponse {

	/**
	 * 未知异常的错误代码
	 */
	public static final int STATUS_UNKNOWN = -1;

	/**
	 * 状态码
	 */
	private int status = STATUS_UNKNOWN;

	/**
	 * 异常信息
	 */
	private String message;

	public ExceptionResponse() {
		this("未知异常");
	}

	/**
	 * 初始化异常信息
	 * 
	 * @param message
	 *            异常信息
	 */
	public ExceptionResponse(String message) {
		this(STATUS_UNKNOWN, message);
	}

	/**
	 * 初始化异常响应
	 * 
	 * @param status
	 *            状态码
	 * @param message
	 *            异常信息
	 */
	public ExceptionResponse(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}
}
