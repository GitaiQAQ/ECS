package me.gitai.ecs.components;

import me.gitai.ecs.Component;

/**
 * Created by Gitai.me on 9/8/17.
 */
public class CompPosition extends Component {

    public static String getStaticName() {
        return CompPosition.class.getName();
    }

    private double degree = Math.toRadians(0);
    private double x,y;

    public CompPosition() {
    }

    public CompPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public CompPosition(int degree, int x, int y) {
        this.degree = Math.toRadians(degree);
        this.x = x;
        this.y = y;
    }

    public CompPosition(double degree, double x, double y) {
        this.degree = Math.toRadians(degree);
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return (int)x;
    }

    public int getY() {
        return (int)y;
    }

    public double getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void to(double degree, double speed) {
        this.degree += degree;
        this.x += Math.sin(this.degree) * speed;
        this.y += Math.cos(this.degree) * speed;
    }
}
