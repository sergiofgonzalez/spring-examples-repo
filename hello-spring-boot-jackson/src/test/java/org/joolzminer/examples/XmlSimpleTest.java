package org.joolzminer.examples;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.joolzminer.examples.Application;
import org.joolzminer.examples.dtos.Course;
import org.joolzminer.examples.dtos.ProductDto;
import org.joolzminer.examples.dtos.RecognitionResultInfoDto;
import org.joolzminer.examples.dtos.Schedule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class XmlSimpleTest {

	@Autowired
	private XmlMapper xmlMapper;
	
	@Test
	public void testSerializeCourseXml() throws Exception {
			
		Course course = new Course();
		course.setCode("A231");
		course.setCredits(3);
		course.setName("Intermediate A");
		
		List<org.joolzminer.examples.dtos.Course> courses = new ArrayList<>();
		courses.add(course);
		
		Schedule schedule = new Schedule();
		schedule.setCourses(courses);
		
		System.out.println(xmlMapper.writeValueAsString(schedule));
	}
	
	@Test
	public void testSerializeProductXml() throws JsonProcessingException {
		List<ProductDto> productDtoList = new ArrayList<>();
		productDtoList.add(new ProductDto(0l, 2921L, "desod_aero_nivea_for_men_dry_impact_92g"));
		RecognitionResultInfoDto recognitionResultInfoDto = new RecognitionResultInfoDto(productDtoList);
		
		System.out.println(xmlMapper.writeValueAsString(recognitionResultInfoDto));
		
	}
	
	@Test
	public void testDeserializeOnlyProductsXml() throws JsonParseException, JsonMappingException, IOException {
		ClassPathResource fileResource = new ClassPathResource("only-products.xml");
		RecognitionResultInfoDto recognitionResultInfoDto = xmlMapper.readValue(fileResource.getFile(), RecognitionResultInfoDto.class);
		System.out.println(recognitionResultInfoDto);
	}
	
	@Test
	public void testDeserializeOutputXml() throws XMLStreamException, FileNotFoundException, IOException {
				
		ClassPathResource fileResource = new ClassPathResource("several-products.xml");
		XMLInputFactory f = XMLInputFactory.newFactory();
		XMLStreamReader sr = f.createXMLStreamReader(new BufferedInputStream(new FileInputStream(fileResource.getFile())));
		RecognitionResultInfoDto recognitionResultInfoDto = null;
		boolean done = false;
		do {
			sr.next();
			if (sr.isStartElement() && sr.getLocalName().equals("products")) {				
				recognitionResultInfoDto = xmlMapper.readValue(sr, RecognitionResultInfoDto.class);
				done = true;
			}			
		} while (!done && sr.hasNext());
		System.out.println(recognitionResultInfoDto);
	}
}
