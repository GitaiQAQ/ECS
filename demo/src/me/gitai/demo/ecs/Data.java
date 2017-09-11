package me.gitai.demo.ecs;

/**
 * Created by Gitai.me(i@gitai.me) on 9/11/17.
 */
public class Data {
    private static Data ourInstance = new Data();

    public static Data getInstance() {
        return ourInstance;
    }

    private Data() {
    }

    private String mScore;

    public String getmScore() {
        return mScore;
    }

    public void setmScore(String score) {
        mScore = score;
    }

    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
