package MinesweeperBackend;

import MinesweeperBackend.geklaut.Mines;

import java.util.Random;
import java.util.Vector;

public class MinesweeperField {
    private int fieldSizeX;
    private int fieldSizeY;
    private int numberOfMines;
    private int updateCount=0;

    public GameState getGameState() {
        return gameState;
    }

    private GameState gameState;

    private long seed;
    private Random rnd;

    private FieldState fieldArrayMines[][];
    private  int mineProximityNumbers[][];

    public static void main(String[] args) {
        MinesweeperField tempMSField = new MinesweeperField(5,5,1);
        System.out.println( tempMSField.toString());
        tempMSField.click(0,0);
        System.out.println( tempMSField.toString());
        System.out.println(tempMSField.getGameState().toString());
    }

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
            if(fieldArrayMines[x][y]!=FieldState.MINE) {
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

    public int getMineProximityNumbers(int x, int y){
        return mineProximityNumbers[x][y];
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
                            newRevealed=true;
                        }
                        fieldArrayMines[nachbX][nachbY]=FieldState.EMPTY_CLICKED;
                    }
                }


            }
        }
        updateCount++;
        if (updateCount>100) {System.out.println("PROBABLE UPDATE() ERROR"); System.exit(-1);}
        if (newRevealed) update();
        updateCount--;
        if (onlyMines) gameState=GameState.WIN;
    }

    public void click(int x, int y){
        if(x<0 || x>fieldSizeX) return;
        if(y<0 || y>fieldSizeY) return;
        if(fieldArrayMines[x][y]==FieldState.EMPTY) fieldArrayMines[x][y]=FieldState.EMPTY_CLICKED;
        if(fieldArrayMines[x][y]==FieldState.MINE) {
            fieldArrayMines[x][y]=FieldState.MINE_CLICKED;
            gameState=GameState.LOSE;
        }
        update();
    }

    private int[][] getNachbarn(int x, int y){
        Vector<int[]> vec = new Vector();
        int newX=0;
        int newY=0;
        for (int xDelta=-1;xDelta<2;xDelta++){
            for (int yDelta=-1;yDelta<2;yDelta++){
                if(xDelta==0 && yDelta==0) continue;
                newX = x + xDelta;
                newY = y + yDelta;
                if(newX>=0 && newX<fieldSizeX && newY>=0 && newY<fieldSizeY){
                    vec.add(new int[]{newX,newY});
                }
            }
        }
        int[][] retArr = vec.toArray(new int[][]{});
        return retArr;
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

    @Override
    public String toString(){
        String returnStr="";
        for (int x=0;x<fieldSizeX;x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                String str=" ";
                    if (fieldArrayMines[x][y]==FieldState.MINE) str="o";
                    if (fieldArrayMines[x][y]==FieldState.EMPTY_CLICKED) str="'";
                    if (fieldArrayMines[x][y]==FieldState.EMPTY) str="_";
                returnStr = returnStr + str;
            }
            returnStr= returnStr+"\n";
        }
        return returnStr;
    }

}
