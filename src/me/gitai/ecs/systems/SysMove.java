package me.gitai.ecs.systems;

import me.gitai.ecs.BaseSystem;
import me.gitai.ecs.Entity;
import me.gitai.ecs.components.ComMoveable;
import me.gitai.ecs.components.CompCollision;
import me.gitai.ecs.components.CompHealth;
import me.gitai.ecs.components.CompPosition;
import org.newdawn.slick.SlickException;

import java.util.List;

/**
 * Created by Gitai.me on 9/8/17.
 */
public class SysMove extends BaseSystem {
    public SysMove(List<Entity> entities) {
        super(entities);
    }

    @Override
    public void update() {
        for (Entity entity:mEntitys) {
            if(entity.hasComponent(ComMoveable.getStaticName())){
                CompPosition position = (CompPosition)entity.getComponent(CompPosition.getStaticName());
                ComMoveable moveable = (ComMoveable)entity.getComponent(ComMoveable.getStaticName());

                moveable.to(position);
            }
        }
    }
}
