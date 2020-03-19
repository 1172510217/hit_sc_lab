package P3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {

    private Map<String, String> map = new HashMap<String, String>();
    private static Board goBoard = new Board(2);// Χ������
    private static Board chessBoard = new Board(1);// ��������
    private Player player1;// ѡ��1
    private Player player2;// ѡ��2
    private static List<Piece> allPieces1 = new ArrayList<>();// ���һ����������
    private static List<Piece> allPieces2 = new ArrayList<>();// ��Ҷ�����������
    private String player1History = "";// ���һ����Ϸ��ʷ
    private String player2History = "";// ��Ҷ�����Ϸ��ʷ

    /**
     * ��������壬����ӳ�ʼ����32������
     * 
     * @param gameTye gameType == 1��������
     */
    public Game(int gameTye) {
        if (gameTye == 1) {// �������壬���ʼ����Ϸ������32����ʼ������
            allPieces1.add(new Piece(0, 0, "Rook", true));
            allPieces1.add(new Piece(0, 1, "Knight", true));
            allPieces1.add(new Piece(0, 2, "Bishop", true));
            allPieces1.add(new Piece(0, 3, "Queen", true));
            allPieces1.add(new Piece(0, 4, "King", true));
            allPieces1.add(new Piece(0, 5, "Bishop", true));
            allPieces1.add(new Piece(0, 6, "Knight", true));
            allPieces1.add(new Piece(0, 7, "Rook", true));
            map.put("Rook", "��");
            map.put("Knight", "��");
            map.put("Bishop", "��");
            map.put("King", "��");
            map.put("Queen", "��");
            map.put("Pawn", "��");

            for (int i = 0; i < 8; i++) {
                allPieces1.add(new Piece(1, i, "Pawn", true));
                allPieces2.add(new Piece(6, i, "Pawn", false));
            }

            allPieces2.add(new Piece(7, 0, "Rook", false));
            allPieces2.add(new Piece(7, 1, "Knight", false));
            allPieces2.add(new Piece(7, 2, "Bishop", false));
            allPieces2.add(new Piece(7, 3, "Queen", false));
            allPieces2.add(new Piece(7, 4, "King", false));
            allPieces2.add(new Piece(7, 5, "Bishop", false));
            allPieces2.add(new Piece(7, 6, "Knight", false));
            allPieces2.add(new Piece(7, 7, "Rook", false));
        }
    }

    /**
     * ���Χ��˵�
     */
    public void showGoMenu() {
        System.out.println("��������ѡ��Ҫʵ�ֵĹ���");
        System.out.println(
                "********************************************************************************************");
        System.out.println("1:����\t2:����\t3:����\t4:����\t5:����\t6:��ʷ\t7:��ѯռ��  8:��ѯ��������");
        System.out.println(
                "********************************************************************************************");
    }

    /**
     * �������˵�
     */
    public void showChessMenu() {
        System.out.println("��������ѡ��Ҫʵ�ֵĹ���");
        System.out.println(
                "********************************************************************************************");
        System.out.println("1:����\t2:����\t3:����\t4:����\t5:����\t6:��ʷ\t7:��ѯռ��  8:��ѯ��������");
        System.out.println(
                "********************************************************************************************");
    }

    /**
     * ��Χ������������
     * 
     * @param px     ���ӵĺ�����
     * @param py     ���ӵ�������
     * @param player ִ�����ӵ�ѡ�֣�true��ʾ����һ
     * @return ������true�����ʾ����λ���ʺ����ӣ������ʾ�Ƿ�����
     */
    public boolean placePiece(int px, int py, Boolean player) {
        if (!checkPlacePiece(px, py)) {// ��Ϊ�Ƿ����꣬��ֱ�ӷ���false
            return false;
        }
        Piece piece = new Piece(px, py, "goPiece", player);
        if (player) {// true�������һ
            player1History += "����:" + "(" + px + "," + py + ") ";
            allPieces1.add(piece);
        } else {// false������Ҷ�
            player2History += "����:" + "(" + px + "," + py + ") ";
            allPieces2.add(piece);
        }
        goBoard.setGoBoard(piece);// �������ӱ�ʾ�������ϣ���Ӧ��������Ϊ�ѱ�ռ��
        return true;
    }

    /**
     * �������������Ƿ�Ϸ�
     * 
     * @param px �����ӵĺ�����
     * @param py �����ӵ�������
     * @return ����ֵΪtrue����ʾ����Ϸ�����������Ƿ�
     */
    public Boolean checkPlacePiece(int px, int py) {
        Position position = new Position();
        position.setPosition(px, py);
        if (position.checkPosition(2)) {// ��������Ƿ�����Χ��Ĳ��������̷�Χ��Ҫ��
            if (!goBoard.getOccupation(position, 2)) {// ����Ƿ���ռ�����
                return true;
            }
            System.out.println("����Ƿ�����ǰ����λ���������ӣ�");
            return false;
        }
        System.out.println("����Ƿ����������̷�Χ��");
        return false;
    }

    /**
     * ��Χ������������
     * 
     * @param px     �����ӵĺ�����
     * @param py     �����ӵ�������
     * @param player true��ʾΪ����һ������Ϊ���ֶ�
     * @return ����true��ʾΪ��������Ϸ������򲻺Ϸ�
     */
    public boolean extractPiece(int px, int py, Boolean player) {
        if (!checkExtractPiece(px, py, player)) {// ������Ƿ����򷵻�false
            return false;
        }
        Piece piece = new Piece(px, py, "goPiece", player);
        if (player) {
            player1History += "����:" + "(" + px + "," + py + ") ";
            for (int i = 0; i < allPieces2.size(); i++) {// ����Ҫɾ��ԭ��������
                if (allPieces2.get(i).getPiecePosition().getPx() == px
                        && allPieces2.get(i).getPiecePosition().getPy() == py) {
                    allPieces2.remove(i);
                    break;
                }
            }
        } else {
            player2History += "����:" + "(" + px + "," + py + ") ";
            for (int i = 0; i < allPieces1.size(); i++) {// ����Ҫɾ��ԭ��������
                if (allPieces1.get(i).getPiecePosition().getPx() == px
                        && allPieces1.get(i).getPiecePosition().getPy() == py) {
                    allPieces1.remove(i);
                    break;
                }
            }
        }
        goBoard.setGoBoard(piece);// ������������λ�ñ��Ϊpalyerռ��
        return true;
    }

    /**
     * ��������λ�������Ƿ�Ϸ�
     * 
     * @param px     �����Ӻ�����
     * @param py     ������������
     * @param player ���������
     * @return ����Ϊtrue����ʾ����Ϸ�������Ƿ�
     */
    public Boolean checkExtractPiece(int px, int py, Boolean player) {
        Position position = new Position();
        position.setPosition(px, py);
        if (position.checkPosition(2)) {// �������λ���Ƿ�δ�����������̷�Χ
            if (goBoard.getOccupation(position, 2)) {// ���Ŀ��λ���Ƿ�ռ��
                if (goBoard.getOccupationPlayer(position, 2) != player) {// ���Ŀ��λ���Ƿ񱻶������ռ��
                    return true;
                }
                System.out.println("����Ƿ�����ǰ����λ�����ӱ�����ռ�ã�");
                return false;
            }
            System.out.println("����Ƿ�����ǰ����λ��δ��ռ�ã�");
            return false;
        }
        System.out.println("����Ƿ������곬�����̷�Χ��");
        return false;
    }

    /**
     * ��������
     * 
     * @param pxOld  ���ӵ�ԭ����ĺ�����
     * @param pyOld  ����ԭ�����������
     * @param pxNew  ����������ĺ�����
     * @param pyNew  �����������������
     * @param player �������
     * @return ����Ϊtrue����ʾ����Ϸ�������Ƿ�
     */
    public Boolean movePiece(int pxOld, int pyOld, int pxNew, int pyNew, boolean player) {
        Action checkAction = new Action();
        if (!checkAction.checkMovePiece(pxOld, pyOld, pxNew, pyNew, player, chessBoard)) {
            return false;// ��������Ƿ�Ϸ�
        }
        if (player) {
            player1History += "����:(" + pxOld + "," + pyOld + ")" + "->(" + pxNew + "," + pxOld + ") ";

        } else {
            player2History += "����:(" + pxOld + "," + pyOld + ")" + "->(" + pxNew + "," + pxOld + ") ";
        }
        Position position = new Position();
        position.setPosition(pxOld, pyOld);
        Piece piece = getPieceByPosition(position);// �õ���������piece
        chessBoard.deleteChessBoardPiece(piece);// �������ϵ�ӡ�Ǳ��Ϊ��Ȼ״̬
        piece.setPiecePosition(pxNew, pyNew);// �������Ӹ���״̬
        chessBoard.addChessBoradPiece(piece);// �������ϵ�ӡ�Ǳ��Ϊ����״̬
        return true;
    }

    /**
     * �������
     * 
     * @param pxOld  ���ӵ�ԭ����ĺ�����
     * @param pyOld  ����ԭ�����������
     * @param pxNew  ����������ĺ�����
     * @param pyNew  �����������������
     * @param player �������
     * @return ����Ϊtrue����ʾ����Ϸ�������Ƿ�
     */
    public Boolean eatPiece(int pxOld, int pyOld, int pxNew, int pyNew, boolean player) {
        Action checkAction = new Action();
        if (!checkAction.checkEatPiece(pxOld, pyOld, pxNew, pyNew, player, chessBoard)) {
            return false;// �����������Ƿ����
        }
        Position position = new Position();
        position.setPosition(pxNew, pyNew);
        Piece piece = getPieceByPosition(position);// �õ���������piece
        if (player) {
            player1History += "����:(" + pxOld + "," + pyOld + ")" + "->(" + pxNew + "," + pxOld + ") ";
            allPieces2.remove(piece);
        } else {
            player2History += "����:(" + pxOld + "," + pyOld + ")" + "->(" + pxNew + "," + pxOld + ") ";
            allPieces1.remove(piece);
        }
        chessBoard.deleteChessBoardPiece(piece);// �������ϵ�ӡ�Ǳ��Ϊ��Ȼ״̬
        piece.setPlayer(player);
        chessBoard.addChessBoradPiece(piece);// �������ϵ�ӡ�Ǳ��Ϊ����״̬
        position.setPosition(pxOld, pyOld);
        piece = getPieceByPosition(position);// �õ���������piece
        chessBoard.deleteChessBoardPiece(piece);// ����������ʼλ�õ�״̬���Ϊ��Ȼ״̬
        piece.setPiecePosition(pxNew, pyNew);
        return true;
    }

    /**
     * function:�������������ʷ��¼
     * 
     * @param player �������ʷ��¼������
     */
    public void showHistory(Boolean player) {
        if (player) {
            System.out.println(player1History);
        } else {
            System.out.println(player2History);
        }
    }

    /**
     * �������һ����
     * 
     * @param string
     */
    public void setPlayer1(String string) {
        player1 = new Player(string);
    }

    /**
     * �õ����һ����
     * 
     * @return �������һ����
     */
    public String getPlayer1() {
        return player1.getPlayerName();
    }

    /**
     * ������Ҷ�����
     * 
     * @param string ��ҵ�����
     */
    public void setPlayer2(String string) {
        player2 = new Player(string);
    }

    /**
     * �õ���Ҷ�������
     * 
     * @return ������Ҷ�������
     */
    public String getPlayer2() {
        return player2.getPlayerName();
    }

    /**
     * �õ�����
     * 
     * @param gameType == 1��������������̣�����ΪΧ������
     * @return ����ֵΪ����Board
     */
    public Board getBoard(int gameType) {
        if (gameType == 1) {
            return chessBoard;
        } else {
            return goBoard;
        }
    }

    /**
     * �õ�������Ŀ
     * 
     * @param player ���1��2
     * @return �������1��2��������Ŀ
     */
    public int getPiecesSize(Boolean player) {
        if (player) {
            return allPieces1.size();
        }
        return allPieces2.size();

    }

    /**
     * ����Position�õ���Ӧλ�õ�����
     * 
     * @param position λ����
     * @return ����Position��Ӧ������
     */
    public Piece getPieceByPosition(Position position) {
        int size1 = getPiecesSize(true);
        int size2 = getPiecesSize(false);
        for (int i = 0; i < size1; i++) {// �������е����ӣ��ҵ�����λ�������
            if (allPieces1.get(i).getPiecePosition().positionEqual(position)) {
                return allPieces1.get(i);
            }
        }
        for (int i = 0; i < size2; i++) {
            if (allPieces2.get(i).getPiecePosition().positionEqual(position)) {
                return allPieces2.get(i);
            }
        }
        return null;
    }

    /**
     * �ı������ʷ
     * 
     * @param string �������ʷ�ϼ���string
     * @param player ���ı�������ʷ��true����ı����һ����ʷ
     */
    public void changeHistory(String string, Boolean player) {
        if (player) {
            player1History += string;
        } else {
            player2History += string;
        }
    }

    /**
     * �������
     * 
     * @param gameType gameType == 1��������������̣��������Χ������
     */
    public void printBoard(int gameType) {
        if (gameType == 1) {// �����������
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Position position = new Position();
                    position.setPosition(i, j);
                    if (chessBoard.getOccupation(position, gameType)) {
                        if (chessBoard.getOccupationPlayer(position, gameType)) {
                            System.out.print("1:" + map.get(this.getPieceByPosition(position).getPieceName()) + "\t");
                        } else {
                            System.out.print("2:" + map.get(this.getPieceByPosition(position).getPieceName()) + "\t");
                        }
                    } else {
                        System.out.print(" �� \t");
                    }
                }
                System.out.println();
            }
            System.out.println("Player1:" + this.getPlayer1() + "\tPlayer2:" + this.getPlayer2());
        } else {// ���Χ������
            for (int i = 0; i < 19; i++) {
                for (int j = 0; j < 19; j++) {
                    Position position = new Position();
                    position.setPosition(i, j);
                    if (goBoard.getOccupation(position, gameType)) {
                        if (goBoard.getOccupationPlayer(position, gameType)) {
                            System.out.print("�� ");
                        } else {
                            System.out.print("�� ");
                        }
                    } else {
                        System.out.print("�� ");
                    }
                }
                System.out.println();
            }
            System.out.println("Player1:" + this.getPlayer1() + "\tPlayer2:" + this.getPlayer2());
        }
    }
}
