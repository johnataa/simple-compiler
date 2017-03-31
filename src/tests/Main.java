/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import compiler.LexicalAnalyzer;
import compiler.SyntacticAnalyzer;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        String conteudo;
        System.out.println("Entrada #1:");
        conteudo = scanFile("/home/johnata/Documentos/simple-compiler/entrada");
        System.out.println(conteudo);
        
        System.out.println("Saída #1:");
        if (LexicalAnalyzer.scanString(conteudo)){
            SyntacticAnalyzer sa = new SyntacticAnalyzer();
            sa.execute();
        }
        
        System.out.println("\n\n----------------------------------------\n");
        System.out.println("Entrada #2:");
        conteudo = scanFile("/home/johnata/Documentos/simple-compiler/entrada2");
        System.out.println(conteudo);
        
        System.out.println("Saída #2:");
        if (LexicalAnalyzer.scanString(conteudo)){
            SyntacticAnalyzer sa = new SyntacticAnalyzer();
            sa.execute();
        }
    }
    
    public static String scanFile(String path){
        String s = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while(br.ready()){
                s = s.concat(br.readLine()).concat("\n");
            }
            br.close();
            return s;
        } catch (FileNotFoundException ex) {
            System.err.println("Falha ao ler arquivo!");
        } catch (IOException ex) {
            System.err.println("Falha ao ler arquivo!");
        }
        return null;
    }    
}
