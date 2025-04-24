package hw4.simulation;

import java.util.*;
import hw4.game.*;
import hw4.maze.*;
import hw4.player.Movement;
import hw4.player.Player;
import java.util.Scanner;

public class Main {
//Simulates the game being played by prompting the user for movement input.
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		//Integer to generate a random grid (may be changed).
		int n = 3;
		Game game = new Game(n);
		//Player is set to start in the bottom-right corner.
		Player player = new Player(game.getGrid().getRows().get(n-1), game.getGrid().getRows().get(n-1).getCells().get(n-1));
		
		System.out.println("Please input a valid movement (UP, DOWN, LEFT, or RIGHT");
		game.printGrid(player);
		//Keeps the game going until the exit is reached.
		while(!game.getWin()) {
			String command = input.nextLine().toUpperCase();
			switch(command) {
			case "UP":
				game.play(Movement.UP, player);
				game.printGrid(player);
				break;
			
			case "DOWN":
				game.play(Movement.DOWN, player);
				System.out.println(player.toString());
				game.printGrid(player);
				break;
			case "LEFT":
				game.play(Movement.LEFT, player);
				game.printGrid(player);
				break;
			case "RIGHT":
				game.play(Movement.UP, player);
				game.printGrid(player);
				break;
			default:
				System.out.println("Please input a valid movement (UP, DOWN, LEFT, or RIGHT");
				break;
			}
		}
		input.close();
		System.out.println("You made it out of the maze!");
	}
}
