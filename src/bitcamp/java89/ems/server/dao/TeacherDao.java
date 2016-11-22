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
  private String filename = "teacher-v1.7.data";
  private ArrayList<Teacher> list;
  private boolean changed;
  
  public static TeacherDao getInstance() {
    if (obj == null) {
      obj = new TeacherDao();
    }
    return obj;
  }
  
  public TeacherDao() {
    this.load();
  }


  public boolean isChanged() {
    return changed;
  }

  @SuppressWarnings("unchecked")
  private void load() {
    FileInputStream in0 = null;
    ObjectInputStream in = null;
    
    try {
      in0 = new FileInputStream(this.filename);
      in = new ObjectInputStream(in0);

      list = (ArrayList<Teacher>)in.readObject();
      
    } catch (EOFException e) {
      // 파일을 모두 읽었다.
    } catch (Exception e) {
      System.out.println("학생 데이터 로딩 중 오류 발생!");
      list = new ArrayList<>();
    } finally {
      try {
        in.close();
        in0.close();
      } catch (Exception e) {
        // close하다가 예외 발생하면 무시한다.
      }
    }
  }

  public void save() throws Exception {
    FileOutputStream out0 = new FileOutputStream(this.filename);
    ObjectOutputStream out = new ObjectOutputStream(out0);

    out.writeObject(list);
    
    changed = false;

    out.close();
    out0.close();
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
    changed = true;
  }

  synchronized public void update(Teacher teacher) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getId().equals(teacher.getId())) {
        list.set(i, teacher);
        changed = true;
        return;
      }
    }
  }


  synchronized public void delete(String id) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getId().equals(id)) {
        list.remove(i);
        changed = true;
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
