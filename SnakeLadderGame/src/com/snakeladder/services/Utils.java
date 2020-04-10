package com.snakeladder.services;

import java.util.Random;

import com.snakeladder.model.Board;
import com.snakeladder.model.Ladder;
import com.snakeladder.model.Player;
import com.snakeladder.model.Snake;

public class Utils {
	private static Random random = new Random();
	public static int getDice(int max,int min) {
	    return random.nextInt((max - min) + 1) + min;
	}

	public static boolean isValidPosition(Player player, int diceNumber) {
		int playerLocation = player.getLocation();
		int finalLocation = playerLocation + diceNumber;
		if (finalLocation <= 100) {
			return true;
		}
		return false;
	}

	public static int finalLocation(int location, Board board) {
		int finalLocation = location;
		Snake[] snakes = board.getSnakes();
		Ladder[] ladders = board.getLadders();
		for (Snake snake : snakes) {
			if (isBittenBySnake(snake, finalLocation)) {
				return finalLocation(snake.getEnd(), board);
			}
		}
		for (Ladder ladder : ladders) {
			if (ladderFound(ladder, finalLocation)) {
				return finalLocation(ladder.getEnd(), board);
			}
		}
		return finalLocation;
	}

	public static boolean isBittenBySnake(Snake snake, int location) {
		return snake.getStart() == location;
	}

	public static boolean ladderFound(Ladder ladder, int location) {
		return ladder.getStart() == location;
	}
}
