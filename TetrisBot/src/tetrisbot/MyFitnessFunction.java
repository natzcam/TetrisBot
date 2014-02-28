/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisbot;

import net.sourceforge.jswarm_pso.FitnessFunction;

/**
 *
 * @author user
 */
public class MyFitnessFunction extends FitnessFunction {

  @Override
  public double evaluate(double position[]) {
    return position[0] + position[1];
  }
}
