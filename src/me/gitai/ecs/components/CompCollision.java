package me.gitai.ecs.components;

import me.gitai.ecs.Component;
import me.gitai.ecs.Entity;
import org.newdawn.slick.SlickException;

/**
 * Created by Gitai.me on 9/8/17.
 */
public class CompCollision extends Component {

    public static String getStaticName() {
        return CompCollision.class.getName();
    }

    private boolean collides = true;
    private boolean elastic = false;

    public CompCollision() {
    }

    public CompCollision(boolean elastic) {
        this.elastic = elastic;
    }

    public CompCollision(boolean collides, boolean elastic) {
        this.collides = collides;
        this.elastic = elastic;
    }

    private CollisionCallback mCollisionCallback = null;

    public CompCollision setCollisionCallback(CollisionCallback collisionCallback) {
        this.mCollisionCallback = collisionCallback;
        return this;
    }

    public boolean whileCollision() throws SlickException {
        if(this.mCollisionCallback!=null) this.mCollisionCallback.OnCollision();
        return true;
    }

    public static class CollisionCallback {
        private Entity top;

        public void OnCollision() throws SlickException {};
    }
}
