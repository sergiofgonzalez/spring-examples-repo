package org.joolzminer.examples.dtos;


import org.springframework.core.style.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDto {
	@JacksonXmlProperty(isAttribute = true)
	private Long id;
	
	@JacksonXmlProperty(localName = "template_id", isAttribute = true)
	private Long templateId;
	
	@JacksonXmlProperty(localName = "template_name", isAttribute = true)
	private String templateName;
	
	public ProductDto() {		
	}

	public ProductDto(Long id, Long templateId, String templateName) {
		this.id = id;
		this.templateId = templateId;
		this.templateName = templateName;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	@Override
	public String toString() {
		return new ToStringCreator(this)
					.append("id", id)
					.append("templateId", templateId)
					.append("templateName", templateName)
					.toString();
	}	
}
