package me.gitai.demo.ecs.gui;

import me.gitai.demo.ecs.Resource;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;

/**
 * Created by Gitai.me(i@gitai.me) on 9/13/17.
 * 简单的按钮实现，基于 MouseOverArea 增加了样式和基本反馈事件
 */
public class Buttom extends MouseOverArea {

    private final String mTitle;

    public Buttom(GUIContext container, String title, int x, int y, int width, int height) {
        super(container, null, x, y, width, height);
        this.mTitle = title;
        this.setNormalColor(Color.gray);
        this.setMouseOverColor(Color.white);
    }

    @Override
    public void render(GUIContext container, Graphics graphics) {
        // 鼠标状态监听，改变指针样式
        if (this.isMouseOver()) {
            try {
                ((GameContainer)container)
                        .setMouseCursor(Resource.getInstance().cursor.getHand(), 0, 0);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        } else {
            try {
                ((GameContainer)container)
                        .setMouseCursor(Resource.getInstance().cursor.getArrow(), 0, 0);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }

        super.render(container, graphics);

        // 屏蔽默认参数
        graphics.setFont(Resource.getInstance().font.printHead());
        graphics.setColor(Color.black);

        // 绘制边框和文字
        graphics.drawRect(getX(), getY(), getWidth(), getHeight());
        int titleWidth = graphics.getFont().getWidth(mTitle);
        int titleHeight = graphics.getFont().getHeight(mTitle);
        graphics.drawString(mTitle,
                getX() + (getWidth() - titleWidth)/2, getY() + (getHeight() - titleHeight)/2);

    }

    public static class ExitComponent implements ComponentListener {

        @Override
        public void componentActivated(AbstractComponent abstractComponent) {
            System.exit(0);
        }
    }

    public static class ToggleComponent implements ComponentListener {
        // TODO: 传递引用，修改无法反馈

        private Boolean mVal;

        public ToggleComponent(Boolean val) {
            this.mVal = val;
        }

        @Override
        public void componentActivated(AbstractComponent abstractComponent) {
            this.mVal = !mVal;
        }
    }
}
