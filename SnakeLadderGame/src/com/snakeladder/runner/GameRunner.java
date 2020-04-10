package com.snakeladder.runner;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Enumeration;
import java.util.Scanner;

import com.snakeladder.model.Board;
import com.snakeladder.model.Ladder;
import com.snakeladder.model.Player;
import com.snakeladder.model.Snake;
import com.snakeladder.services.Utils;

public class GameRunner {

	public static void main(String[] args) {
		for (int i = 0; i <= 10; i++) {
			System.out.println("Game " + (i + 1));
			runGame();
		}
	}

	private static void runGame() {
		GameRunner game = new GameRunner();
		try {
			Board board = game.initializeBoard();
			int chance = 0;
			int pos = 0;
			int diceMin = 1;
			int diceMax = 6;
			playGame(board, chance, pos, diceMin, diceMax);
		} catch (Exception exp) {
			System.out.println("Exception occured");
		}
	}

	public Board initializeBoard() {
		Board board = null;
		try {
			InputStream inputStreamReader = getClass().getClassLoader().getResourceAsStream("input.txt");
			BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStreamReader));
			Snake[] snakes = getSnakes(bufferReader);
			Ladder[] ladders = getLadders(bufferReader);
			Player[] players = getPlayers(bufferReader);
			int boardSize = 10;
			board = new Board(boardSize, players, snakes, ladders);
			bufferReader.close();
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return board;
	}

	private static void playGame(Board board, int chance, int pos, int diceMin, int diceMax) {
		while (true) {
			// generate a dice number in range [1,6]
			int diceNumber = Utils.getDice(diceMax, diceMin);
			pos += diceNumber;
			// check for valid position
			Player player = getCurrentPlayer(board);
			System.out.print(player.getPlayerName() + " rolled a " + diceNumber + " and ");
			chance++;
			if (Utils.isValidPosition(player, pos)) {
				// three turns means no turn
				if (chance == 3 && pos == 3 * diceMax) {
					pos = 0;
					chance = 0;
					System.out.println("Chance aborted as 3 consequtive " + diceMax + " occured");
					board.setActivePlayer(nextPlayer(board));
					continue;
				}
				// repeat chance for that player because player got a 6
				if (diceNumber == diceMax) {
					continue;
				} else {
					boolean movePalyerToNextLocation = movePalyerToNextLocation(board, player, pos);
					if (movePalyerToNextLocation) {
						break;
					}
					board.setActivePlayer(nextPlayer(board));
				}
				pos = 0;
				chance = 0;
			} else if (chance == 3 && pos < 3 * diceMax) {
				chance = 0;
				pos = pos - diceNumber;
				boolean movePalyerToNextLocation = movePalyerToNextLocation(board, player, pos);
				if (movePalyerToNextLocation) {
					break;
				}
				board.setActivePlayer(nextPlayer(board));
			} else {
				chance = 0;
				pos = 0;
				board.setActivePlayer(nextPlayer(board));
			}
		}
	}

	private static boolean movePalyerToNextLocation(Board board, Player player, int pos) {
		int finalLocation = Utils.finalLocation(player.getLocation() + pos, board);
		pos = 0;
		String playerName = player.getPlayerName();
		System.out.println(playerName + " has moved from " + player.getLocation() + " to " + finalLocation);
		player.setLocation(finalLocation);
		if (finalLocation == 100) {
			System.out.println(playerName + " has won the game!");
		}
		return finalLocation == 100;
	}

	private static int nextPlayer(Board board) {
		return (board.getActivePlayer() + 1) % board.getPlayers().length;
	}

	private static Player getCurrentPlayer(Board board) {
		return board.getPlayers()[board.getActivePlayer()];
	}

	public static Player[] getPlayers(BufferedReader bufferReader) {
		Player[] players = null;
		try {
			int playerCount = Integer.parseInt(bufferReader.readLine());
			players = new Player[playerCount];
			for (int i = 0; i < playerCount; i++) {
				players[i] = new Player(i + 1, bufferReader.readLine());
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		return players;
	}

	public Snake[] getSnakes(BufferedReader bufferReader) {
		Snake[] snakes = null;
		try {
			int snakeCount = Integer.parseInt(bufferReader.readLine());
			snakes = new Snake[snakeCount];
			for (int i = 0; i < snakeCount; i++) {
				String[] snake = bufferReader.readLine().split(" ");
				snakes[i] = new Snake(Integer.parseInt(snake[0]), Integer.parseInt(snake[1]));
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		return snakes;
	}

	public static Ladder[] getLadders(BufferedReader bufferReader) {
		Ladder[] ladders = null;
		try {
			int snakeCount = Integer.parseInt(bufferReader.readLine());
			ladders = new Ladder[snakeCount];
			for (int i = 0; i < snakeCount; i++) {
				String[] snake = bufferReader.readLine().split(" ");
				ladders[i] = new Ladder(Integer.parseInt(snake[0]), Integer.parseInt(snake[1]));
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		return ladders;
	}
}
