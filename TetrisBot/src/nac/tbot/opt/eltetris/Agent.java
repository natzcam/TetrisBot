/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nac.tbot.opt.eltetris;

import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 * @author user
 */
public class Agent {

  private double landingHeightWt = -4.500158825082766;
  private double rowsRemovedWt = 3.4181268101392694;
  private double scoreIncWt = 0;
  private double rowTransitionWt = -3.2178882868487753;
  private double columnTransitionWt = -9.348695305445199;
  private double numHolesWt = -7.899265427351652;
  private double wellSumsWt = -3.3855972247263626;

  public void setLandingHeightWt(double landingHeightWt) {
    this.landingHeightWt = landingHeightWt;
  }

  public void setRowsRemovedWt(double rowsRemovedWt) {
    this.rowsRemovedWt = rowsRemovedWt;
  }

  public void setScoreIncWt(double scoreIncWt) {
    this.scoreIncWt = scoreIncWt;
  }

  public void setRowTransitionWt(double rowTransitionWt) {
    this.rowTransitionWt = rowTransitionWt;
  }

  public void setColumnTransitionWt(double columnTransitionWt) {
    this.columnTransitionWt = columnTransitionWt;
  }

  public void setNumHolesWt(double numHolesWt) {
    this.numHolesWt = numHolesWt;
  }

  public void setWellSumsWt(double wellSumsWt) {
    this.wellSumsWt = wellSumsWt;
  }

  public void setEngine(ScriptEngine engine) {
    this.engine = engine;
  }

  public double eval() {
    double score = 0;
    try {
      engine.eval("landingHeightWt = " + landingHeightWt + ";");
      engine.eval("rowsRemovedWt = " + rowsRemovedWt + ";");
      engine.eval("scoreIncWt = " + scoreIncWt + ";");
      engine.eval("rowTransitionWt = " + rowTransitionWt + ";");
      engine.eval("columnTransitionWt = " + columnTransitionWt + ";");
      engine.eval("numHolesWt = " + numHolesWt + ";");
      engine.eval("wellSumsWt = " + wellSumsWt + ";");
      engine.eval("play();");
      score = (Double) engine.eval("score");
    } catch (ScriptException ex) {
      Logger.getLogger(Agent.class.getName()).log(Level.SEVERE, null, ex);
    }
    return score;
  }

  private ScriptEngine engine;

  public Agent() {
    try {
      ScriptEngineManager factory = new ScriptEngineManager();
      engine = factory.getEngineByName("JavaScript");
      engine.eval(new InputStreamReader(getClass().getResourceAsStream("pieces.js")));
      engine.eval(new InputStreamReader(getClass().getResourceAsStream("features.js")));
      engine.eval(new InputStreamReader(getClass().getResourceAsStream("eltetris.js")));
      engine.eval(new InputStreamReader(getClass().getResourceAsStream("game_html.js")));
    } catch (ScriptException ex) {
      Logger.getLogger(Agent.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

}
