package jogodavelha;

/**
 * Esta classe guarda a situação da partida numa matriz bidimensional de 3 posições.
 * Fornece recursos para a execução das jogadas e registra a situação final.
 */

public class Tabuleiro {

	// matrix para registro das posições
	private static char[][] posicao;
	// coordenadas da jogada mais recente
	private static int[] jogada = new int[2];
	// sinal usado pelo vencedor da partida
	private char vencedor = ' ';
	// quantidade de jogadas feitas
	private static int qtdjogadas = 0;
	
	/**
	 * Método construtor.
	 * Inicia um tabuleiro vazio e desenha na tela.
	 */
	public Tabuleiro() {
		posicao = new char[][] {{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};
		desenhaTabuleiro();
	}
	
	/**
	 * @return		uma matriz 3x3 representando o tabuleiro.
	 */
	public static char[][] getPosicao() {
		return posicao;
	}
	
	/**
	 * @return		as coordenadas (linha, coluna) da jogada mais recente.
	 */
	public static int[] getJogada() {
		return jogada;
	}
	
	/**
	 * @return		a quantidade de jogadas feitas.
	 */
	public static int getQtdjogadas() {
		return qtdjogadas;
	}

	/**
	 * @return		o sinal usado pelo vencedor da partida.
	 */
	public char getVencedor() {
		return vencedor;
	}

	/**
	 * Desenha o tabuleiro na tela, conforme a situação atual da partida.
	 */
	private void desenhaTabuleiro() {
		String linha =		"\t ---|---|---";
		System.out.println("\n\t  1   2   3");
		System.out.println("\t1 " + posicao[0][0] + " | " + posicao[0][1] + " | " + posicao[0][2]);
		System.out.println(linha);
		System.out.println("\t2 " + posicao[1][0] + " | " + posicao[1][1] + " | " + posicao[1][2]);
		System.out.println(linha);
		System.out.println("\t3 " + posicao[2][0] + " | " + posicao[2][1] + " | " + posicao[2][2]);
	}
	
	/**
	 * Tenta registrar a jogada na posição solicitada.
	 * 
	 * @param pos		a posição (linha, coluna) a ser marcada.
	 * @param sinal		o sinal do jogador ('X' | 'O').
	 * @param humano	o tipo do jogador:
	 * 					true = humano,
	 * 					false = computador.
	 * @return			true = posição inválida,
	 * 					false = jogada registrada.
	 */
	public Boolean fazJogada(int[] pos, char sinal, Boolean humano) {
		int linha = pos[0];
		int coluna = pos[1];
		
		// testa se a posição está vazia
		if(posicao[linha][coluna] != ' ') {
			if(humano)
				System.out.println("\nEsta posição já está ocupada.\n");
			return true;
		}
		
		// registra a jogada
		posicao[linha][coluna] = sinal;
		jogada[0] = linha;
		jogada[1] = coluna;
		
		// incrementa o contador
		qtdjogadas ++;
		
		// verifica se a jogada vence a partida
		if(vencedor())
			vencedor = sinal;
		
		// mostra a jogada na tela
		System.out.println("\nJogada: " + qtdjogadas + "\tJogador " + sinal + ":");
		desenhaTabuleiro();
		
		// informa que a jogada foi registrada
		return false;
	}
	
	/**
	 * Verifica se um jogador venceu a partida, analisando os sinais existentes
	 * nas linhas, colunas e diagonais.
	 * 
	 * @return		true = existe vencedor,
	 * 				false = não existe vencedor.
	 */
	private Boolean vencedor() {
		
		for(int i = 0; i<3; i++) {
			// verifica linhas
			if(posicao[i][0] != ' ' && posicao[i][0] == posicao[i][1] && posicao[i][1] == posicao[i][2])
				return true;
			// verifica colunas
			if(posicao[0][i] != ' ' && posicao[0][i] == posicao[1][i] && posicao[1][i] == posicao[2][i])
				return true;
		}
		
		// verifica diagonais
		if(posicao[0][0] != ' ' && posicao[0][0] == posicao[1][1] && posicao[1][1] == posicao[2][2])
			return true;
		if(posicao[0][2] != ' ' && posicao[0][2] == posicao[1][1] && posicao[1][1] == posicao[2][0])
			return true;
		
		// segue o jogo
		return false;
	}
	
	/**
	 * Verifica se houve empate.
	 * Nessa situação foram efetuadas 9 jogadas e não houve vencedor.
	 * 
	 * @return		true = partida empatada.
	 */
	public Boolean empate() {
		return qtdjogadas == 9? true : false;
	}
	
	/**
	 * Testa as posições do tabuleiro em sequência, até encontrar uma posição vazia.
	 * 
	 * @return		as coordenadas (linha, coluna)
	 * 				da primeira posição livre encontrada.
	 */
	public static int[] proxPosLivre() {
		int[] pos = new int[2];
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(posicao[i][j] == ' ') {
					pos[0] = i;
					pos[1] = j;
					return pos;
				}
			}
		}
		return pos;
	}
	
	/**
	 * Procura uma única posição livre numa linha ou coluna específica.
	 * Retorna a posição livre caso as outras tenham o mesmo sinal.
	 * 
	 * @param lincol	o número da linha ou coluna (0... 2)
	 * @param tipo		0 = linha, 1 = coluna.
	 * @return			o número da posição livre (0... 2)
	 * 					ou -1 se não existir.
	 */
	public static int posEspaco(int lincol, int tipo) {
		// converte a linha ou coluna num vetor
		char[] pos = new char[3];
		for(int i=0; i<3; i++) {
			if(tipo == 0)
				pos[i] = posicao[lincol][i];
			else
				pos[i] = posicao[i][lincol];
		}
		
		int poslivre = -1;
		int cont = 0;
		
		for(int i=0; i<3; i++) {
			if(pos[i] == ' ') {
				poslivre = i;
				cont++;
			}
		}
		
		// verifica se as outras duas posições possuem sinais iguais
		switch(poslivre) {
		case 0:
			if(cont == 1 && pos[1] == pos[2])
				return poslivre;
			break;
		case 1:
			if(cont == 1 && pos[0] == pos[2])
				return poslivre;
			break;
		case 2:
			if(cont == 1 && pos[0] == pos[1])
				return poslivre;
			break;
		}
		return -1;
	}
}
