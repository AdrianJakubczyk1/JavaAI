import sac.game.*;

import java.util.Scanner;
import java.util.LinkedList;
import java.util.List;

public class Connect4State extends GameStateImpl {





    @Override
    public int hashCode()
    {
        return toString().hashCode();
    }

    public void setFirst(boolean setter)
    {
        setMaximizingTurnNow(setter);
    }
//    public enum symbols{
//        X,O,E
//    }

    public static final int columns = 7;
    public static final int rows = 7;


    symbols[][] getBoard(){
        return this.board;
    }

    int getColumns()
    {
        return columns;
    }

    int getRows()
    {
        return rows;
    }

    private symbols board[][];

    public Connect4State()
    {

        board = new symbols[rows][columns];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                board[i][j] = symbols.E;
            setMaximizingTurnNow(true);
    }

    int checkWin()
    {
        int moduloRows = rows%4;
        int moduloColumns = columns%4;
        int counterX=0;
        int counterO=0;
        for (int i =rows-1;i>=0;i--) {
            counterO=0;
            counterX=0;
            for (int j = 0; j < columns; j++) {

                if(this.board[i][j]==symbols.X)
                {
                    counterX+=1;
                    if(counterO!=0)
                        counterO=0;
                }
                else if(this.board[i][j]==symbols.O)
                {
                    counterO+=1;
                    if(counterX!=0)
                        counterX=0;
                }
                else if(this.board[i][j]==symbols.E)
                {

                    if(counterO!=0 || counterX!=0) {
                        counterX = 0;
                        counterO = 0;
                    }
                }
                if(counterO==4) {

                    return 2;
                }
                else if(counterX==4) {

                    return 1;
                }

            }
        }
        //checkIfWinVertically;
        for (int i =0;i<columns;i++) {
            counterO=0;
            counterX=0;
            for (int j = rows-1; j >0; j--) {
                //  System.out.println("to jest countero kolumny "+counterO);
                //  System.out.println("to jest counterX kolumny "+counterX);
                if(this.board[j][i]==symbols.X)
                {

                    counterX+=1;
                    if(counterO!=0)
                        counterO=0;
                }
                if(this.board[j][i]==symbols.O)
                {
                    counterO+=1;
                    if(counterX!=0)
                        counterX=0;
                }

                if(counterO==4) {

                    return 2;
                }
                else if(counterX==4)
                    return 1;


            }
        }

        //from botttom direction '/'
        for (int i =rows-1;i>=0;i--) {
            counterO=0;
            counterX=0;
            if(i<3)
                continue;
            for (int j = 0; j < columns; j++) {
                counterO=0;
                counterX=0;
                if(j==this.columns-4)
                    break;
                if(counterO==4) {

                    return 2;
                }
                else if(counterX==4) {

                    return 1;
                }
                if(this.board[i][j]==symbols.X)
                {
                    counterX+=1;
                    for (int x = 1;x<=4;x++) {

                        if (this.board[i-x][x + j] == symbols.X) {

                            counterX += 1;

                            if(counterX==4){

                                return 1;}
                        }
                        else
                            break;

                    }
                }
                if(this.board[i][j]==symbols.O)
                {
                    counterO+=1;
                    for (int x = 1;x<=4;x++) {
                        if (this.board[i-x][x + j] == symbols.O)
                            counterO += 1;
                        else
                            break;
                        if(counterO==4)
                            return 2;
                    }
                }
            }
        }


        //fromBottom '\'

        for (int i =rows-1;i>=0;i--) {
            counterO=0;
            counterX=0;
            if(i<3)
                continue;
            for (int j = 3; j < columns; j++) {
                counterO=0;
                counterX=0;
                if(j<3)
                    break;
                if(counterO==4) {

                    return 2;
                }
                else if(counterX==4) {

                    return 1;
                }
                if(this.board[i][j]==symbols.X)
                {
                    counterX+=1;
                    for (int x = 1;x<=4;x++) {

                        if (this.board[i-x][j-x] == symbols.X) {

                            counterX += 1;

                            if(counterX==4){

                                return 1;}
                        }
                        else
                            break;

                    }
                }
                if(this.board[i][j]==symbols.O)
                {
                    counterO+=1;
                    for (int x = 1;x<=4;x++) {
                        if (this.board[i-x][j-x] == symbols.O)
                            counterO += 1;
                        else
                            break;
                        if(counterO==4)
                            return 2;
                    }
                }
            }
        }


        //checkifgottotop
        for (int i =0;i<columns;i++) {
            if(this.board[0][i]==symbols.X)
                return 1;
            else if(this.board[0][i]==symbols.O)
                return 2;
        }
return 0;
    }

