package pt.opensoft.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pt.opensoft.model.entity.Topic;
import pt.opensoft.service.TopicService;

@RestController
@RequestMapping("/api")
public class TopicResource {
	private final Logger log = LoggerFactory.getLogger(TopicResource.class);

	@Autowired
	TopicService topicService;

	@RequestMapping(value = "/topics", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> create(@RequestBody Topic topic) throws URISyntaxException {
		log.debug("REST request to save topic : {}", topic);
		if (topic.getId() != null) {
			return ResponseEntity.badRequest().header("Failure", "A new topic cannot already have an ID").build();
		}
		topicService.save(topic);
		return ResponseEntity.created(new URI("/api/topics/" + topic.getId())).build();
	}

	@RequestMapping(value = "/topics", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> update(@RequestBody Topic topic) throws URISyntaxException {
		log.debug("REST request to update topic : {}", topic);
		if (topic.getId() == null) {
			return create(topic);
		}
		topicService.save(topic);
		return ResponseEntity.ok().build();
	}

	@RequestMapping(value = "/topics", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Topic> getAll() {
		log.debug("REST request to get all topics");
		return topicService.findAll();
	}

	@RequestMapping(value = "/topics/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> get(@PathVariable Long id) {
		log.debug("REST request to get topic : {}", id);
		return Optional.ofNullable(topicService.findOne(id))
				.map(topic -> new ResponseEntity<Object>(topic, HttpStatus.OK))
				.orElse(new ResponseEntity<Object>(HttpStatus.NOT_FOUND));
	}
}
