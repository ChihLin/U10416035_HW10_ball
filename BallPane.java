//U10416035 Cheng Chih Lin

import java.util.Random;
import javafx.application.Application;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javafx.scene.control.Button;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;


public class BallPane extends Pane {
	public final double radius = 20;
	private double x = radius, y = radius;
	private double dx = 1, dy = 1;
	Circle circle = new Circle(x, y, radius);
	private Timeline animation;

	
	public BallPane() {
		circle.setFill(Color.GREEN); // Set ball color
		getChildren().add(circle); // Place a ball into this pane
		
		Button btAdd = new Button("+");
		Button btSubtract = new Button("-");

		HBox hBox = new HBox(10);
		hBox.getChildren().addAll(btAdd, btSubtract);
		getChildren().add(hBox); // Place a ball into this pane
		
		// Add or remove a ball
		btAdd.setOnAction(e ->add());
		btSubtract.setOnAction(e -> subtract());
		
		// Create an animation for moving the ball
		animation = new Timeline(new KeyFrame(Duration.millis(3), e -> moveBall()));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play(); // Start animation
	}
	//add the speed
	public void add() {
		increaseSpeed();
    }
    //subtract the speed
    public void subtract() {
		decreaseSpeed();
    }
	
	public void play() {
		animation.play();
	}

	public void pause() {
		animation.pause();
	}

	public void increaseSpeed() {
		animation.setRate(animation.getRate() + 0.1);
	}

	public void decreaseSpeed() {	
		animation.setRate(animation.getRate() > 0 ? animation.getRate() - 0.1 : 0);
	}

	public DoubleProperty rateProperty() {
		return animation.rateProperty();
	}
  
	protected void moveBall() {
		
		// Check boundaries
		for(Node node : this.getChildren()){
			//Ball ball = (Ball) node;
		if (x < radius || x > getWidth() - radius) {
		dx *= -1; // Change ball move direction
		}
		if (y < radius || y > getHeight() - radius) {
		dy *= -1; // Change ball move direction
		}

		// Adjust ball position
		x += dx;
		y += dy;
		circle.setCenterX(x);
		circle.setCenterY(y);
		}
	}
}
