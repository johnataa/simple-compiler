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
    
    public int getCoodinate() {
        return coodinate;
    }

    public void setCoodinate(int coodinate) {
        this.coodinate = coodinate;
    }

    @Override
    public String toString() {
        return "Lexeme: " + this.lexeme + "\nTag: " + this.tag + "\nLine: " + this.coodinate + "\n"; 
    }
    
}
