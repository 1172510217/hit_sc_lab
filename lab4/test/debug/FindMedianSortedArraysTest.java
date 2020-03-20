package debug;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FindMedianSortedArraysTest {

    /**
     * testing strategy:
     * A == null | B == null, A != null && B != null;
     * size(A + B) %2 == 0,size(A + B) %2 == 1;
     * size(A) < size(B),size(A) = size(B),size(A) > size(B)
     * max(A) <= max(B), min(A) >= min (B);max(A) > max(B), min(A) < min(B)
     */

    // ����AΪ�գ�B���գ�A��B�����С��Ϊż��
    // ���������A = null,B != null; size(A) + size(B) %2 == 0;size(A) < size(B)
    @Test void testArrayNullAndSumEven() {
        int[] A = null, B = { 1, 2, 3, 4 };
        FindMedianSortedArrays findMedianSortedArrays = new FindMedianSortedArrays();
        double res = findMedianSortedArrays.findMedianSortedArrays(A, B);
        assertEquals(2.5, res);
    }

    // ����A���գ�BΪ�գ�A��B�����С��Ϊ������A��СС��B
    // ���������A != null, B == null; size(A) + size(B) % 2 == 1; size(A) > size(B)
    @Test void testArrayNullAndSumOdd() {
        int[] B = null, A = { 1, 2, 3 };
        FindMedianSortedArrays findMedianSortedArrays = new FindMedianSortedArrays();
        double res = findMedianSortedArrays.findMedianSortedArrays(A, B);
        assertEquals(2, res);
    }

    // ����A��B�ǿգ�A��B��С��Ϊż����A��С����B������A���ֵ����B���ֵ����СֵС��B��Сֵ
    // ���������A != null && B != null; size(A) + size(B) % 2 == 0; size(A) == size(B);
    // max(A) > max(B), min(A) < min(B)
    @Test void testNonnullAndSumEven() {
        int[] A = { 1, 2, 7 }, B = { 4, 5, 6 };
        FindMedianSortedArrays findMedianSortedArrays = new FindMedianSortedArrays();
        double res = findMedianSortedArrays.findMedianSortedArrays(A, B);
        assertEquals(4.5, res);
    }

    // ����A��B�ǿգ�A��B��С��Ϊ������A��С����B������A���ֵС��B���ֵ����Сֵ����B��Сֵ
    // ���������A != null && B != null; size(A) + size(B) % 2 == 1; size(A) > size(B);
    // max(A) <= max(B), min(A) >= min (B);

    @Test void testNonnullAndSumOdd() {
        int[] A = { 1, 2, 3 }, B = { 0, 5 };
        FindMedianSortedArrays findMedianSortedArrays = new FindMedianSortedArrays();
        double res = findMedianSortedArrays.findMedianSortedArrays(A, B);
        assertEquals(2, res);
    }

}
