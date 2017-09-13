package me.gitai.ecs.systems;

import me.gitai.ecs.Entity;
import me.gitai.ecs.System;
import me.gitai.ecs.components.CompHealth;

import java.util.List;

/**
 * Created by Gitai.me on 9/9/17.
 * 存活系统
 */
public class SysLive extends System {
    public SysLive(List<Entity> entities) {
        super(entities);
    }

    @Override
    public void update(int entityId) {
        Entity entity = getEntities().get(entityId);
        CompHealth health = (CompHealth) entity.getComponent(CompHealth.getStaticName());

        if (health.getValue() <= 0) {
            entity = null;
            getEntities().remove(entityId);
        }
    }
}
