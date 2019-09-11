package com.shareku.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

/**
 * @author xuleilei
 *
 */
public class Configure implements IConfigure {
	private Properties props;
	private InputStream inStream;
	
	public Configure(){
		
	}
	public Configure(String path){
		this.load(path);
	}
	public Properties getProps() {
		return this.props;
	}
	
	@Override
	public void load(String path) {
		this.props = new Properties();
		this.inStream = this.getClass().getClassLoader().getResourceAsStream(path);
		try {
			this.props.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getValue(String key) {
		String value = StringUtils.trimToEmpty(this.props.getProperty(key));
		return value;
	}
	
	@Override
	public <T> T getValue(String key,Class<T> clazz) {
		String value = StringUtils.trimToEmpty(this.props.getProperty(key));
		
		T res = null;
		try {
			res = clazz.getConstructor(String.class).newInstance(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public Map<String, String> getAllInfo() {
		Map<String, String> map = new HashMap<String,String>();
		Set<Map.Entry<Object,Object>> set = this.props.entrySet();
		Iterator<Map.Entry<Object,Object>> iter = set.iterator();
		while(iter.hasNext()){
			Entry<Object,Object> entry = iter.next();
			String key = (String)entry.getKey();
			String value = (String)entry.getValue();
			map.put(key, value);
		}
		return map;
	}
	
	@Override
	public List<String> getValues(String string) {
		List<String> res = new ArrayList<String>();
		Set<Object> keys = this.props.keySet();
		for(Object obj : keys){
			if(obj.toString().startsWith(string)){
				res.add(this.getValue(obj.toString()));
			}
		}
		return res;
	}
	public static void main(String[] args) {
		Configure conf = new Configure("config.properties");
		System.out.println(conf.getValue("mail.smtp.host"));
	}
	
}
