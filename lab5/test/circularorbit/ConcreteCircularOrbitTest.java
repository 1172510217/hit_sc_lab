package circularorbit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import centralobject.CentralObject;
import circularorbit.ConcreteCircularOrbit;
import java.util.Iterator;
import org.junit.jupiter.api.Test;
import physicalobject.PhysicalObject;
import track.Track;

class ConcreteCircularOrbitTest {

  /**
   * testing strategy : ѡȡһ�������Ĳ�������.
   */
  @Test void testIterator() {
    ConcreteCircularOrbit<CentralObject, PhysicalObject> circularOrbit =
        new ConcreteCircularOrbit<>();
    CentralObject centralObject = new CentralObject();
    circularOrbit.addCentralPoint(centralObject);
    Track<PhysicalObject> track1 = new Track<>(1);
    Track<PhysicalObject> track2 = new Track<>(2);
    PhysicalObject physicalObject1 = new PhysicalObject();
    circularOrbit.addTrack(track1);
    circularOrbit.addTrack(track2);
    track1.add(physicalObject1);
    PhysicalObject physicalObject2 = new PhysicalObject();
    track1.add(physicalObject2);
    PhysicalObject physicalObject3 = new PhysicalObject();
    track2.add(physicalObject3);
    circularOrbit.sortTrack();
    Iterator<PhysicalObject> itertor = circularOrbit.iterator();
    int i = 0;
    while (itertor.hasNext()) {
      assertEquals(circularOrbit.getEByNum(++i), itertor.next());
    }
  }

  /**
   * testing strategy : ��������в����ù��+���иù��.
   */
  @Test void testAddTrack() {
    ConcreteCircularOrbit<CentralObject, PhysicalObject> circularOrbit =
        new ConcreteCircularOrbit<>();
    Track<PhysicalObject> track = new Track<>(1);
    // ��������в����ü���
    assertTrue(circularOrbit.addTrack(track));
    // ��������к��иü���
    assertFalse(circularOrbit.addTrack(track));
  }

  /**
   * testing strategy : ��������в����ù��+���иù��.
   */
  @Test void testDeleteTrack() {
    ConcreteCircularOrbit<CentralObject, PhysicalObject> circularOrbit =
        new ConcreteCircularOrbit<>();
    Track<PhysicalObject> track = new Track<>(1);
    circularOrbit.addTrack(track);
    // ��������к��иü���
    assertTrue(circularOrbit.deleteTrack(track));
    // ��������в����ü���
    assertFalse(circularOrbit.deleteTrack(track));
  }

  /**
   * testing strategy : ϵͳ��������Ϊnull + ��Ϊnull.
   */
  @Test void testAddCentralPoint() {
    ConcreteCircularOrbit<CentralObject, PhysicalObject> circularOrbit =
        new ConcreteCircularOrbit<>();
    CentralObject centralObject = new CentralObject();
    // ϵͳ��������Ϊnull
    assertTrue(circularOrbit.addCentralPoint(centralObject));
    // ϵͳ�������岻Ϊnull
    assertFalse(circularOrbit.addCentralPoint(centralObject));
  }

  /**
   * testing strategy : �����иù��+���иð뾶���.
   */
  @Test void testGetTrackByRadius() {
    ConcreteCircularOrbit<CentralObject, PhysicalObject> circularOrbit =
        new ConcreteCircularOrbit<>();
    Track<PhysicalObject> track1 = new Track<>(1);
    circularOrbit.addTrack(track1);
    // �����ض��뾶���
    assertEquals(track1, circularOrbit.getTrackByRadius(1));
    // �����ض��뾶���
    assertEquals(null, circularOrbit.getTrackByRadius(2));
  }

  /**
   * testing strategy : ����һ����������.
   */
  @Test void testToString() {
    ConcreteCircularOrbit<CentralObject, PhysicalObject> circularOrbit =
        new ConcreteCircularOrbit<>();
    CentralObject centralObject = new CentralObject();
    circularOrbit.addCentralPoint(centralObject);
    Track<PhysicalObject> track1 = new Track<>(1);
    circularOrbit.addTrack(track1);
    String string = "";
    string += "The centralPoint and all the tracks are as follows\n";
    string += "centralpoint : " + circularOrbit.getCentralPoint() + "\n";
    string += track1 + "\n";
    assertEquals(string, circularOrbit.toString());
  }

