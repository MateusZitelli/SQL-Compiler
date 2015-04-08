import java.io.FileNotFoundException;

public class SqlCompiler {
    public static void main (String[] args) {
        String fileContent = null;
        
        try {
            fileContent = Utils.readFile();
        } catch(FileNotFoundException e) {
			System.err.println("Impossível ler o arquivo.");
            return;
        }
        
        System.out.println(Tokenizer.getTokens(fileContent));
        Syntaxer.test();
    }
}
