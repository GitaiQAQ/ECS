package me.gitai.ecs.common;

/**
 * Created by Gitai.me on 9/9/17.
 */
public class Rect {
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    public Rect(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getLeft() {
        return x;
    }

    public int getTop() {
        return y;
    }

    public int getRight() {
        return getLeft() + this.width;
    }

    public int getBottom() {
        return getTop() + this.height;
    }
}
