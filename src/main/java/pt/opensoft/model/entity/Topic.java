package pt.opensoft.model.entity;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import pt.opensoft.model.dao.TopicDaoImpl;

@JsonInclude(JsonInclude.Include.NON_NULL)
@DatabaseTable(daoClass = TopicDaoImpl.class)
public class Topic implements Serializable {

	public static final String APP_SOURCE = "App";
	public static final String REST_SOURCE = "Rest API";
	public static final String PARENT_ID_FIELD = "parentId";

	@DatabaseField(generatedId = true, canBeNull = false)
	private Long id;

	@DatabaseField(columnName = "title", canBeNull = false)
	private String title;

	@DatabaseField(columnName = "userName", canBeNull = false)
	private String userName;

	@DatabaseField(columnName = "content", canBeNull = false)
	private String content;

	@DatabaseField(columnName = "registration", canBeNull = false)
	private String registration;

	@DatabaseField(columnName = "source", canBeNull = false)
	private String source;

	@JsonInclude(Include.NON_NULL)
	@DatabaseField(columnName = PARENT_ID_FIELD)
	private Long parentId;

	@JsonInclude(Include.NON_NULL)
	private List<Topic> replies;

	public Topic() {
	}

	public Topic(Long id, String title, String userName, String content, String registration, String source, Long parentId) {
		this.id = id;
		this.title = title;
		this.userName = userName;
		this.content = content;
		this.registration = registration;
		this.source = source;
		this.parentId = parentId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public List<Topic> getReplies() {
		return replies;
	}

	public void setReplies(List<Topic> replies) {
		this.replies = replies;
	}

	public void applyDefaultInputs() {
		if (registration == null) {
			setRegistration(convertDate(new Date()));
		}
		if (!APP_SOURCE.equalsIgnoreCase(source)) {
			setSource(REST_SOURCE);
		}
	}

	public int getTotalReplies() {
		if (isNotReplied()) {
			return 0;
		}
		return replies.size();
	}

	@JsonInclude(Include.NON_NULL)
	public String getLastResponseDate() {
		if (isNotReplied()) {
			return null;
		}

		Topic firstReply = replies.get(0);
		if (replies.size() > 1) {
			int lastIindex = replies.size() - 1;
			Topic lastReply = replies.get(lastIindex);

			if (lastReply.getId() > firstReply.getId()) {
				return lastReply.getRegistration();
			}
		}
		return firstReply.getRegistration();
	}

	private String convertDate(Date dateToConvert) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return dateFormat.format(dateToConvert);
	}

	private boolean isNotReplied() {
		return replies == null || replies.isEmpty();
	}
}
