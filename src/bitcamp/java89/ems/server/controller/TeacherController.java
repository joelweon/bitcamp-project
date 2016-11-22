package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import bitcamp.java89.ems.server.dao.TeacherDao;
import bitcamp.java89.ems.server.vo.Teacher;

public class TeacherController {
  private Scanner in;
  private PrintStream out;
  
  private TeacherDao teacherDao;

  public TeacherController(Scanner in, PrintStream out) {
    this.in = in;
    this.out = out;
    
    teacherDao = TeacherDao.getInstance();
  }


  public void save() throws Exception {
    if (teacherDao.isChanged()) {
      teacherDao.save();
      out.println("강사 정보를 저장하였습니다.");
    }
  }

  public boolean service() {
    while (true) {
      out.println("강사관리> ");
      out.println();
      
      String[] commands = in.nextLine().split("\\?");
      
      try {
        switch (commands[0]) {
          case "add": this.doAdd(commands[1]); break;
          case "list": this.doList(); break;
          case "view": this.doView(commands[1]); break;
          case "delete": this.doDelete(commands[1]); break;
          case "update": this.doUpdate(commands[1]); break;
          case "main": return true;
          case "quit": return false;
          default:
            out.println("지원하지 않는 명령어입니다.");
        }
      } catch (IndexOutOfBoundsException e) {
        out.println("인덱스가 유효하지 않습니다.");
      } catch (Exception e) {
        out.println("실행 중 오류가 발생했습니다.");
      }
    } //while
  } //service 

  private void doAdd(String params) {
    String[] values = params.split("&");
    HashMap<String,String> paramMap = new HashMap<>();
    
    for (String value : values) {
      String[] kv = value.split("=");
      paramMap.put(kv[0], kv[1]);
    }
    
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
  }//doAdd

  private void doList() {
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

  private void doView(String params) {
    String[] kv = params.split("=");
    
    ArrayList<Teacher> list = teacherDao.getListById(kv[1]);
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
    out.println("해당 아이디가 없습니다.");
  }

  private void doDelete(String params) {
    String[] kv = params.split("=");
    
    if (!teacherDao.existId(kv[1])) {
      out.println("해당 아이디가 없습니다.");
      return;
    }
    teacherDao.delete(kv[1]);
    out.println("해당 데이터를 삭제하였습니다.");
  }

  private void doUpdate(String params) {
    String[] values = params.split("&");
    HashMap<String,String> paramMap = new HashMap<>();
    
    for (String value : values) {
      String[] kv = value.split("=");
      paramMap.put(kv[0], kv[1]);
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


}  //class
