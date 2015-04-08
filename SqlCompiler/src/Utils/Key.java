import Tokenizer.*;
import Syntaxer.*;

public class Key {

    private final TokenType x;
    private final Grammatic y;

    public Key(TokenType x, Grammatic y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Key)) return false;
        Key key = (Key) o;
        return x == key.x && y == key.y;
    }

    @Override
    public int hashCode() {
        String result = x.toString().concat(y.toString());
        return result.hashCode();
    }

}
