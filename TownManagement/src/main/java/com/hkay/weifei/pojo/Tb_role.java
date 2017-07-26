package com.hkay.weifei.pojo;

import java.util.List;
import java.util.Map;


public class Tb_role {

	private String id;
	private String name;
	private String value;
	
	private List<Map<String, String>> nodes;
	private String search;

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public List<Map<String, String>> getNodes() {
		return nodes;
	}

	public void setNodes(List<Map<String, String>> nodes) {
		this.nodes = nodes;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

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

}
