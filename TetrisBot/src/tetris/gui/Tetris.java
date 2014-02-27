package tetris.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

import tetris.input.KeyboardInput;
import tetris.model.BoardCell;
import tetris.model.Game;
import tetris.model.PieceType;

public class Tetris extends Canvas {

    private Game game = new Game();
    private final BufferStrategy strategy;

    private final int BOARD_CORNER_X = 300;
    private final int BOARD_CORNER_Y = 50;

    private final KeyboardInput keyboard = new KeyboardInput();
    private long lastIteration = System.currentTimeMillis();

    private static final int PIECE_WIDTH = 20;


    public Tetris() {
        JFrame container = new JFrame("Tetris");
        JPanel panel = (JPanel) container.getContentPane();
        panel.setPreferredSize(new Dimension(800, 600));
        panel.setLayout(null);

        setBounds(0, 0, 800, 600);
        panel.add(this);
        setIgnoreRepaint(true);

        container.pack();
        container.setResizable(false);
        container.setVisible(true);

        container.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        addKeyListener(keyboard);
        requestFocus();

        createBufferStrategy(2);
        strategy = getBufferStrategy();
    }

    void gameLoop() {
        while (true) {
            if (keyboard.newGame()) {
                game = new Game();
                game.startGame();
            }
            if (game.isPlaying()) {

                if (!game.isPaused()) {
                    tetrisLoop();
                }
                if (keyboard.pauseGame()) {
                    game.pauseGame();
                }
                try {
                    Thread.sleep(20);
                } catch (Exception e) { }
            }
            draw();
        }
    }

    void tetrisLoop() {
        if (game.isDropping()) {
            game.moveDown();
        } else if (System.currentTimeMillis() - lastIteration >= game.getIterationDelay()) {
            game.moveDown();
            lastIteration = System.currentTimeMillis();
        }
        if (keyboard.rotate()) {
            game.rotate();
        } else if (keyboard.left()) {
            game.moveLeft();
        } else if (keyboard.right()) {
            game.moveRight();
        } else if (keyboard.drop()) {
            game.drop();
        }
    }

    public void draw() {
        Graphics2D g = getGameGraphics();
        drawEmptyBoard(g);
        drawHelpBox(g);
        drawPiecePreviewBox(g);

        if (game.isPlaying()) {
            drawCells(g);
            drawPiecePreview(g, game.getNextPiece().getType());

            if (game.isPaused()) {
                drawGamePaused(g);
            }
        }

        if (game.isGameOver()) {
            drawCells(g);
            drawGameOver(g);
        }

        drawStatus(g);
        drawPlayTetris(g);

        g.dispose();
        strategy.show();
    }

    private Graphics2D getGameGraphics() {
        return (Graphics2D) strategy.getDrawGraphics();
    }

    private void drawCells(Graphics2D g) {
        BoardCell[][] cells = game.getBoardCells();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                drawBlock(g, BOARD_CORNER_X + i * 20, BOARD_CORNER_Y + (19 - j) * 20, getBoardCellColor(cells[i][j]));
            }
        }
    }

    private void drawEmptyBoard(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 800, 600);
        g.setColor(Color.GRAY);
        g.drawRect(BOARD_CORNER_X - 1, BOARD_CORNER_Y - 1, 10 * PIECE_WIDTH + 2, 20 * PIECE_WIDTH + 2);
    }

    private void drawStatus(Graphics2D g) {
        g.setFont(new Font("Dialog", Font.PLAIN, 16));
        g.setColor(Color.RED);
        g.drawString(getLevel(), 10, 20);
        g.drawString(getLines(), 10, 40);
        g.drawString(getScore(), 20, 80);
    }

    private void drawGameOver(Graphics2D g) {
        Font font = new Font("Dialog", Font.PLAIN, 16);
        g.setFont(font);
        g.setColor(Color.RED);
        g.drawString("GAME OVER", 350, 550);
    }

    private void drawGamePaused(Graphics2D g) {
        Font font = new Font("Dialog", Font.PLAIN, 16);
        g.setFont(font);
        g.setColor(Color.YELLOW);
        g.drawString("GAME PAUSED", 350, 550);
    }


    private void drawPlayTetris(Graphics2D g) {
        Font font = new Font("Dialog", Font.PLAIN, 16);
        g.setFont(font);
        g.setColor(Color.RED);
        g.drawString("Play TETRIS !", 350, 500);
    }

    private String getLevel() {
        return String.format("Your level: %1s", game.getLevel());
    }

    private String getLines() {
        return String.format("Full lines: %1s", game.getLines());
    }

    private String getScore() {
        return String.format("Score     %1s", game.getTotalScore());
    }

    private void drawPiecePreviewBox(Graphics2D g) {
        g.setFont(new Font("Dialog", Font.PLAIN, 16));
        g.setColor(Color.RED);
        g.drawString("Next:", 50, 420);
    }

    private void drawHelpBox(Graphics2D g) {
        g.setFont(new Font("Dialog", Font.PLAIN, 16));
        g.setColor(Color.RED);
        g.drawString("H E L P", 50, 140);
        g.drawString("F1: Pause Game", 10, 160);
        g.drawString("F2: New Game", 10, 180);
        g.drawString("UP: Rotate", 10, 200);
        g.drawString("ARROWS: Move left/right", 10, 220);
        g.drawString("SPACE: Drop", 10, 240);
    }

    private void drawPiecePreview(Graphics2D g, PieceType type) {
        for (Point p : type.getPoints()) {
            drawBlock(g, 60 + p.x * PIECE_WIDTH, 380 + (3 - p.y) * 20, getPieceColor(type));
        }
    }

    private Color getBoardCellColor(BoardCell boardCell) {
        if (boardCell.isEmpty()) {
            return Color.BLACK;
        }
        return getPieceColor(boardCell.getPieceType());
    }

    private Color getPieceColor(PieceType pieceType) {
        switch (pieceType) {
            case I:
                return Color.RED;
            case J:
                return Color.GRAY;
            case L:
                return Color.CYAN;
            case O:
                return Color.BLUE;
            case S:
                return Color.GREEN;
            default:
                return Color.MAGENTA;
        }
    }

    private void drawBlock(Graphics g, int x, int y, Color color) {
        g.setColor(color);
        g.fillRect(x, y, PIECE_WIDTH, PIECE_WIDTH);
        g.drawRect(x, y, PIECE_WIDTH, PIECE_WIDTH);
    }

    public static void main(String[] args) {
        new Tetris().gameLoop();
    }

}
