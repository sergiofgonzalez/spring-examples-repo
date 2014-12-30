package org.joolzminer.examples.dtos;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "Courses")
public class Schedule {
	@JacksonXmlProperty(isAttribute = true)
	private int semester;
	
	@JacksonXmlElementWrapper(localName = "Courses", useWrapping = false)
	@JacksonXmlProperty(localName = "Course")
	private List<Course> courses;
	
	public Schedule() {		
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "Schedule [semester=" + semester + "]";
	}
}
