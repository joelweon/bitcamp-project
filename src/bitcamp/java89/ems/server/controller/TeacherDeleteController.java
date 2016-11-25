package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.TeacherDao;
import bitcamp.java89.ems.server.vo.Teacher;

public class TeacherDeleteController implements Command {
  private TeacherDao teacherDao;
  
  public TeacherDeleteController() {
    teacherDao = TeacherDao.getInstance();
  }
  
  public void service(HashMap<String,String> paramMap, PrintStream out) {

  if (!teacherDao.existId(paramMap.get("id"))) {
    out.println("존재하지 않는 아이디 입니다.");
    return;
  }

  teacherDao.delete(paramMap.get("id"));
  out.println("삭제하였습니다.");  
  }
}
