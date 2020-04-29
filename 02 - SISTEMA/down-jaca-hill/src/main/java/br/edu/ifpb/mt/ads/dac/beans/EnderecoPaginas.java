package br.edu.ifpb.mt.ads.dac.beans;

public final class EnderecoPaginas {
	
	public EnderecoPaginas() {
		throw new UnsupportedOperationException("Esta classe não deve ser instanciada!");
	}
	
	private static final String REDIRECT_TRUE = "?faces-redirect=true";
	
	public static final String PAGINA_PRINCIPAL = "/paginas/publico/login.xhtml" + REDIRECT_TRUE;
	
	public static final String PAGINA_PRINCIPAL_AUTENTICADO = "/paginas/protegido/index.xhtml" + REDIRECT_TRUE;
	
	public static final String PAGINA_PRINCIPAL_CONTA = "/paginas/protegido/conta/conta_edit.xhtml" + REDIRECT_TRUE;
	
	public static final String PAGINA_PRINCIPAL_PESSOA = "/paginas/protegido/conta/pessoa/pessoa_edit.xhtml" + REDIRECT_TRUE;
	
	public static final String PAGINA_PRINCIPAL_ORGANIZADOR = "/paginas/protegido/conta/pessoa/organizador/organizador_edit.xhtml" + REDIRECT_TRUE;
	
	public static final String PAGINA_PRINCIPAL_CICLISTA = "/paginas/protegido/conta/pessoa/ciclista/ciclista_edit.xhtml" + REDIRECT_TRUE;
	
	public static final String PAGINA_PRINCIPAL_EVENTO = "/paginas/protegido/conta/pessoa/organizador/evento/index.xhtml" + REDIRECT_TRUE;
	
	public static final String PAGINA_PRINCIPAL_ESTADO = "/paginas/protegido/conta/administrador/estado/index.xhtml" + REDIRECT_TRUE;

	public static final String PAGINA_PRINCIPAL_CIDADE = "/paginas/protegido/conta/administrador/cidade/index.xhtml" + REDIRECT_TRUE;
	
	public static final String PAGINA_PRINCIPAL_RECURSO = "/paginas/protegido/conta/administrador/recurso/index.xhtml" + REDIRECT_TRUE;
	
	public static final String PAGINA_PRINCIPAL_MARCA = "/paginas/protegido/conta/administrador/bike/marca/index.xhtml" + REDIRECT_TRUE;
	
	public static final String PAGINA_PRINCIPAL_MODELO = "/paginas/protegido/conta/administrador/bike/modelo/index.xhtml" + REDIRECT_TRUE;
	
	public static final String PAGINA_PRINCIPAL_SENHA = "/paginas/protegido/conta/conta_edit_senha.xhtml" + REDIRECT_TRUE;
	
	public static final String PAGINA_PRINCIPAL_REDEFINICAO_SENHA = "/paginas/publico/conta/redefinicao_senha.xhtml/" + REDIRECT_TRUE;
	
	public static final String PAGINA_PRINCIPAL_RECUPERACAO_CONTA = "/paginas/publico/conta/recuperacao_conta.xhtml/" + REDIRECT_TRUE;
	
	public static final String PAGINA_PRINCIPAL_REDEFINICAO_SENHA_TOKEN = "/paginas/publico/conta/redefinicao_senha.xhtml?token=";
	
	public static final String LINK_APLICACAO = "127.0.0.1:8080/down-jaca-hill";
	
	public static final String PAGINA_PRINCIPAL_PEDIDOS_EVENTO = "/paginas/protegido/conta/pessoa/organizador/evento/pedidos_participacao.xhtml" + REDIRECT_TRUE;
	
	public static final String PAGINA_PRINCIPAL_PEDIDOS = "/paginas/protegido/conta/pessoa/ciclista/pedido/index.xhtml" + REDIRECT_TRUE;

	public static final String PAGINA_PRINCIPAL_PARTICIPACAO_EVENTO = "/paginas/protegido/conta/pessoa/organizador/evento/participacao.xhtml" + REDIRECT_TRUE;
	
}
