package bitcamp.java89.ems.server.dao.impl;

import java.util.ArrayList;

import bitcamp.java89.ems.server.dao.TeacherDao;
import bitcamp.java89.ems.server.vo.Teacher;


public class TeacherFileDao extends AbstractFileDao<Teacher> implements TeacherDao {
  
  
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
