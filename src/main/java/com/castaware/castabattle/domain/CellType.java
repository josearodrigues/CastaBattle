package com.castaware.castabattle.domain;

public enum CellType {
	WATER(false),
	BOAT(true),
	CRUISER(true),
	DESTROYER(true),
	SUBMARINE(true),
	BATTLESHIP(true),
	FIRE(false),
	FAIL(false),
	HIDDEN(false);
	
	private boolean ship;
	
	private CellType(boolean ship) {
		this.ship = ship;
	}
	
	public boolean isShip() {
		return ship;
	}
}