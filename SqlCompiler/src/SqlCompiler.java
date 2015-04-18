import java.util.ArrayList;
import java.io.FileNotFoundException;

import Syntaxer.Syntaxer;
import Tokenizer.Token;
import Tokenizer.Tokenizer;
import Utils.Utils;

public class SqlCompiler {
    public static void main (String[] args) {
        String fileContent = null;
        
        try {
            fileContent = Utils.readFile();
        } catch(FileNotFoundException e) {
			System.err.println("Impossível ler o arquivo.");
            return;
        }
        
        ArrayList<Token> tokens = Tokenizer.getTokens(fileContent);
        Syntaxer.analyze(tokens);
    }
}
