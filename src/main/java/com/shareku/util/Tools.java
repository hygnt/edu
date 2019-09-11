package com.shareku.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author cicada
 * @version v0.1
 * @date 2014-09-25 11:37
 */
public class Tools {

	/**
	 * 将流转换成字符串
	 * @param is
	 * @param l
	 * @return
	 * @throws Exception
	 */
	public static byte[] convertStreamToString(InputStream is,int l) throws Exception {    
		   /*   
		    * To convert the InputStream to String we use the BufferedReader.readLine()   
		    * method. We iterate until the BufferedReader return null which means   
		    * there's no more data to read. Each line will appended to a StringBuilder   
		    * and returned as String.   
		    */   
		//	BufferedReader reader = new BufferedReader(new InputStreamReader(is));    
		   

		    byte[] result = new byte[l];
		    int ireadlen = 0;
		    int line = 0;    
		    try {    
		    	
		        while ((line = is.read(result, ireadlen, l-ireadlen)) != -1) {    
		           // sb+=new String(b,0,line);  
		        	//chararrToBytearr( b,line,result,ireadlen);
		        	ireadlen += line;
		        }    
		    } catch (IOException e) {    
		        e.printStackTrace();    
		    } finally {    
		        try {    
		             is.close();    
		        } catch (IOException e) {    
		        	e.printStackTrace();    
		        }    
		    }    
		  //  b = sb.toString().getBytes();
		    
		    return result;    
		} 
	
	/**
	 * 检测字符串是否不为空(null,"","null")
	 * @param s
	 * @return 不为空则返回true，否则返回false
	 */
	public static boolean notEmpty(String s){
		return s!=null && !"".equals(s) && !"null".equals(s);
	}
	
	/**
	 * 检测字符串是否为空(null,"","null")
	 * @param s
	 * @return 为空则返回true，不否则返回false
	 */
	public static boolean isEmpty(String s){
		return s==null || "".equals(s) || "null".equals(s);
	}
	
	/**
	 * 字符串转换为字符串数组
	 * @param str 字符串
	 * @param splitRegex 分隔符
	 * @return
	 */
	public static String[] str2StrArray(String str,String splitRegex){
		if(isEmpty(str)){
			return null;
		}
		return str.split(splitRegex);
	}
	
	/**
	 * 用默认的分隔符(,)将字符串转换为字符串数组
	 * @param str	字符串
	 * @return
	 */
	public static String[] str2StrArray(String str){
		return str2StrArray(str,",\\s*");
	}
	
	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String date2Str(Date date){
		return date2Str(date,"yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
	 * @param date
	 * @return
	 */
	public static Date str2Date(String date){
		if(notEmpty(date)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new Date();
		}else{
			return null;
		}
	}
	
	/**
	 * 按照参数format的格式，日期转字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2Str(Date date,String format){
		if(date!=null){
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		}else{
			return "";
		}
	}
	
	/**
	 * 将给出的文本字符串进行MD5加密处理
	 * @param plainText
	 * @param flag
	 * @return 将加密后的字符串进行返回
	 */
	public static String getMD5(String plainText, boolean flag) {
		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++)
			{
				i = b[offset];
				if (i < 0) {
					i += 256;
				}
				if (i < 16) {
					buf.append("0");
				}
				buf.append(Integer.toHexString(i));
			}
			result = buf.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//如果参数flag为true，则返回16位的MD5加密数据
		return flag == true ? result.substring(8, 24) : result;
	}

	/**
	 * 默认返回32位的密文
	 * @param plainText
	 * @return
	 */
	public static String getMD5(String plainText) {
		if(plainText == null || "".equals(plainText)){
			return plainText;
		}
		return getMD5(plainText, false);
	}
	
	/**
	 * convert the stream to bytes.
	 * @param is
	 * @return
	 */
	public static byte[] convertStreamToBytes(InputStream is){
		byte[] bResult = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			byte[] buf = new byte[1024];
			int num = -1;
			while ((num = is.read(buf))!= -1) {
				baos.write(buf, 0, num);
			}
			bResult = baos.toByteArray();
			baos.flush();
			baos.close();
			is.close();
			baos = null;
			is = null;
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				if (baos != null) {
					baos.close();
				}
				if (is != null) {
					is.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return bResult;
	}

	/**
	 * 根据指定的范围返回一个随机种子
	 * @param min
	 * @param max
	 * @return
	 */
	public static int random(int min, int max) {
		return new Random().nextInt(max) % (max - min + 1) + min;
	}
	
	/**
	 * 将数字转换成6位固定长度
	 * @param num
	 * @return
	 */
	public static String formatFixedNumber(int num) {
		return new DecimalFormat("000000").format(num);
	}
	
	/**
	 * 将字符串转换成6位固定长度
	 * @param num
	 * @return
	 */
	public static String formatFixedNumber(String s) {
		return formatFixedNumber(Integer.parseInt(s));
	}
}
