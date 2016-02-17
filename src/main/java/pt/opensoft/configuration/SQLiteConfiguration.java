package pt.opensoft.configuration;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.jdbc.DataSourceConnectionSource;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.spring.TableCreator;

import pt.opensoft.model.dao.TopicDao;

@Configuration
@ComponentScan({ "pt.opensoft.model.dao", "pt.opensoft" })
public class SQLiteConfiguration {

	private final static String DATABASE_DRIVER = "org.sqlite.JDBC";
	private final static String DATABASE_URL = "jdbc:sqlite::memory:";

	@Autowired
	TopicDao topicDao;

	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(DATABASE_DRIVER);
		dataSource.setUrl(DATABASE_URL);
		return dataSource;
	}

	@Bean(initMethod = "initialize")
	public DataSourceConnectionSource connectionSource() {
		DataSourceConnectionSource connectionSource = new DataSourceConnectionSource();
		connectionSource.setDatabaseUrl(DATABASE_URL);
		connectionSource.setDataSource(dataSource());
		return connectionSource;
	}

	@Bean(initMethod = "initialize")
	public TableCreator tableCreator() {
		System.setProperty(TableCreator.AUTO_CREATE_TABLES, Boolean.toString(true));
		System.setProperty(TableCreator.AUTO_DROP_TABLES, Boolean.toString(true));

		TableCreator tableCreator = new TableCreator();
		tableCreator.setConnectionSource(connectionSource());
		tableCreator.setConfiguredDaos(getConfiguredDaos());
		return tableCreator;
	}

	@Bean
	public TransactionManager transactionHandler() {
		return new TransactionManager(connectionSource());
	}

	private List<Dao<?, ?>> getConfiguredDaos() {
		List<Dao<?, ?>> configuredDaos = new ArrayList<Dao<?, ?>>();
		configuredDaos.add(topicDao);
		return configuredDaos;
	}
}
