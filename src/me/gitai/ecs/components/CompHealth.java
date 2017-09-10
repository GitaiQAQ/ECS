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
    private int value = 200;

    public CompHealth() {
        this.value = 20;
    }

    public CompHealth(int value) {
        this.max = value;
        this.value = value * 50;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getValueDouble() {
        return value;
    }

    public void setValueDouble(int value) {
        this.value = value;
    }

    public int getValue() {
        return value/50;
    }

    public void setValue(int value) {
        this.value = value*50;
    }

    public double getPercentage() {
        return this.value / (this.max*1.0);
    }

    public int compareTo(CompHealth health) {
        return value < health.value?-1:(health.value == value?0:1);
    }
}
