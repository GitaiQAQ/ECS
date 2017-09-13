package me.gitai.ecs;

import java.util.List;

/**
 * Created by Gitai.me(i@gitai.me) on 9/10/17.
 * 基类
 */
public class System {
    protected List<Entity> mEntities;

    public System(List<Entity> entities) {
        this.mEntities = entities;
    }

    public void update(int entityId) { }

    public List<Entity> getEntities() {
        return mEntities;
    }
}
