package circularorbit;

import applications.App;
import iostrategy.IoStrategy;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import myexception.CentralObjectException;
import myexception.DependencyException;
import myexception.FileChooseException;
import myexception.SystemLegalException;
import physicalobject.Friend;
import track.Track;

public class SocialNetworkCircle extends ConcreteCircularOrbit<Friend, Friend> {

  private List<Friend> allFriends = new ArrayList<>();// �������е�����
  private Map<String, Friend> nameToFriend = new HashMap<>();// �������е�������Ӧ������
  private static final Logger logger =
      Logger.getLogger(App.class.getSimpleName());
  private File readFile = null;
  private String writeFilePath = null;

  @Override public void initSystem() {
    super.initSystem();
    allFriends.clear();
    nameToFriend.clear();
  }

  /**
   * clone ����ʵ��.
   */
  @Override public Object clone() throws CloneNotSupportedException {
    SocialNetworkCircle socialNetworkCircle =
        (SocialNetworkCircle) super.clone();
    socialNetworkCircle.allFriends = new ArrayList<>(this.allFriends);
    socialNetworkCircle.nameToFriend = new HashMap<>(this.nameToFriend);
    socialNetworkCircle.readFile = null;
    socialNetworkCircle.writeFilePath = null;
    return allFriends;

  }

  /**
   * set the file to read.
   * 
   * @param file read file
   */
  public void setReadFile(File file) {
    this.readFile = file;
  }

  /**
   * set the write file path.
   * 
   * @param writeFilePath write file path.
   */
  public void setWriteFilePath(String writeFilePath) {
    this.writeFilePath = writeFilePath;
  }

  /**
   * write file strategy.
   * 
   * @param  ioStrategy  strategy.
   * @throws IOException IO�쳣
   */
  public void saveSystemInfoInFile(IoStrategy ioStrategy) throws IOException {
    ioStrategy.saveSystemInfoInFile(this, writeFilePath);
  }

  /**
   * read file.
   * 
   * @param  ioStrategy          ���ļ��Ĳ���
   * @throws IOException         IO�쳣
   * @throws FileChooseException �ļ�ѡȡ�쳣
   */
  public void readFileAndCreateSystem(IoStrategy ioStrategy)
      throws IOException, FileChooseException {
    this.initSystem();
    ioStrategy.readFileAndCreateSystem(this, readFile);
  }

  /**
   * ����ʾ������.
   * 
   * @throws FileChooseException �ļ�ѡȡ�쳣
   */
  public void checkRep() throws FileChooseException {
    try {
      // �ж������û��Ƿ����
      Friend centralUser = getCentralPoint();
      if (centralUser == null) {
        throw new CentralObjectException("ȱ�������û�");
      }
      // �жϹ���ϵ������Ƿ�������û��ľ�������̾���
      Map<Friend, Boolean> visited = new HashMap<>();
      for (Friend friend : allFriends) {
        visited.put(friend, false);
      }
      visited.put(centralUser, true);
      Queue<Friend> queue = new LinkedBlockingQueue<>();
      queue.add(centralUser);
      Map<Friend, Integer> distance = new HashMap<>();
      distance.put(centralUser, 0);
      while (!queue.isEmpty()) {
        Friend head = queue.poll();
        Friend friend = head.getFriend(1);
        int i = 0;
        int size = head.getAllFriends().size();
        while (friend != null) {
          if (!visited.get(friend)) {
            int dis = distance.get(head) + 1;
            visited.put(friend, true);
            distance.put(friend, dis);
            if (friend.getTrackRadius() != dis) {
              throw new SystemLegalException("����������������������������̾��벻����" + friend
                  + "Ӧ��Ϊ��" + dis + "ʵ��Ϊ��" + friend.getTrackRadius());
            }
            queue.add(friend);
          } else if (++i < size) {
            friend = head.getAllFriends().get(i);
          } else {
            break;
          }
        }
      }
      // �ж��Ƿ��������δ����ͱ����ڹ������ܶȹ�ϵ��ͬʱ�ж�����������Ƿ��ǹ���뾶Ϊ-1
      for (Friend friend : allFriends) {
        if (!visited.get(friend) && friend.getTrackRadius() != -1) {
          throw new SystemLegalException("����������������������������̾��벻����" + friend
              + "Ӧ��Ϊ��" + (-1) + "ʵ��Ϊ��" + friend.getTrackRadius());
        }
        if (friend.getSex().equals("")) {
          throw new DependencyException("δ����������Ѽ�������˹�ϵ");
        }
      }
    } catch (CentralObjectException e) {
      throw new FileChooseException("�����û�������Ҫ����ѡ���ļ���" + e.getMessage());
    } catch (SystemLegalException e) {
      System.out.println("ϵͳ�˳���ϵͳ��������" + e.getMessage());
    } catch (DependencyException e) {
      throw new FileChooseException("������ϵ������Ҫ����ѡ���ļ���" + e.getMessage());
    }
  }

