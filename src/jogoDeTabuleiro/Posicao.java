package jogoDeTabuleiro;

public class Posicao {

	// Atributos
	private Integer linha;
	private Integer coluna;

	// Construtor
	public Posicao(Integer linha, Integer coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}

	// Gets e Sets
	public Integer getLinha() {
		return linha;
	}

	public void setLinha(Integer linha) {
		this.linha = linha;
	}

	public Integer getColuna() {
		return coluna;
	}

	public void setColuna(Integer coluna) {
		this.coluna = coluna;
	}

	// M�todos
	public void setValores(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}

	@Override
	public String toString() {
		return linha + ", " + coluna;
	}

}
