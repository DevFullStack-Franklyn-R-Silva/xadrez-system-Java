package jogoDeTabuleiro;

public class Peca {
		
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
}
