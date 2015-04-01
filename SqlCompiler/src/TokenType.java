/*Nome: TokenType.java
 *Descricao: ENUM que definem as expressoes regulares que serao reconhecidas. 
 *Autores: Joao Flavio e Mateus Zitelli
 * 
 */
public enum TokenType {
	// The order of the tokens implies in precedence
	CREATE("^(?i)(create)$"), 
    DATABASE("^(?i)(database)$"), 
    ALTER("^(?i)(alter)$"), 
    ADD("^(?i)(add)$"),
    RENAME("^(?i)(rename)$"),
    MODIFY("^(?i)(modify)$"),
    DROP("^(?i)(drop)$"), 
    TRUNCATE("^(?i)(truncate)$"), 
    TABLE("^(?i)(table)$"),
    SELECT("^(?i)(select)$"),
    DELETE("^(?i)(delete)$"),
    INSERT("^(?i)(insert)$"),
    INTO("^(?i)(into)$"),
    VALUES("^(?i)(values)$"),
    WHERE("^(?i)(where)$"),
    FROM("^(?i)(from)$"),
    USE("^(?i)(use)$"), 
    NOT("^(?i)(not)$"), 
    NULL("^(?i)(null)$"), 
    PRIMARY("^(?i)(primary)$"), 
    FOREIGN("^(?i)(foreign)$"), 
    KEY("^(?i)(key)$"), 
    AUTO_INCREMENT("^(?i)(auto_increment)$"), 
    REFERENCES("^(?i)(references)$"), 
    OPEN_PARENTHESIS("^\\($"), 
    CLOSE_PARENTHESIS("^\\)$"), 
    END_STATEMENT("^;$"), 
    date("^(((\\d{4})(-)(0[13578]|10|12)(-)(0[1-9]|[12][0-9]|3[01]))|((\\d{4})(-)(0[469]|11)(-)([0][1-9]|[12][0-9]|30))|((\\d{4})(-)(02)(-)(0[1-9]|1[0-9]|2[0-8]))|(([02468][048]00)(-)(02)(-)(29))|(([13579][26]00)(-)(02)(-)(29))|(([0-9][0-9][0][48])(-)(02)(-)(29))|(([0-9][0-9][2468][048])(-)(02)(-)(29))|(([0-9][0-9][13579][26])(-)(02)(-)(29)))(\\s([0-1][0-9]|2[0-4]):([0-5][0-9]):([0-5][0-9]))?$"),
	number("^(-?[0-9]+(?:[0-9]+)?)$"),
    id("^([a-zA-Z0-9]+)$");
    
    //ADD("^(?i)(add)$"),
    //DROP("^(?i)(drop)$"),
    //RENAME("^(?i)(rename)$"),
    //MODIFY("^(?i)(modify)$"),
    //TRUNCATE("^(?i)(truncate)$"),
    //INSERT("^(?i)(insert)$"),
    //INTO("^(?i)(into)$")
    //VALUES("^(?i)(values)$"),
    //DELETE("^(?i)(delete)$"),
	
		
	public String pattern;
	
	private TokenType (String pattern) {
        this.pattern = pattern;
    }
}
