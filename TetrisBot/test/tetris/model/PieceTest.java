package tetris.model;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.assertEquals;

import java.awt.Point;

import org.junit.Test;

public class PieceTest {

    private void assertPoints(Point[] expected, Piece actual) {
        assertEquals(expected.length, actual.getPoints().length);
        for (Point e : actual.getPoints()) {
            assertTrue(containsPointExactlyOnce(e, expected));
        }
    }

    private boolean containsPointExactlyOnce(Point point, Point[] points) {
        int count = 0;
        for (Point p : points) {
            if (p.x == point.x && p.y == point.y) {
                count++;
            }
        }
        return count == 1;
    }

    @Test
    public void getRandomPieceReturnsValue() {
        assertNotNull(Piece.getRandomPiece());
    }

    @Test
    public void rotatePieceO() {
        Piece piece = Piece.getPiece(PieceType.O);

        Point[] points = new Point[4];
        points[0] = new Point(-1, 0);
        points[1] = new Point(0, 0);
        points[2] = new Point(-1, -1);
        points[3] = new Point(0, -1);
        assertPoints(points, piece);

        assertPoints(points, piece.rotate());
    }

    @Test
    public void rotatePieceI() {
        Piece piece = Piece.getPiece(PieceType.I);

        Point[] points = new Point[4];
        points[0] = new Point(-2, 0);
        points[1] = new Point(-1, 0);
        points[2] = new Point(0, 0);
        points[3] = new Point(1, 0);
        assertPoints(points, piece);

        points[0] = new Point(0, 1);
        points[1] = new Point(0, 0);
        points[2] = new Point(0, -1);
        points[3] = new Point(0, -2);
        piece = piece.rotate();
        assertPoints(points, piece);

        points[0] = new Point(-2, 0);
        points[1] = new Point(-1, 0);
        points[2] = new Point(0, 0);
        points[3] = new Point(1, 0);
        piece = piece.rotate();
        assertPoints(points, piece);
    }

    @Test
    public void rotatePieceS() {
        Piece piece = Piece.getPiece(PieceType.S);

        Point[] points = new Point[4];
        points[0] = new Point(0, 0);
        points[1] = new Point(1, 0);
        points[2] = new Point(-1, -1);
        points[3] = new Point(0, -1);
        assertPoints(points, piece);

        points[0] = new Point(0, 1);
        points[1] = new Point(0, 0);
        points[2] = new Point(1, 0);
        points[3] = new Point(1, -1);
        piece = piece.rotate();
        assertPoints(points, piece);

        points[0] = new Point(0, 0);
        points[1] = new Point(1, 0);
        points[2] = new Point(-1, -1);
        points[3] = new Point(0, -1);
        piece = piece.rotate();
        assertPoints(points, piece);
    }

    @Test
    public void rotatePieceZ() {
        Piece piece = Piece.getPiece(PieceType.Z);

        Point[] points = new Point[4];
        points[0] = new Point(-1, 0);
        points[1] = new Point(0, 0);
        points[2] = new Point(0, -1);
        points[3] = new Point(1, -1);
        assertPoints(points, piece);

        points[0] = new Point(1, 1);
        points[1] = new Point(0, 0);
        points[2] = new Point(1, 0);
        points[3] = new Point(0, -1);
        piece = piece.rotate();
        assertPoints(points, piece);

        points[0] = new Point(-1, 0);
        points[1] = new Point(0, 0);
        points[2] = new Point(0, -1);
        points[3] = new Point(1, -1);
        piece = piece.rotate();
        assertPoints(points, piece);

    }

    @Test
    public void rotatePieceL() {
        Piece piece = Piece.getPiece(PieceType.L);

        // Start
        Point[] points = new Point[4];
        points[0] = new Point(-1, 0);
        points[1] = new Point(0, 0);
        points[2] = new Point(1, 0);
        points[3] = new Point(-1, -1);
        assertPoints(points, piece);

        // 90
        points[0] = new Point(0, 1);
        points[1] = new Point(0, 0);
        points[2] = new Point(0, -1);
        points[3] = new Point(1, -1);
        piece = piece.rotate();
        assertPoints(points, piece);

        // 180
        points[0] = new Point(-1, 0);
        points[1] = new Point(0, 0);
        points[2] = new Point(1, 0);
        points[3] = new Point(1, 1);
        piece = piece.rotate();
        assertPoints(points, piece);

        // 270
        points[0] = new Point(-1, 1);
        points[1] = new Point(0, 1);
        points[2] = new Point(0, 0);
        points[3] = new Point(0, -1);
        piece = piece.rotate();
        assertPoints(points, piece);

        // 360 - Start
        points[0] = new Point(-1, 0);
        points[1] = new Point(0, 0);
        points[2] = new Point(1, 0);
        points[3] = new Point(-1, -1);
        piece = piece.rotate();
        assertPoints(points, piece);
    }

    @Test
    public void rotatePieceJ() {
        Piece piece = Piece.getPiece(PieceType.J);

        // start
        Point[] points = new Point[4];
        points[0] = new Point(-1, 0);
        points[1] = new Point(0, 0);
        points[2] = new Point(1, 0);
        points[3] = new Point(1, -1);
        assertPoints(points, piece);

        // 90
        points[0] = new Point(0, 1);
        points[1] = new Point(1, 1);
        points[2] = new Point(0, 0);
        points[3] = new Point(0, -1);
        piece = piece.rotate();
        assertPoints(points, piece);

        // 180
        points[0] = new Point(-1, 1);
        points[1] = new Point(-1, 0);
        points[2] = new Point(0, 0);
        points[3] = new Point(1, 0);
        piece = piece.rotate();
        assertPoints(points, piece);

        // 270
        points[0] = new Point(0, 1);
        points[1] = new Point(0, 0);
        points[2] = new Point(-1, -1);
        points[3] = new Point(0, -1);
        piece = piece.rotate();
        assertPoints(points, piece);

        // 360
        points[0] = new Point(-1, 0);
        points[1] = new Point(0, 0);
        points[2] = new Point(1, 0);
        points[3] = new Point(1, -1);
        piece = piece.rotate();
        assertPoints(points, piece);
    }

    @Test
    public void rotatePieceT() {
        Piece piece = Piece.getPiece(PieceType.T);

        // Start
        Point[] points = new Point[4];
        points[0] = new Point(-1, 0);
        points[1] = new Point(0, 0);
        points[2] = new Point(1, 0);
        points[3] = new Point(0, -1);
        assertPoints(points, piece);

        // 90
        points[0] = new Point(0, 1);
        points[1] = new Point(0, 0);
        points[2] = new Point(1, 0);
        points[3] = new Point(0, -1);
        piece = piece.rotate();
        assertPoints(points, piece);

        // 180
        points[0] = new Point(0, 1);
        points[1] = new Point(-1, 0);
        points[2] = new Point(0, 0);
        points[3] = new Point(1, 0);
        piece = piece.rotate();
        assertPoints(points, piece);

        // 270
        points[0] = new Point(0, 1);
        points[1] = new Point(-1, 0);
        points[2] = new Point(0, 0);
        points[3] = new Point(0, -1);
        piece = piece.rotate();
        assertPoints(points, piece);

        // 360 - Start
        points[0] = new Point(-1, 0);
        points[1] = new Point(0, 0);
        points[2] = new Point(1, 0);
        points[3] = new Point(0, -1);
        piece = piece.rotate();
        assertPoints(points, piece);
    }

}
