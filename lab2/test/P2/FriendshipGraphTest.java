package P2;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class FriendshipGraphTest {

    //Test strategy
    //�жϼ����Ƿ�ɹ�+�жϼ����Ƿ��ظ�
    @Test void testAddVertex() {
        FriendshipGraph graph = new FriendshipGraph();
        Person person1 = new Person("Person");
        graph.addVertex(person1);// �жϼ����˶���
        assertTrue(graph.vertices().contains(person1));

        graph.addVertex(person1);// �ж�û���ظ�����һ������Ķ���
        List<Person> personList = new ArrayList<>();
        for (Person person : graph.vertices()) {
            personList.add(person);
        }
        assertTrue(personList.indexOf(person1) == personList.lastIndexOf(person1));
    }

    //Test strategy
    //�жϼ����Ƿ�ɹ�+�����Ƿ����
    @Test void testAddEdge() {
        FriendshipGraph graph = new FriendshipGraph();
        Person person1 = new Person("Person1");
        Person person2 = new Person("Person2");
        Person person3 = new Person("Person3");
        graph.addVertex(person3);
        graph.addVertex(person2);
        graph.addVertex(person1);
        graph.addEdge(person1, person2);// �ж���û�м���˱�
        assertTrue(graph.targets(person1).containsKey(person2));
        // �ж���û�м�������Ĵ����
        assertTrue(graph.targets(person1).size() == 1);
    }

    //Test strategy
    //�ж�Ϊ0��Ϊ-1��������ȡ���
    @Test void testGetDistance() {
        FriendshipGraph graph = new FriendshipGraph();
        Person A = new Person("a");
        Person B = new Person("b");
        Person C = new Person("c");
        graph.addVertex(A);
        graph.addVertex(B);
        graph.addVertex(C);
        graph.addEdge(A, B);
        graph.addEdge(B, A);
        graph.addEdge(B, C);
        graph.addEdge(C, B);
        graph.addEdge(A, C);
        graph.addEdge(C, A);
        // ��֤�ҵ�����Ϊ��С
        assertEquals(1, graph.getDistance(A, C));

        Person D = new Person("d");
        graph.addVertex(D);
        graph.addEdge(C, D);
        graph.addEdge(D, C);
        graph.addEdge(B, D);
        graph.addEdge(D, B);
        graph.addEdge(A, D);
        graph.addEdge(D, A);
        // ��֤�ҵ�����Ϊ��С
        assertEquals(1, graph.getDistance(A, D));

        // ��֤�Լ����Լ��ľ���Ϊ0
        assertEquals(0, graph.getDistance(A, A));

        Person E = new Person("e");
        graph.addVertex(E);

        // ��֤û�й�ϵ����-1
        assertEquals(-1, graph.getDistance(A, E));
    }

}
