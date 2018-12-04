
public class TS {
	
	private String topico;
	private String descricao;
	
	public TS(String topico, String descricao) {
		this.topico = topico;
		this.descricao = descricao;
	}
	
	public String getTopico() {
		return topico;
	}
	public void setTopico(String topico) {
		this.topico = topico;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}