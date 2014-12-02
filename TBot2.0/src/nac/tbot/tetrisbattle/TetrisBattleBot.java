package nac.tbot.tetrisbattle;

import nac.tbot.Action;
import nac.tbot.Board;
import nac.tbot.Bot;
import nac.tbot.Move;
import nac.tbot.Tetramino;
import nac.tbot.TetraminoFactory;

public class TetrisBattleBot implements Bot {

  private Action action = null;
  private final int BUILDUP = 1;
  private final int BREAKDOWN = 2;
  private int phase = BUILDUP;
  private int breakDownLimit = 5;
  private int buildUpLimit = 15;
  private int towerGap = 2;

  private int hold = 0;
  private boolean pressedShift = false;

  @Override
  public void newPiece(Board board, int current) {

    if (current != 0) {

      if (hold == 0) {
        action.sendShift();
        hold = current;

        pressedShift = true;
      } else {
        Move move1;
        Move move2;
        Move finalMove;

        if (pressedShift == false) {
          move1 = bestMove(board, current);
          move2 = bestMove(board, hold);

          if (move1 != null && move2 != null && move1.getScore() >= move2.getScore()) {
            finalMove = move1;
          } else {
            action.sendShift();
            hold = current;
            pressedShift = true;
            finalMove = move2;
          }
        } else {
          finalMove = bestMove(board, current);
        }

        if (finalMove != null) {

          int rotation = finalMove.getRotation();

          if (rotation == 3) {
            action.sendRotateLeft();
          } else {
            while (rotation > 0) {
              action.sendRotateRight();
              rotation--;
            }
          }

          int column = finalMove.getColumn();
          int offset = column - finalMove.getTetramino().getOffset();

          if (offset > 0) {
            while (offset > 0) {
              action.sendRight();
              offset--;
            }
          } else if (offset < 0) {
            while (offset < 0) {
              action.sendLeft();
              offset++;
            }
          }

          pressedShift = false;
          action.sendSpace();

        }

      }
    }
  }

  private Move bestMove(Board board, int currentMino) {
    double best_evaluation = Integer.MIN_VALUE;
    double evaluation;
    Move best_move1 = null;

    Tetramino[] cvar = TetraminoFactory.get(currentMino - 1);

    int cvarlen = cvar.length;
    int columns = board.getColumns();

    if (phase == BUILDUP) {
      if (board.getBoardHeight() <= buildUpLimit) {
        columns -= towerGap;
      } else {
        phase = BREAKDOWN;
      }
    } else if (phase == BREAKDOWN && board.getBoardHeight() <= breakDownLimit) {
      phase = BUILDUP;
    }

    for (int i = 0; i < cvarlen; i++) {
      for (int c = 0; c < columns - cvar[i].getWidth() + 1; c++) {

        Move move1 = new Move(cvar[i], i, c);
        Board newBoard1 = board.apply(move1);
        if (!move1.isGameOver()) {

          evaluation = newBoard1.evaluate() + move1.evaluate();

          if (evaluation > best_evaluation) {
            best_evaluation = evaluation;
            best_move1 = move1;
          }
        }
      }
    }

    if (best_move1 != null) {
      best_move1.setScore(best_evaluation);
    }

    return best_move1;
  }

  public void setBreakDownLimit(int breakDownLimit) {
    this.breakDownLimit = breakDownLimit;
  }

  public void setBuildUpLimit(int buildUpLimit) {
    this.buildUpLimit = buildUpLimit;
  }

  public void setTowerGap(int towerGap) {
    this.towerGap = towerGap;
  }

  public int getPhase() {
    return phase;
  }

  @Override
  public void setAction(Action action) {
    this.action = action;
  }

}
