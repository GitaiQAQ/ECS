package me.gitai.demo.ecs.assemblages;

import me.gitai.ecs.Entity;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by Gitai.me(i@gitai.me) on 9/10/17.
 * 环形掩体实体类
 */
public class RingBulletEntity extends BulletEntity {
    public RingBulletEntity(Entity from, int x, int y, int degree, int degree1, int _health, int speed) throws SlickException {
        super(from, x, y, degree, _health, speed);
        moveable.setDegree(degree1);
        attenuation.setDurable(20);
    }
}
