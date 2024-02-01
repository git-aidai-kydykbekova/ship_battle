import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Main
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        playGame();

        boolean play = true;
        while(play) {
            System.out.println("Do you want to play again?");
            System.out.println("1 - YES");
            System.out.println("0 - NO");


            int answer = scanner.nextInt();
            if (answer == 1) {
                playGame();
            } else if (answer == 0) {
                System.out.println("Game is over");
                System.out.println();
                play = false;
            }
        }




    }
    public static void playGame() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("What is your name?");
        String name = scanner.nextLine();
        int attempts = 0;


        String [] [] table = new String [7] [7];
        for(int i = 0; i < 7; i++) {
            for(int j = 0; j < 7; j++) {
                table[i][j] = "0";
            }
        }

        String [] [] userTable = new String [7] [7];
        for(int i = 0; i < 7; i++) {
            for(int j = 0; j < 7; j++) {
                userTable[i][j] = "0";
            }
        }
        printUserTable(userTable);


        createThreeDeskShips(table);
        createTwoDeskShip(table);
        createOneDeskShip(table);

        System.out.println();

//        for(int i = 0; i < 7; i++) {
//            for(int j = 0; j < 7; j++) {
//                System.out.print(table[i][j] + " ");//////for checking
//            }
//            System.out.println();
//        }

        userTried(table,userTable,attempts);

        ArrayList<Integer> arrayAttempts = new ArrayList<Integer>();
        ArrayList<String> arrayNames = new ArrayList<String>();

        arrayAttempts.add(attempts);
        arrayNames.add(name);


    }
    public static void userTried(String [] [] table,String [] [] userTable, int attempts) {
        Scanner scanner = new Scanner(System.in);
        boolean isHit = false;


        int k = 0;
        while(k<6) {
            System.out.println("Enter the coordinates ");



            int x = scanner.nextInt();
            int y = scanner.nextInt();

            if(x > 7 || x < 1 || y > 7 || y < 1) {
                System.out.println("BAD INPUT");
                System.out.println("Enter another coordinates");
                userTried(table,userTable, attempts);
                System.exit(0);

            }


            int coordinate1 = x - 1;
            int coordinate2 = y - 1;

            if(table[coordinate1][coordinate2] == "-") {
                System.out.println("You already used that coordinates");
            }

            attempts++;


            if (table[coordinate1][coordinate2] != "0" && table[coordinate1][coordinate2] != "4") {
                //isHit = true;
                if (table[coordinate1][coordinate2] == "1") {   //if it is 1 deck ship
                    clearScreen();
                    System.out.println("SUNK");
                    k++;
                    userTable[coordinate1][coordinate2] = "◉";
                    table[coordinate1][coordinate2] = "-";
                    printUserTable(userTable);
                }
                else if (table[coordinate1][coordinate2] == "2") {   //if it is 2 deck ship

                    clearScreen();

                    if (coordinate1 == 0) {
                        userTable[coordinate1][coordinate2] = "X";
                        if (userTable[coordinate1 + 1][coordinate2] == "X") {
                            System.out.println("SUNK");
                            k++;
                            userTable[coordinate1][coordinate2] = "◉";
                            userTable[coordinate1 + 1][coordinate2] = "◉";

                        } else {
                            System.out.println("HIT");
                        }
                    }
                    else if(coordinate1 == 6) {
                        userTable[coordinate1][coordinate2] = "X";
                        if (userTable[coordinate1 - 1][coordinate2] == "X") {
                            System.out.println("SUNK");
                            k++;
                            userTable[coordinate1][coordinate2] = "◉";
                            userTable[coordinate1 - 1][coordinate2] = "◉";
                        }
                        else {
                            System.out.println("HIT");
                        }
                    }

                    else {
                        userTable[coordinate1][coordinate2] = "X";
                        if (userTable[coordinate1 - 1][coordinate2] == "X" ) {
                            System.out.println("SUNK");
                            k++;
                            userTable[coordinate1][coordinate2] = "◉";
                            userTable[coordinate1 - 1][coordinate2] = "◉";
                        }
                        else if(userTable[coordinate1 + 1][coordinate2] == "X") {
                            System.out.println("SUNK");
                            k++;
                            userTable[coordinate1][coordinate2] = "◉";
                            userTable[coordinate1 + 1][coordinate2] = "◉";
                        }
                        else System.out.println("HIT");


                    }
                    table[coordinate1][coordinate2] = "-";
                    printUserTable(userTable);
                }


                else if(table[coordinate1][coordinate2] == "3") {
                    clearScreen();
                    userTable[coordinate1][coordinate2] = "X";

                    if (coordinate2 == 0) { // check only from right side
                        if (userTable[coordinate1][coordinate2 + 1] == "X" && userTable[coordinate1][coordinate2 + 2] == "X") {
                            System.out.println("SUNK");
                            k++;
                            userTable[coordinate1][coordinate2] = "◉";
                            userTable[coordinate1][coordinate2 + 1] = "◉";
                            userTable[coordinate1][coordinate2 + 2] = "◉";
                        } else if (userTable[coordinate1][coordinate2 + 1] == "X" || userTable[coordinate1][coordinate2 + 2] == "X") {
                            System.out.println("HIT");
                        }

                    }
                    else if(coordinate2 == 6) { // check only from left side
                        if (userTable[coordinate1][coordinate2 - 1] == "X" && userTable[coordinate1][coordinate2 - 2] == "X") {
                            System.out.println("SUNK");
                            k++;
                            userTable[coordinate1][coordinate2] = "◉";
                            userTable[coordinate1][coordinate2 - 1] = "◉";
                            userTable[coordinate1][coordinate2 - 2] = "◉";
                        } else if (userTable[coordinate1][coordinate2 - 1] == "X" || userTable[coordinate1][coordinate2 - 2] == "X") {
                            System.out.println("HIT");
                        }

                    }
                    else {
                        if(userTable[coordinate1][coordinate2 - 1] == "X" && userTable[coordinate1][coordinate2 + 1] == "X" ) {
                            System.out.println("SUNK");
                            k++;
                            userTable[coordinate1][coordinate2] = "◉";
                            userTable[coordinate1][coordinate2 - 1] = "◉";
                            userTable[coordinate1][coordinate2 + 1] = "◉";
                        }
                        else if(userTable[coordinate1][coordinate2 + 1] == "X" && userTable[coordinate1][coordinate2 + 2] == "X") {
                            System.out.println("SUNK");
                            k++;
                            userTable[coordinate1][coordinate2] = "◉";
                            userTable[coordinate1][coordinate2 + 2] = "◉";
                            userTable[coordinate1][coordinate2 + 1] = "◉";
                        }
                        else if( userTable[coordinate1][coordinate2 - 1] == "X" && userTable[coordinate1][coordinate2 - 2] == "X"){
                            System.out.println("SUNK");
                            k++;
                            userTable[coordinate1][coordinate2] = "◉";
                            userTable[coordinate1][coordinate2 - 2] = "◉";
                            userTable[coordinate1][coordinate2 - 1] = "◉";
                        }
                        else System.out.println("HIT");
                    }
                    table[coordinate1][coordinate2] = "-";
                     printUserTable(userTable);

                }


            }
            else {
                clearScreen();
                System.out.println("MISS");
                userTable [coordinate1][coordinate2] = "*";
                printUserTable(userTable);

                table[coordinate1][coordinate2] = "-";
            }
        }

        System.out.println(" !!!!!! CONGRATULATIONS !!!!!");


        printUserTable(userTable);
    }
    public static void createOneDeskShip(String [] [] table) {
        Random random = new Random();

        boolean isMatch = false;
        int x = 0;
        int y = 0;

        // here a desk, which in down
        while (!isMatch) {
            x = random.nextInt(7);
            y = random.nextInt(7);

            if (table[x][y] == "0" && x == 6 && y<6 && y>0) {
                isMatch = true;
                table[x][y] = "1";

                table[x-1][y] = "4";
                table[x-1][y-1] = "4";
                table[x-1][y+1] = "4";
                table[x][y-1] = "4";
                table[x][y+1] = "4";
            }
        }
        // here a desk, which in up
        while (isMatch) {
            x = random.nextInt(7);
            y = random.nextInt(7);

            if (table[x][y] == "0" && x == 0 && y<6 && y>0) {
                isMatch = false;
                table[x][y] = "1";

                table[x+1][y] = "4";
                table[x+1][y-1] = "4";
                table[x+1][y+1] = "4";
                table[x][y-1] = "4";
                table[x][y+1] = "4";
            }
        }
        //here a desk, which in middle of table
        while (!isMatch) {
            x = random.nextInt(7);
            y = random.nextInt(7);

            if (table[x][y] == "0" && x != 6 && x != 0 && y<6 && y>0) {
                isMatch = true;
                table[x][y] = "1";

                table[x-1][y] = "4";
                table[x-1][y-1] = "4";
                table[x-1][y+1] = "4";
                table[x][y-1] = "4";
                table[x][y+1] = "4";
                table[x+1][y] = "4";
                table[x+1][y-1] = "4";
                table[x+1][y+1] = "4";

            }
        }

    }
    public static void createTwoDeskShip(String [] [] table) {
        Random random = new Random();

        boolean isMatch = false;
        int x = 0;
        int y = 0;

        while(isMatch == false ){
            x = random.nextInt(7);
            y = random.nextInt(7);

            if(y==6 && x!=6 && table[x][y]!="4" && table[x+1][y] != "4") {
                isMatch = true;

                table[x][y] = "2";       //Two Deck Ship number 2
                table[x+1][y] = "2";

                table[x][y-1] = "4";
                table[x+1][y-1] = "4";
                if(x==0) {
                    table[x+2][y] = "4";
                    table[x+2][y-1] = "4";
                }
                else if(x==5) {
                    table[x-1][y] = "4";
                    table[x-1][y-1] = "4";
                }
                else {
                    table[x+2][y] = "4";
                    table[x+2][y-1] = "4";
                    table[x-1][y] = "4";
                    table[x-1][y-1] = "4";
                }
            }
        }

        while(isMatch == true) {
            x = random.nextInt(6);
            y = random.nextInt(6);

            if (y == 0 && x != 6 && table[x][y] != "4" && table[x + 1][y] != "4") {
                isMatch = false;

                table[x][y] = "2";       //Two Deck Ship
                table[x + 1][y] = "2";

                table[x][y + 1] = "4";
                table[x + 1][y + 1] = "4";
                if (x == 0) {
                    table[x + 2][y] = "4";
                    table[x + 2][y + 1] = "4";
                } else if (x == 5) {
                    table[x - 1][y] = "4";
                    table[x - 1][y + 1] = "4";
                } else {
                    table[x + 2][y] = "4";
                    table[x + 2][y + 1] = "4";
                    table[x - 1][y] = "4";
                    table[x - 1][y + 1] = "4";
                }
            }
        }
    }

    public static void createThreeDeskShips(String [] [] table) {
        Random random = new Random();

        boolean isMatch = false;

        int x = 0;
        int y = 0;

        while(isMatch == false){
            x = random.nextInt(7);
            y = random.nextInt(7);
            if(y>1 && y<5 && x>1 && x < 5 ) {
                isMatch = true;

                int y1 = y+1;
                int y2 = y-1;

                table[x][y] = "3";
                table[x][y1] = "3";   //threeDeckShip
                table[x][y2] = "3";

                table[x+1][y] = "4";
                table[x-1][y] = "4";
                table[x+1][y+1] = "4";
                table[x-1][y+1] = "4";
                table[x+1][y-1] = "4";
                table[x-1][y-1] = "4";
                table[x-1][y-2] = "4";
                table[x][y-2] = "4";
                table[x+1][y-2] = "4";
                table[x][y+2] = "4";
                table[x-1][y+2] = "4";
                table[x+1][y+2] = "4";
            }
        }

//        createTwoDeskShip(table);
//        createOneDeskShip(table);

    }
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
    }

    public static void printUserTable(String [] [] userTable) {
        for(int i = 0; i < 7; i++) {
            for(int j = 0; j < 7; j++) {
                System.out.print(userTable[i][j] + " ");
            }
            System.out.println();
        }
    }
}

