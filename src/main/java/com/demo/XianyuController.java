package com.demo;

import com.demo.common.model.Xianyu;
import com.jfinal.core.Controller;

/**
 */
public class XianyuController extends Controller {

	public void index() {


		final String bName = getSessionAttr("bName");
		final String bFormat = getSessionAttr("bType");


		setAttr("zkPage", Xianyu.dao.paginatePage(getParaToInt(0, 1),100,bName,bFormat));
		render("xianyu.html");
	}



	public void bookinfo() throws Exception {
		Xianyu model = getModel(Xianyu.class);

		setSessionAttr("bName",model.getName());
		setSessionAttr("bType",model.getXianyutype());

		redirect("/xianyu");

	}


	public void jumpurl() {
		String url = Xianyu.dao.findById(getParaToInt()).getUrl();
		redirect("http://"+url);
	}





}


