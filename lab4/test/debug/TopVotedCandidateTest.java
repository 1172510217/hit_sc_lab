package debug;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TopVotedCandidateTest {

    /**
     * testing strategy : ��������ʱ��
     */
    @Test void testNomal() {
        int[] persons = { 0, 1, 1, 0, 0, 1, 0 };
        int[] times = { 0, 5, 10, 15, 20, 25,30 };
        TopVotedCandidate topVotedCandidate = new TopVotedCandidate(persons, times);
        assertEquals(0, topVotedCandidate.q(3));
    }
    
    /**
     * testing strategy : ���Ե�ƽ��ʱ��ѡȡ�����ѡ��
     */
    @Test void testEqual() {
        int[] persons = { 0, 1, 1, 0, 0, 1, 0 };
        int[] times = { 0, 5, 10, 15, 20, 25,30 };
        TopVotedCandidate topVotedCandidate = new TopVotedCandidate(persons, times);
        assertEquals(1, topVotedCandidate.q(26));
    }
    
    /**
     * testing strategy : ����ʱ���ͶƱʱ��һ��ʱ������ͶƱ��������
     */
    @Test void testTimeEqual() {
        int[] persons = { 0, 1, 1, 0, 0, 1, 0 };
        int[] times = { 0, 5, 10, 15, 20, 25,30 };
        TopVotedCandidate topVotedCandidate = new TopVotedCandidate(persons, times);
        assertEquals(1, topVotedCandidate.q(25));
    }

}
