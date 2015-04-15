/*Nome: Lex.java
 *Descricao: Classe que "constroi" o analisador lexico em si.
 *Autores: Jooao Flavio e Mateus Zitelli
 * 
 */
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Tokenizer {
    // Stores if the lex is ok without erros
    public static boolean ok = true;

    public static Token getToken (String command) {
        int matchedTypes = 0;
        TokenType matchedType = null;
        String matchedData = null;

        Matcher m = Pattern.compile("init").matcher("init");

        for (TokenType type: TokenType.values()) {
            m = m.reset(command).usePattern(type.pattern);

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

    public static String preProcess(String input) {
        // add extra " " after and before some symbols to the tokenizer recognize as an new command
        return input.replaceAll("(;|=|<>|>|<|<=|>=|\\*|\\(|\\)|'|\\\"|,)", " $1 ");
    }

	public static ArrayList<Token> getTokens (String input) {
        ArrayList<Token> tokens = new ArrayList<Token>();
        input = preProcess(input);
        ArrayList<String> splitedInput = new ArrayList<String>(Arrays.asList(input.split("\\s+")));
        System.out.println("( ͡° ͜ʖ ͡°) Tokens:");
        for(String command : splitedInput) {
            Token token = getToken(command);
            System.out.println(token);
            if(token != null) {
                tokens.add(token);
            }
        }

        tokens.add(new Token(TokenType.EOF, ""));

        return tokens;
	}
}
