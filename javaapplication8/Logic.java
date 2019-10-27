package javaapplication8;

/**
 *
 * @author Hasib
 */
//Created by <<<<<Farsim & Hasib>>>>> ;
import java.util.*;//for random number

class Logic {

    int[][] blockS = {{4, 3, 5, 8, 7, 6, 1, 2, 9}, {8, 7, 6, 2, 1, 9, 3, 4, 5}, {2, 1, 9, 4, 3, 5, 7, 8, 6},
        {5, 2, 3, 6, 4, 7, 8, 9, 1}, {9, 8, 1, 5, 2, 3, 4, 6, 7}, {6, 4, 7, 9, 8, 1, 2, 5, 3}, {7, 5, 4, 1, 6, 8, 9, 3, 2},
        {3, 9, 2, 7, 5, 4, 6, 1, 8}, {1, 6, 8, 3, 9, 2, 5, 7, 4}};
    Random R_num = new Random(); //random numbers to exchange rows
    Random Grid_R_num = new Random();//random numbers to exchange GRIDS
    Random R_exnum = new Random();
    Random H_Rnum = new Random();
    int firstrow, secondrow, firstcol, secondcol, firstgrid, secondgrid, gc = 0;
    int carry[] = new int[9];
    int blockh[][] = new int[9][9];
    int blockc[][] = new int[9][9];

