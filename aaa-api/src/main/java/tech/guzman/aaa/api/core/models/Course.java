package tech.guzman.aaa.api.core.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.jackson.JsonSnakeCase;

@JsonSnakeCase
public class Course {
    private int id;
    private int number;
    private String courseName;
    private String meetData;
    private String location;
    private boolean isOpen;

    @JsonCreator
    public Course(@JsonProperty("id") int id,
                      @JsonProperty("number") int number,
                  @JsonProperty("course_name") String courseName,
                  @JsonProperty("meet_data") String meetData,
                  @JsonProperty("location") String location,
                  @JsonProperty("is_open") boolean isOpen) {
        this.id = id;
        this.number = number;
        this.courseName = courseName;
        this.meetData = meetData;
        this.location = location;
        this.isOpen = isOpen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getMeetData() {
        return meetData;
    }

    public void setMeetData(String meetData) {
        this.meetData = meetData;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (id != course.id) return false;
        if (number != course.number) return false;
        if (isOpen != course.isOpen) return false;
        if (courseName != null ? !courseName.equals(course.courseName) : course.courseName != null) return false;
        if (meetData != null ? !meetData.equals(course.meetData) : course.meetData != null) return false;
        return !(location != null ? !location.equals(course.location) : course.location != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + number;
        result = 31 * result + (courseName != null ? courseName.hashCode() : 0);
        result = 31 * result + (meetData != null ? meetData.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (isOpen ? 1 : 0);
        return result;
    }
}