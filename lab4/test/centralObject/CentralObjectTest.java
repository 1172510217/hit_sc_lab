package centralObject;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class CentralObjectTest {

    /**
     * testing strategy : ����һ�������������
     */
    @Test void testGetName() {
        CentralObject centralObject = new CentralObject();
        centralObject.setName("@Author ZJR");
        assertEquals("@Author ZJR", centralObject.getName());
    }

    /**
     * testing strategy : ����һ�������������
     */
    @Test void testSetName() {
        CentralObject centralObject = new CentralObject();
        centralObject.setName("@Author ZJR");
        assertEquals("@Author ZJR", centralObject.getName());
    }

    /**
     * testing strategy : ����һ�������������
     */
    @Test void testToString() {
        CentralObject centralObject = new CentralObject();
        centralObject.setName("@Author ZJR");
        assertEquals("@Author ZJR", centralObject.toString());
    }

}
