package com.example.freakingmath;

import java.util.Random;

public class GenerateLevel {
    // score of each level
    public static final int EASY = 10;
    public static final int MEDIUM = 20;
    public static final int HARD = 150;
    // luu thong tin level tiep theo
    public static LevelModel generateLevel(int cnt){
        LevelModel level = new LevelModel();
        Random rd = new Random();
        // get current difficult level
        if(cnt <= EASY){
            level.difficultLevel = 1;
        }
        else{
            if(cnt <= MEDIUM){
                level.difficultLevel = 2;
            }
            else {
                if(cnt <= HARD){
                    level.difficultLevel = 3;
                }
                else{
                    level.difficultLevel = 4;
                }
            }
        }
        // random operation
        level.operator = rd.nextInt(level.difficultLevel);
        // random operator
        level.x = rd.nextInt(level.arrMaxOperatorValue[level.difficultLevel - 1]) + 1;
        level.y = rd.nextInt(level.arrMaxOperatorValue[level.difficultLevel - 1]) + 1;
        // random result
        level.correctWrong = rd.nextBoolean();
        // tuy vao ket qua dung sai de dua ket qua
        if(!level.correctWrong){
            switch (level.operator){
                case LevelModel.ADD:
                    do {
                        level.result = rd.nextInt(level.arrMaxOperatorValue[level.difficultLevel - 1]);
                    } while(level.result == (level.x + level.y));
                    break;
                case LevelModel.SUB:
                    do {
                        level.result = rd.nextInt(level.arrMaxOperatorValue[level.difficultLevel - 1]);
                    } while(level.result == (level.x - level.y));
                    break;
                case LevelModel.MUL:
                    do {
                        level.result = rd.nextInt(level.arrMaxOperatorValue[level.difficultLevel - 1]);
                    } while(level.result == (level.x * level.y));
                    break;
                case LevelModel.DIV:
                    do {
                        level.result = rd.nextInt(level.arrMaxOperatorValue[level.difficultLevel - 1]);
                    } while(level.result == (level.x / level.y));
                    break;
                default:
                    break;
            }
        }
        else{
            switch (level.operator){
                case LevelModel.ADD:
                    level.result = level.x + level.y;
                    break;
                case LevelModel.SUB:
                    level.result = level.x - level.y;
                    break;
                case LevelModel.MUL:
                    level.result = level.x * level.y;
                    break;
                case LevelModel.DIV:
                    level.result = level.x / level.y;
                    break;
                default:
                    break;
            }
        }
        // create string to display on screen
        level.strOperator = level.x + " " + level.arrOperatorText[level.operator] + " " + level.y + " " + LevelModel.EQU_TEXT + " " + level.result;
        // return LevelModel for next level
        return level;
    }
}
