package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.StudentDao;

public class StudentDeleteController implements Command {
  private StudentDao studentDao;
  
  public StudentDeleteController() {
    studentDao = StudentDao.getInstance();
  }
  
  public void service(HashMap<String,String> paramMap, PrintStream out) {
    
    if (!studentDao.existUserId(paramMap.get("userId"))) {
      out.println("해당 아이디가 없습니다.");
      return;
    }
    studentDao.delete(paramMap.get("userId")]);
    out.println("해당 데이터 삭제 완료하였습니다.");
  }
}
