package FlappyBird;

import javafx.animation.Animation;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Bird {
    private int startedX;
    private int startedY;
    //private int gravity;
    //private int size;
    //private int speed;
    //private Image naruto;
    //private ImageInput narutoInput;
    private SequentialTransition seqT;
    private Circle shape;
    private TranslateTransition gravitation;
    private TranslateTransition jumping;

    public Bird(int size, int translateX, int translateY){
        startedX = translateX;
        startedY = translateY;
        shape = new Circle(size, Color.RED);
        shape.setTranslateX(translateX);
        shape.setTranslateY(translateY);
        gravitation = new TranslateTransition(new Duration(1000), shape);
        jumping = new TranslateTransition(new Duration(100), shape);
        seqT = new SequentialTransition(jumping, gravitation);
        String source = "file:src/FlappyBird/images/naruto.png";
        Image naruto = new Image(source, 64, 64, false, false);
        ImageInput narutoInput = new ImageInput(naruto);
        shape.setEffect(narutoInput);
    }

    public void updatePosition(){
        shape.setTranslateX(startedX);
        shape.setTranslateY(startedY);
    }

    public void jump(){
        seqT.stop();
        jumping.setFromY(shape.getTranslateY());
        jumping.setToY(shape.getTranslateY()-100);
        jumping.setCycleCount(1);
        gravitation.setFromY(shape.getTranslateY()-100);
        gravitation.setToY(640);
        gravitation.setCycleCount(Animation.INDEFINITE);
        seqT.play();
    }

    public void stopJump(){
        seqT.stop();
    }

    public Circle getShape() {
        return shape;
    }

    public void setShape(Circle shape) {
        this.shape = shape;
    }
}
