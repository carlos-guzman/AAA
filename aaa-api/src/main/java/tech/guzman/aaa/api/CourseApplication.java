package tech.guzman.aaa.api;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.skife.jdbi.v2.DBI;
import tech.guzman.aaa.api.core.resources.CourseResource;
import tech.guzman.aaa.api.core.dao.CourseDAO;
import tech.guzman.aaa.api.util.exception_mapper.UnableToExecuteStatementExceptionMapper;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.io.IOException;
import java.util.EnumSet;

public class CourseApplication extends Application<CourseConfiguration> {
    
    public static void main(String[] args) throws Exception {
        new CourseApplication().run(args);
    }
    
    @Override
    public void initialize(Bootstrap<CourseConfiguration> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<CourseConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(CourseConfiguration configuration) {
                return configuration.getDatabase();
            }
        });
    }

    @Override
    public void run(CourseConfiguration configuration, Environment environment) throws IOException, ClassNotFoundException {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDatabase(), "database");

        CourseDAO courseDAO = jdbi.onDemand(CourseDAO.class);
        
        // Set up the API resources
        CourseResource courseResource = new CourseResource(courseDAO);

        //Register exceptions
        environment.jersey().register(new UnableToExecuteStatementExceptionMapper());

        // Register the API endpoints
        environment.jersey().register(courseResource);

        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }
}
