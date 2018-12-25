package com.wiesel.common.config;

/**
 *
 * @ClassName 类名：ActivityConst
 * @Description 功能说明：
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2018年12月13日
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录***************************************
 * 
 *          2018年12月13日 wuj 创建该类功能。
 *
 ************************************************************************
 *          </p>
 */
public class ActivityConst {

	public static String BASE_URL = "http://127.0.0.1:8888/bpmnx-standalone";

	public static String COMPLETE_TASK_URL = BASE_URL + "/bpmnx-standalone/process/task/completeTask/{taskId}";

	public static String START_PROCESS_INSTANCE_BY_ID_URL = BASE_URL
			+ "/bpmnx-standalone/process/instance/startProcessInstanceById/{processDefinitionId}/{businessKey}";

	
	public  static String START_PROCESS_INSTANCE_BY_KEY_URL ="/bpmnx-standalone/process/instance/startProcessInstanceByKey/{processDefinitionKey}/{businessKey}";
}
