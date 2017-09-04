package me.gitai.ecs.components;

import me.gitai.ecs.*;

/**
 * Created by Gitai.me on 9/8/17.
 */
public class CompHealth extends Component {

    public static String getStaticName() {
        return CompHealth.class.getName();
    }

    private int max = 20;
    private int value = 20;

    public CompHealth() {
        this.value = 20;
    }

    public CompHealth(int value) {
        this.max = value;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public double getPercentage() {
        return this.value / (this.max*1.0);
    }

    public int compareTo(CompHealth health) {
        return value < health.value?-1:(health.value == value?0:1);
    }
}
