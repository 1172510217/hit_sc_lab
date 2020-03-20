/*
 * Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course
 * staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {

    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph<String>();
    }

    /*
     * Testing ConcreteVerticesGraph...
     */

    // Testing strategy for ConcreteVerticesGraph.toString()
    // graph����+�ǿ�
    // vertices�����߼�Ϊ��+�ǿ�

    @Test public void testToString() {
        Graph<String> graph = emptyInstance();
        // ��֤Ϊ��
        assertEquals("", graph.toString());
        // ��֤��һ������
        String string0 = "string0";
        String string1 = "string1";
        graph.set(string0, string1, 1);
        String string = "";
        string += "Vertex : " + string1 + "\n\n";
        string += "Vertex : " + string0 + "\n";
        string += "(" + string1 + ":1)\t\n";
        assertEquals(string, graph.toString());
        // ��֤�ж�������
        String string2 = "string2";
        graph.set(string0, string2, 2);
        string = "";
        string += "Vertex : " + string1 + "\n\n";
        string += "Vertex : " + string0 + "\n";
        string += "(" + string1 + ":1)\t" + "(string2:2)\t\n";
        string += "Vertex : " + string2 + "\n\n";
        assertEquals(string, graph.toString());
    }

    /*
     * Testing Vertex...
     */

    // Testing strategy for Vertex
    //test toString & get & addEdge
    @Test public void testVertexToString() {
        //��֤�޶�������
        String string0 = "string0";
        Vertex<String> vertex = new Vertex<String>(string0);
        assertEquals("Vertex : string0\n",vertex.toString());
        //��֤�������
        String string1 = "string1";
        String string2 = "string2";
        vertex.addEdge(string1, 1);
        vertex.addEdge(string2, 2);
        String string = "Vertex : string0\n(string1:1)\t(string2:2)\t";
        assertEquals(string, vertex.toString());
    }
    @Test public void testGet() {
        String string0 = "string0";
        Vertex<String> vertex = new Vertex<String>(string0);
        String string1 = "string1";
        String string2 = "string2";
        vertex.addEdge(string1, 1);
        vertex.addEdge(string2, 2);
        assertEquals(string0, vertex.getSource());
        assertEquals(1, vertex.getWeight(string1));
        assertEquals(2, vertex.getWeight(string2));
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put(string1, 1);
        map.put(string2, 2);
        assertEquals(map, vertex.getRelationMap());
    }
    
    @Test public void testAddEdge() {
        String string0 = "string0";
        Vertex<String> vertex = new Vertex<String>(string0);
        //�жϿձ߱�
        assertEquals(Collections.emptyMap(), vertex.getRelationMap());
        //�жϷǿձ߱�
        String string1 = "string1";
        String string2 = "string2";
        vertex.addEdge(string1, 1);
        vertex.addEdge(string2, 2);
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put(string1, 1);
        map.put(string2, 2);
        assertEquals(map, vertex.getRelationMap());
    }

}
