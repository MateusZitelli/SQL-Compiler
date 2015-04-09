import java.util.*;
import Tokenizer.*;
import Utils.*;

public class ParsingTable {
    public static Map<Key, Production> table = new HashMap<Key, Production>();

    public static void setRule(TokenType tokenType, Grammatic grammatic, Production production) {
        // Map (tokenType, grammatic) -> production
        table.put(new Key(tokenType, grammatic), production);
    }

    public static Production getRule(TokenType tokenType, Grammatic grammatic) {
        // Get rule at (tokenType, grammatic)
        return table.get(new Key(tokenType, grammatic));
    }

    public ParsingTable () {
        // Add at column with token COMMA and line with rule ElementoPrime the production ElementoPrime1 
        setRule(TokenType.CREATE, Grammatic.START, Production.StartCreate);
        setRule(TokenType.USE, Grammatic.START, Production.StartUse);
        setRule(TokenType.ALTER, Grammatic.START, Production.StartAlter);
        setRule(TokenType.DROP, Grammatic.START, Production.StartDrop);
        setRule(TokenType.TRUNCATE, Grammatic.START, Production.StartTruncate);
        setRule(TokenType.INSERT, Grammatic.START, Production.StartInsert);
        setRule(TokenType.DELETE, Grammatic.START, Production.StartDelete);
        setRule(TokenType.SELECT, Grammatic.START, Production.StartSelect);
        setRule(TokenType.CREATE, Grammatic.Create, Production.CreateCreate);
        setRule(TokenType.USE, Grammatic.UDatabase, Production.UDatabaseUse);
        setRule(TokenType.ALTER, Grammatic.ATable, Production.ATableAlter);
        setRule(TokenType.DROP, Grammatic.DTable, Production.DTableDrop);
        setRule(TokenType.TRUNCATE, Grammatic.DTable, Production.DTableTruncate);
        setRule(TokenType.INSERT, Grammatic.CmdInsert, Production.CmdInsertInsert);
        setRule(TokenType.DELETE, Grammatic.CmdDelete, Production.CmdDeleteDelete);
        setRule(TokenType.SELECT, Grammatic.CmdSelect, Production.CmdSelectSelect);
        setRule(TokenType.DATABASE, Grammatic.CreatePrime, Production.CreatePrimeDatabase);
        setRule(TokenType.id, Grammatic.CreatePrime, Production.CreatePrimeId);
        setRule(TokenType.DATABASE, Grammatic.CDatabase, Production.CDatabaseDatabase);
        setRule(TokenType.id, Grammatic.CTable, Production.CTableId);
        setRule(TokenType.OPEN_PARENTHESIS, Grammatic.ConteudoTabela, Production.ConteudoTabelaOPEN_PARENTHESIS);
        setRule(TokenType.id, Grammatic.Elemento, Production.ElementoId);
        setRule(TokenType.id, Grammatic.Coluna, Production.ColunaId);
        setRule(TokenType.CLOSE_PARENTHESIS, Grammatic.ElementoPrime, Production.ElementoPrimeCLOSE_PARENTHESIS);
        setRule(TokenType.COMMA, Grammatic.ElementoPrime, Production.ElementoPrimeComma);
        setRule(TokenType.integer, Grammatic.DataType, Production.DataTypeInt);
        setRule(TokenType.NUMERIC, Grammatic.DataType, Production.DataTypeNumeric);
        setRule(TokenType.CHAR, Grammatic.DataType, Production.DataTypeChar);
        setRule(TokenType.VARCHAR, Grammatic.DataType, Production.DataTypeVarchar);
        setRule(TokenType.date, Grammatic.DataType, Production.DataTypeDate);
        setRule(TokenType.CLOSE_PARENTHESIS, Grammatic.Condition, Production.ConditionCLOSE_PARENTHESIS);
        setRule(TokenType.COMMA, Grammatic.Condition, Production.ConditionComma);
        setRule(TokenType.NOT, Grammatic.Condition, Production.ConditionNot);
        setRule(TokenType.PRIMARY, Grammatic.Condition, Production.ConditionPrimary);
        setRule(TokenType.AUTO_INCREMENT, Grammatic.Condition, Production.ConditionAuto_increment);
        setRule(TokenType.FOREIGN, Grammatic.Condition, Production.ConditionForeign);
        setRule(TokenType.ADD, Grammatic.Stmt, Production.StmtAdd);
        setRule(TokenType.DROP, Grammatic.Stmt, Production.StmtDrop);
        setRule(TokenType.RENAME, Grammatic.Stmt, Production.StmtRename);
        setRule(TokenType.MODIFY, Grammatic.Stmt, Production.StmtModify);
        setRule(TokenType.id, Grammatic.Columns, Production.ColumnsId);
        setRule(TokenType.STAR, Grammatic.Columns, Production.ColumnsSTAR);
        setRule(TokenType.QUOTE, Grammatic.ColumnValue, Production.ColumnValueQUOTE);
        setRule(TokenType.number, Grammatic.ColumnValue, Production.ColumnValueNumber);
        setRule(TokenType.id, Grammatic.ColumnsPrime, Production.ColumnsPrimeId);
        setRule(TokenType.CLOSE_PARENTHESIS, Grammatic.ColumnsPrimePrime, Production.ColumnsPrimePrimeCLOSE_PARENTHESIS);
        setRule(TokenType.COMMA, Grammatic.ColumnsPrimePrime, Production.ColumnsPrimePrimeComma);
        setRule(TokenType.FROM, Grammatic.ColumnsPrimePrime, Production.ColumnsPrimePrimeFROM);
        setRule(TokenType.CLOSE_PARENTHESIS, Grammatic.ColumnValuePrime, Production.ColumnValuePrimeCLOSE_PARENTHESIS);
        setRule(TokenType.COMMA, Grammatic.ColumnValuePrime, Production.ColumnValuePrimeComma);
        setRule(TokenType.CLOSE_PARENTHESIS, Grammatic.ColumnValuePrimePrime, Production.ColumnValuePrimePrimeCLOSE_PARENTHESIS);
        setRule(TokenType.COMMA, Grammatic.ColumnValuePrimePrime, Production.ColumnValuePrimePrimeComma);
        setRule(TokenType.END_STATEMENT, Grammatic.CmdWhere, Production.CmdWhereEND_STATEMENT);
        setRule(TokenType.WHERE, Grammatic.CmdWhere, Production.CmdWhereWhere);
        setRule(TokenType.id, Grammatic.Whereclausule, Production.WhereclausuleId);
        setRule(TokenType.OPERATOR, Grammatic.Operator, Production.Operator);
        setRule(TokenType.END_STATEMENT, Grammatic.WhereclausulePrime, Production.WhereclausulePrimeEND_STATEMENT);
        setRule(TokenType.LOGICAL, Grammatic.WhereclausulePrime, Production.WhereclausulePrime);
        setRule(TokenType.LOGICAL, Grammatic.Logical, Production.Logical);
        setRule(TokenType.id, Grammatic.Tables, Production.TablesId);
        setRule(TokenType.END_STATEMENT, Grammatic.TablesPrime, Production.TablesPrimeEND_STATEMENT);
        setRule(TokenType.COMMA, Grammatic.TablesPrime, Production.TablesPrimeComma);
        setRule(TokenType.WHERE, Grammatic.TablesPrime, Production.TablesPrimeWhere);
        setRule(TokenType.COMMA, Grammatic.ElementoPrime, Production.ElementoPrime1); 
    }
}
