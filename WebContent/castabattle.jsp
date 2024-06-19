<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.castaware.castabattle.domain.Board" %>
<%@page import="com.castaware.castabattle.domain.CellType" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Nunito" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/naval.css" />
<title>Batalha Naval v0.1-alpha</title>
<script>
 	function PlaySound(arquivo) {
 	    var path = "${pageContext.request.contextPath}/resources/audios/";
 	    var snd = new Audio(path + arquivo);
 	    snd.play();
 	}
</script>
<audio>
  <source src="${pageContext.request.contextPath}/resources/audios/sea.mp3" type="audio/mpeg">
  Your browser does not support the audio element (autoplay loop).
</audio>
</head>
<body bgcolor="navy">
	<div class="content">
	<table cellspacing="0" cellpadding="0" border="0">
		<tr>
			<td class="label" colspan="4">
				<h1>Batalha Naval</h1>
     			<div class="message">
        			<c:forEach var="message" items="${result}">
        	  			<c:choose>
        	    			<c:when test="${message.key == 1}">	    
        	       				<div class="success-message">
        	       					<c:out value="${message.value}"/>
        	       				</div>   
        	    			</c:when>
        	    		<c:otherwise>
        	       			<div class="error-message">
        	       				<c:out value="${message.value}"/>	
        	       			</div>
                		</c:otherwise>
        	  			</c:choose>  
            		</c:forEach>
     			</div>
			</td>
		</tr>
		<tr>
			<td>
				<table class="user">
			    	<tr>
						<td class="label" colspan="11">Meu Tabuleiro</td>
			    	</tr>
			    	<tr>
						<td class="label"></td>
						<td class="label">A</td>
						<td class="label">B</td>
						<td class="label">C</td>
						<td class="label">D</td>
						<td class="label">E</td>
						<td class="label">F</td>
						<td class="label">G</td>
						<td class="label">H</td>
						<td class="label">I</td>
						<td class="label">J</td>
	    			</tr>
					<c:forEach var="i" begin="1" end="10">
	    				<tr>
      						<td class="label">${i}</td>
      						<c:forEach var="j" begin="1" end="10">
      							<c:choose>
      								<c:when test="${inicio == 0}">
      									<form action="${pageContext.request.contextPath}/spring/game/createShip" method="get">
		      							<c:choose>
			      							<c:when test="${userBoard.boardTemplate[i-1][j-1] == 'BOAT'}">
			      	 							<td class="cell ship"></td>
			      							</c:when>
			      							<c:when test="${userBoard.boardTemplate[i-1][j-1] == 'SUBMARINE'}">
			      	  							<td class="cell ship"></td>
			      							</c:when>
			      							<c:when test="${userBoard.boardTemplate[i-1][j-1] == 'CRUISER'}">
			      	  							<td class="cell ship"></td>
			      							</c:when>
			      							<c:when test="${userBoard.boardTemplate[i-1][j-1] == 'DESTROYER'}">
			      	  							<td class="cell ship"></td>
			      							</c:when>
			      							<c:when test="${userBoard.boardTemplate[i-1][j-1] == 'BATTLESHIP'}">
			      	  							<td class="cell ship"></td>
			      							</c:when>
			      							<c:otherwise>
				      							<td class="cell">
			      									<button type="submit" name="cell" value="${i}_${j}"></button>
			      								</td>
			      							</c:otherwise>
		      							</c:choose>
									</c:when>
				        	    	<c:otherwise>
			      						<c:choose>
				      						<c:when test="${userBoard.boardTemplate[i-1][j-1] == 'WATER'}">
				      							<td class="cell fail"></td>
				      						</c:when>
				      						<c:when test="${userBoard.boardTemplate[i-1][j-1] == 'FIRE'}">
				      							<td class="cell fire"></td>
				      						</c:when>
				      						<c:otherwise>
					      						<td class="cell">
				      								<button type="submit" name="cell" value="${i}_${j}"></button>
				      							</td>
				      						</c:otherwise>
			      						</c:choose>   		
				        	    	</c:otherwise>
								</c:choose>
      						</c:forEach>
      					</tr> 
					</c:forEach>
				</table>
			</td>
			<td>
				<c:choose>
     				<c:when test="${inicio == 0}">
						<table class="cpu">
					    	<tr>
								<td class="label" colspan="5">Completar Meu Tabuleiro:</td>
					    	</tr>
					    	<tr>
								<td class="label" colspan="5">Artefatos</td>
					    	</tr>
					    	<tr>
					    		<td>
							         <div class="selection">
										<select name="shipType">
											<option value="BOAT" ${boat_selected}>Boat</option>
											<option value="SUBMARINE" ${submarine_selected}>Submarine</option>
											<option value="CRUISER" ${cruiser_selected}>Cruiser</option>
											<option value="DESTROYER" ${destroyer_selected}>Destroyer</option>
											<option value="BATTLESHIP" ${battleship_selected}>BattleShip</option>	  
										</select>
									</div>
								</td>
					    	<tr>
								<td class="label" colspan="5">Orientação</td>
					    	</tr>
					    		<td>
							         <div class="selection">
									    <select name="orientation">
											<option value="H" ${h_selected}>Horizontal</option>
											<option value="V" ${v_selected}>Vertical</option>	  
										</select>   
									</div><br>
								</td>
							</tr>
					    	<tr>
								<td class="label" colspan="5">Operações:</td>
					    	</tr>
					    	<tr>
					    		<td>
									<div class="link-button">
								    	<a href="${pageContext.request.contextPath}/spring/game/reset" class="button" align="center">Limpar Tabuleiros</a>
								    </div><br><br>
								    <div class="link-button">
								    	<a href="${pageContext.request.contextPath}/spring/game/playGame" class="button" align="center">Jogar</a>
								    </div>
			 					</td>
					    	</tr>
						</table>
					</c:when>
				</c:choose>
			</td>
				</form>
			<td>
				<form action="${pageContext.request.contextPath}/spring/game/fire" method="get">
				<table class="cpu">
			    	<tr>
						<td class="label" colspan="11">Tabuleiro CPU</td>
			    	</tr>
			    	<tr>
						<td class="label"></td>
						<td class="label">A</td>
						<td class="label">B</td>
						<td class="label">C</td>
						<td class="label">D</td>
						<td class="label">E</td>
						<td class="label">F</td>
						<td class="label">G</td>
						<td class="label">H</td>
						<td class="label">I</td>
						<td class="label">J</td>
			    	</tr>
					<c:forEach var="i" begin="1" end="10">
			    		<tr>
		      				<td class="label">${i}</td>
		      				<c:forEach var="j" begin="1" end="10">
			      				<c:choose>
				      				<c:when test="${cpuBoard.boardGame[i-1][j-1] == 'FAIL'}">
				      					<td class="cell fail"></td>
				      				</c:when>
				      				<c:when test="${cpuBoard.boardGame[i-1][j-1] == 'FIRE'}">
				      					<td class="cell fire"></td>
				      				</c:when>
				      				<c:otherwise>
					      				<td class="cell">
										<c:choose>
											<c:when test="${inicio == 1}">
				      							<button type="submit" name="cell" value="${i}_${j}"></button>
											</c:when>
											<c:otherwise>
				      							<button type="button" name="cell" value="${i}_${j}"></button>
											</c:otherwise>
										</c:choose>
				      					</td>
				      				</c:otherwise>
			      				</c:choose>   		
		      				</c:forEach>
		      			</tr> 
					</c:forEach>
				</table>
			</td>
			<td>
				<table class="user" cellspacing="0" cellpadding="0" margin="0 0 0 0">
			    	<tr>
						<td class="label" colspan="5">Artefatos:</td>
			    	</tr>
			    	<tr>
						<td class="label" colspan="5"></td>
			    	</tr>
					<tr>
						<td class="label cell fire"></td>
						<td class="label"></td>
						<td class="label"></td>
						<td class="label"></td>
						<td class="label"></td>
					</tr>
			    	<tr>
						<td class="label" colspan="5">BOAT x 5</td>
			    	</tr>
					<tr>
						<td class="label cell fire"></td>
						<td class="label cell fire"></td>
						<td class="label"></td>
						<td class="label"></td>
						<td class="label"></td>
					</tr>
			    	<tr>
						<td class="label" colspan="5">SUBMARINE x 3</td>
			    	</tr>
					<tr>
						<td class="label cell fire"></td>
						<td class="label cell fire"></td>
						<td class="label cell fire"></td>
						<td class="label"></td>
						<td class="label"></td>
					</tr>
			    	<tr>
						<td class="label" colspan="5">CRUISER x 2</td>
			    	</tr>
					<tr>
						<td class="label cell fire"></td>
						<td class="label cell fire"></td>
						<td class="label cell fire"></td>
						<td class="label cell fire"></td>
						<td class="label"></td>
					</tr>
			    	<tr>
						<td class="label" colspan="5">DESTROYER x 2</td>
			    	</tr>
					<tr>
						<td class="label cell fire"></td>
						<td class="label cell fire"></td>
						<td class="label cell fire"></td>
						<td class="label cell fire"></td>
						<td class="label cell fire"></td>
					</tr>
			    	<tr>
						<td class="label" colspan="5">BATTLESHIP x 1</td>
			    	</tr>
			    	<tr>
						<td class="label" colspan="5">Pontuações:</td>
			    	</tr>
			    	<tr>
						<td class="label" colspan="5">Tentativas: ${userBoard.tentativas}</td>
			    	</tr>
				</table>			
			</td>
		</tr>
	</table>
	</div>
	<c:choose>
		<c:when test="${userAcertou == 1}">
			<audio autoplay>
 				 <source src="${pageContext.request.contextPath}/resources/audios/fire.m4a" type="audio/mpeg">
 				 Seu navegador não suporta o elemento audio.
			</audio>
		</c:when>
		<c:when test="${cpuAcertou == 1}">
			<audio autoplay>
  				<source src="${pageContext.request.contextPath}/resources/audios/fire.m4a" type="audio/mpeg">
 				Seu navegador não suporta o elemento audio.
			</audio>
		</c:when>
		<c:when test="${userErrou == 1}">
			<audio autoplay>
 				 <source src="${pageContext.request.contextPath}/resources/audios/water.mp3" type="audio/mpeg">
 				 Seu navegador não suporta o elemento audio.
			</audio>
		</c:when>
		<c:when test="${cpuErrou == 1}">
			<audio autoplay>
  				<source src="${pageContext.request.contextPath}/resources/audios/water.mp3" type="audio/mpeg">
 				Seu navegador não suporta o elemento audio.
			</audio>
		</c:when>
	</c:choose>
</body>
</html>