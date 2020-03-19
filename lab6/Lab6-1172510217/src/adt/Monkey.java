package adt;

import factory.MonkeyCrossRiverSystem;
import factory.MonkeyFactory;
import java.util.logging.Logger;
import ladderstrategy.LadderStrategy;

/**
 * AF:id,���ӵ�Ψһ��ʶ
 * direction,0=L->R;1=R->L
 * speed:�����������ϵ������ٶ�,������
 * RI: speed�Ⱦ�Ϊ����
 * safe from exposure:
 * ����������еĳ�Ա��������liveTime����private��һ����������ı䣬�����б�ʾй©������
 * thread safe:
 * for�����û�ж��̹߳��������ݣ��������̰߳�ȫ����.
 */
public class Monkey implements Runnable {

  private static StringBuilder logBuilder = new StringBuilder();
  private static Logger logger =
      Logger.getLogger(MonkeyCrossRiverSystem.class.getSimpleName());//������־
  private Integer id = null;// ���ӵ�id���Ƕ�һ�޶��ģ���1��ʼ
  private Integer direction = null;// ���ӵ��ƶ�����0����L->R��1����R->L
  private Integer speed = null;// ���ӵ��ٶȣ�Ϊ������,��Χ��[1,MV]
  private Integer bornTime = null;// ���ӵĲ���ʱ�䣬��ȷ���ķǸ�����
  private Integer liveTime = null;// ���ӵĹ�����ֹʱ�䣬��ȷ����������
  private LadderStrategy ladderStrategy = null;// ����ѡ�����ӵĲ���

  /**
   * the monkey is unique when born.
   * 
   * @param id             unique id
   * @param direction      the direction when crossing the river
   * @param speed          the speed when crossing the river
   * @param bornTime       the born time of the monkey
   * @param ladderStrategy the strategy the monkey takes to cross the river
   */
  public Monkey(Integer id, Integer direction, Integer speed, Integer bornTime,
      LadderStrategy ladderStrategy) {
    this.id = id;
    this.direction = direction;
    this.speed = speed;
    this.bornTime = bornTime;
    this.ladderStrategy = ladderStrategy;
  }

  /**
   * override monkey thread.
   */
  @Override public void run() {
    liveTime = 0;
    Integer monkeyState = 0;
    do {
      synchronized (Monkey.class) {
        ladderStrategy.ladderStrategy(this);
        monkeyState = MonkeyFactory.getMonkeyState(id);
        if (monkeyState.equals(0)) {
          logger.info("���ӣ�" + id + " ����" + directionString() + " ״̬���ȴ� ��ʱ��" + liveTime
              + "  ���ԣ�" + ladderStrategy);
          logBuilder.append("���ӣ�").append(id).append(" ����").append(directionString())
              .append(" ״̬���ȴ� ��ʱ��").append(liveTime++).append(" ���ԣ�")
              .append(ladderStrategy).append("\n");
        } else if (monkeyState.equals(1)) {
          logger.info("���ӣ�" + id + " ����" + directionString() + " ״̬������ ��ʱ��" + liveTime
              + "  ���ԣ�" + ladderStrategy);
          logBuilder.append("���ӣ�").append(id).append(" ����").append(directionString())
              .append(" ״̬������ ��ʱ��").append(liveTime++).append(" ���ԣ�")
              .append(ladderStrategy).append("\n");
        } else {
          logger.info("���ӣ�" + id + " ����" + directionString() + " ״̬���ɹ� ��ʱ��" + liveTime
              + "  ���ԣ�" + ladderStrategy);
          logBuilder.append("���ӣ�").append(id).append(" ����").append(directionString())
              .append(" ״̬���ɹ� ��ʱ��").append(liveTime).append(" ���ԣ�").append(ladderStrategy)
              .append("\n");
          break;
        }
      }
      try {
        Thread.sleep(1000 / SystemConstant.getSpeedUp());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    } while (true);
  }

  /**
   * get the live time of the monkey.
   * 
   * @return the liveTime
   */
  public Integer getLiveTime() {
    return liveTime;
  }

  /**
   * get the id of the monkey.
   * 
   * @return the id
   */
  public Integer getId() {
    return id;
  }

  /**
   * get the direction of the monkey.
   * 
   * @return the direction
   */
  public Integer getDirection() {
    return direction;
  }

  /**
   * get the speed of the monkey.
   * 
   * @return the speed
   */
  public Integer getSpeed() {
    return speed;
  }

  /**
   * get the born time of the monkey.
   * 
   * @return the bornTime
   */
  public Integer getBornTime() {
    return bornTime;
  }

  /**
   * get the strategy the monkey takes.
   * 
   * @return the ladderStrategy
   */
  public LadderStrategy getLadderStrategy() {
    return ladderStrategy;
  }

  /**
   * direction to string.
   * 
   * @return the string of the direction
   */
  public String directionString() {
    if (direction.equals(0)) {
      return "L->R";
    } else {
      return "R->L";
    }
  }

  /**
   * override the toString method in the class to express the monkey better.
   */
  @Override public String toString() {
    StringBuilder stringBuilder = new StringBuilder("monkey[id=");
    stringBuilder.append(id).append(",diretion=").append(this.directionString())
        .append(",speed=").append(speed).append(",bornTime=").append(bornTime)
        .append(",ladderStrategy=").append(ladderStrategy).append("]");
    return stringBuilder.toString();
  }

  public static String getLogString() {
    return logBuilder.toString();
  }

}
