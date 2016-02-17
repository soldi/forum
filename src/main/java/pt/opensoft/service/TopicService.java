package pt.opensoft.service;

import java.util.List;

import pt.opensoft.model.entity.Topic;

public interface TopicService {

	List<Topic> findAll();

	Topic findOne(Long id);

	void save(Topic topic);
}
