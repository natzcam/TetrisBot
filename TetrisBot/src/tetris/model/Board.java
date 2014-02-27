package tetris.model;

import java.awt.Point;

/**
 * 20 [ ][ ][ ][X][X][X][X][ ][ ][ ]
 * 19 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
 * 18 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
 * 17 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
 * 16 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
 * 15 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
 * 14 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
 * 13 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
 * 12 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
 * 11 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
 * 10 [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
 * 9  [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
 * 8  [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
 * 7  [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
 * 6  [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
 * 5  [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
 * 4  [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
 * 3  [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
 * 2  [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
 * 1  [ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]
 *     1  2  3  4  5  6  7  8  9 10
 */
public class Board {

    private static final int DROP_X = 5;
    private static final int DROP_Y = 19;

    private static final int WIDTH = 10;
    private static final int HEIGHT = 20;

    private Point pieceCenter = new Point(DROP_X, DROP_Y);

    private Piece currentPiece;

    private BoardCell[][] board = new BoardCell[WIDTH][HEIGHT];

    private int fullLines = 0;

    public Board() {
        board = createEmptyBoard();
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public int getFullLines() {
        return fullLines;
    }

    public BoardCell getBoardAt(int x, int y) {
        return board[x][y];
    }

    private boolean isRowFull(int y) {
        for (int x = 0; x < WIDTH; x++) {
            if (getBoardAt(x, y).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public void removeFullRows() {
        BoardCell[][] boardX = createEmptyBoard();

        int full = 0;
        for (int y = 0; y < HEIGHT; y++) {
            if (isRowFull(y)) {
                full++;
            } else {
                copyRow(boardX, y, y-full);
            }
        }

        fullLines += full;
        board = boardX;
    }

    private void copyRow(BoardCell[][] to, int y, int toy) {
        for (int x = 0; x < WIDTH; x++) {
            to[x][toy] = board[x][y];
        }
    }

    private BoardCell[][] createEmptyBoard() {
        BoardCell[][] boardX = new BoardCell[WIDTH][HEIGHT];

        for (int x = 0; x < WIDTH; x++) {
            boardX[x] = BoardCell.getEmptyArray(HEIGHT);
        }
        return boardX;
    }

    public void rotate() {
        Piece rot = currentPiece.rotate();
        if (fit(rot.getPoints(), 0, 0)) {

            currentPiece = rot;
        }
    }

    public void moveLeft() {
        if (fit(currentPiece.getPoints(), -1, 0)) {
            mv( -1, 0);
        }
    }

    public void moveRight() {
        if (fit(currentPiece.getPoints(), 1, 0)) {
            mv(1, 0);
        }
    }

    public boolean canCurrentPieceMoveDown() {
        return fit(currentPiece.getPoints(), 0, -1);
    }

    public void moveDown() {
        if (canCurrentPieceMoveDown()) {
            mv(0, -1);
            removeFullRows();
        }
    }

    public boolean fit(Point[] points, int moveX, int moveY) {
        for (Point point : points) {
            int x = pieceCenter.x + point.x + moveX;
            int y = pieceCenter.y + point.y + moveY;

            if (x < 0 || x >= getWidth() || y >= getHeight() || y < 0) {
                return false;
            }

            if (!board[x][y].isEmpty()) {
                return false;
            }
        }

        return true;
    }

    public BoardCell[][] getBoardWithPiece() {
        BoardCell[][] dest = new BoardCell[WIDTH][HEIGHT];

        for (int y = 0; y < WIDTH; y++) {
            System.arraycopy(board[y], 0, dest[y], 0, board[0].length);
        }

        // add piece
        for (Point point : currentPiece.getPoints()) {
            int x = point.x + pieceCenter.x;
            int y = point.y + pieceCenter.y;
            dest[x][y] = BoardCell.getCell(currentPiece.getType());
        }

        return dest;
    }

    private void addPieceToBoard() {
        for (Point point : currentPiece.getPoints()) {
            int x = pieceCenter.x + point.x;
            int y = pieceCenter.y + point.y;
            board[x][y] = BoardCell.getCell(currentPiece.getType());
        }
    }

    private void mv(int moveX, int moveY) {
        pieceCenter = new Point(pieceCenter.x + moveX, pieceCenter.y + moveY);
    }

    public void setCurrentPiece(Piece piece) {
        if (currentPiece != null) {
            addPieceToBoard();
        }
        currentPiece = piece;
        resetPieceCenter();
    }

    private void resetPieceCenter() {
        pieceCenter.x = DROP_X;
        pieceCenter.y = DROP_Y;
    }

}
