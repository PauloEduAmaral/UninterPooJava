package jogodavelha;

/**
 * Classe base para a criação de um jogador.
 */
public class Jogador{

	protected int[] pos = new int[2];
	protected char sinal;
	
	/**
	 * Método construtor.
	 * 
	 * @param sinal		o sinal que será usado pelo
	 * 					jogador ('X' | 'O').
	 */
	public Jogador(char sinal) {
		this.sinal = sinal;
	}

	/**
	 * @return		 o sinal do jogador ('X' | 'O').
	 */
	public char getSinal() {
		return sinal;
	}
	
	/**
	 * Seleciona a posição a ser jogada.
	 * 
	 * @return		as coordenadas (linha, coluna)
	 * 				da posição escolhida.
	 */
	protected int[] posicao() {
		return pos;
	}

	/**
	 * @return		o tipo do jogador:
	 * 				true = humano,
	 * 				false = computador.
	 */
	protected Boolean tipo() {
		return false;
	}
}
