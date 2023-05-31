package lk.ijse.dep10.mediaplayer;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class AppInitializer extends Application {
    private MediaPlayer mediaPlayer;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.show();
        mainscene(primaryStage);


    }
    private void mainscene(Stage stage){
        stage.setTitle("Media Player");

        Button btnOpen =new Button("OPEN");
        btnOpen.setPrefWidth(200);
        btnOpen.setStyle("-fx-background-color: #383535;-fx-background-radius: 10px");
        btnOpen.setTextFill(Color.WHITE);
        btnOpen.setBackground(Background.fill(Color.GRAY));
        Label lbl =new Label("..........Now Playing..........");
        lbl.setBackground(Background.fill(Color.WHITE));
        lbl.setPrefWidth(800);
        ImageView imgPlay =new ImageView();
        ImageView imgPause =new ImageView();
        ImageView imgStop =new ImageView();
        ImageView imgRepeat =new ImageView();
        ImageView imgVolume =new ImageView();

        Slider sldr =new Slider();
        sldr.setPrefWidth(100);


        Image iconStop =new Image(this.getClass().getResource("/imgPlay/stop-button.png").toString());
        Image iconPlay =new Image(this.getClass().getResource("/imgPlay/play-button.png").toString());
        Image iconPause =new Image(this.getClass().getResource("/imgPlay/pause-button.png").toString());
        Image iconRepeat =new Image(this.getClass().getResource("/imgPlay/repeat-button.png").toString());
        Image iconVolume =new Image(this.getClass().getResource("/imgPlay/volume.png").toString());
        Image iconmute =new Image(this.getClass().getResource("/imgPlay/mute.png").toString());
        imgPlay.setImage(iconPlay);
        imgPause.setImage(iconPause);
        imgStop.setImage(iconStop);
        imgRepeat.setImage(iconRepeat);
        imgVolume.setImage(iconVolume);
        imgPlay.setFitWidth(100);
        imgPlay.setFitHeight(100);
        imgPause.setFitWidth(100);
        imgPause.setFitHeight(100);
        imgStop.setFitWidth(100);
        imgStop.setFitHeight(100);
        imgRepeat.setFitWidth(100);
        imgRepeat.setFitHeight(100);
        imgVolume.setFitWidth(50);
        imgVolume.setFitHeight(50);


        HBox hBox =new HBox(imgPlay,imgPause,imgStop,imgRepeat,imgVolume,sldr);
        hBox.setSpacing(20);
        hBox.setAlignment(Pos.CENTER);
        hBox.setBackground(Background.fill(Color.DARKGRAY));
        VBox vbox =new VBox(btnOpen,lbl,hBox);
        lbl.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10));
        vbox.setBackground(Background.fill(Color.GRAY));
        stage.setScene(new Scene(vbox));


        stage.setMinWidth(800);
        stage.setMinHeight(400);
        stage.centerOnScreen();

        //MediaPlayer mediaPlayer =new MediaPlayer()

        btnOpen.setOnAction(actionEvent -> {
            FileChooser fileChooser= new FileChooser();
            fileChooser.setTitle("file chooser");
            File audioFile = fileChooser.showOpenDialog(null);
            if (audioFile != null){
                Media media = new Media(audioFile.toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                //mediaPlayer.play();
                lbl.setText(audioFile.toString());

            }
        });
        //mediaPlayer.play();
        imgPlay.setOnMouseEntered(event -> imgPlay.setOpacity(0.6));
        imgPlay.setOnMouseExited(event -> imgPlay.setOpacity(1));
        imgPlay.setOnMousePressed(event -> imgPlay.setEffect(new InnerShadow(10, Color.BLACK)));
        imgPlay.setOnMouseReleased(event ->{
            mediaPlayer.play();
        });


        imgStop.setOnMouseEntered(event -> imgStop.setOpacity(0.6));
        imgStop.setOnMouseExited(event -> imgStop.setOpacity(1));
        imgStop.setOnMousePressed(event -> imgStop.setEffect(new InnerShadow(10, Color.BLACK)));
        imgStop.setOnMouseReleased(event ->{
            mediaPlayer.stop();
        });

        imgPause.setOnMouseEntered(event -> imgPause.setOpacity(0.6));
        imgPause.setOnMouseExited(event -> imgPause.setOpacity(1));
        imgPause.setOnMousePressed(event -> imgPause.setEffect(new InnerShadow(10, Color.BLACK)));
        imgPause.setOnMouseReleased(event ->{
            mediaPlayer.pause();
        });

        imgRepeat.setOnMouseEntered(event -> imgRepeat.setOpacity(0.6));
        imgRepeat.setOnMouseExited(event -> imgRepeat.setOpacity(1));
        imgRepeat.setOnMousePressed(event -> imgRepeat.setEffect(new InnerShadow(10, Color.BLACK)));
        imgRepeat.setOnMouseReleased(event ->{
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        });

        imgVolume.setOnMouseEntered(mouseEvent -> imgVolume.setOpacity(0.6));
        imgVolume.setOnMouseExited(mouseEvent -> imgVolume.setOpacity(1));
        imgVolume.setOnMousePressed(mouseEvent -> {
            if (mediaPlayer!=null){
                if (!mediaPlayer.isMute()){
                    imgVolume.setImage(iconmute);
                    mediaPlayer.setMute(true);
                }else {
                    imgVolume.setImage(iconVolume);
                    mediaPlayer.setMute(false);
                }
            }
        });

        sldr.setOnMouseDragged(mouseEvent -> {
            mediaPlayer.setVolume(sldr.getValue());

        });
        sldr.setOnMouseClicked(mouseEvent -> mediaPlayer.setVolume(sldr.getValue()));



    }
}
