/*Nome: Lex.java
 *Descricao: Classe que "constroi" o analisador lexico em si.
 *Autores: Jooao Flavio e Mateus Zitelli
 * 
 */
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lex {
    // Stores if the lex is ok without erros
    public static boolean ok = true;

    public static Token getToken (String command) {
        int matchedTypes = 0;
        TokenType matchedType = null;
        String matchedData = null;

        Pattern r;
        Matcher m;

        for (TokenType type: TokenType.values()) {
            r = Pattern.compile(type.pattern);
            m = r.matcher(command);

            if (m.find()) {
                // If some type matches then return a token with it
                matchedType = type;
                matchedData = m.group(0);
                return new Token(matchedType, matchedData); 
            }
        }

        // If nothing matches there is no token to represent the command
        ok = false; 
        return null;
    }

	public static ArrayList<Token> getTokens (String input) {
        ArrayList<Token> tokens = new ArrayList<Token>();
        ArrayList<String> splitedInput = new ArrayList<String>(Arrays.asList(input.split("\\s+")));

        for(String command : splitedInput) {
            Token token = getToken(command);
            System.out.println(token);
            if(token != null) {
                tokens.add(token);
            }
        }

        return tokens;
	}

	public static void main(String[] args) {
		//String input = "9213123123 23838 12 SELECT select from FROM";
		
		try {
			
			String  arquivo = LeitorArquivo.readFile();
			ArrayList<Token> tokens = getTokens(arquivo);
			
			for (Token token : tokens){
				System.out.println(token);
			}
			
			
		} catch (FileNotFoundException e) {
			System.err.println("Impossível ler o arquivo.");
		}
		
	}
	
	
}
