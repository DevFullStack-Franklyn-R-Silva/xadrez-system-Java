package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jogoDeTabuleiro.Peca;
import jogoDeTabuleiro.Posicao;
import jogoDeTabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaDeXadrez {

	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;
	private boolean cheque;

	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturada = new ArrayList<>();

	// Construtor
	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		jogadorAtual = Cor.BRANCO;
		cheque = false;
		ConfiguracaoInicial();
	}

	// get e set
	public int getTurno() {
		return turno;
	}

	public Cor getJogadorAtual() {
		return jogadorAtual;
	}
	public boolean getCheque() {
		return cheque;
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

		if (testeCheque(jogadorAtual)) {
			desfazerMovimento(origem, destino, capturarPeca);
			throw new XadrezException("Voce nao pode se colocar em cheque.");
		}
		cheque = (testeCheque(oponente(jogadorAtual))) ? true : false;

		proximoTurno();
		return (PecaDeXadrez) capturarPeca;
	}

	private Peca fazerMover(Posicao origem, Posicao destino) {
		Peca p = tabuleiro.removePeca(origem);
		Peca capturarPeca = tabuleiro.removePeca(destino);
		tabuleiro.colocarPeca(p, destino);

		if (capturarPeca != null) {
			pecasNoTabuleiro.remove(capturarPeca);
			pecasCapturada.add(capturarPeca);
		}

		return capturarPeca;
	}

	private void desfazerMovimento(Posicao origem, Posicao destino, Peca capturaPeca) {
		Peca p = tabuleiro.removePeca(destino);
		tabuleiro.colocarPeca(p, destino);

		if (capturaPeca != null) {
			tabuleiro.colocarPeca(capturaPeca, destino);
			pecasCapturada.remove(capturaPeca);
			pecasNoTabuleiro.add(capturaPeca);
		}
	}

	private void validarPosicaoDeOrigem(Posicao posicao) {
		if (!tabuleiro.haUmaPeca(posicao)) {
			throw new XadrezException("Nao existe peça na posicao de origem.");
		}
		if (jogadorAtual != ((PecaDeXadrez) tabuleiro.peca(posicao)).getCor()) {
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
		jogadorAtual = (jogadorAtual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}

	private void coloqueUmaNovaPeca(char coluna, int linha, PecaDeXadrez peca) {
		tabuleiro.colocarPeca(peca, new XadrezPosicao(coluna, linha).toPosicao());
		pecasNoTabuleiro.add(peca);
	}

	private Cor oponente(Cor cor) {
		return (cor == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}

	private PecaDeXadrez rei(Cor cor) {
		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez) x).getCor() == cor)
				.collect(Collectors.toList());
		for (Peca p : list) {
			if (p instanceof Rei) {
				return (PecaDeXadrez) p;
			}
		}
		throw new IllegalStateException("não há nenhum rei " + cor + " no tabuleiro");
	}

	private boolean testeCheque(Cor cor) {
		Posicao reiPosicao = rei(cor).getXadrezPosicao().toPosicao();
		List<Peca> oponentePecas = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez) x).getCor() == oponente(cor))
				.collect(Collectors.toList());

		for (Peca p : oponentePecas) {
			boolean[][] mat = p.possiveisMovimentos();
			if (mat[reiPosicao.getLinha()][reiPosicao.getColuna()]) {
				return true;
			}
		}
		return false;
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
