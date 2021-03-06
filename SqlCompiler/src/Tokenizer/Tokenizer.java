/*Nome: Lex.java
 *Descricao: Classe que "constroi" o analisador lexico em si.
 *Autores: Jooao Flavio e Mateus Zitelli
 * 
 */
package Tokenizer;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.regex.Matcher;

public class Tokenizer {
	public static ArrayList<Token> getTokens (String input) {
        ArrayList<Token> tokens = Identifier.identifyTokens(input);
        tokens.add(new Token(TokenType.EOF, null));
        System.out.println(tokens);
        return tokens;
	}
}
