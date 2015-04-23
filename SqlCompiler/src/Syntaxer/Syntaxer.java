package Syntaxer;
import java.util.Stack;
import java.util.ArrayList;
import Tokenizer.*;
import GrammaticalElement.*;

public class Syntaxer { 
    public static ParsingTable table;
    public static Stack<GrammaticalInterface> stack = new Stack<GrammaticalInterface>();

    public static void analyze(ArrayList<Token> tokensList) {
        int position = 0;
        Token token;
        Production rule;
        GrammaticalInterface grammaticalElement;

        System.out.println("(ง︡'-'︠)ง Sintax analsys:");
        
        while(stack.size() > 0) {
            token = tokensList.get(position); 
            grammaticalElement = stack.pop(); 
            if(grammaticalElement.isTokenType()){
                if(grammaticalElement == token.type){
                    position += 1; 
                    System.out.print("pop ");
                    System.out.println(token);
                    if(grammaticalElement == TokenType.EOF){
                        System.out.println("Accepted");
                    }
                }else{
                    System.out.print("Bad input: ");
                    System.out.print(token);
                    System.out.print(" expected: ");
                    System.out.println(grammaticalElement);
                    return;
                }
            }else if(grammaticalElement.isGrammatic()){
                rule = table.getRule(token.type, (Grammatic)grammaticalElement); 
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
            }else if(grammaticalElement.isAction()){
                grammaticalElement.act(stack);
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
