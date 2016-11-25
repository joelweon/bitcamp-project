package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.AbstractCommand;
import bitcamp.java89.ems.server.dao.TeacherDao;

public class TeacherDeleteController extends AbstractCommand {
  @Override
  public String getCommandString() {
    return "teacher/delete";
  }
  @Override
  protected void doResponse(HashMap<String,String> paramMap, PrintStream out)
      throws Exception {
    TeacherDao teacherDao = TeacherDao.getInstance();
    if (!teacherDao.existId(paramMap.get("id"))) {
      out.println("존재하지 않는 아이디 입니다.");
      return;
    }
  
    teacherDao.delete(paramMap.get("id"));
    out.println("삭제하였습니다.");  
  }
}
