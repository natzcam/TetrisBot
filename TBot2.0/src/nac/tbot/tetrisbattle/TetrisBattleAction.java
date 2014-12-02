package nac.tbot.tetrisbattle;

import nac.tbot.Action;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

public class TetrisBattleAction implements Action {

  private Robot robot;
  private int delay = 50;
  private ActionOptionPanel actionOptionPanel;

  public TetrisBattleAction() {
    try {
      robot = new Robot();
    } catch (AWTException ex) {
      Logger.getLogger(TetrisBattleAction.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @Override
  public void sendSpace() {
    robot.keyPress(KeyEvent.VK_SPACE);
    robot.delay(delay);
    robot.keyRelease(KeyEvent.VK_SPACE);
    robot.delay(delay);
  }

  @Override
  public void sendLeft() {
    robot.keyPress(KeyEvent.VK_LEFT);
    robot.delay(delay);
    robot.keyRelease(KeyEvent.VK_LEFT);
    robot.delay(delay);
  }

  @Override
  public void sendRight() {
    robot.keyPress(KeyEvent.VK_RIGHT);
    robot.delay(delay);
    robot.keyRelease(KeyEvent.VK_RIGHT);
    robot.delay(delay);
  }

  @Override
  public void sendRotateRight() {
    robot.keyPress(KeyEvent.VK_UP);
    robot.delay(delay);
    robot.keyRelease(KeyEvent.VK_UP);
    robot.delay(delay);
  }

  @Override
  public void sendRotateLeft() {
    robot.keyPress(KeyEvent.VK_Z);
    robot.delay(delay);
    robot.keyRelease(KeyEvent.VK_Z);
    robot.delay(delay);
  }

  @Override
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
    if (actionOptionPanel == null) {
      actionOptionPanel = new ActionOptionPanel(this);
    }
    return actionOptionPanel;
  }

}
