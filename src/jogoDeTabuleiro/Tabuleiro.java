package jogoDeTabuleiro;

public class Tabuleiro {
	
	// Atributos
	private Integer linhas;
	private Integer colunas;
	private Peca[][] pecas;

//	Construtor
	public Tabuleiro(Integer linhas, Integer colunas) {
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new Peca[linhas][colunas];
	}

	// Gets e Sets
	public Integer getLinhas() {
		return linhas;
	}

	public void setLinhas(Integer linhas) {
		this.linhas = linhas;
	}

	public Integer getColunas() {
		return colunas;
	}

	public void setColunas(Integer colunas) {
		this.colunas = colunas;
	}

	// Métodos
	public Peca peca(int linha, int coluna) {
		return pecas[linha][coluna];
	}

	public Peca peca(Posicao posicao) {
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}

}
