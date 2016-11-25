package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.AbstractCommand;
import bitcamp.java89.ems.server.dao.ContactDao;

public class ContactDeleteController extends AbstractCommand {

  @Override
  protected void doResponse(HashMap<String,String> paramMap, PrintStream out)
      throws Exception {
    ContactDao contactDao = ContactDao.getInstance();
    if (!contactDao.existEmail(paramMap.get("email"))) {
      out.println("존재하지 않는 이메일 입니다.");
      return;
    }

    contactDao.delete(paramMap.get("email"));
    out.println("삭제하였습니다.");
  }
}