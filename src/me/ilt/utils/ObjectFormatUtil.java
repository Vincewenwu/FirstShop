package me.ilt.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ObjectFormatUtil {
	public static boolean isNotNull(String param)
	{
		boolean ret = true;
		if(null==param)
			ret = false;
		else if(param.trim().length()==0)
		{
			ret = false;
		}
		return ret;
	}
	public static boolean isNotNull(Integer param)
	{
		boolean ret = true;
		if(null==param)
			ret = false;
		else if(param==0)
		{
			ret = false;
		}
		return ret;
	}
	public static boolean isNotNull(Long param)
	{
		boolean ret = true;
		if(null==param)
			ret = false;
		else if(param==0)
		{
			ret = false;
		}
		return ret;
	}
	public static boolean isCharacterAndDigit(String testStr){
		Pattern pattern = Pattern.compile("[A-Za-z0-9]+");
		Matcher matcher = pattern.matcher(testStr);
		return matcher.matches();
	}
	/**
	 * @description ȥ��ĩβ��0
	 * @param strDate
	 * @return
	 */
	public static String formatDateStr(String strDate)
	{
		String ret = strDate;
		if(strDate!=null)
		{
			ret = strDate.replaceAll("00:00:00[\\S]*", "");
			//ret = ret.replaceAll("00:00:00", "");
		}
		return ret;
	}
	/**
	 * @description ��double��Ǯ��ʽ��Ϊ����λС�����ַ���Ǯ
	 * @param moneyValue
	 * @return
	 */
	public static String formatMoney(Double moneyValue)
	{
		String ret = null;
		if(moneyValue!=null)
		{
			long money = Math.round(Math.abs(moneyValue)*100);
			long fenValue =money%100;
			long yuanValue = (money - fenValue)/100;			
			ret = String.valueOf(yuanValue)+"."+(fenValue>9?String.valueOf(fenValue):"0"+String.valueOf(fenValue));
			if(moneyValue<0.0)
			{
				ret = "-"+ret;
			}
		}
		else
		{
			ret = "0.00";
		}
		return ret;
	}
	/**
	 * @description ��double�ı��ʸ�ʽ��Ϊ����λС����double
	 * @param rateValue
	 * @return
	 */
	public static Double formatRate(Double rateValue,int precision)
	{
		Double ret = null;
		if(rateValue!=null)
		{
			long rate = Math.round(Math.abs(rateValue)*Math.pow(10, precision));

			ret = rate/Math.pow(10, precision);
			
		}
		else
		{
			ret = 0.0;
		}
		return ret;
	}	
	/**
	 * @description ��long�ķ�Ǯ��ʽ��Ϊ����λС�����ַ���Ǯ
	 * @param moneyValue
	 * @return
	 */
	public static String formatFenMoney(long moneyValue)
	{
		String ret = null;
		if(moneyValue!=0)
		{
			long fenValue =moneyValue%100;
			long yuanValue = (moneyValue - fenValue)/100;			
			ret = String.valueOf(yuanValue)+"."+(fenValue>9?String.valueOf(fenValue):"0"+String.valueOf(fenValue));
			if(moneyValue<0.0)
			{
				ret = "-"+ret;
			}
		}
		else
		{
			ret = "0.00";
		}
		return ret;
	}	
	/**
	 * @description ȥ��ĩβ��0
	 * @param strDate
	 * @return
	 */
	public static String formatFixLengthNumber(String srcNumber,int length)
	{
		StringBuffer ret = new StringBuffer();
		if(srcNumber==null)
		{
			srcNumber = "0";
		}
		if(srcNumber.length()<length)
		{
			int diffLength = length-srcNumber.length();
			for(int i=0;i<diffLength;i++)
			{
				ret.append("0");
			}
		}
		ret.append(srcNumber);
		return ret.toString();
	}	
	/**
	 * @description ��ȡyyyy-MM-dd�����ڸ�ʽ
	 * @param strDate
	 * @return
	 */
	public static String getYYYMMDDDate(String dateStr){
		String resultDate = null;
		if(null!=dateStr&&dateStr.length()>=10){
	    resultDate = dateStr.substring(0,10);
		}		
		return resultDate;
	}
	/**
	 * @description ��ȡ����������������ʱʱ�ַ��������ڸ�ʽ
	 * @param srcDate
	 * @return
	 */
	public static String getyyyyMMddHHmmssDate(Date srcDate){
		String retStr = null;
		if(srcDate==null)
		{
			srcDate = new Date();
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		retStr = format.format(srcDate);
		return retStr;
	}	
	/**
	* ����ת�����ַ���
	* @param str
	* @return date
	*/
	public static String dateToStr(Date date) {
	  
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   String str = null;
	   try {
		   str = format.format(date);
		   str = str.replaceAll("00:00:00[\\S]*", "");
	   } catch (Exception e) {
	    e.printStackTrace();
	   }
	   return str;
	}
	/**
	* ����ת�����ַ���
	* @return str
	*/
	public static String getCurrentDayString() {
	  
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	   String str = null;
	   try {
		   str = format.format(new Date());		   
	   } catch (Exception e) {
	    e.printStackTrace();
	   }
	   return str;
	}
	/**
	* �ַ���ת��������
	* @param str
	* @return date
	*/
	public static Date StrToDate(String str) {
	  
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   Date date = null;
	   try {
	    date = format.parse(str);
	   } catch (Exception e) {
	    e.printStackTrace();
	   }
	   return date;
	}
	
	/**
	 * @Title: getFuzzyString 
	 * @description ��ȡ��***��β�ַ����ĸ�ʽ
	 * @param strDate
	 * @return
	 */
	public static String getFuzzyString(String value){
		String returnValue=null;
		if(isNotNull(value)){
	     returnValue = value.substring(0, 2)+"******";
		}
		return returnValue;
		
	}
	
	/**
	 * @Title: getFuzzyString 
	 * @Description: �޸��ַ�����ʽ���м��ĳЩ�ַ����滻��*
	 * @param value
	 * @param startPos
	 * @param endPos
	 * @return
	 * @throws P2pException
	 */
	public static String getFuzzyString(String value,Integer startPos,Integer endPos){
		if(!isNotNull(value)){
		    return null;
			}
		char[] array = value.toCharArray();
		String returnValue="";
		for(int i=0;i<array.length;i++){
			if(i>startPos && i<endPos){
				array[i]='*';
			}
			returnValue+=array[i];
		}
		return returnValue;
	}
	/**
	 * @Title: isSameDay 
	 * @Description: �Ƿ�ͬһ��
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameDay(Date date1,Date date2)
	{
		boolean isSame = false;
		if(date1!=null&&date2!=null)
		{
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(date1);
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(date2);
			if(cal1.get(Calendar.YEAR)==cal2.get(Calendar.YEAR)&&cal1.get(Calendar.MONTH)==cal2.get(Calendar.MONTH)&&cal1.get(Calendar.DAY_OF_MONTH)==cal2.get(Calendar.DAY_OF_MONTH))
			{
				isSame = true;
			}
		}
		return isSame;
	}
	/**
	 * @Title: isCurrentRepaymentCycle 
	 * @Description: �Ƿ��ڵ�ǰ��������
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isCurrentRepaymentCycle(Date date1)
	{
		//ֻҪ���ڱȵ�ǰ+1��С�Ķ��ڵ�ǰ��������
		boolean isSame = false;
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.MONTH, 1);
		Date nextDate = cal.getTime();
		if(date1.before(nextDate)&&!(ObjectFormatUtil.isSameDay(date1,nextDate)))
		{
			isSame = true;
		}
		return isSame;
	}	
	/**
	 * @Title: isSameMonth 
	 * @Description: �Ƿ�ͬһ��
	 * @param date1
	 * @param date2
	 * @return
	 */	
	public static boolean isSameMonth(Date date1,Date date2)
	{
		boolean isSame = false;
		if(date1!=null&&date2!=null)
		{
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(date1);
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(date2);
			if(cal1.get(Calendar.YEAR)==cal2.get(Calendar.YEAR)&&cal1.get(Calendar.MONTH)==cal2.get(Calendar.MONTH))
			{
				isSame = true;
			}
		}
		return isSame;
	}
	/**
	 * @Title: isSameYear 
	 * @Description: �Ƿ�ͬһ��
	 * @param date1
	 * @param date2
	 * @return
	 */	
	public static boolean isSameYear(Date date1,Date date2)
	{
		boolean isSame = false;
		if(date1!=null&&date2!=null)
		{
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(date1);
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(date2);
			if(cal1.get(Calendar.YEAR)==cal2.get(Calendar.YEAR))
			{
				isSame = true;
			}
		}
		return isSame;
	}
	/**
	 * @Title: daysBetween 
	 * @Description: ��������
	 * @param date1
	 * @param date2
	 * @return
	 */	
	public static int daysBetween(Date date1,Date date2)
	{
		int days = 0;
		if(date1!=null&&date2!=null)
		{
	    	try {
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				date1=sdf.parse(sdf.format(date1));
				date2=sdf.parse(sdf.format(date2));
				Calendar cal = Calendar.getInstance();  
				cal.setTime(date1);  
				long time1 = cal.getTimeInMillis();               
				cal.setTime(date2);  
				long time2 = cal.getTimeInMillis();       
				long betweenDays=(time2-time1)/(1000*3600*24);
				days =Math.abs((int)betweenDays);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				days = -1;
			}
		}
		return days;
	}	
	
	public static boolean checkPhoneNum(String phoneNum)
	{
		boolean flag = false;
		try {  
	        //13********* ,15********,18*********   
			if(phoneNum!=null&&phoneNum.length()>0)
			{
		        Pattern p = Pattern.compile("^1\\d{10}$");  
		        Matcher m = p.matcher(phoneNum);  
		        flag = m.matches();  
			}
	    } catch (Exception e) {  
	        flag = false;  
	    }  
		return flag;
	}
	/**
	 * @description  ��listת���ɴ��ָ������ַ���
	 * @param list
	 * @param split
	 * @param addQuote
	 * @return
	 */
	public static String List2String(List<String> list,String split,boolean addQuote){
		String ret = null;
		
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				if(i==0)
				{
					if(addQuote)
					{
						ret = "'"+list.get(i)+"'";
					}
					else
					{
						ret = list.get(i);
					}
					
				}
				else
				{
					if(addQuote)
					{
						ret = ret + split + "'"+list.get(i)+"'";
					}
					else
					{
						ret = ret + split + list.get(i);
					}
				}
			}
		}
		return ret;
		
	}
	
	public static String getDateDiff(Date startDate,Date endDate){
		if(null==startDate && endDate==null){
			return null;
		}
		long l=endDate.getTime()-startDate.getTime();
		  long day=l/(24*60*60*1000);
		  long hour=(l/(60*60*1000)-day*24);
		  long min=((l/(60*1000))-day*24*60-hour*60);
		  long s=(l/1000-day*24*60*60-hour*60*60-min*60);
		  StringBuffer sb = new StringBuffer();
		  if(day!=0){			  
			  sb.append(day+"��");
		  }
		  if(hour!=0){			 
			  sb.append(hour+"Сʱ");
		  }
		  if(min!=0){			 
			  sb.append(min+"��");
		  }
		  if(s!=0){			 
			  sb.append(s+"��");
		  }		
		return sb.toString().trim();
	}
	
	public static boolean isMobileNO(String mobiles){
		boolean flag = false;
		if(isNotNull(mobiles)){
		Pattern p = Pattern.compile("^[1-9]{1}\\d{7,10}$");
		Matcher m = p.matcher(mobiles);
		flag =  m.matches();
		return flag;
		}else{
			return flag;
		}
		}
	
	 /**
     * ���ֽ���дת����˼����д��������Ȼ������ʰ�滻����
     * Ҫ�õ�������ʽ
     */
    public static String digitUppercase(double n){
        String fraction[] = {"��", "��"};
        String digit[] = { "��", "Ҽ", "��", "��", "��", "��", "½", "��", "��", "��" };
        String unit[][] = {{"Ԫ", "��", "��"},{"", "ʰ", "��", "Ǫ"}};
 
        String head = n < 0? "��": "";
        n = Math.abs(n);
         
        String s = "";
        for (int i = 0; i < fraction.length; i++) {
            s += (digit[(int)(Math.floor(n * 10 * Math.pow(10, i)) % 10)] + fraction[i]).replaceAll("(��.)+", "");
        }
        if(s.length()<1){
            s = "��";    
        }
        int integerPart = (int)Math.floor(n);
 
        for (int i = 0; i < unit[0].length && integerPart > 0; i++) {
            String p ="";
            for (int j = 0; j < unit[1].length && n > 0; j++) {
                p = digit[integerPart%10]+unit[1][j] + p;
                integerPart = integerPart/10;
            }
            s = p.replaceAll("(��.)*��$", "").replaceAll("^$", "��") + unit[0][i] + s;
        }
        return head + s.replaceAll("(��.)*��Ԫ", "Ԫ").replaceFirst("(��.)+", "").replaceAll("(��.)+", "��").replaceAll("^��$", "��Ԫ��");
    }
    public static boolean isChineseName(String name) {
    	if(name!=null)
    	{
	        if (name.matches("[\u4e00-\u9fa5]{2,4}")) {           
	            return true;
	        }
    	}
       return false;
    }

}
