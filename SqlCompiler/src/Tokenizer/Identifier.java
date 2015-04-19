package Tokenizer;

import java.util.ArrayList;

public class Identifier {
    private static boolean reservedFound = true;
    private static boolean intFound = true;
    private static boolean floatFound = false;
    private static boolean idFound = true;

    private static int state = 0;
    private static Token actualReserved = null;
    private static int actualInt = 0;
    private static double actualFloat = 0;
    private static int floatDivider = 10;
    private static ArrayList<Character> id = new ArrayList<Character>();
    private static ArrayList<Character> buffer = new ArrayList<Character>();

    private static ArrayList<Token> tokens = new ArrayList<Token>();

    private static String getStringRepresentation(ArrayList<Character> list) {    
        StringBuilder builder = new StringBuilder(list.size());
        for(Character ch: list) {
            builder.append(ch);
        }
        return builder.toString();
    }

    private static void addFoundToken(){
        if(floatFound){
            tokens.add(new Token(TokenType.number, actualFloat));
        }else if(intFound){
            tokens.add(new Token(TokenType.integer, actualInt));
        }else if(reservedFound){
            tokens.add(actualReserved);
        }else if(idFound) {
            tokens.add(new Token(TokenType.id, getStringRepresentation(buffer)));
        }
    }

    private static void resetState(){
        reservedFound = true;
        intFound = true;
        floatFound = false;
        idFound = true;
        state = 0;
        actualReserved = null;
        actualInt = 0;
        actualFloat = 0;
        floatDivider = 10;
        id = new ArrayList<Character>();
        buffer = new ArrayList<Character>();
    }

    public static ArrayList<Token> identifyTokens(String input){
        char[] palavra = input.toLowerCase().toCharArray();
        ArrayList<Character> command = new ArrayList<Character>();
        
        for(char letra : palavra){
            if (letra == ' ' || letra == '\t' || letra == '\r' || letra == '\n') {
                addFoundToken();
                resetState();
            }
            buffer.add(letra);
            if(floatFound || intFound){
                updateNumber(letra);
            }else if(reservedFound){
                updateReservedWord(letra);
            }else if(idFound) {
                updateId(letra);
            }else{
                tokens.add(new Token(TokenType.ERROR, buffer.toString()));
                break;
            }
        }
        return tokens;
    }

    public static void updateNumber(Character letra) {
        // Ir verificando carácter à carácter 
        if(letra == '.'){
            floatFound = !floatFound;
            intFound = false;
            actualFloat += actualInt;
        }
        if(Character.isDigit(letra)){
            if(intFound){
                actualInt = actualInt * 10 + Character.getNumericValue(letra); 
            }else if(floatFound){
                floatDivider *= 10;
                actualFloat += Character.getNumericValue(letra) / floatDivider; 
            }
        }else{
            floatFound = false;
            intFound = false;
        }
    }

    public static void updateId(Character letra){
        if(id.size() == 0 && (!Character.isLetter(letra))){
            idFound = false;
        }
    }

