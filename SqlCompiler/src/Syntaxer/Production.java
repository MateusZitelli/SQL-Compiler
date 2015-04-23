package Syntaxer;

import java.util.*;

import Tokenizer.*;
import GrammaticalElement.*;
import Action.*;
import Synthesized.*;

class FinalizeAct implements ActionInterface {
    public void act(Stack<GrammaticalInterface> stack, Map<String, String> attrs){
        System.out.println("############## Synthesized #################");
        System.out.println("Printa o atributo data recebido do token anterior");
        System.out.println(attrs.get("data"));
        System.out.println("Passa ele para o elemento a cima na stack");
        stack.get(stack.size() - 1).setAttr("id", attrs.get("data"));
        System.out.println("############################################");
    }
}

class PassUpAct implements ActionInterface {
    public void act(Stack<GrammaticalInterface> stack, Map<String, String> attrs){
        System.out.println("############## Action #################");
        System.out.println("Passou informações para o prox. elemento da stack");
        stack.get(stack.size() - 1).setAttr("code", "mkdir");
        System.out.println("############################################");
    }
}

class SynthesizedElements {
    public static Synthesized Print = new Synthesized(new FinalizeAct()); 
}

class ActionElements {
    public static Action passUp = new Action(new PassUpAct()); 
}

public enum Production {
    StartCommandPrime(Grammatic.CommandPrime, SynthesizedElements.Print),
    CommandPrimeEps(),
    CommandPrimeCommand(Grammatic.Command, Grammatic.CommandPrime),
    CommandCreate(Grammatic.Create),
    CommandUse(Grammatic.UDatabase),
    CommandAlter(Grammatic.ATable),
    CommandDrop(Grammatic.DTable),
    CommandTruncate(Grammatic.DTable),
    CommandInsert(Grammatic.CmdInsert),
    CommandDelete(Grammatic.CmdDelete),
    CommandSelect(Grammatic.CmdSelect),    
    CreateCreate(TokenType.CREATE, Grammatic.CreatePrime),
    UDatabaseUse(TokenType.USE, TokenType.id, TokenType.END_STATEMENT),
    ATableAlter(TokenType.ALTER, TokenType.TABLE, TokenType.id, Grammatic.Stmt),
    DTableDrop(TokenType.DROP, TokenType.TABLE, TokenType.id, TokenType.END_STATEMENT),
    DTableTruncate(TokenType.TRUNCATE, TokenType.TABLE, TokenType.id, TokenType.END_STATEMENT),
    CmdInsertInsert(TokenType.INSERT, TokenType.INTO, TokenType.id, TokenType.OPEN_PARENTHESIS, Grammatic.Columns, TokenType.CLOSE_PARENTHESIS, TokenType.VALUES, TokenType.OPEN_PARENTHESIS, Grammatic.ColumnValue, TokenType.CLOSE_PARENTHESIS, TokenType.END_STATEMENT),
    CmdDeleteDelete(TokenType.DELETE, TokenType.FROM, TokenType.id, Grammatic.CmdWhere, TokenType.END_STATEMENT),
    CmdSelectSelect(TokenType.SELECT, Grammatic.Columns, TokenType.FROM, Grammatic.Tables, Grammatic.CmdWhere, TokenType.END_STATEMENT),
    CreatePrimeDatabase(Grammatic.CDatabase),
    CreatePrimeTable(Grammatic.CTable),
    CreatePrimeId(Grammatic.CTable),
    CDatabaseDatabase(TokenType.DATABASE, TokenType.id, TokenType.END_STATEMENT),
                                // Olha a action aqui                 // Elemento sinthesized
    CTableTable(TokenType.TABLE, ActionElements.passUp, TokenType.id, SynthesizedElements.Print, Grammatic.ConteudoTabela),
    ConteudoTabelaOPEN_PARENTHESIS(TokenType.OPEN_PARENTHESIS, Grammatic.Elemento, TokenType.CLOSE_PARENTHESIS),
    ElementoId(Grammatic.Coluna, Grammatic.ElementoPrime),
    ColunaId(TokenType.id, Grammatic.DataType, Grammatic.Condition),
    ElementoPrimeCLOSE_PARENTHESIS(),
    ElementoPrimeComma(TokenType.COMMA, Grammatic.Elemento),
    DataTypeInt(TokenType.integer),
    DataTypeNumeric(TokenType.NUMERIC, TokenType.OPEN_PARENTHESIS, TokenType.integer, TokenType.CLOSE_PARENTHESIS),
    DataTypeChar(TokenType.CHAR, TokenType.OPEN_PARENTHESIS, TokenType.integer, TokenType.CLOSE_PARENTHESIS),
    DataTypeVarchar(TokenType.VARCHAR, TokenType.OPEN_PARENTHESIS, TokenType.integer, TokenType.CLOSE_PARENTHESIS),
    DataTypeDate(TokenType.date),
    ConditionCLOSE_PARENTHESIS(),
    ConditionComma(),
    ConditionNot(TokenType.NOT, TokenType.NULL, Grammatic.Condition),
    ConditionPrimary(TokenType.PRIMARY, TokenType.KEY, Grammatic.Condition),
    ConditionAuto_increment(TokenType.AUTO_INCREMENT, Grammatic.Condition),
    ConditionForeign(TokenType.FOREIGN, TokenType.KEY, TokenType.OPEN_PARENTHESIS, TokenType.id, TokenType.CLOSE_PARENTHESIS, TokenType.REFERENCES, TokenType.id, TokenType.OPEN_PARENTHESIS, TokenType.id, TokenType.CLOSE_PARENTHESIS, Grammatic.Condition),    
    StmtAdd(TokenType.ADD, Grammatic.ConteudoTabela),
    StmtDrop(TokenType.DROP, Grammatic.ConteudoTabela),
    StmtRename(TokenType.RENAME, Grammatic.ConteudoTabela),
    StmtModify(TokenType.MODIFY, Grammatic.ConteudoTabela),
    ColumnsId(Grammatic.ColumnsPrime),
    ColumnsSTAR(TokenType.STAR),
    ColumnValueQUOTE(TokenType.QUOTE, TokenType.id, TokenType.QUOTE, Grammatic.ColumnValuePrime),
    ColumnValueNumber(TokenType.number, Grammatic.ColumnValuePrimePrime),
    ColumnValueInteger(TokenType.integer, Grammatic.ColumnValuePrimePrime),
    ColumnsPrimeId(TokenType.id, Grammatic.ColumnsPrimePrime),
    ColumnsPrimePrimeCLOSE_PARENTHESIS(),
    ColumnsPrimePrimeComma(TokenType.COMMA, Grammatic.ColumnsPrime),
    ColumnsPrimePrimeFROM(),
    ColumnValuePrimeCLOSE_PARENTHESIS(),
    ColumnValuePrimeComma(TokenType.COMMA, Grammatic.ColumnValue),
    ColumnValuePrimePrimeCLOSE_PARENTHESIS(),
    ColumnValuePrimePrimeComma(TokenType.COMMA, Grammatic.ColumnValue),
    CmdWhereEND_STATEMENT(),
    CmdWhereWhere(TokenType.WHERE, Grammatic.Whereclausule),
    WhereclausuleId(TokenType.id, Grammatic.Operator, TokenType.id, Grammatic.WhereclausulePrime),
    Operator(TokenType.OPERATOR),
    WhereclausulePrimeEND_STATEMENT(),
    WhereclausulePrime(Grammatic.Logical, Grammatic.Whereclausule),
    Logical(TokenType.LOGICAL),
    TablesId(TokenType.id, Grammatic.TablesPrime),
    TablesPrimeEND_STATEMENT(),
    TablesPrimeComma(TokenType.COMMA, Grammatic.Tables),
    TablesPrimeWhere();
    
    GrammaticalInterface[] productions;

    private Production(GrammaticalInterface... productions) {
        this.productions = productions;
    }
}
