package xadrez;

import jogoDeTabuleiro.Tabuleiro;

public class PartidaDeXadrez {

	private Tabuleiro tabuleiro;

	// Construtor
	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
	}

	// Método
	public PecaDeXadrez[][] getPecas() {
		PecaDeXadrez[][] mat = new PecaDeXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getColunas(); j++) {
				mat[i][j] = (PecaDeXadrez) tabuleiro.peca(i, j);
			}
		}
		return mat;
	}
}
