package me.ilt.common.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/** 
 * @ClassName: DictionaryMemoryUtils 
 * @Description: �����ڴ�Dictionary
 * @author XXXX@163.com
 * @date 2011-10-2 ����10:08:47 
 *  
 */
public final class DictionaryMemoryUtils {
	
	/** 
	  * @Fields dictionarysMap : ���ϵͳ�ֵ��Map,nickName��value,name
	  */ 
	private static Map<String,Map<String,String>> dictionarysMap = new HashMap<String,Map<String,String>>();
	
	/** 
	  * <p>Title: ˽�й��췽��</p> 
	  * <p>Description:Ϊ��ʵ�ֵ��� </p>  
	  */ 
	private DictionaryMemoryUtils(){
	}
	/** 
	  * @ClassName: DictionaryMemoryUtilsHolder 
	  * @Description:˽�о�̬�࣬������ŵ��� 
	  * @author XXXX@163.com 
	  * @date 2011-10-6 ����2:57:10 
	  *  
	  */
	private static class DictionaryMemoryUtilsHolder{
		static final DictionaryMemoryUtils INSTANCE = new DictionaryMemoryUtils();
	}
	
	/** 
	  * @Title: getInstance 
	  * @Description: ��õ�������
	  * @param @return
	  * @return DictionaryMemoryUtils
	  * @throws 
	  */
	public static DictionaryMemoryUtils getInstance(){
		return DictionaryMemoryUtilsHolder.INSTANCE;
	}
	
	/** 
	  * @Title: getDictionarysMap 
	  * @Description:����ֵ�map 
	  * @param @return
	  * @return Map<String,Map<String,String>>
	  * @throws 
	  */
	public Map<String, Map<String, String>> getDictionarysMap() {
		return dictionarysMap;
	}

	/** 
	  * @Title: setDictionarysMap 
	  * @Description:�����ֵ�map 
	  * @param @param dictionarysMap
	  * @return void
	  * @throws 
	  */
	public void setDictionarysMap(Map<String, Map<String, String>> dictionarysMap) {
		DictionaryMemoryUtils.dictionarysMap = dictionarysMap;
	}

	
	/** 
	  * @Title: getMapByNickName 
	  * @Description: ����nickName���value��name�ļ�ֵ�� 
	  * @param @param nickName
	  * @param @return
	  * @return Map<String,String>
	  * @throws 
	  */
	public Map<String,String> getMapByNickName(String nickName){
		return dictionarysMap.get(nickName);
	}
	/**
	 * @Description: ˢ�¼�ֵ�� 
	 * @param nickName
	 * @param name
	 * @param value
	 * @return
	 */
	public void refreshDic(String nickName,String name,String value){
		dictionarysMap.get(nickName).put(value, name);
	}
	/**
	 * @Description: ��ȡֵ 
	 * @param nickName
	 * @param key
	 * @return
	 */
	public String getNameByNickAndKey(String nickName,String key){
		Map<String,String> tmpMap = dictionarysMap.get(nickName);
		if(tmpMap!=null)
			return tmpMap.get(key);
		else
			return null;
	}	
	/** 
	  * @Title: getNameByValue 
	  * @Description: ����value���name 
	  * @param @param value
	  * @param @return
	  * @return String
	  * @throws 
	  */
	public String getNameByValue(String value){
		Map<String,String> valueNameMap = new HashMap<String,String>();
		Set<String> keys = dictionarysMap.keySet();
		for(Iterator<String> iter=keys.iterator();iter.hasNext();){
			valueNameMap.putAll(dictionarysMap.get(iter.next()));
		}
		return valueNameMap.get(value);
	}
	
}
