package com.wiesel.dubbo;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;

/** 
*
* @ClassName   类名：ProviderServiceImpl 
* @Description 功能说明：
* <p>
* TODO
*</p>
************************************************************************
* @date        创建日期：2018年12月13日
* @author      创建人：wuj
* @version     版本号：V1.0
*<p>
***************************修订记录***************************************
* 
*   2018年12月13日   wuj   创建该类功能。
*
************************************************************************
*</p>
*/
@Service(interfaceClass = ProviderService.class)
@Component
public class ProviderServiceImpl implements ProviderService{
 
  @Override
  public String sayHello() {
    return "hello!!!";
  }
}
