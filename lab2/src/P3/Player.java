package P3;

public class Player {

    private String playName;// ��ҵ�����

    /**
     * �����������
     * 
     * @param playerName
     */
    public Player(String playerName) {
        this.playName = playerName; // �����������
    }

    /**
     * �����������
     * 
     * @return �������
     */
    public String getPlayerName() {
        return this.playName;// ������ҵ�����
    }
}
