package com.my.xymh.base;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.my.xymh.utils.HttpConstants;
import com.my.xymh.utils.JsonDateValueProcessor;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * Controller基类
 */
public class BaseController {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	protected final static String DATE_FORMATE = "yyyy-MM-dd";

	/**
	 * 返回服务端处理结果
	 * 
	 * @param obj
	 *            服务端输出对象
	 * @return 输出处理结果给前段JSON格式数据
	 */
	public String responseResult(Object obj) {
		JSONObject jsonObj = null;
		if (obj != null) {
			logger.info("后端返回对象：{}", obj);
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
			jsonObj = JSONObject.fromObject(obj, jsonConfig);
			logger.info("后端返回数据：" + jsonObj);
		}
		logger.info("输出结果：{}", jsonObj.toString());
		return jsonObj.toString();
	}

	/**
	 * 返回成功
	 * 
	 * @param obj
	 *            输出对象
	 * @return 输出成功的JSON格式数据
	 */
	public String responseSuccess(Object obj) {
		JSONObject jsonObj = null;
		if (obj != null) {
			logger.info("后端返回对象：{}", obj);
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
			jsonObj = JSONObject.fromObject(obj, jsonConfig);
		}
		logger.info("输出结果：{}", jsonObj.toString());
		return jsonObj.toString();
	}

	/**
	 * 返回成功
	 * 
	 * @param obj
	 *            输出对象
	 * @return 输出成功的JSON格式数据
	 */
	public String responseArraySuccess(Object obj) {
		JSONArray jsonObj = null;
		if (obj != null) {
			logger.info("后端返回对象：{}", obj);
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
			jsonObj = JSONArray.fromObject(obj, jsonConfig);
			logger.info("后端返回数据：" + jsonObj);
		}
		logger.info("输出结果：{}", jsonObj.toString());
		return jsonObj.toString();
	}

	/**
	 * 返回成功
	 * 
	 * @param obj
	 *            输出对象
	 * @return 输出成功的JSON格式数据
	 */
	public String responseSuccess(Object obj, String msg) {
		
		JSONObject jsonObj = null;
		JSONObject jsonObj2 = null;
		if (obj != null) {
			logger.info("后端返回对象：{}", obj);
			JsonConfig jsonConfig = new JsonConfig();
			//jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
			jsonObj = JSONObject.fromObject(obj);
			jsonObj2 = new JSONObject();
			jsonObj2.put("datas", jsonObj);
			jsonObj2.put("message", msg);
			logger.info("后端返回数据：" + jsonObj2);
		}
		logger.info("输出结果：{}", jsonObj2.toString());
		return jsonObj2.toString();
	}

	/**
	 * 返回失败
	 * 
	 * @param errorMsg
	 *            错误信息
	 * @return 输出失败的JSON格式数据
	 */
	public String responseFail(String errorMsg) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put(HttpConstants.RESPONSE_RESULT_FLAG_ISERROR, true);
		jsonObj.put(HttpConstants.SERVICE_RESPONSE_RESULT_MSG, errorMsg);
		logger.info("输出结果：{}", jsonObj.toString());
		return jsonObj.toString();
	}

	// 下面是判断null的操作

	public boolean isEmpty(String str) {
		return (null == str) || (str.trim().length() <= 0);
	}

	public boolean isEmpty(Character cha) {
		return (null == cha) || cha.equals(' ');
	}

	public boolean isEmpty(Object obj) {
		return (null == obj);
	}

	public boolean isEmpty(Object[] objs) {
		return (null == objs) || (objs.length <= 0);
	}

	public boolean isEmpty(Collection<?> obj) {
		return (null == obj) || obj.isEmpty();
	}

	public boolean isEmpty(Set<?> set) {
		return (null == set) || set.isEmpty();
	}

	public boolean isEmpty(Serializable obj) {
		return null == obj;
	}

	public boolean isEmpty(Map<?, ?> map) {
		return (null == map) || map.isEmpty();
	}

}
