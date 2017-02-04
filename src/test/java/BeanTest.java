import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;

/**
 * @author xxf
 * @since 1/18/17
 */
@Service
public class BeanTest {

  public static void main(String[] args) {
    BeanTest beanTest = new BeanTest();
    Annotation service = beanTest.getClass().getAnnotation(Service.class);
    Class clazz = service.annotationType();
    System.out.println(clazz);
  }




}
