package com.example.administrator.calcultor10;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import util.MathHelper;

/**
 * 本程序的主要流程：
 * 1.获取各个控件并为控件添加相应处理事件
 * 2.点击各个空间生成相应的算术表达式，并显示相应的表达式
 * 3.使用栈处理生成的算术表达式并得出结果
 * 4.显示结果
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    boolean isComplied = false;
    private String mathExpress = "";

    private TextView tv_result;

    private Button btn_ac;
    private Button btn_del;
    private Button btn_div;
    private Button btn_add;
    private Button btn_sub;
    private Button btn_mul;
    private Button btn_eq;
    private Button btn_dot;

    private Button btn_0;
    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private Button btn_5;
    private Button btn_6;
    private Button btn_7;
    private Button btn_8;
    private Button btn_9;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_result = (TextView) findViewById(R.id.tv_result);

        btn_ac = (Button) findViewById(R.id.btn_ac);
        btn_del = (Button) findViewById(R.id.btn_del);
        btn_div = (Button) findViewById(R.id.btn_div);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_sub = (Button) findViewById(R.id.btn_sub);
        btn_mul = (Button) findViewById(R.id.btn_mul);
        btn_eq = (Button) findViewById(R.id.btn_eq);
        btn_dot = (Button) findViewById(R.id.btn_dot);

        btn_0 = (Button) findViewById(R.id.btn_0);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_9 = (Button) findViewById(R.id.btn_9);

        btn_ac.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_div.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_sub.setOnClickListener(this);
        btn_mul.setOnClickListener(this);
        btn_eq.setOnClickListener(this);
        btn_dot.setOnClickListener(this);

        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (isComplied == true){
            mathExpress = "";
            tv_result.setText(mathExpress);
        }

        isComplied = false;
        String msg = "";
        switch (view.getId()){
            case R.id.btn_0:
                msg = "0";
                break;
            case R.id.btn_1:
                msg = "1";
                break;
            case R.id.btn_2:
                msg = "2";
                break;
            case R.id.btn_3:
                msg = "3";
                break;
            case R.id.btn_4:
                msg = "4";
                break;
            case R.id.btn_5:
                msg = "5";
                break;
            case R.id.btn_6:
                msg = "6";
                break;
            case R.id.btn_7:
                msg = "7";
                break;
            case R.id.btn_8:
                msg = "8";
                break;
            case R.id.btn_9:
                msg = "9";
                break;
            case R.id.btn_add:
                if (isOperator(mathExpress,"+")){
                    mathExpress = mathExpress.substring(0,mathExpress.length()-1);
                }
                msg = "+";
                break;
            case R.id.btn_sub:
                if (isOperator(mathExpress,"-")){
                    mathExpress = mathExpress.substring(0,mathExpress.length()-1);
                }
                msg = "-";
                break;
            case R.id.btn_mul:
                if (isOperator(mathExpress,"×")){
                    mathExpress = mathExpress.substring(0,mathExpress.length()-1);
                }
                msg = "×";
                break;
            case R.id.btn_div:
                if (isOperator(mathExpress,"÷")){
                    mathExpress = mathExpress.substring(0,mathExpress.length()-1);
                }
                msg = "÷";
                break;
            case R.id.btn_ac: //清空显示信息
                tv_result.setText("");
                mathExpress = "";
                break;
            case R.id.btn_del:
                if (mathExpress.length() > 0) {
                    mathExpress = mathExpress.substring(0, mathExpress.length() - 1);
                }
                break;
            case R.id.btn_dot:
                if (isOperator(mathExpress,".")){
                    mathExpress = mathExpress.substring(0,mathExpress.length()-1);
                }
                msg = ".";
                break;
            case R.id.btn_eq:
                msg = "=";
                if (mathExpress == ""){
                    showMessage("");
                    return;
                }
                break;
            default:
                break;

        }
        showMessage(msg);

        if (msg.equals("=")){
            String result = calculate(mathExpress);
            showMessage(result);
            isComplied = true;
        }
    }

    void showMessage(String symbol){
        mathExpress += symbol;
        tv_result.setText(mathExpress);
    }

    private String calculate(String mathExpress) {
        if (mathExpress.equals("")){
            return "";
        }

        List<String> mathList = processMathExpress(mathExpress);
        Stack<String> numStack = new Stack<>();
        Stack<String> symbolStack = new Stack<>();
        String result = "";

        //对表达式进行计算，符号进入符号栈，数字进入数字栈，并按照运算符优先级进行运算
        for (int i=0; i<mathList.size(); i++){
            String cur = mathList.get(i);

            if (cur.equals("+") || cur.equals("-") || cur.equals("×") || cur.equals("÷") || cur.equals("=")){
                String symPeek = "";

                if(symbolStack.size() == 0){
                    symbolStack.push(cur);
                } else {
                    symPeek = symbolStack.peek();
                    if (operatorCompare(cur,symPeek)){ //当前运算符的优先级大于符号栈顶的优先级,
                        String next = mathList.get(++i);
                        result = calculating(numStack.pop(),next,cur);

                        numStack.push(result);
                    } else {
                        String num1 = "";
                        if (numStack.size() > 0)
                            num1 = numStack.pop();
                        String num2 = "";
                        if (numStack.size() > 0)
                            num2 = numStack.pop();
                        result = calculating(num1,num2,symPeek);
                        numStack.push(result);

                        symbolStack.pop();
                        symbolStack.push(cur);
                    }
                }
            } else {
                numStack.push(cur);
            }
        }


        return numStack.pop();
    }

    private String calculating(String num1, String num2, String symPeek) {
        double result = 0;

        if (num1 == null || num1.equals("") || num2 == null || num2.equals("")){
            return "Error";
        }

        if (symPeek.equals("+")){
        result = MathHelper.add(num1,num2);
        } else if (symPeek.equals("-")){
            result = MathHelper.sub(num1,num2);
        } else if (symPeek.equals("×")){
            result = MathHelper.mul(num1,num2);
        } else if (symPeek.equals("÷")){
            if (Double.valueOf(num1) == 0){
                return "Error";
            } else {
                result = MathHelper.div(num1, num2);
            }
        }

        return result + "";
}

    //将字符串拆分为字符串数组
    private List<String> processMathExpress(String mathExpress) {
        if (mathExpress.length() < 0){
            return null;
        }

        List<String> list = new ArrayList<>();
        double num1 = 0;
        boolean hasNum = false;

        for (int i=0; i<mathExpress.length(); i++){
            if(Character.isDigit(mathExpress.charAt(i)) || mathExpress.charAt(i) == '.'){
                if(Character.isDigit(mathExpress.charAt(i))) {
                    //构造整数部分
                    num1 = num1 * 10 + (mathExpress.charAt(i) - '0');
                    hasNum = true;
                }
                //构造小数部分
                double fractional = 0.0;
                if ( mathExpress.charAt(i) == '.'){
                    int index = 1;
                    double base = 1;

                    while(Character.isDigit(mathExpress.charAt(++ i))){
                        base *= 0.1;
                        fractional += base * (mathExpress.charAt(i) - '0');
                    }
                    i --;
                }
                num1 += fractional;

            } else {
                if (hasNum == true) {
                    list.add(num1 + "");
                    num1 = 0;
                    hasNum = false;
                }
                list.add(mathExpress.charAt(i)+"");
            }
        }

        for (int i=0; i<list.size(); i++){
            Log.d("wyg",list.get(i) + "  ");
        }

        return list;
    }


    boolean operatorCompare(String cur,String peek){
        if (cur.equals("×") || cur.equals("÷")){
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断当前输入的运算符前面是否式运算符
     * 有运算符返回 true
     * 没有运算符返回 false
     */
    boolean isOperator(String mathExpress,String operator){
        if (mathExpress == null || mathExpress.length() <=0 ) return false;

        int len = mathExpress.length();
        char lastChar = mathExpress.charAt(len - 1);
        if (lastChar == '+' || lastChar == '-' || lastChar == '×'
                || lastChar == '÷' || lastChar == '.' || lastChar == '=' ){
           return true;
        } else {
            return false;
        }
    }

}





















