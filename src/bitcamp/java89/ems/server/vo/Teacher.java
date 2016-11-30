package bitcamp.java89.ems.server.vo;

import java.io.Serializable;

public class Teacher implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected String id;
  protected String name;
  protected String email;
  protected String tel;
  protected String major;
  protected String majorLanguage;
  protected String gitAddress;
  protected int workExperience;
  protected int lectureExperience;
  protected int age;
  protected int salary;


  public Teacher() {}

  public Teacher(String name, String email, String tel, int age) {
    this.name = name;
    this.email = email;
    this.tel = tel;
    this.age = age;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public String getMajor() {
    return major;
  }

  public void setMajor(String major) {
    this.major = major;
  }

  public String getMajorLanguage() {
    return majorLanguage;
  }

  public void setMajorLanguage(String majorLanguage) {
    this.majorLanguage = majorLanguage;
  }


  public String getGitAddress() {
    return gitAddress;
  }

  public void setGitAddress(String gitAddress) {
    this.gitAddress = gitAddress;
  }

  public int getWorkExperience() {
    return workExperience;
  }

  public void setWorkExperience(int workExperience) {
    this.workExperience = workExperience;
  }

  public int getLectureExperience() {
    return lectureExperience;
  }

  public void setLectureExperience(int lectureExperience) {
    this.lectureExperience = lectureExperience;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public int getSalary() {
    return salary;
  }

  public void setSalary(int salary) {
    this.salary = salary;
  }
  


}
