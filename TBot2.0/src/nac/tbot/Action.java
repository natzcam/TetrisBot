/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nac.tbot;

import javax.swing.JPanel;

/**
 *
 * @author user
 */
public interface Action {

  public void sendLeft();

  public void sendRight();

  public void sendRotateRight();
  
  public void sendRotateLeft();

  public void sendShift();

  public void sendSpace();

  public JPanel getOption();
}
