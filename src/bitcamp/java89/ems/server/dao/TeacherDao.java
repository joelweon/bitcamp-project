package bitcamp.java89.ems.server.dao;

import java.util.ArrayList;

import bitcamp.java89.ems.server.annotation.Component;
import bitcamp.java89.ems.server.vo.Teacher;

@Component //ApplicationContext가 객체를 관리하는 클래스임을 표시하기 위해 태그를 단다.
public class TeacherDao extends AbstractDao<Teacher> {
  
  
  public TeacherDao() {
    this.setFilename("teacher-v1.9.data");
    try {this.load();} catch (Exception e) {}
  }
  public ArrayList<Teacher> getList() {
    return this.list;
  }

  synchronized public void insert(Teacher teacher) {
    list.add(teacher);
    try {this.save();} catch (Exception e) {}
  }
  
  synchronized public void update(Teacher teacher) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getId().equals(teacher.getId())) {
        list.set(i, teacher);
        try {this.save();} catch (Exception e) {}
        return;
      }
    }
  }
  
  synchronized public void delete(String id) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getId().equals(id)) {
        list.remove(i);
        try {this.save();} catch (Exception e) {}
        return;
      }
    }
  }
  
  public boolean existId(String id) {
    for (Teacher teacher : list) {
      if (teacher.getId().equals(id)) {
        return true;
      }
    }
    return false;
  }
  
  
  public ArrayList<Teacher> getListById(String id) {
    ArrayList<Teacher> results = new ArrayList<>();
    for (Teacher teacher : list) {
      if (teacher.getId().equals(id)) {
        results.add(teacher);
      }
    }
    return results;
  }
  
  
  
}
