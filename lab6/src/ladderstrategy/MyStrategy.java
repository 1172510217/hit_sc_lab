package ladderstrategy;

import adt.Monkey;
import factory.LadderFactory;
import factory.MonkeyFactory;

/**
 * my personal strategy:����ѡȡδ��ռ�õ����ӣ�������ռ�ã�����ѡȡ�޶�����ʻ�ĺ��ӣ�
 * then:�������ѡ��������ѡȡ������ͬ�������Ŀ�����Ҿ����Լ���Զ������.
 * 
 * @author 86185
 */
public class MyStrategy implements LadderStrategy {

  @Override public String toString() {
    return "MyStrategy";
  }

  /**
   * my strategy.
   */
  public Integer ladderStrategy(Monkey monkey) {
    Integer ladderNum = LadderFactory.getLadderNum();
    Integer monkeyId = monkey.getId();
    Integer direction = monkey.getDirection();
    if (MonkeyFactory.getMonkeyState(monkey.getId()).equals(0)) {
      // ����ѡ��û�к��ӵ�����
      for (int i = 1; i <= ladderNum; i++) {
        if (LadderFactory.getOccupySize(i) == 0) {
          MonkeyFactory.changeState(monkeyId, 1);
          if (direction.equals(0)) {
            LadderFactory.changeOccupy(i, monkeyId, 1);
          } else {
            LadderFactory.changeOccupy(i, monkeyId, LadderFactory.getLadderLength());
          }
          return i;
        }
      }
      // ������ռ�ã���ѡ���޶�����к��ӵ�����
      Integer betterLadder = ladderNum + 1;
      Integer monkeySameDirection = MonkeyFactory.getMonkeyNum() + 1;
      // ������ΪL->R
      if (direction.equals(0)) {
        for (int i = 1; i <= ladderNum; i++) {
          if (LadderFactory.getOccupySize(i)
              - LadderFactory.monkeySameDirection(i, monkey) == 0) {
            if (!LadderFactory.checkOccupy(i, 1)) {
              // Ѱ��ͬ��������ٵ�����
              if (LadderFactory.monkeySameDirection(i, monkey) < monkeySameDirection) {
                betterLadder = i;
                monkeySameDirection = LadderFactory.monkeySameDirection(i, monkey);
              }
              // ����һ����ǰ���£�Ѱ�������ϵ�һ�����Ӿ൱ǰ������Զ������.
              if (LadderFactory.monkeySameDirection(i, monkey) == monkeySameDirection) {
                if (LadderFactory.getFirstRungIdSameDirection(i, monkey) < LadderFactory
                    .monkeySameDirection(betterLadder, monkey)) {
                  betterLadder = i;
                }
              }
            }
          }
        }
        if (!betterLadder.equals(ladderNum + 1)) {
          MonkeyFactory.changeState(monkeyId, 1);
          LadderFactory.changeOccupy(betterLadder, monkeyId, 1);
          return betterLadder;
        }
      } else {
        for (int i = 1; i <= ladderNum; i++) {
          if (LadderFactory.getOccupySize(i)
              - LadderFactory.monkeySameDirection(i, monkey) == 0) {
            if (!LadderFactory.checkOccupy(i, LadderFactory.getLadderLength())) {
              // Ѱ������ͬ����ӵ�����
              if (LadderFactory.monkeySameDirection(i, monkey) < monkeySameDirection) {
                betterLadder = i;
                monkeySameDirection = LadderFactory.monkeySameDirection(i, monkey);
              }
              // ����һ����ǰ���£�Ѱ�������ϵ�һ�����Ӿ൱ǰ������Զ������.
              if (LadderFactory.monkeySameDirection(i, monkey) == monkeySameDirection) {
                if (LadderFactory.getFirstRungIdSameDirection(i, monkey) > LadderFactory
                    .monkeySameDirection(betterLadder, monkey)) {
                  betterLadder = i;
                }
              }
            }
          }
        }
        if (!betterLadder.equals(ladderNum + 1)) {
          MonkeyFactory.changeState(monkeyId, 1);
          LadderFactory.changeOccupy(betterLadder, monkeyId,
              LadderFactory.getLadderLength());
          return betterLadder;
        }
      }
      return null;
    } else {
      Integer ladderId = LadderFactory.getMonkeyLadder(monkeyId);
      Integer nowRung = LadderFactory.getMonkeyRung(monkeyId);
      Integer speed = monkey.getSpeed();
      Integer ladderLength = LadderFactory.getLadderLength();
      // ������ӣ�L->R
      if (monkey.getDirection().equals(0)) {
        for (int i = nowRung + 1; i <= nowRung + speed; i++) {
          if (i > ladderLength) {
            MonkeyFactory.changeState(monkeyId, 2);
            LadderFactory.changeOccupy(ladderId, monkeyId, 0);
            return null;
          }
          if (LadderFactory.checkOccupy(ladderId, i)) {
            LadderFactory.changeOccupy(ladderId, monkeyId, i - 1);
            return i - nowRung - 1;
          }
        }
        LadderFactory.changeOccupy(ladderId, monkeyId, nowRung + speed);
        return speed; // ������ӣ�R->L
      } else {
        for (int i = nowRung - 1; i >= nowRung - speed; i--) {
          if (i < 1) {
            MonkeyFactory.changeState(monkeyId, 2);
            LadderFactory.changeOccupy(ladderId, monkeyId, 0);
            return null;
          }
          if (LadderFactory.checkOccupy(ladderId, i)) {
            LadderFactory.changeOccupy(ladderId, monkeyId, i + 1);
            return nowRung - i - 1;
          }
        }
        LadderFactory.changeOccupy(ladderId, monkeyId, nowRung - speed);
        return speed;
      }
    }
  }
}
