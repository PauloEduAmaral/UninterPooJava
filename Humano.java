package jogodavelha;
import java.util.Scanner;

/**
 * Classe derivada que constrói um jogador do tipo Humano.
 */
public class Humano extends Jogador {

	private Scanner entrada = new Scanner(System.in);
	
	/**
	 * Método construtor.
	 * 
	 * @param sinal		o sinal que será usado pelo
	 * 					jogador ('X' | 'O').
	 */
	public Humano(char sinal) {
		super(sinal);
	}
	
	/**
	 * Seleciona a posição a ser jogada.
	 * 
	 *<return		as coordenadas (linha, coluna)
	 * 				da posição escolhida.
	 */
	@Override
	public int[] posicao(){
		pos[0] = escolhe("linha");
		pos[1] = escolhe("coluna");
		return pos;
	}
	
	/**
	 * @param texto		uma palavra ("linha" | "coluna") que
	 * 					será exibida ao solicitar a posição.
	 * @return			posição escolhida (valor entre 0 e 2).
	 */
	private int escolhe(String texto) {
		System.out.print("\nEscolha a " + texto + ": ");
		int posicao = entrada.nextInt();
		while(posicao < 1 || posicao > 3) {
			System.out.println("\nEscolha um valor entre 1 e 3: ");
			posicao = entrada.nextInt();
		}
		return posicao-1;
	}
	
	/**
	 * @return		o tipo do jogador:
	 * 				true = humano.
	 */
	@Override
	public Boolean tipo() {
		return true;
	}
}
