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
public class AirplaneEntity extends Entity {
    Image airplane;

    CompAppearance appearance;
    CompHealth health;
    CompPosition position;
    CompCollision collision;
    ComMoveable moveable;
    ComBorder border;


    public AirplaneEntity(Image airplane) throws SlickException {
        this.airplane = airplane.copy();
        appearance =
                new CompAppearance((int)(20));
        health =
                new CompHealth((int)(Math.random() * 100));
        position =
                new CompPosition((int)(Math.random() * 90) - 45,
                        (int)(Math.random() * Demo.WIDTH),
                        (int)(Math.random() * (Demo.HEIGHT / 2)));
        collision =
                new CompCollision();
        moveable =
                new ComMoveable((int)(Math.random() * 8),
                        (int)(Math.random() * 4) - 2);
        border =
                new ComBorder(new Rect(0, 0, Demo.WIDTH, Demo.HEIGHT));

        addComponent(appearance);
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
        // graphics.drawString(String.valueOf(health.getValue()), position.getX(), position.getY());
    }

    public CompPosition getPosition() {
        return position;
    }

    public CompPosition getHeader() {
        int l = appearance.getSize() * 2;
        double x1 = Math.sin(position.getDegree()) * l;
        double y1 = Math.cos(position.getDegree()) * l;
        // System.out.printf("%f %f %f\n", x1, y1, Math.toDegrees(position.getDegree()));
        return new CompPosition(Math.toDegrees(position.getDegree())  ,
            position.getX() + appearance.getSize()/2 + x1,
            position.getY() + appearance.getSize()/2 + y1);
    }
}
