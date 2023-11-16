package com.jrcodecrew.codeschool.dto;
import com.jrcodecrew.codeschool.model.AgeGroup;

public class CourseDto{
    private String courseId;
    private String courseName;
    private String description;
    private AgeGroup ageGroup;

    public CourseDto(String courseId, String courseName, String description, String ageGroup) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.description = description;
        this.ageGroup = Enum.valueOf(AgeGroup.class, ageGroup);
    }

    public CourseDto() {
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AgeGroup getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(AgeGroup ageGroup) {
        this.ageGroup = ageGroup;
    }

    @Override
    public String toString() {
        return "CourseDto{" +
                "course_id='" + courseId + '\'' +
                ", course_name='" + courseName + '\'' +
                ", description='" + description + '\'' +
                ", ageGroup=" + ageGroup +
                '}';
    }
}