    int[][] generate() {   ////switching the rows
        //randomly choosing one of the tow rows to be changed
        int x = 10 + R_num.nextInt(10);
        for (int y = 0; y < x; y++) {
            for (int a = 0; a < 3; a++) {
                //System.out.println("a="+a);

                if (a == 0) {
                    firstrow = R_num.nextInt(3);
                    secondrow = R_num.nextInt(3);
                } else if (a == 1) {
                    firstrow = 3 + R_num.nextInt(3);
                    secondrow = 3 + R_num.nextInt(3);
                } else if (a == 2) {
                    firstrow = 6 + R_num.nextInt(3);
                    secondrow = 6 + R_num.nextInt(3);
                }

                //System.out.println("firstrow"+"="+firstrow);
                //System.out.println("secondrow"+"="+secondrow);


                for (int i = 0; i < 9; i++) {
                    carry[i] = blockS[firstrow][i];
                    blockS[firstrow][i] = blockS[secondrow][i];
                    blockS[secondrow][i] = carry[i];
                }
            }
            //switching the rows complete

            //Switchicng the column
            for (int a = 0; a < 3; a++) {
                //System.out.println("a="+a);

                if (a == 0) {
                    firstcol = R_num.nextInt(3);
                    secondcol = R_num.nextInt(3);
                } else if (a == 1) {
                    firstcol = 3 + R_num.nextInt(3);
                    secondcol = 3 + R_num.nextInt(3);
                } else if (a == 2) {
                    firstcol = 6 + R_num.nextInt(3);
                    secondcol = 6 + R_num.nextInt(3);
                }

                //System.out.println("firstrow"+"="+firstrow);
                //System.out.println("secondrow"+"="+secondrow);


                for (int i = 0; i < 9; i++) {
                    carry[i] = blockS[i][firstcol];
                    blockS[i][firstcol] = blockS[i][secondcol];
                    blockS[i][secondcol] = carry[i];
                }
            }
        }
        //Switchicng the column complet
        //Switchicng the grids
        firstgrid = 1 + Grid_R_num.nextInt(3);
        secondgrid = 1 + Grid_R_num.nextInt(3);

        //System.out.println("firstgrid"+"="+firstgrid);
        //System.out.println("secondgrid"+"="+secondgrid);

        if ((firstgrid == 1 && secondgrid == 2) || (firstgrid == 2 && secondgrid == 1)) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 9; j++) {
                    carry[j] = blockS[i][j];
                    blockS[i][j] = blockS[i + 3][j];
                    blockS[i + 3][j] = carry[j];
                }
            }
        } else if ((firstgrid == 2 && secondgrid == 3) || (firstgrid == 3 && secondgrid == 2)) {
            for (int i = 3; i < 6; i++) {
                for (int j = 0; j < 9; j++) {
                    carry[j] = blockS[i][j];
                    blockS[i][j] = blockS[i + 3][j];
                    blockS[i + 3][j] = carry[j];
                }
            }
        } else if ((firstgrid == 1 && secondgrid == 3) || (firstgrid == 3 && secondgrid == 1)) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 9; j++) {
                    carry[j] = blockS[i][j];
                    blockS[i][j] = blockS[i + 6][j];
                    blockS[i + 6][j] = carry[j];
                }
            }
        }
        //Swithing complete of tow grids


        //suffling the puzzle
        int firstnum, secondnum, shuffle;

        shuffle = 50 + R_num.nextInt(6);
        //System.out.println("Shufflenum="+shuffle);
        for (int k = 0; k < shuffle; k++) {
            firstnum = 1 + R_exnum.nextInt(9);
            secondnum = 1 + R_exnum.nextInt(9);

            //System.out.println("firstnum="+firstnum);
            //System.out.println("secondnum="+secondnum);

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (blockS[i][j] == firstnum) {
                        blockS[i][j] = secondnum;
                        continue;
                    }

                    if (blockS[i][j] == secondnum) {
                        blockS[i][j] = firstnum;
                    }
                }
            }
        }
        return blockS;
    }
    //will save the complet puzzle

    int[][] save() {
        if (gc == 0) {
            blockc = generate();
        }

        gc = 1;

        return blockc;
    }

    //will hide  number of puzzle
    int[][] hide() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                blockh[i][j] = blockc[i][j];
            }
        }

        int row, column, hidingnum;

        hidingnum = +R_num.nextInt(3);

        for (int i = 0; i < hidingnum; i++) {
            row = H_Rnum.nextInt(9);
            column = H_Rnum.nextInt(9);
            //System.out.println("\nrow,col = ("+row+","+column+")");
            blockh[row][column] = 0;
        }
        return blockh;
    }

    int[][] hide2() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                blockh[i][j] = blockc[i][j];
            }
        }

        int row, column, hidingnum;

        hidingnum = 48 + R_num.nextInt(3);


        for (int i = 0; i < hidingnum; i++) {
            row = H_Rnum.nextInt(9);
            column = H_Rnum.nextInt(9);
            blockh[row][column] = 0;
        }
        return blockh;
    }

    int[][] hide3() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                blockh[i][j] = blockc[i][j];
            }
        }

        int row, column, hidingnum;

        hidingnum = 58 + R_num.nextInt(3);


        for (int i = 0; i < hidingnum; i++) {
            row = H_Rnum.nextInt(9);
            column = H_Rnum.nextInt(9);
            blockh[row][column] = 0;
        }
        return blockh;
    }
    //will show main puzzle
    /*void show()
    {
    for(int i=0;i<9;i++)
    {
    System.out.println();
    for(int j=0;j<9;j++)
    System.out.print(" "+blockS[i][j]);
    }
    System.out.println("\n");
    System.out.println("\n");
    blockS=save();
    for(int i=0;i<9;i++)
    {
    System.out.println();
    for(int j=0;j<9;j++)
    System.out.print(" "+blockS[i][j]);
    }
    System.out.println("\n");
    System.out.println("\n");
    blockS=hide();
    for(int i=0;i<9;i++)
    {
    System.out.println();
    for(int j=0;j<9;j++)
    System.out.print(" "+blockS[i][j]);
    }
    }*/
    /*public static void main (String[] args) {

    Logic ob=new Logic();
    //ob.generate();
    ob.show();
    //System.out.println();
    //ob.save();
    //ob.show();
    //ob.hide();
    //ob.show();
    }*/
}
