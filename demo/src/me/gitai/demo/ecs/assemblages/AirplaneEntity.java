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
 * 飞机实体类
 */
public class AirplaneEntity extends Entity {
    private Image airplane;

    private CompAppearance appearance;
    private CompHealth health;
    private CompPosition position;
    private CompCollision collision;

    public AirplaneEntity() throws SlickException {
        this.airplane = Resource.getInstance().drawable.getAirplane();
        appearance =
                new CompAppearance(20);
        health =
                new CompHealth((int)(Math.random() * 100));
        position =
                new CompPosition((int)(Math.random() * 90) - 45,
                        (int)(Math.random() * Config.width) - 100,
                        (int)(Math.random() * (Config.height / 3)) - 100);
        collision =
                new CompCollision();

        ComMoveable moveable = new ComMoveable((int) (Math.random() * 5),
                (int) (Math.random() * 4) - 2);

        ComBorder border = new ComBorder(
                new Rect(-100, -100,
                        Config.width + 200, Config.height + 200));

        addComponent(appearance);
        addComponent(new ComOwnership(this));
        addComponent(health);
        addComponent(position);
        addComponent(collision);
        addComponent(moveable);
        addComponent(border);
    }

    public AirplaneEntity setOnCollision(CompCollision.CollisionCallback collisionCallback) {
        collision.setCollisionCallback(collisionCallback);
        return this;
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) {
        super.render(gameContainer, graphics);
        airplane.setRotation((float) - Math.toDegrees(position.getDegree()));
        airplane.setAlpha((float) health.getPercentage());
        graphics.drawImage(airplane, position.getX(), position.getY());
        graphics.drawString(String.valueOf(health.getValue()), position.getX(), position.getY());
    }

    public CompPosition getPosition() {
        return position;
    }

    public CompPosition getHeader() {
        int l = appearance.getHeight() * 2;
        double x1 = Math.sin(position.getDegree()) * l;
        double y1 = Math.cos(position.getDegree()) * l;
        // System.out.printf("%f %f %f\n", x1, y1, Math.toDegrees(position.getDegree()));
        return new CompPosition(Math.toDegrees(position.getDegree())  ,
            position.getX() + appearance.getWidth()/2 + x1,
            position.getY() + appearance.getWidth()/2 + y1);
    }
}
