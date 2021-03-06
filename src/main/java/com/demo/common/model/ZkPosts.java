package com.demo.common.model;

import com.demo.common.model.base.BaseZkPosts;
import com.jfinal.plugin.activerecord.Page;
import com.utils.HttpclientUtil;
import com.utils.tiezi;
import com.utils.zk8Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class ZkPosts extends BaseZkPosts<ZkPosts> {
	public static final ZkPosts dao = new ZkPosts();

	/**
	 * 所有 sql 与业务逻辑写在 Model 或 Service 中，不要写在 Controller 中，养成好习惯，有利于大型项目的开发与维护
	 */
	public Page<ZkPosts> paginate(int pageNumber, int pageSize,boolean remen) {

		String add="";
				if(remen)
				add = " t where t.readNum>1000 ";



		return paginate(pageNumber, pageSize, "select *", "from zk_posts "+add+"order by id desc");
	}


	public void fetchNewInfo() throws Exception {

		/**
		 * 对赚客吧快速增长的帖子进行通报
		 *
		 */

		long t = System.currentTimeMillis();

		String string = HttpclientUtil.get("http://www.zuanke8.com/forum-15-1.html");

		long t2 = System.currentTimeMillis();

		//去掉所有换行符
		string=string.replaceAll("\n", "");
		string=string.replaceAll("\r", "");
		//System.out.println(string);
		Pattern rePat = Pattern.compile("normalthread.*?</tbody>");
		Matcher matcher = rePat.matcher(string);
		List<String> reList = new ArrayList<String>();
		List<tiezi> tieziList = new ArrayList<tiezi>();
		int i =0;
		Date nowDate = new Date();
		while (matcher.find()) {
			String s = matcher.group();

			String url = getStringFromTwo(s,"http://www.zuanke8.com/thread-",".html");
			String name = getStringFromTwo(s,"class=\"s xst\" target=\"_blank\">","</a>",true);
			String timeS = getStringFromTwo(s,"lastpost\">","</a>",true);
			String huifuNum = getStringFromTwo(s,"class=\"xi2\">","</a>",true);
			String readNum = getStringFromTwo(s,"a><em>","</em>",true);

			SimpleDateFormat dateformat1=new SimpleDateFormat("yyyy-MM-dd HH:mm");

			Date parse = null;
			try {
				parse = dateformat1.parse(timeS);
			} catch (Exception e) {
				System.out.println(s+":ERROR");
			}
			reList.add(s);
			//System.out.println(name+","+timeS);

			ZkPosts zp = dao.findFirst("select * from zk_posts t where t.url = '"+url+"'");
			if(zp==null)
			{
				zp = new ZkPosts();
				zp.set("url",url).set("name", name).set("timeS", timeS).set("huifuNum", huifuNum)
						.set("readNum", readNum).set("createTime", new Date());
				zp.save();
			}
			else
			{
				//热门信息内容读取
				if (zp.getReadNum() > 1000) {
					if (zp.getContent() == null) {
						try {
							String s1 = zk8Text.createTextFromUrl(zp.getUrl());
							if(s1.length()>1200) s1 = s1.substring(0,1190);
							zp.setContent(s1);
						} catch (Exception e) {
							e.printStackTrace();
							zp.setContent("FETCH ERROR");
						}
					}

				}
				if( zp.getSendStatus()==null||zp.getSendStatus()==0)
				{



					try {
						int returnVal = (int) ((nowDate.getTime() - zp.getCreateTime().getTime()) / (1000 * 60 * 60 ));
						//相差小时数
						if(returnVal<2) {
							//2小时候内点击1000
                            //判定是否需要发送邮件提醒
                            if (zp.getReadNum() > 1000) {

//
//                                Mail m = new Mail();
//                                m.setTitle(zp.getName());
//                                m.setSendstatus(0);
//                                m.setContent(zp.getContent()+"<br>"+zp.getUrl());
//                                m.save();
                                zp.setSendStatus(1);
                            }
                        }
						else
						{
							zp.setSendStatus(2);
						}
					} catch (Exception e) {
						e.printStackTrace();
						zp.setSendStatus(2);
					}
				}
				zp.set("huifuNum", huifuNum)
						.set("readNum", readNum).update();
			}

			i++;
		}


		System.out.println("处理时间:"+(System.currentTimeMillis()-t2)+"-抓取时间："+(t2-t));


	}


	public static String getStringFromTwo(String oraString,String one,String two,boolean isCut)
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

	public static String getStringFromTwo(String oraString,String one,String two)
	{

		return getStringFromTwo(oraString,one,two,false);
	}

}
