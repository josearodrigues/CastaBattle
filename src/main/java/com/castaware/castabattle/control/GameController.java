package com.castaware.castabattle.control;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.castaware.castabattle.domain.Board;
import com.castaware.castabattle.domain.CellType;
import com.castaware.castabattle.domain.Game;

@Controller
@RequestMapping("/game") // .../castabattle/spring/game
public class GameController {
	private int inicio;

	@RequestMapping("/start") // .../castabattle/spring/game/start
	public ModelAndView start() {	
		Board userBoard = Game.getUserBoard();
		Board cpuBoard  = Game.getCPUBoard();
		inicio = 0;
		
		ModelAndView mv = new ModelAndView("/castabattle.jsp");
		mv.addObject("userBoard", userBoard);
		mv.addObject("cpuBoard", cpuBoard);
		mv.addObject("inicio", inicio);

		return mv;
	}	
	
	@RequestMapping("/reset") // .../castabattle/spring/game/reset
	public ModelAndView reset() {
		return start();
	}
	
	@RequestMapping(value="/createShip",method=RequestMethod.GET) // .../castabattle/spring/game/createShip
	public ModelAndView createShip(@RequestParam String cell, @RequestParam String shipType,
							 								  @RequestParam String orientation) {
        Board userBoard = Game.getUserBoard();
		Board cpuBoard  = Game.getCPUBoard();

		String[] coordenada = cell.split("_");
		int line   = Integer.parseInt(coordenada[0]);
		int column = Integer.parseInt(coordenada[1]);
		
		String h_selected 			= "";
		String v_selected 			= "";
		String boat_selected 		= "";
		String submarine_selected 	= "";
		String cruiser_selected 	= "";
		String destroyer_selected 	= "";
		String battleship_selected 	= "";
		
		if(orientation.equals("H")) {
			h_selected = "selected";
		} else {
			v_selected = "selected";
		}
		
		switch(shipType) {
			case "BOAT":       boat_selected 		= "selected";
						       break;
			case "SUBMARINE":  submarine_selected 	= "selected";
            				   break;
			case "CRUISER":    cruiser_selected 	= "selected";
	                           break;
			case "DESTROYER":  destroyer_selected 	= "selected";
            				   break;
			case "BATTLESHIP": battleship_selected 	= "selected";
	                           break;
	        default: break;
		}
        
        HashMap<Integer, String> result = userBoard.createShip(column, line, shipType, orientation);
        
        ModelAndView mv = new ModelAndView("/castabattle.jsp");
        mv.addObject("result", result);
        mv.addObject("h_selected", h_selected);
        mv.addObject("v_selected", v_selected);
        mv.addObject("boat_selected", 		boat_selected);
        mv.addObject("submarine_selected", 	submarine_selected);
        mv.addObject("cruiser_selected", 	cruiser_selected);
        mv.addObject("destroyer_selected", 	destroyer_selected);
        mv.addObject("battleship_selected", battleship_selected);
		mv.addObject("userBoard", userBoard);
		mv.addObject("cpuBoard",  cpuBoard);
		mv.addObject("inicio", inicio);
        
 		return mv;
	}

	@RequestMapping(value="/playGame", method=RequestMethod.GET) // .../castabattle/spring/game/playGame
	public ModelAndView playGame() {
		Board userBoard = Game.getUserBoard();
		Board cpuBoard  = Game.getCPUBoard();
		inicio = 1;
		
		ModelAndView mv = new ModelAndView("/castabattle.jsp");
		mv.addObject("userBoard", userBoard);
		mv.addObject("cpuBoard",  cpuBoard);
		mv.addObject("inicio", inicio);
		
		return mv;
	}

	@RequestMapping("/fire") // .../castabattle/spring/game/fire
	public ModelAndView fire(@RequestParam String cell) {
		Board userBoard = Game.getUserBoard();
		Board cpuBoard  = Game.getCPUBoard();

		String[] coordenada = cell.split("_");
		int line   = Integer.parseInt(coordenada[0]);
		int column = Integer.parseInt(coordenada[1]);
		
		CellType celltype = cpuBoard.fire(column, line);
		
		if (Board.hasShip())
		{
			ModelAndView mv = new ModelAndView("/castabattle.jsp");
			mv.addObject("userBoard", userBoard);
			mv.addObject("cpuBoard",  cpuBoard);
			mv.addObject("celltype",  celltype);
			mv.addObject("userAcertou", userBoard.getAcertou());
			mv.addObject("cpuAcertou",  cpuBoard.getAcertou());
			mv.addObject("userErrou",   userBoard.getErrou());
			mv.addObject("cpuErrou",    cpuBoard.getErrou());
			mv.addObject("userTentativas", userBoard.getTentativas());
			mv.addObject("cpuTentativas",  cpuBoard.getTentativas());
			mv.addObject("result", cpuBoard.getResult());
			mv.addObject("inicio", inicio);

			return mv;
		}
		else
		{
			ModelAndView mv = new ModelAndView("/endgame.jsp");

			return mv;
		}
	}
}	