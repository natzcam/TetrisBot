/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nac.tbot;

/**
 *
 * @author camomot
 */
public interface Bot {

  public void setAction(Action action);
  
  public void newPiece(Board board, int currentMino);
  
}
