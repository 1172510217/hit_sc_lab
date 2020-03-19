package difference;

import circularorbit.CircularOrbit;
import java.util.ArrayList;
import java.util.List;

public class Difference<L, E> {

  int trackNumbersDifference = 0;// ��ʾ�����Ŀ����
  List<Integer> objectDifferences = new ArrayList<>();// �������й���ϵ�������Ŀ����

  /**
   * get the difference of two systems.
   * 
   * @param c1 one system
   * @param c2 the other system
   */
  public Difference(CircularOrbit<L, E> c1, CircularOrbit<L, E> c2) {
    this.trackNumbersDifference = c1.getTracksNumber() - c2.getTracksNumber();
    System.out.println("��������죺" + trackNumbersDifference);
    int smaller = trackNumbersDifference > 0 ? c2.getTracksNumber()
        : c1.getTracksNumber();
    int bigger = smaller + trackNumbersDifference;
    for (int i = 0; i < smaller; i++) {
      objectDifferences.add(
          c1.getTrackObjectsNumber(i + 1) - c2.getTrackObjectsNumber(i + 1));
      System.out.println(
          "���" + (i + 1) + "��������Ŀ���죺" + objectDifferences.get(i) + "�������"
              + c1.getTrack(i + 1).toString() + c2.getTrack(i + 1).toString());
    }
    for (int i = smaller; i < bigger; i++) {
      objectDifferences.add(trackNumbersDifference > 0
          ? c1.getTrackObjectsNumber(i + 1) : -c2.getTrackObjectsNumber(i + 1));
      System.out.println(
          "���" + (i + 1) + "��������Ŀ���죺" + objectDifferences.get(i) + "�������"
              + c1.getTrack(i + 1).toString() + c2.getTrack(i + 1).toString());
    }

  }
}
