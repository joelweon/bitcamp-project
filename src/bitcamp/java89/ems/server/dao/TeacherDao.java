package bitcamp.java89.ems.server.dao;

import java.util.ArrayList;

import bitcamp.java89.ems.server.vo.Teacher;

public interface TeacherDao {
  ArrayList<Teacher> getList() throws Exception;
  ArrayList<Teacher> getListById(String id) throws Exception;
  void insert(Teacher teacher) throws Exception;
  void update(Teacher teacher) throws Exception;
  void delete(String id) throws Exception;
  boolean existId(String id) throws Exception;
}
