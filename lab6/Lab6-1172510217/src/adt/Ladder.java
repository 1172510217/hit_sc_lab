package adt;

/**
 * AF:id,���ӵ�Ψһ��ʶ
 * length,���ӵĳ���,Ϊ������
 * RI: ���Ծ�Ϊ����
 * safe from exposure:
 * for����������еĳ�Ա����һ����������ı䣬�����б�ʾй©������
 * thread safe:
 * for�����û�ж��̹߳��������ݣ��������̰߳�ȫ����.
 */
public class Ladder {

  private Integer id = null;// ���ӵ�id����һ�޶��ģ���1��ʼ
  private Integer length = null;// ����̤����

  /**
   * the ladder always has a length and an unique id.
   * 
   * @param id the unique id of the ladder
   */
  public Ladder(Integer id, Integer length) {
    this.id = id;
    this.length = length;
  }

  /**
   * get the ladder id.
   * 
   * @return the id
   */
  public Integer getId() {
    return id;
  }

  /**
   * get the length of the ladder.
   * 
   * @return the length
   */
  public Integer getLength() {
    return length;
  }

  @Override public String toString() {
    return "ladder[id=" + id + ",length=" + length + "]";
  }

}
