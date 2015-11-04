package tech.guzman.aaa.api.core.resources;

import tech.guzman.aaa.api.core.dao.CourseDAO;
import tech.guzman.aaa.api.core.models.Course;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/courses")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CourseResource {
    private final CourseDAO courseDAO;

    public CourseResource(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    @POST
    public Response createCourse(@Context HttpServletRequest request,
                                     @Valid Course course) {
        int id = courseDAO.create(course);

        if (id == 0) {
            return Response.status(Response.Status.NOT_MODIFIED).build();
        }

        course.setId(id);

        return Response.ok(course).build();
    }

    @GET
    public Response listCourses(@Context HttpServletRequest request) {
        List<Course> courseList;

        courseList = courseDAO.list();

        return Response.ok(courseList).build();
    }
}
