/*
 * Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course
 * staff.
 */
package P1.poet;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {

    // Testing strategy
    // corpus��һ������+�������+һ�е���+���е���
    // input��һ������+�������+һ�е���+���е���+��Сд

    @Test(expected = AssertionError.class) public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
  //����Ҫ���ϵ�������������������������corpus����+�����һ��input����+��Сд��
    @Test public void testExample() throws IOException {
        String name = "test/P1/poet/seven-words.txt";
        File file = new File(name);
        GraphPoet graphPoet = new GraphPoet(file);
        String input = "Seek to explore new and exciting synergies!";
        String poem = graphPoet.poem(input);
        String actual = "Seek to explore strange new life and exciting synergies!";
        assertEquals(poem, actual);
    }
    
  //����һ��corpus���뵥�ʣ����������corpusһ������+input��Сд+һ�����ʣ�
    @Test public void testOneWordCorpus1() throws IOException {
        String string = "test/P1/poet/one-word-corpus.txt";
        File file = new File(string);
        GraphPoet graphPoet = new GraphPoet(file);
        String input = "Hello";
        String poem = graphPoet.poem(input);
        String actual = "Hello";
        assertEquals(poem, actual);
    }
    
    //����һ��corpus���뵥�ʣ����������corpusһ������+input��Сд+������ʣ�
    @Test public void testOneWordCorpus2() throws IOException {
        String string = "test/P1/poet/one-word-corpus.txt";
        File file = new File(string);
        GraphPoet graphPoet = new GraphPoet(file);
        String input = "Hello hello";
        String poem = graphPoet.poem(input);
        String actual = "Hello hello";
        assertEquals(poem, actual);
    }
    
  //���Զ��corpus���뵥�ʣ����������corpus�������+input��Сд+������ʣ�
    @Test public void testMutiInputLines() throws IOException {
        String string = "test/P1/poet/muti-line-input.txt";
        File file = new File(string);
        GraphPoet graphPoet = new GraphPoet(file);
        String input = "I have a life idea about a world ";
        input += "We talk about a start";
        String poem = graphPoet.poem(input);
        String actual = "I have a new life idea about a new world ";
        actual += "We don't talk about a new start";
        assertEquals(poem, actual);
    }
}
