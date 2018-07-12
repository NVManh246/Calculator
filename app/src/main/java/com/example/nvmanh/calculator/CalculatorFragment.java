package com.example.nvmanh.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class CalculatorFragment extends Fragment implements View.OnClickListener{

    public static final char ADD = '+';
    public static final char SUB = '-';
    public static final char MUL = '*';
    public static final char DIV = '/';
    public static final char DOT = '.';
    public static final String SPACE = " ";

    private Button mButtonNumberZero;
    private Button mButtonNumberOne;
    private Button mButtonNumberTwo;
    private Button mButtonNumberThree;
    private Button mButtonNumberFour;
    private Button mButtonNumberFive;
    private Button mButtonNumberSix;
    private Button mButtonNumberSeven;
    private Button mButtonNumberEight;
    private Button mButtonNumberNine;
    private Button mButtonDot;
    private Button mButtonEquals;
    private Button mButtonAdd;
    private Button mButtonSub;
    private Button mButtonMul;
    private Button mButtonDiv;
    private Button mButtonDelete;

    private EditText mEidtTextScreen;

    private StringBuilder mCalculation = new StringBuilder("");
    private String mResult = "0";

    private Activity mActivity;
    private CalculatorControl mCalculatorControl = new CalculatorControl();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calaulator, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = getActivity();
        initView();
        setListener();
    }

    private void initView() {
        mButtonNumberZero = mActivity.findViewById(R.id.button_number_zero);
        mButtonNumberOne = mActivity.findViewById(R.id.button_number_one);
        mButtonNumberTwo = mActivity.findViewById(R.id.button_number_two);
        mButtonNumberThree = mActivity.findViewById(R.id.button_number_three);
        mButtonNumberFour = mActivity.findViewById(R.id.button_number_four);
        mButtonNumberFive = mActivity.findViewById(R.id.button_number_five);
        mButtonNumberSix = mActivity.findViewById(R.id.button_number_six);
        mButtonNumberSeven = mActivity.findViewById(R.id.button_number_seven);
        mButtonNumberEight = mActivity.findViewById(R.id.button_number_eight);
        mButtonNumberNine = mActivity.findViewById(R.id.button_number_nine);
        mButtonDot = mActivity.findViewById(R.id.button_dot);
        mButtonEquals = mActivity.findViewById(R.id.button_equals);
        mButtonAdd = mActivity.findViewById(R.id.button_add);
        mButtonSub = mActivity.findViewById(R.id.button_sub);
        mButtonMul = mActivity.findViewById(R.id.button_mul);
        mButtonDiv = mActivity.findViewById(R.id.button_div);
        mButtonDelete = mActivity.findViewById(R.id.button_delete);

        mEidtTextScreen = mActivity.findViewById(R.id.eidttext_screen);
    }

    private void setListener(){
        mButtonNumberZero.setOnClickListener(this);
        mButtonNumberOne.setOnClickListener(this);
        mButtonNumberTwo.setOnClickListener(this);
        mButtonNumberThree.setOnClickListener(this);
        mButtonNumberFour.setOnClickListener(this);
        mButtonNumberFive.setOnClickListener(this);
        mButtonNumberSix.setOnClickListener(this);
        mButtonNumberSeven.setOnClickListener(this);
        mButtonNumberEight.setOnClickListener(this);
        mButtonNumberNine.setOnClickListener(this);
        mButtonDot.setOnClickListener(this);
        mButtonEquals.setOnClickListener(this);
        mButtonAdd.setOnClickListener(this);
        mButtonSub.setOnClickListener(this);
        mButtonMul.setOnClickListener(this);
        mButtonDiv.setOnClickListener(this);
        mButtonDelete.setOnClickListener(this);
    }

    private void setCalculation(String input) {
        char c = input.charAt(0);
        if(mCalculatorControl.isOperator(c)){
            inputOperator(input);
        } else {
            inputOperand(input);
        }

        mEidtTextScreen.setText(mCalculation.toString());
    }

    private void inputOperand(String openrand){
        mCalculation.append(openrand);
    }

    private void inputOperator(String operator){
        if(mCalculation.length() != 0){
            char c = mCalculation.charAt(mCalculation.length() - 1);
            if(c != ADD && c != SUB && c != MUL && c != DIV && c != DOT){
                mCalculation.append(operator);
            } else {
                mCalculation.deleteCharAt(mCalculation.length() - 1).append(operator);
            }
        }
    }

    private void math(StringBuilder mCalculation) {
        mResult = mCalculatorControl.evaluatePostfix(mCalculation.toString()) + "";
        float r2 = Float.parseFloat(mResult);
        int r1 = (int) r2;

        mCalculation.delete(0, mCalculation.length());
        if(r2 - r1 == 0){
            mEidtTextScreen.setText(r1 + "");
            mCalculation.append(r1);
        } else {
            mEidtTextScreen.setText(r2 + "");
            mCalculation.append(r2);
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.button_number_zero:
                setCalculation(getString(R.string.key_0));
                break;
            case R.id.button_number_one:
                setCalculation(getString(R.string.key_1));
                break;
            case R.id.button_number_two:
                setCalculation(getString(R.string.key_2));
                break;
            case R.id.button_number_three:
                setCalculation(getString(R.string.key_3));
                break;
            case R.id.button_number_four:
                setCalculation(getString(R.string.key_4));
                break;
            case R.id.button_number_five:
                setCalculation(getString(R.string.key_5));
                break;
            case R.id.button_number_six:
                setCalculation(getString(R.string.key_6));
                break;
            case R.id.button_number_seven:
                setCalculation(getString(R.string.key_7));
                break;
            case R.id.button_number_eight:
                setCalculation(getString(R.string.key_8));
                break;
            case R.id.button_number_nine:
                setCalculation(getString(R.string.key_9));
                break;
            case R.id.button_dot:
                setCalculation(getString(R.string.key_Dot));
                break;
            case R.id.button_equals:
                math(mCalculation);
                break;
            case R.id.button_add:
                setCalculation(getString(R.string.key_add));
                break;
            case R.id.button_sub:
                setCalculation(getString(R.string.key_sub));
                break;
            case R.id.button_mul:
                setCalculation(getString(R.string.key_mul));
                break;
            case R.id.button_div:
                setCalculation(getString(R.string.key_div));
                break;
            case R.id.button_delete:
                if(mCalculation.length() > 0) {
                    mCalculation.deleteCharAt(mCalculation.length() - 1);
                    mEidtTextScreen.setText(mCalculation.toString());
                }
                break;
        }
    }
}
