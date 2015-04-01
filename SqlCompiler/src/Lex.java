/*Nome: Lex.java
 *Descrição: Classe que "constrói" o analisador léxico em si.
 *Autores: João Flávio e...
 * 
 */
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
            if(token != null) {
                tokens.add(token);
            }
        }

        return tokens;
	}

	public static void main(String[] args) {
		String input = "CREATE DATABASE test ;";
		ArrayList<Token> tokens = getTokens(input);
		
		for (Token token : tokens){
			System.out.println(token);
		}
	}
	
	
}
