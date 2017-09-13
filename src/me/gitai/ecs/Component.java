package me.gitai.ecs;

/**
 * Created by Gitai.me(i@gitai.me) on 9/8/17.
 * 基类
 */
public class Component {
    private String name = this.getClass().getName();

    public String getName() {
        return name;
    }
}
