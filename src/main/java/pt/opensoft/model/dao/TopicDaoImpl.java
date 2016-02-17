package pt.opensoft.model.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import pt.opensoft.model.entity.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class TopicDaoImpl extends BaseDaoImpl<Topic, Long> implements TopicDao {

    @Autowired
    public TopicDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Topic.class);
    }
}
