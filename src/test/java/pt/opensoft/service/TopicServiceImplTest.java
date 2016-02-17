package pt.opensoft.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import pt.opensoft.model.entity.Topic;

public class TopicServiceImplTest {

	@InjectMocks
	private TopicServiceImpl service;

	@BeforeClass
	public static void setUpBeforeClass() {
		FixtureFactoryLoader.loadTemplates("br.com.six2six.template");
	}

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Ignore
	@Test
	public void retrieveAllTopic() throws SQLException {
		List<Topic> topics = service.findAll();
		assertNotNull(topics);
		assertEquals(0, topics.size());

		Topic topicFromApp = Fixture.from(Topic.class).gimme("topicViaApp");
		service.save(topicFromApp);
		topics = service.findAll();
		Assert.assertTrue(topicFromApp.equals(topics.get(0)));

		Topic topicFromRest = Fixture.from(Topic.class).gimme("topicViaRest");
		service.save(topicFromRest);
		topics = service.findAll();
		Assert.assertTrue(topicFromRest.equals(topics.get(1)));
	}
}
