/* 역할: 빈 컨테이너(Bean Container) == IoC 컨테이너
 * => Bean = object = instance
 * => 객체의 생성과 소멸을 관리한다.
 * => 또한 객체가 사용하는 의존 객체 주입을 수행한다.*/
package bitcamp.java89.ems.server.context;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import bitcamp.java89.ems.server.annotation.Component;

public class ApplicationContext {
  HashMap<String,Object> objPool = new HashMap<>();

  public ApplicationContext(String[] packages) {
    this(packages, null); //밑을 호출하고 널이면 밑 3줄만 실행 
  }
  //객체가 미리들어있으면(바깥에 있는) 것도 담고 
  public ApplicationContext(String[] packages, HashMap<String, Object> builtInObjMap) {
    if (builtInObjMap != null) {
//      외부에서 넘겨준 객체를 포함시킨다.
      Set<Entry<String,Object>> entrySet = builtInObjMap.entrySet();
      for (Entry<String,Object> entry : entrySet) { //그 목록은 반복문
        objPool.put(entry.getKey(), entry.getValue());// 키가 이름 값을 pool에 넣는 것.
      }
    }
    ArrayList<Class<?>> classList = getClassList(packages); //스트링으로 된 
    prepareObjects(classList); //클래스로부터 객체 준비
    injectDependencies();
    //주어진 패키지를 뒤져서 @애노테이션을 찾아서 객체를 만들고 의존객체 주입하는일!
//    자동으로 만드는것 외에 
//    기존에 있는거 MAP객체에 데이터가있으면 
  }

  
  public Object getBean(String name) {
    return objPool.get(name);
  }
  
//  이 보관소에 저장된 모드 객체를 리턴.
  public Collection<Object> getAllBeans() {
    return objPool.values();
  }
  
  private void injectDependencies() {
//    HashMap에 저장된 객체 목록을 뽑아 온다.
    Collection<Object> objects = objPool.values();
    
    for (Object obj : objects) {
//      System.out.println(obj.getClass().getName());
//      각 객체의 public 메서드 목록을 뽑는다.
      Method[] methods = obj.getClass().getMethods();
      
      for (Method m : methods) {
        if (!m.getName().startsWith("set")) {  //셋터가 아니면 제외시킨다.
          continue;
        }
        
        if (m.getParameterCount() != 1) {  //파라미터 개수가 1개가 아니면 제외시킨다.
          continue;
        }
//        셋터의 0번째 파라미터 타입을 알아낸다.
        Class<?> paramType = m.getParameterTypes()[0];
        
//        그 타입에 해당하는 객체를 objPool에서 찾는다.
        Object dependency = findDependency(paramType);
        
        if (dependency != null) { //찾았다면,
          try {
//            System.out.println("==>" + m.getName());
            m.invoke(obj, dependency);  //의존 객체를 주입하기위해 셋터를 호출한다.
          } catch (Exception e) {}
        }
      }
    }
    
  }

  private Object findDependency(Class<?> paramType) {
    Collection<Object> objects = objPool.values();
    for (Object obj : objects) {
      if (paramType.isInstance(obj)) {
        return obj;
      }
    }
    return null;
  }

  private ArrayList<Class<?>> getClassList(String[] packages) {
    ArrayList<Class<?>> classList = new ArrayList<>();
    
    for (String packageName : packages) {
      try {
        findClasses(packageNameToFile(packageName), classList);  //클래스 목록 가져와
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return classList;
  }

  private void prepareObjects(ArrayList<Class<?>> classList) {
    for (Class<?> clazz : classList) {
      try {
        Object obj = clazz.newInstance();
        
//        클래스에 태깅된 Component 애노테이션 정보를 꺼낸다.
        Component compAnno = clazz.getAnnotation(Component.class);
        
//        애노테이션의 값을 저장할 때는 변수처럼, 값을 꺼낼 때는 메서드처럼 사용한다.
        if (compAnno.value().length() == 0) { //빈 문자열 이라면
          objPool.put(clazz.getName(), obj);  // 클래스 이름으로 객체를 저장하고,
          System.out.println(clazz.getName());
        } else {
          objPool.put(compAnno.value(), obj);//애노테이션에 기록한 이름으로 객체를 저장한다.
          System.out.println(compAnno.value());
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
  
  private File packageNameToFile(String packageName) {
//  클래스 파일을 찾을 디렉토리 경로를 정의한다.
//  그런데 파라미터로 넘어오는 값은 순수한 패키지 이름
//    예) bitcamp.java89.ems.server.controller)으로 되어있다.
//  그래서 다음과 같이 파일 경로로 만들려면 "."을 "/"로 변경해야한다.
//  예) ./bin/bitcamp/java89/ems/server/controller
    return new File("./bin/" + packageName.replaceAll("\\.", "/")); 
    
  }

  private void findClasses(File dir, ArrayList<Class<?>> classList) {
    if (!dir.isDirectory()) {             //ㄴ어느클래스인지 상관없다.
      return;
    }
    
    File[] files = dir.listFiles(new FileFilter() {
      @Override
      public boolean accept(File pathname) {
        if (pathname.isDirectory())
          return true;
        if (pathname.getName().endsWith(".class") && !pathname.getName().contains("$"))
          return true;
        return false;
      }//**true면 디렉토리 객체가 포함하는 파일의 이름이 걸러진다.
    });
    
    for (File file : files) {
      if (file.isDirectory()) {
        findClasses(file, classList);
        
      } else {
        try {
          Class<?> c = loadClass(file);
          
          if (!isAbstract(c) && isComponent(c)) {
            classList.add(c);
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }
  
  private boolean isComponent(Class<?> c) {
    return c.getAnnotation(Component.class) != null;
  }
  
  
  private Class<?> loadClass(File file) throws IOException, ClassNotFoundException {
    String path = file.getCanonicalPath().replaceAll("\\\\","/").replaceAll(".class", "");
    int pos = path.indexOf("/bin/");
    return Class.forName(path.substring(pos + 5).replaceAll("/", "."));
//    forName에 올수있는건 클래스이름.
  }
  
  private boolean isAbstract(Class<?> clazz) {
    if ((clazz.getModifiers() & Modifier.ABSTRACT) == Modifier.ABSTRACT) {
      return true;
    }
    return false;
  }
  
  
//  public static void main(String[] args) {
//    ApplicationContext appContext = new ApplicationContext(new String[] {
//            "bitcamp.java89.ems.server.controller",
//            "bitcamp.java89.ems.server.dao"});
//  }
  
  //  ======> Test용으로 만든 예제임. 필요한 경우 main메소드 만들어주면됨
}
