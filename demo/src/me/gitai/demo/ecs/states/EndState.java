package me.gitai.demo.ecs.states;

import me.gitai.demo.ecs.Data;
import me.gitai.demo.ecs.Demo;
import me.gitai.demo.ecs.Resource;
import me.gitai.demo.ecs.BasicGameState;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Gitai.me(i@gitai.me) on 9/10/17.
 */
public class EndState extends BasicGameState {

    private MouseOverArea restartBtm;
    private GameContainer mGameContainer;
    private boolean restart;
    private boolean defCursor;

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame)
            throws SlickException {
        super.init(gameContainer, stateBasedGame);
        this.mGameContainer = gameContainer;
        restartBtm = new MouseOverArea(gameContainer,
                null,
                Demo.WIDTH/2 - 100, Demo.HEIGHT/2 + 150, 200, 50,
                abstractComponent -> restart = true);
        restartBtm.setNormalColor(Color.gray);
        restartBtm.setMouseOverColor(Color.white);
    }



    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics)
            throws SlickException {
        super.render(gameContainer, stateBasedGame, graphics);
        restartBtm.render(gameContainer, graphics);
        graphics.setColor(Color.black);

        graphics.setFont(Resource.getInstance().font.printBigLogo());
        graphics.drawString("GAME OVER !", Demo.WIDTH/2 - 170, 200);
        graphics.setFont(Resource.getInstance().font.printBig());
        graphics.drawString("Score: " + Data.getInstance().getmScore(),
                Demo.WIDTH/2 - 100, 400);
        graphics.drawString("  Time: " + Data.getInstance().getTime() + "s",
                Demo.WIDTH/2 - 100, 430);

        graphics.drawRect(Demo.WIDTH/2 - 100, Demo.HEIGHT/2 + 150, 200, 50);
        graphics.drawString("RESTART", Demo.WIDTH/2 - 50, Demo.HEIGHT/2 + 150 + 14);

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i)
            throws SlickException {
        if (restart) {
            restart = false;
            gameContainer.reinit();
        }
    }


    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
        super.mouseMoved(oldx, oldy, newx, newy);

        try {
            if (restartBtm.isMouseOver()) {
                defCursor = false;
                mGameContainer.setMouseCursor(Resource.getInstance().cursor.getHand(), 0, 0);
            } else if(!defCursor) {
                defCursor = true;
                mGameContainer.setMouseCursor(Resource.getInstance().cursor.getArrow(), 0, 0);
            }
        } catch (SlickException e) {
            e.printStackTrace();
        }

    }
}
