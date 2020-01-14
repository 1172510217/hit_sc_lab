package systemfile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import circularorbit.AtomStructure;
import iostrategy.BufferedIoStrategy;

import java.io.File;
import java.io.IOException;
import myexception.FileChooseException;
import org.junit.jupiter.api.Test;

class AtomStructureFileTest {

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
