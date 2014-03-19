
var eltetris = null;
var moveCount = 0;
var score = 0;
var moveLimit = 200;

function playGame() {
  moveCount = 0;
  eltetris = new ElTetris(10, 20);

  while (true) {
    var last_move = eltetris.play();
    moveCount++;

    if (last_move.game_over || moveCount > moveLimit) {
      score += eltetris.score;
      return;
    }
  }
}

function play() {
  score = 0;

  for(var i = 0; i < 3; i++){
      playGame();
  }

  score /= 3;
  println("score:" + score);
}

