package com.castaware.castabattle.domain;

import java.util.Arrays;
import java.util.HashMap;

public class Board {
	private static int SIZE = 10;
	
	private CellType[][] boardGame     = new CellType[SIZE][SIZE];
	private CellType[][] boardTemplate = new CellType[SIZE][SIZE];
	
	private static final int BOATS_LIMIT 		= 5;
	private static final int SUBMARINES_LIMIT 	= 3;
	private static final int CRUISERS_LIMIT		= 2;
	private static final int DESTROYERS_LIMIT 	= 2;
	private static final int BATTLESHIPS_LIMIT	= 1;

	private static int boatCount 		= 0;
	private static int submarineCount 	= 0;
	private static int cruiserCount 	= 0;
	private static int destroyerCount	= 0;
	private static int battleshipCount	= 0;
	
	private static int submarinePart  = 0;
	private static int cruiserPart    = 0;
	private static int destroyerPart  = 0;
	private static int battleshipPart = 0;

	private int acertou;
	private int errou;
	private static int tentativas = 0;

	private HashMap<Integer, String> result = new HashMap<Integer, String>();
	
	private static int hitPoints;
	
	public CellType[][] getBoardGame() {
		return boardGame;
	}
	
	public CellType[][] getBoardTemplate() {
		return boardTemplate;
	}

	public void setBoardGame(CellType[][] board) {
		boardGame = board;
	}

	public void setBoardTemplate(CellType[][] board) {
		boardTemplate = board;
	}
	
	public void initBoardGame(CellType[][] board) {
		for (int i=0;i<SIZE;i++) {
			for (int j=0;j<SIZE;j++) {
				boardGame[i][j] = CellType.HIDDEN;
			}
		}
		
		boardGame = board;
		
		for (int i=0;i<SIZE;i++) {
			for (int j=0;j<SIZE;j++) {
				CellType target = boardGame[i][j];
				
				if (target.isShip())
					hitPoints++;
			}
		}
	}
	
	public void initBoardTemplate(CellType[][] board) {
		boatCount = 0;
		submarineCount = 0;
		cruiserCount = 0;
		destroyerCount = 0;
		battleshipCount = 0;
		
		for (int i=0;i<SIZE;i++) {
			for (int j=0;j<SIZE;j++) {
				boardTemplate[i][j] = CellType.HIDDEN;
			}
		}
		
		boardTemplate = board;
		
		for (int i=0;i<SIZE;i++) {
			for (int j=0;j<SIZE;j++) {
				CellType target = boardTemplate[i][j];
				
				if (target.isShip())
					hitPoints++;
			}
		}
	}
	
	public CellType fire(int column, int line) {
		int y = translate(line);		
		int x = translate(column);
		CellType artefato;
		acertou = 0;
		errou = 0;
		tentativas++;
		
		CellType target = boardTemplate[y][x];
		
		// Se atingiu alguma embarcação, marca com fogo
		if (target.isShip()) {
			artefato = this.boardTemplate[y][x];
			
			if(artefato == CellType.BOAT) result.put(1, "Você afundou um BOAT!");
			if(artefato == CellType.SUBMARINE) {
				if (submarinePart == 1) {
					result.put(1, "Você afundou um SUBMARINE!");
					submarinePart = 0;
				} else {
					result.put(1, "Você acertou um SUBMARINE!");
					submarinePart++;
				}
			}
			if(artefato == CellType.CRUISER) {
				if (cruiserPart == 2) {
					result.put(1, "Você afundou um CRUISER!");
					cruiserPart = 0;
				} else {
					result.put(1, "Você acertou um CRUISER!");
					cruiserPart++;
				}
			}
			if(artefato == CellType.DESTROYER) {
				if (destroyerPart == 3) {
					result.put(1, "Você afundou um DESTROYER!");
					destroyerPart = 0;
				} else {
					result.put(1, "Você acertou um DESTROYER!");
					destroyerPart++;
				}
			}
			if(artefato == CellType.BATTLESHIP) {
				if (battleshipPart == 4) {
					result.put(1, "Você afundou um BATTLESHIP!");
					battleshipPart = 0;
				} else {
					result.put(1, "Você acertou um BATTLESHIP!");
					battleshipPart++;
				}
			}
		
			this.boardGame[y][x] = CellType.FIRE;
			acertou = 1;
			hitPoints--;
		} else {
			this.boardGame[y][x] = CellType.FAIL;
			errou = 1;
			result.put(0, "Atirou na água!");
		}		
				
		return target;
	}
	
