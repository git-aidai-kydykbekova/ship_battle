import java.util.Random;
import java.util.Scanner;
import java.util.Scanner;

public class Main
{
	public static void main(String[] args) {
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
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 7; j++) {
				System.out.print(userTable[i][j] + " ");
			}
			System.out.println();
		}
		createTreeDeskShips(table);
		userTried(table,userTable);



	}
	public static void userTried(String [] [] table,String [] [] userTable) {
		Scanner scanner = new Scanner(System.in);
		boolean isHit = false;


		int k = 0;
		while(k<10) {
			System.out.println("Enter the coordinates ");

			int coordinate1 = scanner.nextInt();
			int coordinate2 = scanner.nextInt();

			if (table[coordinate1][coordinate2] != "0" && table[coordinate1][coordinate2] != "4") {
				isHit = true;
				if (table[coordinate1][coordinate2] == "1") {
					System.out.println("SUNK");
					userTable [coordinate1][coordinate2] = "#";
					for(int i = 0; i < 7; i++) {
						for(int j = 0; j < 7; j++) {
							System.out.print(userTable[i][j] + " ");
						}
						System.out.println();
					}

				}
				else if (table[coordinate1][coordinate2] == "2" || table[coordinate1][coordinate2] == "3") {
					System.out.println("HIT");
					userTable [coordinate1][coordinate2] = "X";
					for(int i = 0; i < 7; i++) {
						for(int j = 0; j < 7; j++) {
							System.out.print(userTable[i][j] + " ");
						}
						System.out.println();
					}

				}
			}

			else {
				System.out.println("MISS");
				userTable [coordinate1][coordinate2] = "*";
				for(int i = 0; i < 7; i++) {
					for(int j = 0; j < 7; j++) {
						System.out.print(userTable[i][j] + " ");
					}
					System.out.println();
				}
			}
		}
		k++;

		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 7; j++) {
				System.out.print(userTable[i][j] + " ");
			}
			System.out.println();
		}
	}
	public static void createOneDeskShip(String [] [] table) {
		Random random = new Random();

		boolean isMatch = false;
		int x = 0;
		int y = 0;

		// here a desk, which in down
		while (isMatch == false ) {
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
		while (isMatch == false ) {
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

	public static void createTreeDeskShips(String [] [] table) {
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

		createTwoDeskShip(table);
		createOneDeskShip(table);

		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 7; j++) {
				System.out.print(table[i][j] + " ");
			}
			System.out.println();
		}
	}
}

