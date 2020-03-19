package adt;

/**
 * this is a class used to organize some constants in the system to make the system faster
 * and easier to meet some changes.
 * 
 * @author 1172510217
 */
public class SystemConstant {

  private static Integer speedUp = 10;// ����ϵͳ���еļ���ϵ������ֵԽ�󣬲��������Լ����Ӿ����ٶ�Խ��
  private static Integer strategyNum = 3;// ����ϵͳ���ṩ�Ĺ��Ӳ��Ե���Ŀ������ʵ������������

  /**
   * get the speedUp.
   * 
   * @return the speedUp
   */
  public static Integer getSpeedUp() {
    return speedUp;
  }

  /**
   * set the speedUp.
   * 
   * @param speedUp the speedUp to set
   */
  public static void setSpeedUp(Integer speedUp) {
    SystemConstant.speedUp = speedUp;
  }

  /**
   * get the strategyNum.
   * 
   * @return the strategyNum
   */
  public static Integer getStrategyNum() {
    return strategyNum;
  }

  /**
   * set the strategyNum.
   * 
   * @param strategyNum the strategyNum to set
   */
  public static void setStrategyNum(Integer strategyNum) {
    SystemConstant.strategyNum = strategyNum;
  }

}
