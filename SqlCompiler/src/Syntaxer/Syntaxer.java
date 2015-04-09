import Tokenizer.*;

public class Syntaxer { 
    public static void test() {
        ParsingTable t = new ParsingTable();
        System.out.println(t.getRule(TokenType.COMMA, Grammatic.ElementoPrime));
    }
}
