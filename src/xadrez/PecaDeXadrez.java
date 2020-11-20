package xadrez;

import jogoDeTabuleiro.Peca;
import jogoDeTabuleiro.Posicao;
import jogoDeTabuleiro.Tabuleiro;

public abstract class PecaDeXadrez extends Peca{
	
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
	
	protected boolean haPecaOponente(Posicao posicao) {
		PecaDeXadrez p = (PecaDeXadrez)getTabuleiro().peca(posicao);
		return p!= null && p.getCor() != cor;
	}  
	
}
