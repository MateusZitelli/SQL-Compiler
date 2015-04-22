package Tokenizer;

/*Nome: TokenType.java
 *Descricao: ENUM que definem as expressoes regulares que serao reconhecidas. 
 *Autores: Joao Flavio e Mateus Zitelli
 * 
 */
import java.util.regex.Pattern;

public enum TokenType {
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
}
