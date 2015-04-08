import java.util.Arrays;
import Tokenizer.*;

public enum Production {
    Elemento(Grammatic.Coluna, Grammatic.ElementoPrime),
    // Representa Elemento' -> vazio
    ElementoPrime0(/*vazio*/),
    // Representa Elemento' -> virgula Elemento
    ElementoPrime1(TokenType.COMMA, Grammatic.Elemento);
    
    Object[] productions;

    private Production(Object... productions) {
        this.productions = productions;
    }
}
