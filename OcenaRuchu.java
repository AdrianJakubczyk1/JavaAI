import sac.State;
import sac.StateFunction;

public class OcenaRuchu extends StateFunction {
    public double calculate(State state)
    {
        Connect4State p = (Connect4State) state;
        symbols[][] board;
        board = p.getBoard();
        double resultMax=0;
        double resultMin=0;
        int columns=p.getColumns();
        int rows = p.getRows();
        int modulo1 = rows%4;
        int modulo2 = columns%4;
        int counterX=0;
        int counterO=0;
        //checkIfWinHorizontally;
        //o negative

        for (int i =rows-1;i>=0;i--) {
            counterO=0;
            counterX=0;
            for (int j = 0; j < columns; j++) {

                if(board[i][j]==symbols.X)
                {
                    counterX+=1;
                    if(counterO!=0)
                        counterO=0;
                }
                else if(board[i][j]==symbols.O)
                {
                    counterO+=1;
                    if(counterX!=0)
                        counterX=0;
                }
                else if(board[i][j]==symbols.E)
                {

                    if(counterO!=0 || counterX!=0) {
                        counterX = 0;
                        counterO = 0;
                    }
                }
                if(counterO==4) {

                    return Double.NEGATIVE_INFINITY;
                }
                else if(counterX==4) {

                    return Double.POSITIVE_INFINITY;
                }

            }
        }



//        for (int i =rows-1;i>=0;i--) {
//            counterO=0;
//            counterX=0;
//            for (int j = 0; j < columns; j++) {
//
//
//                if(counterO==4) {
//
//                    return Double.NEGATIVE_INFINITY;
//                }
//                else if(counterX==4) {
//
//                    return Double.POSITIVE_INFINITY;
//                }
//                if(board[i][j]==symbols.X)
//                {
//                    counterX+=1;
//                    if(counterO!=0)
//                        counterO=0;
//                }
//                if(board[i][j]==symbols.O)
//                {
//                    counterO+=1;
//                    if(counterX!=0)
//                        counterX=0;
//                }
//                if(board[i][j]==symbols.E)
//                {
//
//                    if(counterO!=0 || counterX!=0) {
//                        counterX = 0;
//                        counterO = 0;
//                    }
//                }
//
//            }
//        }
        //checkIfWinVertically;
        for (int i =0;i<columns;i++) {
            counterO=0;
            counterX=0;
            for (int j = rows-1; j >=0; j--) {
                //  System.out.println("to jest countero kolumny "+counterO);
                //  System.out.println("to jest counterX kolumny "+counterX);
                if(board[j][i]==symbols.X)
                {

                    counterX+=1;
                    if(counterO!=0)
                        counterO=0;
                }
                if(board[j][i]==symbols.O)
                {
                    counterO+=1;
                    if(counterX!=0)
                        counterX=0;
                }

                if(counterO==4)
                    return Double.NEGATIVE_INFINITY;
                else if(counterX==4)
                    return Double.POSITIVE_INFINITY;


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
                if(j==columns-4)
                    break;
                if(counterO==4) {

                    return Double.NEGATIVE_INFINITY;
                }
                else if(counterX==4) {

                    return Double.POSITIVE_INFINITY;
                }
                if(board[i][j]==symbols.X)
                {
                    counterX+=1;
                    for (int x = 1;x<=4;x++) {

                        if (board[i-x][x + j] == symbols.X) {

                            counterX += 1;

                            if(counterX==4){

                                return Double.POSITIVE_INFINITY;}
                        }
                        else
                            break;

                    }
                }
                if(board[i][j]==symbols.O)
                {
                    counterO+=1;
                    for (int x = 1;x<=4;x++) {
                        if (board[i-x][x + j] == symbols.O)
                            counterO += 1;
                        else
                            break;
                        if(counterO==4)
                            return Double.NEGATIVE_INFINITY;
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

                    return Double.NEGATIVE_INFINITY;
                }
                else if(counterX==4) {

                    return Double.POSITIVE_INFINITY;
                }
                if(board[i][j]==symbols.X)
                {
                    counterX+=1;
                    for (int x = 1;x<=4;x++) {

                        if (board[i-x][j-x] == symbols.X) {

                            counterX += 1;

                            if(counterX==4){

                                return Double.POSITIVE_INFINITY;}
                        }
                        else
                            break;

                    }
                }
                if(board[i][j]==symbols.O)
                {
                    counterO+=1;
                    for (int x = 1;x<=4;x++) {
                        if (board[i-x][j-x] == symbols.O)
                            counterO += 1;
                        else
                            break;
                        if(counterO==4)
                            return Double.NEGATIVE_INFINITY;
                    }
                }
            }
        }


        //checkifgottotop
        for (int i =0;i<columns;i++) {
            if(board[0][i]==symbols.X)
                return Double.POSITIVE_INFINITY;
            else if(board[0][i]==symbols.O)
                return Double.NEGATIVE_INFINITY;
        }

        /////////////////////////////////////////

        double pairsRowX=0;
        double pairsRowO=0;
        double triosRowX=0;
        double triosRowO=0;

        for (int i =rows-1;i>=0;i--) {
            counterO = 0;
            counterX = 0;
            for (int j = 0; j <columns; j++) {

                if(board[i][j]==symbols.X)
                {

                    counterX+=1;
                    if (counterX==2)
                        pairsRowX+=1;
                    if (counterX==3)
                        triosRowX+=1;
                    if(counterO!=0)
                        counterO=0;
                }
                else if(board[i][j]==symbols.O)
                {
                    counterO+=1;
                    if (counterO==2)
                        pairsRowO+=1;
                    if (counterO==3)
                        triosRowO+=1;
                    if(counterX!=0)
                        counterX=0;
                }
                else if(board[i][j]==symbols.E && counterO!=0 || counterX!=0)
                {
                    counterO=0;
                    counterX=0;
                }

            }
        }





        double pairsX=0;
        double triosX=0;
        double pairsO=0;
        double triosO=0;
        for (int i =0;i<columns;i++) {
            counterO = 0;
            counterX = 0;
            for (int j = rows-1; j >0; j--) {

                if(board[j][i]==symbols.X)
                {

                    counterX+=1;
                    if (counterX==2)
                        pairsX+=1;
                    if (counterX==3)
                        triosX+=1;
                }
                else if(board[j][i]==symbols.O)
                {
                    counterO+=1;
                    if (counterO==2)
                        pairsO+=1;
                    if (counterO==3)
                        triosO+=1;
                }

            }
            }

        double weight=0;
        for (int i =rows-1;i>=0;i--) {
            counterO = 0;
            counterX = 0;

            weight+=50;
            if(i==1)
                weight=10000;
            for (int j = 0; j < columns; j++) {
                if(board[i][j]==symbols.X)
                {
                    counterX+=1;
                    if(j<=columns/2)
                    resultMax+=(j+1)*100+weight*4;
                    else if (j>columns/2)
                    {

                        resultMax+=(j)*100+weight*4-50*j;
                    }
                    if (counterO!=0)
                        counterO=0;
                }
                else if (board[i][j]==symbols.O)
                {
                    counterO+=1;
                    if(j<=columns/2)
                        resultMin+=(j+1)*100+weight*4;
                    else if (j>columns/2)
                    {

                        resultMin+=(j)*100+weight*4-50*j;
                    }
                    if (counterX!=0)
                        counterX=0;
                }
            }
//            for (int j = columns-1; j >= columns/2; j--) {
//                if(board[i][j]==symbols.X)
//                {
//                    counterX+=1;
//                    resultMax+=(j+1)*100+weight*4;
//                    if (counterO!=0)
//                        counterO=0;
//
//                }
//                else if (board[i][j]==symbols.O)
//                {
//                    counterO+=1;
//                    resultMin+=(j+1)*100+weight*4;
//                    if (counterX!=0)
//                        counterX=0;
//                }
//            }

            }

        //look for pairs trios
        resultMax+=pairsX*4+triosX*1000+pairsRowX*4+triosRowX*1000+10;
         resultMin+=pairsO*4+triosO*1000+pairsRowO*4+triosRowO*1000;
        return resultMax - resultMin;
        //return 0.0;
    }

}

