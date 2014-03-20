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
public enum Piece {

  I,
  T,
  O,
  J,
  L,
  S,
  Z;

//  public Piece() {
//  }
//
//  public Piece(String[] representation, int width, int height, int offset) {
//    this.representation = representation;
//    this.width = width;
//    this.height = height;
//    this.offset = offset;
//  }
//
//  public Piece(int[] data, int width, int height, int offset) {
//    this.data = data;
//    this.width = width;
//    this.height = height;
//    this.offset = offset;
//  }
//
//  public int getHeight() {
//    return height;
//  }
//
//  public int getOffset() {
//    return offset;
//  }
//
//  public int[] getData() {
//    return data;
//  }
//
//  void translate() {
//    if (data == null) {
//      int[] d = new int[representation.length];
//      for (int i = representation.length - 1, j = 0; i > -1; j++, i--) {
//        d[j] = Integer.parseInt(new StringBuilder(representation[i]).reverse().toString(), 2);
//      }
//      data = d;
//    }
//  }
//
//  public int getWidth() {
//    return width;
//  }
//
//  public Piece move(int column) {
//    Piece tetramino = new Piece(Arrays.copyOf(data, data.length), width, height, offset);
//    int[] d = tetramino.getData();
//    for (int i = 0; i < d.length; i++) {
//      d[i] = d[i] << column;
//    }
//    return tetramino;
//  }
}
