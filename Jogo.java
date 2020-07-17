package jogodavelha;
import java.util.Scanner;

/**
 * Jogo é a classe principal que inicia a aplicação.
 * Um jogador humano joga contra o computador, e pode
 * escolher entre 3 níveis de dificuldade.
 * O jogo sempre inicia pelo jogador humano.
 * 
 * @author Paulo Eduardo do Amaral
 */
public class Jogo {

	public static void main(String[] args) {
		System.out.println("\n*** JOGO DA VELHA ***\n");
		
		// define que o jogador humano usará o sinal X
		Jogador humano = new Humano('X');
		Jogador computador = null;
		
		// escolhe o nível do oponente
		System.out.print("Selecione o nível"
						+ "\n1 - Fácil"
						+ "\n2 - Médio"
						+ "\n3 - Difícil"
						+ "\nOpção: ");
		Scanner entrada = new Scanner(System.in);
		int opcao = entrada.nextInt();
		while(opcao < 1 || opcao > 3) {
			System.out.print("\nOpção inválida. Selecione 1, 2 ou 3: ");
			opcao = entrada.nextInt();
		}
		
		switch(opcao) {
		case 1:
			computador = new ComputadorA('O');
		break;
		case 2:
			computador = new ComputadorB('O');
		break;
		case 3:
			computador = new ComputadorC('O');
		break;
		}
		
		// inicia o tabuleiro e prepara o jogador inicial
		Tabuleiro tabuleiro = new Tabuleiro();
		Jogador jogador = humano;
		
		// laço principal:
		// repete enquanto não houver vencedor ou empate
		boolean jogadainvalida = true;
		while(tabuleiro.getVencedor() == ' ' && !tabuleiro.empate()) {
			
			// repete até que o jogador faça uma jogada válida
			do {
				jogadainvalida = tabuleiro.fazJogada(jogador.posicao(), jogador.getSinal(), jogador.tipo());
			}
			while (jogadainvalida);
			
			// troca o jogador
			if(jogador == humano)
				jogador = computador;
			else
				jogador = humano;
		}
		
		// Exibe o resultado da partida
		if(tabuleiro.getVencedor() != ' ')
			System.out.println("\nJogador " + tabuleiro.getVencedor() + " venceu!");
		else
			System.out.println("\nEmpate!");
	}

}
