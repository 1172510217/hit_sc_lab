package circularorbit;

import java.util.Iterator;
import track.Track;

public interface CircularOrbit<L, E> extends Iterable<E>, Cloneable {

  public boolean addTrack(Track<E> track);// ���ӹ��

  public double getSystemEntropy();// �õ���ֵ

  public int getTracksNumber();// �õ������Ŀ

  public int getTrackObjectsNumber(int i);// �õ��ض�������i�ϵ�������Ŀ

  public Track<E> getTrack(int i);// �õ��ض�����������Ϊi

  public boolean deleteTrack(Track<E> track);// ɾ�����

  public boolean addCentralPoint(L l);// �������ĵ�

  public boolean addTrackObject(Track<E> track, E e);// �����е�һ���������������

  public L getCentralPoint();// �õ���������

  public Iterator<E> iterator();// ������

}
