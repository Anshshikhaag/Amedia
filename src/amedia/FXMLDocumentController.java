/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package amedia;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;

/**
 *
 * @author hp
 */
public class FXMLDocumentController implements Initializable {
    
    private MediaPlayer mediaPlayer;
 


    @FXML
    private MediaView mediaView;
     
    private String filepath;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        FileChooser fileChooser=new FileChooser();
        FileChooser.ExtensionFilter filter= new FileChooser.ExtensionFilter("Select a file (*.mp4)","*.mp4");
        fileChooser.getExtensionFilters().add(filter);
        File file=fileChooser.showOpenDialog(null);
        filepath=file.toURI().toString();

        if(filepath!=null)
         {
           Media media=new Media(filepath);
           mediaPlayer=new MediaPlayer(media);
           mediaView.setMediaPlayer(mediaPlayer);
           mediaPlayer.play();
         }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

}
