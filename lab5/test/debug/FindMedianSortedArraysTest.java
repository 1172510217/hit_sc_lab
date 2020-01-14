package debug;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class FindMedianSortedArraysTest {

  /**
   * testing strategy:
   * a == null | b == null, a != null && b != null;
   * size(a + b) %2 == 0,size(a + b) %2 == 1;
   * size(a) < size(b),size(a) = size(b),size(a) > size(b)
   * max(a) <= max(b), min(a) >= min (b);max(a) > max(b), min(a) < min(b).
   */

  // ����AΪ�գ�B���գ�A��B�����С��Ϊż��
  // ���������a = null,b != null; size(a) + size(b) %2 == 0;size(a) < size(b)
  @Test void testArrayNullAndSumEven() {
    int[] a = null;
    int[] b = { 1, 2, 3, 4 };
    FindMedianSortedArrays findMedianSortedArrays =
        new FindMedianSortedArrays();
    double res = findMedianSortedArrays.findMedianSortedArrays(a, b);
    assertEquals(2.5, res);
  }

  // ����A���գ�BΪ�գ�A��B�����С��Ϊ������A��СС��B
  // ���������a != null, b == null; size(a) + size(b) % 2 == 1; size(a) > size(b)
  @Test void testArrayNullAndSumOdd() {
    int[] b = null;
    int[] a = { 1, 2, 3 };
    FindMedianSortedArrays findMedianSortedArrays =
        new FindMedianSortedArrays();
    double res = findMedianSortedArrays.findMedianSortedArrays(a, b);
    assertEquals(2, res);
  }

  // ����A��B�ǿգ�A��B��С��Ϊż����A��С����B������A���ֵ����B���ֵ����СֵС��B��Сֵ
  // ���������a != null && b != null; size(a) + size(b) % 2 == 0; size(a) ==
  // size(b);
  // max(a) > max(b), min(a) < min(b)
  @Test void testNonnullAndSumEven() {
    int[] a = { 1, 2, 7 };
    int[] b = { 4, 5, 6 };
    FindMedianSortedArrays findMedianSortedArrays =
        new FindMedianSortedArrays();
    double res = findMedianSortedArrays.findMedianSortedArrays(a, b);
    assertEquals(4.5, res);
  }

  // ����A��B�ǿգ�A��B��С��Ϊ������A��С����B������A���ֵС��B���ֵ����Сֵ����B��Сֵ
  // ���������a != null && b != null; size(a) + size(b) % 2 == 1; size(a) > size(b);
  // max(a) <= max(b), min(a) >= min (b);

  @Test void testNonnullAndSumOdd() {
    int[] a = { 1, 2, 3 };
    int[] b = { 0, 5 };
    FindMedianSortedArrays findMedianSortedArrays =
        new FindMedianSortedArrays();
    double res = findMedianSortedArrays.findMedianSortedArrays(a, b);
    assertEquals(2, res);
  }

}
