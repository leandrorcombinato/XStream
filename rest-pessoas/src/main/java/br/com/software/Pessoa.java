package br.com.software;

import java.util.Calendar;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import br.com.software.converter.PessoaConverter;
import br.com.software.enums.EnumSexo;

public class Pessoa {

	private Long id;
	private String nome;
	private Calendar dataNasc;
	private String sexo;

	public Pessoa() {
		super();
	}

	public Pessoa(Long id, String nome, Calendar dataNasc, String sexo) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataNasc = dataNasc;
		this.sexo = sexo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Calendar getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Calendar dataNasc) {
		this.dataNasc = dataNasc;
	}
	
	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataNasc == null) ? 0 : dataNasc.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (dataNasc == null) {
			if (other.dataNasc != null)
				return false;
		} else if (!dataNasc.equals(other.dataNasc))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (sexo == null) {
			if (other.sexo != null)
				return false;
		} else if (!sexo.equals(other.sexo))
			return false;
		return true;
	}

	public static String converterFromDTO(Pessoa pessoa){
		XStream xStream = new XStream(new DomDriver());
        xStream.registerConverter(new PessoaConverter());
        return xStream.toXML(pessoa);
	}

	public static Pessoa converterFromDTO(String pessoa){
		XStream xStream = new XStream(new DomDriver());
        xStream.registerConverter(new PessoaConverter());
        return (Pessoa) xStream.fromXML(pessoa);
	}
	
	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", dataNasc=" + dataNasc + ", sexo=" + sexo + "]";
	}

	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		Pessoa pessoa = new Pessoa();
		pessoa.setId(new Long(1));
		pessoa.setNome("Leandro");
		pessoa.setDataNasc(calendar);
		pessoa.setSexo(EnumSexo.MASCULINO.getDescricao());
		String pessoaXML = Pessoa.converterFromDTO(pessoa);
        System.out.println("PessoaXML \n"+pessoaXML);

        Pessoa pessoaDTO = Pessoa.converterFromDTO(pessoaXML);
        System.out.println("PessoaDTO \n"+pessoaDTO);

	}

}
