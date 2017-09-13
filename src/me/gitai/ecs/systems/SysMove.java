package me.gitai.ecs.systems;

import me.gitai.ecs.Entity;
import me.gitai.ecs.System;
import me.gitai.ecs.components.ComMoveable;
import me.gitai.ecs.components.CompPosition;

import java.util.List;

/**
 * Created by Gitai.me on 9/8/17.
 * 移动系统
 */
public class SysMove extends System {
    public SysMove(List<Entity> entities) {
        super(entities);
    }

    @Override
    public void update(int entityId) {
        Entity entity = mEntities.get(entityId);
        if(entity.hasComponent(ComMoveable.getStaticName())){
            CompPosition position = (CompPosition)entity.getComponent(CompPosition.getStaticName());
            ComMoveable moveable = (ComMoveable)entity.getComponent(ComMoveable.getStaticName());

            moveable.to(position);
        }
    }
}
