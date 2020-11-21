package com.company;


import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        GerGraph gg = new GerGraph("maps/caso1.txt");
        CaminhamentoLargura caminha = new CaminhamentoLargura(gg.graph,gg.begin);
        System.out.println(caminha.distTo(gg.end));
        caminha.exibe();
    }
}
