package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import bitcamp.java89.ems.server.dao.ContactDao;
import bitcamp.java89.ems.server.vo.Contact;

public class ContactController {
  private Scanner in;
  private PrintStream out;

  private ContactDao contactDao;

  public ContactController(Scanner in, PrintStream out) {
    this.in = in;
    this.out = out;
    
    contactDao = ContactDao.getInstance();
  }

  public void save() throws Exception {
    if (contactDao.isChanged()) {
      contactDao.save();
      out.println("연락처 정보를 저장하였습니다");
    }
  }

  public boolean service() {
    while (true) {
      out.println("연락처관리> ");
      out.println();

      String[] commands = in.nextLine().split("\\?");

      try {
        switch (commands[0]) {
        case "add": this.doAdd(commands[1]); break;
        case "list": this.doList(); break;
        case "view": this.doView(commands[1]); break;
        case "delete": this.doDelete(commands[1]); break;
        case "update": this.doUpdate(commands[1]); break;
        case "main": return true;
        case "quit": return false;
        default:
          out.println("지원하지 않는 명령어입니다.");
        }
      } catch (IndexOutOfBoundsException e) {
        out.println("인덱스가 유효하지 않습니다.");
      } catch (Exception e) {
        out.println("실행 중 오류가 발생했습니다.");
        e.printStackTrace();
      } // try
    } // while
  }

  private void doList() {
    ArrayList<Contact> list = contactDao.getList();
    for (Contact contact : list) {
      out.printf("%s,%s,%s,%s\n",
          contact.getName(),
          contact.getPosition(),
          contact.getTel(),
          contact.getEmail());
    }
  }

  private void doUpdate(String params) {
    String[] values = params.split("&");
    HashMap<String, String> paramMap = new HashMap<>();

    for (String value : values) {
      String[] kv = value.split("=");
      paramMap.put(kv[0], kv[1]);
    }
    
    if (!contactDao.existEmail(paramMap.get("email"))) {
      out.println("해당 이메일이 없습니다.");
      return;
    }
    
    Contact contact = new Contact();
    contact.setEmail(paramMap.get("email"));
    contact.setName(paramMap.get("name"));
    contact.setPosition(paramMap.get("position"));
    contact.setTel(paramMap.get("tel"));
    
    contactDao.update(contact);
    out.println("변경 완료했습니다.");
  } //doUpdate

  // 클라이언트에서 보낸 데이터 형식 add?name=홍길동&position=대리&tel=333-3333&email=dsf@sdf

  private void doAdd(String params) {
    String[] values = params.split("&");
    HashMap<String, String> paraMap = new HashMap<>();

    for (String value : values) {
      String[] kv = value.split("=");
      paraMap.put(kv[0], kv[1]);
    }

    if (contactDao.existEmail(paraMap.get("email"))) {
      out.println("같은 이메일이 존재합니다. 등록을 취소합니다.");
      return;
    }

    Contact contact = new Contact();
    contact.setName(paraMap.get("name"));
    contact.setPosition(paraMap.get("position"));
    contact.setTel(paraMap.get("tel"));
    contact.setEmail(paraMap.get("email"));


    contactDao.insert(contact);
    out.println("등록하였습니다.");
  } //doAdd


  private void doView(String params) {
    String[] kv = params.split("=");

    ArrayList<Contact> list = contactDao.getListByName(kv[1]);
    for (Contact contact : list) {
        out.printf("이름: %s\n", contact.getName());
        out.printf("직위: %s\n", contact.getPosition());
        out.printf("전화: %s\n", contact.getTel());
        out.printf("이메일: %s\n", contact.getEmail());
        out.println("-------------------------------");
        return;
    }
    out.println("존재하지 않는 이름입니다.");
  } //doView

  private void doDelete(String params) { // 마지막 버전
    String[] kv = params.split("=");

    if (!contactDao.existEmail(kv[1])) {
      out.println("존재하지 않는 이메일 입니다.");
      return;
    }

    contactDao.delete(kv[1]);
    out.println("삭제하였습니다.");
  } //doDelete
}

  /*
   * private void doDelete() { //list 삭제 방법 1 System.out.print("이름? "); String
   * name = keyScan.nextLine();
   * 
   * for (int i = list.size() -1; i >= 0; i--) { if
   * (list.get(i).getName().equals(name)) { list.remove(i); } } changed = true;
   * System.out.println("삭제하였습니다."); }
   */

  /*
   * private void doDelete() { //list 삭제 방법 2 System.out.print("이름? "); String
   * name = keyScan.nextLine();
   * 
   * ArrayList<Contact> deleteList = new ArrayList<>();
   * 
   * for (Contact contact : list) { if (contact.getName().equals(name)) {
   * deleteList.add(contact); } }
   * 
   * for (Contact contact : deleteList) { list.remove(contact); } changed =
   * true; System.out.println("삭제하였습니다."); }
   */

