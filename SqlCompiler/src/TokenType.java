/*Nome: Token.java
 *Descri��o: ENUM que definem as express�es regulares que ser�o reconhecidas. 
 *Autores: Jo�o Fl�vio e...
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
