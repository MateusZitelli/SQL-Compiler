package Tokenizer;


public class Automato {
    
	public static Token identifyNumber(String stringToken) {
        try{
            int i = Integer.parseInt(stringToken);
            // TODO send INT value
            Token token = new Token(TokenType.integer, stringToken);
            return token;
        }catch(NumberFormatException e){
            try{
                double d = Double.parseDouble(stringToken);
                Token token = new Token(TokenType.number, stringToken);
                return token;
            }catch(NumberFormatException nfe){
                return null;
            }
        }
    }

    public static Token identifyToken(String stringToken) {
        int state = 0;
        char[] palavra = stringToken.toLowerCase().toCharArray();

        for(char letra : palavra){
        	switch (state) {
            	case 0:
                    switch (letra) {
                    	case '(':
                            if(String.valueOf(palavra).equalsIgnoreCase("(")){
                                return new Token(TokenType.OPEN_PARENTHESIS , String.valueOf(palavra)); // retornar token para )
                            } else
                            	state=21;
                            break;
                        case ')':
                            if(String.valueOf(palavra).equalsIgnoreCase(")")){
                                return new Token(TokenType.CLOSE_PARENTHESIS , String.valueOf(palavra)); // retornar token para (
                            } else
                            	state=21;
                            break;
                        case '*':
                            if(String.valueOf(palavra).equalsIgnoreCase("*")){
                                return new Token(TokenType.STAR , String.valueOf(palavra)); // retornar token para *
                            } else
                            	state=21;
                            break;
                        case ',':
                            if(String.valueOf(palavra).equalsIgnoreCase(",")){
                                return new Token(TokenType.COMMA , String.valueOf(palavra)); // retornar token para ,
                            } else
                            	state=21;
                            break;
                        case ';':
                            if(String.valueOf(palavra).equalsIgnoreCase(";")){
                                return new Token(TokenType.END_STATEMENT , String.valueOf(palavra)); // retornar token para ;
                            } else
                            	state=21;
                            break;
                        case '=':
                            if(String.valueOf(palavra).equalsIgnoreCase("=")){
                                return new Token(TokenType.OPERATOR , String.valueOf(palavra)); // retornar token para =
                            } else
                            	state=21;
                            break;
                        case '<':
                            if(String.valueOf(palavra).equalsIgnoreCase("<")){
                                return new Token(TokenType.OPERATOR , String.valueOf(palavra)); // retornar token para <
                            }
                            state = 8;
                            break;
                        case '>':
                            if(String.valueOf(palavra).equalsIgnoreCase(">")){
                                return new Token(TokenType.OPERATOR, String.valueOf(palavra)); // retornar token para >
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
                            if(String.valueOf(palavra).equalsIgnoreCase("key")){
                                return new Token(TokenType.KEY , String.valueOf(palavra)); // retornar token para key
                            }else if (String.valueOf(palavra).equalsIgnoreCase("k")) {
                            	return new Token(TokenType.id , String.valueOf(palavra));
                            }
                            state = 20;
                            break;
                        case 'm':
                            if(String.valueOf(palavra).equalsIgnoreCase("modify")){
                                return new Token(TokenType.MODIFY , String.valueOf(palavra)); // retornar token para modify
                            }
                            state = 20;
                            break;
                        case 'n':
                            state = 5;
                            break;
                        case 'p':
                            if(String.valueOf(palavra).equalsIgnoreCase("primary")){
                                return new Token(TokenType.PRIMARY , String.valueOf(palavra)); // retornar token para primary
                            }else if (String.valueOf(palavra).equalsIgnoreCase("p")) {
                            	return new Token(TokenType.id , String.valueOf(palavra));
                            }
                            state = 20;
                            break;
                        case 's':
                            if(String.valueOf(palavra).equalsIgnoreCase("select")){
                                return new Token(TokenType.SELECT , String.valueOf(palavra)); // retornar token para select
                            }else if (String.valueOf(palavra).equalsIgnoreCase("s")) {
                            	return new Token(TokenType.id , String.valueOf(palavra));
                            }
                            state = 20;
                            break;
                        case 'r':
                            state = 7;
                            break;
                        case 'u':
                            if(String.valueOf(palavra).equalsIgnoreCase("use")){
                                return new Token(TokenType.USE , String.valueOf(palavra)); // retornar token para use
                            }
                            state = 20;
                            break;
                        case 't':
                            state = 3;
                            break;
                        case 'w':
                            if(String.valueOf(palavra).equalsIgnoreCase("where")){
                                return new Token(TokenType.WHERE , String.valueOf(palavra)); // retornar token para where
                            }
                            state = 20;
                            break;
                        case 'v':
                            state = 11;
                            break;
                        default:
                            //redireciona para o estado para verificar se e um ID
                            state = 20;
                            break;
                    }
                    break;
                case 1:
                    switch (letra) {
                        case 'u':
                            if(String.valueOf(palavra).equalsIgnoreCase("auto_increment")){
                                return new Token(TokenType.AUTO_INCREMENT , String.valueOf(palavra)); // retornar token para auto_increment
                            }
                            state = 20;
                            break;
                        case 'd':
                            if(String.valueOf(palavra).equalsIgnoreCase("add")){
                                return new Token(TokenType.ADD , String.valueOf(palavra)); // retornar token para add
                            }
                            state = 20;
                            break;
                        case 'l':
                            if(String.valueOf(palavra).equalsIgnoreCase("alter")){
                                return new Token(TokenType.ALTER , String.valueOf(palavra)); // retornar token para alter
                            }
                            state = 20;
                            break;
                        default:
                        	state = 20;//possivel ser um ID
                            break;
                    }
                    break;
                case 2:
                    switch (letra) {
                        case 'a':
                            if(String.valueOf(palavra).equalsIgnoreCase("database")){
                                return new Token(TokenType.DATABASE , String.valueOf(palavra)); // retornar token para database
                            }
                            state = 20;
                            break;
                        case 'r':
                            if(String.valueOf(palavra).equalsIgnoreCase("drop")){
                                return new Token(TokenType.DROP , String.valueOf(palavra)); // retornar token para drop
                            }
                            state = 20;
                            break;
                        case 'e':
                            if(String.valueOf(palavra).equalsIgnoreCase("delete")){
                                return new Token(TokenType.DELETE , String.valueOf(palavra)); // retornar token para delete
                            }
                            state = 20;
                            break;
                        default:
                        	state = 20;// possibilidade de ser um ID
                            break;
                    }
                    break;
                case 3:
                    switch (letra) {
                        case 'a':
                            if(String.valueOf(palavra).equalsIgnoreCase("table")){
                                return new Token(TokenType.TABLE , String.valueOf(palavra)); // retornar token para table
                            }
                            state = 20;
                            break;
                        case 'r':
                            if(String.valueOf(palavra).equalsIgnoreCase("truncate")){
                                return new Token(TokenType.TRUNCATE , String.valueOf(palavra)); // retornar token para truncate
                            }
                            state = 20;
                            break;
                        default:
                        	state = 20;// Possibilidade de ser um ID
                            break;
                    }
                    break;
                case 4:
                    switch (letra) {
                        case 'n':
                        	if(String.valueOf(palavra).equalsIgnoreCase("in")){
                                return new Token(TokenType.IN , String.valueOf(palavra)); // retornar token para table
                            }
                            state = 12;
                            break;
                        default:
                            state = 20; // possiblidade de ser um ID
                            break;
                    }
                    break;
                case 5:
                    switch (letra) {
                        case 'u':
                            state = 13;
                            break;
                        case 'o':
                            if(String.valueOf(palavra).equalsIgnoreCase("not")){
                                return new Token(TokenType.NOT , String.valueOf(palavra)); // retornar token para not
                            }
                            state =20;//possibilidade de ser um id
                            break;
                        default:
                        	state =20;//possibilidade de ser um id
                            break;
                    }
                    break;
                case 6:
                    switch (letra) {
                        case 'r':
                            if(String.valueOf(palavra).equalsIgnoreCase("from")){
                                return new Token(TokenType.FROM , String.valueOf(palavra)); // retornar token para from
                            }
                            state =20;//possibilidade de ser um id
                            break;
                        case 'o':
                            if(String.valueOf(palavra).equalsIgnoreCase("foreign")){
                                return new Token(TokenType.FOREIGN , String.valueOf(palavra)); // retornar token para foreign
                            }
                            state =20;//possibilidade de ser um id
                            break;
                        default:
                        	state =20;//possibilidade de ser um id
                            break;
                    }
                    break;
                case 7:
                    switch (letra) {
                        case 'e':
                            state = 14;
                            break;
                        default:
                        	state = 20;//possibilidade de ser um id
                            break;
                    }
                    break;
                case 8:
                    switch (letra) {
                        case '=':
                            if(String.valueOf(palavra).equalsIgnoreCase("<=")){
                                return new Token(TokenType.OPERATOR , String.valueOf(palavra)); // retornar token para <=
                            }
                            state=21;//string do tipo <=xxx... nao aceita ERRO
                            break;
                        case '>':
                            if(String.valueOf(palavra).equalsIgnoreCase("<>")){
                                return new Token(TokenType.OPERATOR, String.valueOf(palavra)); // retornar token para <>
                            }
                            state=21; //string do tipo <>xxx... nao aceita ERRO
                            break;
                        default:
                        	state=21; //string do tipo <xxx... nao aceita ERRO
                            System.out.print("ponto_1 "+ String.valueOf(letra));
                            break;
                    }
                    break;
                case 9:
                    switch (letra) {
                        case '=':
                            if(String.valueOf(palavra).equalsIgnoreCase(">=")){
                                return new Token(TokenType.OPERATOR, String.valueOf(palavra)); // retornar token para >=
                            }else
                                return new Token(TokenType.ERROR, String.valueOf(palavra)); // string do tipo >=xxx... nao aceita ERRO
                            //break;
                        default:
                            state =21; //string do tipo >xxx... nao aceita ERRO
                            break;
                    }
                    break;
                case 10:
                    switch (letra) {
                        case 'h':
                            if(String.valueOf(palavra).equalsIgnoreCase("char")){
                                return new Token(TokenType.CHAR, String.valueOf(palavra)); // retornar token para char
                            }
                            state =20;//possibilidade de ser um id
                            break;
                        case 'r':
                            if(String.valueOf(palavra).equalsIgnoreCase("create")){
                                return new Token(TokenType.CREATE , String.valueOf(palavra)); // retornar token para create
                            }
                            state =20;//possibilidade de ser um id
                            break;
                        default:
                        	state =20;//possibilidade de ser um id
                            break;
                    }
                    break;
                case 11:
                    switch (letra) {
                        case 'a':
                            state = 15;
                            break;
                        default:
                        	state =20;//possibilidade de ser um id
                            break;
                    }
                    break;
                case 12:
                    switch (letra) {
                        case 's':
                            if(String.valueOf(palavra).equalsIgnoreCase("insert")){
                                return new Token(TokenType.INSERT , String.valueOf(palavra)); // retornar token para insert
                            }
                            state =20;//possibilidade de ser um id
                            break;
                        case 't':
                            if(String.valueOf(palavra).equalsIgnoreCase("into")){
                                return new Token(TokenType.INTO , String.valueOf(palavra)); // retornar token para into
                            }
                            state =20;//possibilidade de ser um id
                            break;
                        default:
                        	state =20;//possibilidade de ser um id
                            break;
                    }
                    break;
                case 13:
                    switch (letra) {
                        case 'm':
                            if(String.valueOf(palavra).equalsIgnoreCase("numeric")){
                                return new Token(TokenType.NUMERIC , String.valueOf(palavra)); // retornar token para numeric
                            }
                            state =20;//possibilidade de ser um id
                            break;
                        case 'l':
                            if(String.valueOf(palavra).equalsIgnoreCase("null")){
                                return new Token(TokenType.NULL , String.valueOf(palavra)); // retornar token para null
                            }
                            state =20;//possibilidade de ser um id
                            break;
                        default:
                        	state =20;//possibilidade de ser um id
                            break;
                    }
                    break;
                case 14:
                    switch (letra) {
                        case 'f':
                            if(String.valueOf(palavra).equalsIgnoreCase("references")){
                                return new Token(TokenType.REFERENCES , String.valueOf(palavra)); // retornar token para references
                            }
                            state =20;//possibilidade de ser um id
                            break;
                        case 'n':
                            if(String.valueOf(palavra).equalsIgnoreCase("rename")){
                                return new Token(TokenType.RENAME , String.valueOf(palavra)); // retornar token para rename
                            }
                            state =20;//possibilidade de ser um id
                            break;
                        default:
                            state =20;//possibilidade de ser um id
                            break;
                    }
                    break;
                case 15:
                    switch (letra) {
                        case 'r':
                            if(String.valueOf(palavra).equalsIgnoreCase("varchar")){
                                return new Token(TokenType.VARCHAR , String.valueOf(palavra)); // retornar token para varchar
                            }
                            state =20;//possibilidade de ser um id
                            break;
                        case 'l':
                            if(String.valueOf(palavra).equalsIgnoreCase("values")){
                                return new Token(TokenType.VALUES , String.valueOf(palavra)); // retornar token para values
                            }
                            state =20;//possibilidade de ser um id
                            break;
                        default:
                            state =20;//possibilidade de ser um id
                            break;
                    }
                    break;
                case 21: //Estado que retorna token de erro generico
                	System.out.print("ponto_2_erro "+ String.valueOf(letra));
                	return new Token(TokenType.ERROR ,String.valueOf(palavra));
                
            }//end switch state
        }//end for word

        return null; // No reserved word found
    }

}
