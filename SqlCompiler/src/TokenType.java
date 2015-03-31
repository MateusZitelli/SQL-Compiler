/*Nome: Token.java
 *Descrição: ENUM que definem as expressões regulares que serão reconhecidas. 
 *Autores: João Flávio e...
 * 
 */
public enum TokenType {
	
	digit("-?[0-9]+"),
	espaco("[ \t\f\r\n]+");
	
		
	public String pattern;
	
	private TokenType (String pattern) {
        this.pattern = pattern;
    }
}
