package MinesweeperBackend;

import java.util.Random;

public class MinesweeperField {
    private int fieldSizeX;
    private int fieldSizeY;
    private int numberMines;
    private GameState gameState;

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
        boolean newRevealed=false;
        for (x=0;x<fieldSizeX;x++){
            for(y=0;y<fieldSizeX;y++){

            }
        }
        //if only mines -> win
        if (newRevealed) update();
    }

    public void click(int x, int y){
        //if mine -> game end, set gameState
        //if not mine -> reveal, update
    }

}
