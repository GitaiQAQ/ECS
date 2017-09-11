package me.gitai.demo.ecs;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Gitai.me(i@gitai.me) on 9/11/17.
 */
public class Resource {
    private static Resource ourInstance = new Resource();
    public Cursor cursor = null;
    public Drawable drawable = null;
    public Font font = null;

    public static Resource getInstance() {
        return ourInstance;
    }

    private Resource() {
        try {
            cursor = new Cursor();
            drawable = new Drawable();
            font = new Font();

        } catch (SlickException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class Cursor {
        private Image arrow, hand, appStarting, wait = null;
        public Cursor() throws SlickException {
            arrow       = new Image("assets/cursor/Arrow.png");
            hand        = new Image("assets/cursor/Hand.png");
            appStarting = new Image("assets/cursor/AppStarting/upload.png");
            wait        = new Image("assets/cursor/Wait/upload.png");
        }

        public Image getArrow() {
            return arrow;
        }

        public Image getHand() {
            return hand;
        }

        public Image getAppStarting() {
            return appStarting;
        }

        public Image getWait() {
            return wait;
        }

        public String getAnimatedWait() {
            return "assets/cursor/AppStarting.ani";
        }
    }

    public class Drawable {
        private final Image background, airplane, bullet, transparent;
        private final Image[] hero;

        public Drawable() throws SlickException {
            background = new Image("assets/img/background.png");
            airplane = new Image("assets/img/airplane.png");
            bullet = new Image("assets/img/bullet.png");
            transparent = bullet.copy();
            transparent.setAlpha(0);
            hero = new Image[]{
                    new Image("assets/img/hero0.png"),
                    new Image("assets/img/hero1.png")
            };
        }

        public Image getBackground() {
            return background;
        }

        public Image getAirplane() {
            return airplane.copy();
        }

        public Image getBullet() {
            return bullet.copy();
        }

        public Image getTransparent() {
            return transparent;
        }

        public Image[] getHero() {
            return hero;
        }
    }

    public class Font {
        public java.awt.Font font;
        private TrueTypeFont printHead;
        private TrueTypeFont printLabel;
        private TrueTypeFont printBig;
        private TrueTypeFont printBigLogo;
        private TrueTypeFont printMediumLogo;

        private Font() throws IOException, FontFormatException {
            font = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,
                    new File("assets/font/Some Kinda Madness.otf"));

            printLabel = new TrueTypeFont(font.deriveFont(java.awt.Font.BOLD, 18f), true);
            printHead = new TrueTypeFont(font.deriveFont(java.awt.Font.BOLD, 28f), true);
            printBig = new TrueTypeFont(font.deriveFont(java.awt.Font.BOLD, 25f), true);
            printMediumLogo = new TrueTypeFont(font.deriveFont(java.awt.Font.BOLD, 46f), true);
            printBigLogo = new TrueTypeFont(font.deriveFont(java.awt.Font.BOLD, 65f), true);
        }


        public TrueTypeFont printLabel() {
            return printLabel;
        }

        public TrueTypeFont printHead() {
            return printHead;
        }

        public TrueTypeFont printBig() {
            return printBig;
        }

        public TrueTypeFont printMediumLogo() {
            return printMediumLogo;
        }

        public TrueTypeFont printBigLogo() {
            return printBigLogo;
        }
    }
}
