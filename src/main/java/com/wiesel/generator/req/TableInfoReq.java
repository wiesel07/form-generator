package com.wiesel.generator.req;

import lombok.Data;
import lombok.experimental.Accessors;

/** 
*
* @ClassName   类名：TableInfoReq 
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
public class TableInfoReq {
	
	private String tableName;
	
}
