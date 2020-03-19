package debug;

/**
 * Given two ordered integer arrays nums1 and nums2, with size m and n
 * Find out the median (double) of the two arrays.
 * You may suppose nums1 and nums2 cannot be null at the same time.
 * 
 * <p>Example 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * The output would be 2.0
 * 
 * <p>Example 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * The output would be 2.5
 * 
 * <p>Example 3:
 * nums1 = [1, 1, 1]
 * nums2 = [5, 6, 7]
 * The output would be 3.0
 * 
 * <p>Example 4:
 * nums1 = [1, 1]
 * nums2 = [1, 2, 3]
 * The output would be 1.0
 */

public class FindMedianSortedArrays {

  /**
   * �ҵ����������������λ��.
   * 
   * @param  a ��������a
   * @param  b ��������b
   * @return   ������λ��
   */
  public double findMedianSortedArrays(int[] a, int[] b) {
    /*----->>>�˴��޸�<<<------*/
    /* ����AΪ�գ�B��Ϊ�յ���� */
    if (a == null && b != null) {
      int n = b.length;
      return (b[n / 2] + b[(n - 1) / 2]) / 2.0;
    }
    /*----->>>�˴��޸�<<<------*/
    /* ����BΪ�գ�A��Ϊ�յ���� */
    if (b == null && a != null) {
      int n = a.length;
      return (a[n / 2] + a[(n - 1) / 2]) / 2.0;
    }
    int m = a.length;
    int n = b.length;
    if (m > n) { // ����A���С���飬B��Ŵ�����
      int[] temp = a;
      a = b;
      b = temp;
      int tmp = m;
      m = n;
      n = tmp;
    }
    int imin = 0;
    int imax = m;
    int halfLen = (m + n) / 2;
    while (imin <= imax) {
      int i = (imin + imax + 1) / 2;
      int j = halfLen - i;
      if (i < imax && b[j - 1] > a[i]) {
        imin = i + 1;
      } else if (i > imin && a[i - 1] > b[j]) {
        imax = i - 1;
      } else {
        int maxLeft = 0;
        if (i == 0) {
          maxLeft = b[j - 1];
        } else if (j == 0) {
          maxLeft = a[i - 1];
        } else {
          maxLeft = Math.max(a[i - 1], b[j - 1]);
        }
        int minRight = 0;
        if (i == m) {
          minRight = b[j];
        } else if (j == n) {
          minRight = a[i];
        } else {
          minRight = Math.min(b[j], a[i]);
        }
        /*----->>>�˴��޸�<<<------*/
        /*
         * ������A��B���Ⱥ�Ϊ����ʱ��ֱ�ӷ��صõ���minRight�������Ƿ�����λ��ǰ���һ������
         */
        if ((m + n) % 2 != 0) {
          return minRight;
        }
        return (maxLeft + minRight) / 2.0;
      }
    }
    return 0.0;
  }

}
