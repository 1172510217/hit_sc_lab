package systemfile;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import circularorbit.SocialNetworkCircle;
import iostrategy.BufferedIoStrategy;

import java.io.File;
import java.io.IOException;
import myexception.FileChooseException;
import org.junit.jupiter.api.Test;

class SocialNetworkFileTest {

  /**
   * ���Ա�ǩ���Ϸ�.
   */
  @Test void testLabel() throws IOException {
    SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
    File file = new File("src/TestFile/SocialNetworkFile/LabelTest.txt");
    try {
      socialNetworkCircle.setReadFile(file);
      socialNetworkCircle.readFileAndCreateSystem(new BufferedIoStrategy());
      assertTrue(false, "�˴����ɴ�");
    } catch (Exception e) {
      assertEquals(e.getClass(), FileChooseException.class);
    }
  }

  /**
   * �����Ա𲻺Ϸ�.
   */
  @Test void testSex() throws IOException {
    SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
    File file = new File("src/TestFile/SocialNetworkFile/SexTest.txt");
    try {
      socialNetworkCircle.setReadFile(file);
      socialNetworkCircle.readFileAndCreateSystem(new BufferedIoStrategy());
      assertTrue(false, "�˴����ɴ�");
    } catch (Exception e) {
      assertEquals(e.getClass(), FileChooseException.class);
    }
  }

  /**
   * �������ܶȲ��Ϸ���Ϊ0.
   */
  @Test void testIntimacyZero() throws IOException {
    SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
    File file = new File("src/TestFile/SocialNetworkFile/IntimacyTest1.txt");
    try {
      socialNetworkCircle.setReadFile(file);
      socialNetworkCircle.readFileAndCreateSystem(new BufferedIoStrategy());
      assertTrue(false, "�˴����ɴ�");
    } catch (Exception e) {
      assertEquals(e.getClass(), FileChooseException.class);
    }
  }

  /**
   * �������ܶȲ��Ϸ���λ��������.
   */
  @Test void testIntimacyDigit() throws IOException {
    SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
    File file = new File("src/TestFile/SocialNetworkFile/IntimacyTest2.txt");
    try {
      socialNetworkCircle.setReadFile(file);
      socialNetworkCircle.readFileAndCreateSystem(new BufferedIoStrategy());
      assertTrue(false, "�˴����ɴ�");
    } catch (Exception e) {
      assertEquals(e.getClass(), FileChooseException.class);
    }
  }

  /**
   * �������䲻�Ϸ�����Ϊ����.
   */
  @Test void testAge() throws IOException {
    SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
    File file = new File("src/TestFile/SocialNetworkFile/AgeTest.txt");
    try {
      socialNetworkCircle.setReadFile(file);
      socialNetworkCircle.readFileAndCreateSystem(new BufferedIoStrategy());
      assertTrue(false, "�˴����ɴ�");
    } catch (Exception e) {
      assertEquals(e.getClass(), FileChooseException.class);
    }
  }

  /**
   * ���������û�ȱʧ.
   */
  @Test void testNoCentralUser() throws IOException {
    SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
    File file = new File("src/TestFile/SocialNetworkFile/CentralUserTest.txt");
    try {
      socialNetworkCircle.setReadFile(file);
      socialNetworkCircle.readFileAndCreateSystem(new BufferedIoStrategy());
      assertTrue(false, "�˴����ɴ�");
    } catch (Exception e) {
      assertEquals(e.getClass(), FileChooseException.class);
    }
  }

  /**
   * �������û�ͬ��.
   */
  @Test void testLabelSame() throws IOException {
    SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
    File file = new File("src/TestFile/SocialNetworkFile/LabelSameTest.txt");
    try {
      socialNetworkCircle.setReadFile(file);
      socialNetworkCircle.readFileAndCreateSystem(new BufferedIoStrategy());
      assertTrue(false, "�˴����ɴ�");
    } catch (Exception e) {
      assertEquals(e.getClass(), FileChooseException.class);
    }
  }

  /**
   * ����������ϵ����������δ������Ͳ��������ܶȹ���.
   */
  @Test void testDependency() throws IOException {
    SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
    File file = new File("src/TestFile/SocialNetworkFile/DependencyTest.txt");
    try {
      socialNetworkCircle.setReadFile(file);
      socialNetworkCircle.readFileAndCreateSystem(new BufferedIoStrategy());
      assertTrue(false, "�˴����ɴ�");
    } catch (Exception e) {
      assertEquals(e.getClass(), FileChooseException.class);
    }
  }

  /**
   * ���Զ���������Ϣ��.
   */
  @Test void testUselessLine() throws IOException {
    SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
    File file = new File("src/TestFile/SocialNetworkFile/UselessLineTest.txt");
    try {
      socialNetworkCircle.setReadFile(file);
      socialNetworkCircle.readFileAndCreateSystem(new BufferedIoStrategy());
      assertTrue(false, "�˴����ɴ�");
    } catch (Exception e) {
      assertEquals(e.getClass(), FileChooseException.class);
    }
  }
}
