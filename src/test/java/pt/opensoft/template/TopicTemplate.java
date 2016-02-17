package pt.opensoft.template;

import java.text.SimpleDateFormat;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import pt.opensoft.model.entity.Topic;

public class TopicTemplate implements TemplateLoader {

	public void load() {
		Fixture.of(Topic.class).addTemplate("topicViaApp", new Rule() {
			{
				add("id", random(Long.class, range(1L, 200L)));
				add("title", firstName());
				add("username", firstName());
				add("contet", firstName());
				add("registration", new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(instant("now")));
				add("source", Topic.APP_SOURCE);
			}
		});

		Fixture.of(Topic.class).addTemplate("topicViaRest", new Rule() {
			{
				add("id", random(Long.class, range(1L, 200L)));
				add("title", firstName());
				add("username", firstName());
				add("contet", firstName());
				add("registration", new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(instant("now")));
				add("source", Topic.REST_SOURCE);
			}
		});

	}
}
