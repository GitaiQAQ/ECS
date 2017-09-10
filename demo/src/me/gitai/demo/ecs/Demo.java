package me.gitai.demo.ecs;

import me.gitai.demo.ecs.states.EndState;
import me.gitai.demo.ecs.states.MainState;
import me.gitai.demo.ecs.states.PauseState;
import me.gitai.demo.ecs.states.PlayState;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.BlobbyTransition;

/**
 * Created by Gitai.me(i@gitai.me) on 9/10/17.
 */
public class Demo extends StateBasedGame {
    public static int WIDTH = 1366;
    public static int HEIGHT = 768;

    public static void main(String[] args) throws SlickException {
        AppGameContainer window = new AppGameContainer(new Demo());
        window.setDisplayMode(WIDTH, HEIGHT, true);
        window.setTargetFrameRate(100);
        window.setShowFPS(false);
        window.start();
    }

    public Demo() {
        super("Test");
        addState(new PauseState());
        addState(new PlayState());
        addState(new EndState());
        addState(new MainState());

    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        enterState(1, new BlobbyTransition(Color.black), new BlobbyTransition(new Color(13*17, 13*17, 13*17)));
    }
}
