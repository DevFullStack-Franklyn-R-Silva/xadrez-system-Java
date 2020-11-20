package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;
import xadrez.XadrezException;
import xadrez.XadrezPosicao;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
		List<PecaDeXadrez> capturar = new ArrayList<>();
		
		
		while (true) {
			try {
				UI.limparATela();
				UI.printPartida(partidaDeXadrez, capturar);
				System.out.println();
				System.out.print("Origem: ");
				XadrezPosicao origem = UI.lerPosicaoXadrez(sc);
				
				boolean[][] possiveisMovimentos = partidaDeXadrez.possiveisMovimentos(origem);
				UI.limparATela();
				UI.printTabuleiro(partidaDeXadrez.getPecas(), possiveisMovimentos);
				
				System.out.println();
				System.out.print("Destino: ");
				XadrezPosicao destino = UI.lerPosicaoXadrez(sc);
	
				PecaDeXadrez capturarPeca = partidaDeXadrez.executarMovimentoDeXadrez(origem, destino);
				
				if(capturarPeca!= null) {
					capturar.add(capturarPeca);
				}
				
				
			}
			catch (XadrezException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
	}

}
