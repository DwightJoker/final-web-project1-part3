package edu.training.web.bean;

import java.io.Serializable;
import java.util.Objects;

public class News implements Serializable {
	private static final long serialVersionUID = 1L;

	private String title;
	private String text;
	private String author;
	private String picPath;
	private int id;

	public News() {
	}

	public News(String title, String text, String picPath) {
		super();
		this.title = title;
		this.text = text;
		this.picPath = picPath;
	}

	public News(String title, String text, String picPath, int id) {
		super();
		this.title = title;
		this.text = text;
		this.picPath = picPath;
		this.id = id;
	}

	public News(String title, String text, String author, String picPath, int id) {
		super();
		this.title = title;
		this.text = text;
		this.author = author;
		this.picPath = picPath;
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, id, picPath, text, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		News other = (News) obj;
		return Objects.equals(author, other.author) && id == other.id && Objects.equals(picPath, other.picPath)
				&& Objects.equals(text, other.text) && Objects.equals(title, other.title);
	}

}