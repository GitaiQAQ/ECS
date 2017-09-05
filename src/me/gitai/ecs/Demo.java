package me.gitai.ecs;

import org.newdawn.slick.*;

/**
 * Created by Gitai.me(i@gitai.me) on 9/5/17.
 */
public class Demo extends BasicGame {

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new Demo());
        app.start();
    }

    public Demo() {
        super("Demo");
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {

    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        graphics.drawString("Hello World!", 100f, 100f);
    }
}
