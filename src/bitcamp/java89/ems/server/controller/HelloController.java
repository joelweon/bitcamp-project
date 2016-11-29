package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import bitcamp.java89.ems.server.AbstractCommand;
import bitcamp.java89.ems.server.dao.TeacherDao;
import bitcamp.java89.ems.server.vo.Teacher;

public class HelloController extends AbstractCommand {
  TeacherDao teacherDao;
  
  public void setTeacherDao(TeacherDao teacherDao) {
    this.teacherDao = teacherDao;
  }
  
  @Override
  public String getCommandString() {
    return "hello";
  }

  @Override
  protected void doResponse(HashMap<String, String> paramMap, PrintStream out) throws Exception {
    ArrayList<Teacher> list = teacherDao.getList();
    for (Teacher t : list) {
      out.println(t.getName());
    }
  }
}
