/*Nome: Lex.java
 *Descricao: Classe que "constroi" o analisador lexico em si.
 *Autores: Jooao Flavio e Mateus Zitelli
 * 
 */
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.regex.Matcher;

public class Tokenizer {
    // Stores if the lex is ok without erros
    public static boolean ok = true;

    public static Token getToken (String palavra) {
        int state = 0;
        for(char letra : palavra){ 
            switch (state) {
                case 0:
                    switch (letra) {
                        case ')':
                            if( palavra == ")"){
                                return new Token(TokenType.OPEN_PARENTHESIS , String.valueOf(palavra)); // retornar token para )
                            }
                            break;
                        case '(':
                            if( palavra == "("){
                                return new Token(TokenType.CLOSE_PARENTHESIS , String.valueOf(palavra)); // retornar token para (
                            }
                            break;
                        case '*':
                            if( palavra == "*"){
                                return new Token(TokenType.STAR , String.valueOf(palavra)); // retornar token para *
                            }
                            break;
                        case ',':
                            if( palavra == ","){
                                return new Token(TokenType.COMMA , String.valueOf(palavra)); // retornar token para ,
                            }
                            break;
                        case ';':
                            if( palavra == ";"){
                                return new Token(TokenType.END_STATEMENT , String.valueOf(palavra)); // retornar token para ;
                            }
                            break;
                        case '=':
                            if( palavra == "="){
                                return new Token(TokenType.OPERATOR , String.valueOf(palavra)); // retornar token para =
                            }
                            break;
                        case '<':
                            if( palavra == "<"){
                                return new Token(TokenType.OPERATOR , String.valueOf(palavra)); // retornar token para <
                            }
                            state = 8;
                            break;
                        case '>':
                            if( palavra == ">"){
                                return new Token(TokenType.OPERATOR, String.valueOf(palavra)); // retornar token para >
                            }
                            state = 9;
                            break;
                        case 'a':
                            state = 1;
                            break;
                        case 'c':
                            state = 10;
                            break;
                        case 'd':
                            state = 2;
                            break;
                        case 'f':
                            state = 6;
                            break;
                        case 'i':
                            state = 4;
                            break;
                        case 'k':
                            if( palavra == "key"){
                                return new Token(TokenType.KEY , String.valueOf(palavra)); // retornar token para key
                            }
                            break;
                        case 'm':
                            if( palavra == "modify"){
                                return new Token(TokenType.MODIFY , String.valueOf(palavra)); // retornar token para modify
                            }
                            break;
                        case 'n':
                            state = 5;
                            break;
                        case 'p':
                            if( palavra == "primary"){
                                return new Token(TokenType.PRIMARY , String.valueOf(palavra)); // retornar token para primary
                            }
                            break;
                        case 's':
                            if( palavra == "select"){
                                return new Token(TokenType.SELECT , String.valueOf(palavra)); // retornar token para select
                            }
                            break;
                        case 'r':
                            state = 7;
                            break;
                        case 'u':
                            if( palavra == "use"){
                                return new Token(TokenType.USE , String.valueOf(palavra)); // retornar token para use
                            }
                            break;
                        case 't':
                            state = 3;
                            break;
                        case 'w':
                            if( palavra == "where"){
                                return new Token(TokenType.WHERE , String.valueOf(palavra)); // retornar token para where
                            }
                            break;
                        case 'v':
                            state = 11;
                            break;
                        default:
                            // Definir um erro
                            break;
                    }
                case 1:
                    switch (letra) {
                        case 'u':
                            if( palavra == "auto_increment"){
                                return new Token(TokenType.AUTO_INCREMENT , String.valueOf(palavra)); // retornar token para auto_increment
                            }
                            break;
                        case 'd':
                            if( palavra == "add"){
                                return new Token(TokenType.ADD , String.valueOf(palavra)); // retornar token para add
                            }
                            break;
                        case 'l':
                            if( palavra == "alter"){
                                return new Token(TokenType.ALTER , String.valueOf(palavra)); // retornar token para alter
                            }
                            break;
                        default:
                            // Definir um erro
                            break;
                    }
                case 2:
                    switch (letra) {
                        case 'a':
                            if( palavra == "database"){
                                return new Token(TokenType.DATABASE , String.valueOf(palavra)); // retornar token para database
                            }
                            break;
                        case 'r':
                            if( palavra == "drop"){
                                return new Token(TokenType.DROP , String.valueOf(palavra)); // retornar token para drop
                            }
                            break;
                        case 'e':
                            if( palavra == "delete"){
                                return new Token(TokenType.DELETE , String.valueOf(palavra)); // retornar token para delete
                            }
                            break;
                        default:
                            // Definir um erro
                            break;
                    }
                case 3:
                    switch (letra) {
                        case 'a':
                            if( palavra == "table"){
                                return new Token(TokenType.TABLE , String.valueOf(palavra)); // retornar token para table
                            }
                            break;
                        case 'r':
                            if( palavra == "truncate"){
                                return new Token(TokenType.TRUNCATE , String.valueOf(palavra)); // retornar token para truncate
                            }
                            break;
                        default:
                            // Definir um erro
                            break;
                    }
                case 4:
                    switch (letra) {
                        case 'n':
                            state = 12;
                            break;
                        default:
                            // Definir um erro
                            break;
                    }
                case 5:
                    switch (letra) {
                        case 'u':
                            state = 13;
                            break;
                        case 'o':
                            if( palavra == "not"){
                                return new Token(TokenType.NOT , String.valueOf(palavra)); // retornar token para not
                            }
                            break;
                        default:
                            // Definir um erro
                            break;
                    }
                case 6:
                    switch (letra) {
                        case 'r':
                            if( palavra == "from"){
                                return new Token(TokenType.FROM , String.valueOf(palavra)); // retornar token para from
                            }
                            break;
                        case 'o':
                            if( palavra == "foreign"){
                                return new Token(TokenType.FOREIGN , String.valueOf(palavra)); // retornar token para foreign
                            }
                            break;
                        default:
                            // Definir um erro
                            break;
                    }
                case 7:
                    switch (letra) {
                        case 'e':
                            state = 14;
                            break;
                        default:
                            // Definir um erro
                            break;
                    }
                case 8:
                    switch (letra) {
                        case '=':
                            if( palavra == "<="){
                                return new Token(TokenType.OPERATOR , String.valueOf(palavra)); // retornar token para <=
                            }
                            break;
                        case '>':
                            if( palavra == "<>"){
                                return new Token(TokenType.OPERATOR, String.valueOf(palavra)); // retornar token para <>
                            }
                            break;
                        default:
                            // Definir um erro
                            break;
                    }
                case 9:
                    switch (letra) {
                        case '=':
                            if( palavra == ">="){
                                return new Token(TokenType.OPERATOR, String.valueOf(palavra)); // retornar token para >=
                            }
                            break;
                        default:
                            // Definir um erro
                            break;
                    }
                case 10:
                    switch (letra) {
                        case 'h':
                            if( palavra == "char"){
                                return new Token(TokenType.CHAR, String.valueOf(palavra)); // retornar token para char
                            }
                            break;
                        case 'r':
                            if( palavra == "create"){
                                return new Token(TokenType.CREATE , String.valueOf(palavra)); // retornar token para create
                            }
                            break;
                        default:
                            // Definir um erro
                            break;
                    }
                case 11:
                    switch (letra) {
                        case 'a':
                            state = 15;
                            break;
                        default:
                            // Definir um erro
                            break;
                    }
                case 12:
                    switch (letra) {
                        case 's':
                            if( palavra == "insert"){
                                return new Token(TokenType.INSERT , String.valueOf(palavra)); // retornar token para insert
                            }
                            break;
                        case 't':
                            if( palavra == "into"){
                                return new Token(TokenType.INTO , String.valueOf(palavra)); // retornar token para into
                            }
                            break;
                        default:
                            // Definir um erro
                            break;
                    }
                case 13:
                    switch (letra) {
                        case 'm':
                            if( palavra == "numeric"){
                                return new Token(TokenType.NUMERIC , String.valueOf(palavra)); // retornar token para numeric
                            }
                            break;
                        case 'l':
                            if( palavra == "null"){
                                return new Token(TokenType.NULL , String.valueOf(palavra)); // retornar token para null
                            }
                            break;
                        default:
                            // Definir um erro
                            break;
                    }
                case 14:
                    switch (letra) {
                        case 'f':
                            if( palavra == "references"){
                                return new Token(TokenType.REFERENCES , String.valueOf(palavra)); // retornar token para references
                            }
                            break;
                        case 'n':
                            if( palavra == "rename"){
                                return new Token(TokenType.RENAME , String.valueOf(palavra)); // retornar token para rename
                            }
                            break;
                        default:
                            // Definir um erro
                            break;
                    }
                case 15:
                    switch (letra) {
                        case 'r':
                            if( palavra == "varchar"){
                                return new Token(TokenType.VARCHAR , String.valueOf(palavra)); // retornar token para varchar
                            }
                            break;
                        case 'l':
                            if( palavra == "values"){
                                return new Token(TokenType.VALUES , String.valueOf(palavra)); // retornar token para values
                            }
                            break;
                        default:
                            // Definir um erro
                            break;
                    }
                default:
                    // Definir um erro
                    break;
            } 
        }
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
