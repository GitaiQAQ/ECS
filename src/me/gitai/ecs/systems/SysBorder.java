package me.gitai.ecs.systems;

import me.gitai.ecs.BaseSystem;
import me.gitai.ecs.Entity;
import me.gitai.ecs.components.*;
import org.newdawn.slick.SlickException;

import java.util.List;

/**
 * Created by Gitai.me on 9/9/17.
 */
public class SysBorder extends BaseSystem {
    public SysBorder(List<Entity> entitys) {
        super(entitys);
    }

    @Override
    public void update() {
        super.update();
        for (int i = 0; i < mEntitys.size(); i++) {
            Entity entity = mEntitys.get(i);

            if (entity.hasComponent(ComBorder.getStaticName()) && isOut(entity)) {
                CompHealth health = (CompHealth)entity.getComponent(CompHealth.getStaticName());
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