  /**
   * testing strategy : �޹��+ֻ��һ�����+������������.
   */
  @Test void testGetSystemEntropy() {
    ConcreteCircularOrbit<CentralObject, PhysicalObject> circularOrbit =
        new ConcreteCircularOrbit<>();
    CentralObject centralObject = new CentralObject();
    circularOrbit.addCentralPoint(centralObject);
    // �����޹��ʱ����ֵΪ0
    assertEquals(0, circularOrbit.getSystemEntropy());
    Track<PhysicalObject> track1 = new Track<>(1);
    circularOrbit.addTrack(track1);
    track1.add(new PhysicalObject());
    circularOrbit.sortTrack();
    // ����ֻ��һ�����ʱ����ֵΪ0
    assertEquals(0, circularOrbit.getSystemEntropy());
    Track<PhysicalObject> track2 = new Track<>(2);
    circularOrbit.addTrack(track2);
    track2.add(new PhysicalObject());
    // ����һ�����
    circularOrbit.sortTrack();
    assertTrue(Math.abs(0.9183 - circularOrbit.getSystemEntropy()) < 0.01);
  }

  /**
   * testing strategy : num <= 0 ; num > �������� ;����.
   */
  @Test void testGetEByNum() {
    ConcreteCircularOrbit<CentralObject, PhysicalObject> circularOrbit =
        new ConcreteCircularOrbit<>();
    CentralObject centralObject = new CentralObject();
    circularOrbit.addCentralPoint(centralObject);
    Track<PhysicalObject> track1 = new Track<>(1);
    Track<PhysicalObject> track2 = new Track<>(2);
    PhysicalObject physicalObject1 = new PhysicalObject();
    circularOrbit.addTrack(track1);
    circularOrbit.addTrack(track2);
    track1.add(physicalObject1);
    PhysicalObject physicalObject2 = new PhysicalObject();
    track1.add(physicalObject2);
    PhysicalObject physicalObject3 = new PhysicalObject();
    track2.add(physicalObject3);
    circularOrbit.sortTrack();
    // num <= 0
    assertEquals(null, circularOrbit.getEByNum(0));
    // �������
    assertEquals(physicalObject1, circularOrbit.getEByNum(1));
    // �������
    assertEquals(physicalObject3, circularOrbit.getEByNum(3));
    // num������������
    assertEquals(null, circularOrbit.getEByNum(4));
  }

  /**
   * testing strategy:����������С�ڵ���0+���ڹ����+����.
   */
  @Test void testGetTrackObjectsNumber() {
    ConcreteCircularOrbit<CentralObject, PhysicalObject> circularOrbit =
        new ConcreteCircularOrbit<>();
    Track<PhysicalObject> track = new Track<>(1);
    circularOrbit.addTrack(track);
    track.add(new PhysicalObject());
    track.add(new PhysicalObject());
    circularOrbit.sortTrack();
    // ����������С�ڵ���0
    assertEquals(-1, circularOrbit.getTrackObjectsNumber(0));
    assertEquals(-1, circularOrbit.getTrackObjectsNumber(2));
    // ������������ڹ����
    assertEquals(2, circularOrbit.getTrackObjectsNumber(1));
  }

  /**
   * testing strategy:����������С�ڵ���0+���ڹ����+����.
   */
  @Test void testGetTrack() {
    ConcreteCircularOrbit<CentralObject, PhysicalObject> circularOrbit =
        new ConcreteCircularOrbit<>();
    Track<PhysicalObject> track1 = new Track<>(1);
    Track<PhysicalObject> track2 = new Track<>(2);
    circularOrbit.addTrack(track1);
    circularOrbit.addTrack(track2);
    circularOrbit.sortTrack();
    assertEquals(track1, circularOrbit.getTrack(1));
    // ���ڹ����
    assertEquals(null, circularOrbit.getTrack(3));
    // ����������С�ڵ���0
    assertEquals(null, circularOrbit.getTrack(0));
  }

}
