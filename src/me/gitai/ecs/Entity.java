package me.gitai.ecs;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.HashMap;

/**
 * Created by Gitai.me on 9/8/17.
 * 基类
 */
public class Entity {
    private String name = this.getClass().getName();

    public String getName() {
        return name;
    }

    private HashMap<String, Component> components = new HashMap<String, Component>();

    public Entity addComponent(Component component) {
        components.put(component.getName(), component);
        return this;
    }

    public Component getComponent(String name) {
        return components.get(name);
    }

    public boolean hasComponent(String name) {
        return components.containsKey(name);
    }

    public void render(GameContainer gameContainer, Graphics graphics) {
        // System.out.printf("Render: %s \n", name);
    }

    @Override
    public String toString() {
        return super.toString() +
                components.keySet();
    }
}
