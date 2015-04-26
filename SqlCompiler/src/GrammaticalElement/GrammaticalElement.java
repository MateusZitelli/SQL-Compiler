package GrammaticalElement;

import java.util.*;

import Tokenizer.TokenType;
import Syntaxer.Grammatic;

public class GrammaticalElement implements GrammaticalInterface {
    private boolean tokenType;
    private boolean grammatic;
    private boolean synthesized;

    private Map<String, String> attrs;
    public ActionInterface action;

    public boolean isTokenType() {
        return tokenType;
    }

    public boolean isGrammatic() {
        return grammatic;
    }

    public boolean isAction () {
        return action != null;
    }

    public boolean isSynthesized () {
        return synthesized;
    }

    public String getAttr(String key) {
        return attrs.get(key);
    }

    public void setAttr(String key, String value) {
        attrs.put(key, value);
    }

    public void act(Stack<GrammaticalInterface> stack) {
        action.act(stack, attrs);
    }

    public void getFromParent(GrammaticalInterface parent) {
        action.getFromParent(parent, attrs);
    }

    public String name() {
        return "GrammaticalElement";
    }

    public void cleanAttrs() {
        attrs = new HashMap<String, String>();
    }

    public GrammaticalElement (){
        attrs = new HashMap<String, String>();
        this.action = null;
    }
    
    public GrammaticalElement (ActionInterface action){
        attrs = new HashMap<String, String>();
        this.action = action;
    }
}
