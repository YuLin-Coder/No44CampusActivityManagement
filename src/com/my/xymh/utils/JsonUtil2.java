package com.my.xymh.utils;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import net.sf.ezmorph.MorphException;
import net.sf.ezmorph.MorpherRegistry;
import net.sf.ezmorph.object.AbstractObjectMorpher;
import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;

public class JsonUtil2 {

	private static final String[] DATE_FORMAT = { "yyyy-MM-dd HH:mm:ss" }; 
	
	
	
    public static String toJson(Object o) {
    	
    	JsonConfig jsonConfig = new JsonConfig();  
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor());
		jsonConfig.registerJsonValueProcessor(Timestamp.class, new DateJsonValueProcessor());
    	System.out.println(JSONObject.fromObject(o));
		System.out.println(JSONObject.fromObject(o, jsonConfig).toString());
    	try {
			return JSONObject.fromObject(o, jsonConfig).toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
    
   /**
    * 获取jsonObject对象
    * @return
    */
    public static JSONObject getJsonObject(){
    	return new JSONObject();
    }
    /**
     * 获取JSONArray对象
     * @return
     */
    public static JSONArray getJSONArray(){
    	return new JSONArray();
    }
    /***
     * 
    * @Title: toJsonArray 
    * @Description:把一个List转换为json字符串
    * @param @param o
    * @param @return    设定文件 
    * @return String    返回类型 
    * @Author 
    * @throws
     */
    public static String toJsonArray(Object o) {
    	
    	JsonConfig jsonConfig = new JsonConfig();  
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor());
		jsonConfig.registerJsonValueProcessor(Timestamp.class, new DateJsonValueProcessor());
    	
    	try {
			return JSONArray.fromObject(o, jsonConfig).toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
	@SuppressWarnings("unchecked")
	public static <T> T toBean(String jsonStr, Class<T> clazz) {
		T returnValue = null;
		
		MorpherRegistry morpherRegistry = JSONUtils.getMorpherRegistry();
		morpherRegistry.registerMorpher(new DateMorpher(DATE_FORMAT));
		morpherRegistry.registerMorpher(new TimestampMorpher(DATE_FORMAT)); 
		
		try {
			JSONObject obj = JSONObject.fromObject(jsonStr);
			returnValue = (T) JSONObject.toBean(obj, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnValue;
	}
	
	
//	private static ObjectMapper objectMapper = null;
//    
//    static {
//    	objectMapper = new ObjectMapper(); // can reuse, share globally
//		objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
//		objectMapper.setSerializationInclusion(Include.NON_NULL);
//		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
//    }
//    
//    public static ObjectMapper getObjectMapper() {
//    	return objectMapper;
//    }
//
//    public static String toJson2(Object o) {
//    	try {
//			return objectMapper.writeValueAsString(o);
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}
//    }
//    
//    public static <T> T toBean2(String jsonStr, Class<T> clazz) {
//    	T returnValue = null;
//    	try {
//    		returnValue = objectMapper.readValue(jsonStr, clazz);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return returnValue;
//    }
//    
    public static void main(String[] args) throws IOException {
//    	Map map = new HashMap();
//    	map.put("total", 0);
//    	map.put("rows", new Object[0]);
//    	map.put("sessionStatus", 0);
//    	
//    	System.out.println(toJson(map));
    }
}

class DateJsonValueProcessor implements net.sf.json.processors.JsonValueProcessor {
	public Object processArrayValue(Object object, JsonConfig jsonConfig) {
		return null;
	}
	public Object processObjectValue(String string, Object object, JsonConfig jsonConfig) {
		if (null == object) {
			return "";
		} else {
			if (object instanceof Timestamp) {
				return DateUtil.format((java.util.Date)object);
			}
			else if (object instanceof java.util.Date) {
				return DateUtil.format((java.util.Date)object);
			}
		}

		return object.toString();
	}
}

class TimestampMorpher extends AbstractObjectMorpher {
	/*** 日期字符串格式 */
	private String[] formats;

	public TimestampMorpher(String[] formats) {
		this.formats = formats;
	}

	public Object morph(Object value) {
		if (value == null || "".equals(value)) {
			return null;
		}
		if (Timestamp.class.isAssignableFrom(value.getClass())) {
			return (Timestamp) value;
		}
		if (!supports(value.getClass())) {
			throw new MorphException(value.getClass() + " 是不支持的类型");
		}
		String strValue = (String) value;
		SimpleDateFormat dateParser = null;
		for (int i = 0; i < formats.length; i++) {
			if (null == dateParser) {
				dateParser = new SimpleDateFormat(formats[i]);
			} else {
				dateParser.applyPattern(formats[i]);
			}
			try {
				return new Timestamp(dateParser.parse(strValue.toLowerCase()).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}
		return null;
	}

	@Override
	public Class morphsTo() {
		return Timestamp.class;
	}

	@Override
	public boolean supports(Class clazz) {
		return String.class.isAssignableFrom(clazz);
	}

}