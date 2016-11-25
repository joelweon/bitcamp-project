package bitcamp.java89.ems.server.dao;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import bitcamp.java89.ems.server.vo.Contact;
// 제너럴했을때는 그 자체로 쓰기위함이 아니라
// 공통부분을 상속해주기위함이기 때문에
// 추상 클래스로 만든다. (추상메서드 없어도된다.)
public abstract class AbstractDao<T> {

  private String filename;
  protected ArrayList<T> list;

  public AbstractDao(String filename) {
//    super(); 자동생성!!
    this.filename = filename;
  }

  @SuppressWarnings("unchecked")
  protected void load() throws Exception {
  
    try (
        ObjectInputStream in = new ObjectInputStream(
                                  new FileInputStream(this.filename)); ) {
  
      list = (ArrayList<T>) in.readObject();
  
    } catch (EOFException e) {
      // 파일을 모두 읽었다.
    } catch (Exception e) {
      list = new ArrayList<T>();
      throw new Exception("데이터 로딩 중 오류 발생!");
    }
  }

  public void save() throws Exception {
    try (
    ObjectOutputStream out = new ObjectOutputStream(
                                new FileOutputStream(this.filename)); ) {
  
    out.writeObject(list);
  
    } catch (Exception e) {
      throw e;
    }
  }  

}