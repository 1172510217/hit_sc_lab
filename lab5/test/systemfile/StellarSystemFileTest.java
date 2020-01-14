package systemfile;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import circularorbit.StellarSystem;
import iostrategy.BufferedIoStrategy;

import java.io.File;
import java.io.IOException;
import myexception.FileChooseException;
import org.junit.jupiter.api.Test;

class StellarSystemFileTest {

  /**
   * ����label����.
   */
  @Test void lableTest() throws IOException {
    File file = new File("src/TestFile/StellarSystemFile/LabelTest.txt");
    StellarSystem stellarSystem = new StellarSystem();
    try {
      stellarSystem.setReadFile(file);
      stellarSystem.readFileAndCreateSystem(new BufferedIoStrategy());
      assertTrue(false, "�˴����ɴ�");
    } catch (FileChooseException e) {
      assertEquals(e.getClass(), FileChooseException.class);
    }
  }

  /**
   * ��ѧ��������ʾ����.
   */
  @Test void enotationTest() throws IOException {
    File file = new File("src/TestFile/StellarSystemFile/E_NotationTest.txt");
    StellarSystem stellarSystem = new StellarSystem();
    try {
      stellarSystem.setReadFile(file);
      stellarSystem.readFileAndCreateSystem(new BufferedIoStrategy());
      assertTrue(false, "�˴����ɴ�");
    } catch (FileChooseException e) {
      assertEquals(e.getClass(), FileChooseException.class);
    }
  }

  /**
   * ���Թ�ת�������.
   */
  @Test void revolutionDirectionTest() throws IOException {
    File file =
        new File("src/TestFile/StellarSystemFile/RevolutionDirectionTest.txt");
    StellarSystem stellarSystem = new StellarSystem();
    try {
      stellarSystem.setReadFile(file);
      stellarSystem.readFileAndCreateSystem(new BufferedIoStrategy());
      assertTrue(false, "�˴����ɴ�");
    } catch (FileChooseException e) {
      assertEquals(e.getClass(), FileChooseException.class);
    }
  }

  /**
   * ���Գ�ʼ�Ƕȴ���.
   */
  @Test void angelTest() throws IOException {
    File file = new File("src/TestFile/StellarSystemFile/AngelTest.txt");
    StellarSystem stellarSystem = new StellarSystem();
    try {
      stellarSystem.setReadFile(file);
      stellarSystem.readFileAndCreateSystem(new BufferedIoStrategy());
      assertTrue(false, "�˴����ɴ�");
    } catch (FileChooseException e) {
      assertEquals(e.getClass(), FileChooseException.class);
    }
  }

  /**
   * ���Դ��ڱ�ǩ��ͬ������������ͬ��Ԫ��.
   */
  @Test void labelSameTest() throws IOException {
    File file = new File("src/TestFile/StellarSystemFile/LabelSameTest.txt");
    StellarSystem stellarSystem = new StellarSystem();
    try {
      stellarSystem.setReadFile(file);
      stellarSystem.readFileAndCreateSystem(new BufferedIoStrategy());
      assertTrue(false, "�˴����ɴ�");
    } catch (FileChooseException e) {
      assertEquals(e.getClass(), FileChooseException.class);
    }
  }

  /**
   * �������ĺ���ȱʧ.
   */
  @Test void noStellarTest() throws IOException {
    File file = new File("src/TestFile/StellarSystemFile/NoStellarTest.txt");
    StellarSystem stellarSystem = new StellarSystem();
    try {
      stellarSystem.setReadFile(file);
      stellarSystem.readFileAndCreateSystem(new BufferedIoStrategy());
      assertTrue(false, "�˴����ɴ�");
    } catch (FileChooseException e) {
      assertEquals(e.getClass(), FileChooseException.class);
    }
  }

  /**
   * ����һ��������ж������.
   */
  @Test void mutiTrackObjectTest() throws IOException {
    File file =
        new File("src/TestFile/StellarSystemFile/MutiTrackObjectTest.txt");
    StellarSystem stellarSystem = new StellarSystem();
    try {
      stellarSystem.setReadFile(file);
      stellarSystem.readFileAndCreateSystem(new BufferedIoStrategy());
      assertTrue(false, "�˴����ɴ�");
    } catch (FileChooseException e) {
      assertEquals(e.getClass(), FileChooseException.class);
    }
  }

  /**
   * ���Ե�i�������i-1����֮��С�ڵ����������ǰ뾶֮��.
   */
  @Test void trackRadiusTest() throws IOException {
    File file = new File("src/TestFile/StellarSystemFile/TrackRaidusTest.txt");
    StellarSystem stellarSystem = new StellarSystem();
    try {
      stellarSystem.setReadFile(file);
      stellarSystem.readFileAndCreateSystem(new BufferedIoStrategy());
      assertTrue(false, "�˴����ɴ�");
    } catch (FileChooseException e) {
      assertEquals(e.getClass(), FileChooseException.class);
    }
  }

}
