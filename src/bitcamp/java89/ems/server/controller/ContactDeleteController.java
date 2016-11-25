package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.ContactDao;
import bitcamp.java89.ems.server.vo.Contact;

public class ContactDeleteController implements Command {
  private ContactDao contactDao;
  

  public ContactDeleteController() {
    contactDao = ContactDao.getInstance();
  }

  public void service(HashMap<String,String> paramMap, PrintStream out) {

    if (!contactDao.existEmail(paramMap.get("email"))) {
      out.println("존재하지 않는 이메일 입니다.");
      return;
    }

    contactDao.delete(paramMap.get("email"));
    out.println("삭제하였습니다.");
  }
}