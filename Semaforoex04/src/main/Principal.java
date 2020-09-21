package main;

import java.util.concurrent.Semaphore;

import controller.Threadtransacoes;

public class Principal {
	public static void main(String[] args) {
		int permissoes = 1;
		int idoperacao = 0;
		int codconta=312832;
		int salconta=5000;
		int valortransição=0;
		Semaphore semaforo1 = new Semaphore(permissoes);
		Semaphore semaforo2 = new Semaphore(permissoes);
		for (int i = 0; i < 20; i++) {
			valortransição = (int) ((Math.random()*501)+500);
			idoperacao = (int) ((Math.random()*2)+0);
			Thread tTrans = new Threadtransacoes(idoperacao,semaforo1,semaforo2,codconta,salconta,valortransição);
			tTrans.start();
		}
	}
}
