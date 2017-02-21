package com.teste;

import java.text.ParseException;

import org.junit.Test;

public class ArquivoConfirmacaoTest {

	
	@Test(expected=NumberFormatException.class)
	public void testCodigoCartorioInalido() throws ParseException {
		new ArquivoConfirmacao("2a", "0000000000", "11111111");
	}

	@Test(expected=ParseException.class)
	public void testDataProtocoloInalido() throws ParseException {
		new ArquivoConfirmacao("02", "0000000000", "a1111111");
	}

}
