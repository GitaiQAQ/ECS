package me.gitai.ecs.components;

import me.gitai.ecs.Component;

/**
 * Created by Gitai.me on 9/8/17.
 */
public class CompPlayerControlled extends Component {

    public static String getStaticName() {
        return CompPlayerControlled.class.getName();
    }

    private boolean PC = true;

}
