/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suport;

/**
 *
 * @author Johnata Rodrio and Iago Bachega
 */
public class Symbol {
    private String id;
    private Token token;
    private String categoria;
    private String tipo;

    public Symbol(Token token, String categoria, String tipo) {
        this.id = token.getLexeme() + categoria;
        this.token = token;
        this.categoria = categoria;
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }
    
    public String getLexeme(){
        return this.token.getLexeme();
    }
    
    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object obj) {
        Symbol s = (Symbol) obj;
        return this.id.equals(s.getId());
    }
}
