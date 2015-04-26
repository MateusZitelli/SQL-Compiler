package Syntaxer;

import java.util.*;

import Tokenizer.*;
import GrammaticalElement.*;
import Action.*;
import Synthesized.*;

class OutputBuffer {
    public static ArrayList<String> buffer = new ArrayList<String>();
    public static int identation = 0;
    public static void addIdentation (){
        identation += 1;
    }
    public static void rmIdentation (){
        identation -= 1;
    }
    public static void add(String output){
        StringBuffer b = new StringBuffer();
        for(int i = 0; i < identation; i++){
            b.append("  ");
        }
        buffer.add(b + output);
        System.out.println(buffer);
    }
}

class PassPackage1Up implements ActionInterface {
    public void getFromParent(GrammaticalInterface parent, Map<String, String> attrs){}
    public void act(Stack<GrammaticalInterface> stack, Map<String, String> attrs){
        stack.get(stack.size() - 1).setAttr("package", attrs.get("package"));
        OutputBuffer.add("package " + attrs.get("package") + ";\n");
    }
}

class PassData2Up implements ActionInterface {
    public void getFromParent(GrammaticalInterface parent, Map<String, String> attrs){}
    public void act(Stack<GrammaticalInterface> stack, Map<String, String> attrs){
        stack.get(stack.size() - 2).setAttr("package", attrs.get("data"));
    }
}

class PassColumnId4Up implements ActionInterface {
    public void getFromParent(GrammaticalInterface parent, Map<String, String> attrs){}
    public void act(Stack<GrammaticalInterface> stack, Map<String, String> attrs){
        stack.get(stack.size() - 4).setAttr("id", attrs.get("data"));
    }
}

class PassDatatypeSize2Up implements ActionInterface {
    public void getFromParent(GrammaticalInterface parent, Map<String, String> attrs){}
    public void act(Stack<GrammaticalInterface> stack, Map<String, String> attrs){
        stack.get(stack.size() - 2).setAttr("size", attrs.get("data"));
    }
}

class PassNumericTypeUp implements ActionInterface {
    public void getFromParent(GrammaticalInterface parent, Map<String, String> attrs){}
    public void act(Stack<GrammaticalInterface> stack, Map<String, String> attrs){
        stack.get(stack.size() - 1).setAttr("type", "numeric");
        stack.get(stack.size() - 1).setAttr("size", attrs.get("size"));
    }
}

class PassVarcharTypeUp implements ActionInterface {
    public void getFromParent(GrammaticalInterface parent, Map<String, String> attrs){}
    public void act(Stack<GrammaticalInterface> stack, Map<String, String> attrs){
        stack.get(stack.size() - 1).setAttr("type", "varchar");
        stack.get(stack.size() - 1).setAttr("size", attrs.get("size"));
    }
}

class PassDataType2Up implements ActionInterface {
    public void getFromParent(GrammaticalInterface parent, Map<String, String> attrs){}
    public void act(Stack<GrammaticalInterface> stack, Map<String, String> attrs){
        stack.get(stack.size() - 2).setAttr("type", attrs.get("type"));
        stack.get(stack.size() - 2).setAttr("size", attrs.get("size"));
    }
}

class AddNotNull2Up implements ActionInterface {
    public void getFromParent(GrammaticalInterface parent, Map<String, String> attrs){}
    public void act(Stack<GrammaticalInterface> stack, Map<String, String> attrs){
        if(stack.get(stack.size() - 2).getAttr("not_null") != null){
            System.out.println("Error: duplicated NOT NULL condition.");
            return;
        }
        stack.get(stack.size() - 2).setAttr("not_null", "true");
    }
}

class AddAutoIncrement2Up implements ActionInterface {
    public void getFromParent(GrammaticalInterface parent, Map<String, String> attrs){}
    public void act(Stack<GrammaticalInterface> stack, Map<String, String> attrs){
        if(stack.get(stack.size() - 2).getAttr("auto_increment") != null){
            System.out.println("Error: duplicated AUTO_INCREMENT condition.");
            return;
        }
        stack.get(stack.size() - 2).setAttr("auto_increment", "true");
    }
}

class AddPrimaryKey2Up implements ActionInterface {
    public void getFromParent(GrammaticalInterface parent, Map<String, String> attrs){}
    public void act(Stack<GrammaticalInterface> stack, Map<String, String> attrs){
        if(stack.get(stack.size() - 2).getAttr("primary_key") != null){
            System.out.println("Error: duplicated PRIMARY KEY condition.");
            return;
        }
        stack.get(stack.size() - 2).setAttr("primary_key", "true");
    }
}

class ShowTable implements ActionInterface {
    public void getFromParent(GrammaticalInterface parent, Map<String, String> attrs){}
    public void act(Stack<GrammaticalInterface> stack, Map<String, String> attrs){
        String name = attrs.get("data");
        String capitalized = Character.toUpperCase(name.charAt(0)) + name.substring(1);
        OutputBuffer.add("@Entity\n");
        OutputBuffer.add("@Table(name=\"" + name + "\")\n");
        OutputBuffer.add("public class " + capitalized + " {\n");
        OutputBuffer.addIdentation();
    }
}

class CommandAct implements ActionInterface{
    public void getFromParent(GrammaticalInterface parent, Map<String, String> attrs){
        attrs.put("package", parent.getAttr("package"));
    }
    public void act(Stack<GrammaticalInterface> stack, Map<String, String> attrs){
        stack.get(stack.size() - 2).setAttr("package", attrs.get("package"));
        stack.get(stack.size() - 1).setAttr("package", attrs.get("package"));
    }
}

