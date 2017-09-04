package me.gitai.ecs.systems;

import me.gitai.ecs.Entity;
import me.gitai.ecs.components.ComMoveable;
import me.gitai.ecs.components.CompCollision;
import me.gitai.ecs.components.CompHealth;
import me.gitai.ecs.components.CompPosition;

import java.util.List;

/**
 * Created by Gitai.me on 9/8/17.
 */
public class SysMove {
    public SysMove(List<Entity> entities) {
        for (Entity entity:entities) {
            if(entity.hasComponent(ComMoveable.getStaticName())){
                CompPosition position = (CompPosition)entity.getComponent(CompPosition.getStaticName());
                ComMoveable moveable = (ComMoveable)entity.getComponent(ComMoveable.getStaticName());

                moveable.to(position);
            }
        }
    }
}
