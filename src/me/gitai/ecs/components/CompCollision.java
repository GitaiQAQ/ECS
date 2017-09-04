package me.gitai.ecs.components;

import me.gitai.ecs.Component;

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
}
