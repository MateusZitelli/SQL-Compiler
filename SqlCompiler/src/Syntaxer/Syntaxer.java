import java.util.Stack;
import java.util.ArrayList;
import Tokenizer.*;

public class Syntaxer { 
    public static ParsingTable table;
    table.setRule(TokenType.CREATE, Grammatic.START, Production.StartCreate);
    table.setRule(TokenType.USE, Grammatic.START, Production.StartUse);
    table.setRule(TokenType.ALTER, Grammatic.START, Production.StartAlter);
    table.setRule(TokenType.DROP, Grammatic.START, Production.StartDrop);
    table.setRule(TokenType.TRUNCATE, Grammatic.START, Production.StartTruncate);
    table.setRule(TokenType.INSERT, Grammatic.START, Production.StartInsert);
    table.setRule(TokenType.DELETE, Grammatic.START, Production.StartDelete);
    table.setRule(TokenType.SELECT, Grammatic.START, Production.StartSelect);
    table.setRule(TokenType.CREATE, Grammatic.Create, Production.CreateCreate);
    table.setRule(TokenType.USE, Grammatic.UDatabase, Production.UDatabaseUse);
    table.setRule(TokenType.ALTER, Grammatic.ATable, Production.ATableAlter);
    table.setRule(TokenType.DROP, Grammatic.DTable, Production.DTableDrop);
    table.setRule(TokenType.TRUNCATE, Grammatic.DTable, Production.DTableTruncate);
    table.setRule(TokenType.INSERT, Grammatic.CmdInsert, Production.CmdInsertInsert);
    table.setRule(TokenType.DELETE, Grammatic.CmdDelete, Production.CmdDeleteDelete);
    table.setRule(TokenType.SELECT, Grammatic.CmdSelect, Production.CmdSelectSelect);
    table.setRule(TokenType.DATABASE, Grammatic.CreatePrime, Production.CreatePrimeDatabase);
    table.setRule(TokenType.id, Grammatic.CreatePrime, Production.CreatePrimeId);
    table.setRule(TokenType.DATABASE, Grammatic.CDatabase, Production.CDatabaseDatabase);
    table.setRule(TokenType.id, Grammatic.CTable, Production.CTableId);
    table.setRule(TokenType.OPEN_PARENTHESIS, Grammatic.ConteudoTabela, Production.ConteudoTabelaOPEN_PARENTHESIS);
    table.setRule(TokenType.id, Grammatic.Elemento, Production.ElementoId);
    table.setRule(TokenType.id, Grammatic.Coluna, Production.ColunaId);
    table.setRule(TokenType.CLOSE_PARENTHEIS, Grammatic.ElementoPrime, Production.ElementoPrimeCLOSE_PARENTHESIS);
    table.setRule(TokenType.COMMA, Grammatic.ElementoPrime, Production.ElementoPrimeComma);
    table.setRule(TokenType.INT, Grammatic.DataType, Production.DataTypeInt);
    table.setRule(TokenType.NUMERIC, Grammatic.DataType, Production.DataTypeNumeric);
    table.setRule(TokenType.CHAR, Grammatic.DataType, Production.DataTypeChar);
    table.setRule(TokenType.VARCHAR, Grammatic.DataType, Production.DataTypeVarchar);
    table.setRule(TokenType.date, Grammatic.DataType, Production.DataTypeDate);
    table.setRule(TokenType.CLOSE_PARENTHESIS, Grammatic.Condition, Production.ConditionCLOSE_PARENTHESIS);
    table.setRule(TokenType.COMMA, Grammatic.Condition, Production.ConditionComma);
    table.setRule(TokenType.NOT, Grammatic.Condition, Production.ConditionNot);
    table.setRule(TokenType.PRIMARY, Grammatic.Condition, Production.ConditionPrimary);
    table.setRule(TokenType.AUTO_INCREMENT, Grammatic.Condition, Production.ConditionAuto_increment);
    table.setRule(TokenType.FOREIGN, Grammatic.Condition, Production.ConditionForeign);
    table.setRule(TokenType.ADD, Grammatic.Stmt, Production.StmtAdd);
    table.setRule(TokenType.DROP, Grammatic.Stmt, Production.StmtDrop);
    table.setRule(TokenType.RENAME, Grammatic.Stmt, Production.StmtRename);
    table.setRule(TokenType.MODIFY, Grammatic.Stmt, Production.StmtModify);
    table.setRule(TokenType.id, Grammatic.Columns, Production.ColumnsId);
    table.setRule(TokenType.STAR, Grammatic.Columns, Production.ColumnsSTAR);
    table.setRule(TokenType.QUOTE, Grammatic.ColumnValue, Production.ColumnValueQUOTE);
    table.setRule(TokenType.number, Grammatic.ColumnValue, Production.ColumnValueNumber);
    table.setRule(TokenType.id, Grammatic.ColumnsPrime, Production.ColumnsPrimeId);
    table.setRule(TokenType.CLOSE_PARENTHESIS, Grammatic.ColumnsPrimePrime, Production.ColumnsPrimePrimeCLOSE_PARENTHESIS);
    table.setRule(TokenType.COMMA, Grammatic.ColumnsPrimePrime, Production.ColumnsPrimePrimeComma);
    table.setRule(TokenType.FROM, Grammatic.ColumnsPrimePrime, Production.ColumnsPrimePrimeFrom);
    table.setRule(TokenType.CLOSE_PARENTHESIS, Grammatic.ColumnValuePrime, Production.ColumnValuePrimeCLOSE_PARENTHESIS);
    table.setRule(TokenType.COMMA, Grammatic.ColumnValuePrime, Production.ColumnValuePrimeComma);
    table.setRule(TokenType.CLOSE_PARENTHESIS, Grammatic.ColumnValuePrimePrime, Production.ColumnValuePrimePrimeCLOSE_PARENTHESIS);
    table.setRule(TokenType.COMMA, Grammatic.ColumnValuePrimePrime, Production.ColumnValuePrimePrimeComma);
    table.setRule(TokenType.END_STATEMENT, Grammatic.CmdWhere, Production.CmdWhereEND_STATEMENT);
    table.setRule(TokenType.WHERE, Grammatic.CmdWhere, Production.CmdWhereWhere);
    table.setRule(TokenType.id, Grammatic.Whereclausule, Production.WhereclausuleId);
    table.setRule(TokenType.OPERATOR, Grammatic.Operator, Production.Operator);
    table.setRule(TokenType.END_STATEMENT, Grammatic.WhereclausulePrime, Production.WhereclausulePrimeEND_STATEMENT);
    table.setRule(TokenType.AND, Grammatic.WhereclausulePrime, Production.WhereclausulePrimeAnd);
    table.setRule(TokenType.OR, Grammatic.WhereclausulePrime, Production.WhereclausulePrimeOr);
    table.setRule(TokenType.AND, Grammatic.Logical, Production.LogicalAnd);
    table.setRule(TokenType.OR, Grammatic.Logical, Production.LogicalOr);
    table.setRule(TokenType.id, Grammatic.Tables, Production.TablesId);
    table.setRule(TokenType.AND, Grammatic.WhereclausulePrime, Production.WhereclausulePrimeAnd);
    table.setRule(TokenType.END_STATEMENT, Grammatic.TablesPrime, Production.TablesPrimeEND_STATEMENT);
    table.setRule(TokenType.Comma, Grammatic.TablesPrime, Production.TablesPrimeComma);
    table.setRule(TokenType.WHERE, Grammatic.TablesPrime, Production.TablesPrimeWhere);
    public static Stack<Object> stack = new Stack<Object>();

