package Tokenizer;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Identifier {
    // Actual state
    private static boolean reservedFound = false;
    private static boolean notReserved = false;
    private static boolean intFound = false;
    private static boolean floatFound = false;
    private static boolean idFound = false;

    private static int state = 0;
    private static Token actualReserved = null;
    private static int actualInt = 0;
    private static double actualFloat = 0;
    private static double floatDivider = 10;
    private static ArrayList<Character> id = new ArrayList<Character>();
    private static ArrayList<Character> buffer = new ArrayList<Character>();

    // Ahead State
    private static boolean aheadReservedFound = true;
    private static boolean aheadNotReserved = false;
    private static boolean aheadIntFound = true;
    private static boolean aheadFloatFound = false;
    private static boolean aheadIdFound = true;

    private static int aheadState = 0;
    private static Token aheadReserved = null;
    private static int aheadInt = 0;
    private static double aheadFloat = 0;
    private static double aheadFloatDivider = 10;
    private static ArrayList<Character> aheadId = new ArrayList<Character>();
    private static ArrayList<Character> aheadBuffer = new ArrayList<Character>();

    // Identifier general state
    private static Iterator statesIterator;
    private static ArrayList<Token> tokens = new ArrayList<Token>();
    private static boolean firstRun = true;

    private static String getStringRepresentation(ArrayList<Character> list) {    
        StringBuilder builder = new StringBuilder(list.size());
        for(Character ch: list) {
            builder.append(ch);
        }
        return builder.toString();
    }

    private static boolean addTokenIfWereAvailable(){
        boolean aheadReservedAccept = aheadReservedFound && !aheadNotReserved;
        boolean reservedAccept = reservedFound && !notReserved;
        boolean justOne = (floatFound?1:0) + (intFound?1:0) + (reservedAccept?1:0) + (idFound?1:0) == 1;
        justOne &= !aheadFloatFound && !aheadIntFound && !aheadReservedAccept && !aheadIdFound;

        if(!aheadFloatFound && floatFound && justOne){
            tokens.add(new Token(TokenType.number, actualFloat));
            return true;
        }else if(!aheadIntFound && intFound && justOne){
            tokens.add(new Token(TokenType.integer, actualInt));
            return true;
        }else if(reservedAccept){
            tokens.add(actualReserved);
            return true;
        }else if(!aheadIdFound && idFound && justOne) {
            tokens.add(new Token(TokenType.id, getStringRepresentation(buffer)));
            return true;
        }

        return false;
    }

    private static void resetState(){
        // Actual state
        reservedFound = false;
        notReserved = false;
        intFound = false;
        floatFound = false;
        idFound = false;

        state = 0;
        actualReserved = null;
        actualInt = 0;
        actualFloat = 0;
        floatDivider = 10;

        // Ahead State
        aheadReservedFound = false;
        aheadNotReserved = false;
        aheadIntFound = false;
        aheadFloatFound = false;
        aheadIdFound = false;

        aheadState = 0;
        aheadReserved = null;
        aheadInt = 0;
        aheadFloat = 0;
        aheadFloatDivider = 10;
        firstRun = true;
        buffer = new ArrayList<Character>();
        aheadBuffer = new ArrayList<Character>();
    }

    private static void tickAtualState(){
        reservedFound = aheadReservedFound;
        notReserved = aheadNotReserved;
        intFound = aheadIntFound;
        floatFound = aheadFloatFound;
        idFound = aheadIdFound;

        state = aheadState;
        actualReserved = aheadReserved;
        actualInt = aheadInt;
        actualFloat = aheadFloat;
        floatDivider = aheadFloatDivider;
        buffer = new ArrayList<Character>(aheadBuffer);
    }

    public static void tickWithChar(char letra) {
        boolean tokenAdded = false;
        if(floatFound || intFound || firstRun){
            updateNumber(letra);
        }
        if(idFound || firstRun) {
            updateId(letra);
        }
        if(!notReserved || firstRun){
            updateReservedWord(letra);
        }
        tokenAdded = addTokenIfWereAvailable();
        if(tokenAdded){
            resetState();
            if (!(letra == ' ' || letra == '\t' || letra == '\r' || letra == '\n')) {
                tickWithChar(letra);
            }
        }else{
            aheadBuffer.add(letra);
            tickAtualState();
            firstRun = false;
        }
    }
    

    public static ArrayList<Token> identifyTokens(String input){
        char[] palavra = input.toLowerCase().toCharArray();
        
        for(char letra : palavra){
            tickWithChar(letra);
        }
        return tokens;
    }

    public static void updateNumber(Character letra) {
        // Ir verificando carácter à carácter 
        if(letra == '.'){
            aheadFloatFound = !floatFound;
            aheadIntFound = false;
            aheadFloat = actualInt;
        }
        if(Character.isDigit(letra)){
            if(!floatFound)
                intFound = true;

            if(intFound || firstRun){
                aheadInt = actualInt * 10 + Character.getNumericValue(letra); 
                aheadIntFound = true;
            }else if(floatFound || firstRun){
                aheadFloatDivider *= 10;
                aheadFloat += Character.getNumericValue(letra) / floatDivider; 
                aheadFloatFound = true;
            }
        }else if(letra != '.'){
            aheadFloatFound = false;
            aheadIntFound = false;
        }
    }

    public static void updateId(Character letra){
        aheadIdFound = Character.isLetter(letra);
    }

    private static void createReservedStatesQueue(String remReservedWord) {
        LinkedList ll = new LinkedList(Arrays.asList(remReservedWord.split("")));
        statesIterator = ll.iterator();
    }

    private static void updateReservedWord(Character letra) {
        switch (state) {
            case 0:
                switch (letra) {
                    case '"':
                        // Add remanining string from token OPEN_PARENTHESIS
                        aheadReserved = new Token(TokenType.QUOTE , null);
                        aheadReservedFound = true;
                        break;
                    case ')':
                        // Add remanining string from token OPEN_PARENTHESIS
                        aheadReserved = new Token(TokenType.CLOSE_PARENTHESIS , null);
                        aheadReservedFound = true;
                        break;
                    case '(':
                        // Add remanining string from token CLOSE_PARENTHESIS
                        aheadReserved = new Token(TokenType.OPEN_PARENTHESIS , null);
                        aheadReservedFound = true;
                        break;
                    case '*':
                        // Add remanining string from token STAR
                        aheadReserved = new Token(TokenType.STAR , null);
                        aheadReservedFound = true;
                        break;
                    case ',':
                        // Add remanining string from token COMMA
                        aheadReserved = new Token(TokenType.COMMA , null);
                        aheadReservedFound = true;
                        break;
                    case ';':
                        // Add remanining string from token END_STATEMENT
                        aheadReserved = new Token(TokenType.END_STATEMENT , null);
                        aheadReservedFound = true;
                        break;
                    case '=':
                        // Add remanining string from token OPERATOR
                        aheadReserved = new Token(TokenType.OPERATOR , null);
                        aheadReservedFound = true;
                        break;
                    case '<':
                        // Add remanining string from token OPERATOR
                        aheadReserved = new Token(TokenType.OPERATOR , null);
                        aheadReservedFound = true;
                        aheadState = 8;
                        break;
                    case '>':
                        // Add remanining string from token OPERATOR
                        aheadReserved = new Token(TokenType.OPERATOR , null);
                        aheadReservedFound = true;
                        aheadState = 9;
                        break;
                    case 'a':
                        aheadState = 1;
                        break;
                    case 'c':
                        aheadState = 10;
                        break;
                    case 'd':
                        aheadState = 2;
                        break;
                    case 'f':
                        aheadState = 6;
                        break;
                    case 'i':
                        aheadState = 4;
                        break;
                    case 'k':
                        // Add remanining string from token KEY
                        aheadReserved = new Token(TokenType.KEY , null);
                        createReservedStatesQueue("ey");
                        aheadReservedFound = false;
                        aheadState = 16;
                        break;
                    case 'm':
                        // Add remanining string from token MODIFY
                        aheadReserved = new Token(TokenType.MODIFY , null);
                        createReservedStatesQueue("odify");
                        aheadReservedFound = false;
                        aheadState = 16;
                        break;
                    case 'n':
                        aheadState = 5;
                        break;
                    case 'p':
                        // Add remanining string from token PRIMARY
                        aheadReserved = new Token(TokenType.PRIMARY , null);
                        createReservedStatesQueue("rimary");
                        aheadReservedFound = false;
                        aheadState = 16;
                        break;
                    case 's':
                        // Add remanining string from token SELECT
                        aheadReserved = new Token(TokenType.SELECT , null);
                        createReservedStatesQueue("elect");
                        aheadReservedFound = false;
                        aheadState = 16;
                        break;
                    case 'r':
                        aheadState = 7;
                        break;
                    case 'u':
                        // Add remanining string from token USE
                        aheadReserved = new Token(TokenType.USE , null);
                        createReservedStatesQueue("se");
                        aheadReservedFound = false;
                        aheadState = 16;
                        break;
                    case 't':
                        aheadState = 3;
                        break;
                    case 'w':
                        // Add remanining string from token WHERE
                        aheadReserved = new Token(TokenType.WHERE , null);
                        createReservedStatesQueue("here");
                        aheadReservedFound = false;
                        aheadState = 16;
                        break;
                    case 'v':
                        aheadState = 11;
                        break;
                    default:
                        aheadState = 17;
                        aheadNotReserved = true;
                }
                break;
            case 1:
                switch (letra) {
                    case 'u':
                        // Add remanining string from token AUTO_INCREMENT
                        aheadReserved = new Token(TokenType.AUTO_INCREMENT , null);
                        createReservedStatesQueue("to_increment");
                        aheadReservedFound = false;
                        aheadState = 16;
                        break;
                    case 'd':
                        // Add remanining string from token ADD
                        aheadReserved = new Token(TokenType.ADD , null);
                        createReservedStatesQueue("d");
                        aheadReservedFound = false;
                        aheadState = 16;
                        break;
                    case 'l':
                        // Add remanining string from token ALTER
                        aheadReserved = new Token(TokenType.ALTER , null);
                        createReservedStatesQueue("ter");
                        aheadReservedFound = false;
                        aheadState = 16;
                        break;
                    default:
                        aheadState = 17;
                        aheadNotReserved = true;
                }
                break;
            case 2:
                switch (letra) {
                    case 'a':
                        // Add remanining string from token DATABASE
                        aheadReserved = new Token(TokenType.DATABASE , null);
                        createReservedStatesQueue("tabase");
                        aheadReservedFound = false;
                        aheadState = 16;
                        break;
                    case 'r':
                        // Add remanining string from token DROP
                        aheadReserved = new Token(TokenType.DROP , null);
                        createReservedStatesQueue("op");
                        aheadReservedFound = false;
                        aheadState = 16;
                        break;
                    case 'e':
                        // Add remanining string from token DELETE
                        aheadReserved = new Token(TokenType.DELETE , null);
                        createReservedStatesQueue("lete");
                        aheadReservedFound = false;
                        aheadState = 16;
                        break;
                    default:
                        aheadState = 17;
                        aheadNotReserved = true;
                }
                break;
            case 3:
                switch (letra) {
                    case 'a':
                        // Add remanining string from token TABLE
                        aheadReserved = new Token(TokenType.TABLE , null);
                        createReservedStatesQueue("ble");
                        aheadReservedFound = false;
                        aheadState = 16;
                        break;
                    case 'r':
                        // Add remanining string from token TRUNCATE
                        aheadReserved = new Token(TokenType.TRUNCATE , null);
                        createReservedStatesQueue("uncate");
                        aheadReservedFound = false;
                        aheadState = 16;
                        break;
                    default:
                        aheadState = 17;
                        aheadNotReserved = true;
                }
                break;
            case 4:
                switch (letra) {
                    case 'n':
                        aheadState = 12;
                        break;
                    default:
                        aheadState = 17;
                        aheadNotReserved = true;
                }
                break;
            case 5:
                switch (letra) {
                    case 'u':
                        aheadState = 13;
                        break;
                    case 'o':
                        // Add remanining string from token NOT
                        aheadReserved = new Token(TokenType.NOT , null);
                        createReservedStatesQueue("t");
                        aheadReservedFound = false;
                        aheadState = 16;
                        break;
                    default:
                        aheadState = 17;
                        aheadNotReserved = true;
                }
                break;
            case 6:
                switch (letra) {
                    case 'r':
                        // Add remanining string from token FROM
                        aheadReserved = new Token(TokenType.FROM , null);
                        createReservedStatesQueue("om");
                        aheadReservedFound = false;
                        aheadState = 16;
                        break;
                    case 'o':
                        // Add remanining string from token FOREIGN
                        aheadReserved = new Token(TokenType.FOREIGN , null);
                        createReservedStatesQueue("reign");
                        aheadReservedFound = false;
                        aheadState = 16;
                        break;
                    default:
                        aheadState = 17;
                        aheadNotReserved = true;
                }
                break;
            case 7:
                switch (letra) {
                    case 'e':
                        aheadState = 14;
                        break;
                    default:
                        aheadState = 17;
                        aheadNotReserved = true;
                }
                break;
            case 8:
                switch (letra) {
                    case '=':
                        // Add remanining string from token OPERATOR
                        aheadReserved = new Token(TokenType.OPERATOR , null);
                        createReservedStatesQueue("=");
                        aheadReservedFound = false;
                        aheadState = 16;
                        break;
                    case '>':
                        // Add remanining string from token OPERATOR
                        aheadReserved = new Token(TokenType.OPERATOR , null);
                        createReservedStatesQueue(">");
                        aheadReservedFound = false;
                        aheadState = 16;
                        break;
                    default:
                        aheadState = 17;
                        aheadNotReserved = true;
                }
                break;
            case 9:
                switch (letra) {
                    case '=':
                        // Add remanining string from token OPERATOR
                        aheadReserved = new Token(TokenType.OPERATOR , null);
                        createReservedStatesQueue("=");
                        aheadReservedFound = false;
                        aheadState = 16;
                        break;
                    default:
                        aheadState = 17;
                        aheadNotReserved = true;
                }
                break;
            case 10:
                switch (letra) {
                    case 'h':
                        // Add remanining string from token CHAR
                        aheadReserved = new Token(TokenType.CHAR , null);
                        createReservedStatesQueue("ar");
                        aheadReservedFound = false;
                        aheadState = 16;
                        break;
                    case 'r':
                        // Add remanining string from token CREATE
                        aheadReserved = new Token(TokenType.CREATE , null);
                        createReservedStatesQueue("eate");
                        aheadReservedFound = false;
                        aheadState = 16;
                        break;
                    default:
                        aheadState = 17;
                        aheadNotReserved = true;
                }
                break;
            case 11:
                switch (letra) {
                    case 'a':
                        aheadState = 15;
                        break;
                    default:
                        aheadState = 17;
                        aheadNotReserved = true;
                }
                break;
            case 12:
                switch (letra) {
                    case 's':
                        // Add remanining string from token INSERT
                        aheadReserved = new Token(TokenType.INSERT , null);
                        createReservedStatesQueue("ert");
                        aheadReservedFound = false;
                        aheadState = 16;
                        break;
                    case 't':
                        // Add remanining string from token INTO
                        aheadReserved = new Token(TokenType.INTO , null);
                        createReservedStatesQueue("o");
                        aheadReservedFound = false;
                        aheadState = 16;
                        break;
                    default:
                        aheadState = 17;
                        aheadNotReserved = true;
                }
                break;
            case 13:
                switch (letra) {
                    case 'm':
                        // Add remanining string from token NUMERIC
                        aheadReserved = new Token(TokenType.NUMERIC , null);
                        createReservedStatesQueue("eric");
                        aheadReservedFound = false;
                        aheadState = 16;
                        break;
                    case 'l':
                        // Add remanining string from token NULL
                        aheadReserved = new Token(TokenType.NULL , null);
                        createReservedStatesQueue("l");
                        aheadReservedFound = false;
                        aheadState = 16;
                        break;
                    default:
                        aheadState = 17;
                        aheadNotReserved = true;
                }
                break;
            case 14:
                switch (letra) {
                    case 'f':
                        // Add remanining string from token REFERENCES
                        aheadReserved = new Token(TokenType.REFERENCES , null);
                        createReservedStatesQueue("erences");
                        aheadReservedFound = false;
                        aheadState = 16;
                        break;
                    case 'n':
                        // Add remanining string from token RENAME
                        aheadReserved = new Token(TokenType.RENAME , null);
                        createReservedStatesQueue("ame");
                        aheadReservedFound = false;
                        aheadState = 16;
                        break;
                    default:
                        aheadState = 17;
                        aheadNotReserved = true;
                }
                break;
            case 15:
                switch (letra) {
                    case 'r':
                        // Add remanining string from token VARCHAR
                        aheadReserved = new Token(TokenType.VARCHAR , null);
                        createReservedStatesQueue("char");
                        aheadReservedFound = false;
                        aheadState = 16;
                        break;
                    case 'l':
                        // Add remanining string from token VALUES
                        aheadReserved = new Token(TokenType.VALUES , null);
                        createReservedStatesQueue("ues");
                        aheadReservedFound = false;
                        aheadState = 16;
                        break;
                    default:
                        aheadState = 17;
                        aheadNotReserved = true;
                }
                break;
                // State of queue check
            case 16:
                if(statesIterator.hasNext()){
                    char nextChar = String.valueOf(statesIterator.next()).charAt(0);
                    char inputChar = String.valueOf(letra).charAt(0);
                    // is the chars dont match
                    if(nextChar != inputChar){
                        aheadReservedFound = false;
                        aheadNotReserved = true;
                        aheadState = 17;
                        return;
                    }
                    // If reach the end of the queue
                    if(!statesIterator.hasNext()){
                        aheadReservedFound = true;
                        aheadState = 17;
                        return;
                    }
                }
                break;
                // Final aheadState of nonacceptance
            case 17:
            default:
                aheadReservedFound = false;
                aheadNotReserved = true;
        }
    }//end for word
}

