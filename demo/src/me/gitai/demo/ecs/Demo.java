package me.gitai.demo.ecs;


import me.gitai.demo.ecs.assemblages.AirplaneEntity;
import me.gitai.demo.ecs.assemblages.BulletEntity;
import me.gitai.demo.ecs.assemblages.HeroEntity;
import me.gitai.ecs.BaseSystem;
import me.gitai.ecs.Entity;
import me.gitai.ecs.components.CompCollision;
import me.gitai.ecs.components.CompPosition;
import me.gitai.ecs.systems.*;
import org.newdawn.slick.*;

import javax.annotation.Resource;
import java.io.File;
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
        AppGameContainer app = new AppGameContainer(new Demo("Demo"));
        app.setDisplayMode(WIDTH, HEIGHT, false);
        app.setTargetFrameRate(100);
        app.start();
    }

    public Demo(String title) {
        super(title);
    }

    private List<Entity> entitys = new ArrayList<>();
    private HeroEntity hero;
    private boolean shoot = false;

    private Image airplane;

    private BaseSystem sys_move, sys_collision, sys_border, sys_score, sys_live;

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        hero = new HeroEntity();

        airplane = new Image("assets/img/airplane.png");
        entitys.add(hero);

        for (int i = 0; i < 2; i++) {
            entitys.add(new AirplaneEntity(airplane));
        }

        gameContainer.setShowFPS(false);

        gameContainer.getGraphics()
                .setColor(new Color(0, 0, 0));
        gameContainer.getGraphics()
                .setBackground(new Color(13*17, 13*17, 13*17));

        sys_move        = new SysMove(entitys);
        sys_collision   = new SysCollision(entitys);
        sys_border      = new SysBorder(entitys);
        sys_score       = new SysScore(entitys, 0);
        sys_live        = new SysLive(entitys);
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
                        entity,
                        position,
                        1, 15));
            }
        }

        if (shoot) {
            CompPosition position = hero.getPosition();
            entitys.add(new BulletEntity(hero, position.getX() + 45, position.getY() - 45, 60,
                    1, - 20));
            entitys.add(new BulletEntity(hero, position.getX() + 30, position.getY() - 60, 30,
                    10, - 20));
            entitys.add(new BulletEntity(hero, hero.getTop(),
                    1, - 20)
                    .setOnCollision(new CompCollision.CollisionCallback(){
                        @Override
                        public void OnCollision() throws SlickException {
                            entitys.add(new BulletEntity(hero, 100, (int)(Math.random() * 500.0), -30,
                                    10,  -10));
                            entitys.add(new BulletEntity(hero, Demo.WIDTH - 100, (int)(Math.random() * 500.0),
                                    30,10,  -10));
                            super.OnCollision();
                        }
                    }));
            entitys.add(new BulletEntity(hero, position.getX() + 60, position.getY() - 60, -30,
                    10, - 20));
            entitys.add(new BulletEntity(hero, position.getX() + 45, position.getY() - 45, -60,
                    10, - 20));
        }

        sys_move.update();
        sys_collision.update();
        sys_border.update();
        sys_score.update();
        sys_live.update();
        if(hero.isDead()) gameContainer.pause();
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        graphics.drawString("FPS: " + gameContainer.getFPS(), 10F, 10F);
        graphics.drawString("Entities: " + entitys.size(), 10F, 34F);
        graphics.drawString("Score: " + ((SysScore)sys_score).getScore(), 10F, 58F);

        for (Entity entity:entitys) {
            entity.render(gameContainer, graphics);
        }

        hero.render(gameContainer, graphics);
    }

    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
        hero.setMouse(newx - 45, newy - 45);
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
