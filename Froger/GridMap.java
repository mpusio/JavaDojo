package Froger;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class GridMap extends GridPane {
    private int sizeX;
    private int sizeY;


    public GridMap(int sizeX, int sizeY){
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        createMap();
    }

    private void createMap(){

        for (int i = 0; i < sizeX; i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPercentWidth(100/sizeX);
            this.getColumnConstraints().add(columnConstraints);
        }

        for (int i = 0; i < sizeY; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPercentHeight(100/sizeY);
            this.getRowConstraints().add(rowConstraints);
        }
    }
}
