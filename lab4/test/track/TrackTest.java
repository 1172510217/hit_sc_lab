package track;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import physicalObject.PhysicalObject;

class TrackTest {

    /**
     * testing strategy : ���Ի�ȡ����ȷ��
     */
    @Test void testGetRadius() {
        Track<PhysicalObject> track = new Track<>(100);
        assertEquals(track.getRadius(), 100);
    }

    /**
     * ����track�в���ĳ����+�Ѻ���ĳ����
     */
    @Test void testAdd() {
        Track<PhysicalObject> track = new Track<>(100);
        PhysicalObject physicalObject = new PhysicalObject();
        physicalObject.setTrackRadius(100);
        track.add(physicalObject);
        assertTrue(track.getTrackObjects().contains(physicalObject));
        Boolean res = track.add(physicalObject);
        assertFalse(res);
        assertTrue(track.contains(physicalObject));
    }

    /**
     * ����track�к���ĳ����ɾ��������򲻺���ĳ����ɾ�������
     */
    @Test void testRemove() {
        Track<PhysicalObject> track = new Track<>(100);
        boolean res = true;
        PhysicalObject physicalObject = new PhysicalObject();
        physicalObject.setTrackRadius(100);
        res = track.remove(physicalObject);
        // ����track�в�����ĳ����ɾ�������
        assertFalse(res);
        assertFalse(track.contains(physicalObject));
        // ����track�к���ĳ����ɾ�������
        track.add(physicalObject);
        res = track.remove(physicalObject);
        assertTrue(res);
        assertFalse(track.contains(physicalObject));
    }

    /**
     * ����track�в���ĳ���壨����false��+����ĳ���壨����true��
     */
    @Test void testContains() {
        Track<PhysicalObject> track = new Track<>(100);
        PhysicalObject physicalObject = new PhysicalObject();
        physicalObject.setTrackRadius(100);
        // ����track�в���ĳ���壨����false��
        assertFalse(track.contains(physicalObject));
        // ���Ժ���ĳ���壨����true��
        track.add(physicalObject);
        assertTrue(track.contains(physicalObject));
    }
    
    /**
     * testing strategy : ������ж������
     */
    @Test void testToString() {
        Track<PhysicalObject> track = new Track<>(100);
        PhysicalObject physicalObject = new PhysicalObject();
        physicalObject.setTrackRadius(100);
        PhysicalObject physicalObject1 = new PhysicalObject();
        physicalObject1.setTrackRadius(100);
        track.add(physicalObject);
        track.add(physicalObject1);
        String string = "radius=" + 100.0 + " : ";
        string += "[e]" + " ";
        string += "[e]" + " ";
        assertEquals(string, track.toString());
    }

    /**
     * testing strategy : track��������+���ж������
     */
    @Test void testGetNumberOfObjects() {
        Track<PhysicalObject> track = new Track<>(100);
        PhysicalObject physicalObject = new PhysicalObject();
        physicalObject.setTrackRadius(100);
        PhysicalObject physicalObject1 = new PhysicalObject();
        physicalObject1.setTrackRadius(100);
        // track��������
        assertEquals(0, track.getNumberOfObjects());
        // ���ж������
        track.add(physicalObject);
        track.add(physicalObject1);
        track.add(physicalObject1);
        assertEquals(2, track.getNumberOfObjects());
    }

    /**
     * testing strategy : �������
     */
    @Test void testGetTrackObjects() {
        Track<PhysicalObject> track = new Track<>(100);
        PhysicalObject physicalObject = new PhysicalObject();
        physicalObject.setTrackRadius(100);
        PhysicalObject physicalObject1 = new PhysicalObject();
        physicalObject1.setTrackRadius(100);
        track.add(physicalObject);
        track.add(physicalObject1);
        List<PhysicalObject> list = new ArrayList<>();
        list.add(physicalObject);
        list.add(physicalObject1);
        assertEquals(list, track.getTrackObjects());
    }

    /**
     * testing strategy : ������ĳ���壬����-1������ĳ���壬����index
     */
    @Test void testGetPhysicalObjectIndex() {
        Track<PhysicalObject> track = new Track<>(100);
        PhysicalObject physicalObject = new PhysicalObject();
        physicalObject.setTrackRadius(100);
        PhysicalObject physicalObject1 = new PhysicalObject();
        physicalObject1.setTrackRadius(100);
        // ������ĳ���壬����-1
        assertEquals(-1, track.getPhysicalObjectIndex(physicalObject));
        // ����ĳ���壬����index
        track.add(physicalObject1);
        track.add(physicalObject);
        assertEquals(1, track.getPhysicalObjectIndex(physicalObject));
    }

}
