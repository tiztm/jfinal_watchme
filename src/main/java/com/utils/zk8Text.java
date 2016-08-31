package com.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class zk8Text {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {

		createTextFromUrl("http://www.zuanke8.com/thread-2958696-1-1.html");
		
	}

	public static String createTextFromUrl(String url) throws Exception {
		String string = HttpclientUtil.get(url);

		//去掉所有换行符
		string=string.replaceAll("\n", "");
		string=string.replaceAll("\r", "");


//
//		<td class="t_f" id="postmessage_39542869">
//				来个<br><a href="misc.php?mod=mobile" target="_blank" style="font-size:12px;color:#708090;">来自: Android客户端</a></td>
		String text = getStringFromTwo(string,"<td class=\"t_f\" id=\"postmessage_","</td>");

		return Html2Text(text.replaceAll("<br.*?>","\r\n")).replaceAll("来自: Android客户端","");
	}

	public static String Html2Text(String inputString) {
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;

		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
			// }
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
			// }
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签

			textStr = htmlStr;

		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}

		return textStr;// 返回文本字符串
	}


	private static String getStringFromTwo(String oraString,String one,String two,boolean isCut)
	{
		String backString = null;
		Pattern rePat = Pattern.compile(one+".*?"+two);
		Matcher matcher = rePat.matcher(oraString);
		while (matcher.find()) {
			backString = matcher.group();
			if(isCut)
			{
				backString = backString.substring(one.length(),backString.length()-two.length());
			}
			break;
		}

		return backString;
	}

	private static String getStringFromTwo(String oraString,String one,String two)
	{

		return getStringFromTwo(oraString,one,two,false);
	}




}
