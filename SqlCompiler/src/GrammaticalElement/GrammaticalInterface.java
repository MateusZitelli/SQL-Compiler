package GrammaticalElement;

import java.util.*;

public interface GrammaticalInterface {
    public boolean isTokenType();
    public boolean isGrammatic(); 
    public boolean isAction();
    public boolean isSynthesized();
    void setAttr(String key, String value);
    String getAttr(String key);
    String name();
    void act(Stack<GrammaticalInterface> stack);
}

