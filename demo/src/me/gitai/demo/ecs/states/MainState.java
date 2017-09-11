package me.gitai.demo.ecs.states;

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
public class MainState extends BasicGameState {
    private boolean start;
    private MouseOverArea startBtm, exitBtm;
    private boolean hand;
    private GameContainer mGameContainer;
    private boolean defCursor;

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame)
            throws SlickException {
        super.init(gameContainer, stateBasedGame);
        this.mGameContainer = gameContainer;
        startBtm = new MouseOverArea(gameContainer,
                null,
                Demo.WIDTH/2 - 100, Demo.HEIGHT/2, 200, 50, abstractComponent -> start=true);
        startBtm.setNormalColor(Color.gray);
        startBtm.setMouseOverColor(Color.white);

        exitBtm = new MouseOverArea(gameContainer,
                null,
                Demo.WIDTH / 2 - 100, Demo.HEIGHT / 2 + 64, 200, 50, abstractComponent -> System.exit(0));
        exitBtm.setNormalColor(Color.gray);
        exitBtm.setMouseOverColor(Color.white);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        if (start) {
            stateBasedGame.enterState(3);
        }
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics)
            throws SlickException {
        super.render(gameContainer, stateBasedGame, graphics);

        startBtm.render(gameContainer, graphics);
        exitBtm.render(gameContainer, graphics);

        graphics.setColor(Color.black);
        graphics.setFont(Resource.getInstance().font.printBigLogo());
        graphics.drawString("ICE & FIRE", Demo.WIDTH/2 - 130, 200);
        graphics.setFont(Resource.getInstance().font.printLabel());
        graphics.drawString("@Gitai", Demo.WIDTH/2 + 100, 270);

        graphics.setFont(Resource.getInstance().font.printHead());

        graphics.drawRect(Demo.WIDTH/2 - 100, Demo.HEIGHT/2, 200, 50);
        graphics.drawString("START", Demo.WIDTH/2 - 40, Demo.HEIGHT/2 + 14);
        graphics.drawRect(Demo.WIDTH/2 - 100, Demo.HEIGHT/2 + 64, 200, 50);
        graphics.drawString("EXIT", Demo.WIDTH/2 - 30, Demo.HEIGHT/2 + 64 + 14);
    }

    @Override
    public void keyPressed(int key, char c) {
        super.keyPressed(key, c);
        start = true;
    }

    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
        super.mouseMoved(oldx, oldy, newx, newy);

        try {
            if (startBtm.isMouseOver()) {
                defCursor = false;
                mGameContainer.setMouseCursor(Resource.getInstance().cursor.getHand(), 0, 0);
            } else if (exitBtm.isMouseOver()) {
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
