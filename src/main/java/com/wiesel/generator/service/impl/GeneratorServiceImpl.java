package com.wiesel.generator.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.wiesel.common.config.properties.GeneratorProperties;
import com.wiesel.common.exception.ApiException;
import com.wiesel.common.utils.GenUtils;
import com.wiesel.generator.entity.TableField;
import com.wiesel.generator.entity.TableInfo;
import com.wiesel.generator.mapper.GeneratorMapper;
import com.wiesel.generator.service.IGeneratorService;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

/**
 *
 * @ClassName 类名：GeneratorServiceImpl
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
@Service
public class GeneratorServiceImpl implements IGeneratorService {

	@Autowired
	private GeneratorMapper generatorMapper;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Page<TableInfo> queryTablePage(Page page, String tableName, String owner) {
		List<TableInfo> tableInfos = generatorMapper.queryTablePage(page, tableName, owner);
		int total = generatorMapper.queryTableCount(tableName, owner);
		page.setRecords(tableInfos);
		page.setTotal(total);
		return page;
	}

	@Override
	public byte[] generatorCode(String[] tableNames, String owner) throws IOException {
		// 配置信息
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(outputStream);
		GeneratorProperties generatorProperties = GeneratorProperties.getGeneratorProperties();

		// 判断配置属性里是否有配置明细表属性，有判断表是否存在，获取相应明细表信息
		String detailTableName = generatorProperties.getDetailTableName();

		TableInfo detailTableInfo = generatorMapper.queryTablePage(new Page<>(), detailTableName, owner)
				.get(0);
		if (ObjectUtil.isNull(detailTableInfo)) {
			throw new ApiException("明细表不存在");
		}
		List<TableField> detailTableFields = generatorMapper.queryTableFields(detailTableName, owner);

		for (String tableName : tableNames) {
			// 查询表字段信息
			List<TableField> tableFields = generatorMapper.queryTableFields(tableName, owner);

			TableInfo tableInfo = generatorMapper.queryTablePage(new Page<>(), tableName, owner).get(0);
			// 生成代码
			GenUtils.generatorCode(tableInfo, tableFields, detailTableInfo, detailTableFields, zip);
			break;
		}

		IOUtils.closeQuietly(zip);
		return outputStream.toByteArray();
	}

}
