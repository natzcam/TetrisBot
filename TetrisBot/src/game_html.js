
var eltetris = null;
var moveCount = 0;
var finalScore = 0;
var moveLimit = 200;

function playGame() {
  moveCount = 0;
  eltetris = new ElTetris(10, 20);

  while (true) {
    var last_move = eltetris.play();
    moveCount++;

    if (last_move.game_over || moveCount > moveLimit) {
      finalScore += eltetris.rows_completed;
      return;
    }
  }
}

function play() {
  finalScore = 0;

  for (var i = 0; i < 1; i++) {
    playGame();
  }

  finalScore /= 1;
  println("finalScore:" + finalScore);
}

