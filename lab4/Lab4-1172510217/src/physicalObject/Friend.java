package physicalObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Logger;

import applications.App;

public class Friend extends PhysicalObject {

    private static final Logger logger = Logger.getLogger(App.class.getSimpleName());

    private String friendName = new String();
    private int age = -1;
    private String sex = new String();
    private Map<Friend, Double> socialTieMap = new HashMap<>();
    private LinkedList<Friend> allFriends = new LinkedList<>();

    /**
     * @return the friendName
     */
    public String getFriendName() {
        assert friendName!=null && !friendName.equals("") : logIn("�����������ֲ�ӦΪnull��մ�");
        return friendName;
    }

    /**
     * precondition : friendName != null && friendName != "";
     * 
     * @param friendName the friendName to set
     */
    public void setFriendName(String friendName) {
        assert friendName != null && !friendName.equals("") : logIn("�����������ֲ�ӦΪnull��մ�");
        this.friendName = friendName;
    }

    /**
     * postcondition:age >= 0
     * 
     * @return the age
     */
    public int getAge() {
        assert age >= 0 : logIn("������������Ӧ�÷Ǹ�");
        return age;
    }

    /**
     * precondition:age >= 0
     * 
     * @param age the age to set
     */
    public void setAge(int age) throws AssertionError{
        try {
            assert age >= 0;
        } catch (AssertionError e) {
            logIn("������������Ӧ�÷Ǹ�");
            throw new AssertionError();
        }
        this.age = age;
    }

    /**
     * postcondition: sex should be "F" or "M";
     * 
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * precondition: sex should be "F" or "M";
     * 
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        assert sex.equals("F") || sex.equals("M") : logIn("��������sexӦΪ�ض����ַ���");
        this.sex = sex;
    }

    /**
     * add a socialTie between him and his friend
     * 
     * @param friend   his friend
     * @param intimacy socialTie
     * @return true if the friend is not in the map; else false
     */
    public boolean addSocialTie(Friend friend, double intimacy) {
        boolean result = socialTieMap.containsKey(friend);
        socialTieMap.put(friend, intimacy);
        allFriends.add(friend);
        return !result;
    }

    /**
     * delete a socialTie between him and his friend
     * 
     * @param friend his friend
     * @return true if the socialTie has been added; else false
     */
    public boolean deleteSocialTie(Friend friend) {
        Boolean result = socialTieMap.containsKey(friend);
        socialTieMap.remove(friend);
        allFriends.remove(friend);
        return result;
    }

    /**
     * get the socialTie between him and the friend
     * 
     * @param friend his friend
     * @return the socialTie
     */
    public double getSocialTie(Friend friend) {
        if (socialTieMap.containsKey(friend)) {
            return socialTieMap.get(friend);
        }
        return 0;
    }

    public LinkedList<Friend> getAllFriends() {
        return this.allFriends;
    }

    @Override public String toString() {
        if (getSex().equals("M")) {
            return "[name=" + friendName + ", age=" + getAge() + " sex=��" + "]";
        } else if (getSex().equals("F")) {
            return "[name=" + friendName + ", age=" + getAge() + " sex=Ů" + "]";
        } else {
            return "�Ա�����������";
        }
    }

    private static String logIn(String message) {
        logger.severe(message);
        return "�ѽ�assert������Ϣ��������־�ļ���";
    }
}
