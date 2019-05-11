package org.fogbeam.example.opennlp.test;


import org.fogbeam.example.opennlp.TokenizerMain;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestTokenizerMain {
	
	private TokenizerMain tokenizerMain= new TokenizerMain();

	/**
	 * Valida si se crea el archivo
	 */
	@Test
	public void test1_archivoExiste(){
		tokenizerMain.leerArchivo();
		assertTrue(tokenizerMain.getArchivo().isFile());
	}
	
	/**
	 * Valida si el archivo contiene texto
	 */
	@Test 
	public void test2_contieneTExto(){
		tokenizerMain.leerArchivo();
		assertTrue(!tokenizerMain.getTexto().isEmpty());
	}
	
	/**
	 * Valida si se obtuvieron los tokens del texto
	 * @throws Exception 
	 */
	@Test
	public void test3_contieneTokens() throws Exception{
		test1_archivoExiste();
		test2_contieneTExto();
		tokenizerMain.Tokenizer(tokenizerMain.getTexto());
		assertTrue(!tokenizerMain.getTokens().toString().isEmpty());
	}

}


