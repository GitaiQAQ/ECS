package me.gitai.ecs.components;

import me.gitai.ecs.Component;

/**
 * Created by Gitai.me on 9/8/17.
 * 基础属性组件
 */
public class CompAppearance extends Component {
    private int color;

    private int width;
    private int height;

    public CompAppearance() {
    }

    public CompAppearance(int size) {
        this.width = this.height = size;
    }

    public CompAppearance(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public static String getStaticName() {
        return CompAppearance.class.getName();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