	public CellType readInGame(int column, int line) {
		int y = translate(line);		
		int x = translate(column);
		
		CellType target = boardGame[y][x];
		
		return target;
	}
	
	public CellType readInTemplate(int column, int line) {
		int y = translate(line);		
		int x = translate(column);
		
		CellType target = boardTemplate[y][x];
		
		return target;
	}
	
	public static boolean hasShip() {
		return hitPoints > 0;
	}

	public int getTentativas() {
		return tentativas;
	}

	public HashMap<Integer, String> getResult() {
		return result;
	}

	public int getErrou() {
		return errou;
	}

	public int getAcertou() {
		return acertou;
	}
	
	@Override
	public String toString() {
		String result =   "BOARD<br>";
		result = result + "=====<br>";
		
		for(int i=0;i<10;i++) {
			result = result + Arrays.toString(boardGame[i]) + "<br>";
		}
		
		return result;
	}	
	
	private static int translate(int valor) {
		int ponto = valor - 1;
		
		if (ponto < 0 || ponto > 9)	{
			throw new IllegalArgumentException("Definição inválida: " + ponto);
		}
		return ponto;
	}
	

	public HashMap<Integer, String> createShip(int column, int line, String shipType, String orientation) {

		HashMap<Integer, String> result = new HashMap<Integer, String>();

		switch (shipType) {
		case "BOAT":
			result = createBoat(column, line);
			break;
		case "SUBMARINE":
			if (orientation.equals("H")) {
				result = createSubmarine_H(column, line);
			} else {
				result = createSubmarine_V(column, line);
			}
			break;
		case "CRUISER":
			if (orientation.equals("H")) {
				result = createCruiser_H(column, line);
			} else {
				result = createCruiser_V(column, line);
			}
			break;
		case "DESTROYER":
			if (orientation.equals("H")) {
				result = createDestroyer_H(column, line);
			} else {
				result = createDestroyer_V(column, line);
			}
			break;
		case "BATTLESHIP":
			if (orientation.equals("H")) {
				result = createBattleship_H(column, line);
			} else {
				result = createBattleship_V(column, line);
			}
			break;
		default:
			break;
		}

		return result;
	}

	public HashMap<Integer, String> createBoat(int column, int line) {

		HashMap<Integer, String> result = new HashMap<Integer, String>();

		if (boatCount < BOATS_LIMIT) {
			int x_line = translate(line);
			int y_coln = translate(column);

			if (line < 1 || line > 10) {
				throw new IllegalArgumentException("Definição de linha inválida: " + line + "!");
			}

			if (column > 10 || line > 10) {
				result.put(0, "Falha na criação: Não posso criar um BOAT nesta posição!");
				return result;
			}

			if (boardTemplate[x_line][y_coln] == CellType.WATER) {
				boardTemplate[x_line][y_coln] = CellType.BOAT;
				boatCount++;
				result.put(1, "Criação do BOAT realizada!");
				return result;
			} else {
				result.put(0, "Falha na criação: Não posso criar um BOAT nesta posição!");
				return result;
			}
		}
		result.put(0, "Falha na criação: limite de BOATS estourado (" + BOATS_LIMIT + ")!");
		
		return result;
	}

