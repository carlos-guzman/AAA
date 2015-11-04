package tech.guzman.aaa.api.core.dao;

import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.stringtemplate.UseStringTemplate3StatementLocator;
import tech.guzman.aaa.api.core.mappers.CourseMapper;
import tech.guzman.aaa.api.core.models.Course;

import java.util.List;

@RegisterMapper(CourseMapper.class)
@UseStringTemplate3StatementLocator
public interface CourseDAO {
    String myTable = "courses";

    @SqlQuery("INSERT INTO " + myTable +
              " (number, name, description) " +
              " VALUES " +
              " (:c.number, :c.courseName, :c.description)" +
              " RETURNING * ")
    @GetGeneratedKeys
    Integer create(@BindBean("c") Course course);

    @SqlQuery("SELECT * FROM " + myTable + " ORDER BY id ASC")
    List<Course> list();
}
