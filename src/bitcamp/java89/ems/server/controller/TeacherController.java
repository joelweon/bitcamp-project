package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import bitcamp.java89.ems.server.annotation.Component;
import bitcamp.java89.ems.server.annotation.RequestMapping;
import bitcamp.java89.ems.server.dao.TeacherDao;
import bitcamp.java89.ems.server.vo.Teacher;

@Component
public class TeacherController {

   TeacherDao teacherDao;
  
  public void setTeacherDao(TeacherDao teacherDao) {
    this.teacherDao = teacherDao;
  }
  
  @RequestMapping(value="teacher/add")
  public void add(HashMap<String,String> paramMap, PrintStream out)
      throws Exception {
    if (teacherDao.existId(paramMap.get("id"))) {
      out.println("같은 아이디가 존재합니다. 등록을 취소합니다.");
      return;
    }
    
    Teacher teacher = new Teacher();
    teacher.setId(paramMap.get("id"));
    teacher.setName(paramMap.get("name"));
    teacher.setEmail(paramMap.get("email"));
    teacher.setTel(paramMap.get("tel"));
    teacher.setMajor(paramMap.get("major"));
    teacher.setMajorLanguage(paramMap.get("majorLanguage"));
    teacher.setBook(paramMap.get("book"));
    teacher.setProjectName(paramMap.get("projectName"));
    teacher.setGitAddress(paramMap.get("gitAddress"));
    teacher.setWorkExperience(Integer.parseInt(paramMap.get("workExperience")));
    teacher.setLectureExperience(Integer.parseInt(paramMap.get("lectureExperience")));
    teacher.setAge(Integer.parseInt(paramMap.get("age")));
    teacher.setSalary(Integer.parseInt(paramMap.get("salary")));
    
    teacherDao.insert(teacher);
    out.println("등록하였습니다.");
  }
  
  @RequestMapping(value="teacher/delete")
  public void delete(HashMap<String,String> paramMap, PrintStream out)
      throws Exception {
    if (!teacherDao.existId(paramMap.get("id"))) {
      out.println("존재하지 않는 아이디 입니다.");
      return;
    }
  
    teacherDao.delete(paramMap.get("id"));
    out.println("삭제하였습니다.");  
  }
  
  @RequestMapping(value="teacher/list")
  public void list(HashMap<String,String> paramMap, PrintStream out)
      throws Exception {
    ArrayList<Teacher> list = teacherDao.getList();
    for (Teacher teacher : list) {
      out.printf("%s,%s,%s,%s,%s,%s,%s,%s,%s,%d,%d,%d,%d\n",
        teacher.getId(),
        teacher.getName(),
        teacher.getEmail(),
        teacher.getTel(),
        teacher.getMajor(),
        teacher.getMajorLanguage(),
        teacher.getBook(),
        teacher.getProjectName(),
        teacher.getGitAddress(),
        teacher.getWorkExperience(),
        teacher.getLectureExperience(),
        teacher.getAge(),
        teacher.getSalary());
      out.println("-----------------------------------");
    }
  }
  
  @RequestMapping(value="teacher/update")
  public void update(HashMap<String,String> paramMap, PrintStream out)
      throws Exception {
    
    if (!teacherDao.existId(paramMap.get("id"))) {
      out.println("해당 아이디가 없습니다."); 
      return;
    }

    Teacher teacher = new Teacher();
    teacher.setId(paramMap.get("id"));
    teacher.setName(paramMap.get("name"));
    teacher.setEmail(paramMap.get("email"));
    teacher.setTel(paramMap.get("tel"));
    teacher.setMajor(paramMap.get("major"));
    teacher.setMajorLanguage(paramMap.get("majorLanguage"));
    teacher.setBook(paramMap.get("book"));
    teacher.setProjectName(paramMap.get("projectName"));
    teacher.setGitAddress(paramMap.get("gitAddress"));
    teacher.setWorkExperience(Integer.parseInt(paramMap.get("workExperience")));
    teacher.setLectureExperience(Integer.parseInt(paramMap.get("lectureExperience")));
    teacher.setAge(Integer.parseInt(paramMap.get("age")));
    teacher.setSalary(Integer.parseInt(paramMap.get("salary")));
    
    teacherDao.update(teacher);
    out.println("변경 완료했습니다.");
  }
  
  @RequestMapping(value="teacher/view")
  public void view(HashMap<String,String> paramMap, PrintStream out)
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
    out.println("존재하지 않는 아이디입니다.");
  }
}
