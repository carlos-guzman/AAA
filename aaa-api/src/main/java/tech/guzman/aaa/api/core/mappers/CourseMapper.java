package tech.guzman.aaa.api.core.mappers;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import tech.guzman.aaa.api.core.models.Course;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseMapper implements ResultSetMapper<Course> {

    @Override
    public Course map(int i, ResultSet rs, StatementContext ctx) throws SQLException {
        return new Course(rs.getInt("id"), rs.getInt("number"), rs.getString("course_name"), rs.getString("meet_data"), rs.getString("location"), rs.getBoolean("is_open"));
    }
}
