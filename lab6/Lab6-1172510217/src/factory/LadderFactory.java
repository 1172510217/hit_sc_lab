package factory;

import adt.Ladder;
import adt.Monkey;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LadderFactory {

  private static ArrayList<Ladder> allLadders = new ArrayList<>();
  private static Map<Integer, HashMap<Integer, Integer>> ladderOccupy =
      Collections.synchronizedMap(new HashMap<>());
  // ������������ռ�����������ID��Ӧ���Ӽ�������̤��ID

  public static void addLadder(Ladder ladder) {
    allLadders.add(ladder);
    ladderOccupy.put(ladder.getId(), new HashMap<>());
  }

  /**
   * change the ladder occupy. if rungId is 0, remove the monkey on the ladder;else put
   * the key and value in the ladderOccupy map.
   * 
   * @param ladderId the id of the ladder
   * @param monkeyId the id of the monkey
   * @param rungId   the id of the rung
   */
  public static void changeOccupy(Integer ladderId, Integer monkeyId, Integer rungId) {
    if (rungId.equals(0)) {
      ladderOccupy.get(ladderId).remove(monkeyId);
    } else {
      ladderOccupy.get(ladderId).put(monkeyId, rungId);
    }
  }

  /**
   * get the number of all the ladders.
   * 
   * @return the number of all the ladder
   */
  public static Integer getLadderNum() {
    return allLadders.size();
  }

  public static Integer getOccupySize(Integer ladderId) {
    return ladderOccupy.get(ladderId).size();
  }

  /**
   * get the monkey's ladder.
   * 
   * @param monkeyId the monkey id
   */
  public static Integer getMonkeyLadder(Integer monkeyId) {
    for (Integer ladderId : ladderOccupy.keySet()) {
      if (ladderOccupy.get(ladderId).containsKey(monkeyId)) {
        return ladderId;
      }
    }
    return null;
  }

  /**
   * get the rung id of the monkey's state.
   * 
   * @param  monkeyId the unique monkey id
   * @return          the rung id which is in use by the monkey
   */
  public static Integer getMonkeyRung(Integer monkeyId) {
    return ladderOccupy.get(getMonkeyLadder(monkeyId)).get(monkeyId);
  }

  /**
   * get the length of the ladder, it is a static number.
   * 
   * @return the number of the ladder.
   */
  public static Integer getLadderLength() {
    return allLadders.get(0).getLength();
  }

  /**
   * get the monkey number which has the same direction with the monkey.
   * 
   * @param  ladderId the unique ladder id
   * @param  monkey   the monkey whose direction is our consideration
   * @return          the monkey number
   */
  public static Integer monkeySameDirection(Integer ladderId, Monkey monkey) {
    Integer direction = monkey.getDirection();
    Integer monkeyNum = 0;
    for (Integer monkeyId : ladderOccupy.get(ladderId).keySet()) {
      if (MonkeyFactory.getMonkeyById(monkeyId).getDirection().equals(direction)) {
        monkeyNum++;// ��ú���ͬ��ĺ�����Ŀ
      }
    }
    return monkeyNum;
  }

  /**
   * return true if the ladder and the rung is in use; else false.
   * 
   * @param  ladderId the unique ladderId
   * @param  rungId   the unique rungId
   * @return          true if the special rung is in use, else false
   */
  public static boolean checkOccupy(Integer ladderId, Integer rungId) {
    return ladderOccupy.get(ladderId).containsValue(rungId);
  }

  /**
   * in order to:�õ������ͬ��ĸ������ϵ�һֻ��������̤���id.
   * 
   * @param  ladderId ����id
   * @param  monkey   ����
   * @return          ����Ŀ��̤��id
   */
  public static Integer getFirstRungIdSameDirection(Integer ladderId, Monkey monkey) {
    Integer resultRungId = null;
    if (monkey.getDirection().equals(0)) {
      resultRungId = getLadderLength() + 1;
      for (Integer rungId : ladderOccupy.get(ladderId).values()) {
        if (rungId < resultRungId) {
          resultRungId = rungId;
        }
      }
    } else {
      resultRungId = 0;
      for (Integer rungId : ladderOccupy.get(ladderId).values()) {
        if (rungId > resultRungId) {
          resultRungId = rungId;
        }
      }
    }
    return resultRungId;
  }

  /**
   * ���Ӳ�����.
   * 
   * @param ladderNum ������Ŀ
   * @param length    ���ӳ���
   */
  public static void ladderGenerator(Integer ladderNum, Integer length) {
    assert ladderNum != null && ladderNum > 0 : "������Ŀ����";
    assert length != null && length > 0 : "���ӳ��ȴ���";
    for (int i = 0; i < ladderNum; i++) {
      Ladder ladder = new Ladder(i + 1, length);
      LadderFactory.addLadder(ladder);
    }
  }

}
