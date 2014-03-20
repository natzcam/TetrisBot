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
  private final Piece piece;

  public Move(Piece piece, int rotation, int column) {
    this.piece = piece;
    this.rotation = rotation;
    this.column = column;
  }

  public int getRotation() {
    return rotation;
  }

  public int getColumn() {
    return column;
  }

  public Piece getPiece() {
    return piece;
  }

}
