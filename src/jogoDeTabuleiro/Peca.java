package jogoDeTabuleiro;

public abstract class Peca {

	// Atributos
	protected Posicao posicao;
	private Tabuleiro tabuleiro;

	// Construtor
	public Peca(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		posicao = null;
	}

	// Gets e Sets
	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	// Métodos

	public abstract boolean[][] possiveisMovimentos();

	public boolean possiveisMovimento(Posicao posicao) {
		return possiveisMovimentos()[posicao.getLinha()][posicao.getColuna()];
	}

	public boolean existeAlgumMovimentoPossivel() {
		boolean[][] mat = possiveisMovimentos();
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				if(mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}

}
