package FlappyBird;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Random;

public class Bollards {

    Rectangle[] tabUpper;
    Rectangle[] tabLower;
    //private Random rand;
    //private int pass;
    //private TranslateTransition translateTransition;
    //private TranslateTransition translateTransition2;
    private TranslateTransition[] tabTranslateTransition = new TranslateTransition[100];
    private TranslateTransition[] tabTranslateTransition2 = new TranslateTransition[100];
    //private Image image = new Image( "file:src/FlappyBird/images/green_pattern.png");
    //private ImagePattern pattern = new ImagePattern(image, 0,0,100,100,false);

    public Bollards(){
        int pass = 240;
        int space = 300;
        Random rand = new Random();
        tabUpper = new Rectangle[100];
        tabLower = new Rectangle[100];
        Image image = new Image( "file:src/FlappyBird/images/green_pattern.png");
        ImagePattern pattern = new ImagePattern(image, 0,0,100,100,false);

        for (int i = 0; i < 100; i++) {
            int randomHeight = rand.nextInt(300)+20;
            tabUpper[i] = new Rectangle(50, randomHeight, Color.DEEPPINK);
            tabUpper[i].setTranslateX(space);
            tabUpper[i].setTranslateY(0);
            tabUpper[i].setFill(pattern);
            tabUpper[i].getStyleClass().add("my-rect");

            tabLower[i] = new Rectangle(50, 600, Color.DEEPPINK);
            tabLower[i].setTranslateX(space);
            tabLower[i].setTranslateY(pass+randomHeight);
            tabLower[i].setFill(pattern);
            tabLower[i].getStyleClass().add("my-rect");

            space += 250;
        }
    }
    public void moveBollards(boolean result){
        if (result){
            for (int i = 0; i < 100; i++) {
                TranslateTransition translateTransition = new TranslateTransition(new Duration(300000));
                tabTranslateTransition[i] = translateTransition;
                translateTransition.setNode(tabUpper[i]);
                translateTransition.setFromX(tabUpper[i].getTranslateX());
                translateTransition.setToX(tabUpper[i].getTranslateX()-200000);
                translateTransition.setCycleCount(1);
                translateTransition.play();

                TranslateTransition translateTransition2 = new TranslateTransition(new Duration(300000));
                tabTranslateTransition2[i] = translateTransition2;
                translateTransition2.setNode(tabLower[i]);
                translateTransition2.setFromX(tabLower[i].getTranslateX());
                translateTransition2.setToX(tabLower[i].getTranslateX()-200000);
                translateTransition2.setCycleCount(1);
                translateTransition2.play();
            }
        }
    }

    public void updatePosition(){
        for (int i = 0; i < 100; i++) {
            tabTranslateTransition[i].play();
            tabTranslateTransition2[i].play();
        }
    }

    public void stopBollards(){
        for (int i = 0; i < 100; i++) {
            tabTranslateTransition[i].stop();
            tabTranslateTransition2[i].stop();
        }
    }
}
