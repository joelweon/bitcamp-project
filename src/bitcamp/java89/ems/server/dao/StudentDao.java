package bitcamp.java89.ems.server.dao;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import bitcamp.java89.ems.server.vo.Student;

public class StudentDao {
  static StudentDao obj;
  private String filename = "student-v1.7.data";
  private ArrayList<Student> list;
  private boolean changed;
  
  public static StudentDao getInstance() {
    if (obj == null) {
      obj = new StudentDao();
    }
    return obj;
  }
  
  public StudentDao() {
    this.load();
  }


  public boolean isChanged() {
    return changed;
  }

  @SuppressWarnings("unchecked")
  private void load() {
    
    try (
      ObjectInputStream in = new ObjectInputStream(
                                   new FileInputStream(this.filename));) {
      list = (ArrayList<Student>)in.readObject();
      
    } catch (EOFException e) {
      // 파일을 모두 읽었다.
    } catch (Exception e) {
      System.out.println("학생 데이터 로딩 중 오류 발생!");
      list = new ArrayList<>();
    }
  }

  synchronized public void save() throws Exception {
    try (
    ObjectOutputStream out = new ObjectOutputStream(
                                 new FileOutputStream(this.filename)); ) {

    out.writeObject(list);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public ArrayList<Student> getList() {
    return this.list;
  }
  
  public ArrayList<Student> getListByUserId(String userId) {
    ArrayList<Student> results = new ArrayList<>();
    for (Student student : list) {
      if (student.getUserId().equals(userId)) {
        results.add(student);
      }
    }
    return results;
  }
  
  synchronized public void insert(Student student) {
    list.add(student);
    changed = true;
  }

  synchronized public void update(Student student) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getUserId().equals(student.getUserId())) {
        list.set(i, student);
        changed = true;
        return;
      }
    }
  }


  synchronized public void delete(String userId) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getUserId().equals(userId)) {
        list.remove(i);
        changed = true;
        return;
      }
    }
  }
  
  public boolean existUserId(String userId) {
    for (Student student : list) {
      if (student.getUserId().equals(userId)) {
        return true;
      }
    }
    return false;
  }
  
}
