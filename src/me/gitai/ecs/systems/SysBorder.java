package me.gitai.ecs.systems;

import me.gitai.ecs.Entity;
import me.gitai.ecs.System;
import me.gitai.ecs.components.ComBorder;
import me.gitai.ecs.components.CompAppearance;
import me.gitai.ecs.components.CompHealth;
import me.gitai.ecs.components.CompPosition;

import java.util.List;

/**
 * Created by Gitai.me on 9/9/17.
 * 边界系统
 */
public class SysBorder extends System {
    public SysBorder(List<Entity> entitys) {
        super(entitys);
    }

    @Override
    public void update(int entityId) {
        if (getEntities().size() > entityId) {
            Entity entity = getEntities().get(entityId);

            if (entity.hasComponent(ComBorder.getStaticName()) && isOut(entity)) {
                CompHealth health = (CompHealth) entity.getComponent(CompHealth.getStaticName());
                health.setValue(-1);
            }
        }
    }

    public boolean isOut(Entity entity) {
        ComBorder broder = (ComBorder)entity.getComponent(ComBorder.getStaticName());
        CompPosition position = (CompPosition)entity.getComponent(CompPosition.getStaticName());

        CompAppearance appearance
                = ((CompAppearance)entity.getComponent(CompAppearance.getStaticName()));
        Rect rect = new Rect(
                position.getX() - appearance.getWidth(),
                position.getY() - appearance.getHeight(),
                appearance.getWidth()*2,
                appearance.getHeight()*2);

        return rect.isOut(broder.getRect());
    }

    class Rect extends me.gitai.ecs.common.Rect {
        public Rect(int x, int y, int width, int height) {
            super(x, y, width, height);
        }

        public boolean isOut(me.gitai.ecs.common.Rect rect) {

            return !(getLeft() > rect.getLeft()  &&
                    getTop() > rect.getTop()  &&
                    getRight() < rect.getRight() &&
                    getBottom() < rect.getBottom());
        }
    }
}