	public HashMap<Integer, String> createSubmarine_H(int column, int line) {

		HashMap<Integer, String> result = new HashMap<Integer, String>();

		if (submarineCount < SUBMARINES_LIMIT) {
			int x_line = translate(line);
			int y_coln = translate(column);

			if (line < 1 || line > 10) {
				throw new IllegalArgumentException("Definição de linha inválida: " + line + "!");
			}

			if (column > 9) {
				result.put(0, "Falha na criação: Não posso criar um SUBMARINE nesta posição!");
				return result;
			}

			if (boardTemplate[x_line][y_coln] == CellType.WATER && boardTemplate[x_line][y_coln + 1] == CellType.WATER) {
				boardTemplate[x_line][y_coln] 	  = CellType.SUBMARINE;
				boardTemplate[x_line][y_coln + 1] = CellType.SUBMARINE;
				submarineCount++;
				result.put(1, "Criação do SUBMARINE realizada!");
				return result;
			} else {
				result.put(0, "Falha na criação: Não posso criar um SUBMARINE nesta posição!");
				return result;
			}
		}
		result.put(0, "Falha na criação: limite de SUBMARINES estourado (" + SUBMARINES_LIMIT + ")!");
		
		return result;
	}

	public HashMap<Integer, String> createSubmarine_V(int column, int line) {

		HashMap<Integer, String> result = new HashMap<Integer, String>();

		if (submarineCount < SUBMARINES_LIMIT) {
			int x_line = translate(line);
			int y_coln = translate(column);

			if (line < 1 || line > 10) {
				throw new IllegalArgumentException("Definição de linha inválida: " + line + "!");
			}

			if (line > 9) {
				result.put(0, "Falha na criação: Não posso criar um SUBMARINE nesta posição!");
				return result;
			}

			if (boardTemplate[x_line][y_coln] == CellType.WATER && boardTemplate[x_line + 1][y_coln] == CellType.WATER) {
				boardTemplate[x_line][y_coln] 	  = CellType.SUBMARINE;
				boardTemplate[x_line + 1][y_coln] = CellType.SUBMARINE;
				submarineCount++;
				result.put(1, "Criação do SUBMARINE realizada!");
				return result;
			} else {
				result.put(0, "Falha na criação: Não posso criar um SUBMARINE nesta posição!");
				return result;
			}
		}
		result.put(0, "Falha na criação: limite de SUBMARINES estourado (" + SUBMARINES_LIMIT + ")!");
		
		return result;
	}

	public HashMap<Integer, String> createCruiser_H(int column, int line) {

		HashMap<Integer, String> result = new HashMap<Integer, String>();

		if (cruiserCount < CRUISERS_LIMIT) {
			int x_line = translate(line);
			int y_coln = translate(column);

			if (line < 1 || line > 10) {
				throw new IllegalArgumentException("Definição de linha inválida: " + line + "!");
			}

			if (column > 8) {
				result.put(0, "Falha na criação: Não posso criar um CRUISER nesta posição!");
				return result;
			}

			if (boardTemplate[x_line][y_coln] == CellType.WATER && boardTemplate[x_line][y_coln + 1] == CellType.WATER 
				 												&& boardTemplate[x_line][y_coln + 2] == CellType.WATER) {
				boardTemplate[x_line][y_coln] 	  = CellType.CRUISER;
				boardTemplate[x_line][y_coln + 1] = CellType.CRUISER;
				boardTemplate[x_line][y_coln + 2] = CellType.CRUISER;
				cruiserCount++;
				result.put(1, "Criação do CRUISER realizada!");
				return result;
			} else {
				result.put(0, "Falha na criação: Não posso criar um CRUISER nesta posição!");
				return result;
			}
		}
		result.put(0, "Falha na criação: limite de CRUISERS estourado (" + CRUISERS_LIMIT + ")!");
		
		return result;
	}

