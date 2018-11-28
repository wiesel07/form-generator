package com.wiesel.common.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.wiesel.common.config.properties.DruidProperties;

import cn.hutool.core.date.DateTime;
import lombok.extern.slf4j.Slf4j;

/** 
*
* @ClassName   类名：DruidConfig 
* @Description 功能说明：
* <p>
* TODO
*</p>
************************************************************************
* @date        创建日期：2018年11月23日
* @author      创建人：wuj
* @version     版本号：V1.0
*<p>
***************************修订记录***************************************
* 
*   2018年11月23日   wuj   创建该类功能。
*
************************************************************************
*</p>
*/
@Slf4j
@Configuration
public class DruidConfig {
	
	@Resource
    private DruidProperties druidProperties;
	
	@Bean(name = "dataSource", initMethod = "init", destroyMethod = "close") // 声明其为Bean实例
	@Primary // 在同样的DataSource中，首先使用被标注的DataSource
	public DataSource dataSource() {

		log.info(DateTime.now()+" 创建jdbc数据源:dataSource");
		DruidDataSource dataSource = new DruidDataSource();
		druidProperties.config(dataSource);
		return dataSource;
	}

	/**
	 * 注册一个StatViewServlet 相当于在web.xml中声明了一个servlet
	 */
	@Bean
	public ServletRegistrationBean druidServlet() {
		ServletRegistrationBean reg = new ServletRegistrationBean();
		reg.setServlet(new StatViewServlet());
		reg.addUrlMappings("/druid/*");
		reg.addInitParameter("allow", ""); // 白名单
		/** IP黑名单(共同存在时，deny优先于allow) */
		// reg.addInitParameter("deny", "10.211.61.4");

		/** 是否能够重置数据 禁用HTML页面上的“Reset All”功能 */
		// reg.addInitParameter("resetEnable", "false");
		return reg;
	}

	/**
	 * 注册一个：filterRegistrationBean 相当于在web.xml中声明了一个Filter
	 */
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new WebStatFilter());
		/** 添加过滤规则. */
		filterRegistrationBean.addUrlPatterns("/*");
		/** 监控选项滤器 */
		filterRegistrationBean.addInitParameter("DruidWebStatFilter", "/*");
		/** 添加不需要忽略的格式信息. */
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/monitor/druid/*");
		/** 配置profileEnable能够监控单个url调用的sql列表 */
		filterRegistrationBean.addInitParameter("profileEnable", "true");
		/** 当前的cookie的用户 */
		filterRegistrationBean.addInitParameter("principalCookieName", "USER_COOKIE");
		/** 当前的session的用户 */
		filterRegistrationBean.addInitParameter("principalSessionName", "USER_SESSION");
		return filterRegistrationBean;
	}
}

