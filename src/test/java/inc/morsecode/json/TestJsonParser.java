package inc.morsecode.json;

import inc.morsecode.json.ex.MalformedJsonException;
import inc.morsecode.spec.json.JsonStructure;
import org.junit.Test;
import org.junit.Assert;

public class TestJsonParser {

	private final static String SIMPLE= "{\"x\": 43, \"y\": 22}";
	private final static String OBJECT= "{\"x\": 43, \"address\": {\"street\": \"123 main\", \"city\": \"Denver\", \"state\": \"CO\"}}";
	private final static String ARRAY= "{\"array\": 43, \"address\": {\"street\": \"123 main\", \"city\": \"Denver\", \"state\": \"CO\"}}";
	private final static String MALFORMED_JSON= "{x: 43}";
	private final static String COMPLEX= "{\"x\": 43, \"y\": 22, \"foo\": \"2\", \"bar\": 4561231232132132131}";
	private final static String MALFORMED_JSON_COMPLEX= "{\"x\": 43, \"y\": 22, \"foo\": \"2\", \"bar\"x: \"abc\" }}";

	@Test
	public void testParse() {
		try {
			JsonStructure json= JsonParser.parse(SIMPLE);
			int x= json.get("x", 0);
			Assert.assertEquals(43, x);
		} catch (MalformedJsonException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test(expected= MalformedJsonException.class)
	public void testMalformed() throws MalformedJsonException {
		JsonParser.parse(MALFORMED_JSON);
	}

	@Test
	public void testComplex() {
		try {
			JsonStructure json= JsonParser.parse(COMPLEX);
			int x= json.get("x", 0);
			Assert.assertEquals(43, x);
		} catch (MalformedJsonException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test(expected= MalformedJsonException.class)
	public void testMalformedComplex() throws MalformedJsonException {
		JsonParser.parse(MALFORMED_JSON_COMPLEX);
	}

	@Test(expected= MalformedJsonException.class)
	public void testMalformedComplex1() throws MalformedJsonException {
		JsonParser.parse("{\"x\": 43, \"y\": 22, \"foo\": \"2\", \"bar\": abc }}");
	}

	@Test(expected= MalformedJsonException.class)
	public void testMalformedComplex2() throws MalformedJsonException {
		JsonParser.parse("{\"x\": 43, \"y\": 22b, \"foo\": \"2\" }");
	}
}
