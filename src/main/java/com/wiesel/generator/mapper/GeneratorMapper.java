package com.wiesel.generator.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wiesel.generator.entity.TableField;
import com.wiesel.generator.entity.TableInfo;

/**
 *
 * @ClassName 类名：GeneratorMapper
 * @Description 功能说明：
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2018年8月11日
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录*************************************
 * 
 *          2018年8月11日 wuj 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */
@Mapper
public interface GeneratorMapper {

	/**
	 * 
	 * <p>函数名称：        </p>
	 * <p>功能说明：根据用户获取当前数据库表信息分页列表
	 *
	 * </p>
	 *<p>参数说明：</p>
	 * @param page
	 * @param tableName
	 * @param owner
	 * @return
	 *
	 * @date   创建时间：2018年11月26日
	 * @author 作者：wuj
	 */
	@SuppressWarnings("rawtypes")
	IPage<TableInfo> queryTablePage(@Param("page")Page page,@Param("tableName")String tableName,@Param("owner")String owner);

	/**
	 * 
	 * <p>函数名称：        </p>
	 * <p>功能说明：根据表名、用户获取表的字段信息
	 *
	 * </p>
	 *<p>参数说明：</p>
	 * @param tableName
	 * @param owner
	 * @return
	 *
	 * @date   创建时间：2018年11月26日
	 * @author 作者：wuj
	 */
	List<TableField> queryTableFields(@Param("tableName")String tableName,@Param("owner")String owner);
	

}
