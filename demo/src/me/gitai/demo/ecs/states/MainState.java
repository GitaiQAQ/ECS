package me.gitai.demo.ecs.states;

import me.gitai.demo.ecs.Config;
import me.gitai.demo.ecs.Demo;
import me.gitai.demo.ecs.Resource;
import me.gitai.demo.ecs.gui.Buttom;
import me.gitai.ecs.BasicGameState;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Gitai.me(i@gitai.me) on 9/10/17.
 */
public class MainState extends BasicGameState {
    private boolean start;
    private Buttom startBtm, exitBtm;

    public MainState() {
    }

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame)
            throws SlickException {
        super.init(gameContainer, stateBasedGame);
        startBtm = new Buttom(gameContainer,
                "START",
                Config.width/2 - 100, Config.height/2, 200, 50);
        startBtm.addListener(new ComponentListener() {
            @Override
            public void componentActivated(AbstractComponent abstractComponent) {
                start = true;
            }
        });

        exitBtm = new Buttom(gameContainer,
                "EXIT",
                Config.width / 2 - 100, Config.height / 2 + 64, 200, 50);
        exitBtm.addListener(new Buttom.ExitComponent());
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i)
            throws SlickException {
        if (start) {
            stateBasedGame.enterState(3);
        }
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics)
            throws SlickException {
        super.render(gameContainer, stateBasedGame, graphics);

        graphics.setColor(Color.black);
        graphics.setFont(Resource.getInstance().font.printBigLogo());
        graphics.drawString("ICE & FIRE", Config.width/2 - 130, 200);
        graphics.setFont(Resource.getInstance().font.printLabel());
        graphics.drawString("@Gitai", Config.width/2 + 100, 270);

        startBtm.render(gameContainer, graphics);
        exitBtm.render(gameContainer, graphics);
    }

    @Override
    public void keyPressed(int key, char c) {
        super.keyPressed(key, c);
        if (key == Input.KEY_ENTER || key == Input.KEY_SPACE)
            start = true;
    }
}
