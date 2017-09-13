package me.gitai.demo.ecs.assemblages;

import me.gitai.demo.ecs.Config;
import me.gitai.demo.ecs.Demo;
import me.gitai.demo.ecs.Resource;
import me.gitai.demo.ecs.states.PlayState;
import me.gitai.ecs.Entity;
import me.gitai.ecs.common.Rect;
import me.gitai.ecs.components.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by Gitai.me on 9/9/17.
 * 子弹实体类
 */
public class BulletEntity extends Entity {
    private Image bullet;

    private CompAppearance appearance;
    private CompHealth health;
    private ComOwnership ownership;
    private CompPosition position;
    private CompCollision collision;
    protected ComMoveable moveable;
    private ComBorder border;
    protected ComAttenuation attenuation;

    public BulletEntity(Entity from, CompPosition position, int _health, int speed) throws SlickException {
        this.bullet = Resource.getInstance().drawable.getBullet();

        appearance =
                new CompAppearance(2, 5);
        health =
                new CompHealth(_health);
        ownership = new ComOwnership(from);
        this.position = position;
        collision =
                new CompCollision();

        moveable =
                new ComMoveable(speed);
        border =
                new ComBorder(new Rect(0, 0, Config.width, Config.height));

        attenuation = new ComAttenuation(500);

        addComponent(appearance);
        addComponent(ownership);
        addComponent(health);
        addComponent(this.position);
        addComponent(collision);
        addComponent(moveable);
        addComponent(border);
        addComponent(attenuation);
    }

    public BulletEntity(Entity from, int x, int y, int degree, int _health, int speed) throws SlickException {
        this(from, new CompPosition(degree, x, y), _health, speed);
    }

    public BulletEntity setOnCollision(CompCollision.CollisionCallback collisionCallback) {
        collision.setCollisionCallback(collisionCallback);
        return this;
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) {
        super.render(gameContainer, graphics);
        bullet.setRotation((float) - Math.toDegrees(position.getDegree()));
        graphics.drawImage(bullet, position.getX(), position.getY());
        // graphics.drawString(String.valueOf(health.getValue()), position.getX(), position.getY());
    }


    public CompPosition getTop() {
        return new CompPosition(position.getDegree(),
                position.getX() + 5,
                position.getY() - 5);
    }
}
