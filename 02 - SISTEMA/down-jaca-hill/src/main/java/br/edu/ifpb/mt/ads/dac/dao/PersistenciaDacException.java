package br.edu.ifpb.mt.ads.dac.dao;

public class PersistenciaDacException extends Exception {

	private static final long serialVersionUID = 1780243892137781599L;

	public PersistenciaDacException(String mensagem) {
		super(mensagem);
	}

	public PersistenciaDacException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
