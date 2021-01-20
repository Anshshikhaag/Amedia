/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package amedia;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;


/**
 *
 * @author hp
 */
public class FXMLDocumentController implements Initializable {
    
    private MediaPlayer mediaPlayer;
 

    @FXML
    private Button Play;
    @FXML
    private MediaView mediaView;
    private String filepath;
    @FXML
    private Slider slider;
    @FXML
    private Slider seekSlider;
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
                  DoubleProperty width=mediaView.fitWidthProperty();
                  DoubleProperty height=mediaView.fitHeightProperty();
                  width.bind(Bindings.selectDouble(mediaView.sceneProperty(),"width"));
                  height.bind(Bindings.selectDouble(mediaView.sceneProperty(),"height"));
                  
                  slider.setValue(mediaPlayer.getVolume()*100);
                  slider.valueProperty().addListener(new InvalidationListener(){
               @Override
               public void invalidated(Observable observable) {
                   mediaPlayer.setVolume(slider.getValue()/100);
               }
                                           
                  });
               mediaPlayer.currentTimeProperty().addListener((ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) -> {
                seekSlider.setValue(newValue.toSeconds());
           }); 
              seekSlider.setOnMouseClicked(new EventHandler<MouseEvent>() {
               @Override
               public void handle(MouseEvent event) {
                   mediaPlayer.seek(Duration.seconds(seekSlider.getValue()));
               }
           });
             mediaPlayer.play();
               }
    }
    
      @FXML
     private void playVideo(ActionEvent event)
     {
         try
         {
              MediaPlayer.Status status=mediaPlayer.getStatus();
         if(status==MediaPlayer.Status.PLAYING)
         {
             mediaPlayer.pause();
             Play.setText("Play");
           //  Play.setGraphic(new ImageView(new Image(new FileInputStream("Amedia/src/images/play.png"))));
          }
         else
         {
            mediaPlayer.play();
            Play.setText("Pause");
         }
         mediaPlayer.setRate(1);
         }catch(Exception e)
         {
             e.printStackTrace();
         }
        
     }
      @FXML
     private void stopVideo(ActionEvent event)
     {
         mediaPlayer.stop();
     }
      @FXML
     private void fastVideo(ActionEvent event)
     {
         mediaPlayer.setRate(1.5);
     }
      @FXML
     private void fasterVideo(ActionEvent event)
     {
         mediaPlayer.setRate(2);
     }
      @FXML
     private void slowVideo(ActionEvent event)
     {
         mediaPlayer.setRate(0.75);
     }
     @FXML
     private void slowerVideo(ActionEvent event)
     {
         mediaPlayer.setRate(0.5);
     }
     @FXML
     private void Exit(ActionEvent event)
     {
         System.exit(0);
     }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }    

   
    
   

}
