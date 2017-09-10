package me.gitai.ecs;

import me.gitai.ecs.components.ComBorder;
import me.gitai.ecs.components.CompHealth;
import org.newdawn.slick.SlickException;

import java.util.List;

/**
 * Created by Gitai.me(i@gitai.me) on 9/10/17.
 */
public class BaseSystem {
    protected List<Entity> mEntitys;

    public BaseSystem(List<Entity> mEntitys) {
        this.mEntitys = mEntitys;
    }

    public void update() {

    }
}
