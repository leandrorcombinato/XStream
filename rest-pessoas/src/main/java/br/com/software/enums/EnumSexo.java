package br.com.software.enums;

public enum EnumSexo {
	
	MASCULINO("M"), FEMININO("F");
	
	private String descricao;
	 
    EnumSexo(String descricao) {
        this.descricao = descricao;
    }
 
    public String getDescricao() {
        return descricao;
    }

}
