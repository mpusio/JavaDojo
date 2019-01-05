package Froger;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;


public class HealthPoints extends Rectangle{

    Label inscriptionHp = new Label("HP: ");

    public HealthPoints(){
        setters();
    }

    public void reduceLifePoints(){
        this.setWidth(this.getWidth()-0.1);
    }

    private void setters(){
        this.setHeight(20);
        this.setWidth(150);
        this.setLayoutX(530);
        this.setLayoutY(10);
        this.setFill(Color.RED);
        this.setStyle("-fx-stroke: black;");
        this.setStyle("-fx-stroke-width: 1");
        this.setStyle("-fx-stroke-line-cap: square;");

        inscriptionHp.setLayoutX(500);
        inscriptionHp.setLayoutY(10);
        inscriptionHp.setFont(Font.font(15));
        inscriptionHp.setTextFill(Color.RED);
        inscriptionHp.setStyle("-fx-font-weight: bold");
        inscriptionHp.setStyle("-fx-stroke: black;");
        inscriptionHp.setStyle("-fx-stroke-width: 1");

    }

    public Label getInscriptionHp() {
        return inscriptionHp;
    }

    public void setInscriptionHp(Label inscriptionHp) {
        this.inscriptionHp = inscriptionHp;
    }

    public double getHP() {
        return this.getWidth();
    }

    public void setHP(int hp) {
        this.setWidth(hp);
    }
}
