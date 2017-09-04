package me.gitai.ecs.systems;

import me.gitai.ecs.Entity;
import me.gitai.ecs.components.CompHealth;

import java.util.List;

/**
 * Created by Gitai.me on 9/9/17.
 */
public class SysLive {
    public SysLive(List<Entity> entities) {
        for (int i = entities.size()-1; i >=0 ; i--) {
            Entity entity = entities.get(i);
            CompHealth health = (CompHealth)entity.getComponent(CompHealth.getStaticName());

            if (health.getValue() <= 0) {
                    entity = null;
                    entities.remove(i);
                }
        }
    }
}
