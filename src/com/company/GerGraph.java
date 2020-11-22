package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GerGraph {

    Graph graph;
    Map<Character, Integer> outside;
    Map<Character, Integer> inside;
    char begin;
    char end;

    GerGraph(String arq) throws IOException {
        outside = new HashMap<>();
        inside = new HashMap<>();
        BufferedReader buffRead = new BufferedReader(new FileReader(arq));
        String line = "";
        int countLine = 0;
        //pega primeira linha que indica o inicio e fim do caminhamento
        line = buffRead.readLine();
        begin = line.charAt(0);
        end = line.charAt(2);
        //segunda linha auxilia a montagem do grafo com o tamanho e o começo do grafo
        line = buffRead.readLine();
        char[] old;
        char[] chars = line.toCharArray();
        graph = new Graph(line.length() * line.length());

        for (int i = 0; i < chars.length; i++){
            char aux = chars[i];
            if(chars.length == i + 1){
                if(aux != '.' && aux != '#'){
                    outside.put(aux, i);
                }
            }else if(aux == '.'){
                if(chars[i+1] != '#'){
                    graph.addEdge(i,i+1);
                }
            } else if(aux != '#'){
                outside.put(aux, i);
                if(chars[i+1] != '#'){
                    graph.addEdge(i,i+1);
                }
            }
        }

        line = buffRead.readLine();
        countLine++;

        while (true){
            old = chars;
            chars = line.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char aux = chars[i];
                char oldAux = old[i];
                char nextAux;
                int ver = chars.length * countLine + i;
                int oldVer = chars.length * (countLine - 1) + i;
                if(chars.length <= i + 1){
                    if(aux == '.'){
                        if(oldAux != '#'){
                            graph.addEdge(oldVer, ver);
                        }
                    } else if(aux != '#'){
                        outside.put(aux, ver);
                        if(oldAux != '#'){
                            graph.addEdge(oldVer, ver);
                        }
                    }
                }else if(aux == '.'){
                    nextAux = chars[i+1];
                    if(nextAux != '#' && nextAux != ' '){
                        graph.addEdge(ver,ver+1);
                    }
                    if(oldAux != '#' && oldAux != ' '){
                        graph.addEdge(ver, oldVer);
                    }
                } else if(aux != ' ' && aux != '#'){
                    nextAux = chars[i+1];
                    //verificar se é no outside ou no inside
                    if(countLine != chars.length - 1 && i < chars.length - 1 && i > 0){
                        inside.put(aux,ver);
                    }else {
                        outside.put(aux, ver);
                    }
                    if(nextAux != '#' && nextAux != ' '){
                        graph.addEdge(ver,ver+1);
                    }
                    if(oldAux != '#' && oldAux != ' '){
                        graph.addEdge(ver, oldVer);
                    }
                }
            }


            line = buffRead.readLine();
            if(line == null)
                break;
            countLine++;
        }

        for (char a: outside.keySet()){
            int out = outside.get(a);
            int in = inside.get(a);
            graph.addEdge(out, in);
        }
        System.out.println("pause");
    }

}
