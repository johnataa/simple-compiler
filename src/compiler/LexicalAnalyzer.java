/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compiler;

import java.util.ArrayDeque;
import java.util.Queue;
import suport.Tag;
import suport.Token;

/**
 *
 * @author Johnata Rodrigo and Iago Bachega
 */
public class LexicalAnalyzer {
    private static Queue<Token> tokens;
    private static Queue<Character> text;
    private static Character peek;
    
    public static void scanString(String str){
        if (text == null){
            text = parseToCharQueue(str);
        } else if (tokens != null){
            if (tokens.isEmpty()){
                text = parseToCharQueue(str);
            } else {
                System.out.println("String para analise já está configurada e ainda há tokens para ser lido.");
                System.out.println("Impossível realizar esta ação!");
                return;
            }
        }
        
        tokens = new ArrayDeque<>();
        int linha = 1;
        Token t;
        nextChar();
        do {
            t = null;
            while(true){
                if (peek == '\n'){
                    linha++;
                } else if (peek != ' ' && peek != '\t') {
                    break;
                }
                nextChar();
            }
            
            switch (peek) {
                case '+':
                    t = new Token("+", Tag.OPERATOR, linha);
                    tokens.add(t);
                    break;
                case ',':
                    t = new Token(",", Tag.SS, linha);
                    tokens.add(t);
                    break;
                case ':':
                    if (nextChar('=')){
                        t = new Token(":=", Tag.ATTRIBUTION, linha);
                        nextChar();
                    } else {
                       t = new Token(":", Tag.SS, linha);
                    }
                    tokens.add(t);
                    break;
                case ';':
                    t = new Token(";", Tag.SS, linha);
                    tokens.add(t);
                    break;
            }
            
            if (t != null){
                nextChar();
                continue;
            }
            
            StringBuilder lex = new StringBuilder();
            do {
                lex.append(peek);
                nextChar();
            }while(peek != null && Character.isLetter(peek));
            
            String lexema = lex.toString();
            t = findToken(lexema, linha);
            if (t == null){
                t = new Token(lexema, Tag.ID, linha);
            }
            tokens.add(t);        
        } while(peek != null);
    }
    
    public static Token getNextToken() {
        if (tokens != null && !tokens.isEmpty()){
            return tokens.poll();
        }
        return null;
    }
    
    public static boolean nextTokenIs(String token) {
        if (tokens != null && !tokens.isEmpty()){
            return tokens.peek().getLexeme().equals(token);
        }
        return false;        
    }
    
    
    /*********************************/
    /******** PRIVATE METHODS ********/
    /*********************************/
    
    
    private static void nextChar(){
        if (!text.isEmpty()){
            peek = text.poll();
        } else {
            peek = null;
        }
        
    }
    
    private static boolean nextChar(char c){
        char b = text.peek();
        if (b != c){
            return false;
        }
        peek = ' ';
        return true;
    }
    
    private static Queue<Character> parseToCharQueue(String t){
        Queue<Character> fc = new ArrayDeque<>();
        for (char c : t.toCharArray()) {
            fc.add(c);
        }
        return fc;
    }
    
    private static Token findToken(String lexema, int linha){
        Token t = null;
        String[] lexemas = "var integer real if then".split(" ");
        for (String l : lexemas) {
            if (lexema.equals(l)){
                t = new Token(lexema, Tag.RESERVED_WORD, linha);
                break;
            }
        }
        return t;
    }
}