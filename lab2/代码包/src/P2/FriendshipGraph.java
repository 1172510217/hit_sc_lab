package P2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

import P1.graph.ConcreteEdgesGraph;

public class FriendshipGraph extends ConcreteEdgesGraph<Person> {

    /**
     * ��һ���˼��붥�㼯��ͬʱ������Ƿ��Ѿ���������ж�
     * 
     * @param person person to be added
     */
    public void addVertex(Person person) {
        if (this.vertices().contains(person)) {
            System.out.println("The person has been added");
        } else {
            this.add(person);
        }
    }

    /**
     * ������������Ϊ�й�ϵ
     * 
     * @param person1 ���
     * @param person2 ����
     */
    public void addEdge(Person person1, Person person2) {
        this.set(person1, person2, 1);
    }

    /**
     * �ҵ����˵Ĺ�ϵ����
     * 
     * @param person1 person1Ϊ��Ѱ�ҵ�
     * @param person2 person2ΪĿ���
     * @return �������ߵľ���
     */
    public int getDistance(Person person1, Person person2) {
        if (person1 == person2) {// ��ͬһ���ˣ�����0
            return 0;
        }
        Map<Person, Boolean> visited = new HashMap<>();// �����ж��Ƿ񱻷���
        Map<Person, Integer> dis = new HashMap<>();// ���ڼ�¼����
        for (Person temp : this.vertices()) {// ������person���Ϊδ������
            visited.put(temp, false);
        }
        visited.put(person1, true);
        dis.put(person1, 0);
        Queue<Person> queue = new LinkedBlockingQueue<>();// �ȹ�Ҫ�ö�������
        queue.add(person1);// person1���
        while (!queue.isEmpty()) {// ѭ��ֱ������Ϊ��
            Person head = queue.poll();// �õ�����Ԫ�أ����������
            Set<Person> targetSet = this.targets(head).keySet();
            LinkedList<Person> targetList = new LinkedList<>();
            for (Person person : targetSet) {
                targetList.add(person);
            }
            Person temp = targetList.peek();// �õ������йصĵ�һ����
            this.targets(head);
            int i = 0;
            while (temp != null) {// ѭ��ֱ��������head��ֱ�ӹ�ϵ
                if (!visited.get(temp)) {// ��tempδ������
                    if (temp.equals(person2)) {// ���ҵ�person2
                        return dis.get(head) + 1;
                    } else {// ��δ�ҵ�
                        visited.put(temp, true);
                        dis.put(temp, dis.get(head) + 1);
                        queue.add(temp);// ����ǰperson���
                    }
                }
                if (++i < targetList.size()) {// ����Ѱ�������й�ϵ����
                    temp = targetList.get(i);
                } else {
                    break;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        FriendshipGraph graph = new FriendshipGraph();
        Person rachel = new Person("Rachel");
        Person ross = new Person("Ross");
        Person ben = new Person("Ben");
        Person kramer = new Person("Kramer");
        graph.addVertex(rachel);
        graph.addVertex(ross);
        graph.addVertex(ben);
        graph.addVertex(kramer);
        graph.addEdge(rachel, ross);
        graph.addEdge(ross, rachel);
        graph.addEdge(ross, ben);
        graph.addEdge(ben, ross);
        System.out.println(graph.getDistance(rachel, ross));
        // should print 1
        System.out.println(graph.getDistance(rachel, ben));
        // should print 2
        System.out.println(graph.getDistance(rachel, rachel));
        // should print 0
        System.out.println(graph.getDistance(rachel, kramer));
        // should print -1
    }
}