    public Connect4State(Connect4State toCopy)
    {
        board = new symbols[rows][columns];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                board[i][j] = toCopy.board[i][j];
       setMaximizingTurnNow(toCopy.isMaximizingTurnNow());
    }

    @Override
    public String toString()
    {
        String txt = "";
        for (int i = 0; i <= rows; i++) {
            if (i!=rows)
            txt+=i+"\t";
            for (int j = 0; j < columns; j++) {
                if(i!=rows) {
                    if (i == 0 && j == 0)
                        txt += " \t\t\t\t\t\t\t\t\t\t";
                    txt += board[i][j] + " | ";
                    if (j == columns - 1)
                        txt += "\n \t\t\t\t\t\t\t\t\t\t";
                }else{
                    if (j ==0)
                        txt+="\n \t\t\t\t\t\t\t\t\t\t\t";
                    txt+=j+"   ";
                }
            }
        }

        return txt;
    }

    public boolean makeMove(int column)
    {

        if(column >= columns)
            return false;

        //szukanie pierwszego wolnego pola w kolumnie
        for(int i = rows-1;i>=0;i--)
        {

            if(board[i][column]==symbols.E)
            {
                if(this.isMaximizingTurnNow()) {
                    board[i][column] =symbols.X;
                    this.setMaximizingTurnNow(false);
                    return true;
                }
                else
                {
                    board[i][column] =symbols.O;
                    this.setMaximizingTurnNow(true);
                    return true;
                }

            }
        }
        return false;
    }

    @Override
    public List<GameState> generateChildren() {
        LinkedList<GameState> children = new LinkedList<GameState>();
        for(int i=0;i<columns;i++)
        {
            Connect4State child = new Connect4State(this);
           boolean flag= child.makeMove(i);
            //child.setMoveName("ruch w kolumnie " + Integer.valueOf(i) +" oceniony na: ");
            child.setMoveName(Integer.toString(i));
            if(flag)
            children.add(child);
        }
        return children;
    }


    public static void main(String[] args) {
        // write your code here
        boolean correct=false;
        boolean isPlayerFirst=true;
        Connect4State.setHFunction(new OcenaRuchu());
        Connect4State ob = new Connect4State();

        Scanner console = new Scanner(System.in);
        System.out.println("the game has startted");
        System.out.println(ob);
        int number;


       // GameSearchAlgorithm algo = new AlphaBetaPruning(ob);

        while(true) {

            if(isPlayerFirst) {
                System.out.print("Player's turn : ");
                number = console.nextInt();
                ob.makeMove(number);
                System.out.println(ob);
                System.out.println(ob.getH());
                if (ob.checkWin()== 1) {
                    System.out.println("zwyciestwo gracza");
                    break;
                }
                GameSearchAlgorithm  algo = new  AlphaBetaPruning(ob);
                algo.execute();
                System.out.println(algo.getMovesScores());
                String Best = algo.getFirstBestMove();
                //Integer.valueOf(Best)

                ob.makeMove(Integer.valueOf(Best));

                System.out.println(ob);
                System.out.println(ob.getH());
                System.out.println(ob.checkWin());
                if (ob.checkWin()== 2) {
                    System.out.println("z" +
                            "wyciestwo AI");
                    break;
                }
            }
            else{
                GameSearchAlgorithm  algo = new AlphaBetaPruning(ob);
                algo.execute();
                System.out.println(algo.getMovesScores());
                String Best = algo.getFirstBestMove();
                ob.makeMove(Integer.valueOf(Best));
                System.out.println(ob);
                if (ob.checkWin()== 1) {
                    System.out.println("zwyciestwo AI");
                    break;
                }
                System.out.print("Player's turn : ");
                number = console.nextInt();
                ob.makeMove(number);
                System.out.println(ob);
                if (ob.checkWin()== 2) {
                    System.out.println("zwyciestwo gracza");
                    break;
                }
            }
        }


    }


}
