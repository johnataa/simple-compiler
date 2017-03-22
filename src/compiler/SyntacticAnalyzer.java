/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import suport.Quadruple;
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
    private int prox;
    private int temp;
    private SymbolTable symbolTable;
    private List<Token> tokenList;
    private List<Quadruple> quadrupleList;

    public SyntacticAnalyzer() {
        this.error = false;
        this.symbolTable = new SymbolTable();
        this.quadrupleList = new ArrayList<>();
        this.tokenList = new ArrayList<>();
        this.prox = 1;
        this.temp = 1;
    }
    
    public void execute(){
        A();
        if (!error){
            Collections.sort(quadrupleList);
            for (Quadruple quadruple : quadrupleList) {
                System.out.println(quadruple);
            }
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
                    this.error = true;
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
            
            s = symbolTable.search(currentToken.getLexeme()+Symbol.VARIABLE);
            
            if((s != null) && s.getCategoria().equals(Symbol.VARIABLE)){
                currentToken = LexicalAnalyzer.getNextToken();
                if (currentToken.getLexeme().equals(":=")){
                    Symbol Edir = E(s);
                    if (!error){
                        Quadruple q = new Quadruple(this.prox++, ":=", Edir.toString(), Symbol.EMPTY, s.toString());
                        quadrupleList.add(q);
                    }
                }else {
                    error(":=");
                }
            }else{
                this.error = true;
                System.err.println("Doesn't contain '" + currentToken.getLexeme() + "' in symbol table!");
            }
        } else if (currentToken.getLexeme().equals("if")){
            Symbol Edir = E(s);
            currentToken = LexicalAnalyzer.getNextToken();
            if (currentToken.getLexeme().equals("then")){
                int quad = this.prox++;
                S();
                if (!error){
                    Quadruple q = new Quadruple(quad, "JF", Edir.toString() , "" + this.prox, Symbol.EMPTY);
                    quadrupleList.add(q);
                }
            } else {
                error("then");
            }
        } else {
            error("id OR if");
        }
    }
    
    private Symbol E(Symbol Eesq){
        Symbol Tdir = T(Eesq);
        Symbol Rdir = R(Tdir);
        
        return Rdir;        
    }
    
    private Symbol T(Symbol s){
        currentToken = LexicalAnalyzer.getNextToken();
        if (!currentToken.getTag().equals(Tag.ID)){
            error("id");
            return null;
        }else{
            Symbol saux = symbolTable.search(currentToken.getLexeme()+Symbol.VARIABLE);
            if (saux != null){
                if(s != null){
                    if(s.getTipo().equals(saux.getTipo())){
                        return saux;
                    }else{
                        this.error = true;
                        System.err.println("Type of variables must be equal!");
                        return null;
                    }
                }else{
                    return saux;
                }
            } else {
                this.error = true;
                System.err.println("Doesn't contain '" + currentToken.getLexeme() + "' in symbol table!");
                return null;
            }
        } 
    }
    
    private Symbol R(Symbol Resq){
        if (LexicalAnalyzer.nextTokenIs("+")){
            currentToken = LexicalAnalyzer.getNextToken();
            Symbol R1esq = T(Resq);
            Symbol R1dir = R(R1esq);
            Symbol Rdir = geratemp();
            
            if (!error){
                Quadruple q = new Quadruple(this.prox++, "+", Resq.toString(), R1dir.toString(), Rdir.toString());
                quadrupleList.add(q);
            }            
            return Rdir;
        } else {
            return Resq;
        }
    }
    
    private Symbol geratemp(){
        Symbol b = new Symbol("t" + this.temp++);
        return b;        
    }
    
    private void error(String expected){
        this.error = true;
        System.err.println("Syntactic Error!\nExpected '" + expected + "'. Found:\n" + this.currentToken);
    } 
}
