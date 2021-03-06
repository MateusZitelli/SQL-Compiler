package Syntaxer;

import java.util.*;
import GrammaticalElement.*;

public enum Grammatic implements GrammaticalInterface {
    START, Command, CommandPrime, Create, UDatabase, ATable, DTable, CmdInsert, CmdDelete, CmdSelect, CreatePrime, CDatabase, CTable, ConteudoTabela, Elemento, Coluna, ElementoPrime, DataType, Condition, Stmt, Columns, ColumnValue, ColumnsValue, ColumnsPrime, ColumnsPrimePrime, ColumnValuePrime, ColumnValuePrimePrime, CmdWhere, Whereclausule, Operator, WhereclausulePrime, Logical, Tables, TablesPrime;

    public ActionInterface action;

    public boolean isTokenType() {
        return false;
    }

    public boolean isGrammatic() {
        return true;
    }

    public boolean isAction () {
        return false;
    }
    
    public boolean isSynthesized () {
        return false;
    }

    public void getFromParent(GrammaticalInterface parent) {
        if(action != null)
            action.getFromParent(parent, this.attrs);
    }

    public void act(Stack<GrammaticalInterface> stack) {
        if(action != null)
            action.act(stack, attrs);
    }

    private Map<String, String> attrs = new HashMap<String, String>();

    public String getAttr(String key) {
        return attrs.get(key);
    }

    public void setAttr(String key, String value) {
        attrs.put(key, value);
    }

    private Grammatic(ActionInterface action, String... attrs) {
        this.attrs = new HashMap<String, String>();
        for(String attr: attrs){
            setAttr(attr, null);
        }
    }

    private Grammatic(String... attrs) {
        this.attrs = new HashMap<String, String>();
        for(String attr: attrs){
            setAttr(attr, null);
        }
    }
}
