package Utils;

import Tokenizer.*;
import Syntaxer.*;
import GrammaticalElement.*;

public class Key {

    private final GrammaticalInterface x;
    private final GrammaticalInterface y;

    public Key(GrammaticalInterface x, GrammaticalInterface y) {
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
