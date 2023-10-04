package com.example;
 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class PrimaryController implements Initializable{

     @FXML Pagination pagination;

    private int pagina = 1;

    public FlowPane carregar(){

        try {
            var url = new URL("https://api.pokemontcg.io/v2/cards?page=" + pagina);
            var con = url.openConnection();
            con.connect();

            var is = con.getInputStream();

            var reader = new BufferedReader(new InputStreamReader(is));
            var json = reader.readLine();

            var lista = jsonParaLista(json);

            return mostrarPersonagens(lista);

        } catch (IOException e) {
            e.printStackTrace();
            mostrarMensagem("Erro. " + e.getMessage()); 
        }

        return null;
    }


    private FlowPane mostrarPersonagens(List<Personagem> lista) {
        var flow = new FlowPane();
        flow.setVgap(20);
        flow.setHgap(20);

        lista.forEach(personagem ->{
            var image = new ImageView(new Image(personagem.getImg()));
            image.setFitHeight(150);
            image.setFitWidth(150);
            var labelName = new Label(personagem.getName());
            flow.getChildren().add(new VBox(image, labelName));
        }); 
        return flow;
    }
    

    private void mostrarMensagem(String mensagem){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText(mensagem);
        alert.show();
    }

     private List<Personagem> jsonParaLista(String json) throws JsonMappingException, JsonProcessingException {
        var mapper = new ObjectMapper();
        var data = mapper.readTree(json).get("data");
        List<Personagem> lista = new ArrayList<>();

        data.forEach(personagem -> {
            try {
                lista.add(mapper.readValue(personagem.toString(), Personagem.class));
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });

        return lista;
    }



    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        pagination.setPageFactory(pag -> {
            pagina = pag + 1;
            return carregar();
        });
    }

    
}
