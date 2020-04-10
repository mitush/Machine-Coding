package com.snakeladder.model;

public class Board {
	int boardSize;
	int activePlayer;
	Player[] players;
	Snake[] snakes;
	Ladder[] ladders;

	public Board(int size, Player[] players, Snake[] snakes, Ladder[] ladders) {
		activePlayer = 0;
		boardSize = size * size;
		this.players = players;
		this.snakes = snakes;
		this.ladders = ladders;
	}

	public int getActivePlayer() {
		return activePlayer;
	}

	public void setActivePlayer(int activePlayer) {
		this.activePlayer = activePlayer;
	}

	public Player[] getPlayers() {
		return players;
	}

	public void setPlayers(Player[] players) {
		this.players = players;
	}

	public Snake[] getSnakes() {
		return snakes;
	}

	public void setSnakes(Snake[] snakes) {
		this.snakes = snakes;
	}

	public Ladder[] getLadders() {
		return ladders;
	}

	public void setLadders(Ladder[] ladders) {
		this.ladders = ladders;
	}

}
