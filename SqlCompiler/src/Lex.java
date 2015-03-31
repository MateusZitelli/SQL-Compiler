/*Nome: Lex.java
 *Descrição: Classe que "constrói" o analisador léxico em si.
 *Autores: João Flávio e...
 * 
 */
import java.util.ArrayList;


public class Lex {

	public static ArrayList<Token> lex (String input){
		//Os tokens que vão ser retornados
		ArrayList<Token> tokens = new ArrayList<Token>();
		
		return tokens;
		
	}

	public static void main(String[] args) {
		String input = "9213123123 23838 12";
		ArrayList<Token> tokens = new ArrayList<Token>();
		tokens = lex(input);
		
		
		for (Token token : tokens){
			System.out.println(token.toString());
		}
		
		System.out.println("Teste");
	}
	
	
}
