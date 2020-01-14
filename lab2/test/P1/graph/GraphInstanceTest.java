/*
 * Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course
 * staff.
 */
package P1.graph;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;

import org.junit.Test;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>
 * PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {

    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();

    @Test(expected = AssertionError.class) public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

    @Test public void testInitialVerticesEmpty() {
        assertEquals("expected new graph to have no vertices", Collections.emptySet(), emptyInstance().vertices());
    }

    // Testing strategy
    // graph����+�ǿ�
    // vertex�����ӱ߲�����+���ӱߴ���
    // �����������ֵΪtrue��������Ŀ++�Ҷ��㼯�а���vertex�����򣬶��㼯�����Ҷ��㼯�а���vertex
    // number of vertices increases by 1 else graph unmodified
    // observe with vertices()
    @Test public void testAdd() {
        Graph<String> graph = emptyInstance();
        final int size0 = graph.vertices().size();
        final String string0 = "string0";
        // �жϿյ�graph����һ�����㣨���������graphΪ��+vertex���ӱ߲�����+����ֵΪtrue��������++��
        assertTrue(graph.add(string0));// ��֤�յ�graph����string0
        assertTrue(size0 == 0 && graph.vertices().size() == 1);// ��֤����󶥵���Ϊ1�Ҽ���ǰΪ0
        assertTrue(graph.vertices().contains(string0));// ��֤�����˸ö���
        // �ж������graph�ټ���string0�����������graph�ǿ�+���ӱߴ���+����ֵΪfalse�Ҷ��㼯�����Ұ���string0��
        final String string1 = "string1";
        graph.add(string1);
        assertFalse(graph.add(string0));// ��֤����ֵΪfalse
        assertTrue(graph.vertices().size() == 2 && graph.vertices().contains(string0));// ��֤������string0
    }

    // Testing strategy
    // graph����+�ǿ�
    // vertex�������ö����Ѿ�����+������
    // weight��Ϊ0ʱ��ɾ�������㣬�޸�
    // ����ֵ��Ϊ0���߲����ڣ����򣬱ߴ��ڣ��ҷ���ֵ��ԭ����Ȩ��
    @Test public void testSet() {
        Graph<String> graph = emptyInstance();
        final String string0 = "string0";
        final String string1 = "string1";
        // ��֤�յ�graph���ñ�weight�����������graphΪ��+�����ñ߲�����+weight��Ϊ0+����ֵΪ0��
        assertTrue(graph.set(string0, string1, 1) == 0);
        assertTrue(graph.vertices().size() == 2 && graph.vertices().contains(string0)
                && graph.vertices().contains(string1));
        assertTrue(graph.targets(string0).containsKey(string1) && graph.targets(string0).size() == 1);
        assertTrue(graph.sources(string1).containsKey(string0) && graph.sources(string1).size() == 1);
        assertTrue(graph.targets(string0).get(string1) == 1);// ��֤�����õı�Ȩ��Ϊ1
        // ��֤�ǿյ�graph���ñ�weight�����������graph�ǿ�+�����ñߴ���+weight��Ϊ0+����ֵ��0��
        assertTrue(graph.set(string0, string1, 2) == 1);// ����Ȩ��Ϊ1
        assertTrue(graph.vertices().size() == 2 && graph.vertices().contains(string0)
                && graph.vertices().contains(string1));
        assertTrue(graph.targets(string0).containsKey(string1) && graph.targets(string0).size() == 1);
        assertTrue(graph.sources(string1).containsKey(string0) && graph.sources(string1).size() == 1);
        assertTrue(graph.targets(string0).get(string1) == 2);// ��֤�����õı�Ȩ��Ϊ2
        // ��֤�ǿ�graphɾ���ߣ����������graph�ǿ�+�����ö������+weightΪ0+����ֵ��0��
        assertTrue(graph.set(string0, string1, 0) == 2);// ����Ȩ��Ϊ2
        assertTrue(graph.vertices().size() == 2 && graph.vertices().contains(string0)
                && graph.vertices().contains(string1));
        assertTrue(graph.targets(string0).size() == 0 && graph.sources(string1).size() == 0);

    }

    // Testing strategy
    // graph����+�ǿ�
    // vertex����ɾ��������ڶ��㼯��+�����ڣ���ɾ��������ڱ���+������
    // �����������ֵΪfalse�������ڸö��㣬���㼯��Ŀ���䣬�߼����䣻�����ڶ��㼯���߱߼����ڸö��㣬���㼯��Ŀ--
    @Test public void testRemove() {
        Graph<String> graph = emptyInstance();
        final String string0 = "string0";
        final String string1 = "string1";
        final String string2 = "string2";
        // ��֤�յ�graphɾ��һ�����㣨���������graphΪ��+vertex��ɾ�߲�����+����ֵΪfalse�����������䣩
        assertFalse(graph.remove(string0));
        assertFalse(graph.vertices().contains(string0));
        // ��֤�ǿ�graphɾ��һ�����㣨���������graph�ǿ�+vertex��ɾ�ߴ���+����ֵΪtrue�Ҷ�����--�Ҷ��㼯�в����ö��㣩
        graph.add(string0);
        graph.add(string1);
        assertTrue(graph.remove(string0));// �жϷ���ֵΪtrue
        assertFalse(graph.vertices().contains(string0));// �ж�ɾ���󲻺��øö���
        assertTrue(graph.vertices().size() == 1);// �ж϶��㼯��Ŀ--
        // ��֤�ǿ�graphɾ��һ�����㣨����������߼��д��ڸö��㣩
        graph.add(string0);
        graph.add(string2);
        graph.set(string0, string1, 1);
        graph.set(string0, string2, 2);
        graph.set(string1, string2, 3);
        assertTrue(graph.remove(string0));// ��֤����ֵΪtrue
        assertTrue(graph.vertices().size() == 2 && !graph.vertices().contains(string0));// ��֤���㼯��Ŀ--�Ҳ����ö���
        assertTrue(graph.sources(string0).size() == 0 && graph.sources(string0).size() == 0);// ��֤�߼��в����ö���
        // ��֤graph�ı߼��в����ö��㣨����������߼��в����ö��㣩
        graph.add(string0);
        assertTrue(graph.remove(string0));
    }

    // Testing strategy
    // graph����+�ǿ�
    @Test public void testVertices() {
        Graph<String> graph = emptyInstance();
        // ��֤graphΪ�յ����
        assertEquals(Collections.emptySet(), graph.vertices());
        // ��֤graph�ǿյ����
        String string0 = "string0";
        String string1 = "string1";
        graph.add(string0);
        graph.add(string1);
        assertTrue(graph.vertices().size() == 2 && graph.vertices().contains(string0)
                && graph.vertices().contains(string1));
    }

    // Testing strategy
    // graph����+�ǿ�
    // vertex����Ѱ�Ҷ�������ڶ��㼯��+�����ڣ�
    // edges����Ѱ�Ҷ���������+������
    // ��������ص�map�������е����
    @Test public void testSources() {
        Graph<String> graph = emptyInstance();
        String string0 = new String("string0");
        // ��֤graphΪ�յ����(���������graphΪ��+��Ѱ�ҽڵ㲻�����ڶ��㼯��)
        assertEquals(Collections.emptyMap(), graph.sources(string0));
        // ��֤graph�ǿ��Ҵ�Ѱ�Ҷ��㲻�����ڱ߼���
        String string1 = new String("string1");
        graph.add(string0);
        graph.add(string1);
        assertEquals(Collections.emptyMap(), graph.sources(string0));
        // ��֤graph�ǿ��Ҵ��ڸýڵ�����
        graph.set(string1, string0, 1);
        assertTrue(graph.sources(string0).size() == 1 && graph.sources(string0).containsKey(string1)
                && graph.sources(string0).get(string1) == 1);
    }

    // Testing strategy
    // graph����+�ǿ�
    // vertex����Ѱ�Ҷ�������ڶ��㼯��+�����ڣ�
    // edges����Ѱ�Ҷ�����ڳ���+������
    // ��������ص�map�������еĳ���
    @Test public void testTargets() {
        Graph<String> graph = emptyInstance();
        String string0 = new String("string0");
        // ��֤graphΪ�յ����(���������graphΪ��+��Ѱ�ҽڵ㲻�����ڶ��㼯��)
        assertEquals(Collections.emptyMap(), graph.sources(string0));
        // ��֤graph�ǿ��Ҵ�Ѱ�Ҷ��㲻�����ڱ߼���
        String string1 = new String("string1");
        graph.add(string0);
        graph.add(string1);
        assertEquals(Collections.emptyMap(), graph.sources(string0));
        // ��֤graph�ǿ��Ҵ��ڸýڵ�ĳ���
        graph.set(string1, string0, 1);
        assertTrue(graph.targets(string1).size() == 1 && graph.targets(string1).containsKey(string0)
                && graph.targets(string1).get(string0) == 1);
    }
}
