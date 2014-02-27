package tetris.model;

import java.awt.Point;

public class Piece {

    private final Point points[];
    private final PieceType type;
    private final boolean initialOrientation;

    private Piece(PieceType pieceType, Point[] points, boolean initial) {
        initialOrientation = initial;
        this.points = points;
        this.type = pieceType;
    }

    public static Piece getRandomPiece() {
        PieceType pieceType = PieceType.getRandomPiece();
        return new Piece(pieceType, pieceType.getPoints(), true);
    }

    public static Piece getPiece(PieceType pieceType) {
        return new Piece(pieceType, pieceType.getPoints(), true);
    }

    public PieceType getType() {
        return type;
    }

    public Point[] getPoints() {
        return points;
    }

    public Piece rotate() {
        if (type.getMaxOrientations() == 0) {
            return this;
        } else if (type.getMaxOrientations() == 2) {
            if (initialOrientation) {
                return new Piece(type, rotateRight(points), false);
            } else {
                return new Piece(type, rotateLeft(points), true);
            }
        }
        return new Piece(type, rotateRight(points), true);
    }

    private Point[] rotateLeft(Point toRotate[]) {
        return rotate(toRotate, 1, -1);
    }

    private Point[] rotateRight(Point toRotate[]) {
        return rotate(toRotate, -1, 1);
    }

    private Point[] rotate(Point toRotate[], int x, int y) {
        Point rotated[] = new Point[4];

        for (int i = 0; i < 4; i++) {
            int temp = toRotate[i].x;
            rotated[i] = new Point(x * toRotate[i].y, y * temp);
        }

        return rotated;
    }

}