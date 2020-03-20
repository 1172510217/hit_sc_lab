package physicalObject;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PhysicalObjectTest {

    /**
     * testing strategy : �������ԣ�ѡȡ������һ������
     */
    @Test void testGetTrackRadius() {
        PhysicalObject physicalObject = new PhysicalObject();
        physicalObject.setTrackRadius(100);
        assertEquals(100.0, physicalObject.getTrackRadius());
    }

    /**
     * testing strategy : �������ԣ�ѡȡ������һ������
     */
    @Test void testSetTrackRadius() {
        PhysicalObject physicalObject = new PhysicalObject();
        physicalObject.setTrackRadius(100);
        assertEquals(100.0, physicalObject.getTrackRadius());
    }

    /**
     * testing strategy : �������ԣ�ѡȡ������һ������
     */
    @Test void testToString() {
        PhysicalObject physicalObject = new PhysicalObject();
        physicalObject.setTrackRadius(100);
        assertEquals("[e]", physicalObject.toString());
    }

}
