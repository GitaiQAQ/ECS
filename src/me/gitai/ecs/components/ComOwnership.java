package me.gitai.ecs.components;

import me.gitai.ecs.Component;
import me.gitai.ecs.Entity;

/**
 * Created by Gitai.me(i@gitai.me) on 9/10/17.
 */
public class ComOwnership extends Component {
    private Entity mFrom;

    public ComOwnership(Entity entity) {
        this.mFrom = entity;
    }
}
