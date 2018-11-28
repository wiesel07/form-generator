package com.wiesel.common.api;

import lombok.experimental.Accessors;

/** 
*
* @ClassName   类名：IErrorCode 
* @Description 功能说明：REST API 错误码接口
* <p>
* TODO
*</p>
************************************************************************
* @date        创建日期：2018年8月24日
* @author      创建人：wuj
* @version     版本号：V1.0
*<p>
***************************修订记录*************************************
* 
*   2018年8月24日   wuj   创建该类功能。
*
***********************************************************************
*</p>
*/
public interface IErrorCode {
	 /**
     * 错误编码 0、正常  1、失败
     */
    String getCode();

    /**
     * 错误描述
     */
    String getMsg();
}
