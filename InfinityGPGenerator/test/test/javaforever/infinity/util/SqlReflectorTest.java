package test.javaforever.infinity.util;

import junit.framework.TestCase;

import org.javaforever.infinity.domain.Domain;
import org.javaforever.infinity.utils.SqlReflector;
import org.junit.Test;

public class SqlReflectorTest extends TestCase {
	@Test
	public void testGenerateTableDefinition() throws Exception {
		Domain d = new Domain();
		d.setStandardName("Goose");
		d.setPlural("geese");
		d.addField( "id","long");
		d.addField("name", "String");
		d.addField("comment","String");
		d.addField("description", "String");
		
		SqlReflector sr = new SqlReflector();
		System.out.println(sr.generateTableDefinition(d));
	}
	
	@Test
	public void testGenerateInsertSqlWithQuestionMark() throws Exception {
		Domain d = new Domain();
		d.setStandardName("Leave");
		d.addField( "id","long");
		d.addField("name", "String");
		d.addField("comment","String");
		d.addField("description", "String");
		d.addField("price", "double");
		d.addField("amount", "int");
		
		SqlReflector sr = new SqlReflector();
		System.out.println(sr.generateInsertSqlWithQuestionMark(d));
	}
	
	@Test
	public void testGenerateUpdateSqlWithQuestionMark() throws Exception {
		Domain d = new Domain();
		d.setStandardName("Leave");
		d.addField( "id","long");
		d.addField("name", "String");
		d.addField("comment","String");
		d.addField("description", "String");
		d.addField("price", "double");
		d.addField("amount", "int");
		
		SqlReflector sr = new SqlReflector();
		System.out.println(sr.generateUpdateSqlWithQuestionMark(d));
	}
	
	@Test
	public void testGenerateDeleteSqlWithQuestionMark() throws Exception {
		Domain d = new Domain();
		d.setStandardName("Leave");
		d.addField( "id","long");
		d.addField("name", "String");
		d.addField("comment","String");
		d.addField("description", "String");
		d.addField("price", "double");
		d.addField("amount", "int");
		
		SqlReflector sr = new SqlReflector();
		System.out.println(sr.generateDeleteSqlWithQuestionMark(d));
	}
}
