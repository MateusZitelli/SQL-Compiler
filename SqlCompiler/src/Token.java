/*Nome: Token.java
 *Descrição: Classe responsável por guardar a estrutura do TOKEN. 
 *Autores: João Flávio e...
 * 
 */

public class Token {
	
	 public TokenType type;
     public String data;
     
     public Token(TokenType type, String data) {
         this.type = type;
         this.data = data;
     }

     @Override
     public String toString() {
         return String.format("(%s %s)", type.name(), data);
     }
}
