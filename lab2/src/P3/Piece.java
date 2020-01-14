package P3;

public class Piece {

    private Position piecePosition = new Position();// ���ӵ�����
    private String pieceName = new String();// ���ӵ����
    private Boolean player;// ���ӵ�ӵ���ߣ�true��ʾΪ����1

    /**
     * ��ʼ��һ������
     * 
     * @param px        ���ӵĺ�����
     * @param py        ���ӵ�������
     * @param pieceName ���ӵ�����
     * @param player    ���ӵ�ӵ����
     */
    public Piece(int px, int py, String pieceName, Boolean player) {
        this.pieceName = pieceName;
        this.piecePosition.setPosition(px, py);
        this.player = player;
    }

    /**
     * �������ӵ�����
     * 
     * @param px ���ӵĺ�����
     * @param py ���ӵ�������
     */
    public void setPiecePosition(int px, int py) {
        this.piecePosition.setPosition(px, py);
    }

    /**
     * �������ӵ�ӵ����
     * 
     * @param player ����ӵ����ΪPlayer
     */
    public void setPlayer(Boolean player) {
        this.player = player;
    }

    /**
     * �õ�����
     * 
     * @return ��������
     */
    public Position getPiecePosition() {
        return this.piecePosition;
    }

    /**
     * �õ����ӵ�ӵ����
     * 
     * @return �������ӵ�ӵ����
     */
    public Boolean getPlayer() {
        return player;
    }

    /**
     * �õ����ӵ�����
     * 
     * @return �������ӵ�����
     */
    public String getPieceName() {
        return pieceName;
    }

    /**
     * �������ӵ�����
     * 
     * @param pieceName ���ӵ�����
     */
    public void setPieceName(String pieceName) {
        this.pieceName = pieceName;
    }
}
