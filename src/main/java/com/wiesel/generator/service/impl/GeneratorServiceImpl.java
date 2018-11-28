package com.wiesel.generator.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wiesel.generator.entity.TableField;
import com.wiesel.generator.entity.TableInfo;
import com.wiesel.generator.mapper.GeneratorMapper;
import com.wiesel.generator.service.IGeneratorService;

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

	@SuppressWarnings("rawtypes")
	public IPage<TableInfo> queryTablePage(Page page, String tableName, String owner) {
		return generatorMapper.queryTablePage(page, tableName, owner);
	}

	@Override
	public byte[] generatorCode(String[] tableNames, String owner) throws IOException {
		// 配置信息
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(outputStream);

		for (String tableName : tableNames) {
			// 查询表字段信息
			List<TableField> tableFields = generatorMapper.queryTableFields(tableName, owner);
			
			TableInfo tableInfo = generatorMapper.queryTablePage(new Page<>(), tableName, owner).getRecords().get(0);
			// 生成代码
		//	GenUtils.generatorCode(tableName, tableFields, zip);
		}
		IOUtils.closeQuietly(zip);
		return outputStream.toByteArray();
	}

}