class PrintColumn implements ActionInterface{
    public void getFromParent(GrammaticalInterface parent, Map<String, String> attrs){ }
    public void act(Stack<GrammaticalInterface> stack, Map<String, String> attrs){
        String type = attrs.get("type");
        if(attrs.get("primary_key") == "true"){
            OutputBuffer.add("@Id\n");
        }
        if(attrs.get("not_null") == "true"){
            OutputBuffer.add("@Column(nullable = false)\n");
        }else{
            OutputBuffer.add("@Column\n");
        }
        if(attrs.get("auto_increment") == "true"){
            OutputBuffer.add("@GeneratedValue(strategy=GenerationType.AUTO)\n");
        }
        OutputBuffer.add("public ");
        if(type == "numeric"){
            OutputBuffer.add("int ");
        }else if(type == "varchar"){
            OutputBuffer.add("String ");
        }
        OutputBuffer.add(attrs.get("id") + ";\n");
    }
}

class CreateTable implements ActionInterface{
    public void getFromParent(GrammaticalInterface parent, Map<String, String> attrs){ }
    public void act(Stack<GrammaticalInterface> stack, Map<String, String> attrs){
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>> EEEEEEEEEEENNNNDDDDDDDD");
    }
}

class SynthesizedElements {
    public static Synthesized UDatabase = new Synthesized(new PassPackage1Up());
    public static Synthesized UDatabaseUse = new Synthesized(new PassData2Up());
    public static Synthesized TableId = new Synthesized(new ShowTable());
    public static Synthesized ColumnId = new Synthesized(new PassColumnId4Up());
    public static Synthesized Column = new Synthesized(new PrintColumn());
    public static Synthesized DatatypeSize = new Synthesized(new PassDatatypeSize2Up());
    public static Synthesized DatatypeNumeric = new Synthesized(new PassNumericTypeUp());
    public static Synthesized DatatypeVarchar = new Synthesized(new PassVarcharTypeUp());
    public static Synthesized DatatypeInt = new Synthesized(new ShowTable());
    public static Synthesized DataType = new Synthesized(new PassDataType2Up());
    public static Synthesized CreateTable = new Synthesized(new CreateTable());
    public static Synthesized NotNull = new Synthesized(new AddNotNull2Up());
    public static Synthesized PrimaryKey = new Synthesized(new AddPrimaryKey2Up());
    public static Synthesized AutoIncrement = new Synthesized(new AddAutoIncrement2Up());
}

class ActionElements {
    public static Action Command = new Action(new CommandAct());
}

public enum Production {
    StartCommandPrime(Grammatic.CommandPrime),
    CommandPrimeEps(),
    CommandPrimeCommand(ActionElements.Command, Grammatic.Command, Grammatic.CommandPrime),
    CommandCreate(Grammatic.Create),
    CommandUse(Grammatic.UDatabase, SynthesizedElements.UDatabase),
    CommandAlter(Grammatic.ATable),
    CommandDrop(Grammatic.DTable),
    CommandTruncate(Grammatic.DTable),
    CommandInsert(Grammatic.CmdInsert),
    CommandDelete(Grammatic.CmdDelete),
    CommandSelect(Grammatic.CmdSelect),    
    CreateCreate(TokenType.CREATE, Grammatic.CreatePrime),
    UDatabaseUse(TokenType.USE, TokenType.id, SynthesizedElements.UDatabaseUse, TokenType.END_STATEMENT),
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
    CTableTable(TokenType.TABLE, TokenType.id, SynthesizedElements.TableId, Grammatic.ConteudoTabela, SynthesizedElements.CreateTable),
    ConteudoTabelaOPEN_PARENTHESIS(TokenType.OPEN_PARENTHESIS, Grammatic.Elemento, TokenType.CLOSE_PARENTHESIS),
    ElementoId(Grammatic.Coluna, SynthesizedElements.Column, Grammatic.ElementoPrime),
    ColunaId(TokenType.id, SynthesizedElements.ColumnId, Grammatic.DataType, SynthesizedElements.DataType, Grammatic.Condition),
    ElementoPrimeCLOSE_PARENTHESIS(),
    ElementoPrimeComma(TokenType.COMMA, Grammatic.Elemento),
    DataTypeInt(TokenType.integer, SynthesizedElements.DatatypeInt),
    DataTypeNumeric(TokenType.NUMERIC, TokenType.OPEN_PARENTHESIS, TokenType.integer, SynthesizedElements.DatatypeSize, TokenType.CLOSE_PARENTHESIS, SynthesizedElements.DatatypeNumeric),
    DataTypeChar(TokenType.CHAR, TokenType.OPEN_PARENTHESIS, TokenType.integer, TokenType.CLOSE_PARENTHESIS),
    DataTypeVarchar(TokenType.VARCHAR, TokenType.OPEN_PARENTHESIS, TokenType.integer, SynthesizedElements.DatatypeSize, TokenType.CLOSE_PARENTHESIS, SynthesizedElements.DatatypeVarchar),
    DataTypeDate(TokenType.date),
    ConditionCLOSE_PARENTHESIS(),
    ConditionComma(),
    ConditionNot(TokenType.NOT, TokenType.NULL, SynthesizedElements.NotNull, Grammatic.Condition),
    ConditionPrimary(TokenType.PRIMARY, TokenType.KEY, SynthesizedElements.PrimaryKey, Grammatic.Condition),
    ConditionAuto_increment(TokenType.AUTO_INCREMENT, SynthesizedElements.AutoIncrement, Grammatic.Condition),
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
