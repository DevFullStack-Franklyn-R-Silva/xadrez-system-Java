package xadrez;

import jogoDeTabuleiro.Posicao;

public class XadrezPosicao {
	private char coluna;
	private Integer linha;

	public XadrezPosicao(char coluna, Integer linha) {
		if (coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8) {
			throw new XadrezException("Error instânciando a posição no xadrez. Valores válidos são de a1 a h8.");
		}
		this.coluna = coluna;
		this.linha = linha;
	}

	public char getColuna() {
		return coluna;
	}

	public Integer getLinha() {
		return linha;
	}

	protected Posicao toPosicao() {
		return new Posicao(8 - linha, coluna - 'a');
	}

	protected static XadrezPosicao daPosicao(Posicao posicao) {
		return new XadrezPosicao((char) ('a' - posicao.getColuna()), 8 - posicao.getLinha());
	}
	
	@Override
	public String toString() {
		return "" + coluna +linha;
	}
}
