/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nac.tbot;

import com.google.gson.Gson;
import java.awt.Color;
import java.io.InputStreamReader;

/**
 *
 * @author camomot
 */
public class TetraminoFactory {

  private static Tetramino[][] MINOS;
  private static final TetraminoFactory INSTANCE = new TetraminoFactory();
  private static final String MINOS_FILE = "minos";

  private TetraminoFactory() {
    Gson gson = new Gson();
    MINOS = gson.fromJson(
            new InputStreamReader(TetraminoFactory.class
                    .getResourceAsStream(MINOS_FILE)), Tetramino[][].class);
    for (int i = 0; i < MINOS.length; i++) {
      Tetramino[] t = MINOS[i];
      for (int j = 0; j < t.length; j++) {
        t[j].translate();
      }
    }
  }

  public static Tetramino[] get(int type) {
    return MINOS[type];
  }

  public static final String indexToCode(int index) {
    switch (index) {
      case 1:
        return "I";
      case 2:
        return "T";
      case 3:
        return "O";
      case 4:
        return "J";
      case 5:
        return "L";
      case 6:
        return "S";
      case 7:
        return "Z";
      default:
        return "";
    }
  }
}
