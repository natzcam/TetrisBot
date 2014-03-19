/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nac.tbot.opt.eltetris;

import net.sourceforge.jswarm_pso.FitnessFunction;

/**
 *
 * @author user
 */
public class MyFitnessFunction extends FitnessFunction {

  private final Agent agent = new Agent();

  @Override
  public double evaluate(double position[]) {
    agent.setLandingHeightWt(position[0]);
    agent.setRowsRemovedWt(position[1]);
    agent.setScoreIncWt(position[2]);
    agent.setRowTransitionWt(position[3]);
    agent.setColumnTransitionWt(position[4]);
    agent.setNumHolesWt(position[5]);
    agent.setWellSumsWt(position[6]);
    return agent.eval();
  }
}
