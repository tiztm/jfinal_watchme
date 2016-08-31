package com.demo.common.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseMybooks<M extends BaseMybooks<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}

	public java.lang.Integer getId() {
		return get("id");
	}

	public void setBookName(java.lang.String bookName) {
		set("book_name", bookName);
	}

	public java.lang.String getBookName() {
		return get("book_name");
	}

	public void setBookUri(java.lang.String bookUri) {
		set("book_uri", bookUri);
	}

	public java.lang.String getBookUri() {
		return get("book_uri");
	}

	public void setBookSize(java.lang.String bookSize) {
		set("book_size", bookSize);
	}

	public java.lang.String getBookSize() {
		return get("book_size");
	}

	public void setBookFormat(java.lang.String bookFormat) {
		set("book_format", bookFormat);
	}

	public java.lang.String getBookFormat() {
		return get("book_format");
	}

	public void setIsRead(java.lang.Integer isRead) {
		set("is_read", isRead);
	}

	public java.lang.Integer getIsRead() {
		return get("is_read");
	}

	public void setDownCount(java.lang.Integer downCount) {
		set("down_count", downCount);
	}

	public java.lang.Integer getDownCount() {
		return get("down_count");
	}

	public void setTags(java.lang.String tags) {
		set("tags", tags);
	}

	public java.lang.String getTags() {
		return get("tags");
	}

}