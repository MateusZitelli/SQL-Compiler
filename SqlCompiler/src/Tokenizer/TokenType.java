package Tokenizer;

/*Nome: TokenType.java
 *Descricao: ENUM que definem as expressoes regulares que serao reconhecidas. 
 *Autores: Joao Flavio e Mateus Zitelli
 * 
 */
import java.util.*;

import GrammaticalElement.GrammaticalInterface;

public enum TokenType implements GrammaticalInterface {
    // The order of the tokens implies in precedence
    CREATE(), 
    DATABASE(), 
    ALTER(), 
    ADD(),
    RENAME(),
    MODIFY(),
    DROP(), 
    TRUNCATE(), 
    TABLE(),
    SELECT(),
    DELETE(),
    INSERT(),
    INTO(),
    VALUES(),
    WHERE(),
    FROM(),
    USE(), 
    NOT(), 
    NULL(), 
    PRIMARY(), 
    FOREIGN(), 
    KEY(), 
    AUTO_INCREMENT(), 
    REFERENCES(), 
    OPEN_PARENTHESIS(), 
    CLOSE_PARENTHESIS(), 
    QUOTE(), 
    OPERATOR(),
    IN(),
    LOGICAL(),
    STAR(),
    COMMA(),
    END_STATEMENT(), 
    EOF(),
    date(),
    NUMERIC(),
    CHAR(),
    VARCHAR(),
    integer(),
    number(),
    id(),
    ERROR();

    private Map<String, String> attrs;

    public boolean isTokenType() {
        return true;
    }

    public boolean isGrammatic() {
        return false;
    }

    public boolean isAction () {
        return false;
    }
    
    public boolean isSynthesized () {
        return false;
    }

    public String getAttr(String key) {
        return attrs.get(key);
    }

    public void setAttr(String key, String value) {
        attrs.put(key, value);
    }

    private TokenType(String... attrs) {
        this.attrs = new HashMap<String, String>();

        for(String attr: attrs){
            setAttr(attr, null);
        }
    }
}
