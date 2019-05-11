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
	
	/**
	 * Metodo que obtiene el archivo dada en el objeto ruta
	 * @return texto: texto de todas las lineas del archivo
	 */
	public String leerArchivo() {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		String texto="";		
		try {
			archivo = new File (ruta);
			fr = new FileReader (archivo);
			br = new BufferedReader(fr);
			String linea;
			while((linea=br.readLine())!=null) {
				texto=texto+linea;
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
		return texto;
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
			String[] tokens = tokenizer.tokenize
					(texto);

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
