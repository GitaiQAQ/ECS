package me.gitai.demo.ecs.states;

import me.gitai.demo.ecs.Config;
import me.gitai.demo.ecs.Data;
import me.gitai.demo.ecs.Demo;
import me.gitai.demo.ecs.Resource;
import me.gitai.demo.ecs.gui.Buttom;
import me.gitai.ecs.BasicGameState;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Gitai.me(i@gitai.me) on 9/10/17.
 */
public class EndState extends BasicGameState {

    private Buttom restartBtm;
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

        restartBtm = new Buttom(gameContainer,
                "RESTART",
                Config.width/2 - 100, Config.height/2 + 150, 200, 50);
        restartBtm.addListener(new ComponentListener() {
            @Override
            public void componentActivated(AbstractComponent abstractComponent) {
                restart = true;
            }
        });
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
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics)
            throws SlickException {
        super.render(gameContainer, stateBasedGame, graphics);
        graphics.setColor(Color.black);

        graphics.setFont(Resource.getInstance().font.printBigLogo());
        graphics.drawString("GAME OVER !", Config.width/2 - 170, 200);
        graphics.setFont(Resource.getInstance().font.printBig());
        graphics.drawString("Score: " + Data.getInstance().getmScore(),
                Config.width/2 - 100, 400);
        graphics.drawString("  Time: " + Data.getInstance().getTime() + "s",
                Config.width/2 - 100, 430);

        restartBtm.render(gameContainer, graphics);
    }
}
