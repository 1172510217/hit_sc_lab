package circularOrbit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import myException.FileChooseException;
import physicalObject.Friend;

class SocialNetworkCircleTest {

    /**
     * testing strategy : ��ǩ���Ϸ�
     */
    @Test void testLabel() throws IOException {
        SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
        File file = new File("src/TestFile/SocialNetworkFile/LabelTest.txt");
        try {
            socialNetworkCircle.readFileAndCreateSystem(file);
            assertTrue(false, "�˴����ɴ�");
        } catch (Exception e) {
            assertEquals(e.getClass(), new FileChooseException().getClass());
        }
    }

    /**
     * �����Ա𲻺Ϸ�
     */
    @Test void testSex() throws IOException {
        SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
        File file = new File("src/TestFile/SocialNetworkFile/SexTest.txt");
        try {
            socialNetworkCircle.readFileAndCreateSystem(file);
            assertTrue(false, "�˴����ɴ�");
        } catch (Exception e) {
            assertEquals(e.getClass(), new FileChooseException().getClass());
        }
    }

    /**
     * �������ܶȲ��Ϸ���Ϊ0
     */
    @Test void testIntimacyZero() throws IOException {
        SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
        File file = new File("src/TestFile/SocialNetworkFile/IntimacyTest1.txt");
        try {
            socialNetworkCircle.readFileAndCreateSystem(file);
            assertTrue(false, "�˴����ɴ�");
        } catch (Exception e) {
            assertEquals(e.getClass(), new FileChooseException().getClass());
        }
    }

    /**
     * �������ܶȲ��Ϸ���λ��������
     */
    @Test void testIntimacyDigit() throws IOException {
        SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
        File file = new File("src/TestFile/SocialNetworkFile/IntimacyTest2.txt");
        try {
            socialNetworkCircle.readFileAndCreateSystem(file);
            assertTrue(false, "�˴����ɴ�");
        } catch (Exception e) {
            assertEquals(e.getClass(), new FileChooseException().getClass());
        }
    }

    /**
     * �������䲻�Ϸ�����Ϊ����
     */
    @Test void testAge() throws IOException {
        SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
        File file = new File("src/TestFile/SocialNetworkFile/AgeTest.txt");
        try {
            socialNetworkCircle.readFileAndCreateSystem(file);
            assertTrue(false, "�˴����ɴ�");
        } catch (Exception e) {
            assertEquals(e.getClass(), new FileChooseException().getClass());
        }
    }

    /**
     * ���������û�ȱʧ
     */
    @Test void testNoCentralUser() throws IOException {
        SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
        File file = new File("src/TestFile/SocialNetworkFile/CentralUserTest.txt");
        try {
            socialNetworkCircle.readFileAndCreateSystem(file);
            assertTrue(false, "�˴����ɴ�");
        } catch (Exception e) {
            assertEquals(e.getClass(), new FileChooseException().getClass());
        }
    }

    /**
     * �������û�ͬ��
     */
    @Test void testLabelSame() throws IOException {
        SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
        File file = new File("src/TestFile/SocialNetworkFile/LabelSameTest.txt");
        try {
            socialNetworkCircle.readFileAndCreateSystem(file);
            assertTrue(false, "�˴����ɴ�");
        } catch (Exception e) {
            assertEquals(e.getClass(), new FileChooseException().getClass());
        }
    }

    /**
     * ����������ϵ����������δ������Ͳ��������ܶȹ���
     */
    @Test void testDependency() throws IOException {
        SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
        File file = new File("src/TestFile/SocialNetworkFile/DependencyTest.txt");
        try {
            socialNetworkCircle.readFileAndCreateSystem(file);
            assertTrue(false, "�˴����ɴ�");
        } catch (Exception e) {
            assertEquals(e.getClass(), new FileChooseException().getClass());
        }
    }

    /**
     * ���Զ���������Ϣ��
     */
    @Test void testUselessLine() throws IOException {
        SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
        File file = new File("src/TestFile/SocialNetworkFile/UselessLineTest.txt");
        try {
            socialNetworkCircle.readFileAndCreateSystem(file);
            assertTrue(false, "�˴����ɴ�");
        } catch (Exception e) {
            assertEquals(e.getClass(), new FileChooseException().getClass());
        }
    }

    @Test void testGetDistance() throws Exception {
        SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
        socialNetworkCircle
                .readFileAndCreateSystem(new File("src/Spring2019_HITCS_SC_Lab3-master/SocialNetworkCircle.txt"));
        int distance = socialNetworkCircle.getDistance(socialNetworkCircle.getCentralPoint(),
                socialNetworkCircle.getFriendByName("LisaWong"));
        assertEquals(1, distance);
    }

    @Test void testAddRelationAndRefactor() throws Exception {
        SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
        socialNetworkCircle
                .readFileAndCreateSystem(new File("src/Spring2019_HITCS_SC_Lab3-master/SocialNetworkCircle.txt"));
        socialNetworkCircle.addRelationAndRefactor(socialNetworkCircle.getCentralPoint(),
                socialNetworkCircle.getFriendByName("JackMa"), 0.9);
        assertTrue(socialNetworkCircle.getTrack(1).getTrackObjects()
                .contains(socialNetworkCircle.getFriendByName("JackMa")));
        assertFalse(socialNetworkCircle.addRelationAndRefactor(null, socialNetworkCircle.getCentralPoint(), 0.8));
        assertFalse(socialNetworkCircle.addRelationAndRefactor(socialNetworkCircle.getCentralPoint(), null, 0.8));
        assertFalse(socialNetworkCircle.addRelationAndRefactor(socialNetworkCircle.getCentralPoint(),
                socialNetworkCircle.getFriendByName("TomWong"), 0.5));
        assertFalse(socialNetworkCircle.addRelationAndRefactor(socialNetworkCircle.getCentralPoint(),
                socialNetworkCircle.getFriendByName("PonyMa"), 0.52121));
        assertFalse(socialNetworkCircle.addRelationAndRefactor(socialNetworkCircle.getCentralPoint(),
                socialNetworkCircle.getFriendByName("PonyMa"), 0));
    }

