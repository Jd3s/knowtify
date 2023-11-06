package com.jrcodecrew.codeschool.dto;
import com.jrcodecrew.codeschool.model.AgeGroup;

public class CourseDto{
    private String course_id;
    private String course_name;
    private String description;
    private AgeGroup age_group;

    public CourseDto(String course_id, String course_name, String description, String age_group) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.description = description;
        this.age_group = Enum.valueOf(AgeGroup.class,age_group);
    }

    public CourseDto() {
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AgeGroup getAge_group() {
        return age_group;
    }

    public void setAge_group(AgeGroup age_group) {
        this.age_group = age_group;
    }

    @Override
    public String toString() {
        return "CourseDto{" +
                "course_id='" + course_id + '\'' +
                ", course_name='" + course_name + '\'' +
                ", description='" + description + '\'' +
                ", ageGroup=" + age_group +
                '}';
    }
}

