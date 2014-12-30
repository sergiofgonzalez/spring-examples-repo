package org.joolzminer.examples.dtos;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

public class Course {
	@JacksonXmlProperty(isAttribute = true, namespace="")
	private String code;
	
	@JacksonXmlProperty(isAttribute = true)
	private int credits;
	
	@JacksonXmlText(value = true)
	private String name;
	
	public Course() {		
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Course [code=" + code + ", credits=" + credits + ", name="
				+ name + "]";
	}
	
	
}
