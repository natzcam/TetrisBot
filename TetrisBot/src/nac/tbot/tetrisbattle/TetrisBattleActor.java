package nac.tbot.tetrisbattle;

import nac.tbot.Actor;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import nac.tbot.Move;

public class TetrisBattleActor implements Actor {

  private Robot robot;
  private int delay = 50;
  private ActorOptionPanel ActorOptionPanel;

  public TetrisBattleActor() {
    try {
      robot = new Robot();
    } catch (AWTException ex) {
      Logger.getLogger(TetrisBattleActor.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void sendSpace() {
    robot.keyPress(KeyEvent.VK_SPACE);
    robot.delay(delay);
    robot.keyRelease(KeyEvent.VK_SPACE);
    robot.delay(delay);
  }

  public void sendLeft() {
    robot.keyPress(KeyEvent.VK_LEFT);
    robot.delay(delay);
    robot.keyRelease(KeyEvent.VK_LEFT);
    robot.delay(delay);
  }

  public void sendRight() {
    robot.keyPress(KeyEvent.VK_RIGHT);
    robot.delay(delay);
    robot.keyRelease(KeyEvent.VK_RIGHT);
    robot.delay(delay);
  }

  public void sendRotateRight() {
    robot.keyPress(KeyEvent.VK_UP);
    robot.delay(delay);
    robot.keyRelease(KeyEvent.VK_UP);
    robot.delay(delay);
  }

  public void sendRotateLeft() {
    robot.keyPress(KeyEvent.VK_Z);
    robot.delay(delay);
    robot.keyRelease(KeyEvent.VK_Z);
    robot.delay(delay);
  }

  public void sendShift() {
    robot.keyPress(KeyEvent.VK_SHIFT);
    robot.delay(delay);
    robot.keyRelease(KeyEvent.VK_SHIFT);
    robot.delay(delay);
  }

  public int getDelay() {
    return delay;
  }

  public void setDelay(int delay) {
    this.delay = delay;
  }

  @Override
  public JPanel getOption() {
    if (ActorOptionPanel == null) {
      ActorOptionPanel = new ActorOptionPanel(this);
    }
    return ActorOptionPanel;
  }

  @Override
  public void doMove(Move move) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

}
