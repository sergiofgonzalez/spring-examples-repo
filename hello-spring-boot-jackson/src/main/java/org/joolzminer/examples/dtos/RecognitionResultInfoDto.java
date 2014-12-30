package org.joolzminer.examples.dtos;

import java.util.List;

import org.springframework.core.style.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "products")
public class RecognitionResultInfoDto {
	
	@JacksonXmlElementWrapper(localName = "product", useWrapping = false)
	@JacksonXmlProperty(localName = "product")
	private List<ProductDto> productDtoList;
	
	public RecognitionResultInfoDto() {		
	}	
	
	public RecognitionResultInfoDto(List<ProductDto> productDtoList) {
		this.productDtoList = productDtoList;
	}
	
	
	public List<ProductDto> getProductDtoList() {
		return productDtoList;
	}
	
	@Override
	public String toString() {
		return new ToStringCreator(this)
			.append("productDtoList", productDtoList)
			.toString();
	}
}
