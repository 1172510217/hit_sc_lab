package circularorbit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import circularorbit.SocialNetworkCircle;
import iostrategy.BufferedIoStrategy;

import java.io.File;
import java.io.IOException;
import myexception.FileChooseException;
import org.junit.jupiter.api.Test;
import physicalobject.Friend;

class SocialNetworkCircleTest {

  /**
   * testing strategy : ��ǩ���Ϸ�.
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

  @Test void testGetDistance() throws Exception {
    SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
    File file =
        new File("src/Spring2019_HITCS_SC_Lab3-master/SocialNetworkCircle.txt");
    socialNetworkCircle.setReadFile(file);
    socialNetworkCircle.readFileAndCreateSystem(new BufferedIoStrategy());
    int distance =
        socialNetworkCircle.getDistance(socialNetworkCircle.getCentralPoint(),
            socialNetworkCircle.getFriendByName("LisaWong"));
    assertEquals(1, distance);
  }

  @Test void testAddRelationAndRefactor() throws Exception {
    SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
    File file =
        new File("src/Spring2019_HITCS_SC_Lab3-master/SocialNetworkCircle.txt");
    socialNetworkCircle.setReadFile(file);
    socialNetworkCircle.readFileAndCreateSystem(new BufferedIoStrategy());
    socialNetworkCircle.addRelationAndRefactor(
        socialNetworkCircle.getCentralPoint(),
        socialNetworkCircle.getFriendByName("JackMa"), 0.9);
    assertTrue(socialNetworkCircle.getTrack(1)
        .contains(socialNetworkCircle.getFriendByName("JackMa")));
    assertFalse(socialNetworkCircle.addRelationAndRefactor(null,
        socialNetworkCircle.getCentralPoint(), 0.8));
    assertFalse(socialNetworkCircle.addRelationAndRefactor(
        socialNetworkCircle.getCentralPoint(), null, 0.8));
    assertFalse(socialNetworkCircle.addRelationAndRefactor(
        socialNetworkCircle.getCentralPoint(),
        socialNetworkCircle.getFriendByName("TomWong"), 0.5));
    assertFalse(socialNetworkCircle.addRelationAndRefactor(
        socialNetworkCircle.getCentralPoint(),
        socialNetworkCircle.getFriendByName("PonyMa"), 0.52121));
    assertFalse(socialNetworkCircle.addRelationAndRefactor(
        socialNetworkCircle.getCentralPoint(),
        socialNetworkCircle.getFriendByName("PonyMa"), 0));
  }

  /**
   * testing strategy : ����Ϊnull+ɾ����ĳ�û��������������û��Ĺ�ϵ.
   */
  @Test void testDeleteAndRefactor() throws Exception {
    SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
    File file =
        new File("src/Spring2019_HITCS_SC_Lab3-master/SocialNetworkCircle.txt");
    socialNetworkCircle.setReadFile(file);
    socialNetworkCircle.readFileAndCreateSystem(new BufferedIoStrategy());
    socialNetworkCircle.deleteRelationAndRefactor(
        socialNetworkCircle.getCentralPoint(),
        socialNetworkCircle.getFriendByName("TomWong"));
    assertEquals(2, socialNetworkCircle.getTrackObjectsNumber(1));
    assertFalse(socialNetworkCircle.deleteRelationAndRefactor(null,
        socialNetworkCircle.getCentralPoint()));
    assertFalse(socialNetworkCircle.deleteRelationAndRefactor(
        socialNetworkCircle.getCentralPoint(), null));
    assertTrue(socialNetworkCircle.deleteRelationAndRefactor(
        socialNetworkCircle.getCentralPoint(),
        socialNetworkCircle.getFriendByName("LisaWong")));
    assertTrue(socialNetworkCircle.deleteRelationAndRefactor(
        socialNetworkCircle.getCentralPoint(),
        socialNetworkCircle.getFriendByName("DavidChen")));
  }

