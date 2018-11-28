package com.wiesel.common.api;

import java.util.Optional;

import com.wiesel.common.enums.ApiErrorCode;
import com.wiesel.common.exception.ApiException;

import lombok.Data;

/**
 *
 * @ClassName 类名：ApiResult
 * @Description 功能说明：REST API 返回结果
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2018年8月24日
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录*************************************
 * 
 *          2018年8月24日 wuj 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */
@Data
public class ApiResult<T> {

	public final static String SUCCESS = "success";
	/**
	 * 业务错误码
	 */
	private String code;
	/**
	 * 结果集
	 */
	private T data;
	/**
	 * 描述
	 */
	private String msg;

	public ApiResult() {
		// to do nothing
	}

	public ApiResult(IErrorCode errorCode) {
		// orElseGet若不为空值，orElseGet() 方法不创建对象。
		errorCode = Optional.ofNullable(errorCode).orElseGet(() -> ApiErrorCode.FAILED);
		this.code = errorCode.getCode();
		this.msg = errorCode.getMsg();
	}

	public static  ApiResult<String> ok() {
		return restResult(SUCCESS, ApiErrorCode.SUCCESS);
	}

	
	public static <T> ApiResult<T> ok(T data) {
		return restResult(data, ApiErrorCode.SUCCESS);
	}

	public static <T> ApiResult<T> error(String msg) {
		return restResult(null, ApiErrorCode.FAILED.getCode(), msg);
	}

	public static <T> ApiResult<T> error(IErrorCode errorCode) {
		return restResult(null, errorCode);
	}

	public static <T> ApiResult<T> restResult(T data, IErrorCode errorCode) {
		return restResult(data, errorCode.getCode(), errorCode.getMsg());
	}

	private static <T> ApiResult<T> restResult(T data, String code, String msg) {
		ApiResult<T> apiResult = new ApiResult<>();
		apiResult.setCode(code);
		apiResult.setData(data);
		apiResult.setMsg(msg);
		return apiResult;
	}

	public boolean isOk() {
		return ApiErrorCode.SUCCESS.getCode().equals(this.code);
	}

	/**
	 * 服务间调用非业务正常，异常直接释放
	 */
	public T serviceData() {
		if (!isOk()) {
			throw new ApiException(this.msg);
		}
		return data;
	}
}
