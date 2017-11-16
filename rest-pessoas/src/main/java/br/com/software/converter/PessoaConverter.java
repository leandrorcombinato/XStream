package br.com.software.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import br.com.software.Pessoa;

public class PessoaConverter implements Converter {

	@Override
	@SuppressWarnings("rawtypes")
	public boolean canConvert(Class clazz) {
		return clazz.equals(Pessoa.class);
	}

	@Override
	public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {
		DateFormat formatter = DateFormat.getDateInstance(DateFormat.FULL);
		Pessoa pessoa = (Pessoa) value;

		writer.startNode("id");
		writer.setValue(pessoa.getId().toString());
		writer.endNode();

		writer.startNode("nome");
		writer.setValue(pessoa.getNome().toString());
		writer.endNode();

		Calendar calendar = (Calendar) pessoa.getDataNasc();
		writer.startNode("dataNasc");
		writer.setValue(formatter.format(calendar.getTime()));
		writer.endNode();

		writer.startNode("sexo");
		writer.setValue(pessoa.getSexo());
		writer.endNode();

	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		Pessoa pessoa = new Pessoa();
		try {
			reader.moveDown();
			pessoa.setId(Long.parseLong(reader.getValue()));
			reader.moveUp();
			
			reader.moveDown();
			pessoa.setNome(reader.getValue());
			reader.moveUp();

			reader.moveDown();
			DateFormat FORMATTER = DateFormat.getDateInstance(DateFormat.FULL);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(FORMATTER.parse(reader.getValue()));
			pessoa.setDataNasc(calendar);
			reader.moveUp();
			
			reader.moveDown();
			pessoa.setSexo(reader.getValue());
			reader.moveUp();
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return pessoa;
	}
}