  /**
   * testing strategy : ����ĳ����+����ĳ����.
   */
  @Test void testRemoveFriend() {
    SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
    Friend centralUser = new Friend();
    centralUser.setFriendName("TOMMYWONG");
    assertFalse(socialNetworkCircle.removeFriend(centralUser));
    socialNetworkCircle.addCentralUser(centralUser);
    assertTrue(socialNetworkCircle.removeFriend(centralUser));
  }

  /**
   * testing strategy : num��Χ����+num����.
   */
  @Test void testGetFriend() {
    SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
    Friend friend = new Friend();
    Friend friend2 = new Friend();
    friend.setFriendName("xiaoming");
    friend2.setFriendName("xiaozhang");
    socialNetworkCircle.addFriend(friend);
    socialNetworkCircle.addFriend(friend2);
    assertEquals(friend, socialNetworkCircle.getFriend(1));
    assertEquals(null, socialNetworkCircle.getFriend(0));
    assertEquals(null, socialNetworkCircle.getFriend(3));
  }

  /**
   * testing strategy : friend���ǵ�һ��������+�ǵ�һ��������.
   */
  @Test void testInfomationDiffusivity()
      throws IOException, FileChooseException {
    SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
    File file =
        new File("src/Spring2019_HITCS_SC_Lab3-master/SocialNetworkCircle.txt");
    socialNetworkCircle.setReadFile(file);
    socialNetworkCircle.readFileAndCreateSystem(new BufferedIoStrategy());
    assertEquals(-1, socialNetworkCircle
        .informationdiffusivity(socialNetworkCircle.getFriendByName("JackMa")));
    assertEquals(1, socialNetworkCircle.informationdiffusivity(
        socialNetworkCircle.getFriendByName("LisaWong")));
    assertEquals(0, socialNetworkCircle.informationdiffusivity(
        socialNetworkCircle.getFriendByName("TomWong")));
  }

  /**
   * testing strategy : ����Ϊͬһ����+�����޹�ϵ+�й�ϵ.
   */
  @Test void testGetLogicalDistance() throws Exception {
    SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
    File file =
        new File("src/Spring2019_HITCS_SC_Lab3-master/SocialNetworkCircle.txt");
    socialNetworkCircle.setReadFile(file);
    socialNetworkCircle.readFileAndCreateSystem(new BufferedIoStrategy());
    int distance = socialNetworkCircle.getLogicalDistance(
        socialNetworkCircle.getCentralPoint(),
        socialNetworkCircle.getFriendByName("LisaWong"));
    assertEquals(1, distance);
    assertEquals(0,
        socialNetworkCircle.getLogicalDistance(
            socialNetworkCircle.getCentralPoint(),
            socialNetworkCircle.getCentralPoint()));
    assertEquals(-1,
        socialNetworkCircle.getLogicalDistance(
            socialNetworkCircle.getCentralPoint(),
            socialNetworkCircle.getFriendByName("PonyMa")));
  }

  /**
   * testing strategy : Ϊ�����û�+�޹�ϵ+�й�ϵ.
   */
  @Test void testGetFriendTrackNum() throws Exception {
    SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
    File file =
        new File("src/Spring2019_HITCS_SC_Lab3-master/SocialNetworkCircle.txt");
    socialNetworkCircle.setReadFile(file);
    socialNetworkCircle.readFileAndCreateSystem(new BufferedIoStrategy());
    assertEquals(0, socialNetworkCircle
        .getFriendTrackNum(socialNetworkCircle.getCentralPoint()));
    assertEquals(-1, socialNetworkCircle
        .getFriendTrackNum(socialNetworkCircle.getFriendByName("PonyMa")));
    assertEquals(1, socialNetworkCircle
        .getFriendTrackNum(socialNetworkCircle.getFriendByName("LisaWong")));
  }

  /**
   * testing strategy : ��ӿյ������û�.
   */
  @Test void testAddCentralUser() {
    SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
    assertEquals(false, socialNetworkCircle.addCentralUser(null));
  }

  /**
   * testing strategy :�������û�.
   */
  @Test void testCheckRep() {
    SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
    try {
      socialNetworkCircle.checkRep();
    } catch (FileChooseException e) {
      assertTrue(true);
    }
  }
}