  /**
   * �������.
   * 
   * @param  friend ����ӵ�����
   * @return        ��������Ƿ�ɹ�����ֵ
   */
  public boolean addFriend(Friend friend) {
    assert friend != null : logIn("��������null");
    Boolean result = allFriends.contains(friend);
    allFriends.add(friend);
    nameToFriend.put(friend.getFriendName(), friend);
    return result;
  }

  /**
   * ��������û�.
   * 
   * @param  centralUser �����û�
   * @return             ��������Ƿ�ɹ�����ֵ
   */
  public boolean addCentralUser(Friend centralUser) {
    if (centralUser == null) {
      return false;
    }
    allFriends.add(centralUser);
    nameToFriend.put(centralUser.getFriendName(), centralUser);
    this.addCentralPoint(centralUser);
    assert getCentralPoint() != null : logIn("�����û�Ϊnull");
    return true;

  }

  /**
   * get the distance of the centralUser and the friend.
   * 
   * @param  friend1 one friend
   * @param  friend2 another friend
   * @return         the distance
   */
  public int getDistance(Friend friend1, Friend friend2) {
    assert friend1 != null && friend2 != null : logIn("��������null");
    assert (allFriends.contains(friend1))
        && (allFriends.contains(friend2)) : logIn("�������󣺲�������ϵͳ��");
    Map<Friend, Boolean> visited = new HashMap<>();// �����ж��Ƿ񱻷���
    for (Friend temp : allFriends) { // ������person���Ϊδ������
      visited.put(temp, false);
    }
    visited.put(friend1, true);
    Queue<Friend> queue = new LinkedBlockingQueue<>();// �ȹ�Ҫ�ö�������
    queue.add(friend1);// person1���
    Map<Friend, Integer> distance = new HashMap<>();// ���ڼ�¼����
    distance.put(friend1, 0);
    if (friend1.equals(friend2)) {
      return 0;
    }
    while (!queue.isEmpty()) { // ѭ��ֱ������Ϊ��
      Friend head = queue.poll();// �õ�����Ԫ�أ����������
      Friend tempt = head.getFriend(1);// �õ������йصĵ�һ����
      int i = 0;
      int size = head.getAllFriends().size();
      while (tempt != null) { // ѭ��ֱ��������head��ֱ�ӹ�ϵ
        if (!visited.get(tempt)) { // ��temptδ������
          if (tempt.equals(friend2)) { // ���ҵ�friend
            return distance.get(head) + 1;
          } else { // ��δ�ҵ�
            visited.put(tempt, true);
            distance.put(tempt, distance.get(head) + 1);
            queue.add(tempt);// ����ǰfriend���
          }
        }
        if (++i < size) { // ����Ѱ�������й�ϵ����
          tempt = head.getAllFriends().get(i);
        } else {
          break;
        }
      }
    }
    return -1;
  }

  /**
   * get all the distance in the socialNetwork circle.
   */
  public void getAllDistance() {
    Friend centralUser = getCentralPoint();
    Map<Friend, Boolean> visited = new HashMap<>();// �����ж��Ƿ񱻷���
    for (Friend friend : allFriends) { // ������person���Ϊδ������
      visited.put(friend, false);
    }
    visited.put(centralUser, true);
    Queue<Friend> queue = new LinkedBlockingQueue<>();// �ȹ�Ҫ�ö�������
    queue.add(centralUser);
    Map<Friend, Integer> distance = new HashMap<>();// ���ڼ�¼��̾���
    distance.put(centralUser, 0);
    while (!queue.isEmpty()) { // ѭ��ֱ������Ϊ��
      Friend head = queue.poll();// �õ�����Ԫ�أ����������
      Friend friend = head.getFriend(1);// �õ������йصĵ�һ����
      int i = 0;
      int size = head.getAllFriends().size();
      while (friend != null) { // ѭ��ֱ��������head��ֱ�ӹ�ϵ
        if (!visited.get(friend)) { // ��temptδ������
          int dis = distance.get(head) + 1;
          visited.put(friend, true);
          distance.put(friend, dis);
          friend.setTrackRadius(dis);
          this.addFriendOnTrack(friend);
          queue.add(friend);// ����ǰfriend���
        } else if (++i < size) { // ����Ѱ�������й�ϵ����
          friend = head.getAllFriends().get(i);
        } else {
          break;
        }
      }
    }
  }

