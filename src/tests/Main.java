/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import compiler.LexicalAnalyzer;
import compiler.SyntacticAnalyzer;
import suport.Token;


/**
 *
 * @author Johnata Rodrigo and Iago Bachega
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     
        String conteudo = "var\n\ta,b,c:integer;\n\td:real\nif a + b then a:=c";
        LexicalAnalyzer.scanString(conteudo);
         
        SyntacticAnalyzer sa = new SyntacticAnalyzer();
        
        sa.execute();
        
        
    }
    
}
