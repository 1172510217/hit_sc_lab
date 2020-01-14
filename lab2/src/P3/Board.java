package P3;

public class Board {

    private Boolean[][] goBoard1 = new Boolean[19][19];// ���ڴ����������Ӵ��������true��ʾ�ѱ�ռ��
    private Boolean[][] goBoard2 = new Boolean[19][19];// ���ڴ�������ռ�������true��ʾplayer1

    private Boolean[][] chessBoard1 = new Boolean[8][8];// ���ڴ����������Ӵ��������true��ʾ�ѱ�ռ��
    private Boolean[][] chessBoard2 = new Boolean[8][8];// ���ڴ�������ռ�������true��ʾplayer1

    /**
     * function����ʼ��Boolean����Ԫ��Ϊfalse����ʾ������������
     */
    public Board(int gameType) {
        if (gameType == 2) {
            for (int i = 0; i < 19; i++) {
                for (int j = 0; j < 19; j++) {
                    goBoard1[i][j] = false;// false��ʾ�˴�������
                    goBoard2[i][j] = false;// false��ʾ�˴�������1ռ��
                }
            }
        } else {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (i >= 0 && i < 2) {
                        chessBoard1[i][j] = true;// false��ʾ�˴�������
                        chessBoard2[i][j] = true;// false��ʾ�˴�������1ռ��
                    } else if (i >= 6 && i < 8) {
                        chessBoard1[i][j] = true;// false��ʾ�˴�������
                        chessBoard2[i][j] = false;// false��ʾ�˴�������1ռ��
                    } else {
                        chessBoard1[i][j] = false;// false��ʾ�˴�������
                        chessBoard2[i][j] = false;// false��ʾ�˴�������1ռ��
                    }
                }
            }
        }
    }

    /**
     * function����ĳ����������������
     * 
     * @param piece ������
     */
    public void setGoBoard(Piece piece) {
        int px = piece.getPiecePosition().getPx();// �õ�λ�ú�����
        int py = piece.getPiecePosition().getPy();// �õ�λ��������
        goBoard1[px][py] = true;
        goBoard2[px][py] = piece.getPlayer();
    }

    /**
     * ��ĳ�����Ӵ����̵Ķ�ά�����ʾ��ɾ��
     * 
     * @param piece ����������
     */
    public void deleteChessBoardPiece(Piece piece) {
        int px = piece.getPiecePosition().getPx();
        int py = piece.getPiecePosition().getPy();
        chessBoard1[px][py] = false;
        chessBoard2[px][py] = false;
    }

    /**
     * ��ĳ�����ӵ�����λ�ñ�ʾ�ڶ�ά����������
     * 
     * @param piece ����������
     */
    public void addChessBoradPiece(Piece piece) {
        int px = piece.getPiecePosition().getPx();
        int py = piece.getPiecePosition().getPy();
        chessBoard1[px][py] = true;
        chessBoard2[px][py] = piece.getPlayer();
    }

    /**
     * ���������жϸ�λ���Ƿ�ռ��
     * 
     * @param position Ҫ�жϵ�λ��
     * @param gameType Ҫ�жϵ���Ϸ����
     * @return ����ֵΪtrue����ʾ�ѱ�ռ�ã�false��ʾδ��ռ��
     */
    public boolean getOccupation(Position position, int gameType) {
        if (gameType == 1) {// ��Ϊ���壬�����chessBoard
            return chessBoard1[position.getPx()][position.getPy()];
        } else {
            return goBoard1[position.getPx()][position.getPy()];
        }
    }

    /**
     *���������жϸ�λ�ñ�˭ռ��
     * @param position Ҫ�жϵ�����λ��
     * @param gameType Ҫ�жϵ���Ϸ����
     * @return ����ֵΪtrue����ʾ��ѡ��һռ�ã�����ѡ�ֶ�ռ��
     */
    public boolean getOccupationPlayer(Position position, int gameType) {
        if (gameType == 1) {//��ʾΪ����
            return chessBoard2[position.getPx()][position.getPy()];
        } else {
            return goBoard2[position.getPx()][position.getPy()];
        }
    }

    /**
     * ���ĳλ�õ�ռ������������Ƿ�ռ���Լ���˭ռ��
     * @param position Ҫ�жϵ�����λ��
     * @param gameType Ҫ�жϵ���Ϸ��1�������壬2����Χ��
     */
    public void checkOccupation(Position position, int gameType) {
        int px = position.getPx();// �õ�λ�ú�����
        int py = position.getPy();// �õ�λ��������
        if (gameType == 2) {
            if (goBoard1[px][py]) {
                System.out.print("��λ��" + position.toString() + "�ѱ�����");
                if (goBoard2[px][py]) {
                    System.out.println("1ռ��");
                } else {
                    System.out.println("2ռ��");
                }
            } else {
                System.out.println("��λ��" + position.toString() + "δ��ռ��");
            }
        } else {
            if (chessBoard1[px][py]) {
                System.out.print("��λ��" + position.toString() + "�ѱ�����");
                if (chessBoard2[px][py]) {
                    System.out.println("1ռ��");
                } else {
                    System.out.println("2ռ��");
                }
            } else {
                System.out.println("��λ��" + position.toString() + "δ��ռ��");
            }
        }
    }
}
