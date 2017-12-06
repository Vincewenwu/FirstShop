package me.ilt.common.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/** 
 * @ClassName: AppParamMemoryUtils 
 * @Description: 读入内存AppParamCode 
 *  
 */
public final class AppParamMemoryUtils {
	
	/** 
	  * @Fields paramCodeMap : 存放应用系统字典的Map,nickName和value,name
	  */ 
	private static Map<String,Map<String,String>> paramCodeMap = new HashMap<String,Map<String,String>>();
	
	/** 
	  * <p>Title: 私有构�?方法</p> 
	  * <p>Description:为了实现单例 </p>  
	  */ 
	private AppParamMemoryUtils(){
	}
	/** 
	  * @ClassName: DictionaryMemoryUtilsHolder 
	  * @Description:私有静�?类，用来存放单例 
	  * @date 2011-10-6 下午2:57:10 
	  *  
	  */
	private static class AppParamMemoryUtilsHolder{
		static final AppParamMemoryUtils INSTANCE = new AppParamMemoryUtils();
	}
	
	/** 
	  * @Title: getInstance 
	  * @Description: 获得单例对象
	  * @param @return
	  * @return DictionaryMemoryUtils
	  * @throws 
	  */
	public static AppParamMemoryUtils getInstance(){
		return AppParamMemoryUtilsHolder.INSTANCE;
	}
	
	/** 
	  * @Title: getParamCodeMap 
	  * @Description:获得字典map 
	  * @param @return
	  * @return Map<String,Map<String,String>>
	  * @throws 
	  */
	public Map<String, Map<String, String>> getParamCodeMap() {
		return paramCodeMap;
	}

	/** 
	  * @Title: setDictionarysMap 
	  * @Description:设置字典map 
	  * @param @param dictionarysMap
	  * @return void
	  * @throws 
	  */
	public void setParamCodeMapMap(
			Map<String, Map<String, String>> paramCodeMap) {
		AppParamMemoryUtils.paramCodeMap = paramCodeMap;
	}

	
	/** 
	  * @Title: getMapByNickName 
	  * @Description: 根据nickName获得value与name的键值对 
	  * @param @param nickName
	  * @param @return
	  * @return Map<String,String>
	  * @throws 
	  */
	public Map<String,String> getMapByDomainName(String nickName){
		return paramCodeMap.get(nickName);
	}
	
	/** 
	  * @Title: getNameByValue 
	  * @Description: 根据value获得name 
	  * @param @param value
	  * @param @return
	  * @return String
	  * @throws 
	  */
	public String getNameByValue(String value){
		Map<String,String> valueNameMap = new HashMap<String,String>();
		Set<String> keys = paramCodeMap.keySet();
		for(Iterator<String> iter=keys.iterator();iter.hasNext();){
			valueNameMap.putAll(paramCodeMap.get(iter.next()));
		}
		return valueNameMap.get(value);
	}
	/**
	 * @Description: 获取�?
	 * @param nickName
	 * @param key
	 * @return
	 */
	public String getNameByNickAndKey(String nickName,String key){
		Map<String,String> tmpMap = paramCodeMap.get(nickName);
		if(tmpMap!=null)
			return tmpMap.get(key);
		else
			return null;
	}
	/**
	 * @Description: 刷新键�?�?
	 * @param nickName
	 * @param name
	 * @param value
	 * @return
	 */
	public void refreshDic(String nickName,String name,String value){
		paramCodeMap.get(nickName).put(value, name);
	}	
}
