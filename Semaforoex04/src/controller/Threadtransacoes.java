package controller;

import java.util.concurrent.Semaphore;

public class Threadtransacoes extends Thread {
	private int idoperacao;
	private Semaphore semaforo1;
	private Semaphore semaforo2;
	private static int codconta;
	private static int salconta;
	private int valortransição;

	public Threadtransacoes(int idoperacao, Semaphore semaforo1, Semaphore semaforo2, int codconta, int salconta, int valortransição) {
		this.idoperacao = idoperacao;
		this.semaforo1 = semaforo1;
		this.semaforo2 = semaforo2;
		this.codconta = codconta;
		this.salconta = salconta;
		this.valortransição = valortransição;
	}

	public void run() {
		if (idoperacao == 0) {
			try {
				semaforo1.acquire();
				operacaoSaque();
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				operacaoFin();
				semaforo1.release();
			}
		} else {
			try {
				semaforo2.acquire();
				operacaoDeposito();
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				operacaoFin();
				semaforo2.release();
			}
		}

	}

	private void operacaoSaque() {
		System.out.println("efetuando o Saque na conta de cod: " +codconta +" saldo atual da conta: " +salconta+" valor de saque: "+valortransição);
		try {
			salconta=salconta-valortransição;
			sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("efetuado o saque!, saldo da conta: "+salconta);
	}

	private void operacaoDeposito() {
		System.out.println("efetuando o deposito na conta de cod: " +codconta +" saldo atual da conta: " +salconta+" valor de deposito: "+valortransição);
		try {
			salconta=salconta+valortransição;
			sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("efetuado o deposito!, saldo da conta: "+salconta);
	}
		

	private void operacaoFin() {
		String operacao = "";
		if (idoperacao == 0) {
			operacao = "Saque";
		}else {
			operacao = "Deposito";
		}
		System.out.println("operação "+operacao+" finalizada");
		
	}
}
