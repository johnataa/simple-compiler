/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;

import java.util.HashMap;
import java.util.Map;
import suport.Symbol;

/**
 *
 * @author Johnata Rodrigo and Iago Bachega
 */
public class SymbolTable {
    private Map<String, Symbol> table;

    public SymbolTable() {
        this.table = new HashMap<>();
    }
    
    public void insert(Symbol s){
        Symbol aux = search(s.getId());
        if (aux == null){
            this.table.put(s.getId(), s);
        }
    }
    
    public Symbol search(String id){
        return this.table.get(id);
    }
}
