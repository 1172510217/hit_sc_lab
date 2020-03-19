package P3;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

class BoardTest {

    // Test strategy for deleteChessBoardPiece
    // ɾ������������ʾΪδ��ռ��
    @Test void testDeleteChessBoardPiece() {
        Game chessGame = new Game(1);
        Board chessBoard = new Board(1);
        Position position = new Position();
        position.setPosition(0, 0);
        Piece piece = chessGame.getPieceByPosition(position);
        chessBoard.deleteChessBoardPiece(piece);
        assertFalse(chessBoard.getOccupation(position, 1));
    }

    // Test strategy for deleteChessBoardPiece
    // ��Ӻ����������ʾΪ��ռ��
    @Test void testAddChessBoradPiece() {
        Game chessGame = new Game(1);
        Board chessBoard = new Board(1);
        Position position = new Position();
        position.setPosition(0, 0);
        Piece piece = chessGame.getPieceByPosition(position);
        chessBoard.deleteChessBoardPiece(piece);
        chessBoard.addChessBoradPiece(piece);
        assertTrue(chessBoard.getOccupation(position, 1));
    }

}
