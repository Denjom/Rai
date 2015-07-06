package com.denjom;

import java.util.ArrayList;
import java.util.List;

public class Navigation {

	private String id;
	private String name;
	private String url;
	private List<Navigation> children = new ArrayList<Navigation>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<Navigation> getChildren() {
		return children;
	}
	public void setChildren(List<Navigation> children) {
		this.children = children;
	}
	@Override
	public String toString() {
		return "Navigation [id=" + id + ", name=" + name + ", url=" + url
				+ ", children=" + children + "]";
	}
	
}
