package jogodavelha;

/**
 * Classe derivada que constrói um jogador do tipo Computador A.
 * 
 * Este oponente é utilizado no nível Fácil do jogo.
 * Ele sempre irá escolher a primeira posição livre do tabuleiro,
 * independente das jogadas do adversário humano, iniciando nas
 * coordenadas (0, 0) até (2, 2).
 */
public class ComputadorA extends Jogador{

	private int linha = 0;
	private int coluna = -1;

	/**
	 * Método construtor.
	 * 
	 * @param sinal		o sinal que será usado pelo
	 * 					jogador ('X' | 'O').
	 */
	public ComputadorA(char sinal) {
		super(sinal);
	}

	/**
	 * Seleciona a posição a ser jogada.
	 * 
	 *<return		as coordenadas (linha, coluna)
	 * 				da posição escolhida.
	 */
	@Override
	public int[] posicao() {
		
		if(coluna == 2) {
			coluna = 0;
			linha++;
		}
		else
			coluna++;
		
		pos[0] = linha;
		pos[1] = coluna;
		return pos;
	}
}
