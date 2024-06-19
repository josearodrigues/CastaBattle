package com.castaware.castabattle.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.castaware.castabattle.domain.CellType.*;

public class BoardTest {
	Board board;
	
	@Before
	public void setUpBoard() {					
		CellType[][] template = new CellType[][] {
			{WATER, WATER,   WATER,   WATER,   WATER,   WATER, WATER, WATER, WATER, WATER},   
			{WATER, WATER,   WATER,   WATER,   WATER,   WATER, WATER, WATER, WATER, WATER},	
			{WATER, WATER,   BOAT,    WATER,   WATER,   WATER, WATER, WATER, WATER, WATER},		                                      
			{WATER, WATER,   WATER,   WATER,   WATER,   WATER, WATER, WATER, WATER, WATER},		
			{WATER, WATER,   WATER,   WATER,   WATER,   WATER, WATER, WATER, WATER, WATER},		
			{WATER, WATER,   WATER,   WATER,   WATER,   WATER, BOAT , WATER, WATER, WATER},		
			{WATER, WATER,   WATER,   WATER,   WATER,   WATER, WATER, WATER, WATER, WATER},		
			{WATER, CRUISER, CRUISER, CRUISER, CRUISER, WATER, WATER, WATER, WATER, WATER},		
			{WATER, WATER,   WATER,   WATER,   WATER,   WATER, WATER, WATER, WATER, WATER},		
			{WATER, WATER,   WATER,   WATER,   WATER,   WATER, WATER, WATER, WATER, WATER}
		};		
														
		board = new Board();
		board.setBoardTemplate(template);
	}
	
	@Test
	public void testWaterFire() {
		Assert.assertEquals(board.readInGame(1, 1), CellType.HIDDEN);
		CellType target = board.fire(1, 1);
		Assert.assertEquals(target, CellType.WATER);
		Assert.assertEquals(board.readInGame(1, 1), CellType.WATER);
	}
	
	@Test 
	public void testShipFire() {
		Assert.assertEquals(board.readInGame(2, 8), CellType.HIDDEN);
		CellType target = board.fire(2, 8);
		Assert.assertEquals(target, CellType.CRUISER);
		Assert.assertEquals(board.readInGame(2, 8), CellType.FIRE);
	}
}