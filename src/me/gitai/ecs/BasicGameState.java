package me.gitai.ecs;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Gitai.me(i@gitai.me) on 9/11/17.
 * 游戏状态基类，增加调试器
 */
public abstract class BasicGameState extends org.newdawn.slick.state.BasicGameState {
    private int[] mHeight, mWidth;
    protected boolean debug = true;

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        if (debug) {
            int row = gameContainer.getHeight()/8;
            int col = gameContainer.getWidth()/8;
            mHeight = new int[]{
                    row,
                    row * 2,
                    row * 3,
                    row * 4,
                    row * 5,
                    row * 6,
                    row * 7,
            };
            mWidth = new int[]{
                    col,
                    col * 2,
                    col * 3,
                    col * 4,
                    col * 5,
                    col * 6,
                    col * 7,
            };
        }
        gameContainer.getGraphics()
                .setBackground(new Color(13*17, 13*17, 13*17));
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        if (debug) {
            for (int row:mHeight) {
                graphics.drawLine(0, row, gameContainer.getWidth(), row);
            }
            for (int col:mWidth) {
                graphics.drawLine(col, 0, col, gameContainer.getHeight());
            }
        }
    }
}
