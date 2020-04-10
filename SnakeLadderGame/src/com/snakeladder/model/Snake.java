package com.snakeladder.model;

public class Snake {
	int start;
	int end;
	
	public Snake(int x,int y){
		start = x;
		end = y;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}
}
