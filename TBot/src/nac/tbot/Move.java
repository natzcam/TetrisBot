/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nac.tbot;

/**
 *
 * @author DTIT
 */
public class Move {

  private int rotation = 0;
  private int column = 0;
  private final Tetramino tetramino;
  private int rowsRemoved = 0;
  private boolean gameOver = false;
  private int landingHeight = 0;
  private double score = 0;

  public Move(Tetramino tetramino) {
    this.tetramino = tetramino;
  }

  public Move(Tetramino tetramino, int rotation, int column) {
    this.tetramino = tetramino;
    this.rotation = rotation;
    this.column = column;
  }

  public int getRotation() {
    return rotation;
  }

  public void setRotation(int rotation) {
    this.rotation = rotation;
  }

  public int getColumn() {
    return column;
  }

  public void setColumn(int column) {
    this.column = column;
  }

  public int getRowsRemoved() {
    return rowsRemoved;
  }

  public boolean isGameOver() {
    return gameOver;
  }

  public void setGameOver(boolean gameOver) {
    this.gameOver = gameOver;
  }

  public void setRowsRemoved(int rowsRemoved) {
    this.rowsRemoved = rowsRemoved;
  }

  public Tetramino getTetramino() {
    return tetramino;
  }

  public int getLandingHeight() {
    return landingHeight;
  }

  public double getScore() {
    return score;
  }

  public void setScore(double score) {
    this.score = score;
  }

  public void setLandingHeight(int landingHeight) {
    this.landingHeight = landingHeight;
  }
  
  public double evaluate(){
    return getLandingRow() * -4.500158825082766 +
    rowsRemoved * 3.4181268101392694;
  }
  
  public double evaluate(int boardHeight, boolean lastMoveRemovedRow, int limit) {
    if (boardHeight > limit) {
      return getLandingRow() * -4.500158825082766
              + (rowsRemoved) * 3.4181268101392694;
    } else {
      if (!lastMoveRemovedRow && rowsRemoved == 1) {
        return -2000000;
      } else {
        return getLandingRow() * -4.500158825082766
                + rowsRemoved * 10000000.0;
      }
    }
  }

  public int getLandingRow() {
    return (int) landingHeight + ((tetramino.getData().length - 1) / 2);
  }
}
