package com.wiesel.common.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import com.wiesel.common.config.properties.GeneratorProperties;
import com.wiesel.common.enums.DbColumnType;
import com.wiesel.common.enums.IColumnType;
import com.wiesel.generator.entity.TableField;
import com.wiesel.generator.entity.TableInfo;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @ClassName 类名：GenUtils
 * @Description 功能说明：
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2018年11月27日
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录***************************************
 * 
 *          2018年11月27日 wuj 创建该类功能。
 *
 ************************************************************************
 *          </p>
 */
@Slf4j
@UtilityClass
public class GenUtils {

	// 无明细表的表单生成
	public void generatorCode(TableInfo tableInfo, List<TableField> tableFields, ZipOutputStream zip) {
		GeneratorProperties generatorProperties = GeneratorProperties.getGeneratorProperties();
		tableInfo = getTableInfo(generatorProperties, tableInfo, tableFields);
		VelocityContext context = getVelocityContext(generatorProperties, tableInfo, tableFields);

		// 获取模板列表
		List<String> templates = getTemplates(false);
		for (String template : templates) {
			// 渲染模板
			StringWriter sw = new StringWriter();
			Template tpl = Velocity.getTemplate(template, "UTF-8");
			tpl.merge(context, sw);
			try {
				// 添加到zip
				log.info(template);
				zip.putNextEntry(new ZipEntry(getFileName(template, tableInfo, generatorProperties)));
				IOUtils.write(sw.toString(), zip, "UTF-8");

				IOUtils.closeQuietly(sw);
				zip.closeEntry();
			} catch (IOException e) {
				e.printStackTrace();
				log.info("渲染模板失败，表名：" + tableInfo.getTableName());
			}
		}

	}

	// 有明细表的表单生成
	public void generatorCode(TableInfo tableInfo, List<TableField> tableFields, TableInfo detailTableInfo,
			List<TableField> detailTableFields, ZipOutputStream zip) {
		GeneratorProperties generatorProperties = GeneratorProperties.getGeneratorProperties();
		tableInfo = getTableInfo(generatorProperties, tableInfo, tableFields);
		detailTableInfo = getTableInfo(generatorProperties, detailTableInfo, detailTableFields);
		VelocityContext context = getVelocityContext(generatorProperties, tableInfo, tableFields,
				detailTableInfo, detailTableFields);

		// 获取模板列表
		List<String> templates = getTemplates(true);
		for (String template : templates) {
			// 渲染模板
			StringWriter sw = new StringWriter();
			Template tpl = Velocity.getTemplate(template, "UTF-8");
			tpl.merge(context, sw);
			try {
				// 添加到zip
				log.info(template);
				zip.putNextEntry(new ZipEntry(getFileName(template, tableInfo, generatorProperties)));
				zip.putNextEntry(new ZipEntry(getFileName(template, detailTableInfo, generatorProperties)));
				IOUtils.write(sw.toString(), zip, "UTF-8");

				IOUtils.closeQuietly(sw);
				zip.closeEntry();
			} catch (IOException e) {
				e.printStackTrace();
				log.info("渲染模板失败，表名：" + tableInfo.getTableName());
			}
		}

	}

