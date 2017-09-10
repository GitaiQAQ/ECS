package me.gitai.demo.ecs.assemblages;

import me.gitai.demo.ecs.Demo;
import me.gitai.ecs.Entity;
import me.gitai.ecs.components.CompAppearance;
import me.gitai.ecs.components.CompCollision;
import me.gitai.ecs.components.CompHealth;
import me.gitai.ecs.components.CompPosition;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by Gitai.me on 9/9/17.
 */
public class HeroEntity extends Entity {
    private Image hero0, hero1, hero;

    private CompAppearance appearance;
    private CompHealth health;
    private CompPosition position;
    private CompCollision collision;


    public HeroEntity() throws SlickException {
        hero0 = new Image("assets/img/hero0.png");
        hero = hero1 = new Image("assets/img/hero1.png");

        appearance =
                new CompAppearance((int)(20));
        health =
                new CompHealth(500);
        position =
                new CompPosition(Demo.WIDTH / 2, Demo.HEIGHT - 40);
        collision =
                new CompCollision();

        addComponent(appearance);
        addComponent(health);
        addComponent(position);
        addComponent(collision);
    }

    public void updateHero(int i) {
        hero = (i%2==0)?hero0:hero1;
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

    public CompPosition getTop() {
        return new CompPosition(position.getDegree(),
                position.getX() + 45,
                position.getY() - 45);
    }

    public boolean isDead() {
        return health.getValue() > 0;
    }
}
