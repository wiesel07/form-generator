package com.wiesel.activity;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wiesel.common.api.ApiResult;
import com.wiesel.common.config.ActivityConst;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @ClassName 类名：ActivityController
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

@Slf4j
@Controller
@RequestMapping("/activity")
public class ActivityController {
	String BASE_URL = "http://127.0.0.1:8888/bpmnx-standalone";
	private String prefix = "activity";

	@GetMapping("/activity")
	String generator() {
		return prefix + "/activity";
	}

	@ResponseBody
	@PostMapping("/start")
	public ApiResult<String> start() {

//		RestTemplate restTemplate = new RestTemplate();
//		Map<String, Object> urlParams = new HashMap<>();
//		urlParams.put("taskId", "77777");
//
//		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ActivityConst.START_PROCESS_INSTANCE_BY_ID_URL);
//		// .queryParam("pageNum", 1)
//		// .queryParam("pageSize", 10);
//
//		JSONObject requestBody = new JSONObject();
//		requestBody.put("variables", "");
//
//		HttpHeaders httpHeaders = new HttpHeaders();
//		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//
//		HttpEntity<String> requestEntity = new HttpEntity<>(requestBody.toJSONString(), httpHeaders);
//		ResponseEntity<String> responseEntity = restTemplate.postForEntity(builder.toUriString(), requestEntity,
//				String.class, urlParams);
//		String body = JSON.toJSONString(responseEntity.getBody());
//		log.info(body);

		return ApiResult.ok();
	}
	
	
	@ResponseBody
	@PostMapping("/complete")
	public ApiResult<String> complete() {

//		RestTemplate restTemplate = new RestTemplate();
//		Map<String, Object> urlParams = new HashMap<>();
//		urlParams.put("taskId", "77777");
//
//		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ActivityConst.START_PROCESS_INSTANCE_BY_ID_URL);
//		// .queryParam("pageNum", 1)
//		// .queryParam("pageSize", 10);
//
//		JSONObject requestBody = new JSONObject();
//		requestBody.put("variables", "");
//
//		HttpHeaders httpHeaders = new HttpHeaders();
//		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//
//		HttpEntity<String> requestEntity = new HttpEntity<>(requestBody.toJSONString(), httpHeaders);
//		ResponseEntity<String> responseEntity = restTemplate.postForEntity(builder.toUriString(), requestEntity,
//				String.class, urlParams);
//		String body = JSON.toJSONString(responseEntity.getBody());
//		log.info(body);

		return ApiResult.ok();
	}
}
