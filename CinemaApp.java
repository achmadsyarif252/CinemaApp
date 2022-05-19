package jetbrains_academy;

import java.util.Arrays;
import java.util.Scanner;

public class CinemaApp {
	static Scanner scanner = new Scanner(System.in);
	static boolean[][] bookedSeats;
	static int row;
	static int column;
	static int currentTotalIncome = 0;
	static int totalIncome = 0;

	static void calculatePrice(int row, int column, int rowNumber, int seatNumber) {
		if (seatNumber > column || rowNumber > row) {
			System.out.println("Limit is " + column);
			return;
		}

		int ticket_price;
		int totalSeat = row * column;
		if (totalSeat < 60) {
			ticket_price = 10;

		} else {
			ticket_price = rowNumber > row / 2 ? 8 : 10;
		}

		System.out.println("Ticket price :$" + ticket_price + "\n");
		currentTotalIncome += ticket_price;
	}

	static void printSeat(int row, int column, boolean[][] bookedSeats) {
		System.out.println();
		System.out.println("Cinema:\n");

		for (int i = 1; i <= column; i++)
			System.out.print(" " + i);
		System.out.println();

		for (int i = 0; i < row; i++) {
			System.out.print(i + 1 + " ");
			for (int j = 0; j < column; j++) {
				System.out.print(bookedSeats[i][j] ? "B " : "S ");
			}
			System.out.println();
		}
		System.out.println();
	}

	static void showMenu() {

		boolean isExit = false;
		scanner.nextLine();
		while (!isExit) {
			System.out.println("1.Show the seats");
			System.out.println("2.Buy a ticket");
			System.out.println("3.Statistic");
			System.out.println("0.Exit");

			String input = scanner.nextLine();
			switch (input) {
			case "1":
				printSeat(row, column, bookedSeats);
				break;
			case "2":
				buyTicket();
				break;
			case "3":
				statistic();
				break;
			case "0":
				isExit = true;
				return;
			}
		}
	}

	static void statistic() {
		int totalPurchased = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if (bookedSeats[i][j] == true) {
					totalPurchased += 1;
				}
			}
		}
		System.out.println("Number of purchased tickets: " + totalPurchased);
		double percentage = (double) totalPurchased / (row * column) * 100;
		System.out.printf("Percentage :%.2f%% %n", percentage);
		System.out.printf("Current Income: $%d%n", currentTotalIncome);
		System.out.printf("Total income: $%d%n%n", totalIncome);

	}

	static void buyTicket() {
		boolean isValid = false;
		int rowNumber = 0;
		int seatNumber = 0;
		while (!isValid) {
			System.out.println("Enter a row number:");
			rowNumber = scanner.nextInt();

			System.out.println("Enter a seat number in that row:");
			seatNumber = scanner.nextInt();

			if (rowNumber > row || seatNumber > column) {
				System.out.println("Wrong input!\n");
			} else if (bookedSeats[rowNumber - 1][seatNumber - 1] == true) {
				System.out.println("That ticket has already been purchased!\n");
			} else {
				isValid = true;
				bookedSeats[rowNumber - 1][seatNumber - 1] = true;

			}
		}
		calculatePrice(row, column, rowNumber, seatNumber);
		totalIncome();
		scanner.nextLine();

	}

	static void totalIncome() {
		if (row * column < 60) {
			totalIncome = 10 * row * column;
		} else {
			int incomeFrontHalf = (row / 2 * 10) * column;
			int incomeBackHalf = (row % 2 == 0 ? row / 2 * 8 : ((row / 2) + 1) * 8) * column;
			System.out.println(incomeFrontHalf);
			System.out.println(incomeBackHalf);
			totalIncome = incomeFrontHalf + incomeBackHalf;
		}
	}

	public static void main(String[] args) {
		System.out.println();
		System.out.println("Enter the number of rows");
		row = scanner.nextInt();
		System.out.println("Enter the number of seats in each row");
		column = scanner.nextInt();
		bookedSeats = new boolean[row][column];
		totalIncome();

		System.out.println();
		showMenu();

	}
}
