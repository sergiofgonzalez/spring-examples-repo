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
	
	public void setForum(Forum forum) {
		this.forum = forum;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getSubject() {
		return subject;
	}

	public void setAuthor(Account author) {
		this.author = author;
	}
	
	public Account getAuthor() {
		return author;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = (dateCreated == null) ? null : (Date) dateCreated;
	}
	
	public Date getDateCreated() {
		return (dateCreated == null) ? null : (Date) dateCreated.clone();
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public boolean isVisible() {
		return visible;
	}	
}
