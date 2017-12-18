package com.mycompany.jogodaforcarest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("app")
public class Partida {

    static Jogo jogo = new Jogo();

    @GET
    @Path("jogada")
    public String realizarJogada(@QueryParam("letra") String letra) {
        return jogo.descobrirLetra(letra, jogo);
    }

    @GET
    @Path("reiniciar")
    public String resetarJogo() {
        jogo.inicializarAtributos();
        jogo.setInicio(true);
        return "Jogo reiniciado";
    }

    @GET
    @Path("estado")
    public String verificarEstado() {
        return jogo.verificarRsultadoDaPartida(jogo.getAcertos(), jogo.getLetras(), jogo);
    }
}