  /**
   * ͨ�������õ�friend.
   * 
   * @param  name ����
   * @return      ����friend
   */
  public Friend getFriendByName(String name) {
    return nameToFriend.get(name);
  }

  /**
   * ��������ӵ������.
   * 
   * @param  friend ����
   * @return        �����Ƿ���ӳɹ�����ֵ
   */
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
   * get the friend's track number.
   * 
   * @param  friend the centralUser's friend
   * @return        the number on which track the friend is
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
   * get logical distance between friend1 and friend2.
   * 
   * @param  friend1 one friend
   * @param  friend2 another friend
   * @return         the logical distance
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
   * calculate the "information diffusion" of a friend in the first orbit.
   * 
   * @return the number of new friends who can you meet indirectly through this
   *         friend
   */
  public int informationdiffusivity(Friend friend) {
    assert friend != null : logIn("��������null");
    if (friend.getTrackRadius() != 1) {
      System.out.println("�����Ѳ��������û������ѣ�");
      return -1;
    }
    int result = 0;
    for (Friend friend2 : friend.getAllFriends()) {
      // �������û����ѵ����Ѳ��������û������Ѷ��ҹ�ϵ���ܶȳ˻�����0.25����˵���п�����ʶ
      if (!getCentralPoint().getAllFriends().contains(friend2)
          && getCentralPoint().getSocialTie(friend)
              * friend.getSocialTie(friend2) > 0.25) {
        result++;
      }
    }
    return result;
  }

  /**
   * add a relation between friend1 and friend2, and recreate the system.
   * 
   * @param friend1  one friend
   * @param friend2  another friend
   * @param intimacy the intimacy between friend1 and friend2
   */
  public boolean addRelationAndRefactor(Friend friend1, Friend friend2,
      double intimacy) {
    if (friend1 == null || friend2 == null) {
      return false;
    }
    if (friend1.getSocialTie(friend2) != 0) {
      return false;
    }
    Pattern pattern =
        Pattern.compile("([0][.][0-9]{0,2}[0-9])|([1]([.][0]{0,3})?)");
    Matcher matcher = pattern.matcher(String.valueOf(intimacy));
    if (!matcher.matches() || intimacy == 0) {
      return false;
    }
    friend1.addSocialTie(friend2, intimacy);
    friend2.addSocialTie(friend1, intimacy);
    Friend centralUser = getCentralPoint();
    for (Friend friend : allFriends) {
      int distance = getDistance(centralUser, friend);
      if (distance != friend.getTrackRadius()) { // ɾȥԭ����ϵ�����
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
    assert friend1.getSocialTie(friend2) == intimacy
        && friend2.getSocialTie(friend1) == intimacy : logIn("���ӹ�ϵʧ��");
    return true;
  }

  /**
   * delete a relation between friend1 and friend2, and recreate the system.
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
      if (distance != friend.getTrackRadius()) { // ɾȥԭ����ϵ�����
        Track<Friend> track = getTrackByRadius(friend.getTrackRadius());
        track.remove(friend);
        if (track.getNumberOfObjects() == 0) {
          this.deleteTrack(track);
        }
      }
      friend.setTrackRadius(distance);
      this.addFriendOnTrack(friend);
    }
    assert (friend1.getSocialTie(friend2) == 0)
        && (friend2.getSocialTie(friend1) == 0) : logIn("ɾ��ʧ��");
    return true;
  }

  /**
   * get the number of the list of allFriends.
   * 
   * @return the number of the list of allFriends
   */
  public int getFriendNum() {
    return allFriends.size();
  }

  /**
   * ��������ҵ�����.
   * 
   * @param  num ���
   * @return     ����ĳ������
   */
  public Friend getFriend(int num) {
    if (num <= 0 || num > getFriendNum()) {
      return null;
    }
    return allFriends.get(num - 1);
  }

  /**
   * remove friend from the list of allFriends.
   * 
   * @param  friend friend != null
   * @return        true if allFriends contain friend , else false
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
    nameToFriend.remove(friend.getFriendName());
    return true;
  }

  private static String logIn(String message) {
    logger.severe(message);
    return "�ѽ�assert������Ϣ��������־�ļ���";
  }

  public List<Friend> getAllFriends() {
    return allFriends;
  }

}
