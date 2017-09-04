package me.gitai.ecs;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.HashMap;

/**
 * Created by Gitai.me on 9/8/17.
 */
public class Entity {
    private String name = this.getClass().getName();

    public String getName() {
        return name;
    }

    private HashMap<String, Component> components = new HashMap<>();

    public Entity addComponent(Component component) {
        components.put(component.getName(), component);
        return this;
    }

    public Entity removeComponent(String name) {
        components.remove(name);
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
        return new StringBuilder(super.toString())
                .append(components.keySet())
                .toString();
    }
}
