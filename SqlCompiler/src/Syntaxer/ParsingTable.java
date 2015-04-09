import java.util.*;
import Tokenizer.*;
import Utils.*;

public class ParsingTable {
    public static Map<Key, Production> table = new HashMap<Key, Production>();

    public static void setRule(TokenType tokenType, Grammatic grammatic, Production production) {
        // Map (tokenType, grammatic) -> production
        table.put(new Key(tokenType, grammatic), production);
    }

    public static Production getRule(TokenType tokenType, Grammatic grammatic) {
        // Get rule at (tokenType, grammatic)
        return table.get(new Key(tokenType, grammatic));
    }

    public ParsingTable () {
        // Add at column with token COMMA and line with rule ElementoPrime the production ElementoPrime1 
        setRule(TokenType.COMMA, Grammatic.ElementoPrime, Production.ElementoPrime1); 
    }
}
