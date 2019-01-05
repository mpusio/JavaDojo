package Froger;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;


public class MainLoop extends Application {

    private int score = 0;
    private int sizeMapX = 20;
    private int sizeMapY = 10;
    private Group root = new Group();
    private GridMap gridMap = new GridMap(sizeMapX, sizeMapY);
    private Frog frog;
    private Cars cars[];
    private Gold gold;
    private Timeline tl;
    private HealthPoints healthPoints;
    private Label lbScore;

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(root, 700, 700, Color.WHITE);
        healthPoints = new HealthPoints();
        frog = new Frog(gridMap);
        cars = new Cars[sizeMapY-1];
        gold = new Gold(gridMap);

        addGridMap();
        addCars();
        addScoreLabel();
        changeBackground(scene);

        //timelines
        collision();
        catchMoney();

        scene.setOnKeyPressed(keyEvent -> {
            frog.jump(keyEvent.getCode());
            frog.setVisible(true);
        });

        root.getChildren().addAll(gridMap, healthPoints, healthPoints.getInscriptionHp(), lbScore);

        primaryStage.setTitle("Frogger");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void addCars(){
        for (int i = 1; i < sizeMapY-1; i++) {
            cars[i] = new Cars(20, 20, Color.RED, gridMap);
            cars[i].setLocation(1, i);
            cars[i].run();
            cars[i] = new Cars(20, 20, Color.VIOLET, gridMap);
            cars[i].setLocation(1, i);
            cars[i].run();
        }
    }
    private void addGridMap(){
        gridMap.setPadding(new Insets(10, 10, 10, 10));
        gridMap.setPrefSize(700, 700);
        gridMap.setAlignment(Pos.CENTER);
    }
    private void addScoreLabel(){
        lbScore = new Label("Score: "+score);
        lbScore.setLayoutX(20);
        lbScore.setLayoutY(20);
        lbScore.setFont(Font.font(20));
        lbScore.setTextFill(Color.WHITE);
        lbScore.setStyle("-fx-font-weight: bold");
        lbScore.setStyle("-fx-stroke: black;");
        lbScore.setStyle("-fx-stroke-width: 1");
    }
    private void catchMoney(){
        tl = new Timeline(new KeyFrame(new Duration(100), actionEvent -> {
            if (frog.getBoundsInParent().intersects(gold.getBoundsInParent())){
                gold.randomLocation();
                score++;
                System.out.println(score);
                lbScore.setText("Score: "+score);
            }
        }));
        tl.setCycleCount(Animation.INDEFINITE);
        tl.play();
    }
    private void changeBackground(Scene scene){
        String pathToImage = "file:/home/michal/IdeaProjects/mojaApka/src/Froger/images/road.jpg";
        ImagePattern imagePattern = new ImagePattern(new Image(pathToImage), 0, 0, 100, 70, false);
        scene.setFill(imagePattern);
    }
    private void collision(){
        tl = new Timeline(new KeyFrame(new Duration(1), actionEvent -> {
            for (int i = 1; i < sizeMapY-1; i++) {
                if (frog.getBoundsInParent().intersects(cars[i].getBoundsInParent())) {
                    healthPoints.reduceLifePoints();
                }
                else if (healthPoints.getHP()<0){
                    Platform.exit();
                }
            }
        }));
        tl.setCycleCount(Animation.INDEFINITE);
        tl.play();
    }
}
