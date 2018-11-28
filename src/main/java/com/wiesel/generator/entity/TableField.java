package com.wiesel.generator.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * @ClassName 类名：TableField
 * @Description 功能说明：
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2018年11月26日
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录***************************************
 * 
 *          2018年11月26日 wuj 创建该类功能。
 *
 ************************************************************************
 *          </p>
 */
@Data
@Accessors(chain = true)
public class TableField {
    // 字段名，如BILL_ID
	private String columnName;

	private String dataType;

	// 是否主键PRI
	private String keys;

	private String comment;
	
	
	private Integer dataLength;
	
	private String  nullable;
	
	// 根据dataType转换成java中对应的数据类型
	private String propertyType;
	
	// 列名(第一个字母小写)，如：org_id =>orgId
	private String propertyName;
	
	// 列名(第一个字母大写)，如：org_id =>OrgId
	private String capitalName;

}
