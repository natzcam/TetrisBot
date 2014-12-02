package nac.tbot;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Keyboard {

  private Robot robot;
  private int delay;

  public Keyboard() {
    try {
      robot = new Robot();
    } catch (AWTException ex) {
      Logger.getLogger(Keyboard.class.getName()).log(Level.SEVERE, null, ex);
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

  public void sendRotate() {
    robot.keyPress(KeyEvent.VK_UP);
    robot.delay(delay);
    robot.keyRelease(KeyEvent.VK_UP);
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
  
  
}
