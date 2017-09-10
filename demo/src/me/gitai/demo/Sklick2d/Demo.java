package me.gitai.demo.Sklick2d;

import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;

/**
 * Created by Gitai.me(i@gitai.me) on 9/5/17.
 */
public class Demo extends BasicGame {

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new Demo());
        app.start();
    }

    public Demo() {
        super("PlayState");
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {

    }

    int mI = 0;

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        mI = i;
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        graphics.drawString("Hello World!" + mI, 100f, 100f);
    }
}