	/**
	 * 获取文件名
	 */
	private String getFileName(String template, TableInfo tableInfo,
			GeneratorProperties generatorProperties) {

		String capitalClassName = tableInfo.getCapitalClassName();
		String classname = tableInfo.getClassName();

		String capitalEntityName = String.format(generatorProperties.getEntityName(), capitalClassName);
		String capitalMapperName = String.format(generatorProperties.getMapperName(), capitalClassName);
		String capitalXmlName = String.format(generatorProperties.getXmlName(), capitalClassName);
		String capitalServiceName = String.format(generatorProperties.getServiceName(), capitalClassName);
		String capitalServiceImplName = String.format(generatorProperties.getServiceImplName(),
				capitalClassName);
		String capitalControllerName = String.format(generatorProperties.getControllerName(),
				capitalClassName);
		String packagePath="";
		if (template.contains("Entity.java.vm")) {
			
		}
		
		// String packagePath = "src" + File.separator + "main" + File.separator
		// + "java";
		// if (StringUtils.isNotBlank(packageName)) {
		// packagePath += File.separator + packageName.replace(".",
		// File.separator);
		// }
		// if (StringUtils.isNotBlank(moduleName)) {
		// packagePath += File.separator + moduleName;
		// }
		//
		// if (template.contains("Entity.java.vm") && !template.contains("Req"))
		// {
		// return packagePath + File.separator + "entity" + File.separator +
		// entityName + ".java";
		// }
		//
		// if (template.contains("Mapper.java.vm")) {
		// return packagePath + File.separator + "mapper" + File.separator +
		// mapperName + ".java";
		// }
		//
		// if (template.contains("Service.java.vm")) {
		// return packagePath + File.separator + "service" + File.separator +
		// serviceName + ".java";
		// }
		//
		// if (template.contains("ServiceImpl.java.vm")) {
		// return packagePath + File.separator + "service" + File.separator +
		// "impl" + File.separator + serviceImplName
		// + ".java";
		// }
		//
		// if (template.contains("Controller.java.vm")) {
		// return packagePath + File.separator + "controller" + File.separator +
		// controllerName + ".java";
		// }
		//
		// if (template.contains("ReqEntity.java.vm")) {
		// return packagePath + File.separator + "controller" + File.separator +
		// "req" + File.separator + reqEntityName
		// + ".java";
		// }
		//
		// // html
		// String htmlPrefix = "src" + File.separator + "main" + File.separator
		// + "resources";
		// String jsPrefix = "src" + File.separator + "main" + File.separator +
		// "resources";
		// String xmlPrefix = "src" + File.separator + "main" + File.separator +
		// "resources";
		// if (StrUtil.isNotBlank(moduleName)) {
		// htmlPrefix += File.separator + "templates" + File.separator +
		// moduleName;
		// jsPrefix += File.separator + "static" + File.separator + "js" +
		// File.separator + "app" + File.separator
		// + moduleName;
		// xmlPrefix += File.separator + "mapper" + File.separator + moduleName;
		// } else {
		// htmlPrefix += File.separator + "templates";
		// jsPrefix += File.separator + "static" + File.separator + "js" +
		// File.separator + "app";
		// xmlPrefix += File.separator + "mapper";
		// }
		//
		// if (template.contains("Mapper.xml.vm")) {
		// return xmlPrefix + File.separator + xmlName + ".xml";
		// }
		//
		// if (template.contains("list.html.vm")) {
		// return htmlPrefix + File.separator + classname + File.separator +
		// classname + ".html";
		// }
		//
		// if (template.contains("add.html.vm")) {
		// return htmlPrefix + File.separator + classname + File.separator +
		// "add.html";
		// }
		// if (template.contains("edit.html.vm")) {
		// return htmlPrefix + File.separator + classname + File.separator +
		// "edit.html";
		// }
		//
		// // js
		// if (template.contains("list.js.vm")) {
		// return jsPrefix + File.separator + classname + File.separator +
		// classname + ".js";
		// }
		//
		// if (template.contains("add.js.vm")) {
		// return jsPrefix + File.separator + classname + File.separator +
		// "add.js";
		// }
		//
		// if (template.contains("edit.js.vm")) {
		// return jsPrefix + File.separator + classname + File.separator +
		// "edit.js";
		// }
		//
		// if (template.contains("menu.sql.vm")) {
		// return classname + "_menu.sql";
		// }

		return null;
	}

	public List<String> getTemplates(Boolean isHasDetailTable) {
		List<String> templates = new ArrayList<String>();
		if (isHasDetailTable) {

		} else {

			templates.add("vm/java/Entity.java.vm");
			templates.add("vm/java/Mapper.java.vm");
			templates.add("vm/java/Service.java.vm");
			templates.add("vm/java/ServiceImpl.java.vm");
			templates.add("vm/java/Controller.java.vm");

			templates.add("vm/xml/Mapper.xml.vm");
			templates.add("vm/xml/index.xml.vm");
			templates.add("vm/xml/add.xml.vm");

			templates.add("vm/js/index.js.vm");
			templates.add("vm/js/add.js.vm");
		}

		return templates;
	}

