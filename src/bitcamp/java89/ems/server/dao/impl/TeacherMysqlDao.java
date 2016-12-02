package bitcamp.java89.ems.server.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bitcamp.java89.ems.server.annotation.Component;
import bitcamp.java89.ems.server.dao.TeacherDao;
import bitcamp.java89.ems.server.vo.Teacher;

@Component
public class TeacherMysqlDao implements TeacherDao {
  Connection con;
  
//  connection 객체 외부에서 주입받기
  public void setConnection(Connection con) {
    this.con = con;
  }
  
  public ArrayList<Teacher> getList() throws Exception {
    ArrayList<Teacher> list = new ArrayList<>();
    try (
      PreparedStatement stmt = con.prepareStatement(
          "select id, name, email, tel, major, mlanguage, gitaddr,"
          + " work, lecture, age, salary from ex_teachers");
      ResultSet rs = stmt.executeQuery(); ){
      
      while (rs.next()) { // 서버에서 레코드 한 개를 가져왔다면,
        Teacher teacher = new Teacher(); 
        teacher.setId(rs.getString("id"));
        teacher.setName(rs.getString("name"));
        teacher.setEmail(rs.getString("email"));
        teacher.setTel(rs.getString("tel"));
        teacher.setMajor(rs.getString("major"));
        teacher.setMajorLanguage(rs.getString("mlanguage"));
        teacher.setGitAddress(rs.getString("gitaddr"));
        teacher.setWorkExperience(rs.getInt("work"));
        teacher.setLectureExperience(rs.getInt("lecture"));
        teacher.setAge(rs.getInt("age"));
        teacher.setSalary(rs.getInt("salary"));
        list.add(teacher);
      }
    }
    return list;
  }

  public void insert(Teacher teacher) throws Exception {
    try (
      PreparedStatement stmt = con.prepareStatement(
          "insert into ex_teachers(id, name, email, tel, major, mlanguage, gitaddr,"
          + " work, lecture, age, salary) values(?,?,?,?,?,?,?,?,?,?,?)"); ) {
      
      stmt.setString(1, teacher.getId());
      stmt.setString(2, teacher.getName());
      stmt.setString(3, teacher.getEmail());
      stmt.setString(4, teacher.getTel());
      stmt.setString(5, teacher.getMajor());
      stmt.setString(6, teacher.getMajorLanguage());
      stmt.setString(7, teacher.getGitAddress());
      stmt.setInt(8, teacher.getWorkExperience());
      stmt.setInt(9, teacher.getLectureExperience());
      stmt.setInt(10, teacher.getAge());
      stmt.setInt(11, teacher.getSalary());
      
      stmt.executeUpdate();
    }
  }
  
  public void update(Teacher teacher) throws Exception {
    try (
      PreparedStatement stmt = con.prepareStatement(
          "update ex_teachers set name=?, email=?, tel=?, major=?, mlanguage=?,"
          + " gitaddr=?, work=?, lecture=?, age=?, salary=? where id=?"); ) {
      
      stmt.setString(1, teacher.getName());
      stmt.setString(2, teacher.getEmail());
      stmt.setString(3, teacher.getTel());
      stmt.setString(4, teacher.getMajor());
      stmt.setString(5, teacher.getMajorLanguage());
      stmt.setString(6, teacher.getGitAddress());
      stmt.setInt(7, teacher.getWorkExperience());
      stmt.setInt(8, teacher.getLectureExperience());
      stmt.setInt(9, teacher.getAge());
      stmt.setInt(10, teacher.getSalary());
      stmt.setString(11, teacher.getId());
      
      stmt.executeUpdate();
    }
  }
  
  public void delete(String id) throws Exception {
    try (
      PreparedStatement stmt = con.prepareStatement(
          "delete from ex_teachers where id=?"); ) {
      
      stmt.setString(1, id);
      
      stmt.executeUpdate();
    } 
  }
  
  public boolean existId(String id) throws Exception {
    try (
      PreparedStatement stmt = con.prepareStatement(
          "select * from ex_teachers where id=?"); ) {
      
      stmt.setString(1, id);
      ResultSet rs = stmt.executeQuery();
      
      if (rs.next()) { // 서버에서 레코드 한 개를 가져왔다면,
        rs.close();
        return true;
      } else {
        rs.close();
        return false;
      }
    }
  }
  
  
  public ArrayList<Teacher> getListById(String id) throws Exception {
    ArrayList<Teacher> list = new ArrayList<>();
    try (
      PreparedStatement stmt = con.prepareStatement(
          "select id, name, email, tel, major, mlanguage, gitaddr, work,"
          + " lecture, age, salary from ex_teachers where id=?"); ) {
      
      stmt.setString(1, id);
      ResultSet rs = stmt.executeQuery();
      
      while (rs.next()) { // 서버에서 레코드 한 개를 가져왔다면,
        Teacher teacher = new Teacher(); 
        teacher.setName(rs.getString("name"));
        teacher.setEmail(rs.getString("email"));
        teacher.setTel(rs.getString("tel"));
        teacher.setMajor(rs.getString("major"));
        teacher.setMajorLanguage(rs.getString("mlanguage"));
        teacher.setGitAddress(rs.getString("gitaddr"));
        teacher.setWorkExperience(rs.getInt("work"));
        teacher.setLectureExperience(rs.getInt("lecture"));
        teacher.setAge(rs.getInt("age"));
        teacher.setSalary(rs.getInt("salary"));
        teacher.setId(rs.getString("id"));
        list.add(teacher);
      }
      
      rs.close();
    }
    return list;
  }
  
  
  
}
