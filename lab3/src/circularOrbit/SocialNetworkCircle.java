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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import physicalObject.Friend;
import track.Track;

public class SocialNetworkCircle extends ConcreteCircularOrbit<Friend, Friend> {

    private List<Friend> allFriends = new ArrayList<>();// �������е�����

    public void readFileAndCreateSystem(File file) throws IOException {
        InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
        BufferedReader bfReader = new BufferedReader(reader);
        String string = "";
        List<String> lineList = new ArrayList<>();
        int lineNum = 0;
        while ((string = bfReader.readLine()) != null) {
            lineNum++;
            String[] strings = string.split("[:][:][=]");
            Pattern pattern1 = Pattern.compile("([A-Z]|[a-z]|[0-9])+");
            Pattern pattern3 = Pattern.compile("[,][ ]*(([0][.][0-9]{0,2}[0-9])|([1]([.][0]{0,3})?))");// ��ȡ�����ܶ��������ڵ�����
            Pattern pattern2 = Pattern.compile("([0][.][0-9]{0,2}[1-9])|([1]([.][0]{0,3})?)");// Ϊ��ȡ�����ܶ�����ͬʱ��֤����ȡ��
            Matcher matcher;
            if (strings[0].contains("CentralUser")) {
                matcher = pattern1.matcher(strings[1]);
                while (matcher.find()) {
                    lineList.add(matcher.group());
                }
                Friend centralUser = new Friend();
                centralUser.setAge(Integer.parseInt(lineList.get(1)));
                centralUser.setFriendName(lineList.get(0));
                centralUser.setSex(lineList.get(2));
                centralUser.setTrackRadius(0.0);
                addCentralUser(centralUser);// �������û�����ϵͳ
                lineList.removeAll(lineList);

            } else if (strings[0].contains("SocialTie")) {
                matcher = pattern1.matcher(strings[1]);
                while (matcher.find() && lineList.size() < 2) {
                    lineList.add(matcher.group());
                }
                matcher = pattern3.matcher(strings[1]);
                if (matcher.find()) {
                    matcher = pattern2.matcher(matcher.group());
                    if (matcher.find()) {
                        lineList.add(matcher.group());
                    }
                }
                if (getFriendByName(lineList.get(0)) == null) {
                    Friend friend1 = new Friend();
                    friend1.setFriendName(lineList.get(0));
                    this.addFriend(friend1);
                }
                if (getFriendByName(lineList.get(1)) == null) {
                    Friend friend2 = new Friend();
                    friend2.setFriendName(lineList.get(1));
                    this.addFriend(friend2);
                }
                if (lineList.size() < 3 || Double.parseDouble(lineList.get(2)) == 0) {
                    System.out.println("���и�ʽ�Ƿ���" + lineNum);
                    continue;
                }
                getFriendByName(lineList.get(0)).addSocialTie(getFriendByName(lineList.get(1)),
                        Double.parseDouble(lineList.get(2)));
                getFriendByName(lineList.get(1)).addSocialTie(getFriendByName(lineList.get(0)),
                        Double.parseDouble(lineList.get(2)));
                lineList.removeAll(lineList);
            } else if (strings[0].contains("Friend")) {
                matcher = pattern1.matcher(strings[1]);
                while (matcher.find()) {
                    lineList.add(matcher.group());
                }
                Friend friend = getFriendByName(lineList.get(0));
                if (friend == null) {
                    friend = new Friend();
                    friend.setFriendName(lineList.get(0));
                    this.addFriend(friend);
                }
                friend.setAge(Integer.parseInt(lineList.get(1)));
                friend.setSex(lineList.get(2));
                lineList.removeAll(lineList);
            } else {
                System.out.println("���и�ʽ�Ƿ���" + lineNum);
            }
        }
        bfReader.close();
        Friend centralUser = getCentralPoint();
        for (Friend friend : allFriends) {
            friend.setTrackRadius(getDistance(centralUser, friend));
            this.addFriendOnTrack(friend);// ��ӵ�ͬʱ�½����
        }
        sortTrack();
    }

    public boolean addFriend(Friend friend) {
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
    public void addRelationAndRefactor(Friend friend1, Friend friend2, double intimacy) {
        if (friend1 == null || friend2 == null) {
            System.out.println("friend����Ƿ���Ϊ������");
            return;
        }
        if (friend1.getSocialTie(friend2) != 0) {
            System.out.println("friend1��friend2���������ѹ�ϵ��");
            return;
        }
        Pattern pattern = Pattern.compile("([0][.][0-9]{0,2}[0-9])|([1]([.][0]{0,3})?)");
        Matcher matcher = pattern.matcher(String.valueOf(intimacy));
        if (!matcher.matches() || intimacy == 0) {
            System.out.println("���ܶȲ����Ƿ���");
            return;
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

    public boolean removeFriend(Friend friend) {
        if (!allFriends.contains(friend)) {
            return false;
        }
        for (Friend friend2 : allFriends) {
            deleteRelationAndRefactor(friend2, friend);
        }
        allFriends.remove(friend);
        return true;
    }

}
