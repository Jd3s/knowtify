package com.jrcodecrew.codeschool.dto;
import com.jrcodecrew.codeschool.model.AgeGroup;
public class ChildDto extends UserDto {
    private AgeGroup ageGroup;
    private String parentId;

    public ChildDto(String userName, String password, String email, String phone, String firstName, String lastName, String role, String parentId, String ageGroup) {
        super(userName, password, email, phone, firstName, lastName, role);
        this.parentId = parentId;
        this.ageGroup = Enum.valueOf(AgeGroup.class,ageGroup);
    }

    public AgeGroup getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(AgeGroup ageGroup) {
        this.ageGroup = ageGroup;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "ChildDto{" +
                "ageGroup=" + ageGroup +
                ", parentId='" + parentId + '\'' +
                '}';
    }

}

