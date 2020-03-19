package P3;

public class Position {

    private int px = -1;
    private int py = -1;

    /**
     * ������������
     * 
     * @param px ������
     * @param py ������
     */
    public void setPosition(int px, int py) {
        this.px = px;
        this.py = py;
    }

    /**
     * �õ�������
     * 
     * @return ����ֵΪ������
     */
    public int getPx() {
        return px;
    }

    /**
     * �õ�������
     * 
     * @return ������
     */
    public int getPy() {
        return py;
    }

    /**
     * �ж�position�͵�ǰλ���Ƿ��غ�
     * 
     * @param position ���жϵ�����λ��
     * @return true��ʾ�غϣ������غ�
     */
    public boolean positionEqual(Position position) {
        if (px == position.getPx()) {
            if (py == position.getPy()) {
                return true;
            }
        }
        return false;
    }

    @Override public String toString() {
        return "(" + px + "," + py + ")";
    }

    /**
     * ��������Ƿ�Խ�磬�������̷�Χ��
     * 
     * @param gametype gameType == 1��ʾΪ���壬����ΪΧ��
     * @return ����ֵΪtrue��ʾ�����̷�Χ��
     */
    public Boolean checkPosition(int gametype) {
        if (gametype == 2) {// Χ��
            return px >= 0 && px <= 18 && py >= 0 && py <= 18;
        } // ����
        return px >= 0 && px <= 7 && py >= 0 && py <= 7;
    }

}
