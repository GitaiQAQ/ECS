package me.gitai.ecs.components;

import me.gitai.ecs.Component;

/**
 * Created by Gitai.me on 9/8/17.
 */
public class ComString extends Component {

    public static String getStaticName() {
        return ComString.class.getName();
    }

    private String str;

    public ComString(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
