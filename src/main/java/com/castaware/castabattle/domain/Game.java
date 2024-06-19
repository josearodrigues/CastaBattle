package com.castaware.castabattle.domain;

import static com.castaware.castabattle.domain.CellType.BOAT;
import static com.castaware.castabattle.domain.CellType.BATTLESHIP;
import static com.castaware.castabattle.domain.CellType.CRUISER;
import static com.castaware.castabattle.domain.CellType.DESTROYER;
import static com.castaware.castabattle.domain.CellType.SUBMARINE;
import static com.castaware.castabattle.domain.CellType.WATER;

public class Game {
	private static Board userBoard;
	private static Board cpuBoard;
	
	private static CellType[][] user = new CellType[][] {
/*			{WATER,      WATER,      WATER,      WATER,      WATER,      BOAT,      WATER,     SUBMARINE, SUBMARINE, WATER},   
			{DESTROYER,  DESTROYER,  DESTROYER,  DESTROYER,  WATER,      WATER,     WATER,     WATER,     WATER,     WATER},	
			{WATER,      WATER,      WATER,      WATER,      WATER,      WATER,     WATER,     WATER,     WATER,     BOAT},		                                      
			{WATER,      WATER,      WATER,      SUBMARINE,  SUBMARINE,  WATER,     WATER,     WATER,     WATER,     WATER},		
			{WATER,      SUBMARINE,  WATER,      WATER,      WATER ,     WATER,     WATER,     WATER,     WATER,     CRUISER},		
			{WATER,      SUBMARINE,  WATER,      WATER,      WATER,      CRUISER,   CRUISER,   CRUISER,   WATER,     CRUISER},		
			{BOAT,       WATER,      WATER,      BOAT,       WATER,      WATER,     WATER,     WATER,     WATER,     CRUISER},		
			{WATER,      WATER,      WATER,      WATER,      WATER,      WATER,     WATER,     BOAT,      WATER,     WATER},		
			{BATTLESHIP, BATTLESHIP, BATTLESHIP, BATTLESHIP, BATTLESHIP, WATER,     WATER,     WATER,     WATER,     WATER},		
			{WATER,      WATER,      WATER,      WATER,      WATER,      WATER,     DESTROYER, DESTROYER, DESTROYER, DESTROYER} */
			{WATER, WATER, WATER, WATER, WATER, WATER, WATER, WATER, WATER, WATER},   
			{WATER, WATER, WATER, WATER, WATER, WATER, WATER, WATER, WATER, WATER},   
			{WATER, WATER, WATER, WATER, WATER, WATER, WATER, WATER, WATER, WATER},   
			{WATER, WATER, WATER, WATER, WATER, WATER, WATER, WATER, WATER, WATER},   
			{WATER, WATER, WATER, WATER, WATER, WATER, WATER, WATER, WATER, WATER},   
			{WATER, WATER, WATER, WATER, WATER, WATER, WATER, WATER, WATER, WATER},   
			{WATER, WATER, WATER, WATER, WATER, WATER, WATER, WATER, WATER, WATER},   
			{WATER, WATER, WATER, WATER, WATER, WATER, WATER, WATER, WATER, WATER},   
			{WATER, WATER, WATER, WATER, WATER, WATER, WATER, WATER, WATER, WATER},   
			{WATER, WATER, WATER, WATER, WATER, WATER, WATER, WATER, WATER, WATER},   
		};

	private static CellType[][] cpu = new CellType[][] {
			{WATER,      WATER,      WATER,      WATER,      WATER,      BOAT,      WATER,     SUBMARINE, SUBMARINE, WATER},   
			{DESTROYER,  DESTROYER,  DESTROYER,  DESTROYER,  WATER,      WATER,     WATER,     WATER,     WATER,     WATER},	
			{WATER,      WATER,      WATER,      WATER,      WATER,      WATER,     WATER,     WATER,     WATER,     BOAT},		                                      
			{WATER,      WATER,      WATER,      SUBMARINE,  SUBMARINE,  WATER,     WATER,     WATER,     WATER,     WATER},		
			{WATER,      SUBMARINE,  WATER,      WATER,      WATER ,     WATER,     WATER,     WATER,     WATER,     CRUISER},		
			{WATER,      SUBMARINE,  WATER,      WATER,      WATER,      CRUISER,   CRUISER,   CRUISER,   WATER,     CRUISER},		
			{BOAT,       WATER,      WATER,      BOAT,       WATER,      WATER,     WATER,     WATER,     WATER,     CRUISER},		
			{WATER,      WATER,      WATER,      WATER,      WATER,      WATER,     WATER,     BOAT,      WATER,     WATER},		
			{BATTLESHIP, BATTLESHIP, BATTLESHIP, BATTLESHIP, BATTLESHIP, WATER,     WATER,     WATER,     WATER,     WATER},		
			{WATER,      WATER,      WATER,      WATER,      WATER,      WATER,     DESTROYER, DESTROYER, DESTROYER, DESTROYER}
		};
	
	public static Board getUserBoard() {
		userBoard = new Board();
		userBoard.setBoardTemplate(user);
		userBoard.initBoardGame(user);
		return userBoard;
	}
	
	public static Board getCPUBoard() {
		cpuBoard = new Board();
		cpuBoard.setBoardTemplate(cpu);
		cpuBoard.initBoardGame(cpu);
		return cpuBoard;
	}
}