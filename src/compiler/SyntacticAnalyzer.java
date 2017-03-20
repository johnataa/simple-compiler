/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;

import java.util.List;
import suport.Symbol;
import suport.Tag;
import suport.Token;

/**
 *
 * @author Johnata Rodrigo and Iago Bachega
 */
public class SyntacticAnalyzer {
    private Token currentToken;
    private boolean error;
    private SymbolTable symbolTable = new SymbolTable();
    private List<Token> tokenList;

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
            tokenList.add(currentToken);
            X();
        } else {
            error("id");
        }
    }
          
    private void K() {
        currentToken = LexicalAnalyzer.getNextToken();
        if (!(currentToken.getLexeme().equals("integer") || currentToken.getLexeme().equals("real"))){
            error("integer OR real");
        }else{
            for (Token t : tokenList) {
                Symbol s = new Symbol(t, Symbol.VARIABLE, currentToken.getLexeme());
                if(!symbolTable.equals(s)){
                    symbolTable.insert(s);
                }else{
                    System.err.println("Already contains " + t.getLexeme() + " in symbol table!");
                }
            }
            tokenList.clear();
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
        Symbol s = null;
        if (currentToken.getTag().equals(Tag.ID)){
            
            currentToken = LexicalAnalyzer.getNextToken();
            
            s = symbolTable.search(currentToken.getLexeme());

            if((s != null) && s.getCategoria().equals(Symbol.VARIABLE)){
                if (currentToken.getLexeme().equals(":=")){
                    E(s);

                }else {
                    error(":=");
                }
            }else{
                System.err.println("Doesn't contain " + currentToken.getLexeme() + " in symbol table!");
            }
        } else if (currentToken.getLexeme().equals("if")){
            E(s);
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
    
    private void E(Symbol s){
        Symbol s2 = T(s);
        R(s2);
        
    }
    
    private Symbol T(Symbol s){
        currentToken = LexicalAnalyzer.getNextToken();
        if (!currentToken.getTag().equals(Tag.ID)){
            error("id");
            return null;
        }else{
            Symbol saux = symbolTable.search(currentToken.getLexeme());
            if(s != null){
                if(s.getTipo().equals(saux.getTipo())){
                    return saux;
                }else{
                    System.err.println("Type of variables must be equal!");
                    return null;
                }
            }else{
                 return saux;
            }
        } 
    }
    
    private void R(Symbol s){
        if (LexicalAnalyzer.nextTokenIs("+")){
            currentToken = LexicalAnalyzer.getNextToken();
            Symbol s2 = T(s);
            R(s2);
            
        }
    }
    
    private void error(String expected){
        this.error = true;
        System.err.println("Syntactic Error!\nExpected '" + expected + "'. Found:\n" + this.currentToken);
    } 
}
