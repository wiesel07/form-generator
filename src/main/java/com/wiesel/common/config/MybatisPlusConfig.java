package com.wiesel.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;

import cn.hutool.core.date.DateTime;
import lombok.extern.java.Log;

/**
 *
 * @ClassName 类名：MybatisPlusConfig
 * @Description 功能说明：
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2018年11月23日
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录***************************************
 * 
 *          2018年11月23日 wuj 创建该类功能。
 *
 ************************************************************************
 *          </p>
 */
@Log
@Configuration
//@MapperScan("com.wiesel.**.mapper")
@MapperScan(basePackages = {"com.wiesel.**.mapper"})
public class MybatisPlusConfig {


	/**
	 * 
	 * <p>
	 * 函数名称：
	 * </p>
	 * <p>
	 * 功能说明：配置mybatis-plus分页插件
	 *
	 * </p>
	 * <p>
	 * 参数说明：
	 * </p>
	 * 
	 * @return
	 *
	 * @date 创建时间：2018年8月30日
	 * @author 作者：wuj
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		return new PaginationInterceptor();
	}

	/**
	 * 
	 * <p>
	 * 函数名称：
	 * </p>
	 * <p>
	 * 功能说明：配置事务管理
	 *
	 * </p>
	 * <p>
	 * 参数说明：
	 * </p>
	 * 
	 * @param dataSource
	 * @return
	 *
	 * @date 创建时间：2018年8月30日
	 * @author 作者：wuj
	 */
	@DependsOn("dataSource")
	@Bean(name = "transactionManager")
	public DataSourceTransactionManager transactionManager(
			@Qualifier(value = "dataSource") DruidDataSource dataSource) {
		log.info(DateTime.now() + " 配置事务管理");
		return new DataSourceTransactionManager(dataSource);
	}

	/**
	 * 性能分析拦截器，不建议生产使用
	 */
	@Bean
	public PerformanceInterceptor performanceInterceptor() {
		PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
		/* <!-- SQL 执行性能分析，开发环境使用，线上不推荐。 maxTime 指的是 sql 最大执行时长 --> */
		performanceInterceptor.setMaxTime(3000);
		/* <!--SQL是否格式化 默认false--> */
		performanceInterceptor.setFormat(false);
		return performanceInterceptor;
	}



}
