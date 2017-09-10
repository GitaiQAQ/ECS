package me.gitai.ecs.components;

import me.gitai.ecs.Component;

/**
 * Created by Gitai.me on 9/8/17.
 */
public class ComMoveable extends Component {
    private double speed = 1;
    private double degree = Math.toRadians(0);

    public ComMoveable(double speed, double degree) {
        this.speed = speed;
        this.degree = Math.toRadians(degree);
    }

    public ComMoveable(int speed) {
        this.speed = speed;
    }

    public static String getStaticName() {
        return ComMoveable.class.getName();
    }

    public double getSpeed() {
        return speed;
    }

    public void setDegree(double degree) {
        this.degree = Math.toRadians(degree);
    }

    public double getDegree() {
        return degree;
    }

    public void to(CompPosition position) {
        position.to(degree, speed);
    }
}
