package com.wiesel.common.base.entity;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * @ClassName 类名：PageResp
 * @Description 功能说明：
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2018年7月21日
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录*************************************
 * 
 *          2018年7月21日 wuj 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */
@Data
@Accessors(chain = true)//使用链式创建
public class PageResp<T> {

	/**
	 * 总数
	 */
	private Long total;

	/**
	 * 数据
	 */
	private List<T> rows;

}