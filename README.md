# TBot
> a bot for tetris games on [tetrisfriends.com](http://www.tetrisfriends.com) and [Tetris Battle](https://apps.facebook.com/tetris_battle/) on Facebook.

This bot uses [java.awt.Robot](https://docs.oracle.com/javase/7/docs/api/java/awt/Robot.html) class to "visualize" the board by identifying piece colors and detect the current piece through color-nearness . It selects the between the current piece and the piece on hold and which position and orientation will generate the best outcome of the tetris board. Additionally, the bot will go through buildup and breakdown phases which greatly increases the chances to score.

The algorithm used is mainly based on [El-Tetris](http://ielashi.com/el-tetris-an-improvement-on-pierre-dellacheries-algorithm) but implemented in java.

## Bot in action...
[![TBot](https://img.youtube.com/vi/Gmp3qFVQiCM/0.jpg)](https://www.youtube.com/watch?v=Gmp3qFVQiCM)

## Instructions

1. Download and install [java](http://www.java.com/en/download/index.jsp) if you haven't it yet.
2. Download the zip file from [here](https://drive.google.com/folderview?id=0BwaWDMD7MRkxRXJOc3MyYzF0U2M&usp=sharing). Then, unzip it anywhere.
3. Double click on TBot.jar to start the application.
4. Restore the browser window of Tetris Battle. *It is important that the browser and the application don't share the same screen space*.
5. Just press the "Find Board" button and adjust the transparent window that will popout to the edges of the tetris board. *Closing the transparent window* will store the board location so do not move the tetris board after.
6. Press the "Start" button on the game and just maintain window focus on the browser and that's it. It will automatically detect the start of the game.

Marathon Mode: Reduce the number of buildup limit and breakdown limit ex: 5/0

## Options

**Key Delay** - This is the time delay before the key/move is sent to the focused window.
This number should be balanced. If the number is very low, the bot will go crazy. If the number is very high, the bot is slow.

**Tower Gap** - The width of the gap on the right that the bot reserves on the Buildup phase.

**Buildup Limit** - The height of the board that will trigger the bot to go into the Breakdown phase and decrease its height.

**Breakdown Limit** - The height of the board that will trigger the bot to go into the Buildup phase and increase its height while maintaining the tower gap.

## TODO

  * 1-piece lookahead
  * better board awareness

## Disclaimer

This will not make you rank 100 in Tetris Battle. Skilled humans can beat this bot.
