package com.company;


import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        GerGraph gg = new GerGraph("maps/caso1.txt");
        int inicio = gg.outside.get(gg.begin);
        int chegada = gg.inside.get(gg.end);
        CaminhamentoLargura caminha = new CaminhamentoLargura(gg.graph,inicio);
        System.out.println(caminha.distTo(chegada));
    }
}
