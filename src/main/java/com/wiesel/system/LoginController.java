package com.wiesel.system;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
*
* @ClassName   类名：LoginController 
* @Description 功能说明：
* <p>
* TODO
*</p>
************************************************************************
* @date        创建日期：2018年11月26日
* @author      创建人：wuj
* @version     版本号：V1.0
*<p>
***************************修订记录***************************************
* 
*   2018年11月26日   wuj   创建该类功能。
*
************************************************************************
*</p>
*/

@RequestMapping("")
@Controller
public class LoginController {

	
	@GetMapping({ "/", "" })
	String welcome(Model model) {
		// return "redirect:/blog";
		return "index";
	}
	
	
	@GetMapping("main")
	String main() {
		return "main";
	}
}
