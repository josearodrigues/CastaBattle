# CastaBattle - Batalha Naval Web em Java & Spring MVC

Trabalho da disciplina Programação de Servidores WEB (PSW) do período 2017.1, ministrada pelo Prof. Eduardo Bezerra Castaneda no Centro Federal de Educação Tecnológica Celso Suckow da Fonseca (CEFET/RJ) - Unidade Maracanã.

    Curso: Curso Superior de Tecnologia em Sistemas para Internet (CST-SI)
    Discente: José Américo Rodrigues
    Período: 2017.1
    Docente: Prof. Castaneda

---

## Visão Geral

O **CastaBattle** é uma aplicação web interativa desenvolvida em arquitetura Cliente-Servidor em Java que recria o clássico jogo de estratégia de **Batalha Naval (*Battleship*)**. Criado como um projeto lúdico para consolidação dos conceitos de desenvolvimento web corporativo em Java, o sistema homenageia o professor da disciplina através do nome e do pacote da aplicação (*CastaWare / CastaBattle*).

O jogo implementa controle de sessão e turnos via Spring Web MVC, interface dinâmica com JSP/JSTL, tabuleiro do jogador e da inteligência da máquina (CPU), validação de regras navais e integração de efeitos sonoros multimídia para imersão no combate naval.

---

## Regras e Mecânicas de Jogo

### 1. Tabuleiro e Posicionamento
* **Dimensão**: Grade matricial de 10x10 células.
* **Orientação**: Cada embarcação pode ser ancorada na orientação Horizontal (`H`) ou Vertical (`V`).
* **Validação de Frota**: O sistema impede sobreposição de navios e extrapolação das bordas do tabuleiro.

### 2. Composição da Frota
A esquadra de batalha é composta por 13 embarcações com diferentes tamanhos e limites:
* **Bote (`BOAT`)**: 5 unidades (1 célula cada).
* **Submarino (`SUBMARINE`)**: 3 unidades (2 células cada).
* **Cruzador (`CRUISER`)**: 2 unidades (3 células cada).
* **Contratorpedeiro (`DESTROYER`)**: 2 unidades (4 células cada).
* **Encouraçado (`BATTLESHIP`)**: 1 unidade (5 células).

### 3. Fases da Partida
1. **Montagem da Frota**: O usuário seleciona a coordenada inicial, tipo de navio e orientação para compor seu tabuleiro (`userBoard`).
2. **Combate Naval**: Ao iniciar o jogo, o usuário alveja células do tabuleiro inimigo (`cpuBoard`).
3. **Estados de Célula**:
   * `HIDDEN`: Célula oculta e não explorada.
   * `FIRE`: Disparo certeiro em embarcação inimiga.
   * `FAIL`: Disparo n'água (alvo errado).
   * `WATER`: Mar aberto.
4. **Estatísticas em Tempo Real**: Exibição de tentativas totais, acertos e erros de cada lado.
5. **Efeitos Multimídia**: Sons integrados de disparo de canhão (`fire.m4a`), impacto na água (`water.mp3`) e áudio ambiente marinho (`sea.mp3`).

---

## Arquitetura e Módulos do Sistema

A aplicação adota o padrão MVC (Model-View-Controller) gerenciado pelo Spring Framework:

* **Controlador (`com.castaware.castabattle.control`)**:
  * [`GameController.java`](file:///home/joserodrigues/GitHub/CastaBattle/src/main/java/com/castaware/castabattle/control/GameController.java): Gerencia o ciclo de jogo mapeando rotas REST/MVC:
    * `/game/start`: Inicializa os tabuleiros do usuário e CPU.
    * `/game/createShip`: Adiciona uma nova embarcação à frota do usuário.
    * `/game/playGame`: Inicia a fase ativa de combate.
    * `/game/fire`: Executa o disparo do jogador, calcula a resposta e verifica condições de vitória/derrota.
    * `/game/reset`: Reinicia os tabuleiros para uma nova partida.
* **Domínio e Regras de Negócio (`com.castaware.castabattle.domain`)**:
  * [`Board.java`](file:///home/joserodrigues/GitHub/CastaBattle/src/main/java/com/castaware/castabattle/domain/Board.java): Representação do tabuleiro de 10x10, lógica de posicionamento, detecção de colisões e pontuação de dano.
  * [`Game.java`](file:///home/joserodrigues/GitHub/CastaBattle/src/main/java/com/castaware/castabattle/domain/Game.java): Fábrica e repositório de estado dos tabuleiros do usuário e CPU.
  * [`CellType.java`](file:///home/joserodrigues/GitHub/CastaBattle/src/main/java/com/castaware/castabattle/domain/CellType.java): Enumeração que define os tipos de navios e status de combate de cada célula.
* **Camada de Visão (Web)**:
  * [`WebContent/castabattle.jsp`](file:///home/joserodrigues/GitHub/CastaBattle/WebContent/castabattle.jsp): Interface principal de jogo com estilização marítima e controles.
  * `WebContent/resources/css/naval.css`: Estilização visual temática em tons navais.
  * `WebContent/resources/audios/`: Efeitos sonoros da batalha.
  * `WebContent/WEB-INF/web.xml` e `springweb-servlet.xml`: Descritores de implantação e configuração do DispatcherServlet do Spring.

---

## Tecnologias Utilizadas

* **Linguagem**: Java 8 (JDK 1.8)
* **Framework Web**: Spring Framework (Spring Web MVC)
* **Especificações Web**: Java Servlet API 3.1, JavaServer Pages (JSP 2.3) e JSTL 1.2
* **Gerenciador de Dependências e Build**: Apache Maven
* **Servidor de Aplicação / Container Servlet**: Apache Tomcat 8+ (ou Jetty)
* **Testes**: JUnit

---

## Instruções de Build e Execução

### 1. Pré-requisitos
* Java Development Kit (JDK 8 ou superior instalado e configurado no `JAVA_HOME`).
* Apache Maven instalado (`mvn -version`).
* Apache Tomcat 8 ou superior instalado.

### 2. Compilar e Gerar o Pacote WAR
Na raiz da pasta do projeto:
```bash
cd /home/joserodrigues/GitHub/CastaBattle
mvn clean package
```
O Maven gerará o arquivo empacotado em:
`target/castabattle.war`

### 3. Deploy no Apache Tomcat
Copie o arquivo `castabattle.war` gerado para o diretório `webapps/` da sua instalação do Tomcat:
```bash
cp target/castabattle.war /caminho/para/tomcat/webapps/
```
Inicie o Tomcat:
```bash
/caminho/para/tomcat/bin/startup.sh   # Linux / macOS
# ou
/caminho/para/tomcat/bin/startup.bat  # Windows
```

### 4. Acessar a Aplicação
Abra o navegador no endereço:
[http://localhost:8080/castabattle/spring/game/start](http://localhost:8080/castabattle/spring/game/start)