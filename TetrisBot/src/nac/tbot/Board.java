/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nac.tbot;

import java.util.Arrays;

/**
 *
 * @author DTIT
 */
public class Board {

  private final int rows;
  private final int columns;
  private final boolean[][] data;

  public Board(int rows, int columns) {
    this.columns = columns;
    this.rows = rows;
    boolean[][] d = new boolean[rows][columns];
    for (int i = 0; i < rows; i++) {
      boolean[] r = new boolean[columns];
      for (int j = 0; j < columns; j++) {
        r[j] = false;
      }
      d[i] = r;
    }
    this.data = d;
  }

  public boolean[][] getData() {
    return data;
  }

  public int getRows() {
    return rows;
  }

  public int getColumns() {
    return columns;
  }

  public Board apply(Move move) {
    return null;
  }

  @Override
  public String toString() {

    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < rows; i++) {
      boolean[] row = data[i];
      for (int j = 0; j < columns; j++) {
        if (row[j]) {
          sb.append("1");
        } else {
          sb.append("0");
        }
      }
      sb.append("\n");
    }

    return sb.toString();
  }

  public boolean compare(Board board) {
    return Arrays.deepEquals(data, board.getData());
  }
}
