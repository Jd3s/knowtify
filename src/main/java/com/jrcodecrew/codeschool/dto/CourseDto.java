package com.jrcodecrew.codeschool.dto;
import com.jrcodecrew.codeschool.model.AgeGroup;

public class CourseDto{
    private String courseId;
    private String courseName;
    private String description;
    private AgeGroup ageGroup;

    private String previewVideo;

    private Double price;

    public CourseDto(String courseId, String courseName, String description, String ageGroup, Double price, String previewVideo) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.description = description;
        this.ageGroup = Enum.valueOf(AgeGroup.class, ageGroup);
        this.price = price;
        this.previewVideo = previewVideo;
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

    public String getPreviewVideo() {
        return previewVideo;
    }

    public void setPreviewVideo(String previewVideo) {
        this.previewVideo = previewVideo;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CourseDto{" +
                "course_id='" + courseId + '\'' +
                ", course_name='" + courseName + '\'' +
                ", description='" + description + '\'' +
                ", ageGroup=" + ageGroup +
                ", price=" + price +
                ", previewVideo=" + previewVideo +
                '}';
    }
}

