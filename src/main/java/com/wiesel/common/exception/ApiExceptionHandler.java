package com.wiesel.common.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.wiesel.common.api.ApiResult;
import com.wiesel.common.api.IErrorCode;
import com.wiesel.common.enums.ApiErrorCode;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @ClassName 类名：ApiExceptionHandler
 * @Description 功能说明：
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2019年1月8日
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录***************************************
 * 
 *          2019年1月8日 wuj 创建该类功能。
 *
 ************************************************************************
 *          </p>
 */
@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {

	@ExceptionHandler(ApiException.class)
	public ApiResult<String> handleException(ApiException ex) {

		IErrorCode ErrorCode = ex.getErrorCode();
		if (ObjectUtil.isNotNull(ErrorCode)) {
			log.info("ApiErrorCode:{}({})", ex.getErrorCode().getMsg(), ex.getErrorCode().getCode());

			return new ApiResult<>(ex.getErrorCode());
		}

		return ApiResult.error(ex.getMessage());

	}
}
