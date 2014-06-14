package org.joolzminer.examples.sip.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Message extends AbstractEntity {
	@ManyToOne
	@JoinColumn(name = "forum_id", nullable = false)
	private Forum forum;
	
	@ManyToOne
	@JoinColumn(name = "author_id", nullable = false)
	private Account author;
	
	@NotEmpty
	private String subject;
	
	@NotEmpty
	private String text;
	
	private boolean visible = true;
	
	@Column(name = "date_created")
	private Date dateCreated;

	public Message() {		
	}
		
	public Forum getForum() {
		return forum;
	}

	public String getSubject() {
		return subject;
	}
	
	public Account getAuthor() {
		return author;
	}

	public String getText() {
		return text;
	}

	public Date getDateCreated() {
		return (dateCreated == null) ? null : (Date) dateCreated.clone();
	}

	public boolean isVisible() {
		return visible;
	}	
}