    /**
     * testing strategy : ����Ϊnull+ɾ����ĳ�û��������������û��Ĺ�ϵ
     */
    @Test void testDeleteAndRefactor() throws Exception {
        SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
        socialNetworkCircle
                .readFileAndCreateSystem(new File("src/Spring2019_HITCS_SC_Lab3-master/SocialNetworkCircle.txt"));
        socialNetworkCircle.deleteRelationAndRefactor(socialNetworkCircle.getCentralPoint(),
                socialNetworkCircle.getFriendByName("TomWong"));
        assertEquals(2, socialNetworkCircle.getTrackObjectsNumber(1));
        assertFalse(socialNetworkCircle.deleteRelationAndRefactor(null, socialNetworkCircle.getCentralPoint()));
        assertFalse(socialNetworkCircle.deleteRelationAndRefactor(socialNetworkCircle.getCentralPoint(), null));
        assertTrue(socialNetworkCircle.deleteRelationAndRefactor(socialNetworkCircle.getCentralPoint(),
                socialNetworkCircle.getFriendByName("LisaWong")));
        assertTrue(socialNetworkCircle.deleteRelationAndRefactor(socialNetworkCircle.getCentralPoint(),
                socialNetworkCircle.getFriendByName("DavidChen")));
    }

    /**
     * testing strategy : ����ĳ����+����ĳ����
     */
    @Test void testRemoveFriend() {
        SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
        Friend centralUser = new Friend();
        assertFalse(socialNetworkCircle.removeFriend(centralUser));
        socialNetworkCircle.addCentralUser(centralUser);
        assertTrue(socialNetworkCircle.removeFriend(centralUser));
    }

    /**
     * testing strategy : num��Χ����+num����
     */
    @Test void testGetFriend() {
        SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
        Friend friend = new Friend();
        Friend friend2 = new Friend();
        socialNetworkCircle.addFriend(friend);
        socialNetworkCircle.addFriend(friend2);
        assertEquals(friend, socialNetworkCircle.getFriend(1));
        assertEquals(null, socialNetworkCircle.getFriend(0));
        assertEquals(null, socialNetworkCircle.getFriend(3));
    }

    /**
     * testing strategy : friend���ǵ�һ��������+�ǵ�һ��������
     */
    @Test void testInfomationDiffusivity() throws IOException, FileChooseException {
        SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
        socialNetworkCircle
                .readFileAndCreateSystem(new File("src/Spring2019_HITCS_SC_Lab3-master/SocialNetworkCircle.txt"));
        assertEquals(-1, socialNetworkCircle.Informationdiffusivity(socialNetworkCircle.getFriendByName("JackMa")));
        assertEquals(1, socialNetworkCircle.Informationdiffusivity(socialNetworkCircle.getFriendByName("LisaWong")));
        assertEquals(0, socialNetworkCircle.Informationdiffusivity(socialNetworkCircle.getFriendByName("TomWong")));
    }

    /**
     * testing strategy : ����Ϊͬһ����+�����޹�ϵ+�й�ϵ
     */
    @Test void testGetLogicalDistance() throws Exception {
        SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
        socialNetworkCircle
                .readFileAndCreateSystem(new File("src/Spring2019_HITCS_SC_Lab3-master/SocialNetworkCircle.txt"));
        int distance = socialNetworkCircle.getLogicalDistance(socialNetworkCircle.getCentralPoint(),
                socialNetworkCircle.getFriendByName("LisaWong"));
        assertEquals(1, distance);
        assertEquals(0, socialNetworkCircle.getLogicalDistance(socialNetworkCircle.getCentralPoint(),
                socialNetworkCircle.getCentralPoint()));
        assertEquals(-1, socialNetworkCircle.getLogicalDistance(socialNetworkCircle.getCentralPoint(),
                socialNetworkCircle.getFriendByName("PonyMa")));
    }

    /**
     * testing strategy : Ϊ�����û�+�޹�ϵ+�й�ϵ
     */
    @Test void testGetFriendTrackNum() throws Exception {
        SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
        socialNetworkCircle
                .readFileAndCreateSystem(new File("src/Spring2019_HITCS_SC_Lab3-master/SocialNetworkCircle.txt"));
        assertEquals(0, socialNetworkCircle.getFriendTrackNum(socialNetworkCircle.getCentralPoint()));
        assertEquals(-1, socialNetworkCircle.getFriendTrackNum(socialNetworkCircle.getFriendByName("PonyMa")));
        assertEquals(1, socialNetworkCircle.getFriendTrackNum(socialNetworkCircle.getFriendByName("LisaWong")));
    }

    /**
     * testing strategy : ��ӿյ������û�
     */
    @Test void testAddCentralUser() {
        SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
        assertEquals(false, socialNetworkCircle.addCentralUser(null));
    }
    
    /**
     * testing strategy :�������û�
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
