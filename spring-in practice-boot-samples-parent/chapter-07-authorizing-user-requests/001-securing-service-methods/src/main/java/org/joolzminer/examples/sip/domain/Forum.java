package org.joolzminer.examples.sip.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
@NamedQuery(
		name = "findForumsWithStats",
		query = "select forum, count(message), max(message.dateCreated)" +
				"  from Forum as forum" +
				"  left outer join forum.messages as message with message.visible = true" +
				" group by forum")
public class Forum extends AbstractEntity {
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "owner_id", nullable = false)
	private Account owner;
	
	@OneToMany(mappedBy = "forum")
	private List<Message> messages = new ArrayList<>();
	
	@Transient
	private boolean calculateMessageStats = false;
	
	@Transient
	private int numVisibleMessages;
	
	@Transient
	private Date lastVisibleMessageDate;
	
	public Forum() {		
	}

	public String getName() {
		return name;
	}

	
	public Account getOwner() {
		return owner;
	}
	public List<Message> getMessages() {
		return Collections.unmodifiableList(messages);
	}

	public boolean getCalculateMessageStats() {
		return calculateMessageStats;
	}

	public int getNumVisibleMessages() {
		if (calculateMessageStats) {
			int count = 0;
			for (Message message : messages) {
				if (message.isVisible()) {
					count++;
				}
			}
			return count;
		} else {
			return numVisibleMessages;
		}
	}

	public Date getLastVisibleMessageDate() {
		if (calculateMessageStats) {
			Date date = null;
			for (Message message : messages) {
				if (message.isVisible()) {
					Date dateCreated = message.getDateCreated();
					if (date == null || date.compareTo(dateCreated) < 0) {
						date = message.getDateCreated();
					}
				}
			}
			return date;
		} else {
			return lastVisibleMessageDate;
		}		
	}

	public void setCalculateMessageStats(boolean flag) {
		calculateMessageStats = flag;
	}

	public void setNumVisibleMessages(int numVisibleMessages) {
		this.numVisibleMessages = numVisibleMessages;
	}

	public void setLastVisibleMessageDate(Date lastVisibleMessageDate) {
		this.lastVisibleMessageDate = (lastVisibleMessageDate == null)? null : (Date) lastVisibleMessageDate.clone();
	}

	
	
}
