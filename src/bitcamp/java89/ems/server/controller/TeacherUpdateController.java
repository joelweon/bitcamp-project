package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.AbstractCommand;
import bitcamp.java89.ems.server.dao.TeacherDao;
import bitcamp.java89.ems.server.vo.Teacher;

public class TeacherUpdateController extends AbstractCommand {
  @Override
  public String getCommandString() {
    return "teacher/update";
  }
  @Override
  protected void doResponse(HashMap<String,String> paramMap, PrintStream out)
      throws Exception {
    TeacherDao teacherDao = TeacherDao.getInstance();
    
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
}
