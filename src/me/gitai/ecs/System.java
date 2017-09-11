package me.gitai.ecs;

import com.sun.org.apache.xpath.internal.Arg;
import me.gitai.ecs.components.ComBorder;
import me.gitai.ecs.components.CompHealth;
import org.newdawn.slick.SlickException;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Gitai.me(i@gitai.me) on 9/10/17.
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
