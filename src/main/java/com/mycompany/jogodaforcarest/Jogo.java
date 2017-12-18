package com.mycompany.jogodaforcarest;

import static com.mycompany.jogodaforcarest.Partida.jogo;
import java.util.ArrayList;
import java.util.Random;

public class Jogo {

    int erros;
    int acertos;
    Random gerador = new Random();
    BancoDePalavras bancoPalavras;
    char[] letras;
    int[] marcasao;
    ArrayList<Character> letrasRepetidas;
    int jogador;
    boolean inicio = true;
    boolean estado = true;

    public Jogo() {
        Jogo.this.inicializarAtributos();
    }

    public boolean isInicio() {
        return inicio;
    }

    public void setInicio(boolean inicio) {
        this.inicio = inicio;
    }

    public boolean isEstado() {
        return estado;
    }

    public int getJogador() {
        return jogador;
    }

    public void setJogador(int jogador) {
        this.jogador = jogador;
    }

    public char[] getLetras() {
        return letras;
    }

    public int[] getMarcasao() {
        return marcasao;
    }

    public ArrayList<Character> getLetrasRepetidas() {
        return letrasRepetidas;
    }

    public int getErros() {
        return erros;
    }

    public void setErros(int erros) {
        this.erros = erros;
    }

    public int getAcertos() {
        return acertos;
    }

    public String criarCampo(char[] lista, int[] marcador) {
        String campo = new String();
        for (int i = 0; i < lista.length; i++) {
            if (marcador[i] == 1) {
                campo += lista[i] + " ";
            } else {
                campo += "_ ";
            }
        }
        return campo;
    }

    public boolean verificarAcerto(char letra) {
        boolean cont = false;
        for (int i = 0; i < this.letras.length; i++) {
            if (letra == this.letras[i]) {
                acertos = (++acertos);
                this.marcasao[i] = 1;
                cont = true;
            }
        }
        if (cont != true) {
            erros = (++erros);
            Jogo.this.mudarJogador(jogo);
        }
        if (acertos == this.letras.length || erros == 6) {
            estado = false;
            return true;
        }

        return acertos < letras.length && erros < 6;
    }

    public String verificarRsultadoDaPartida(int acertos, char[] lista, Jogo jogo) {
        if (acertos == lista.length) {
            return jogo.criarCampo(jogo.getLetras(), jogo.getMarcasao()) + "<br>Vitoria do Jogador " + jogo.jogador;
        } else if (erros == 6) {
            return jogo.criarCampo(jogo.getLetras(), jogo.getMarcasao()) + "<br>Erros:6<br>Derrota";
        } else {
            return "O jogo esta em progresso<br>" + jogo.mostrarJogo(jogo);

        }
    }

    public boolean verificarRepeticaoDeLetra(char pl, ArrayList<Character> letrasRepetidas, Jogo j) {
        if (letrasRepetidas.contains(pl)) {
            return false;
        }
        letrasRepetidas.add(pl);
        return verificarAcerto(pl);
    }

    public String mostrarLista() {
        String lista = new String();
        for (int i = 0; i < this.bancoPalavras.getPalavras().size(); i++) {
            lista += " " + this.bancoPalavras.getPalavras().get(i);
        }
        return lista;
    }

    public String mostrarLetrasRepetidas(Jogo jogo) {
        String lista = "";
        for (int i = 0; i < jogo.letrasRepetidas.size(); i++) {
            lista += " " + jogo.letrasRepetidas.get(i);
        }
        return lista;
    }

    public boolean escolherLetra(String caracter, ArrayList<Character> letrasRepetidas, Jogo jogo) {
        char letra = caracter.toUpperCase().charAt(0);
        return verificarRepeticaoDeLetra(letra, letrasRepetidas, jogo);
    }

    public void inicializarAtributos() {
        this.bancoPalavras = new BancoDePalavras();
        int posicao = this.gerador.nextInt(this.bancoPalavras.getPalavras().size());
        String palavra = this.bancoPalavras.getPalavras().get(posicao);
        this.letras = palavra.toCharArray();
        this.marcasao = new int[this.letras.length];
        this.letrasRepetidas = new ArrayList<Character>();
        this.jogador = 1;
        this.erros = 0;
        this.acertos = 0;
        this.estado = true;
    }

    public void mudarJogador(Jogo jogo) {
        jogo.setJogador(++jogador);
        if (jogo.getJogador() == 3) {
            jogo.setJogador(1);
        }
    }

    public String descobrirLetra(String letra, Jogo jogo) {
        if (jogo.isInicio() == true) {
            jogo.setInicio(false);
            return jogo.criarCampo(jogo.getLetras(), jogo.getMarcasao()) + "<br> Escolha do Jogador 1";
        }
        if (jogo.isEstado() == false) {
            return "O jogo esta finalizado, voce nao podera mais jogar ate o jogo ser reiniciado<br>"
                    + jogo.mostrarJogo(jogo);
        } else if (jogo.escolherLetra(letra, jogo.getLetrasRepetidas(), jogo) == true) {
            return jogo.mostrarJogo(jogo);
        } else {
            return jogo.mostrarJogo(jogo) + "<br>Letra ja selecionada anteriormente";
        }
    }

    public String mostrarJogo(Jogo jogo) {
        return (jogo.criarCampo(jogo.getLetras(), jogo.getMarcasao()) + "<br>Escolha do Jogador " + jogo.getJogador()
                + "<br>Erros :" + jogo.getErros()) + "<br>Letras ja escolhidas: " + jogo.mostrarLetrasRepetidas(jogo);
    }

}
