/*Nome: LeitorArquivo.java
 *Descricao: Classe que le o codigo fonte em SQL e passa para o analisador lexico.
 *Autores: João Flavio
 * 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Utils{
    public static String readFile() throws FileNotFoundException {
        File file = new File("script.sql");
        Scanner in = new Scanner(file);

        StringBuilder string = new StringBuilder();
        while (in.hasNext()){
            string.append(in.next() + " ");
        }
        in.close();
        return string.toString();
    }
}
