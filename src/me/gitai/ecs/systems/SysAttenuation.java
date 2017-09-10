package me.gitai.ecs.systems;

import me.gitai.ecs.BaseSystem;
import me.gitai.ecs.Entity;
import me.gitai.ecs.components.ComAttenuation;
import me.gitai.ecs.components.ComBorder;
import me.gitai.ecs.components.CompHealth;

import java.util.List;

/**
 * Created by Gitai.me(i@gitai.me) on 9/10/17.
 */
public class SysAttenuation extends BaseSystem {
    public SysAttenuation(List<Entity> mEntitys) {
        super(mEntitys);
    }

    @Override
    public void update() {
        super.update();
        for (int i = mEntitys.size()-1; i >=0 ; i--) {
            Entity entity = mEntitys.get(i);

            if (entity.hasComponent(ComAttenuation.getStaticName())) {
                ComAttenuation attenuation = ((ComAttenuation)entity.getComponent(ComAttenuation.getStaticName()));
                attenuation.setDurable(attenuation.getDurable() - 1);
                if (attenuation.getDurable() <= 0) mEntitys.remove(i);
            }
        }
    }
}
