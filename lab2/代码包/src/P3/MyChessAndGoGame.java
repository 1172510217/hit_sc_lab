package P3;

import java.util.Scanner;

public class MyChessAndGoGame {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int player = 0;// �����ж������˭
        System.out.println("����ѡ����Ϸ��chess��go");
        String gametype = in.nextLine();
        int input = 0;
        if (gametype.equals("chess")) {
            Game game = new Game(1);
            System.out.print("����������һ�����֣�");
            String player1 = in.nextLine();
            game.setPlayer1(player1);
            System.out.print("���������ֶ������֣�");
            String player2 = in.nextLine();
            game.setPlayer2(player2);
            game.showChessMenu();
            input = in.nextInt();
            while (true) {// ���϶�ȡ�������Ĺ���ѡ�ֱ���˳���Ϸ
                switch (input) {
                case 1:
                    System.out.println("��������������");
                    if (game.movePiece(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), player % 2 == 0)) {
                        player++;
                    }
                    game.printBoard(1);
                    break;
                case 2:
                    System.out.println("�������������");
                    if (game.eatPiece(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), player % 2 == 0)) {
                        player++;
                    }
                    game.printBoard(1);
                    break;
                case 3:
                    System.out.println("������ѡ��");
                    game.changeHistory("���� ", player % 2 == 0);
                    player++;
                    game.printBoard(1);
                    break;
                case 4:
                    in.close();
                    System.exit(0);
                case 5:
                    game.printBoard(1);
                    break;
                case 6:
                    System.out.println("������Ҫѡ���ѡ��1��2");
                    game.showHistory(in.nextInt() == 1);
                    break;
                case 7:
                    System.out.println("�������ѯ����");
                    Position position = new Position();
                    position.setPosition(in.nextInt(), in.nextInt());
                    game.getBoard(1).checkOccupation(position, 1);
                    break;
                case 8:
                    System.out.println("������Ҫѡ���ѡ��1��2");
                    System.out.println(game.getPiecesSize(in.nextInt() == 1));
                    break;
                default:
                    System.out.println("��������ȷ��");
                }
                game.showChessMenu();
                input = in.nextInt();
            }
        } else if (gametype.equals("go")) {
            Game game = new Game(2);
            System.out.print("����������һ�����֣�");
            String player1 = in.nextLine();
            game.setPlayer1(player1);
            System.out.print("���������ֶ������֣�");
            String player2 = in.nextLine();
            game.setPlayer2(player2);
            game.showGoMenu();
            input = in.nextInt();
            while (true) {// ���϶�ȡ�������Ĺ���ѡ�ֱ���˳���Ϸ
                switch (input) {
                case 1:
                    System.out.println("��������������");
                    if (game.placePiece(in.nextInt(), in.nextInt(), player % 2 == 0)) {
                        player++;
                    }
                    game.printBoard(2);
                    break;
                case 2:
                    System.out.println("��������������");
                    if (game.extractPiece(in.nextInt(), in.nextInt(), player % 2 == 0)) {
                        player++;
                    }
                    game.printBoard(2);
                    break;
                case 3:
                    System.out.println("������ѡ��");
                    game.changeHistory("���� ", player % 2 == 0);
                    player++;
                    break;
                case 4:
                    in.close();
                    System.exit(0);
                case 5:
                    game.printBoard(2);
                    break;
                case 6:
                    System.out.println("������Ҫѡ���ѡ��1��2");
                    game.showHistory(in.nextInt() == 1);
                    break;
                case 7:
                    System.out.println("�������ѯ����");
                    Position position = new Position();
                    position.setPosition(in.nextInt(), in.nextInt());
                    game.getBoard(2).checkOccupation(position, 2);
                    break;
                case 8:
                    System.out.println("������Ҫѡ���ѡ��1��2");
                    System.out.println(game.getPiecesSize(in.nextInt() == 1));
                    break;
                default:
                    System.out.println("��������ȷ��");
                }
                game.showGoMenu();
                input = in.nextInt();
            }
        } else {
            System.out.println("Error input game!");
            in.close();
            System.exit(0);
        }
        in.close();
    }
}
