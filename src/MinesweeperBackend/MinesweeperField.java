package MinesweeperBackend;

import java.util.Random;

public class MinesweeperField {
    private int fieldSizeX;
    private int fieldSizeY;
    private int numberOfMines;
    private GameState gameState;

    private long seed;
    Random rnd;

    private FieldState fieldArrayMines[][];
    private  int mineProximityNumbers[][];


    public MinesweeperField(int fieldSizeX, int fieldSizeY, int numberOfMines) {
        rnd= new Random( new java.util.Date().getTime() );
        this.fieldSizeX = fieldSizeX;
        this.fieldSizeY = fieldSizeY;
        this.numberOfMines = numberOfMines;
        fieldArrayMines = new FieldState[fieldSizeX][fieldSizeY];
        mineProximityNumbers = new int[fieldSizeX][fieldSizeY];
        for (int x=0;x<fieldSizeX;x++){
            for(int y=0;y<fieldSizeY;y++){
                fieldArrayMines[x][y]=FieldState.EMPTY;
            }
        }
        placeMines();
    }

    private void placeMines() {
        int mineNumber=1;
        int x,y;
        boolean newRevealed=false;
        while (mineNumber<= numberOfMines){
            x=rnd.nextInt(fieldSizeX);
            y=rnd.nextInt(fieldSizeY);
            if(fieldArrayMines[x][y]==FieldState.EMPTY) {
                fieldArrayMines[x][y]=FieldState.MINE;
                mineNumber++;
            }
        }
    }

    public MinesweeperField(int fieldSizeX, int fieldSizeY, int numberOfMines, int seed) {
        this(fieldSizeX, fieldSizeY, numberOfMines);
        rnd = new Random(seed);
    }

    public FieldState[][] getStateArray(){
        return fieldArrayMines;
    }

    public int[][]getMineProximityNumbers(){
        return mineProximityNumbers;
    }

    private void update(){
        int x,y;
        boolean newRevealed=false;
        for (x=0;x<fieldSizeX;x++){
            for(y=0;y<fieldSizeY;y++){

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
