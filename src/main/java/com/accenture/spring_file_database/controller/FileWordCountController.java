package com.accenture.spring_file_database.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@RestController
public class FileWordCountController {

    // TODO planejamento futuro da classe abaixo
    /*
    => A ideia no futuro é baixar o arquivo do banco (nome + id + extensao), fazer
    a contagem de palavras e depois deletar o arquivo. Visto que não encontrei uma forma
    de ler o conteudo direto do banco...
    => Uma opção é salvar o Map no banco quando for fazer upload... eu sou burro? preciso de café...
    */

    // TODO tem como passar diretorio no value???
    @GetMapping(value = "/countWords/{name}")
    public Map<String, Integer> countWords(@PathVariable String name) throws FileNotFoundException {

        Map<String, Integer> words = new HashMap<String, Integer>();
        // TODO diretorio nao pode ser hardcoded
        Scanner file = new Scanner(new File("C:\\Users\\iago.luis.g.barros\\Downloads\\" + name));
        String word;
        Integer count;

        while(file.hasNext()) {
            word = file.next();
            count = words.get(word);
            if(count != null) count++;
            else count = 1;
            words.put(word, count);
        }
        file.close();
        return words;
    }

}
