/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisbot;

import net.sourceforge.jswarm_pso.Swarm;

/**
 *
 * @author user
 */
public class TetrisBot {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    // Create a swarm (using 'MyParticle' as sample particle 
// and 'MyFitnessFunction' as finess function)
    Swarm swarm = new Swarm(Swarm.DEFAULT_NUMBER_OF_PARTICLES, new MyParticle(), new MyFitnessFunction());
// Set position (and velocity) constraints. 
// i.e.: where to look for solutions
    swarm.setMaxPosition(1);
    swarm.setMinPosition(0);
// Optimize a few times
    for (int i = 0; i < 20; i++) {
      swarm.evolve();
    }
// Print en results
    System.out.println(swarm.toStringStats());
  }

}