	public HashMap<Integer, String> createCruiser_V(int column, int line) {

		HashMap<Integer, String> result = new HashMap<Integer, String>();

		if (cruiserCount < CRUISERS_LIMIT) {
			int x_line = translate(line);
			int y_coln = translate(column);

			if (line < 1 || line > 10) {
				throw new IllegalArgumentException("Definição de linha inválida: " + line + "!");
			}

			if (line > 8) {
				result.put(0, "Falha na criação: Não posso criar um CRUISER nesta posição!");
				return result;
			}

			if (boardTemplate[x_line][y_coln] == CellType.WATER && boardTemplate[x_line + 1][y_coln] == CellType.WATER 
																&& boardTemplate[x_line + 2][y_coln] == CellType.WATER) {
				boardTemplate[x_line][y_coln] 	  = CellType.CRUISER;
				boardTemplate[x_line + 1][y_coln] = CellType.CRUISER;
				boardTemplate[x_line + 2][y_coln] = CellType.CRUISER;
				cruiserCount++;
				result.put(1, "Criação do CRUISER realizada!");
				return result;
			} else {
				result.put(0, "Falha na criação: Não posso criar um CRUISER nesta posição!");
				return result;
			}
		}
		result.put(0, "Falha na criação: limite de CRUISERS estourado (" + CRUISERS_LIMIT + ")!");
		
		return result;
	}
	public HashMap<Integer, String> createDestroyer_H(int column, int line) {

		HashMap<Integer, String> result = new HashMap<Integer, String>();

		if (destroyerCount < DESTROYERS_LIMIT) {
			int x_line = translate(line);
			int y_coln = translate(column);

			if (line < 1 || line > 10) {
				throw new IllegalArgumentException("Definição de linha inválida: " + line + "!");
			}
			if (column > 7) {
				result.put(0, "Falha na criação: Não posso criar um DESTROYER nesta posição!");
				return result;
			}

			if (boardTemplate[x_line][y_coln] == CellType.WATER && boardTemplate[x_line][y_coln + 1] == CellType.WATER
					 											&& boardTemplate[x_line][y_coln + 2] == CellType.WATER 
					 											&& boardTemplate[x_line][y_coln + 3] == CellType.WATER) {
				boardTemplate[x_line][y_coln] 	  = CellType.DESTROYER;
				boardTemplate[x_line][y_coln + 1] = CellType.DESTROYER;
				boardTemplate[x_line][y_coln + 2] = CellType.DESTROYER;
				boardTemplate[x_line][y_coln + 3] = CellType.DESTROYER;
				destroyerCount++;
				result.put(1, "Criação do DESTROYER realizada!");
				return result;
			} else {
				result.put(0, "Falha na criação: Não posso criar um DESTROYER nesta posição!");
				return result;
			}
		}
		result.put(0, "Falha na criação: limite de DESTROYERS estourado (" + DESTROYERS_LIMIT + ")!");

		return result;
	}

	public HashMap<Integer, String> createDestroyer_V(int column, int line) {

		HashMap<Integer, String> result = new HashMap<Integer, String>();

		if (destroyerCount < DESTROYERS_LIMIT) {
			int x_line = translate(line);
			int y_coln = translate(column);

			if (line < 1 || line > 10) {
				throw new IllegalArgumentException("Definição de linha inválida: " + line + "!");
			}

			if (line > 7) {
				result.put(0, "Falha na criação: Não posso criar um DESTROYER nesta posição!");
				return result;
			}

			if (boardTemplate[x_line][y_coln] == CellType.WATER && boardTemplate[x_line + 1][y_coln] == CellType.WATER
					 											&& boardTemplate[x_line + 2][y_coln] == CellType.WATER 
					 											&& boardTemplate[x_line + 3][y_coln] == CellType.WATER) {
				boardTemplate[x_line][y_coln] 	  = CellType.DESTROYER;
				boardTemplate[x_line + 1][y_coln] = CellType.DESTROYER;
				boardTemplate[x_line + 2][y_coln] = CellType.DESTROYER;
				boardTemplate[x_line + 3][y_coln] = CellType.DESTROYER;
				destroyerCount++;
				result.put(1, "Criação do DESTROYER realizada!");
				return result;
			} else {
				result.put(0, "Falha na criação: Não posso criar um DESTROYER nesta posição!");
				return result;
			}
		}
		result.put(0, "Falha na criação: limite de DESTROYERS estourado (" + DESTROYERS_LIMIT + ")!");

		return result;
	}

