package me.gitai.ecs.components;

import me.gitai.ecs.Component;
import me.gitai.ecs.common.Rect;

/**
 * Created by Gitai.me on 9/9/17.
 * 边界组件，标记元素活动边界
 */
public class ComBorder extends Component {
    public static String getStaticName() {
        return ComBorder.class.getName();
    }

    private Rect rect;

    public ComBorder(Rect rect) {
        this.rect = rect;
    }

    public Rect getRect() {
        return rect;
    }
}

