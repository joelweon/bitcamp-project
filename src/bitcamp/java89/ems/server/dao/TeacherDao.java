package bitcamp.java89.ems.server.dao;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import bitcamp.java89.ems.server.vo.Teacher;

public class TeacherDao {
  static TeacherDao obj;
  private String filename = "teacher-v1.8.data";
  private ArrayList<Teacher> list;

  
  public static TeacherDao getInstance() {
    if (obj == null) {
      obj = new TeacherDao();
    }
    return obj;
  }
  
  public TeacherDao() {
    this.load();
  }

  @SuppressWarnings("unchecked")
  private void load() {
    try (
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(this.filename)); ) {

      list = (ArrayList<Teacher>)in.readObject();
      
    } catch (EOFException e) {
      // 파일을 모두 읽었다.
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("강사 데이터 로딩 중 오류 발생!");
      list = new ArrayList<>();
    }
  }

  public void save() throws Exception {
    try (
    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(this.filename));  ) {
  
    out.writeObject(list);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public ArrayList<Teacher> getList() {
    return this.list;
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
  
}
