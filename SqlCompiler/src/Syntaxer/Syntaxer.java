package Syntaxer;
import java.util.Stack;
import java.util.ArrayList;
import Tokenizer.*;

public class Syntaxer { 
    public static ParsingTable table;
    public static Stack<Object> stack = new Stack<Object>();

    public static void analyze(ArrayList<Token> tokensList) {
        int position = 0;
        Token token;
        Production rule;
        Object expectedInput;

        System.out.println("(ง︡'-'︠)ง Sintax analsys:");
        
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
