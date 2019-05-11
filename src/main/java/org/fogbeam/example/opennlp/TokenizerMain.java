package org.fogbeam.example.opennlp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

/**
 * Tokenizer: Clase que permite cortar una oración en partes mas pequeñas (tokens)
 * 
 * @author Felipe Valencia - fvalenci@ull.edu.es
 * @version 11.05.2019 
 */

public class TokenizerMain{
	
	private final String ruta="src/main/resources/archivo.txt";
	public File archivo = null;
	public String texto = "";	
	public String[] tokens = null;
	
	/**
	 * Getter
	 * @return: devuelve el valor que contiene archivo
	 */
	public File getArchivo() {
		return archivo;
	}
	
	/**
	 * Getter
	 * @return: devuelve el valor que contiene texto
	 */
	public String getTexto() {
		return texto;
	}
	
	public void setTexto(String texto) {
		this.texto = texto;
	}

	/**
	 * Getter
	 * @return: obtiene los valores del arreglo de tokens
	 */
	public String[] getTokens() {
		return tokens;
	}
	
	public void setTokens(String[] tokens) {
		this.tokens = tokens;
	}

	/**
	 * Metodo que obtiene el archivo dada en el objeto ruta
	 * @return texto: texto de todas las lineas del archivo
	 */
	public String leerArchivo() {
		FileReader fr = null;
		BufferedReader br = null;
		try {
			archivo = new File (ruta);
			fr = new FileReader (archivo);
			br = new BufferedReader(fr);
			String linea;
			while((linea=br.readLine())!=null) {
				setTexto(getTexto()+linea);
			}
		}
		catch(Exception e){
			e.getMessage();
		}finally{
			try{
				if( null != fr ){
					fr.close();
				}
			}catch (Exception e){
				e.getMessage();
			}
		}
		return getTexto();
	}
	
	/**
	 * Imprime cada uno de los componentes de un texto cortados por tokens
	 * @param texto: texto que se quiere cortar 
	 * @throws Exception: captura la excepcion de no poder abrir el archivo del modelo
	 */
	public void Tokenizer(String texto) throws Exception{
		InputStream modelIn = new FileInputStream( "models/en-token.model" );
		try{
			TokenizerModel model = new TokenizerModel( modelIn );
			Tokenizer tokenizer = new TokenizerME(model);
			setTokens(tokenizer.tokenize(texto));

			for( String token : tokens ){
				System.out.println( token );
			}
		}
		catch( IOException e ){
			e.printStackTrace();
		}
		finally{
			if( modelIn != null ){
				try{
					modelIn.close();
				}
				catch( IOException e ){
					e.getMessage();
				}
			}
		}
		
		System.out.println( "\n-----\ndone" );
	}

	public static void main( String[] args ) throws Exception{
		TokenizerMain tokenizerMain = new TokenizerMain();
		String texto=tokenizerMain.leerArchivo();
		tokenizerMain.Tokenizer(texto);
	}
}
