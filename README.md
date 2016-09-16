*TBot* is a bot for tetris games on [tetrisfriends.com](http://www.tetrisfriends.com) and [Tetris Battle](https://apps.facebook.com/tetris_battle/) on Facebook.

This bot uses [java.awt.Robot](https://docs.oracle.com/javase/7/docs/api/java/awt/Robot.html) class to "visualize" the board by identifying piece colors and detect the current piece through color-nearness . It selects the between the current piece and the piece on hold and which position and orientation will generate the best outcome of the tetris board. Additionally, the bot will go through buildup and breakdown phases which greatly increases the chances to score.

The algorithm used is mainly based on [El-Tetris](http://ielashi.com/el-tetris-an-improvement-on-pierre-dellacheries-algorithm) but implemented in java.

Marathon Mode: Reduce the number of buildup limit and breakdown limit ex: 5/0

===Bot in Action===

[http://vimeo.com/61396163 http://vimeo.com/61396163] - this is a previous version.


===Instructions===

  # Download and install [http://www.java.com/en/download/index.jsp java] if you haven't it yet.
  # Download the zip file from [https://drive.google.com/folderview?id=0BwaWDMD7MRkxRXJOc3MyYzF0U2M&usp=sharing here]. Then, unzip it anywhere.
  # Double click on TBot.jar to start the application.
  # Restore the browser window of Tetris Battle. *It is important that the browser and the application don't share the same screen space*. <br/><img height="200" width="500" src="http://tetris-battle-bot.googlecode.com/svn/trunk/TBot2.0/screen%20space.PNG"></img>
  # Just press the "Find Board" button and adjust the transparent window that will popout to the edges of the tetris board. *Closing the transparent window* will store the board location so do not move the tetris board after. <br/><img height="200" src="http://tetris-battle-bot.googlecode.com/svn/trunk/TBot2.0/find%20board.PNG"></img>
  # Press the "Start" button on the game and just maintain window focus on the browser and that's it. It will automatically detect the start of the game.


===Options===

Key Delay - This is the time delay before the key/move is sent to the focused window.
This number should be balanced. If the number is very low, the bot will go crazy. If the number is very high, the bot is slow.

Tower Gap - The width of the gap on the right that the bot reserves on the Buildup phase.

Buildup Limit - The height of the board that will trigger the bot to go into the Breakdown phase and decrease its height.

Breakdown Limit - The height of the board that will trigger the bot to go into the Buildup phase and increase its height while maintaining the tower gap.


===TODO===

  # 1-piece lookahead
  # better board awareness


===Disclaimer===

This will not make you rank 100 in Tetris Battle.
Skilled humans can beat this bot.
At least, in v1.0. We don't know in v2.0. =)
