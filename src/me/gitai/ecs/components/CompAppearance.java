package me.gitai.ecs.components;

import me.gitai.ecs.Component;

/**
 * Created by Gitai.me on 9/8/17.
 */
public class CompAppearance extends Component {
    private int color;
    private int size;

    public CompAppearance() {
    }

    public CompAppearance(int size) {
        this.size = size;
    }

    public static String getStaticName() {
        return CompAppearance.class.getName();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
