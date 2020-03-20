package circularOrbit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import applications.App;
import myException.CentralObjectException;
import myException.DependencyException;
import myException.FileChooseException;
import myException.FileGrammerException;
import myException.LabelSameException;
import myException.SystemLegalException;
import physicalObject.Friend;
import track.Track;

public class SocialNetworkCircle extends ConcreteCircularOrbit<Friend, Friend> {

    private List<Friend> allFriends = new ArrayList<>();// �������е�����
    private static final Logger logger = Logger.getLogger(App.class.getSimpleName());

    public void readFileAndCreateSystem(File file) throws IOException, FileChooseException {
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
            BufferedReader bfReader = new BufferedReader(reader);
            Pattern pattern1 = Pattern.compile("CentralUser ::= <[ ]*(\\S+)[ ]*,[ ]*(\\S+)[ ]*,[ ]*(\\S+)[ ]*>");
            Pattern pattern2 = Pattern.compile("Friend ::= <[ ]*(\\S+)[ ]*,[ ]*(\\S+)[ ]*,[ ]*(\\S+)[ ]*>");
            Pattern pattern3 = Pattern.compile("SocialTie ::= <[ ]*(\\S+)[ ]*,[ ]*(\\S+)[ ]*,[ ]*(\\S+)[ ]*>");
            String lineString = new String();
            int count = 0;
            Matcher matcher;
            int lineCount = 0;
            while ((lineString = bfReader.readLine()) != null) {
                lineCount++;// �м����Լ�һ
                if ((matcher = pattern1.matcher(lineString)).find()) {
                    count++;
                    if (!Pattern.matches("[A-Za-z0-9]+", matcher.group(1))) {
                        bfReader.close();
                        throw new FileGrammerException("�����û����Ƿ���������" + lineCount, 30);
                    }
                    if (!Pattern.matches("\\d+", matcher.group(2))) {
                        bfReader.close();
                        throw new FileGrammerException("����Ƿ���������" + lineCount, 30);
                    }
                    if (!Pattern.matches("M|F", matcher.group(3))) {
                        bfReader.close();
                        throw new FileGrammerException("�Ա���ض������ַ���������" + lineCount, 30);
                    }
                    Friend centralUser = new Friend();
                    centralUser.setAge(Integer.parseInt(matcher.group(2)));
                    centralUser.setFriendName(matcher.group(1));
                    centralUser.setSex(matcher.group(3));
                    centralUser.setTrackRadius(0);
                    addCentralPoint(centralUser);
                    addFriend(centralUser);
                } else if ((matcher = pattern2.matcher(lineString)).find()) {
                    if (!Pattern.matches("[A-Za-z0-9]+", matcher.group(1))) {
                        bfReader.close();
                        throw new FileGrammerException("���������Ƿ���������" + lineCount, 30);
                    }
                    if (!Pattern.matches("\\d+", matcher.group(2))) {
                        bfReader.close();
                        throw new FileGrammerException("����Ƿ���������" + lineCount, 30);
                    }
                    if (!Pattern.matches("M|F", matcher.group(3))) {
                        bfReader.close();
                        throw new FileGrammerException("�Ա���ض������ַ���������" + lineCount, 30);
                    }
                    Friend friend = getFriendByName(matcher.group(1));
                    if (friend == null) {
                        friend = new Friend();
                        friend.setFriendName(matcher.group(1));
                        friend.setAge(Integer.parseInt(matcher.group(2)));
                        friend.setSex(matcher.group(3));
                        addFriend(friend);
                    } else {
                        if (!friend.getSex().equals("")) {
                            bfReader.close();
                            throw new LabelSameException("�����������Ѵ��ڣ�������" + lineCount);
                        } else {
                            friend.setAge(Integer.parseInt(matcher.group(2)));
                            friend.setSex(matcher.group(3));
                        }
                    }
                } else if ((matcher = pattern3.matcher(lineString)).find()) {
                    String f1String = matcher.group(1);
                    String f2String = matcher.group(2);
                    if (!Pattern.matches("[A-Za-z0-9]+", f1String)) {
                        bfReader.close();
                        throw new FileGrammerException("���������Ƿ���������" + lineCount, 30);
                    }
                    if (!Pattern.matches("[A-Za-z0-9]+", f2String)) {
                        bfReader.close();
                        throw new FileGrammerException("���������Ƿ���������" + lineCount, 30);
                    }
                    if (!Pattern.matches("([0][.][0-9]{0,2}[1-9])|([1]([.][0]{0,3})?)", matcher.group(3))) {
                        bfReader.close();
                        throw new FileGrammerException("���ܶȲ�������������" + lineCount, 30);
                    }
                    if (f1String.equals(f2String)) {
                        bfReader.close();
                        throw new LabelSameException("����ӹ�ϵ������Ϊͬһ���ˣ�������" + lineCount);
                    }
                    double intimacy = Double.parseDouble(matcher.group(3));
                    Friend friend1 = getFriendByName(f1String);
                    Friend friend2 = getFriendByName(f2String);
                    if (friend1 == null) {
                        friend1 = new Friend();
                        friend1.setFriendName(f1String);
                        addFriend(friend1);
                    }
                    if (friend2 == null) {
                        friend2 = new Friend();
                        friend2.setFriendName(f2String);
                        addFriend(friend2);
                    }
                    if (friend1.getSocialTie(friend2) != 0 && friend1.getSocialTie(friend2) != intimacy) {
                        bfReader.close();
                        throw new LabelSameException("����ӵ������Ѵ������ܶ�����˴����ܶȲ�ͬ��������" + lineCount);
                    }
                    friend1.addSocialTie(friend2, intimacy);
                    friend2.addSocialTie(friend1, intimacy);
                } else {
                    if (!lineString.equals("")) {
                        bfReader.close();
                        throw new FileGrammerException("������Ϣ�У�������" + lineCount, 31);
                    }
                }
            }
            if (count != 1) {
                bfReader.close();
                throw new CentralObjectException("�����û�ȱʧ����࣬������" + lineCount);
            }
            Friend centralUser = getCentralPoint();
            for (Friend friend : allFriends) {
                friend.setTrackRadius(getDistance(centralUser, friend));
                this.addFriendOnTrack(friend);// ��ӵ�ͬʱ�½����
            }
            bfReader.close();
            sortTrack();
            checkRep();
        } catch (FileGrammerException e) {
            throw new FileChooseException("�ļ��﷨����������Ҫ����ѡ���ļ���" + e.getMessage());
        } catch (LabelSameException e) {
            throw new FileChooseException("���ڱ�����ͬԪ�أ���Ҫ����ѡ���ļ���" + e.getMessage());
        } catch (CentralObjectException e) {
            throw new FileChooseException("�����û�������Ҫ����ѡ���ļ���" + e.getMessage());
        }

    }

    public void checkRep() throws FileChooseException {
        try {
            // �ж������û��Ƿ����
            if (getCentralPoint() == null) {
                throw new CentralObjectException("ȱ�������û�");
            }
            Friend centralUser = getCentralPoint();
            // �ж�������������Ƿ���ȷ���ж��Ƿ��������δ����ͱ����ڹ������ܶȹ�ϵ
            for (Friend friend : allFriends) {
                if (getDistance(centralUser, friend) != friend.getTrackRadius()) {
                    throw new SystemLegalException("�������Υ���������������ڹ����ʵ�ʲ���");
                }
                if (friend.getSex().equals("")) {
                    throw new DependencyException("δ����������Ѽ�������˹�ϵ");
                }
            }
        } catch (CentralObjectException e) {
            throw new FileChooseException("�����û�������Ҫ����ѡ���ļ���" + e.getMessage());
        } catch (SystemLegalException e) {
            System.out.println("ϵͳ�˳���ϵͳ��������");
            System.exit(0);
        } catch (DependencyException e) {
            throw new FileChooseException("������ϵ������Ҫ����ѡ���ļ���" + e.getMessage());
        }
    }

    public boolean addFriend(Friend friend) {
        assert friend != null : logIn("��������null");
        Boolean result = allFriends.contains(friend);
        allFriends.add(friend);
        return result;
    }

    public boolean addCentralUser(Friend centralUser) {
        if (centralUser == null) {
            return false;
        }
        allFriends.add(centralUser);
        this.addCentralPoint(centralUser);
        assert getCentralPoint() != null : logIn("�����û�Ϊnull");
        return true;

    }

    /**
     * get the distance of the centralUser and the friend
     * 
     * @param friend1 one friend
     * @param friend2 another friend
     * @return the distance
     */
    public int getDistance(Friend friend1, Friend friend2) {
        assert friend1 != null && friend2 != null : logIn("��������null");
        assert allFriends.contains(friend1) && allFriends.contains(friend2) : logIn("�������󣺲�������ϵͳ��");
        Map<Friend, Boolean> visited = new HashMap<>();// �����ж��Ƿ񱻷���
        Map<Friend, Integer> distance = new HashMap<>();// ���ڼ�¼����
        for (Friend temp : allFriends) {// ������person���Ϊδ������
            visited.put(temp, false);
        }
        visited.put(friend1, true);
        Queue<Friend> queue = new LinkedBlockingQueue<>();// �ȹ�Ҫ�ö�������
        queue.add(friend1);// person1���
        distance.put(friend1, 0);
        if (friend1.equals(friend2)) {
            return 0;
        }
        while (!queue.isEmpty()) {// ѭ��ֱ������Ϊ��
            Friend head = queue.poll();// �õ�����Ԫ�أ����������
            Friend tempt = head.getAllFriends().peek();// �õ������йصĵ�һ����
            int i = 0;
            while (tempt != null) {// ѭ��ֱ��������head��ֱ�ӹ�ϵ
                if (!visited.get(tempt)) {// ��temptδ������
                    if (tempt.equals(friend2)) {// ���ҵ�friend
                        return distance.get(head) + 1;
                    } else {// ��δ�ҵ�
                        visited.put(tempt, true);
                        distance.put(tempt, distance.get(head) + 1);
                        queue.add(tempt);// ����ǰfriend���
                    }
                }
                if (++i < head.getAllFriends().size()) {// ����Ѱ�������й�ϵ����
                    tempt = head.getAllFriends().get(i);
                } else {
                    break;
                }
            }
        }
        return -1;
    }

    public Friend getFriendByName(String name) {
        for (Friend friend : allFriends) {
            if (friend.getFriendName().equals(name)) {
                return friend;
            }
        }
        return null;
    }

    public boolean addFriendOnTrack(Friend friend) {
        assert friend != null : logIn("��������null");
        if (friend.getTrackRadius() == -1 || friend.equals(getCentralPoint())) {
            return false;
        }
        Track<Friend> track = getTrackByRadius(friend.getTrackRadius());
        if (track == null) {
            track = new Track<Friend>(friend.getTrackRadius());
            track.add(friend);
            addTrack(track);
            return true;
        }
        return track.add(friend);
    }

    /**
     * get the friend's track number
     * 
     * @param friend the centralUser's friend
     * @return the number on which track the friend is
     */
    public int getFriendTrackNum(Friend friend) {
        assert friend != null : logIn("��������null");
        int result = (int) friend.getTrackRadius();
        if (result == -1) {
            System.out.println("He/She is not the friend of the centralUser!");
        } else if (result == 0) {
            System.out.println("He/She is the centralUser!");
        }
        return result;
    }

    /**
     * get logical distance between friend1 and friend2
     * 
     * @param friend1 one friend
     * @param friend2 another friend
     * @return the logical distance
     */
    public int getLogicalDistance(Friend friend1, Friend friend2) {
        assert friend1 != null && friend2 != null : logIn("��������null");
        int result = getDistance(friend1, friend2);
        if (result == 0) {
            System.out.println("����Ϊͬһ�ˣ�");
        } else if (result == -1) {
            System.out.println("����֮�����κι�ϵ��");
        }
        return result;
    }

    /**
     * calculate the "information diffusion" of a friend in the first orbit
     * 
     * @return the number of new friends who can you meet indirectly through this
     *         friend
     */
    public int Informationdiffusivity(Friend friend) {
        assert friend != null : logIn("��������null");
        if (friend.getTrackRadius() != 1) {
            System.out.println("�����Ѳ��������û������ѣ�");
            return -1;
        }
        int result = 0;
        for (Friend friend2 : friend.getAllFriends()) {
            if (!getCentralPoint().getAllFriends().contains(friend2)// �������û����ѵ����Ѳ��������û������Ѷ��ҹ�ϵ���ܶȳ˻�����0.25����˵���п�����ʶ
                    && getCentralPoint().getSocialTie(friend) * friend.getSocialTie(friend2) > 0.25) {
                result++;
            }
        }
        return result;
    }

    /**
     * add a relation between friend1 and friend2, and recreate the system
     * 
     * @param friend1  one friend
     * @param friend2  another friend
     * @param intimacy the intimacy between friend1 and friend2
     */
    public boolean addRelationAndRefactor(Friend friend1, Friend friend2, double intimacy) {
        if (friend1 == null || friend2 == null) {
            return false;
        }
        if (friend1.getSocialTie(friend2) != 0) {
            return false;
        }
        Pattern pattern = Pattern.compile("([0][.][0-9]{0,2}[0-9])|([1]([.][0]{0,3})?)");
        Matcher matcher = pattern.matcher(String.valueOf(intimacy));
        if (!matcher.matches() || intimacy == 0) {
            return false;
        }
        friend1.addSocialTie(friend2, intimacy);
        friend2.addSocialTie(friend1, intimacy);
        Friend centralUser = getCentralPoint();
        for (Friend friend : allFriends) {
            int distance = getDistance(centralUser, friend);
            if (distance != friend.getTrackRadius()) {// ɾȥԭ����ϵ�����
                Track<Friend> track = getTrackByRadius(friend.getTrackRadius());
                if (track != null) {
                    track.remove(friend);
                    if (track.getNumberOfObjects() == 0) {
                        this.deleteTrack(track);
                    }
                }
            }
            friend.setTrackRadius(distance);
            this.addFriendOnTrack(friend);
        }
        assert friend1.getSocialTie(friend2) == intimacy && friend2.getSocialTie(friend1) == intimacy : logIn("���ӹ�ϵʧ��");
        return true;
    }

    /**
     * delete a relation between friend1 and friend2, and recreate the system
     * 
     * @param friend1 one friend
     * @param friend2 another friend
     */
    public boolean deleteRelationAndRefactor(Friend friend1, Friend friend2) {
        if (friend1 == null || friend2 == null) {
            return false;
        }
        if (friend1.getSocialTie(friend2) == 0) {
            return false;
        }
        friend1.deleteSocialTie(friend2);
        friend2.deleteSocialTie(friend1);
        Friend centralUser = getCentralPoint();
        for (Friend friend : allFriends) {
            int distance = getDistance(centralUser, friend);
            if (distance != friend.getTrackRadius()) {// ɾȥԭ����ϵ�����
                Track<Friend> track = getTrackByRadius(friend.getTrackRadius());
                track.remove(friend);
                if (track.getNumberOfObjects() == 0) {
                    this.deleteTrack(track);
                }
            }
            friend.setTrackRadius(distance);
            this.addFriendOnTrack(friend);
        }
        assert friend1.getSocialTie(friend2) == 0 && friend2.getSocialTie(friend1) == 0 : logIn("ɾ��ʧ��");
        return true;
    }

    /**
     * get the number of the list of allFriends
     * 
     * @return the number of the list of allFriends
     */
    public int getFriendNum() {
        return allFriends.size();
    }

    public Friend getFriend(int num) {
        if (num <= 0 || num > getFriendNum()) {
            return null;
        }
        return allFriends.get(num - 1);
    }

    /**
     * 
     * @param friend friend != null
     * @return true if allFriends contain friend , else false
     */
    public boolean removeFriend(Friend friend) {
        assert friend != null : logIn("��������null");
        if (!allFriends.contains(friend)) {
            return false;
        }
        for (Friend friend2 : allFriends) {
            deleteRelationAndRefactor(friend2, friend);
        }
        allFriends.remove(friend);
        return true;
    }

    private static String logIn(String message) {
        logger.severe(message);
        return "�ѽ�assert������Ϣ��������־�ļ���";
    }

}
