package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.annotation.Component;
import bitcamp.java89.ems.server.annotation.RequestMapping;
import bitcamp.java89.ems.server.dao.TeacherDao;

@Component(value="teacher/delete")
public class TeacherDeleteController {

  TeacherDao teacherDao;
  
 public void setTeacherDao(TeacherDao teacherDao) {
   this.teacherDao = teacherDao;
 }
 
  @RequestMapping
  public void delete(HashMap<String,String> paramMap, PrintStream out)
      throws Exception {
    if (!teacherDao.existId(paramMap.get("id"))) {
      out.println("존재하지 않는 아이디 입니다.");
      return;
    }
  
    teacherDao.delete(paramMap.get("id"));
    out.println("삭제하였습니다.");  
  }
}
