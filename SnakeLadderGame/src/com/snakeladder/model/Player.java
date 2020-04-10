package com.snakeladder.model;

public class Player {
	int playerId;
	String playerName;
	int location;

	public Player(int id, String name) {
		playerId = id;
		playerName = name;
		location = 0;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}


	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

}
