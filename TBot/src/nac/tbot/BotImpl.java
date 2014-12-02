package nac.tbot;

public class BotImpl implements Bot {

  private final int BUILDUP = 1;
  private final int BREAKDOWN = 2;
  private int phase = BUILDUP;
  private int breakDownLimit;
  private int buildUpLimit;
  private int towerGap;

  @Override
  public Move move(Board board, int currentMino) {
    double best_evaluation = -100000;
    double evaluation;
    Move best_move1 = null;

    Tetramino[] cvar = TetraminoFactory.get(currentMino);

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
}
