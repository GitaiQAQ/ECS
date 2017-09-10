package me.gitai.ecs.systems;

import me.gitai.ecs.BaseSystem;
import me.gitai.ecs.Entity;
import me.gitai.ecs.components.CompHealth;
import org.newdawn.slick.SlickException;

import java.util.List;

/**
 * Created by Gitai.me(i@gitai.me) on 9/10/17.
 */
public class SysScore extends BaseSystem{
    private long mScore;
    public SysScore(List<Entity> entities, long score) {
        super(entities);
    }

    public long getScore() {
        return mScore;
    }

    @Override
    public void update() {
        for (int i = mEntitys.size()-1; i >=0 ; i--) {
            Entity entity = mEntitys.get(i);
            CompHealth health = (CompHealth)entity.getComponent(CompHealth.getStaticName());

            if (health.getValue() <= 0) {
                mScore += health.getMax();
            }
        }
    }
}
