package Froger;

import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Frog extends Rectangle{
    private int gridX ;
    private int gridY ;
    private GridMap gridMap;

    public Frog(GridMap gridMap){

        this.gridMap = gridMap;
        this.setWidth(20);
        this.setHeight(20);
        this.setFill(Color.GREEN);
        this.gridX = gridMap.getColumnCount()/2;
        this.gridY = gridMap.getRowCount()-1;
        System.out.println(gridX+ " " + gridY);
        changeLook();
        addToGrid();
    }
    public void jump(KeyCode code){
        gridMap.getChildren().remove(this);

        if (code == KeyCode.RIGHT){
            gridX++;
        }
        else if (code == KeyCode.LEFT){
            gridX--;
        }
        else if (code == KeyCode.UP){
            gridY--;
        }
        else if (code == KeyCode.DOWN){
            gridY++;
        }

        gridMap.add(this, gridX, gridY);
    }
    private void addToGrid(){
        gridMap.getChildren().remove(this);
        gridMap.add(this, this.gridX, this.gridY);
    }
    private void changeLook(){
        String source = "file:/home/michal/IdeaProjects/mojaApka/src/Froger/images/Frog.png";
        Image image = new Image(source, 34, 34, false, false);
        ImageInput imageInput = new ImageInput(image);
        this.setEffect(imageInput);
    }
}
