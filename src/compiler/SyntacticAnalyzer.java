/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;

import suport.Tag;
import suport.Token;

/**
 *
 * @author Johnata Rodrigo and Iago Bachega
 */
public class SyntacticAnalyzer {
    private Token currentToken;
    private boolean error;

    public SyntacticAnalyzer() {
        this.error = false;
    }
    
    public void execute(){
        A();
        if (!error){
            System.out.println("It's done!");
        }
    }
    
    private void A(){
        I();
        S();
    }
    
    private void I(){
        currentToken = LexicalAnalyzer.getNextToken();
        if (currentToken.getLexeme().equals("var")){
            D();
        } else {
            error("var");
        }
    }

    private void D() {
        L();
        currentToken = LexicalAnalyzer.getNextToken();
        if (currentToken.getLexeme().equals(":")){
            K();
            O();
        } else {
            error(":");
        }
    }
    
    private void L() {
        currentToken = LexicalAnalyzer.getNextToken();
        if (currentToken.getTag().equals(Tag.ID)){
            X();
        } else {
            error("id");
        }
    }
          
    private void K() {
        currentToken = LexicalAnalyzer.getNextToken();
        if (!(currentToken.getLexeme().equals("integer") || currentToken.getLexeme().equals("real"))){
            error("integer OR real");
        }
    }
    
    private void O(){
        if (LexicalAnalyzer.nextTokenIs(";")){
            currentToken = LexicalAnalyzer.getNextToken();
            D();
        }
    }
    
    private void X(){
        if (LexicalAnalyzer.nextTokenIs(",")){
            currentToken = LexicalAnalyzer.getNextToken();
            L();
        }
    }
    
    private void S(){
        currentToken = LexicalAnalyzer.getNextToken();
        if (currentToken.getTag().equals(Tag.ID)){
            currentToken = LexicalAnalyzer.getNextToken();
            if (currentToken.getLexeme().equals(":=")){
                E();
            }else {
                error(":=");
            }
        } else if (currentToken.getLexeme().equals("if")){
            E();
            currentToken = LexicalAnalyzer.getNextToken();
            if (currentToken.getLexeme().equals("then")){
                S();
            } else {
                error("then");
            }
        } else {
            error("id OR if");
        }
    }
    
    private void E(){
        T();
        R();
    }
    
    private void T(){
        currentToken = LexicalAnalyzer.getNextToken();
        if (!currentToken.getTag().equals(Tag.ID)){
            error("id");
        }
    }
    
    private void R(){
        if (LexicalAnalyzer.nextTokenIs("+")){
            currentToken = LexicalAnalyzer.getNextToken();
            T();
            R();
        }
    }
    
    private void error(String expected){
        this.error = true;
        System.err.println("Syntactic Error!\nExpected '" + expected + "'. Found:\n" + this.currentToken);
    } 
}
