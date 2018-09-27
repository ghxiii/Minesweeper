package MinesweeperBackend;

import java.util.Random;
import java.util.Vector;

public class MinesweeperField {
    private int fieldSizeX;
    private int fieldSizeY;
    private int numberOfMines;

    public GameState getGameState() {
        return gameState;
    }

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
        gameState = GameState.RUNNING;
    }

    public MinesweeperField(int fieldSizeX, int fieldSizeY, int numberOfMines, int seed) {
        this(fieldSizeX, fieldSizeY, numberOfMines);
        rnd = new Random(seed);
    }

    private void placeMines() {
        int mineNumber=1;
        int x,y;
        while (mineNumber<= numberOfMines){
            x=rnd.nextInt(fieldSizeX);
            y=rnd.nextInt(fieldSizeY);
            if(fieldArrayMines[x][y]==FieldState.EMPTY) {
                fieldArrayMines[x][y]=FieldState.MINE;
                mineNumber++;
            }
        }
        calculateMineProximityNumbers();
    }

    private void calculateMineProximityNumbers() {
        for (int x=0;x<fieldSizeX;x++){
            for(int y=0;y<fieldSizeY;y++){
                mineProximityNumbers[x][y]=getMinesInProximity(x,y);
            }
        }
    }

    public FieldState[][] getStateArray(){
        return fieldArrayMines;
    }

    public int[][] getMineProximityNumbers(){
        return mineProximityNumbers;
    }

    private void update(){ //finde geklikte '0' Felder, check Nachbarn, setzt entspr. Nachbarn auf "clicked_empty"
        int x,y;
        boolean newRevealed=false;
        boolean onlyMines=true;
        int [][] nachbarn;
        for (x=0;x<fieldSizeX;x++){
            for(y=0;y<fieldSizeY;y++){
                if( !(fieldArrayMines[x][y] == FieldState.MINE ||fieldArrayMines[x][y] == FieldState.EMPTY_CLICKED) ) onlyMines=false;
                if(fieldArrayMines[x][y] == FieldState.EMPTY_CLICKED && mineProximityNumbers[x][y]==0) {
                    nachbarn = getNachbarn(x, y);
                    for(int i=0;i<nachbarn.length;i++){
                        int nachbX=nachbarn[i][0];
                        int nachbY=nachbarn[i][1];
                        if (fieldArrayMines[nachbX][nachbY]==FieldState.EMPTY && mineProximityNumbers[nachbX][nachbY]==0 ){
                            fieldArrayMines[nachbX][nachbY]=FieldState.EMPTY_CLICKED;
                            newRevealed=true;
                        }
                    }
                }


            }
        }
        if (newRevealed) update();
        if (onlyMines) gameState=GameState.WIN;
    }

    public void click(int x, int y){

        if(fieldArrayMines[x][y]==FieldState.MINE) gameState=GameState.LOSE;
        update();
    }

    private int[][] getNachbarn(int x, int y){
        Vector<int[]> vec = new Vector();
        int newX=0;
        int newY=0;
        for (int xDelta=-1;xDelta<2;xDelta++){
            for (int yDelta=-1;yDelta<2;yDelta++){
                newX = x + xDelta;
                newY = y + yDelta;
                if(newX>0 && newX<=fieldSizeX && newY>0 && newY<=fieldSizeY){
                    vec.add(new int[]{newX,newY});
                }
            }
        }
        int[][] tempArr = new int[vec.size()][2];

        return vec.toArray(new int[][]{});
    }

    private int getMinesInProximity(int x, int y){
        int[][] nachbarn = getNachbarn(x,y);
        int minesCounted = 0;
        for(int i=0;i<nachbarn.length;i++){
            int nachbX=nachbarn[i][0];
            int nachbY=nachbarn[i][1];
            if (fieldArrayMines[nachbX][nachbY]==FieldState.MINE ||fieldArrayMines[nachbX][nachbY]==FieldState.MINE_CLICKED ){
                minesCounted++;
            }
        }
        return minesCounted;
    }

}
