package circularorbit;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import circularorbit.AtomStructure;
import iostrategy.BufferedIoStrategy;

import java.io.File;
import java.io.IOException;
import myexception.FileChooseException;
import org.junit.jupiter.api.Test;
import physicalobject.PhysicalObject;

class AtomStructureTest {

  /**
   * testing strategy : ����ԾǨǰ��ԾǨ��������+�������.
   */
  @Test void testTransit() throws Exception {
    AtomStructure atomStructure = new AtomStructure();
    atomStructure.setReadFile(
        new File("src/Spring2019_HITCS_SC_Lab3-master/AtomicStructure.txt"));
    atomStructure.readFileAndCreateSystem(new BufferedIoStrategy());
    int num1 = atomStructure.getTrackObjectsNumber(1);
    int num2 = atomStructure.getTrackObjectsNumber(2);
    atomStructure.transit(atomStructure.getTrack(1).getIndexPhysicalObject(1),
        atomStructure.getTrack(2));
    assertTrue(atomStructure.getTrackObjectsNumber(1) == (num1 - 1)
        && atomStructure.getTrackObjectsNumber(2) == num2 + 1);
    assertFalse(atomStructure.transit(
        atomStructure.getTrack(1).getIndexPhysicalObject(1),
        atomStructure.getTrack(1)));
  }

  /**
   * testing strategy : ���Բ���ĳ����.
   */
  @Test void testDeletePhysicalObject()
      throws IOException, FileChooseException {
    AtomStructure atomStructure = new AtomStructure();
    atomStructure.setReadFile(
        new File("src/Spring2019_HITCS_SC_Lab3-master/AtomicStructure.txt"));
    atomStructure.readFileAndCreateSystem(new BufferedIoStrategy());
    PhysicalObject physicalObject = new PhysicalObject();
    assertFalse(atomStructure.deletePhysicalObject(physicalObject));
  }

  /**
   * ����Ԫ����ֻ��һ��Сд��ĸ+����������Ϣ��.
   */
  @Test void testAtomLabelCase() throws IOException {
    try {
      AtomStructure atomStructure = new AtomStructure();
      File file = new File("src/TestFile/AtomFile/LabelTest1.txt");
      atomStructure.setReadFile(file);
      atomStructure.readFileAndCreateSystem(new BufferedIoStrategy());
      assertTrue(false, "�˴����ɴ�!");
    } catch (Exception e) {
      assertEquals(e.getClass(), FileChooseException.class);
    }
  }

  /**
   * ����Ԫ������������ĸ+����������Ϣ��.
   */
  @Test void testAtomLabelLength() throws IOException {
    try {
      AtomStructure atomStructure = new AtomStructure();
      File file = new File("src/TestFile/AtomFile/LabelTest2.txt");
      atomStructure.setReadFile(file);
      atomStructure.readFileAndCreateSystem(new BufferedIoStrategy());
      assertTrue(false, "�˴����ɴ�!");
    } catch (Exception e) {
      assertEquals(e.getClass(), FileChooseException.class);
    }
  }

  /**
   * ���Թ����Ŀ��ʵ�ʸ����Ĺ������һ��+����������Ϣ��.
   */
  @Test void testAtomTrackNum() throws IOException {
    try {
      AtomStructure atomStructure = new AtomStructure();
      File file = new File("src/TestFile/AtomFile/TrackTest.txt");
      atomStructure.setReadFile(file);
      atomStructure.readFileAndCreateSystem(new BufferedIoStrategy());
      assertTrue(false, "�˴����ɴ�!");
    } catch (Exception e) {
      assertEquals(e.getClass(), FileChooseException.class);
    }
  }

  /**
   * ���Զ�ȡ�ļ�ȱ����Ϣ��.
   */
  @Test void testLackOfLine() throws IOException {
    try {
      AtomStructure atomStructure = new AtomStructure();
      File file = new File("src/TestFile/AtomFile/LackOfLineTest.txt");
      atomStructure.setReadFile(file);
      atomStructure.readFileAndCreateSystem(new BufferedIoStrategy());
      assertTrue(false, "�˴����ɴ�!");
    } catch (Exception e) {
      assertEquals(e.getClass(), FileChooseException.class);
    }
  }

  /**
   * �����ļ�������˳������.
   */
  @Test void testTrackOrder() throws IOException {
    try {
      AtomStructure atomStructure = new AtomStructure();
      File file = new File("src/TestFile/AtomFile/TrackNumOrderTest.txt");
      atomStructure.setReadFile(file);
      atomStructure.readFileAndCreateSystem(new BufferedIoStrategy());
      assertTrue(false, "�˴����ɴ�!");
    } catch (Exception e) {
      assertEquals(e.getClass(), FileChooseException.class);
    }
  }
}
