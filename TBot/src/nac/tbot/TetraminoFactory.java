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

  public static int getIndexByRBG(Color rgb) {
    double[] dists = new double[]{getColorDistance(lightestBlack, rgb), getColorDistance(lightBlue, rgb), getColorDistance(purple, rgb), getColorDistance(yellow, rgb), getColorDistance(blue, rgb), getColorDistance(orange, rgb), getColorDistance(green, rgb), getColorDistance(red, rgb)};
    int result = 0;
    for (int i = 1; i < dists.length; i++) {
      if (dists[i] < dists[result]) {
        result = i;
      }
    }
    return result - 1;
  }

  private static double getColorDistance(Color c1, Color c2) {
    return (c1.getRed() - c2.getRed()) * (c1.getRed() - c2.getRed())
            + (c1.getGreen() - c2.getGreen()) * (c1.getGreen() - c2.getGreen())
            + (c1.getBlue() - c2.getBlue()) * (c1.getBlue() - c2.getBlue());
  }
}
