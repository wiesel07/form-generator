package com.wiesel.generator.entity;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * @ClassName 类名：TableInfo
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
public class TableInfo {

	private String owner;

	private String tableType;

	// 表名，如AIMS_USE
	private String tableName;

	private String comments;

	// 表的主键
	TableField pk;
	// 表的列名(不包含主键)
	List<TableField> tableFields;
	// 类名(第一个字母小写)，如：sys_user => sysUser
	private String className;

	// 类名(第一个字母大写)，如：sys_user => SysUser
	private String capitalClassName;
	
}
