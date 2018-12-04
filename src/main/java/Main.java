
public class Main {

	private static Model model;
	
	public static void main(String[] args) {

		model = Model.getInstance();
		initializeModel(model);
		View view = new View(model);
		model.registerObserver(view); //connection Model -> View
		view.receiveUsersMessages();
	}
	
	public static void initializeModel(Model model){
		model.addTS(new TS("1", "S�o testes que possuem menor escopo (abrang�ncia). Incluem, por exemplo, classes simples, m�todos e fun��es, componentes pouco complexos, etc."));
		model.addTS(new TS("2", "S�o testes que abrangem dois ou mais componentes, incluindo componentes complexos (e.g.: classes controladoras, servi�os, etc.). Tipicamente s�o executados pela equipe de desenvolvimento e integra��o."));
		model.addTS(new TS("3", "Algumas vezes tamb�m s�o chamados de testes funcionais. T�m como foco os requisitos de alto n�vel do sistema e incluem a intera��o de diversos componentes que interagem no provimento da funcionalidade final do sistema. Podem ser executados por uma equipe espec�fica de V&V."));
		model.addTS(new TS("4", "Tamb�m conhecidos como testes estruturais. S�o executados tendo-se o conhecimento dos detalhes de constru��o do sistema de software."));
		model.addTS(new TS("5", "S�o executados sem o conhecimento de detalhes de constru��o do sistema."));
		model.addTS(new TS("6", "S�o executados tendo-se como base um conhecimento parcial sobre a constru��o do sistema."));
		
		model.addBD(new BD("1", "Conjunto de atributos de uma entidade que ocorre v�rias vezes para cada ocorr�ncia da Entidade"));
		model.addBD(new BD("2", "Ocorre quando um atributo depende apenas de parte de uma chave prim�ria composta"));
		model.addBD(new BD("3", "Ocorre quando um atributo al�m de depender da chave prim�ria da tabela, depende de outra coluna ou conjunto de colunas da tabela"));
	}
}