package nac.tbot;

import java.awt.AWTException;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class TPanel extends JPanel {

  public final Dimension d = new Dimension(10 * 18, 20 * 18);

  private Robot robot;
  private Rectangle rect;
  private TState state = TState.STANDBY;
  private final Keyboard keyboard = new Keyboard();
  private BotImpl bot = new BotImpl();
  private Color newPieceColor;
  private int hold = -1;
  private int current = -1;
  private boolean pressedShift = false;
  private int lastRowsRemoved = 0;
  private JTextField delayTextField;
  private JTextField towerGapTextField;
  private JTextField buildLimitTextField;
  private JTextField breakLimitTextField;
  private JLabel statusArea;

  public TPanel() {
    try {
      robot = new Robot();
    } catch (AWTException e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    BufferedImage image = null;
    int height = getHeight();
    int width = getWidth();

    if (rect != null) {
      image = robot.createScreenCapture(rect);
    }

    g2d.setColor(Color.red);
    g2d.setStroke(new BasicStroke(2F));

    int gridHeight = 18;
    int gridWidth = 18;

    for (int i = 1; i < 10; i++) {
      g2d.drawLine(i * gridWidth, 0, i * gridWidth, height);
    }

    for (int i = 1; i < 20; i++) {
      g2d.drawLine(0, i * gridHeight, width, i * gridHeight);
    }

    g2d.setStroke(new BasicStroke(1F));
    int blankcount = 0;

    int[] board = new int[19];

    for (int i = 1, x = 19; i < 21; i++, x--) {
      String rowStr = "";
      for (int j = 1; j < 11; j++) {
        if (image != null) {
          int px = j * gridWidth - 9;
          int py = i * gridHeight - 9;

          int rgb = image.getRGB(px, py);

          System.out.println("TetraminoFactory.getIndexByRBG(new Color(rgb))" + TetraminoFactory.getIndexByRBG(new Color(rgb)));
          if (TetraminoFactory.getIndexByRBG(new Color(rgb)) == -1) {
            g2d.setColor(Color.white);
            int rx = j * gridWidth - 13;
            int ry = i * gridHeight - 13;
            g2d.fillRect(rx, ry, 8, 8);
            blankcount++;
            rowStr += "0";
          } else {
            g2d.setColor(new Color(rgb));
            int rx = j * gridWidth - 13;
            int ry = i * gridHeight - 13;
            g2d.fillRect(rx, ry, 8, 8);
            rowStr += "1";

            if (i == 1 && (state == TState.STARTED || state == TState.WAIT_FOR_PIECE)) {
              state = TState.SENDING_PIECE;
              newPieceColor = new Color(rgb);
            }

          }
        }

      }
      if (!rowStr.isEmpty()) {
        if (x != 19) {
          board[x] = Integer.parseInt(new StringBuffer(rowStr).reverse().toString(), 2);
        }
      }

    }

    if (blankcount == 200) {
      state = TState.STARTED;
      hold = -1;
      pressedShift = false;
      bot = new BotImpl();
      keyboard.setDelay(Integer.parseInt(delayTextField.getText()));
      bot.setTowerGap(Integer.parseInt(towerGapTextField.getText()));
      bot.setBuildUpLimit(Integer.parseInt(buildLimitTextField.getText()));
      bot.setBreakDownLimit(Integer.parseInt(breakLimitTextField.getText()));
    }
    if (state == TState.SENDING_PIECE) {
      current = TetraminoFactory.getIndexByRBG(newPieceColor);

      if (current != -1) {

        if (hold == -1) {
          keyboard.sendShift();
          hold = current;

          pressedShift = true;
        } else {
          Move move1;
          Move move2;
          Move finalMove;

          if (pressedShift == false) {

            move1 = bot.move(new Board(board, 19, 10), current);
            move2 = bot.move(new Board(board, 19, 10), hold);

            if (move1 != null && move2 != null && move1.getScore() >= move2.getScore()) {
              finalMove = move1;
            } else {

              keyboard.sendShift();
              hold = current;

              pressedShift = true;
              finalMove = move2;
            }
          } else {
            finalMove = bot.move(new Board(board, 19, 10), current);
          }

          if (finalMove != null) {
            lastRowsRemoved = finalMove.getRowsRemoved();
            int rotation = finalMove.getRotation();

            while (rotation > 0) {
              keyboard.sendRotate();
              rotation--;
            }

            int column = finalMove.getColumn();
            int offset = column - finalMove.getTetramino().getOffset();

            if (offset > 0) {
              while (offset > 0) {
                keyboard.sendRight();
                offset--;
              }
            } else if (offset < 0) {
              while (offset < 0) {
                keyboard.sendLeft();
                offset++;
              }
            }
            pressedShift = false;
            keyboard.sendSpace();

          }
        }
        state = TState.WAIT_FOR_PIECE;
      } else {
        state = TState.STARTED;
        hold = -1;
        pressedShift = false;
        bot = new BotImpl();
        keyboard.setDelay(Integer.parseInt(delayTextField.getText()));
        keyboard.setDelay(Integer.parseInt(delayTextField.getText()));
        bot.setTowerGap(Integer.parseInt(towerGapTextField.getText()));
        bot.setBuildUpLimit(Integer.parseInt(buildLimitTextField.getText()));
        bot.setBreakDownLimit(Integer.parseInt(breakLimitTextField.getText()));
      }

    }
    if (!statusArea.getText().equals(state.toString())) {
      statusArea.setText(state.toString());
    }
  }

  @Override
  public Dimension getPreferredSize() {
    return d;
  }

  public void setLoc() {
    rect = new Rectangle(getLocationOnScreen(), d);
  }

  public void setLoc(Point p) {
    rect = new Rectangle(p, d);
  }

  public void resetLoc() {
    rect = null;
    state = TState.STANDBY;
  }

  public boolean isSetLoc() {
    return (rect == null) ? false : true;
  }

  public void setDelayTextField(JTextField delayTextField) {
    this.delayTextField = delayTextField;
  }

  public void setTowerGapTextField(JTextField towerGapTextField) {
    this.towerGapTextField = towerGapTextField;
  }

  public void setBuildLimitTextField(JTextField buildLimitTextField) {
    this.buildLimitTextField = buildLimitTextField;
  }

  public void setBreakLimitTextField(JTextField breakLimitTextField) {
    this.breakLimitTextField = breakLimitTextField;
  }

  public void setStatusArea(JLabel statusArea) {
    this.statusArea = statusArea;
  }
}
