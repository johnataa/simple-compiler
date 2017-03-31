/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package suport;

/**
 *
 * @author Johnata Rodrigo and Iago Bachega
 */
public class Token {
    private String lexeme;
    private final String tag;
    private int coodinate;

    public Token(String lexeme, String tag, int coodinate) {
        this.lexeme = lexeme;
        this.tag = tag;        
        this.coodinate = coodinate;
    }

    public String getLexeme() {
        return lexeme;
    }

    public String getTag() {
        return tag;
    }
    
    public int getCoodinate() {
        return coodinate;
    }

    public void setCoodinate(int coodinate) {
        this.coodinate = coodinate;
    }

    @Override
    public boolean equals(Object obj) {
        String lexemeObj = ((Token) obj).getLexeme();
        return this.lexeme.equals(lexemeObj);
    }   

    @Override
    public String toString() {
        return "Lexeme: " + this.lexeme + "\nTag: " + this.tag + "\nLine: " + this.coodinate + "\n"; 
    }

}
