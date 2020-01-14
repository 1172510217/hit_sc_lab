package circularorbit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import track.Track;

public class ConcreteCircularOrbit<L, E> implements CircularOrbit<L, E> {

  private Map<Track<E>, Integer> allTracks = new HashMap<>();// �������еĹ������Ӧ�Ĺ�����
  private Map<Double, Track<E>> allRadius = new HashMap<>();// �������еİ뾶����Ӧ�Ĺ��
  private Map<Integer, Track<E>> allOrders = new HashMap<>();// �������е���źͶ�Ӧ�Ĺ��
  private L centralPoint = null;// central point of the system

  /**
   * clone ����ʵ��.
   */
  @SuppressWarnings("unchecked")
  @Override public Object clone()
      throws CloneNotSupportedException {
    ConcreteCircularOrbit<L, E> circularOrbit =
        (ConcreteCircularOrbit<L, E>) super.clone();
    circularOrbit.allTracks = new HashMap<>(this.allTracks);
    circularOrbit.allRadius = new HashMap<>(this.allRadius);
    circularOrbit.allOrders = new HashMap<>(this.allOrders);
    return circularOrbit;
  }

  public Iterator<E> iterator() {
    return new PhysicalObjectItertor();
  }

  /**
   * ��ʼ��ϵͳ.
   */
  public void initSystem() {
    allTracks.clear();
    allRadius.clear();
    allOrders.clear();
    this.centralPoint = null;
  }

  /**
   * add a track in the circularOrbit system, return true if the track has not
   * been in the list of allTracks; else return false.
   */
  public boolean addTrack(Track<E> track) {
    if (allTracks.containsKey(track)) {
      return false;
    }
    allRadius.put(track.getRadius(), track);
    allTracks.put(track, 0);
    return true;
  }

  /**
   * delete a track from the list of the allTracks. Return true if the track is
   * in
   * the list; else return false
   */
  public boolean deleteTrack(Track<E> track) {
    if (allTracks.containsKey(track)) {
      allTracks.remove(track);
      allRadius.remove(track.getRadius());
      return true;
    }
    return false;
  }

  /**
   * add a central point in the system. Return true if a central point is in the
   * system; else return false
   */
  public boolean addCentralPoint(L l) {
    if (centralPoint != null) {
      return false;
    }
    centralPoint = l;
    return true;
  }

  /**
   * add an object on a track
   * return true if the object e has been in the track; else return false.
   */
  public boolean addTrackObject(Track<E> track, E e) {
    return track.add(e);
  }

  /**
   * ͨ���뾶�õ����.
   * 
   * @param  radius �뾶
   * @return        ���ض�Ӧ�뾶�Ĺ��
   */
  public Track<E> getTrackByRadius(double radius) {
    return allRadius.get(radius);
  }

  public L getCentralPoint() {
    return centralPoint;
  }

  @Override public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder
        .append("The centralPoint and all the tracks are as follows\n");
    stringBuilder.append("centralpoint : " + centralPoint + "\n");
    for (Track<E> track : allTracks.keySet()) {
      stringBuilder.append(track + "\n");
    }
    return stringBuilder.toString();
  }

  /**
   * �Թ��������������.
   */
  public void sortTrack() {
    List<Track<E>> tracks = new ArrayList<>();
    tracks.addAll(allTracks.keySet());
    Collections.sort(tracks, new Comparator<Track<E>>() {

      @Override public int compare(Track<E> track1, Track<E> track2) {
        return Double.compare(track1.getRadius(), track2.getRadius());
      }
    });
    for (int i = 0; i < tracks.size(); i++) {
      allOrders.put(i + 1, tracks.get(i));
      allTracks.put(tracks.get(i), i + 1);
    }
  }

  /**
   * ����ϵͳ����ֵ.
   */
  public double getSystemEntropy() {
    if (allTracks.size() == 0) {
      return 0;
    }
    double minRadius = allOrders.get(1).getRadius();
    double maxRadius = allOrders.get(allTracks.size()).getRadius();
    if (minRadius == maxRadius) {
      return 0;
    }
    List<Double> list = new ArrayList<>();
    for (Track<E> track : allTracks.keySet()) {
      list.add((track.getRadius() - minRadius) / (maxRadius - minRadius) + 1);
    }
    double sum = 0;// ����������
    int sumOfObjects = 0;
    for (int i = 0; i < list.size(); i++) {
      sum += list.get(i) * allOrders.get(i + 1).getNumberOfObjects();
      sumOfObjects += allOrders.get(i + 1).getNumberOfObjects();
    } // �����forѭ�����������ı���
    for (int i = 0; i < list.size(); i++) {
      list.set(i, list.get(i) / sum);
    }
    double k = 1 / Math.log(sumOfObjects);// ����k
    double result = 0;// ���¼�����ֵ
    for (int i = 0; i < list.size(); i++) {
      result += -k * list.get(i) * Math.log(list.get(i));
    }
    return result;
  }

  public int getTracksNumber() {
    return allTracks.size();
  }

  /**
   * ͨ����ŵõ�����.
   * 
   * @param  num ���
   * @return     ��������
   */
  public E getEByNum(int num) {
    if (num <= 0) {
      return null;
    }
    for (int i = 0; i < getTracksNumber(); i++) {
      num -= getTrackObjectsNumber(i + 1);
      if (num <= 0) {
        return getTrack(i + 1)
            .getIndexPhysicalObject(num + getTrackObjectsNumber(i + 1));
      }
    }
    return null;

  }

  /**
   * ͨ������ŵõ�����ϵ��������.
   * 
   * @param  trackNumber ������
   * @return             ���ع���ϵ��������
   */
  public int getTrackObjectsNumber(int trackNumber) {
    if (trackNumber > allTracks.size() || trackNumber <= 0) {
      return -1;
    }
    return allOrders.get(trackNumber).getNumberOfObjects();
  }

  /**
   * ͨ������ŵõ���Ӧ�Ĺ��.
   */
  public Track<E> getTrack(int trackNumber) {
    return allOrders.get(trackNumber);
  }

  public class PhysicalObjectItertor implements Iterator<E> {

    private int countObject = 0;// ��ǰ�������
    private int numObject = 0;// ��������Ŀ

    /**
     * �õ�ϵͳ���������Ŀ.
     */
    public PhysicalObjectItertor() { // �õ�ϵͳ��������Ŀ
      for (int i = 0; i < getTracksNumber(); i++) {
        numObject += getTrackObjectsNumber(i + 1);
      }
    }

    public boolean hasNext() { // �ж��Ƿ�����һ������
      return countObject < numObject;
    }

    /**
     * ��������һԪ�أ��ͷ�����һԪ��.
     */
    public E next() {
      if (hasNext()) {
        return getEByNum(++countObject);// ͨ����ǰ�������ŵõ�������
      }
      throw new NoSuchElementException();
    }
  }

  public Set<Track<E>> getAlltracks() {
    return allTracks.keySet();
  }

}
