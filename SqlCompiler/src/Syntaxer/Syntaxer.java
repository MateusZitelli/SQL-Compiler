package Syntaxer;
import java.util.*;
import Tokenizer.*;
import GrammaticalElement.*;
import Synthesized.*;

class SaveAct implements ActionInterface {
    public void getFromParent(GrammaticalInterface parent, Map<String, String> attrs){}
    public void act(Stack<GrammaticalInterface> stack, Map<String, String> attrs){
        System.out.print("Save ");
        System.out.println(attrs.get("code"));
    }
}

public class Syntaxer { 
    public static ParsingTable table;
    public static Stack<GrammaticalInterface> stack = new Stack<GrammaticalInterface>();

    public static void analyze(ArrayList<Token> tokensList) {
        int position = 0;
        Token token;
        Production rule;
        GrammaticalInterface grammaticalElement;

        System.out.println("(ง︡'-'︠)ง Sintax analsys:");
        
        System.out.print("Stack: ");
        System.out.println(stack);
        while(stack.size() > 0) {
            token = tokensList.get(position); 
            grammaticalElement = stack.pop(); 
            if(grammaticalElement.isTokenType()){
                if(grammaticalElement == token.type){
                    position += 1; 
                    System.out.print("pop ");
                    System.out.println(token);
                    // If there is data put in the next element in the stack
                    if(token.data != null){
                        stack.get(stack.size() - 1).setAttr("data", token.data);
                    }
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
                    GrammaticalInterface nextRule = rule.productions[i];
                    nextRule.getFromParent(grammaticalElement);
                    stack.push(nextRule);
                }
            }else if(grammaticalElement.isAction()){
                grammaticalElement.act(stack);
            }
            System.out.print("Stack: ");
            System.out.println(stack);
        }
    }

    public Syntaxer() {
        Synthesized Save = new Synthesized(new SaveAct()); 
        this.table = new ParsingTable();
        stack.push(TokenType.EOF); 
        stack.push(Save);
        stack.push(Grammatic.START);
    }
}
