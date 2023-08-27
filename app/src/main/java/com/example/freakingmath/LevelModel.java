package com.example.freakingmath;

public class LevelModel {
    public int difficultLevel = 1;
    public static final int ADD = 0;
    public static final int SUB = 1;
    public static final int MUL = 2;
    public static final int DIV = 3;
    public static final String ADD_TEXT = "+";
    public static final String SUB_TEXT = "-";
    public static final String MUL_TEXT = "*";
    public static final String DIV_TEXT = "/";
    public static final String EQU_TEXT = "=";
    public static final String[] arrOperatorText = {ADD_TEXT, SUB_TEXT, MUL_TEXT, DIV_TEXT};
    public int x;
    public int y;
    public int result;
    public int operator;
    public boolean correctWrong;
    public String strOperator = "";
    public static final int MAX_OPERATOR_LEVEL_EASY = 5;
    public static final int MAX_OPERATOR_LEVEL_MEDIUM = 10;
    public static final int MAX_OPERATOR_LEVEL_HARD = 50;
    public static final int[] arrMaxOperatorValue = {MAX_OPERATOR_LEVEL_EASY, MAX_OPERATOR_LEVEL_MEDIUM, MAX_OPERATOR_LEVEL_HARD};
}
