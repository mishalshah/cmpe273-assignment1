package edu.sjsu.cmpe.library.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Author {

	public static int id=0;
	public Author()
	{
	
		id = id++;
	}
	
	private String name;
//	private List<String> book = new ArrayList<String>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
