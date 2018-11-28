package com.wiesel.common.exception;

import com.wiesel.common.api.IErrorCode;

import lombok.Getter;

/**
 *
 * @ClassName 类名：ApiException
 * @Description 功能说明：REST API 请求异常类
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

public class ApiException extends RuntimeException {

	private static final long serialVersionUID = -6188829639109514288L;

	/**
     * 错误码
     */
	@Getter
    private IErrorCode errorCode;

    public ApiException(IErrorCode errorCode) {
    	super(errorCode.getMsg());
        this.errorCode = errorCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

}
