package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import bitcamp.java89.ems.server.AbstractCommand;
import bitcamp.java89.ems.server.dao.TeacherDao;
import bitcamp.java89.ems.server.vo.Teacher;

public class TeacherListController extends AbstractCommand {

  @Override
  public String getCommandString() {
    return "teacher/list";
  }
  @Override
  protected void doResponse(HashMap<String,String> paramMap, PrintStream out)
      throws Exception {
    TeacherDao teacherDao = TeacherDao.getInstance();
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
}