	private VelocityContext getVelocityContext(GeneratorProperties generatorProperties, TableInfo tableInfo,
			List<TableField> tableFields) {

		// 设置velocity资源加载器
		Properties prop = new Properties();
		prop.put("file.resource.loader.class",
				"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		Velocity.init(prop);

		return new VelocityContext(assembleData(generatorProperties, tableInfo));
	}

	private VelocityContext getVelocityContext(GeneratorProperties generatorProperties, TableInfo tableInfo,
			List<TableField> tableFields, TableInfo detailTableInfo, List<TableField> detailTableFields) {

		// 设置velocity资源加载器
		Properties prop = new Properties();
		prop.put("file.resource.loader.class",
				"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		Velocity.init(prop);

		Map<String, Object> result = new HashMap<>();
		Map<String, Object> main = assembleData(generatorProperties, tableInfo);
		Map<String, Object> detail = assembleData(generatorProperties, detailTableInfo);
		result.put("main", main);
		result.put("detail", detail);
		return new VelocityContext(result);
	}

	// 组装模板数据
	private Map<String, Object> assembleData(GeneratorProperties generatorProperties, TableInfo tableInfo) {
		// 封装模板数据
		Map<String, Object> map = new HashMap<>();

		String capitalClassName = tableInfo.getCapitalClassName();
		map.put("tableName", tableInfo.getTableName());
		map.put("comments", tableInfo.getComments());
		map.put("pk", tableInfo.getPk());
		map.put("className", tableInfo.getClassName());
		map.put("capitalClassname", capitalClassName);
		map.put("tableFields", tableInfo.getTableFields());
		map.put("parent", generatorProperties.getParent());
		String moduleName = generatorProperties.getModuleName();
		map.put("moduleName", moduleName);
		if (StrUtil.isNotBlank(moduleName)) {
			map.put("packageName", generatorProperties.getParent() + "." + moduleName);
		} else {
			map.put("packageName", generatorProperties.getParent());
		}
		map.put("author", generatorProperties.getAuthor());
		map.put("createDate", DateUtil.format(DateUtil.date(), DatePattern.NORM_DATE_PATTERN));

		// 实体
		String capitalEntityName = String.format(generatorProperties.getEntityName(), capitalClassName);
		map.put("capitalEntityName", capitalEntityName);
		map.put("entityName", capitalEntityName);

		// mapper
		String capitalMapperName = String.format(generatorProperties.getMapperName(), capitalClassName);
		map.put("capitalMapperName", capitalMapperName);
		map.put("mapperName", StringUtils.uncapitalize(capitalMapperName));

		map.put("xmlName", String.format(generatorProperties.getXmlName(), capitalClassName));

		// 服务层接口
		String capitalServiceName = String.format(generatorProperties.getServiceName(), capitalClassName);
		map.put("capitalServiceName", capitalServiceName);
		map.put("serviceName", StringUtils.uncapitalize(capitalServiceName));

		map.put("serviceImplName", String.format(generatorProperties.getServiceImplName(), capitalClassName));
		map.put("controllerName", String.format(generatorProperties.getControllerName(), capitalClassName));

		return map;
	}

	// 生成渲染表的信息
	private TableInfo getTableInfo(GeneratorProperties generatorProperties, TableInfo tableInfo,
			List<TableField> tableFields) {

		// 表名转换成Java类名
		String className = tableToJava(tableInfo.getTableName(), generatorProperties.getTablePrefix());
		tableInfo.setCapitalClassName(className);// 设置类名(第一个字母大写)
		tableInfo.setClassName(StringUtils.uncapitalize(className)); // 设置类名(第一个字母小写)

		for (TableField tableField : tableFields) {
			tableField.setPropertyType(processTypeConvert(tableField.getDataType()).getType());

			// 列名转换成Java属性名
			String capitalName = columnToJava(tableField.getColumnName());
			tableField.setCapitalName(capitalName);
			tableField.setPropertyName(StringUtils.uncapitalize(capitalName));
			// 是否主键
			if ("PRI".equalsIgnoreCase(tableField.getKeys()) && tableField.getKeys() == null) {
				tableInfo.setPk(tableField);
			}
		}
		tableInfo.setTableFields(tableFields);
		return tableInfo;
	}

	/**
	 * 
	 * <p>
	 * 函数名称：
	 * </p>
	 * <p>
	 * 功能说明：表名转换成Java类名(替换指定表前缀为空)
	 *
	 * </p>
	 * <p>
	 * 参数说明：
	 * </p>
	 * 
	 * @param tableName
	 * @param tablePrefix
	 * @return
	 *
	 * @date 创建时间：2018年8月22日
	 * @author 作者：wuj
	 */
	private String tableToJava(String tableName, String tablePrefix) {
		if (StringUtils.isNotBlank(tablePrefix)) {
			tableName = tableName.replace(tablePrefix, "");
		}
		return columnToJava(tableName);
	}

	/**
	 * 列名转换成Java属性名
	 */
	private String columnToJava(String columnName) {
		return WordUtils.capitalizeFully(columnName, new char[] { '_' }).replace("_", "");
	}

	private IColumnType processTypeConvert(String fieldType) {
		String t = fieldType.toLowerCase();
		if (t.contains("char")) {
			return DbColumnType.STRING;
		} else if (t.contains("date") || t.contains("timestamp")) {
			// switch (globalConfig.getDateType()) {
			// case ONLY_DATE:
			// return DbColumnType.DATE;
			// case SQL_PACK:
			// return DbColumnType.TIMESTAMP;
			// case TIME_PACK:
			// return DbColumnType.LOCAL_DATE_TIME;
			// }
		} else if (t.contains("number")) {
			if (t.matches("number\\(+\\d\\)")) {
				return DbColumnType.INTEGER;
			} else if (t.matches("number\\(+\\d{2}+\\)")) {
				return DbColumnType.LONG;
			}
			return DbColumnType.DOUBLE;
		} else if (t.contains("float")) {
			return DbColumnType.FLOAT;
		} else if (t.contains("clob")) {
			return DbColumnType.CLOB;
		} else if (t.contains("blob")) {
			return DbColumnType.BLOB;
		} else if (t.contains("binary")) {
			return DbColumnType.BYTE_ARRAY;
		} else if (t.contains("raw")) {
			return DbColumnType.BYTE_ARRAY;
		}
		return DbColumnType.STRING;
	}
}