	public HashMap<Integer, String> createBattleship_H(int column, int line) {

		HashMap<Integer, String> result = new HashMap<Integer, String>();

		if (battleshipCount < BATTLESHIPS_LIMIT) {
			int x_line = translate(line);
			int y_coln = translate(column);

			if (line < 1 || line > 10) {
				throw new IllegalArgumentException("Definição de linha inválida: " + line + "!");
			}

			if (column > 6) {
				result.put(0, "Falha na criação: Não posso criar um BATTLESHIP nesta posição!");
				return result;
			}

			if (boardTemplate[x_line][y_coln] == CellType.WATER && boardTemplate[x_line][y_coln + 1] == CellType.WATER
																&& boardTemplate[x_line][y_coln + 2] == CellType.WATER
																&& boardTemplate[x_line][y_coln + 3] == CellType.WATER
																&& boardTemplate[x_line][y_coln + 4] == CellType.WATER) {
				boardTemplate[x_line][y_coln] = CellType.BATTLESHIP;
				boardTemplate[x_line][y_coln + 1] = CellType.BATTLESHIP;
				boardTemplate[x_line][y_coln + 2] = CellType.BATTLESHIP;
				boardTemplate[x_line][y_coln + 3] = CellType.BATTLESHIP;
				boardTemplate[x_line][y_coln + 4] = CellType.BATTLESHIP;
				battleshipCount++;
				result.put(1, "Criação do BATTLESHIP realizada!");
				return result;
			} else {
				result.put(0, "Falha na criação: Não posso criar um BATTLESHIP nesta posição!");
				return result;
			}
		}
		result.put(0, "Falha na criação: limite de BATTLESHIP estourado (" + BATTLESHIPS_LIMIT + ")!");
		
		return result;
	}

	public HashMap<Integer, String> createBattleship_V(int column, int line) {

		HashMap<Integer, String> result = new HashMap<Integer, String>();

		if (battleshipCount < BATTLESHIPS_LIMIT) {
			int x_line = translate(line);
			int y_coln = translate(column);

			if (line < 1 || line > 10) {
				throw new IllegalArgumentException("Definição de linha inválida: " + line + "!");
			}

			if (line > 6) {
				result.put(0, "Falha na criação: Não posso criar um BATTLESHIP nesta posição!");
				return result;
			}

			if (boardTemplate[x_line][y_coln] == CellType.WATER && boardTemplate[x_line + 1][y_coln] == CellType.WATER
																&& boardTemplate[x_line + 2][y_coln] == CellType.WATER
																&& boardTemplate[x_line + 3][y_coln] == CellType.WATER
																&& boardTemplate[x_line + 4][y_coln] == CellType.WATER) {
				boardTemplate[x_line][y_coln] = CellType.BATTLESHIP;
				boardTemplate[x_line + 1][y_coln] = CellType.BATTLESHIP;
				boardTemplate[x_line + 2][y_coln] = CellType.BATTLESHIP;
				boardTemplate[x_line + 3][y_coln] = CellType.BATTLESHIP;
				boardTemplate[x_line + 4][y_coln] = CellType.BATTLESHIP;
				battleshipCount++;
				result.put(1, "Criação do BATTLESHIP realizada!");
				return result;
			} else {
				result.put(0, "Falha na criação: Não posso criar um BATTLESHIP nesta posição!");
				return result;
			}
		}
		result.put(0, "Falha na criação: limite de BATTLESHIP estourado (" + BATTLESHIPS_LIMIT + ")!");
		
		return result;
	}
}