package com.example.nvmanh.calculator;

import java.util.Arrays;
import java.util.Stack;

public class CalculatorControl {

    private int getPriority(char c){
        if(c == CalculatorFragment.ADD || c == CalculatorFragment.SUB){
            return 1;
        } else if(c == CalculatorFragment.MUL || c == CalculatorFragment.DIV){
            return 2;
        }
        return 0;
    }

    public boolean isOperator(char c){  // kiem tra xem co phai toan tu
        char operator[] = { CalculatorFragment.ADD, CalculatorFragment.SUB,
                CalculatorFragment.MUL, CalculatorFragment.DIV};
        Arrays.sort(operator);
        if (Arrays.binarySearch(operator, c) > -1)
            return true;
        else
            return false;
    }

    private String[] standardString(String mMath){
        if(isOperator(mMath.charAt(mMath.length() - 1))){
            mMath = mMath.substring(0, mMath.length() - 1);
        }
        StringBuilder s1 = new StringBuilder("");
        String elementMath[] = null;
        for(int i = 0; i < mMath.length(); i++){
            char c = mMath.charAt(i);
            if(!isOperator(c)){
                s1.append(c);
            } else {
                s1.append(CalculatorFragment.SPACE + c + CalculatorFragment.SPACE);
            }
        }
        s1.toString().trim();
        elementMath = s1.toString().split(CalculatorFragment.SPACE);
        return elementMath;
    }

    public String postfix(String mMath){
        String[] element = standardString(mMath);
        StringBuilder s1 = new StringBuilder("");
        Stack<String> s = new Stack<>();

        for(int i = 0; i < element.length; i++){
            char c = element[i].charAt(0);
            if(!isOperator(c)){
                s1.append(CalculatorFragment.SPACE + element[i]);
            } else {
                while (!s.isEmpty() && getPriority(s.peek().charAt(0)) >= getPriority(c)){
                    s1.append(CalculatorFragment.SPACE + s.peek());
                    s.pop();
                }
                s.push(element[i]);
            }
        }

        while (!s.isEmpty()){
            s1.append(" " + s.peek());
            s.pop();
        }
        return s1.toString();
    }

    public float evaluatePostfix(String mMath){
        String postfix = postfix(mMath).trim();
        String[] element = postfix.split(CalculatorFragment.SPACE);
        Stack<Float> S = new Stack<>();
        for(int i = 0; i < element.length; i++){
            char c = element[i].charAt(0);
            if(!isOperator(c)){
                S.push(Float.parseFloat(element[i]));
            } else {
                progressMath(S, c);
            }
        }
        return S.pop();
    }

    private void progressMath(Stack<Float> S, char c){
        float x = S.pop();
        float y = S.pop();
        switch (c){
            case CalculatorFragment.ADD:
                y += x;
                break;
            case CalculatorFragment.SUB:
                y -= x;
                break;
            case CalculatorFragment.MUL:
                y *= x;
                break;
            case CalculatorFragment.DIV:
                y /= x;
                break;
        }
        S.push(y);
    }

}
