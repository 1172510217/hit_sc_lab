package centralobject;

import static org.junit.Assert.assertEquals;

import centralobject.CentralObject;
import org.junit.jupiter.api.Test;

class CentralObjectTest {

  /**
   * testing strategy : ����һ�������������.
   */
  @Test void testGetName() {
    CentralObject centralObject = new CentralObject();
    centralObject.setName("@Author ZJR");
    assertEquals("@Author ZJR", centralObject.getName());
  }

  /**
   * testing strategy : ����һ�������������.
   */
  @Test void testSetName() {
    CentralObject centralObject = new CentralObject();
    centralObject.setName("@Author ZJR");
    assertEquals("@Author ZJR", centralObject.getName());
  }

  /**
   * testing strategy : ����һ�������������.
   */
  @Test void testToString() {
    CentralObject centralObject = new CentralObject();
    centralObject.setName("@Author ZJR");
    assertEquals("@Author ZJR", centralObject.toString());
  }

}
