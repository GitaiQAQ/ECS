package me.gitai.ecs.systems;

import me.gitai.ecs.BaseSystem;
import me.gitai.ecs.Entity;
import me.gitai.ecs.components.CompHealth;
import org.newdawn.slick.SlickException;

import java.util.List;

/**
 * Created by Gitai.me on 9/9/17.
 */
public class SysLive extends BaseSystem {
    public SysLive(List<Entity> entities) {
        super(entities);
    }

    @Override
    public void update() {
        for (int i = mEntitys.size()-1; i >=0 ; i--) {
            Entity entity = mEntitys.get(i);
            CompHealth health = (CompHealth)entity.getComponent(CompHealth.getStaticName());

            if (health.getValue() <= 0) {
                entity = null;
                mEntitys.remove(i);
            }
        }
    }
}
