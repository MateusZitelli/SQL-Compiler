package Tokenizer;

/*Nome: Token.java
 *Descricao: Classe responsavel por guardar a estrutura do TOKEN. 
 *Autores: Joao Flavio
 * 
 */

public class Token {
	
	 public TokenType type;
     public Object data;
     
     public Token(TokenType type, Object data) {
         this.type = type;
         this.data = data;
     }

     @Override
     public String toString() {
         return String.format("(%s %s)", type.name(), data);
     }
}
