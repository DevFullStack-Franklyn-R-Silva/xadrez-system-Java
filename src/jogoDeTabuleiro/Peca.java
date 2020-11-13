package jogoDeTabuleiro;

public class Peca {
	/**
	 * Checklist: 
	 * 			Classes Piece, Board[public] 
	 * 			OPP Topics: Associations
	 * 						Encapsulation / Access Modifiers 
	 * 			Data Structures Topics: 
	 * 						Matrix
	 */
	
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
