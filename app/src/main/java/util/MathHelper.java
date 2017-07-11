package util;

/**
 * Created by Administrator on 2017/7/11 0011.
 * 定义计算器所有的算术操作，包括加、减、乘、除
 */

public class MathHelper {
    /**
     * 加法操作
     * @param num1
     * @param num2
     * @return
     */
    public static double add(double num1,double num2){
        return 0;
    }

    public static double add(String num1,String num2){
        double res = Double.valueOf(num1) + Double.valueOf(num2);

        return res;
    }

    /**
     * 减法操作
     * @param num1
     * @param num2
     * @return
     */
    public static double sub(double num1,double num2){
        return 0;
    }

    public static double sub(String num1,String num2){
        double res = Double.valueOf(num2) - Double.valueOf(num1);

        return res;
    }

    /**
     * 乘法操作
     * @param num1
     * @param num2
     * @return
     */
    public static double mul(double num1,double num2){
        return 0;
    }

    public static double mul(String num1,String num2){
        double res = Double.valueOf(num1) * Double.valueOf(num2);

        return res;
    }

    /**
     * 除法操作
     * @param num1
     * @param num2
     * @return
     */
    public static double div(double num1,double num2){
        return 0;
    }

    public static double div(String num1,String num2){
        double res = Double.valueOf(num2) / Double.valueOf(num1);

        return res;
    }
}
