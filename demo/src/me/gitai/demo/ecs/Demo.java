package me.gitai.demo.ecs;


import me.gitai.demo.ecs.assemblages.AirplaneEntity;
import me.gitai.demo.ecs.assemblages.BulletEntity;
import me.gitai.demo.ecs.assemblages.HeroEntity;
import me.gitai.ecs.Entity;
import me.gitai.ecs.components.CompPosition;
import me.gitai.ecs.systems.SysBorder;
import me.gitai.ecs.systems.SysCollision;
import me.gitai.ecs.systems.SysLive;
import me.gitai.ecs.systems.SysMove;
import org.newdawn.slick.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gitai.me on 9/8/17.
 * http://vasir.net/blog/game-development/how-to-build-entity-component-system-in-javascript
 */
public class Demo extends BasicGame {
    public static int WIDTH = 1366;
    public static int HEIGHT = 768;

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new Demo("Test"));
        app.setDisplayMode(WIDTH, HEIGHT, false);
        app.start();
    }

    public Demo(String title) {
        super(title);
    }

    private List<Entity> entitys = new ArrayList<>();
    private HeroEntity hero;
    private boolean shoot = false;

    private Image airplane;

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        hero = new HeroEntity();

        airplane = new Image("assets/img/airplane.png");
        entitys.add(hero);

        for (int i = 0; i < 10; i++) {
            entitys.add(new AirplaneEntity(airplane));
        }

        gameContainer.setShowFPS(false);

        gameContainer.getGraphics()
                .setColor(new Color(0, 0, 0));
        gameContainer.getGraphics()
                .setBackground(new Color(13*17, 13*17, 13*17));
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        hero.updateHero(i);
        entitys.add(new AirplaneEntity(airplane));
        for (int j = 0; j < entitys.size(); j++) {
            Entity entity = entitys.get(j);
            if (entity instanceof AirplaneEntity){
                CompPosition position = ((AirplaneEntity)entity).getHeader();
                entitys.add(new BulletEntity(
                        position,
                        1, 15));
            }
        }

        if (shoot) {
            CompPosition position = hero.getPosition();
            entitys.add(new BulletEntity(position.getX() + 45, position.getY() - 45, 60,
                    1, - 20));
            entitys.add(new BulletEntity(position.getX() + 30, position.getY() - 60, 30,
                    10, - 20));
            entitys.add(new BulletEntity(hero.getHeader(),
                    1, - 20));
            entitys.add(new BulletEntity(position.getX() + 60, position.getY() - 60, -30,
                    10, - 20));
            entitys.add(new BulletEntity(position.getX() + 45, position.getY() - 45, -60,
                    10, - 20));
        }
        new SysMove(entitys);
        new SysCollision(entitys);
        new SysBorder(entitys);
        new SysLive(entitys);
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        graphics.drawString("FPS: " + gameContainer.getFPS(), 10F, 10F);
        graphics.drawString("Entities: " + entitys.size(), 10F, 34F);
        for (Entity entity:entitys) {
            entity.render(gameContainer, graphics);
        }
        hero.render(gameContainer, graphics);
    }

    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
        hero.setMouse(newx, newy);
        super.mouseMoved(oldx, oldy, newx, newy);
    }

    @Override
    public void keyPressed(int key, char c) {
        switch (key) {
            case 57: shoot = true;
        }
        super.keyPressed(key, c);
    }

    @Override
    public void keyReleased(int key, char c) {
        switch (key) {
            case 57: shoot = false;
        }
        super.keyReleased(key, c);
    }
}
