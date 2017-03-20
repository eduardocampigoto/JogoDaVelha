import java.util.Scanner;

public class Jogo {

	public int[][] criaMatriz() {
		int[][] matriz = new int[3][3];

		//Popula a matriz criada com zeros.
		for (int linha = 0; linha < 3; linha++) {
			for (int coluna = 0; coluna < 3; coluna++) {
				matriz[linha][coluna] = 0;
			}
		}
		return matriz;
	}

	
	public void iniciarJogo(int[][] matriz) throws Exception {
		int jogador = 1;

		try {
			//Mostra uma string com o valor atualizado das posições.
			mostrarJogada(matriz);
			while (true) {
				solicitaPosicao(matriz, jogador);

				//Valida a jogada e verifica se há um vencedor,
				if (retornaVencedor(matriz) != 0) {
					System.out.println(" O jogador " + jogador+ " é o vencedor ! \n");
					mostrarJogada(matriz);
					break;
				}

				if (jogador == 2) {
					jogador = 0;
				}
				jogador++;
				//Mostra uma string atualizada da matriz.
				mostrarJogada(matriz);
			}
		
		} catch (Exception e) {
			//Captura e mostra a mensagem em caso de erro.
			e.getMessage();
			e.printStackTrace();
		}

	}
	
	/*Cria uma string da matriz atualizada*/
	public void mostrarJogada(int[][] matriz) {
		String mostra = "\n     0    1   2  ";
		mostra += "\n  _______________ \n";

		for (int linha = 0; linha < 3; linha++) {
			for (int coluna = 0; coluna < 3; coluna++) {

				// Adiciona o mapa de posições a string
				if (coluna == 0) {
					mostra += linha + " |  " + matriz[linha][coluna];
				} else {
					mostra += "  | " + matriz[linha][coluna];
				}
			}
			mostra += " |\n";
		}
		mostra += "  ---------------\n";
		System.out.println(mostra);
	}

	 /*
	 * Solicita a posição da próxima jogada
	 */
	public int[][] solicitaPosicao(int[][] matriz, int jogador) {
		@SuppressWarnings("resource")
		Scanner entrada = new Scanner(System.in);
		int count = 0;
		int linha = 0;
		int coluna = 0;

		while (true) {

			System.out.println("Jogador " + jogador
					+ " informe a linha da jogada:");
			String linhaStr = entrada.nextLine();
			System.out.println("Jogador " + jogador
					+ " informe a coluna da jogada:");
			String colunaStr = entrada.nextLine();

			if (linhaStr.matches("0|1|2") && colunaStr.matches("0|1|2")) {
				linha = Integer.parseInt(linhaStr);
				coluna = Integer.parseInt(colunaStr);

				// Valida o valor inserido e verifica se a posição está vazia
				// para consolidar a jogada.
				if (matriz[linha][coluna] == 0 && linha <= 2 && coluna <= 2) {
					matriz[linha][coluna] = jogador;
					break;
				} else {
					System.out.println("\n Jogada inválida, tente novamente: ");
					jogador -= 1;
				}

			}
			count++;
			if (count >= 1) {
				System.out.println("\n \n Jogada inválida, tente novamente !");
			}

		}
		return matriz;
	}

	/* Chama a verificação das jogadas*/	
	public int retornaVencedor(int[][] jogo) {
		return verificaJogadas(jogo);
	}

	/*Faz a validação de cada jogada e avalia se ainda restam posições livres*/
	public int verificaJogadas(int[][] matriz) {
		int count = 0;

		//Valida as linhas e colunas utilizando o valor de X para
		//Caso os valores sejam iguais, retorna o valor da posição, que
		// deve ser o número do jogador vencedor.
		for (int x = 0; x <= 2; x++) {
			//Valida as linhas
			if (matriz[x][0] == matriz[x][1] && matriz[x][0] == matriz[x][2]) {
				return matriz[x][0];
			}
			//Valida as colunas
			if (matriz[0][x] == matriz[1][x] && matriz[0][x] == matriz[2][x]) {
				return matriz[0][x];
			}
		}
		
		// Validação das diagonais
		if (matriz[0][0] == matriz[1][1] && matriz[0][0] == matriz[2][2]) {
			return matriz[0][0];
		}

		if (matriz[0][2] == matriz[1][1] && matriz[0][2] == matriz[2][0]) {
			return matriz[0][2];
		}
        // Valida as posições vazias 
		count = 0;
		for (int linha = 0; linha < 3; linha++) {
			for (int coluna = 0; coluna < 3; coluna++) {
				if (matriz[linha][coluna] == 0) {
					count++;
				}
			}
		}
		
		// Caso não exista mais posições livres e não há vencedor
		//finaliza o jogo com empate
		if (count == 0) {
			System.out.println("\n Empate !!\n");
			System.exit(0);
		}

		// retorna 0 caso ainda não haja vencedores.
		return 0;

	}

}
