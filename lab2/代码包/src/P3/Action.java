package P3;

public class Action {

    /**
     * ������ӵ������Ƿ����
     * 
     * @param px         �ɵĺ�����
     * @param py         �ɵ�������
     * @param pxNew      �µĺ�����
     * @param pyNew      �µ�������1
     * @param player     �ж������˭
     * @param chessBoard ��������
     * @return �����������������true������false
     */
    public boolean checkMovePiece(int px, int py, int pxNew, int pyNew, Boolean player, Board chessBoard) {
        Position oldPosition = new Position();
        oldPosition.setPosition(px, py);
        Position newPosition = new Position();
        newPosition.setPosition(pxNew, pyNew);
        if (oldPosition.checkPosition(1)) {// �ж������Ƿ񳬳����̷�Χ
            if (newPosition.checkPosition(1)) {// �ж������Ƿ񳬳����̷�Χ
                if (chessBoard.getOccupation(oldPosition, 1)) {// �ж�ԭ�������Ƿ�ռ��
                    if (chessBoard.getOccupationPlayer(oldPosition, 1) == player) {// �ж�ԭ�������Ƿ񱻸�ѡ��ռ��
                        if (!chessBoard.getOccupation(newPosition, 1)) {// �ж�Ŀ�������Ƿ�ռ��
                            return true;
                        }
                        System.out.println("����Ƿ���Ŀ������λ���������ӣ�");
                        return false;
                    }
                    System.out.println("����Ƿ�����ǰ����λ�����ӷǼ������ӣ�");
                    return false;
                }
                System.out.println("����Ƿ�����ǰ����λ�������ӣ�");
                return false;
            }
            System.out.println("����Ƿ�����ǰ���곬�����̷�Χ��");
            return false;
        }
        System.out.println("����Ƿ���Ŀ�����곬�����̷�Χ��");
        return false;
    }

    /**
     * �����ӵ������Ƿ����
     * 
     * @param px         �ɵĺ�����
     * @param py         �ɵ�������
     * @param pxNew      �µĺ�����
     * @param pyNew      �µ�������
     * @param player     �ж������˭
     * @param chessBoard ��������
     * @return �����������������true������false
     */
    public Boolean checkEatPiece(int px, int py, int pxNew, int pyNew, Boolean player, Board chessBoard) {
        Position oldPosition = new Position();
        oldPosition.setPosition(px, py);
        Position newPosition = new Position();
        newPosition.setPosition(pxNew, pyNew);
        if (oldPosition.checkPosition(1)) {// �ж������Ƿ񳬳����̷�Χ
            if (newPosition.checkPosition(1)) {// �ж������Ƿ񳬳����̷�Χ
                if (chessBoard.getOccupation(oldPosition, 1)) {// �ж�ԭ�������Ƿ�ռ��
                    if (chessBoard.getOccupationPlayer(oldPosition, 1) == player) {// �ж�ԭ�������Ƿ񱻸�ѡ��ռ��
                        if (chessBoard.getOccupation(newPosition, 1)) {// �ж�Ŀ�������Ƿ�ռ��
                            if (chessBoard.getOccupationPlayer(newPosition, 1) != player) {// �ж�Ŀ�����걻��λѡ��ռ��
                                return true;
                            }
                            System.out.println("����Ƿ���Ŀ������λ�÷ǶԷ����ӣ�");
                            return false;
                        }
                        System.out.println("����Ƿ���Ŀ������λ�������ӣ�");
                        return false;
                    }
                    System.out.println("����Ƿ�����ǰ����λ�����ӷǼ������ӣ�");
                    return false;
                }
                System.out.println("����Ƿ�����ǰ����λ�������ӣ�");
                return false;
            }
            System.out.println("����Ƿ���Ŀ�����곬�����̷�Χ��");
            return false;
        }
        System.out.println("����Ƿ�����ǰ���곬�����̷�Χ��");
        return false;
    }
}
