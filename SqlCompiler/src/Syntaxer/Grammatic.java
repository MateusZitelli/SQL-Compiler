package Syntaxer;

import java.util.*;
import Action.Attributable;

public enum Grammatic implements Attributable{
    START, Command, CommandPrime, Create, UDatabase, ATable, DTable, CmdInsert, CmdDelete, CmdSelect, CreatePrime, CDatabase, CTable, ConteudoTabela, Elemento, Coluna, ElementoPrime, DataType, Condition, Stmt, Columns, ColumnValue, ColumnsValue, ColumnsPrime, ColumnsPrimePrime, ColumnValuePrime, ColumnValuePrimePrime, CmdWhere, Whereclausule, Operator, WhereclausulePrime, Logical, Tables, TablesPrime;

    private Map<String, String> attrs = new HashMap<String, String>();

    public String getAttr(String key) {
        return attrs.get(key);
    }

    public void setAttr(String key, String value) {
        attrs.put(key, value);
    }

    private Grammatic(String... attrs) {
        for(String attr: attrs){
            setAttr(attr, null);
        }
    }
}
