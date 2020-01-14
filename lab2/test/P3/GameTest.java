package P3;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

class GameTest {

    // Test strategy for Game.checkPlacePiece
    // ����Խ��+��Խ��
    // ����λ���ѱ�ռ��+δ��ռ��

    @Test void testCheckPlacePiece() {
        Game goGame = new Game(2);
        assertFalse(goGame.checkPlacePiece(-1, 19));// ��������Խ��

        assertTrue(goGame.checkPlacePiece(1, 2));// �������겻Խ�� +δ��ռ��

        goGame.placePiece(1, 2, true);
        assertFalse(goGame.checkPlacePiece(1, 2));// ���������ѱ�ռ��
    }

    // Test strategy for Game.checkExtractPiece
    // ����Խ��+��Խ��
    // ����λ���ѱ�����ռ��+δ������ռ��
    @Test void testCheckExtractPiece() {
        Game goGame = new Game(2);
        assertFalse(goGame.checkExtractPiece(-1, 19, true));// ��������Խ��
        assertFalse(goGame.checkExtractPiece(2, 3, true)); // �������겻Խ��+δ���Է�ռ��
    }

    // Test strategy for Game.movePiece
    // ����Խ��+��Խ��
    // ����λ���ѱ�����ռ��+δ������ռ��
    // Ŀ��λ���ѱ�ռ��+δ��ռ��
    @Test void testMovePiece() {
        Game chessGame = new Game(1);
        assertTrue(chessGame.movePiece(1, 1, 2, 2, true));// ������겻Խ��+����λ�ñ��Լ�ռ��+Ŀ��λ��δ��ռ��
        assertFalse(chessGame.movePiece(10, 0, 1, 1, true));// �������Խ��
        assertFalse(chessGame.movePiece(1, 0, 1, 1, false));// ���Ҫ�ƶ��������Ƕ�������
        assertFalse(chessGame.movePiece(1, 0, 7, 1, true));// ���Ҫ�ƶ�������λ���ѱ�ռ��
    }

    // Test strategy for Game.eatPiece
    // ����Խ��+��Խ��
    // ��ʼ���ӷǼ���+�Ǽ���
    // Ŀ�����ӷǵз�+�ǵз�
    @Test void testEatPiece() {
        Game chessGame = new Game(1);
        assertTrue(chessGame.eatPiece(0, 0, 7, 0, true));// �����ȷ�������
        assertFalse(chessGame.eatPiece(0, 0, 6, 1, false));// �����ʼ���ӷǼ���
        assertFalse(chessGame.eatPiece(0, 0, 10, 0, false));// �����������λ��Խ��
        assertFalse(chessGame.eatPiece(0, 0, 1, 1, true));// ���Ŀ�����ӷǼ���
    }

}
