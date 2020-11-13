package xadrez;

import jogoDeTabuleiro.Peca;
import jogoDeTabuleiro.Tabuleiro;

public class PecaDeXadrez extends Peca{
	
	// Atributos
	private Cor cor;
		
	// Construtor
	public PecaDeXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}
	
	// Gets e Sets
	public Cor getCor() {
		return cor;
	}

	
}
