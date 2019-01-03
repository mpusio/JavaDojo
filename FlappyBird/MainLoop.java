package FlappyBird;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;



public class MainLoop extends Application {

    private Group group1;
    private VBox vboxBtn;
    private FadeTransition fadeTransition;
    private Bird bird;
    private Bollards bollards;
    private Timeline collision;
    private Label lbScore;
    private Label lbResult;
    private int score = 0;

    private void startGame(Stage stage) {
        stage.show();
    }

    private void cleanup() {
        bird.updatePosition();
        bollards.updatePosition();
        vboxBtn.setVisible(false);
        lbResult.setVisible(false);
    }

    private void restart(Stage stage) {
        cleanup();
        collision.play();
        score = 0;
        lbScore.setText("Score: "+score);
        fadeTransition.setToValue(1);
        fadeTransition.play();
        startGame(stage);
    }

    @Override
    public void start(Stage primaryStage) {
        //Scene scene;
        //Group root;
        //ImageView imageView;
        //Button btnRestart;
        //Button btnExit;
        //Image image;

        lbScore = new Label("Score: "+ score);
        lbScore.setLayoutX(350);
        lbScore.setLayoutY(20);
        lbScore.setFont(Font.font(25));

        lbResult = new Label("Your result:  "+score);
        lbResult.setFont(Font.font("Verdana", 50));
        lbResult.setTextFill(Color.WHITE);
        lbResult.setVisible(false);

        int widthScene = 480;
        int heightScene = 640;

        Button btnRestart = new Button("Restart");
        btnRestart.setOnMouseClicked(mouseEvent -> {
            restart(primaryStage);
        });

        Button btnExit = new Button("Exit");
        btnExit.setOnMouseClicked(mouseEvent -> {
            primaryStage.close();
        });


        Image image = new Image("file:src/FlappyBird/images/background.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(image.getWidth()/2);
        imageView.setFitHeight(image.getHeight()/2);
        imageView.setX(-150);

        bird = new Bird(32, 50, heightScene/2);
        bollards = new Bollards();
        bollards.moveBollards(true);

        collision = new Timeline(new KeyFrame(new Duration(1), actionEvent -> {
            for (int i = 0; i < 100; i++) {
                if(bird.getShape().getBoundsInParent().intersects(bollards.tabLower[i].getBoundsInParent()) ||
                   bird.getShape().getBoundsInParent().intersects(bollards.tabUpper[i].getBoundsInParent()) ||
                   bird.getShape().getTranslateY()>=586 ||
                   bird.getShape().getTranslateY()<=0)
                {
                    bird.getShape().setTranslateY(bird.getShape().getTranslateY());
                    bird.stopJump();
                    fadeTransition = new FadeTransition(new Duration(1000), group1);
                    fadeTransition.setFromValue(1);
                    fadeTransition.setToValue(0);
                    fadeTransition.play();
                    bollards.stopBollards();
                    collision.stop();
                    vboxBtn.setVisible(true);
                    lbResult.setVisible(true);
                    lbResult.setText("Your result:  "+score);
                }
            }
        }));
        collision.setCycleCount(Animation.INDEFINITE);
        collision.play();

        group1 = new Group(imageView, bird.getShape());
        group1.getChildren().addAll(bollards.tabUpper);
        group1.getChildren().addAll(bollards.tabLower);
        group1.getChildren().add(lbScore);

        vboxBtn = new VBox(lbResult, btnRestart, btnExit);
        vboxBtn.setSpacing(20);
        vboxBtn.setLayoutX(60);
        vboxBtn.setLayoutY(260);
        vboxBtn.setAlignment(Pos.CENTER);
        vboxBtn.setVisible(false);

        Group root = new Group(group1, vboxBtn);

        Scene scene = new Scene(root, widthScene, heightScene);
        scene.setFill(Color.BLACK);
        scene.getStylesheets().add("FlappyBird/style.css");
        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.SPACE){
                bird.jump();
                score++;
                lbScore.setText("Score: "+score);

            }
        });

        primaryStage.setScene(scene);
        primaryStage.setTitle("Flappy Bird");
        primaryStage.setResizable(false);
        startGame(primaryStage);
    }
}
