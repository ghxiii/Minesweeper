package MinesweeperBackend;

import java.util.Random;

public class MinesweeperField {
    private int fieldSizeX;
    private int fieldSizeY;
    private int numberMines;
    private long seed;
    Random rnd;

    private FieldState fieldArrayMines[][];
    private  int mineNumbers[][];


    public MinesweeperField(int fieldSizeX, int fieldSizeY, int numberMines) {
        rnd= new Random( new java.util.Date().getTime() );
        this.fieldSizeX = fieldSizeX;
        this.fieldSizeY = fieldSizeY;
        this.numberMines = numberMines;
        fieldArrayMines = new FieldState[fieldSizeX][fieldSizeY];
        mineNumbers = new int[fieldSizeX][fieldSizeY];
    }

    public MinesweeperField(int fieldSizeX, int fieldSizeY, int numberMines, int seed) {
        rnd = new Random(seed);
        this.fieldSizeX = fieldSizeX;
        this.fieldSizeY = fieldSizeY;
        this.numberMines = numberMines;
        fieldArrayMines = new FieldState[fieldSizeX][fieldSizeY];
        mineNumbers = new int[fieldSizeX][fieldSizeY];
    }

    public FieldState[][] getStateArray(){
        return fieldArrayMines;
    }

    public int[][]getMineProximityNumbers(){
        return  mineNumbers;
    }

    private void update(){
        int x,y;
        for (x=0;x<fieldSizeX){
            for(y=0;y<fieldSizeX)
        }
    }

}
