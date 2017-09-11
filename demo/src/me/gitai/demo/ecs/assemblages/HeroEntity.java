package me.gitai.demo.ecs.assemblages;

import me.gitai.demo.ecs.Demo;
import me.gitai.demo.ecs.Resource;
import me.gitai.demo.ecs.states.PlayState;
import me.gitai.ecs.Entity;
import me.gitai.ecs.components.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by Gitai.me on 9/9/17.
 */
public class HeroEntity extends Entity {
    private CompAppearance appearance;
    private CompHealth health;
    private CompPosition position;
    private CompCollision collision;
    private Image hero;


    public HeroEntity() throws SlickException {
        appearance =
                new CompAppearance(20);
        health =
                new CompHealth(500);
        position =
                new CompPosition(Demo.WIDTH / 2, Demo.HEIGHT - 40);
        collision =
                new CompCollision();

        addComponent(appearance);
        addComponent(new ComOwnership(this));
        addComponent(health);
        addComponent(position);
        addComponent(collision);
    }

    public void updateHero(int i) {
        hero = Resource.getInstance().drawable.getHero()[i%2];
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) {
        super.render(gameContainer, graphics);
        graphics.drawImage(hero, position.getX(), position.getY());
        graphics.drawString(String.valueOf(health.getValue()), position.getX(), position.getY());
    }

    public void setMouse(int newx, int newy) {
        position.setX(newx);
        position.setY(newy);
    }

    public CompPosition getPosition() {
        return position;
    }

    public CompHealth getHealth() {
        return health;
    }

    public CompPosition getTop() {
        return new CompPosition(position.getDegree(),
                position.getX() + 45,
                position.getY() - 45);
    }

    public boolean isDead() {
        return health.getValue() <= 0;
    }
}
