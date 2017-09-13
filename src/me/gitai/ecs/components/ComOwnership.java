package me.gitai.ecs.components;

import me.gitai.ecs.Component;
import me.gitai.ecs.Entity;

/**
 * Created by Gitai.me(i@gitai.me) on 9/10/17.
 * 所有权组件，规定元素所有权，用于记分和排除队友伤害
 */
public class ComOwnership extends Component {
    public static String getStaticName() {
        return ComOwnership.class.getName();
    }

    private Entity mFrom;

    public ComOwnership(Entity entity) {
        this.mFrom = entity;
    }

    public Entity getFrom() {
        return mFrom;
    }
}
