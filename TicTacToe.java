// Load required classes
import java.util.Scanner;
import java.io.File;

public class TicTacToe{
    public static void main(String[] args) throws Exception {
        int[][] board;
        int player1 = 5;
        int player2 = 7;
        int counter;
        
        // Scanner variable
        Scanner reader;
        // If an argument is present, we are reading from a file
        // specified in args[0]
        if(args.length > 0){
            reader = new Scanner(new File(args[0]));
        }
        else{
            reader = new Scanner(System.in);
        }
            
        //Defines dimensions of Board
        int m = Integer.parseInt(reader.nextLine());
        int n = Integer.parseInt(reader.nextLine());

        board = new int[m][n];
        //Sets base values
        int x;
        int y;
        counter = 2;
        boolean win = false;
        //Reads each move and adds the players placement to the array
        while(true){
            try{
                x = Integer.parseInt(reader.nextLine());
                y = Integer.parseInt(reader.nextLine());

                if(board[x][y]==0){
                    //places move
                    if(counter%2==0){
                        board[x][y] = player1;
                    }else{
                        board[x][y] = player2;
                    }

                    //checks for a winning move

                    if(m==n){
                        //checks diagonals
                        int diagonalCounter = 0;
                        for(int i = 0; i < m; i++){
                            if(board[0][0]==0){
                                break;
                            }
                            if(board[i][i] == board[0][0]){
                                diagonalCounter++;
                                if(diagonalCounter == m){
                                  System.out.println("Winning move at " + x + " " + y);
                                    win = true;
                                  break;
                                }
                            }else{
                                break;
                            }

                        }
                        if(win){break;}
                        int SecondDiagonalX = 0;
                        int SecondDiagonalY = n - 1;
                        for(int i = 0; i < n; i++){
                            if(board[n-1][0]==0){
                                break;
                            }
                            if(board[SecondDiagonalY][SecondDiagonalX] == board[n-1][0]){
                                if(SecondDiagonalX == n-1){
                                  System.out.println("Winning move at " + x + " " + y);
                                  win = true;
                                  break;
                                }
                                SecondDiagonalX++;
                                SecondDiagonalY--;
                            }else{
                                break;
                            }
                        }
                        if(win){break;}
                    }

                    //Checks rows
                    //Moves from row to row

                        for(int i = 0; i < m; i++){
                            if(board[i][0]==0){
                                continue;
                            }
                            //Checks each spot in the specified row
                            for(int j = 0; j < n; j++){
                                if(board[i][j]==board[i][0]){
                                    if(j == n-1){
                                        System.out.println("Winning move at " + x + " " + y);
                                        win = true;
                                        break;
                                    }
                                }else{
                                    break;
                                }
                            }
                            if(win){break;}
                        }
                        if(win){break;}
                    //Checks Columns
                        //Moves from column to column
                        for(int i = 0; i < n; i++){
                            if(board[0][i]==0){
                                continue;
                            }
                            //Checks each spot in the specified column
                            for(int j = 0; j < m; j++){
                                if(board[j][i]==board[0][i]){
                                    if(j == m-1){
                                        System.out.println("Winning move at " + x + " " + y);
                                        win = true;
                                        break;
                                    }
                                }
                                else{
                                    break;
                                }

                            }
                            if(win){break;}
                        }
                        if(win){break;}
                    boolean zeroFound = false;
                    for(int i = 0; i < m; i++){

                            for(int j = 0; j < n; j++){
                                if(board[i][j]==0){
                                    zeroFound = true;
                                    break;
                                }
                            }
                            if(zeroFound){
                                break;
                            }
                        }
                    if(zeroFound == false){
                        System.out.println("Draw at " + x + " " + y);
                        break;
                    }
                }else{
                    System.out.println("Invalid move at " + x + " " + y);
                }
                //starts next turn
                counter++;

            }catch(Exception e){
                System.out.println(e);
                break;
            }
        }
    }
}