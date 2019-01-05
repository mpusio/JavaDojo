package Froger;

import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Random;

public class Cars extends Rectangle{

    private int xGrid;
    private int yGrid;
    private TranslateTransition tt;
    private GridMap gridMap;
    private Random rand;

    public Cars(double width, double height, Paint fill, GridMap fieldToMove) {
        super(width, height, fill);
        this.gridMap = fieldToMove;
        tt = new TranslateTransition(new Duration(3000));
        rand = new Random();
        changeLook();
    }

    public void run() {
        addToGrid();
        randomStartDuration();
        randomSpeed();
        rotateShape();
        gas();
    }

    public void setLocation(int x, int y){
        this.xGrid = x;
        this.yGrid = y;
    }

    private void gas(){
        tt.setFromX(-100);
        tt.setToX(700);
        tt.setNode(this);
        tt.setAutoReverse(true);
        tt.setCycleCount(Animation.INDEFINITE);
        tt.play();
    }

    private void rotateShape(){
        RotateTransition rt = new RotateTransition(new Duration(rand.nextInt(3000)+3000), this);
        rt.setToAngle(360);
        rt.setCycleCount(Animation.INDEFINITE);
        rt.play();
    }

    private void randomSpeed(){
        tt.setDuration(new Duration(rand.nextInt(1500)+1000));
    }

    private void randomStartDuration(){
        tt.setDelay(new Duration(rand.nextInt(1000)));
    }

    private void addToGrid(){
        gridMap.add(this, this.xGrid, this.yGrid);
    }

    private void changeLook(){
        String source = "file:/home/michal/IdeaProjects/mojaApka/src/Froger/images/whiteCar.png";
        Image image = new Image(source, 64, 32, false, false);
        ImageInput imageInput = new ImageInput(image);

        this.setEffect(imageInput);
    }
}
