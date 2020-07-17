package jogodavelha;
import java.util.Random;

/**
 * Classe derivada que constrói um jogador do tipo Computador B.
 * 
 * Este oponente é utilizado no nível Médio do jogo.
 * Ele sempre irá escolher uma posição aleatória do tabuleiro,
 * independente das jogadas do adversário humano.
 */
public class ComputadorB extends Jogador{
	
	private Random r = new Random();
	
	/**
	 * Método construtor.
	 * 
	 * @param sinal		o sinal que será usado pelo
	 * 					jogador ('X' | 'O').
	 */
	public ComputadorB(char sinal) {
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
		pos[0] = r.nextInt(3);
		pos[1] = r.nextInt(3);
		return pos;
	}

}
