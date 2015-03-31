public enum TokenType {
	
	digit("-?[0-9]+"),
	espaco("[ \t\f\r\n]+");
	
		
	public String pattern;
	
	private TokenType (String pattern) {
        this.pattern = pattern;
    }
}
