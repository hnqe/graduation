package enums;

public enum TipoDeDocumento {
	RG("Registro Geral"),
	CPF("Cadastro de Pessoa Física"),
	CNPJ("Cadastro de Pessoa Jurídica");

	private String descricao;

	private TipoDeDocumento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
