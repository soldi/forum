package pt.opensoft.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.j256.ormlite.stmt.QueryBuilder;

import pt.opensoft.model.dao.TopicDao;
import pt.opensoft.model.entity.Topic;

@Component
public class TopicServiceImpl implements TopicService {

	@Autowired
	TopicDao topicDao;

	public List<Topic> findAll() {
		try {
			QueryBuilder<Topic, Long> queryBuilder = topicDao.queryBuilder();
			List<Topic> topics = queryBuilder.where().isNull(Topic.PARENT_ID_FIELD).query();
			for (Topic topic : topics) {
				topic.setReplies(findReplies(topic.getId()));
			}
			return topics;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Topic findOne(Long id) {
		try {
			Topic topic = topicDao.queryForId(id);
			topic.setReplies(findReplies(id));
			return topic;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private List<Topic> findReplies(Long id) throws SQLException {
		return topicDao.queryForEq(Topic.PARENT_ID_FIELD, id);
	}

	public void save(Topic topic) {
		try {
			topic.applyDefaultInputs();
			topicDao.createOrUpdate(topic);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
