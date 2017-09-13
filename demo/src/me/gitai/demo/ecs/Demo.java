package me.gitai.demo.ecs;

import com.beust.jcommander.JCommander;
import me.gitai.demo.ecs.states.EndState;
import me.gitai.demo.ecs.states.MainState;
import me.gitai.demo.ecs.states.PauseState;
import me.gitai.demo.ecs.states.PlayState;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ResourceBundle;

/**
 * Created by Gitai.me(i@gitai.me) on 9/10/17.
 */
public class Demo extends StateBasedGame {
    public static void main(String[] args) throws SlickException {
        Config config = new Config();
        new JCommander(config, args);
        System.setProperty("org.lwjgl.librarypath", Config.lwjglNativesDir);

        AppGameContainer window = new AppGameContainer(new Demo());
        window.setDisplayMode(Config.width, Config.height, Config.fullscreen);
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
        gameContainer.setMouseCursor(Resource.getInstance().cursor.getArrow(),
                12, 12);
        enterState(1);
    }
}