    private static void updateReservedWord(Character letra) {
        String palavra = buffer.toString();
        switch (state) {
            case 0:
                switch (letra) {
                    case ')':
                        if( palavra == ")"){
                            reservedFound = true;
                            actualReserved = new Token(TokenType.OPEN_PARENTHESIS , String.valueOf(palavra)); // define o token para )
                            state = 16;
                        }
                        break;
                    case '(':
                        if( palavra == "("){
                            reservedFound = true;
                            actualReserved = new Token(TokenType.CLOSE_PARENTHESIS , String.valueOf(palavra)); // define o token para (
                            state = 16;
                        }
                        break;
                    case '*':
                        if( palavra == "*"){
                            reservedFound = true;
                            actualReserved = new Token(TokenType.STAR , String.valueOf(palavra)); // define o token para *
                            state = 16;
                        }
                        break;
                    case ',':
                        if( palavra == ","){
                            reservedFound = true;
                            actualReserved = new Token(TokenType.COMMA , String.valueOf(palavra)); // define o token para ,
                            state = 16;
                        }
                        break;
                    case ';':
                        if( palavra == ";"){
                            reservedFound = true;
                            actualReserved = new Token(TokenType.END_STATEMENT, String.valueOf(palavra)); // define o token para ;
                            state = 16;
                        }
                        break;
                    case '=':
                        if( palavra == "="){
                            reservedFound = true;
                            actualReserved = new Token(TokenType.OPERATOR , String.valueOf(palavra)); // define o token para =
                            state = 16;
                        }
                        break;
                    case '<':
                        if( palavra == "<"){
                            reservedFound = true;
                            actualReserved = new Token(TokenType.OPERATOR , String.valueOf(palavra)); // define o token para <
                            state = 16;
                        }
                        state = 8;
                        break;
                    case '>':
                        if( palavra == ">"){
                            reservedFound = true;
                            actualReserved = new Token(TokenType.OPERATOR , String.valueOf(palavra)); // define o token para >
                            state = 16;
                        }
                        state = 9;
                        break;
                    case 'a':
                        state = 1;
                        break;
                    case 'c':
                        state = 10;
                        break;
                    case 'd':
                        state = 2;
                        break;
                    case 'f':
                        state = 6;
                        break;
                    case 'i':
                        state = 4;
                        break;
                    case 'k':
                        if( palavra == "key"){
                            reservedFound = true;
                            actualReserved = new Token(TokenType.KEY , String.valueOf(palavra)); // define o token para key
                            state = 16;
                        }
                        break;
                    case 'm':
                        if( palavra == "modify"){
                            reservedFound = true;
                            actualReserved = new Token(TokenType.MODIFY , String.valueOf(palavra)); // define o token para modify
                            state = 16;
                        }
                        break;
                    case 'n':
                        state = 5;
                        break;
                    case 'p':
                        if( palavra == "primary"){
                            reservedFound = true;
                            actualReserved = new Token(TokenType.PRIMARY , String.valueOf(palavra)); // define o token para primary
                            state = 16;
                        }
                        break;
                    case 's':
                        if( palavra == "select"){
                            reservedFound = true;
                            actualReserved = new Token(TokenType.SELECT , String.valueOf(palavra)); // define o token para select
                            state = 16;
                        }
                        break;
                    case 'r':
                        state = 7;
                        break;
                    case 'u':
                        if( palavra == "use"){
                            reservedFound = true;
                            actualReserved = new Token(TokenType.USE , String.valueOf(palavra)); // define o token para use
                            state = 16;
                        }
                        break;
                    case 't':
                        state = 3;
                        break;
                    case 'w':
                        if( palavra == "where"){
                            reservedFound = true;
                            actualReserved = new Token(TokenType.WHERE , String.valueOf(palavra)); // define o token para where
                            state = 16;
                        }
                        break;
                    case 'v':
                        state = 11;
                        break;
                    default:
                        reservedFound = false;
                }
            case 1:
                switch (letra) {
                    case 'u':
                        if( palavra == "auto_increment"){
                            reservedFound = true;
                            actualReserved = new Token(TokenType.AUTO_INCREMENT , String.valueOf(palavra)); // define o token para auto_increment
                            state = 16;
                        }
                        break;
                    case 'd':
                        if( palavra == "add"){
                            reservedFound = true;
                            actualReserved = new Token(TokenType.ADD , String.valueOf(palavra)); // define o token para add
                            state = 16;
                        }
                        break;
                    case 'l':
                        if( palavra == "alter"){
                            reservedFound = true;
                            actualReserved = new Token(TokenType.ALTER , String.valueOf(palavra)); // define o token para alter
                            state = 16;
                        }
                        break;
                    default:
                        reservedFound = false;
                }
            case 2:
                switch (letra) {
                    case 'a':
                        if( palavra == "database"){
                            reservedFound = true;
                            actualReserved = new Token(TokenType.DATABASE , String.valueOf(palavra)); // define o token para database
                            state = 16;
                        }
                        break;
                    case 'r':
                        if( palavra == "drop"){
                            reservedFound = true;
                            actualReserved = new Token(TokenType.DROP , String.valueOf(palavra)); // define o token para drop
                            state = 16;
                        }
                        break;
                    case 'e':
                        if( palavra == "delete"){
                            reservedFound = true;
                            actualReserved = new Token(TokenType.DELETE , String.valueOf(palavra)); // define o token para delete
                            state = 16;
                        }
                        break;
                    default:
                        reservedFound = false;
                }
            case 3:
                switch (letra) {
                    case 'a':
                        if( palavra == "table"){
                            reservedFound = true;
                            actualReserved = new Token(TokenType.TABLE , String.valueOf(palavra)); // define o token para table
                            state = 16;
                        }
                        break;
                    case 'r':
                        if( palavra == "truncate"){
                            reservedFound = true;
                            actualReserved = new Token(TokenType.TRUNCATE , String.valueOf(palavra)); // define o token para truncate
                            state = 16;
                        }
                        break;
                    default:
                        reservedFound = false;
                }
            case 4:
                switch (letra) {
                    case 'n':
                        state = 12;
                        break;
                    default:
                        reservedFound = false;
                }
            case 5:
                switch (letra) {
                    case 'u':
                        state = 13;
                        break;
                    case 'o':
                        if( palavra == "not"){
                            reservedFound = true;
                            actualReserved = new Token(TokenType.NOT , String.valueOf(palavra)); // define o token para not
                            state = 16;
                        }
                        break;
                    default:
                        reservedFound = false;
                }
            case 6:
                switch (letra) {
                    case 'r':
                        if( palavra == "from"){
                            reservedFound = true;
                            actualReserved = new Token(TokenType.FROM , String.valueOf(palavra)); // define o token para from
                            state = 16;
                        }
                        break;
                    case 'o':
                        if( palavra == "foreign"){
                            reservedFound = true;
                            actualReserved = new Token(TokenType.FOREIGN , String.valueOf(palavra)); // define o token para foreign
                            state = 16;
                        }
                        break;
                    default:
                        reservedFound = false;
                }
            case 7:
                switch (letra) {
                    case 'e':
                        state = 14;
                        break;
                    default:
                        reservedFound = false;
                }
            case 8:
                switch (letra) {
                    case '=':
                        if( palavra == "<="){
                            reservedFound = true;
                            actualReserved = new Token(TokenType.OPERATOR, String.valueOf(palavra)); // define o token para <=
                            state = 16;
                        }
                        break;
                    case '>':
                        if( palavra == "<>"){
                            reservedFound = true;
                            actualReserved = new Token(TokenType.OPERATOR, String.valueOf(palavra)); // define o token para <>
                            state = 16;
                        }
                        break;
                    default:
                        reservedFound = false;
                }
            case 9:
                switch (letra) {
                    case '=':
                        if( palavra == ">="){
                            reservedFound = true;
                            actualReserved = new Token(TokenType.OPERATOR, String.valueOf(palavra)); // define o token para >=
                            state = 16;
                        }
                        break;
                    default:
                        reservedFound = false;
                }
            case 10:
                switch (letra) {
                    case 'h':
                        if( palavra == "char"){
                            reservedFound = true;
                            actualReserved = new Token(TokenType.CHAR , String.valueOf(palavra)); // define o token para char
                            state = 16;
                        }
                        break;
                    case 'r':
                        if( palavra == "create"){
                            reservedFound = true;
                            actualReserved = new Token(TokenType.CREATE , String.valueOf(palavra)); // define o token para create
                            state = 16;
                        }
                        break;
                    default:
                        reservedFound = false;
                }
            case 11:
                switch (letra) {
                    case 'a':
                        state = 15;
                        break;
                    default:
                        reservedFound = false;
                }
            case 12:
                switch (letra) {
                    case 's':
                        if( palavra == "insert"){
                            reservedFound = true;
                            actualReserved = new Token(TokenType.INSERT , String.valueOf(palavra)); // define o token para insert
                            state = 16;
                        }
                        break;
                    case 't':
                        if( palavra == "into"){
                            reservedFound = true;
                            actualReserved = new Token(TokenType.INTO , String.valueOf(palavra)); // define o token para into
                            state = 16;
                        }
                        break;
                    default:
                        reservedFound = false;
                }
            case 13:
                switch (letra) {
                    case 'm':
                        if( palavra == "numeric"){
                            reservedFound = true;
                            actualReserved = new Token(TokenType.NUMERIC , String.valueOf(palavra)); // define o token para numeric
                            state = 16;
                        }
                        break;
                    case 'l':
                        if( palavra == "null"){
                            reservedFound = true;
                            actualReserved = new Token(TokenType.NULL , String.valueOf(palavra)); // define o token para null
                            state = 16;
                        }
                        break;
                    default:
                        reservedFound = false;
                }
            case 14:
                switch (letra) {
                    case 'f':
                        if( palavra == "references"){
                            reservedFound = true;
                            actualReserved = new Token(TokenType.REFERENCES , String.valueOf(palavra)); // define o token para references
                            state = 16;
                        }
                        break;
                    case 'n':
                        if( palavra == "rename"){
                            reservedFound = true;
                            actualReserved = new Token(TokenType.RENAME , String.valueOf(palavra)); // define o token para rename
                            state = 16;
                        }
                        break;
                    default:
                        reservedFound = false;
                }
            case 15:
                switch (letra) {
                    case 'r':
                        if( palavra == "varchar"){
                            reservedFound = true;
                            actualReserved = new Token(TokenType.VARCHAR , String.valueOf(palavra)); // define o token para varchar
                            state = 16;
                        }
                        break;
                    case 'l':
                        if( palavra == "values"){
                            reservedFound = true;
                            actualReserved = new Token(TokenType.VALUES , String.valueOf(palavra)); // define o token para values
                            state = 16;
                        }
                        break;
                    default:
                        reservedFound = false;
                }
            case 16:
            default:
                reservedFound = false;
        }
    }//end for word
}

