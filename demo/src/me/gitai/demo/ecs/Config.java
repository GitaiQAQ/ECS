package me.gitai.demo.ecs;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.io.File;

/**
 * Created by Gitai.me(i@gitai.me) on 9/13/17.
 * 全局静态参数储存类
 */
public class Config {
    public static int width = 1366;  // 640
    public static int height = 768;  // 480

    @Parameter(names = { "-f", "-fullscreen" }, description = "Display fullscreen")
    public static boolean fullscreen = false;

    @Parameter(names = { "-b", "-baseDir" }, description = "BaseDir Path")
    public static String baseDir
            = new File("..").getAbsolutePath();

    @Parameter(names = { "-a", "-assets" }, description = "Assets Path")
    public static String assetsDir
            = baseDir + File.separator + "demo/assets";

    @Parameter(names = { "-l", "-library" }, description = "Library Path")
    public static String librariesDir
            = baseDir + File.separator + "libs";

    @Parameter(names = { "-n", "-native" }, description = "Lwjgl Natives Library Path")
    public static String lwjglNativesDir
            = librariesDir + File.separator + "natives";
}
