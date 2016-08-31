package com.demo;

import com.demo.common.model.Mybooks;
import com.jfinal.core.Controller;

import java.io.File;

/**
 */
public class BookController extends Controller {

	public void index() {

		final String bName = getSessionAttr("bName");
		final String bFormat = getSessionAttr("bFormat");



		setAttr("zkPage", Mybooks.dao.paginatePage(getParaToInt(0, 1),100,bName,bFormat));
		setAttr("bName", bName);
		setAttr("bFormat", bFormat);
		render("books.html");
	}


	public void reIndex() throws Exception {

		BookScanner kanHouseWatcher = new BookScanner();
		kanHouseWatcher.scanBook();
		renderText("Reindex OK!");

	}


	public void bookinfo() throws Exception {
		Mybooks model = getModel(Mybooks.class);
		System.out.println();

		setSessionAttr("bName",model.getBookName());
		setSessionAttr("bFormat",model.getBookFormat());

		redirect("/books");

	}

	public void jumpurl() {
		String url = Mybooks.dao.findById(getParaToInt()).getBookUri();

		renderFile(new File(BookScanner.BOOK_URI+url));
	}



}


