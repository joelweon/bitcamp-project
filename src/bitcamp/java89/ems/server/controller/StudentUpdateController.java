//package bitcamp.java89.ems.server.controller;
//
//import java.io.PrintStream;
//import java.util.HashMap;
//
//import bitcamp.java89.ems.server.dao.StudentDao;
//import bitcamp.java89.ems.server.vo.Student;
//
//public class StudentUpdateController {
//  private StudentDao studentDao;
//  public StudentUpdateController() {
//    studentDao = StudentDao.getInstance();
//  }
//  
//  public void service(HashMap<String,String> paramMap, PrintStream out) {
//    if (!studentDao.existUserId(paramMap.get("userId"))) {
//      out.println("해당 아이디가 없습니다."); 
//      return;
//    }
//    
//    Student student = new Student();
//    student.setUserId(paramMap.get("userId"));
//    student.setPassword(paramMap.get("password"));
//    student.setName(paramMap.get("name"));
//    student.setTel(paramMap.get("tel"));
//    student.setEmail(paramMap.get("email"));
//    student.setWorking(paramMap.get("working").equals("y") ? true : false);
//    student.setBirthYear(Integer.parseInt(paramMap.get("birthYear")));
//    student.setSchool(paramMap.get("school"));
//
//    studentDao.update(student);
//    out.println("변경 완료했습니다.");
//  }
//}
