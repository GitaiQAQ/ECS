package me.gitai.ecs.systems;

import me.gitai.ecs.Entity;
import me.gitai.ecs.components.*;

import java.util.List;

/**
 * Created by Gitai.me on 9/9/17.
 */
public class SysBorder {
    public SysBorder(List<Entity> entitys) {
        for (int i = 0; i < entitys.size(); i++) {
            Entity entity = entitys.get(i);

            if (entity.hasComponent(ComBorder.getStaticName()) && isOut(entity)) {
                CompHealth health = (CompHealth)entity.getComponent(CompHealth.getStaticName());
                health.setValue(-1);
            }
        }
    }

    public boolean isOut(Entity entity) {
        ComBorder broder = (ComBorder)entity.getComponent(ComBorder.getStaticName());
        CompPosition position = (CompPosition)entity.getComponent(CompPosition.getStaticName());
        int size = ((CompAppearance)entity.getComponent(CompAppearance.getStaticName())).getSize();
        Rect rect = new Rect(
                position.getX() - size,
                position.getY() - size,
                size*2,
                size*2);

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
