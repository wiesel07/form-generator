package com.wiesel.common.base.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/** 
*
* @ClassName   类名：PageReq 
* @Description 功能说明：
* <p>
* TODO
*</p>
************************************************************************
* @date        创建日期：2018年11月26日
* @author      创建人：wuj
* @version     版本号：V1.0
*<p>
***************************修订记录***************************************
* 
*   2018年11月26日   wuj   创建该类功能。
*
************************************************************************
*</p>
*/
@Data
@Accessors(chain = true)
public class PageReq<T> {

	/**
	 * 页数
	 */
	private Integer pageNo;

	/**
	 * 每页大小
	 */
	private Integer pageSize;

	/**
	 * 开始日期
	 */
	private String startDate;

	/**
	 * 结束日期
	 */
	private String endDate;

}
