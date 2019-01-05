package Froger;

import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Gold extends Rectangle{

    private int gridX;
    private int gridY;
    private GridMap gridMap;
    private Random rand;

    public Gold(GridMap gridMap){
        this.setHeight(30);
        this.setWidth(30);
        this.gridMap = gridMap;
        this.rand = new Random();
        changeLook();
        addToGrid();
        randomLocation();
    }

    public void randomLocation(){
        this.gridX = rand.nextInt(gridMap.getColumnCount()-2);
        this.gridY = rand.nextInt(gridMap.getRowCount()-2);
        addToGrid();
    }
    private void addToGrid(){
        gridMap.getChildren().remove(this);
        gridMap.add(this, this.gridX, this.gridY);
    }
    private void changeLook(){
        String source = "file:/home/michal/IdeaProjects/mojaApka/src/Froger/images/money.png";
        Image image = new Image(source, 50, 50, false, false);
        ImageInput imageInput = new ImageInput(image);
        this.setEffect(imageInput);
    }

}
