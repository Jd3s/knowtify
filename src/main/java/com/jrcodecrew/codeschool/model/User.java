package com.jrcodecrew.codeschool.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "user_name", nullable = false)
  private String userName;

  @JsonIgnore
  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Column(name = "phone", unique = true)
  private String phone;

  @Enumerated(EnumType.STRING)
  @Column(name = "role", nullable = false)
  private Role role;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Temporal(TemporalType.DATE)
  @Column(name = "date_created")
  private Date date_created;

  @ManyToOne
  @JoinColumn(name = "parent_id")
  private User parent;

  public enum Role {
    PARENT,
    CHILD
  }

  public User(
      String userName,
      String password,
      String email,
      String phone,
      Role role,
      String firstName,
      String lastName,
      Date date_created,
      User parent) {
    this.userName = userName;
    this.password = password;
    this.email = email;
    this.phone = phone;
    this.role = role;
    this.firstName = firstName;
    this.lastName = lastName;
    this.date_created = date_created;
    this.parent = parent;
  }

  public User() {}

  @Override
  public String toString() {
    return "User{"
        + "id="
        + id
        + ", userName='"
        + userName
        + '\''
        + ", email='"
        + email
        + '\''
        + ", phone='"
        + phone
        + '\''
        + ", role="
        + role
        + ", first_name='"
        + firstName
        + '\''
        + ", last_name='"
        + lastName
        + '\''
        + ", date_created="
        + date_created
        + ", parent="
        + parent
        + '}';
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public User setUserName(String userName) {
    this.userName = userName;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public User setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public User setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getPhone() {
    return phone;
  }

  public User setPhone(String phone) {
    this.phone = phone;
    return this;
  }

  public Role getRole() {
    return role;
  }

  public User setRole(Role role) {
    this.role = role;
    return this;
  }

  public String getFirstName() {
    return firstName;
  }

  public User setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public User setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public Date getDate_created() {
    return date_created;
  }

  public User setDate_created(Date date_created) {
    this.date_created = date_created;
    return this;
  }

  public User getParent() {
    return parent;
  }

  public User setParent(User parent) {
    this.parent = parent;
    return this;
  }
}
