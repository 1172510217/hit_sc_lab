package centralObject;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StellarTest {

    /**
     * testing strategy : ��������һ���������
     */
    @Test void testToString() {
        Stellar stellar = new Stellar();
        stellar.setName("@Author ZJR");
        stellar.setMess(100);
        stellar.setRadius(1000);
        String string = "@Author ZJR" + " [radius=" + 1000.0 + ", mess=" + 100.0 + "]";
        assertEquals(string, stellar.toString());
    }

    /**
     * testing strategy : ��������һ���������
     */
    @Test void testGetRadius() {
        Stellar stellar = new Stellar();
        stellar.setName("@Author ZJR");
        stellar.setMess(100);
        stellar.setRadius(1000);
        assertEquals(1000, stellar.getRadius());
    }

    /**
     * testing strategy : ��������һ���������
     */
    @Test void testSetRadius() {
        Stellar stellar = new Stellar();
        stellar.setName("@Author ZJR");
        stellar.setMess(100);
        stellar.setRadius(1000);
        assertEquals(1000, stellar.getRadius());
    }

    /**
     * testing strategy : ��������һ���������
     */
    @Test void testGetMess() {
        Stellar stellar = new Stellar();
        stellar.setName("@Author ZJR");
        stellar.setMess(100);
        stellar.setRadius(1000);
        assertEquals(100, stellar.getMess());
    }

    /**
     * testing strategy : ��������һ���������
     */
    @Test void testSetMess() {
        Stellar stellar = new Stellar();
        stellar.setName("@Author ZJR");
        stellar.setMess(100);
        stellar.setRadius(1000);
        assertEquals(100, stellar.getMess());
    }

}