    public static void analyze(ArrayList<Token> tokensList) {
        int position = 0;
        Token token;
        Production rule;
        Object expectedInput;

        while(stack.size() > 0) {
            token = tokensList.get(position); 
            expectedInput = stack.pop(); 
            if(expectedInput instanceof TokenType){
                if(expectedInput == token.type){
                    position += 1; 
                    System.out.print("pop ");
                    System.out.println(token);
                    if(expectedInput == TokenType.EOF){
                        System.out.println("Accepted");
                    }
                }else{
                    System.out.print("Bad input: ");
                    System.out.print(token);
                    System.out.print(" expected: ");
                    System.out.println(expectedInput);
                    return;
                }
            }else if(expectedInput instanceof Grammatic){
                rule = table.getRule(token.type, (Grammatic)expectedInput); 
                if(rule == null){
                    System.out.print("Bad input: ");
                    System.out.println(token);
                    return;
                }
                System.out.print("Rule: ");
                System.out.println(rule);
                // Stack expected instructions
                for(int i = rule.productions.length - 1; i >= 0; i--){
                    stack.push(rule.productions[i]);
                }
            }
            System.out.print("Stack: ");
            System.out.println(stack);
        }
    }

    public Syntaxer() {
        this.table = new ParsingTable();
        stack.push(TokenType.EOF); 
        stack.push(Grammatic.START);
    }
}
