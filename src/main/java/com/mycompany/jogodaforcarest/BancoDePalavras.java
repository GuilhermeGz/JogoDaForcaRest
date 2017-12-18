package com.mycompany.jogodaforcarest;

import java.util.ArrayList;

public class BancoDePalavras {

    protected ArrayList<String> Palavras = new ArrayList<String>();

    public BancoDePalavras() {
        this.Palavras.add("LEITE");
        this.Palavras.add("QUEIJO");
        this.Palavras.add("BANANA");
        this.Palavras.add("CAJU");
        this.Palavras.add("GOIABA");
        this.Palavras.add("TOMATE");
        this.Palavras.add("ARROZ");
        this.Palavras.add("LARANJA");
        this.Palavras.add("CARNE");
        this.Palavras.add("BISCOITO");
        this.Palavras.add("BOLACHA");
    }

    public ArrayList<String> getPalavras() {
        return Palavras;
    }

    public void setPalavras(ArrayList<String> palavras) {
        Palavras = palavras;
    }

}
