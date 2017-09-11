package me.gitai.ecs.systems;

import me.gitai.ecs.System;
import me.gitai.ecs.Entity;
import me.gitai.ecs.components.ComAttenuation;

import java.util.List;

/**
 * Created by Gitai.me(i@gitai.me) on 9/10/17.
 */
public class SysAttenuation extends System {
    public SysAttenuation(List<Entity> mEntitys) {
        super(mEntitys);
    }

    @Override
    public void update(int entityId) {
        if (getEntities().size() > entityId) {
            Entity entity = getEntities().get(entityId);

            if (entity.hasComponent(ComAttenuation.getStaticName())) {
                ComAttenuation attenuation = ((ComAttenuation)entity.getComponent(ComAttenuation.getStaticName()));
                attenuation.setDurable(attenuation.getDurable() - 1);
                if (attenuation.getDurable() <= 0) getEntities().remove(entityId);
            }
        }
    }
}
