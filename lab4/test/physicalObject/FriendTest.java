package physicalObject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class FriendTest {

    /**
     * testing strategy : ����friend�Ա�ΪF+ΪM
     */
    @Test void testToString() {
        Friend friend = new Friend();
        friend.setFriendName("@Author ZJR");
        friend.setAge(10);
        friend.setSex("M");
        assertEquals("[name=" + friend.getFriendName() + ", age=" + friend.getAge() + " sex=��" + "]",
                friend.toString());
        friend.setSex("F");
        assertEquals("[name=" + friend.getFriendName() + ", age=" + friend.getAge() + " sex=Ů" + "]",
                friend.toString());
    }

    /**
     * testing strategy : �������ԣ�ѡȡһ����������
     */
    @Test void testGetFriendName() {
        Friend friend = new Friend();
        friend.setFriendName("@Author ZJR");
        assertEquals("@Author ZJR", friend.getFriendName());
    }

    /**
     * testing strategy : �������ԣ�ѡȡһ����������
     */
    @Test void testSetFriendName() {
        Friend friend = new Friend();
        friend.setFriendName("@Author ZJR");
        assertEquals("@Author ZJR", friend.getFriendName());
    }

    /**
     * testing strategy : �������ԣ�ѡȡһ����������
     */
    @Test void testGetAge() {
        Friend friend = new Friend();
        friend.setFriendName("@Author ZJR");
        friend.setAge(10);
        friend.setSex("M");
        assertEquals(10, friend.getAge());
    }

    /**
     * testing strategy : �������ԣ�ѡȡһ����������
     */
    @Test void testSetAge() {
        Friend friend = new Friend();
        friend.setFriendName("@Author ZJR");
        friend.setAge(10);
        friend.setSex("M");
        assertEquals(10, friend.getAge());
    }

    /**
     * testing strategy : �������ԣ�ѡȡһ����������
     */
    @Test void testGetSex() {
        Friend friend = new Friend();
        friend.setFriendName("@Author ZJR");
        friend.setAge(10);
        friend.setSex("M");
        assertEquals("M", friend.getSex());
    }

    /**
     * testing strategy : �������ԣ�ѡȡһ����������
     */
    @Test void testSetSex() {
        Friend friend = new Friend();
        friend.setFriendName("@Author ZJR");
        friend.setAge(10);
        friend.setSex("M");
        assertEquals("M", friend.getSex());

    }

    /**
     * testing strategy :friends���Ѻ��и�����+�����и�����
     */
    @Test void testAddSocialTie() {
        Friend centralUser = new Friend();
        Friend friend1 = new Friend();
        boolean res = centralUser.addSocialTie(friend1, 0.9);
        // friends���Ѻ��и�����
        assertEquals(res, true);
        assertEquals(0.9, centralUser.getSocialTie(friend1));
        // friends�в���������
        res = centralUser.addSocialTie(friend1, 0.99);
        assertEquals(res, false);
        assertEquals(0.99, centralUser.getSocialTie(friend1));
    }

    /**
     * testing strategy : friends����ĳ����+����ĳ����
     */
    @Test void testDeleteSocialTie() {
        Friend centralUser = new Friend();
        Friend friend1 = new Friend();
        Friend friend2 = new Friend();
        centralUser.addSocialTie(friend1, 0.9);
        centralUser.addSocialTie(friend2, 0.99);
        // friends���в��Ե�����
        boolean res = centralUser.deleteSocialTie(friend1);
        assertEquals(res, true);
        assertEquals(0, centralUser.getSocialTie(friend1));
        // friends�������Ե�����
        res = centralUser.deleteSocialTie(friend1);
        assertEquals(res, false);
        assertEquals(0, centralUser.getSocialTie(friend1));
    }

    /**
     * testing strategy : friends�в���������+���и�����
     */
    @Test void testGetSocialTie() {
        Friend centralUser = new Friend();
        Friend friend1 = new Friend();
        Friend friend2 = new Friend();
        centralUser.addSocialTie(friend1, 0.9);
        // friends�к�������
        assertEquals(0.9, centralUser.getSocialTie(friend1));
        // �����и�����
        assertEquals(0, centralUser.getSocialTie(friend2));
    }

    /**
     * testing strategy : �������ԣ�friends���ж�����Ұ�˳��
     */
    @Test void testGetAllFriends() {
        Friend centralUser = new Friend();
        Friend friend1 = new Friend();
        Friend friend2 = new Friend();
        centralUser.addSocialTie(friend1, 0.9);
        centralUser.addSocialTie(friend2, 0.99);
        List<Friend> list = new ArrayList<>();
        list.add(friend1);
        list.add(friend2);
        assertEquals(list, centralUser.getAllFriends());
    }

}
