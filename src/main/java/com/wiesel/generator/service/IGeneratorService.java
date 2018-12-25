package com.wiesel.generator.service;

import java.io.IOException;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wiesel.generator.entity.TableInfo;

/**
 *
 * @ClassName 类名：IGeneratorService
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
public interface IGeneratorService {

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
	IPage<TableInfo> queryTablePage(IPage page,String tableName,String owner);

	/**
	 * 
	 * <p>函数名称：        </p>
	 * <p>功能说明：根据用户和表名生成代码
	 *
	 * </p>
	 *<p>参数说明：</p>
	 * @param tableNames
	 * @param owner
	 * @return
	 * @throws IOException
	 *
	 * @date   创建时间：2018年11月27日
	 * @author 作者：wuj
	 */
	byte[] generatorCode(String[] tableNames,String owner) throws IOException;
}
