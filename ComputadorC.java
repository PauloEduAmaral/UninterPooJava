package jogodavelha;

/**
 * Classe derivada que constrói um jogador do tipo Computador C.
 *
 * Este oponente é utilizado no nível Difícil do jogo.
 * Ele escolhe a posição analisando a situação atual
 * do tabuleiro, verificando as linhas, colunas e diagonais.
 */
public class ComputadorC extends Jogador {
	
	// comtém uma cópia da situação do tabuleiro
	private char[][] postab = new char[3][3];
	// sinal usado pelo adversário
	private char sinaladv = 'X';

	/**
	 * Método construtor.
	 * 
	 * @param sinal		o sinal que será usado pelo
	 * 					jogador ('X' | 'O').
	 */
	public ComputadorC(char sinal) {
		super(sinal);
		// guarda o sinal do adversário
		if(this.sinal == 'X')
			sinaladv = 'O';
	}
	
	/**
	 * Seleciona a posição a ser jogada.
	 * 
	 *<return		as coordenadas (linha, coluna)
	 * 				da posição escolhida.
	 */
	@Override
	public int[] posicao() {
		// recupera a situação atual do tabuleiro.
		postab = Tabuleiro.getPosicao();

		// primeira rodada
		if(Tabuleiro.getQtdjogadas() == 1) {
			// prefere a posição central
			pos[0] = 1;
			pos[1] = 1;
			// se adversário já escolheu a posição central
			if(postab[1][1] != ' ') {
				// escolhe o canto
				pos[0] = 0;
				pos[1] = 0;
			}
			return pos;
		}
			
		// segunda rodada
		// caso adversário escolheu a posição central na primeira jogada
		if(postab[1][1] == sinaladv && Tabuleiro.getQtdjogadas() == 3) {
			// procura a segunda jogada do adversário e bloqueia
			if(temSinal(sinaladv, 0, 1, 2, 1))
				return pos;
			if(temSinal(sinaladv, 0, 2, 2, 0))
				return pos;
			if(temSinal(sinaladv, 1, 0, 1, 2))
				return pos;
			if(temSinal(sinaladv, 1, 2, 1, 0))
				return pos;
			if(temSinal(sinaladv, 2, 0, 0, 2))
				return pos;
			if(temSinal(sinaladv, 2, 1, 0, 1))
				return pos;
			
			// se não encontrou situação de risco, escolhe a próxima posição disponível
			return pos = Tabuleiro.proxPosLivre();
			
		}
		
		// se o adversário não jogou na posição central, temos 4 possibilidades:
		//
		// 1-	procura linha com dois sinais iguais
		//		escolhe a posição livre na linha.
		int poslivre;
		for(int lin = 0; lin < 3; lin++) {
			// procura uma posição livre na linha
			poslivre = Tabuleiro.posEspaco(lin, 0);
			if(poslivre != -1) {
				pos[0] = lin;
				pos[1] = poslivre;
				return pos;
			}
		}
		// 2-	procura coluna com dois sinais iguais
		//		escolhe a posição livre na coluna.
		for(int col = 0; col < 3; col++) {
			// procura uma posição livre na coluna
			poslivre = Tabuleiro.posEspaco(col, 1);
			if(poslivre != -1) {
				pos[0] = poslivre;
				pos[1] = col;
				return pos;
			}
		}
		// 3-	procura diagonal com dois sinais iguais
		//		escolhe a posição livre na diagonal.
		if(temSinal(postab[1][1], 0, 0, 2, 2))
			return pos;
		if(temSinal(postab[1][1], 0, 2, 2, 0))
			return pos;
		if(temSinal(postab[1][1], 2, 2, 0, 0))
			return pos;
		if(temSinal(postab[1][1], 2, 0, 0, 2))
			return pos;
		
		// 4-	escolhe a próxima posição disponível
		return pos = Tabuleiro.proxPosLivre();
	}

	
	/**
	 * Verifica se uma posição do tabuleiro contém o sinal indicado
	 * e se a posição pretendida está livre.
	 * 
	 * @param sinal		o sinal a ser testado ('X' | 'O')
	 * @param tlin		a linha que pode conter o sinal
	 * @param tcol		a coluna que pode conter o sinal
	 * @param plin		a linha que deseja selecionar
	 * @param pcol		a coluna que deseja selecionar
	 * @return			true = o sinal foi encontrado e a posição desejada está livre,
	 * 					false = o sinal não foi encontrado ou a posição desejada está ocupada.
	 */
	private Boolean temSinal(char sinal, int tlin, int tcol, int plin, int pcol) {
		if(postab[tlin][tcol] == sinal && postab[plin][pcol] == ' ') {
			pos[0] = plin;
			pos[1] = pcol;
			return true;
		}
		return false;
	}
	
}
