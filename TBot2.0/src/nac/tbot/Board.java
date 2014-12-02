/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nac.tbot;

import java.awt.Color;
import java.util.Arrays;
import nac.tbot.tetrisbattle.TGridPanel;

/**
 *
 * @author DTIT
 */
public class Board {

  private int rows;
  private int columns;
  private int[] data;
  private int fullRow;

  public Board(int rows, int columns) {

    this.columns = columns;
    this.rows = rows;
    int[] d = new int[rows];
    for (int i = 0; i < rows; i++) {
      d[i] = 0;
    }
    this.data = d;
    this.fullRow = (int) Math.pow(2, columns) - 1;
  }

  public Board(int[] data, int rows, int columns) {
    this.columns = columns;
    this.rows = rows;
    this.data = data;
    this.fullRow = (int) Math.pow(2, columns) - 1;
  }

  public int[] getData() {
    return data;
  }

  public int getRows() {
    return rows;
  }

  public int getColumns() {
    return columns;
  }

  public int getRowTransitions() {
    int transitions = 0;
    int last_bit = 1;

    for (int i = 0; i < data.length; ++i) {
      int row = data[i];
      int bit = 1;
      for (int j = 0; j < columns; ++j) {
        bit = (row >> j) & 1;

        if (bit != last_bit) {
          ++transitions;
        }

        last_bit = bit;
      }

      if (bit == 0) {
        ++transitions;
      }
      last_bit = 1;
    }
    return transitions;
  }

  public int getColumnTransitions() {
    int transitions = 0;
    int last_bit = 1;

    for (int i = 0; i < columns; ++i) {
      for (int j = 0; j < data.length; ++j) {
        int row = data[j];
        int bit = (row >> i) & 1;

        if (bit != last_bit) {
          ++transitions;
        }

        last_bit = bit;
      }

      last_bit = 1;
    }

    return transitions;
  }

  public int getHoles() {
    int holes = 0;
    int row_holes = 0x0000;
    int previous_row = data[data.length - 1];

    for (int i = data.length - 2; i >= 0; --i) {
      row_holes = ~data[i] & (previous_row | row_holes);

      for (int j = 0; j < columns; ++j) {
        holes += ((row_holes >> j) & 1);
      }

      previous_row = data[i];
    }

    return holes;
  }

  public int getWellSums() {
    int well_sums = 0;

    for (int i = 1; i < columns - 1; ++i) {
      for (int j = data.length - 1; j >= 0; --j) {
        if ((((data[j] >> i) & 1) == 0)
                && (((data[j] >> (i - 1)) & 1) == 1)
                && (((data[j] >> (i + 1)) & 1) == 1)) {

          ++well_sums;
          for (int k = j - 1; k >= 0; --k) {
            if (((data[k] >> i) & 1) == 0) {
              ++well_sums;
            } else {
              break;
            }
          }
        }
      }
    }

    for (int j = data.length - 1; j >= 0; --j) {
      if ((((data[j]) & 1) == 0)
              && (((data[j] >> (0 + 1)) & 1) == 1)) {

        ++well_sums;
        for (int k = j - 1; k >= 0; --k) {
          if (((data[k]) & 1) == 0) {
            ++well_sums;
          } else {
            break;
          }
        }
      }
    }

    for (int j = data.length - 1; j >= 0; --j) {
      if ((((data[j] >> (columns - 1)) & 1) == 0)
              && (((data[j] >> (columns - 2)) & 1) == 1)) {

        ++well_sums;
        for (int k = j - 1; k >= 0; --k) {
          if (((data[k] >> (columns - 1)) & 1) == 0) {
            ++well_sums;
          } else {
            break;
          }
        }
      }
    }

    return well_sums;
  }

  public int getBoardHeight() {
    for (int i = 0; i < data.length; i++) {
      if (data[i] == 0) {
        return i;
      }
    }
    return rows;
  }

  public int getTowerHeight() {
    int towerHeight = 0;
    for (int i = 0; i < data.length; i++) {
      if (data[i] >= 1020) {
        towerHeight++;
      }
    }
    return towerHeight;
  }

  public Board apply(Move move) {
    int column = move.getColumn();
    Tetramino current = move.getTetramino().move(column);
    int[] tdata = current.getData();
    int placementRow = getPlacementRow(current);
    int rowsRemoved = 0;

    if (placementRow + tdata.length > rows) {
      move.setGameOver(true);
      return null;
    }

    int[] bdata = Arrays.copyOf(data, data.length);

    for (int i = 0; i < tdata.length; i++) {
      bdata[placementRow + i] |= tdata[i];
    }

    for (int i = 0; i < tdata.length; i++) {
      int index = placementRow + i;
      if (bdata[index] == fullRow) {

        System.arraycopy(bdata, index + 1, bdata, index, bdata.length - 1 - index);

        bdata[bdata.length - 1] = 0;
        i--;
        rowsRemoved++;
      }
    }
    move.setRowsRemoved(rowsRemoved);
    move.setLandingHeight(placementRow);
    return new Board(bdata, rows, columns);
  }

  public double evaluate() {
    return (double) getRowTransitions() * -3.2178882868487753
            + (double) getColumnTransitions() * -9.348695305445199
            + (double) getHoles() * -7.899265427351652
            + (double) getWellSums() * -3.3855972247263626;
  }

  private int getPlacementRow(Tetramino tetramino) {
    int[] tdata = tetramino.getData();
    int[] bdata = this.data;

    for (int row = rows - tdata.length; row >= 0; row--) {

      for (int i = 0; i < tdata.length; i++) {
        if ((bdata[row + i] & tdata[i]) != 0) {
          return row + 1;
        }
      }
    }
    return 0;
  }

  @Override
  public String toString() {

    StringBuilder sb = new StringBuilder();

    for (int i = rows - 1; i >= 0; i--) {
      int row = data[i];
      for (int j = 0; j < columns; j++) {
        if (TGridPanel.getBit(row, j) == 1) {
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
    int[] data2 = board.getData();
    for (int i = 0; i < rows; i++) {
      if (data[i] != data2[i]) {
        return false;
      }
    }
    return true;
  }
}
