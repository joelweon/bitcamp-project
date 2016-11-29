package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import bitcamp.java89.ems.server.AbstractCommand;
import bitcamp.java89.ems.server.dao.TeacherDao;
import bitcamp.java89.ems.server.vo.Teacher;

public class TeacherViewController extends AbstractCommand {
  
  TeacherDao teacherDao;
  
 public void setTeacherDao(TeacherDao teacherDao) {
   this.teacherDao = teacherDao;
 }
 
  @Override
  public String getCommandString() {
    return "teacher/view";
  }
  @Override
  protected void doResponse(HashMap<String,String> paramMap, PrintStream out)
      throws Exception {
    
    ArrayList<Teacher> list = teacherDao.getListById(paramMap.get("id"));
    for (Teacher teacher : list) {
      out.println("-----------------------------------");
      out.printf("아이디: %s\n", teacher.getId());
      out.printf("이름: %s\n", teacher.getName());
      out.printf("이메일: %s\n", teacher.getEmail());
      out.printf("전화번호: %s\n", teacher.getTel());
      out.printf("주요언어: %s\n", teacher.getMajorLanguage());
      out.printf("저서: %s\n", teacher.getBook());
      out.printf("프로젝트명: %s\n", teacher.getProjectName());
      out.printf("Git주소: %s\n", teacher.getGitAddress());
      out.printf("직장경력: %d\n", teacher.getWorkExperience());
      out.printf("강의경력: %d\n", teacher.getLectureExperience());
      out.printf("나이: %d\n", teacher.getAge());
      out.printf("희망급여: %d\n", teacher.getSalary());
      out.println("-----------------------------------");
      return;
    }
  }
}
