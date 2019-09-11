package com.shareku.util;

import java.util.List;
import java.util.Map;

/**
 * @author xuleilei
 *
 */
public interface IConfigure {
	/**
	 * 加载配置文件
	 * @param path
	 */
	public void load(String path);
	/**
	 * 根据key获取value，默认返回的值为String类型
	 * @param key
	 * @return value
	 */
	public String getValue(String key);
	/**
	 * 获取所有属性
	 * @return
	 */
	public Map<String,String> getAllInfo();
	/**
	 * 获取指定属性值，clazz参数指定返回结果类型
	 * @param key
	 * @param clazz
	 * @return
	 */
	public <T> T getValue(String key,Class<T> clazz);
	/**
	 * 当属性文件中key字符串包含指定字符串时，返回所有value集合
	 * @param List<String> 符合条件的所有value
	 */
	public List<String> getValues(String string);
}
