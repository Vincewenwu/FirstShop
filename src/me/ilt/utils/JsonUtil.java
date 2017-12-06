package me.ilt.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {
	/**
	 * ��ResultSet����ת����JsonArray����
	 * @param rs
	 * @return
	 * @throws SQLException 
	 * @throws Exception
	 */
	public static JSONArray formatRsToJsonArray(ResultSet rs)throws Exception{
		ResultSetMetaData md=rs.getMetaData();
		int num=md.getColumnCount();
		JSONArray array=new JSONArray();
		while(rs.next()){
			JSONObject mapOfColValues=new JSONObject();
			for(int i=1;i<=num;i++){
				if(md.getColumnName(i).equals("birthday")||md.getColumnName(i).equals("orderTime") || md.getColumnName(i).equals("theAppointment")){
					mapOfColValues.put(md.getColumnName(i), ObjectFormatUtil.dateToStr(rs.getTimestamp(i))); //��ȡ������ֵ�Ž�JSON���������
				}else{
					mapOfColValues.put(md.getColumnName(i), rs.getObject(i)); //��ȡ������ֵ�Ž�JSON���������
				}
			}
			array.add(mapOfColValues);//�ŵ�JSON������
		}
		return array;
	}
}
