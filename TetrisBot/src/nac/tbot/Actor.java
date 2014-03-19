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
public interface Actor {

  public void doMove(Move move);

  public JPanel getOption();
}
