package xadrez;

import jogoDeTabuleiro.Peca;
import jogoDeTabuleiro.Posicao;
import jogoDeTabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaDeXadrez {

	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;

	// Construtor
	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		jogadorAtual = Cor.BRANCO;
		ConfiguracaoInicial();
	}

	// get e set
	public int getTurno() {
		return turno;
	}

	public Cor getJogadorAtual() {
		return jogadorAtual;
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

	public boolean[][] possiveisMovimentos(XadrezPosicao origemPosicao) {
		Posicao posicao = origemPosicao.toPosicao();
		validarPosicaoDeOrigem(posicao);
		return tabuleiro.peca(posicao).possiveisMovimentos();
	}

	public PecaDeXadrez executarMovimentoDeXadrez(XadrezPosicao posicaoOrigem, XadrezPosicao posicaoDestino) {
		Posicao origem = posicaoOrigem.toPosicao();
		Posicao destino = posicaoDestino.toPosicao();
		validarPosicaoDeOrigem(origem);
		validarPosicaoDestino(origem, destino);
		Peca capturarPeca = fazerMover(origem, destino);
		proximoTurno();
		return (PecaDeXadrez) capturarPeca;
	}

	private Peca fazerMover(Posicao origem, Posicao destino) {
		Peca p = tabuleiro.removePeca(origem);
		Peca capturarPeca = tabuleiro.removePeca(destino);
		tabuleiro.colocarPeca(p, destino);

		return capturarPeca;
	}

	private void validarPosicaoDeOrigem(Posicao posicao) {
		if (!tabuleiro.haUmaPeca(posicao)) {
			throw new XadrezException("Nao existe peça na posicao de origem.");
		}
		if(jogadorAtual != ((PecaDeXadrez)tabuleiro.peca(posicao)).getCor()) {
			throw new XadrezException("A peca escolhida nao e sua.");
		}
		if (!tabuleiro.peca(posicao).existeAlgumMovimentoPossivel()) {
			throw new XadrezException("Nao existe movimentos possiveis para peca escolhida.");
		}
	}

	private void validarPosicaoDestino(Posicao origem, Posicao destino) {
		if (!tabuleiro.peca(origem).possiveisMovimento(destino)) {
			throw new XadrezException("A peca escolhida nao pode se mover para posicao de destino.");
		}
	}

	private void proximoTurno() {
		turno++;
		jogadorAtual = (jogadorAtual == Cor.BRANCO)? Cor.PRETO : Cor.BRANCO;
	}

	private void coloqueUmaNovaPeca(char coluna, int linha, PecaDeXadrez peca) {
		tabuleiro.colocarPeca(peca, new XadrezPosicao(coluna, linha).toPosicao());
	}

	// inicia a partida de xadrez colocando as pecas no tabuleiro.
	private void ConfiguracaoInicial() {

		coloqueUmaNovaPeca('c', 1, new Torre(tabuleiro, Cor.BRANCO));
		coloqueUmaNovaPeca('c', 2, new Torre(tabuleiro, Cor.BRANCO));
		coloqueUmaNovaPeca('d', 2, new Torre(tabuleiro, Cor.BRANCO));
		coloqueUmaNovaPeca('e', 2, new Torre(tabuleiro, Cor.BRANCO));
		coloqueUmaNovaPeca('e', 1, new Torre(tabuleiro, Cor.BRANCO));
		coloqueUmaNovaPeca('d', 1, new Rei(tabuleiro, Cor.BRANCO));

		coloqueUmaNovaPeca('c', 7, new Torre(tabuleiro, Cor.PRETO));
		coloqueUmaNovaPeca('c', 8, new Torre(tabuleiro, Cor.PRETO));
		coloqueUmaNovaPeca('d', 7, new Torre(tabuleiro, Cor.PRETO));
		coloqueUmaNovaPeca('e', 7, new Torre(tabuleiro, Cor.PRETO));
		coloqueUmaNovaPeca('e', 8, new Torre(tabuleiro, Cor.PRETO));
		coloqueUmaNovaPeca('d', 8, new Rei(tabuleiro, Cor.PRETO));
	}
}
