package com.wiesel.generator.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wiesel.common.api.ApiResult;
import com.wiesel.common.base.entity.PageReq;
import com.wiesel.common.base.entity.PageResp;
import com.wiesel.common.config.properties.GeneratorProperties;
import com.wiesel.generator.entity.TableInfo;
import com.wiesel.generator.req.TableInfoReq;
import com.wiesel.generator.service.IGeneratorService;

/**
 *
 * @ClassName 类名：GeneratorController
 * @Description 功能说明：
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2018年11月26日
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录*************************************
 * 
 *          2018年11月26日 wuj 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */
@Controller
@RequestMapping("/generator")
public class GeneratorController {

	private String prefix = "generator";

	// 数据库用户名
	@Value("${spring.datasource.username}")
	private String OWNER;

	@Autowired
	private IGeneratorService generatorService;

	@GetMapping("/generator")
	String generator() {
		return prefix + "/generator";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResponseBody
	@GetMapping("/list")
	ApiResult<PageResp<TableInfo>> list(PageReq<TableInfo> pageReq, TableInfoReq tableInfoReq) {

		Page page = new Page(pageReq.getPageNo(), pageReq.getPageSize());
		page = (Page) generatorService.queryTablePage(page, tableInfoReq.getTableName(), OWNER);

		PageResp<TableInfo> pageResp = new PageResp<TableInfo>();
		pageResp.setRows(page.getRecords());
		pageResp.setTotal(page.getTotal());
		return ApiResult.ok(pageResp);
	}

	/**
	 * 
	 * <p>
	 * 函数名称：
	 * </p>
	 * <p>
	 * 功能说明：返回生成策略编辑界面
	 *
	 * </p>
	 * <p>
	 * 参数说明：
	 * </p>
	 * 
	 * @param model
	 * @return
	 *
	 * @date 创建时间：2018年11月27日
	 * @author 作者：wuj
	 */
	@GetMapping("/edit")
	String edit(Model model) {

		model.addAttribute("property",GeneratorProperties.getGeneratorProperties());
		return prefix + "/edit";
	}


	/**
	 * 
	 * <p>
	 * 函数名称：
	 * </p>
	 * <p>
	 * 功能说明：更新生成策略
	 *
	 * </p>
	 * <p>
	 * 参数说明：
	 * </p>
	 * 
	 * @param map
	 * @return
	 *
	 * @date 创建时间：2018年11月27日
	 * @author 作者：wuj
	 */
	@SuppressWarnings("static-access")
	@ResponseBody
	@PostMapping("/update")
	public ApiResult<String> update(GeneratorProperties generatorProperties) {
		try {
			PropertiesConfiguration conf = new PropertiesConfiguration("generator.properties");
			conf.setProperty(generatorProperties.AUTHOR, generatorProperties.getAuthor());
			conf.setProperty(generatorProperties.AUTO_REMOVE_PRE, generatorProperties.getAutoRemovePre());
			conf.setProperty(generatorProperties.TABLE_PRE_FIX, generatorProperties.getTablePrefix());
			conf.setProperty(generatorProperties.ENTITY_NAME, generatorProperties.getEntityName());
			conf.setProperty(generatorProperties.MAPPER_NAME, generatorProperties.getMapperName());
			conf.setProperty(generatorProperties.XML_NAME, generatorProperties.getXmlName());
			conf.setProperty(generatorProperties.SERVICE_IMPL_NAME, generatorProperties.getServiceImplName());
			conf.setProperty(generatorProperties.SERVICE_NAME, generatorProperties.getServiceName());
			conf.setProperty(generatorProperties.CONTROLLER_NAME, generatorProperties.getControllerName());
			conf.setProperty(generatorProperties.PARENT, generatorProperties.getParent());
			conf.setProperty(generatorProperties.MODULE_NAME, generatorProperties.getModuleName());
			conf.setProperty(generatorProperties.API_PATH,generatorProperties.getApiPath());
			conf.setProperty(generatorProperties.SPI_PATH,generatorProperties.getSpiPath());
			conf.setProperty(generatorProperties.MYBATIS_PATH,generatorProperties.getMybatisPath());
			conf.setProperty(generatorProperties.WEB_PATH,generatorProperties.getWebPath());
			conf.setProperty(generatorProperties.XML_NAME,generatorProperties.getXmlPath());
			conf.setProperty(generatorProperties.JS_PATH,generatorProperties.getJsPath());
			conf.setProperty(generatorProperties.JSP_PATH,generatorProperties.getJsPath());
			conf.save();
		} catch (ConfigurationException e) {
			return ApiResult.error("保存配置文件出错");
		}
		return ApiResult.ok();
	}
	
	
	/**
	 * 
	 * <p>
	 * 函数名称：
	 * </p>
	 * <p>
	 * 功能说明：根据表名生成代码
	 *
	 * </p>
	 * <p>
	 * 参数说明：
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param tableName
	 * @throws IOException
	 *
	 * @date 创建时间：2018年8月31日
	 * @author 作者：wuj
	 */
	@RequestMapping("/code/{tableName}")
	public void code(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("tableName") String tableName) throws IOException {
		String[] tableNames = new String[] { tableName };
		byte[] data = generatorService.generatorCode(tableNames,OWNER);
		response.reset();
		response.setHeader("Content-Disposition", "attachment; filename=\"wiesel.zip\"");
		response.addHeader("Content-Length", "" + data.length);
		response.setContentType("application/octet-stream; charset=UTF-8");

		IOUtils.write(data, response.getOutputStream());
	}

}
