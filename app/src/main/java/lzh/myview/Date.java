package lzh.myview;

/**
 * Created by Administrator on 2017/6/7 0007.
 */

public class Date {
     private  int color;
    private float angle;
    private float value;        // 数值
    private float percentage;   // 百分比

    public Date(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
}
