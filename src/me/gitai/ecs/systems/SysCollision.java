package me.gitai.ecs.systems;

import me.gitai.ecs.BaseSystem;
import me.gitai.ecs.Entity;
import me.gitai.ecs.components.*;
import org.newdawn.slick.SlickException;

import java.util.List;

/**
 * Created by Gitai.me on 9/8/17.
 */
public class SysCollision extends BaseSystem{
    public SysCollision(List<Entity> entities) {
        super(entities);
    }

    @Override
    public void update() {
        for (int entityid1 = mEntitys.size() -1; entityid1 >=0 ; entityid1--) {
            Entity entity1 = mEntitys.get(entityid1);

            if(entity1.hasComponent(CompCollision.getStaticName()) &&
                    entity1.hasComponent(CompPosition.getStaticName()) &&
                    entity1.hasComponent(CompHealth.getStaticName())) {
                for (int entityid2 = mEntitys.size() - 1; entityid2 >=entityid1 ; entityid2--) {
                    if (mEntitys.size() < entityid2) continue;
                    Entity entity2 = mEntitys.get(entityid2);

                    if(entity2 != entity1 &&
                            entity2.hasComponent(CompCollision.getStaticName()) &&
                            entity2.hasComponent(CompPosition.getStaticName()) &&
                            entity2.hasComponent(CompHealth.getStaticName())){

                        if (isCollision(entity1, entity2)) {
                            try {
                                ((CompCollision)entity1.getComponent(CompCollision.getStaticName()))
                                        .whileCollision();
                                ((CompCollision)entity2.getComponent(CompCollision.getStaticName()))
                                        .whileCollision();
                            } catch (SlickException e) {
                                e.printStackTrace();
                            }
                            killEntity(entity1, entity2);
                        }
                    }
                }
            }
        }
    }

    private boolean isCollision(Entity entity1, Entity entity2) {
        CompPosition position1 = (CompPosition)entity1.getComponent(CompPosition.getStaticName());
        CompPosition position2 = (CompPosition)entity2.getComponent(CompPosition.getStaticName());

        ComOwnership ownership1 = (ComOwnership) entity1.getComponent(ComOwnership.getStaticName());
        ComOwnership ownership2 = (ComOwnership) entity2.getComponent(ComOwnership.getStaticName());

        if (ownership1.getFrom().equals(ownership2.getFrom())) return false;

        CompAppearance appearance1
                = ((CompAppearance)entity1.getComponent(CompAppearance.getStaticName()));
        CompAppearance appearance2
                = ((CompAppearance)entity1.getComponent(CompAppearance.getStaticName()));

        Rect rect1 = new Rect(
                position1.getX() - appearance1.getWidth(),
                position1.getY() - appearance1.getHeight(),
                appearance1.getWidth()*2,
                appearance1.getHeight()*2);
        Rect rect2 = new Rect(
                position2.getX() - appearance1.getWidth(),
                position2.getY() - appearance1.getHeight(),
                appearance1.getWidth()*2,
                appearance1.getHeight()*2);

        return rect1.isCollision(rect2);
    }

    private void killEntity(Entity entity1, Entity entity2) {
        CompHealth health1 = (CompHealth) entity1.getComponent(CompHealth.getStaticName());
        CompHealth health2 = (CompHealth) entity2.getComponent(CompHealth.getStaticName());

        if (health1.getValue() <= 0 || health2.getValue() <= 0 ) return;

        health1.setValue(health1.getValue() - health2.getValue());
        health2.setValue(- health1.getValue());
    }

    class Rect extends me.gitai.ecs.common.Rect {
        public Rect(int x, int y, int width, int height) {
            super(x, y, width, height);
        }

        public boolean isCollision(Rect rect) {
            return (getLeft() < rect.getRight() &&
                    getRight() > rect.getLeft() &&
                    getTop() < rect.getBottom() &&
                    getBottom() > rect.getTop());
        }
    }
}
