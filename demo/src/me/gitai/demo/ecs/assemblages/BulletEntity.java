package me.gitai.demo.ecs.assemblages;

import me.gitai.demo.ecs.Demo;
import me.gitai.ecs.Entity;
import me.gitai.ecs.common.Rect;
import me.gitai.ecs.components.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by Gitai.me on 9/9/17.
 */
public class BulletEntity extends Entity {
    private Image bullet;

    private CompAppearance appearance;
    private CompHealth health;
    private CompPosition position;
    private CompCollision collision;
    private ComMoveable moveable;
    private ComBorder border;


    public BulletEntity(CompPosition position, int _health, int speed) throws SlickException {
        bullet = new Image("assets/img/bullet.png");
        appearance =
                new CompAppearance((int)(5));
        health =
                new CompHealth(_health);
        this.position = position;
        collision =
                new CompCollision();
        moveable =
                new ComMoveable(speed);
        border =
                new ComBorder(new Rect(0, 0, Demo.WIDTH, Demo.HEIGHT));

        addComponent(appearance);
        addComponent(health);
        addComponent(this.position);
        addComponent(collision);
        addComponent(moveable);
        addComponent(border);
    }

    public BulletEntity(int x, int y, int degree, int _health, int speed) throws SlickException {
        this(new CompPosition(degree, x, y), _health, speed);
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) {
        super.render(gameContainer, graphics);
        bullet.setRotation((float) - Math.toDegrees(position.getDegree()));
        graphics.drawImage(bullet, position.getX(), position.getY());
        // graphics.drawString(String.valueOf(health.getValue()), position.getX(), position.getY());
    }
}
