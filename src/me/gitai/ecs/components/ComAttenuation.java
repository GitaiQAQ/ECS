package me.gitai.ecs.components;

import me.gitai.ecs.Component;

/**
 * Created by Gitai.me(i@gitai.me) on 9/10/17.
 */
public class ComAttenuation extends Component {
    public static String getStaticName() {
        return ComAttenuation.class.getName();
    }

    protected int durable;


    public ComAttenuation(int durable) {
        this.durable = durable;
    }

    public int getDurable() {
        return durable;
    }

    public void setDurable(int durable) {
        this.durable = durable;
    }
}
