/*Nome: TokenType.java
 *Descrição: ENUM que definem as expressões regulares que serão reconhecidas. 
 *Autores: João Flávio e...
 * 
 */
public enum TokenType {
	
	number("-?[0-9]+(.[0-9])"),
	espaco("[ \t\f\r\n]+");
		
	public String pattern;
	
	private TokenType (String pattern) {
        this.pattern = pattern;
    }
}
