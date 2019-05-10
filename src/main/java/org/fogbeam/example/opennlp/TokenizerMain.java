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


public class TokenizerMain{

	String texto="";

	public void leerArchivo() {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;		
		try {
			archivo = new File ("src/main/resources/archivo.txt");
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
	}

	public void Tokenizer() throws Exception{
		// The Provided Model
		// InputStream modelIn = new FileInputStream( "models/en-token.bin" );

		// The model we trained
		InputStream modelIn = new FileInputStream( "models/en-token.model" );

		try{
			TokenizerModel model = new TokenizerModel( modelIn );
			Tokenizer tokenizer = new TokenizerME(model);
			/* note what happens with the "three depending on which model you use */
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
		tokenizerMain.leerArchivo();
		tokenizerMain.Tokenizer();
	}
}
