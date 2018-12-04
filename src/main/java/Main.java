
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
		model.addTS(new TS("1", "São testes que possuem menor escopo (abrangência). Incluem, por exemplo, classes simples, métodos e funções, componentes pouco complexos, etc."));
		model.addTS(new TS("2", "São testes que abrangem dois ou mais componentes, incluindo componentes complexos (e.g.: classes controladoras, serviços, etc.). Tipicamente são executados pela equipe de desenvolvimento e integração."));
		model.addTS(new TS("3", "Algumas vezes também são chamados de testes funcionais. Têm como foco os requisitos de alto nível do sistema e incluem a interação de diversos componentes que interagem no provimento da funcionalidade final do sistema. Podem ser executados por uma equipe específica de V&V."));
		model.addTS(new TS("4", "Também conhecidos como testes estruturais. São executados tendo-se o conhecimento dos detalhes de construção do sistema de software."));
		model.addTS(new TS("5", "São executados sem o conhecimento de detalhes de construção do sistema."));
		model.addTS(new TS("6", "São executados tendo-se como base um conhecimento parcial sobre a construção do sistema."));
		
		model.addBD(new BD("1", "Conjunto de atributos de uma entidade que ocorre várias vezes para cada ocorrência da Entidade"));
		model.addBD(new BD("2", "Ocorre quando um atributo depende apenas de parte de uma chave primária composta"));
		model.addBD(new BD("3", "Ocorre quando um atributo além de depender da chave primária da tabela, depende de outra coluna ou conjunto de colunas da tabela"));
	}
}