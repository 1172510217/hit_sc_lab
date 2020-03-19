/*
 * Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course
 * staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {

    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph<String>();
    }

    /*
     * Testing ConcreteEdgesGraph...
     */

    @Test public void testEmptyGraph() {
        Graph<String> graph = new ConcreteEdgesGraph<String>();
        assertTrue(graph.vertices().isEmpty());
    }

    // Testing strategy for ConcreteEdgesGraph.toString()
    // graph����+�ǿ�
    // vertices����+�������
    // edges����+�����
    @Test public void testToString() {
        Graph<String> graph = emptyInstance();
        String string = new String();
        string += "All the vertices : ";
        string += "\n" + "All the edges : ";
        // ��֤graphΪ�յ���������������graphΪ��+verticesΪ��+edgesΪ�գ�
        assertEquals(string, graph.toString());
        // ��֤graph�ǿգ����������graph�ǿ�+vertices�ǿգ�
        String string0 = new String("string0");
        String string1 = new String("string1");
        graph.add(string0);
        graph.add(string1);
        string = "";
        string += "All the vertices : ";
        string += string1 + " " + string0 + " ";
        string += "\n" + "All the edges : ";
        assertEquals(string, graph.toString());
        // ��֤graph�ǿգ����������graph�ǿ�+vertices�ǿ�+edges�ǿգ�
        String string2 = new String("string2");
        graph.add(string2);
        graph.set(string0, string1, 1);
        graph.set(string0, string2, 2);
        string = "";
        string += "All the vertices : ";
        string += string1 + " " + string2 + " " + string0 + " ";
        string += "\n" + "All the edges : ";
        string += "(" + string0 + "->" + string1 + ":1)";
        string += "(" + string0 + "->" + string2 + ":2)";
        assertEquals(string, graph.toString());
    }

    /*
     * Testing Edge...
     */

    // Testing strategy for Edge
    // test get & toString
    @Test public void testGet() {
        String string0 = new String("string0");
        String string1 = new String("string1");
        int weight = 1;
        Edge<String> edge1 = new Edge<String>(string0, string1, weight);
        assertEquals(edge1.getSource(), string0);
        assertEquals(edge1.getTarget(), string1);
        assertEquals(edge1.getWeight(), 1);
    }
    @Test public void testEdgeToString() {
        String string0 = new String("string0");
        String string1 = new String("string1");
        String string2 = new String("string2");
        int weight = 1;
        Edge<String> edge1 = new Edge<String>(string0, string1, weight);
        Edge<String> edge2 = new Edge<String>(string0, string2, weight);
        String string = "";
        string += "(" + string0 +"->" + string1 + ":1)";
        assertEquals(string, edge1.toString());
        string = "";
        string += "(" + string0 +"->" + string2 + ":1)";
        assertEquals(string, edge2.toString());
    }
}
