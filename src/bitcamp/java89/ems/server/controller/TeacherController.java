package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;

import bitcamp.java89.ems.server.annotation.Component;
import bitcamp.java89.ems.server.annotation.RequestMapping;
import bitcamp.java89.ems.server.annotation.RequestParam;
import bitcamp.java89.ems.server.dao.TeacherDao;
import bitcamp.java89.ems.server.vo.Teacher;

@Component
public class TeacherController {

   TeacherDao teacherDao;
  
  public void setTeacherDao(TeacherDao teacherDao) {
    this.teacherDao = teacherDao;
  }
  
  @RequestMapping(value="teacher/add")
  public void add(
      @RequestParam("id") String id,
      @RequestParam("name") String name,
      @RequestParam("email") String email,
      @RequestParam("tel") String tel,
      @RequestParam("major") String major,
      @RequestParam("majorLanguage") String majorLanguage,
      @RequestParam("gitAddress") String gitAddress,
      @RequestParam("workExperience") int workExperience,
      @RequestParam("lectureExperience") int lectureExperience,
      @RequestParam("age") int age,
      @RequestParam("salary") int salary,
      PrintStream out)
      throws Exception {
    if (teacherDao.existId(id)) {
      out.println("같은 아이디가 존재합니다. 등록을 취소합니다.");
      return;
    }
    
    Teacher teacher = new Teacher();
    teacher.setId(id);
    teacher.setName(name);
    teacher.setEmail(email);
    teacher.setTel(tel);
    teacher.setMajor(major);
    teacher.setMajorLanguage(majorLanguage);
    teacher.setGitAddress(gitAddress);
    teacher.setWorkExperience(workExperience);
    teacher.setLectureExperience(lectureExperience);
    teacher.setAge(age);
    teacher.setSalary(salary);
    
    teacherDao.insert(teacher);
    out.println("등록하였습니다.");
  }
  
  @RequestMapping(value="teacher/delete")
  public void delete(@RequestParam("id") String id, PrintStream out)
      throws Exception {
    if (!teacherDao.existId(id)) {
      out.println("존재하지 않는 아이디 입니다.");
      return;
    }
  
    teacherDao.delete(id);
    out.println("삭제하였습니다.");  
  }
  
  @RequestMapping(value="teacher/list")
  public void list(PrintStream out)
      throws Exception {
    ArrayList<Teacher> list = teacherDao.getList();
    for (Teacher teacher : list) {
      out.printf("%s,%s,%s,%s,%s,%s,%s,%d,%d,%d,%d\n",
        teacher.getId(),
        teacher.getName(),
        teacher.getEmail(),
        teacher.getTel(),
        teacher.getMajor(),
        teacher.getMajorLanguage(),
        teacher.getGitAddress(),
        teacher.getWorkExperience(),
        teacher.getLectureExperience(),
        teacher.getAge(),
        teacher.getSalary());
      out.println("-----------------------------------");
    }
  }
  
  @RequestMapping(value="teacher/update")
  public void update(
      @RequestParam("id") String id,
      @RequestParam("name") String name,
      @RequestParam("email") String email,
      @RequestParam("tel") String tel,
      @RequestParam("major") String major,
      @RequestParam("majorLanguage") String majorLanguage,
      @RequestParam("gitAddress") String gitAddress,
      @RequestParam("workExperience") int workExperience,
      @RequestParam("lectureExperience") int lectureExperience,
      @RequestParam("age") int age,
      @RequestParam("salary") int salary,
      PrintStream out)
      throws Exception {
    
    if (!teacherDao.existId(id)) {
      out.println("해당 아이디가 없습니다."); 
      return;
    }

    Teacher teacher = new Teacher();
    teacher.setId(id);
    teacher.setName(name);
    teacher.setEmail(email);
    teacher.setTel(tel);
    teacher.setMajor(major);
    teacher.setMajorLanguage(majorLanguage);
    teacher.setGitAddress(gitAddress);
    teacher.setWorkExperience(workExperience);
    teacher.setLectureExperience(lectureExperience);
    teacher.setAge(age);
    teacher.setSalary(salary);
    
    teacherDao.update(teacher);
    out.println("변경 완료했습니다.");
  }
  
  @RequestMapping(value="teacher/view")
  public void view(@RequestParam("id") String id, PrintStream out)
      throws Exception {
    
    ArrayList<Teacher> list = teacherDao.getListById(id);
    for (Teacher teacher : list) {
      out.println("-----------------------------------");
      out.printf("아이디: %s\n", teacher.getId());
      out.printf("이름: %s\n", teacher.getName());
      out.printf("이메일: %s\n", teacher.getEmail());
      out.printf("전화번호: %s\n", teacher.getTel());
      out.printf("주요언어: %s\n", teacher.getMajorLanguage());
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
