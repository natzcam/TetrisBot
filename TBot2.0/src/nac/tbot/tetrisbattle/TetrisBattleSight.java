/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nac.tbot.tetrisbattle;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.Timer;
import nac.tbot.Board;
import nac.tbot.Sight;
import nac.tbot.SightReactor;

/**
 *
 * @author user
 */
public class TetrisBattleSight implements Sight {

  public static final Color lightBlue = new Color(50, 190, 250);
  public static final Color purple = new Color(210, 76, 173);
  public static final Color yellow = new Color(255, 194, 37);
  public static final Color blue = new Color(68, 100, 233);
  public static final Color orange = new Color(255, 126, 37);
  public static final Color green = new Color(124, 212, 36);
  public static final Color red = new Color(250, 50, 90);
  public static final Color lightBlack = new Color(47, 47, 47);
  public static final Color lightestBlack = new Color(77, 77, 77);
  public static final Color darkBlack = new Color(43, 43, 43);
  public static final int ROW_COUNT = 20;
  public static final int COL_COUNT = 10;
  public static final int GRID_HEIGHT = 18;
  public static final int GRID_WIDTH = 18;
  public static final int GRID_HEIGHT_HALF = 9;
  public static final int GRID_WIDTH_HALF = 9;
  public static final Dimension BOARD_DIMENSION = new Dimension(COL_COUNT * GRID_WIDTH + 1, ROW_COUNT * GRID_HEIGHT + 2);

  private final Timer timer;

  private SightOptionPanel sightOptionPanel;
  private Robot robot;
  private Rectangle focus;
  private SightReactor sightReactor;
  private boolean active = false;

  public TetrisBattleSight() {
    try {
      robot = new Robot();
    } catch (AWTException e) {
      e.printStackTrace();
    }
    timer = new Timer(30, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (active) {
          visualize();
        }
      }
    });
    timer.start();
  }

  @Override
  public void setSightReactor(SightReactor sightReactor) {
    this.sightReactor = sightReactor;
  }

  private void visualize() {
    BufferedImage rawImg;
    if (robot != null && focus != null) {

      rawImg = robot.createScreenCapture(focus);

      for (int j = 0; j < COL_COUNT; j++) {

        int py = GRID_HEIGHT_HALF;
        int px = j * GRID_WIDTH + GRID_WIDTH_HALF;

        int rgb = rawImg.getRGB(px, py);
        int np = getPieceIndex(new Color(rgb));
        if (np != 0 && sightReactor != null) {
          sightReactor.newPiece(np);
          return;
        }

      }

    }
  }

  @Override
  public Board getBoard() {
    Board board = null;
    BufferedImage rawImg;
    if (robot != null && focus != null) {

      rawImg = robot.createScreenCapture(focus);

      int[] bd = new int[ROW_COUNT];
      bd[19] = 0;
      //start from 18 discount 19
      for (int i = 1; i < ROW_COUNT; i++) {
        StringBuilder sb = new StringBuilder();

        for (int j = 0; j < COL_COUNT; j++) {

          int py = i * GRID_HEIGHT + GRID_HEIGHT_HALF;
          int px = j * GRID_WIDTH + GRID_WIDTH_HALF;

          int rgb = rawImg.getRGB(px, py);

          if (getPieceIndex(new Color(rgb)) != 0) {
            sb.append("1");
          } else {
            sb.append("0");
          }

        }

        bd[ROW_COUNT - 1 - i] = Integer.parseInt(sb.reverse().toString(), 2);
      }

      board = new Board(bd, ROW_COUNT, COL_COUNT);
    }
    return board;
  }

  @Override
  public JPanel getOption() {
    if (sightOptionPanel == null) {
      sightOptionPanel = new SightOptionPanel(this);
    }
    return sightOptionPanel;
  }

  public void setLocation(Point loc) {
    this.focus = new Rectangle(loc, BOARD_DIMENSION);
  }

  @Override
  public void setActive(boolean active) {
    this.active = active;
  }

  public static int getPieceIndex(Color rgb) {
    double[] dists = new double[]{getColorDistance(lightestBlack, rgb), getColorDistance(lightBlue, rgb), getColorDistance(purple, rgb), getColorDistance(yellow, rgb), getColorDistance(blue, rgb), getColorDistance(orange, rgb), getColorDistance(green, rgb), getColorDistance(red, rgb)};
    int result = 0;
    for (int i = 1; i < dists.length; i++) {
      if (dists[i] < dists[result]) {
        result = i;
      }
    }
    return result;
  }

  private static double getColorDistance(Color c1, Color c2) {
    return (c1.getRed() - c2.getRed()) * (c1.getRed() - c2.getRed())
            + (c1.getGreen() - c2.getGreen()) * (c1.getGreen() - c2.getGreen())
            + (c1.getBlue() - c2.getBlue()) * (c1.getBlue() - c2.getBlue());
  }

}
