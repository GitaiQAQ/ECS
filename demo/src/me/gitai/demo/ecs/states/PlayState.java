package me.gitai.demo.ecs.states;


import me.gitai.demo.ecs.Config;
import me.gitai.ecs.BasicGameState;
import me.gitai.demo.ecs.Data;
import me.gitai.demo.ecs.Demo;
import me.gitai.demo.ecs.assemblages.AirplaneEntity;
import me.gitai.demo.ecs.assemblages.BulletEntity;
import me.gitai.demo.ecs.assemblages.HeroEntity;
import me.gitai.demo.ecs.assemblages.RingBulletEntity;
import me.gitai.ecs.Entity;
import me.gitai.ecs.System;
import me.gitai.ecs.components.CompCollision;
import me.gitai.ecs.components.CompHealth;
import me.gitai.ecs.components.CompPosition;
import me.gitai.ecs.systems.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gitai.me on 9/8/17.
 * http://vasir.net/blog/game-development/how-to-build-entity-component-system-in-javascript
 */
public class PlayState extends BasicGameState {
    private List<Entity> mEntities = new ArrayList<>();
    private HeroEntity hero;
    private boolean shoot = false;

    private System sys_move, sys_collision, sys_border, sys_score, sys_live, sys_attenuation;
    private long time;

    public PlayState() {
        super();
    }

    @Override
    public int getID() {
        return 3;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame)
            throws SlickException {
        super.init(gameContainer, stateBasedGame);
        time = java.lang.System.currentTimeMillis();
        hero = new HeroEntity();
        mEntities.add(hero);

        //for (int i = 0; i < 2; i++) {
        //    entitys.add(new AirplaneEntity());
        //}

        gameContainer.setShowFPS(false);

        gameContainer.getGraphics()
                .setColor(new Color(0, 0, 0));

        sys_move        = new SysMove(mEntities);
        sys_collision   = new SysCollision(mEntities);
        sys_border      = new SysBorder(mEntities);
        sys_score       = new SysScore(mEntities, 0);
        sys_live        = new SysLive(mEntities);
        sys_attenuation = new SysAttenuation(mEntities);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i)
            throws SlickException {
        hero.updateHero(i);
        mEntities.add(new AirplaneEntity());
        for (int j = 0; j < mEntities.size(); j++) {
            Entity entity = mEntities.get(j);
            if (entity instanceof AirplaneEntity){
                CompPosition position = ((AirplaneEntity)entity).getHeader();
                mEntities.add(new BulletEntity(
                        entity,
                        position,
                        1, 13));
            }
        }

        CompPosition position = hero.getPosition();

        if (shoot) {
            mEntities.add(new BulletEntity(hero,
                    position.getX() + 45, position.getY() - 45, 60,
                    20, - 20));
            mEntities.add(new BulletEntity(hero,
                    position.getX() + 30, position.getY() - 60, 30,
                    20, - 20));
            mEntities.add(new BulletEntity(hero,
                    hero.getTop(), 30, - 20));
            mEntities.add(new BulletEntity(hero,
                    position.getX() + 60, position.getY() - 60, -30,
                    20, - 20));
            mEntities.add(new BulletEntity(hero,
                    position.getX() + 45, position.getY() - 45, -60,
                    20, - 20));
        } else {
            hero.getHealth().setValueDouble(hero.getHealth().getValueDouble() + 1);
            mEntities.add(new RingBulletEntity(hero,
                    position.getX() + 90 + 80, position.getY() + 45, 180,
                    4, 50, 9));
            mEntities.add(new BulletEntity(hero, hero.getTop(),
                    50, - 20)
                    .setOnCollision(new CompCollision.CollisionCallback(){
                        @Override
                        public void OnCollision() throws SlickException {
                            mEntities.add(new BulletEntity(hero,
                                    100, (int)(Math.random() * 500.0), -30,
                                    10,  -10));
                            mEntities.add(new BulletEntity(hero,
                                    Config.width - 100, (int)(Math.random() * 500.0), 30,
                                    10,  -10));
                            super.OnCollision();
                        }
                    }));
            mEntities.add(new RingBulletEntity(hero,
                    position.getX() - 80, position.getY() + 45, 180,
                    - 4, 50, 9));
        }


        for (int entityId1 = mEntities.size() - 1; entityId1 >= 0 ; entityId1 --) {
            sys_move.update(entityId1);
            if (mEntities.size() > entityId1) {
                Entity entity1 = mEntities.get(entityId1);
                if(entity1.hasComponent(CompCollision.getStaticName()) &&
                        entity1.hasComponent(CompPosition.getStaticName()) &&
                        entity1.hasComponent(CompHealth.getStaticName())) {

                    for (int entityId2 = mEntities.size()-1; entityId2 >=entityId1 ; entityId2--) {
                        ((SysCollision)sys_collision).update(entityId1, entityId2);
                    }
                }
            }
            sys_score.update(entityId1);
            sys_border.update(entityId1);
            sys_attenuation.update(entityId1);
            sys_live.update(entityId1);
        }
        if(hero.isDead()) {
            Data.getInstance().setTime(String.valueOf((java.lang.System.currentTimeMillis() - time)/1000));
            Data.getInstance().setmScore(((SysScore)sys_score).getHumanReadable());
            stateBasedGame.enterState(0);
        }
    }
    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics)
            throws SlickException {
        super.render(gameContainer, stateBasedGame, graphics);
        graphics.drawString("FPS: " + gameContainer.getFPS(), 10F, 10F);
        graphics.drawString("Entities: " + mEntities.size(), 10F, 34F);
        graphics.drawString("Score: " + ((SysScore)sys_score).getHumanReadable(), 10F, 58F);

        for (Entity entity:mEntities) {
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
