//CSE205 Honors Project
//Name: Pradyoth Velagapudi
//StudentID: 1224389868
//Lecture time: 1:30 Tu/Th

import javafx.scene.layout.*;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class GamePane extends BorderPane {

	// Task 1: Declare all instance variables listed in UML diagram
	private Pane gameCanvas;
	private Rectangle road1;
	private Rectangle road2;
	private Rectangle car1;
	private Rectangle car2;
	private Rectangle car3;
	private Rectangle car4;
	private PathTransition pathTransitionCar1;
	private PathTransition pathTransitionCar2;
	private PathTransition pathTransitionCar3;
	private PathTransition pathTransitionCar4;
	private PathTransition pathTransitionLog;
	private PathTransition pathTransitionLog2;
	private PathTransition pathTransitionBee;
	private Timeline timelineLilyPad1;
	private Timeline timelineLilyPad2;
	private Circle bush1;
	private Circle bush2;
	private Circle bush3;
	private Rectangle river;
	private Rectangle log;
	private Rectangle log2;
	private Circle lilyPad1;
	private Circle lilyPad2;
	private Group bee;
	private Group beehive;
	private Circle chickenBody;
	private Polygon chickenBeak;
	private Rectangle chickenHead;
	private Group chicken;
	private Double[] chickenBeakPoints = { 380.0, 750.0, 420.0, 750.0, 400.0, 710.0 };
	private boolean wPressed;
	private boolean sPressed;
	private boolean aPressed;
	private boolean dPressed;
	private boolean alive;
	private Text deathMessage;
	private Text restartMessage;
	private Rectangle messageBox;
	private int numLives;
	private Rectangle livesBox;
	private Text livesMessage;
	private Timer t;

	// Task 2: Implement the constructor
	public GamePane() {
		// Set up the layout
		gameCanvas = new Pane();
		gameCanvas.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		HBox hbox1 = new HBox(20);
		hbox1.setMinHeight(20);
		hbox1.setMinWidth(40);
		hbox1.setAlignment(Pos.CENTER);
		hbox1.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
		setCenter(gameCanvas);
		gameCanvas.setFocusTraversable(true);
		gameCanvas.addEventHandler(KeyEvent.ANY, new KeyHandler());
		wPressed = false;
		sPressed = false;
		aPressed = false;
		dPressed = false;
		alive = true;
		numLives = 5;
		t = new Timer();
		t.start();

		road1 = new Rectangle();
		road1.setX(0);
		road1.setY(600);
		road1.setWidth(800);
		road1.setHeight(100);
		road1.setFill(Color.DARKGREY);
		road1.setStroke(Color.DARKGREY);

		road2 = new Rectangle();
		road2.setX(0);
		road2.setY(450);
		road2.setWidth(800);
		road2.setHeight(100);
		road2.setFill(Color.DARKGREY);
		road2.setStroke(Color.DARKGREY);

		car1 = new Rectangle();
		car1.setX(200);
		car1.setY(625);
		car1.setWidth(100);
		car1.setHeight(50);
		car1.setArcHeight(10);
		car1.setArcWidth(10);
		car1.setFill(Color.ORANGE);
		car1.setStroke(Color.BLACK);

		car2 = new Rectangle(-100, 500, 100, 50);
		car2.setArcHeight(10);
		car2.setArcWidth(10);
		car2.setFill(Color.BLUE);
		car2.setStroke(Color.BLACK);

		car3 = new Rectangle(900, 625, 100, 50);
		car3.setArcHeight(10);
		car3.setArcWidth(10);
		car3.setFill(Color.RED);
		car3.setStroke(Color.BLACK);

		car4 = new Rectangle(-100, 500, 100, 50);
		car4.setArcHeight(10);
		car4.setArcWidth(10);
		car4.setFill(Color.VIOLET);
		car4.setStroke(Color.BLACK);

		bush1 = new Circle(400, 575, 20);
		bush1.setFill(Color.OLIVE);
		bush1.setStroke(Color.SADDLEBROWN);
		bush2 = new Circle(200, 575, 20);
		bush2.setFill(Color.OLIVE);
		bush2.setStroke(Color.SADDLEBROWN);
		bush3 = new Circle(600, 575, 20);
		bush3.setFill(Color.OLIVE);
		bush3.setStroke(Color.SADDLEBROWN);

		river = new Rectangle(0, 240, 800, 160);
		river.setFill(Color.DODGERBLUE);

		log = new Rectangle(0, 350, 200, 50);
		log.setFill(Color.SADDLEBROWN);

		log2 = new Rectangle(-200, 240, 200, 50);
		log2.setFill(Color.SADDLEBROWN);

		lilyPad1 = new Circle(266, 320, 30);
		lilyPad1.setFill(Color.LIMEGREEN);
		lilyPad1.setStroke(Color.GREEN);

		lilyPad2 = new Circle(533, 320, 30);
		lilyPad2.setFill(Color.LIMEGREEN);
		lilyPad2.setStroke(Color.GREEN);

		Ellipse beeBody = new Ellipse(50, 50, 20, 10);
		beeBody.setFill(Color.YELLOW);
		beeBody.setStroke(Color.BLACK);
		Ellipse beeWing1 = new Ellipse(50, 40, 10, 15);
		beeWing1.setFill(Color.LIGHTGRAY);
		Ellipse beeWing2 = new Ellipse(50, 60, 10, 15);
		beeWing2.setFill(Color.LIGHTGRAY);
		Line beeStripe = new Line(50, 50, 50, 50);
		beeStripe.setFill(Color.BLACK);
		beeStripe.setStrokeWidth(20);

		bee = new Group();
		bee.getChildren().add(beeWing1);
		bee.getChildren().add(beeWing2);
		bee.getChildren().add(beeBody);
		bee.getChildren().add(beeStripe);

		Ellipse bh1 = new Ellipse(400, 130, 30, 20);
		bh1.setFill(Color.YELLOW);
		Ellipse bh2 = new Ellipse(400, 110, 20, 10);
		bh2.setFill(Color.YELLOW);
		Ellipse bh3 = new Ellipse(400, 100, 10, 10);
		bh3.setFill(Color.YELLOW);
		Ellipse bh4 = new Ellipse(400, 130, 5, 10);
		bh4.setFill(Color.BLACK);
		beehive = new Group();
		beehive.getChildren().add(bh1);
		beehive.getChildren().add(bh2);
		beehive.getChildren().add(bh3);
		beehive.getChildren().add(bh4);

		chickenBody = new Circle(400, 750, 20);
		chickenBody.setFill(Color.WHITE);
		chickenBody.setStroke(Color.BLACK);
		chickenBeak = new Polygon();
		chickenBeak.getPoints().addAll(chickenBeakPoints);
		chickenBeak.setFill(Color.YELLOW);
		chickenBeak.setStroke(Color.BLACK);
		chickenHead = new Rectangle(395, 745, 10, 20);
		chickenHead.setArcHeight(10);
		chickenHead.setArcWidth(10);
		chickenHead.setFill(Color.RED);
		chickenHead.setStroke(Color.BLACK);

		chicken = new Group();
		chicken.getChildren().add(chickenBeak);
		chicken.getChildren().add(chickenBody);
		chicken.getChildren().add(chickenHead);

		Line pathCar1 = new Line(900, 650, -100, 650);
		pathTransitionCar1 = new PathTransition();
		pathTransitionCar1.setDuration(Duration.millis(4000));
		pathTransitionCar1.setPath(pathCar1);
		pathTransitionCar1.setNode(car1);
		pathTransitionCar1.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		pathTransitionCar1.setCycleCount(Timeline.INDEFINITE);
		pathTransitionCar1.play();

		Line pathCar2 = new Line(-100, 500, 900, 500);
		pathTransitionCar2 = new PathTransition();
		pathTransitionCar2.setDuration(Duration.millis(3000));
		pathTransitionCar2.setPath(pathCar2);
		pathTransitionCar2.setNode(car2);
		pathTransitionCar2.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		pathTransitionCar2.setCycleCount(Timeline.INDEFINITE);
		pathTransitionCar2.play();

		Line pathCar3 = new Line(900, 650, -100, 650);
		pathTransitionCar3 = new PathTransition();
		pathTransitionCar3.setDuration(Duration.millis(4000));
		pathTransitionCar3.setPath(pathCar3);
		pathTransitionCar3.setNode(car3);
		pathTransitionCar3.setDelay(Duration.millis(2000));
		pathTransitionCar3.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		pathTransitionCar3.setCycleCount(Timeline.INDEFINITE);
		pathTransitionCar3.play();

		Line pathCar4 = new Line(-100, 500, 900, 500);
		pathTransitionCar4 = new PathTransition();
		pathTransitionCar4.setDuration(Duration.millis(3000));
		pathTransitionCar4.setPath(pathCar4);
		pathTransitionCar4.setNode(car4);
		pathTransitionCar4.setDelay(Duration.millis(1500));
		pathTransitionCar4.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		pathTransitionCar4.setCycleCount(Timeline.INDEFINITE);
		pathTransitionCar4.play();

		Line pathLog = new Line(-200, 375, 1000, 375);
		pathTransitionLog = new PathTransition();
		pathTransitionLog.setDuration(Duration.millis(8000));
		pathTransitionLog.setPath(pathLog);
		pathTransitionLog.setNode(log);
		pathTransitionLog.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		pathTransitionLog.setCycleCount(Timeline.INDEFINITE);
		pathTransitionLog.play();

		Line pathLog2 = new Line(1000, 265, -200, 265);
		pathTransitionLog2 = new PathTransition();
		pathTransitionLog2.setDuration(Duration.millis(8000));
		pathTransitionLog2.setPath(pathLog2);
		pathTransitionLog2.setNode(log2);
		pathTransitionLog2.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		pathTransitionLog2.setCycleCount(Timeline.INDEFINITE);
		pathTransitionLog2.setDelay(Duration.millis(4000));
		pathTransitionLog2.play();

		SVGPath svg = new SVGPath();
		svg.setFill(Color.TRANSPARENT);
		svg.setStrokeWidth(1.0);
		svg.setStroke(Color.BLACK);
		svg.setContent(
				"M 787.49,150 C 787.49,203.36 755.56,247.27 712.27,269.5 S 622.17,290.34 582.67,279.16 508.78,246.56 480,223.91 424.93,174.93 400,150 348.85,98.79 320,76.09 256.91,32.03 217.33,20.84 130.62,8.48 87.73,30.5 12.51,96.64 12.51,150 44.44,247.27 87.73,269.5 177.83,290.34 217.33,279.16 291.22,246.56 320,223.91 375.07,174.93 400,150 451.15,98.79 480,76.09 543.09,32.03 582.67,20.84 669.38,8.48 712.27,30.5 787.49,96.64 787.49,150 z");
		svg.setScaleX(1);
		svg.setScaleY(0.5);
		pathTransitionBee = new PathTransition();
		pathTransitionBee.setPath(svg);
		pathTransitionBee.setNode(bee);
		pathTransitionBee.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		pathTransitionBee.setCycleCount(Timeline.INDEFINITE);
		pathTransitionBee.setDuration(Duration.millis(5000));
		pathTransitionBee.setInterpolator(Interpolator.LINEAR);
		pathTransitionBee.play();

		timelineLilyPad1 = new Timeline();
		timelineLilyPad1.setCycleCount(Timeline.INDEFINITE);
		timelineLilyPad1.setAutoReverse(false);
		timelineLilyPad1.getKeyFrames()
				.add(new KeyFrame(Duration.millis(0), new KeyValue(lilyPad1.opacityProperty(), 1.0)));
		timelineLilyPad1.getKeyFrames()
				.add(new KeyFrame(Duration.millis(5000), new KeyValue(lilyPad1.opacityProperty(), 1.0)));
		timelineLilyPad1.getKeyFrames()
				.add(new KeyFrame(Duration.millis(5500), new KeyValue(lilyPad1.opacityProperty(), 0.0)));
		timelineLilyPad1.getKeyFrames()
				.add(new KeyFrame(Duration.millis(6500), new KeyValue(lilyPad1.opacityProperty(), 0.0)));
		timelineLilyPad1.getKeyFrames()
				.add(new KeyFrame(Duration.millis(7000), new KeyValue(lilyPad1.opacityProperty(), 1.0)));
		timelineLilyPad1.play();

		timelineLilyPad2 = new Timeline();
		timelineLilyPad2.setCycleCount(Timeline.INDEFINITE);
		timelineLilyPad2.setAutoReverse(false);
		timelineLilyPad2.getKeyFrames()
				.add(new KeyFrame(Duration.millis(0), new KeyValue(lilyPad2.opacityProperty(), 1.0)));
		timelineLilyPad2.getKeyFrames()
				.add(new KeyFrame(Duration.millis(5000), new KeyValue(lilyPad2.opacityProperty(), 1.0)));
		timelineLilyPad2.getKeyFrames()
				.add(new KeyFrame(Duration.millis(5500), new KeyValue(lilyPad2.opacityProperty(), 0.0)));
		timelineLilyPad2.getKeyFrames()
				.add(new KeyFrame(Duration.millis(6500), new KeyValue(lilyPad2.opacityProperty(), 0.0)));
		timelineLilyPad2.getKeyFrames()
				.add(new KeyFrame(Duration.millis(7000), new KeyValue(lilyPad2.opacityProperty(), 1.0)));
		timelineLilyPad2.play();

		messageBox = new Rectangle(225, 250, 365, 175);
		messageBox.setFill(Color.LIGHTGREY);
		messageBox.setOpacity(10);
		messageBox.setVisible(false);

		deathMessage = new Text();
		deathMessage.setX(375);
		deathMessage.setY(300);
		deathMessage.setFill(Color.RED);
		deathMessage.setStroke(Color.BLACK);
		deathMessage.setStrokeWidth(0.5);
		deathMessage.setScaleX(5);
		deathMessage.setScaleY(5);

		restartMessage = new Text();
		restartMessage.setX(350);
		restartMessage.setY(375);
		restartMessage.setFill(Color.RED);
		restartMessage.setStroke(Color.BLACK);
		restartMessage.setStrokeWidth(0.25);
		restartMessage.setScaleX(2);
		restartMessage.setScaleY(2);

		livesBox = new Rectangle(590, 0, 210, 50);
		livesBox.setFill(Color.BLACK);
		livesBox.setStroke(Color.BLACK);

		livesMessage = new Text();
		livesMessage.setX(635);
		livesMessage.setY(30);
		livesMessage.setScaleX(1.5);
		livesMessage.setScaleY(1.5);
		livesMessage.setFill(Color.WHITE);
		livesMessage.setText("Number of Lives: " + numLives);

		gameCanvas.getChildren().add(road1);
		gameCanvas.getChildren().add(road2);
		gameCanvas.getChildren().add(river);
		gameCanvas.getChildren().add(log);
		gameCanvas.getChildren().add(log2);
		gameCanvas.getChildren().add(lilyPad1);
		gameCanvas.getChildren().add(lilyPad2);
		gameCanvas.getChildren().add(bush1);
		gameCanvas.getChildren().add(bush2);
		gameCanvas.getChildren().add(bush3);
		gameCanvas.getChildren().add(beehive);
		gameCanvas.getChildren().add(bee);
		gameCanvas.getChildren().add(car1);
		gameCanvas.getChildren().add(car2);
		gameCanvas.getChildren().add(car3);
		gameCanvas.getChildren().add(car4);
		gameCanvas.getChildren().add(chicken);
		gameCanvas.getChildren().add(messageBox);
		gameCanvas.getChildren().add(deathMessage);
		gameCanvas.getChildren().add(restartMessage);
		gameCanvas.getChildren().add(livesBox);
		gameCanvas.getChildren().add(livesMessage);

	}

	public class Timer extends AnimationTimer {
		public void handle(long now) {
			if (wPressed && chickenBody.getCenterY() - 3 >= 0 && alive) {
				chickenBody.setCenterY(chickenBody.getCenterY() - 3);
				chickenHead.setY(chickenHead.getY() - 3);
				chickenBeak.getPoints().clear();
				chickenBeakPoints[1] = chickenBeakPoints[1] - 3;
				chickenBeakPoints[3] = chickenBeakPoints[3] - 3;
				chickenBeakPoints[5] = chickenBeakPoints[5] - 3;
				chickenBeak.getPoints().addAll(chickenBeakPoints);

			}
			if (sPressed && chickenBody.getCenterY() + 3 <= HonorsProject.WINSIZE_Y && alive) {
				chickenBody.setCenterY(chickenBody.getCenterY() + 3);
				chickenHead.setY(chickenHead.getY() + 3);
				chickenBeak.getPoints().clear();
				chickenBeakPoints[1] = chickenBeakPoints[1] + 3;
				chickenBeakPoints[3] = chickenBeakPoints[3] + 3;
				chickenBeakPoints[5] = chickenBeakPoints[5] + 3;
				chickenBeak.getPoints().addAll(chickenBeakPoints);

			}

			if (aPressed && chickenBody.getCenterX() - 3 >= 0 && alive) {
				chickenBody.setCenterX(chickenBody.getCenterX() - 3);
				chickenHead.setX(chickenHead.getX() - 3);
				chickenBeak.getPoints().clear();
				chickenBeakPoints[0] = chickenBeakPoints[0] - 3;
				chickenBeakPoints[2] = chickenBeakPoints[2] - 3;
				chickenBeakPoints[4] = chickenBeakPoints[4] - 3;
				chickenBeak.getPoints().addAll(chickenBeakPoints);

			}
			if (dPressed && chickenBody.getCenterX() + 3 <= HonorsProject.WINSIZE_X && alive) {
				chickenBody.setCenterX(chickenBody.getCenterX() + 3);
				chickenHead.setX(chickenHead.getX() + 3);
				chickenBeak.getPoints().clear();
				chickenBeakPoints[0] = chickenBeakPoints[0] + 3;
				chickenBeakPoints[2] = chickenBeakPoints[2] + 3;
				chickenBeakPoints[4] = chickenBeakPoints[4] + 3;
				chickenBeak.getPoints().addAll(chickenBeakPoints);

			}

			System.out.println(chickenBody.getCenterX() + ", " + chickenBody.getCenterY());
			if (chicken.getBoundsInParent().intersects(car1.getBoundsInParent())
					|| chicken.getBoundsInParent().intersects(car2.getBoundsInParent())
					|| chicken.getBoundsInParent().intersects(car3.getBoundsInParent())
					|| chicken.getBoundsInParent().intersects(car4.getBoundsInParent())
					|| chicken.getBoundsInParent().intersects(bush1.getBoundsInParent())
					|| chicken.getBoundsInParent().intersects(bush2.getBoundsInParent())
					|| chicken.getBoundsInParent().intersects(bush3.getBoundsInParent())
					|| chicken.getBoundsInParent().intersects(bee.getBoundsInParent())
					|| (chickenBody.getBoundsInParent().intersects(river.getBoundsInParent())
							&& ((!chickenBody.getBoundsInParent().intersects(log.getBoundsInParent())
									&& !chickenBody.getBoundsInParent().intersects(log2.getBoundsInParent()))
									&& !chickenBody.getBoundsInParent().intersects(lilyPad1.getBoundsInParent())
									&& !chickenBody.getBoundsInParent().intersects(lilyPad2.getBoundsInParent())))
					|| (chickenBody.getBoundsInParent().intersects(river.getBoundsInParent())
							&& ((!chickenBody.getBoundsInParent().intersects(log.getBoundsInParent())
									&& !chickenBody.getBoundsInParent().intersects(log2.getBoundsInParent()))
									&& (chickenBody.getBoundsInParent().intersects(lilyPad1.getBoundsInParent())
											&& lilyPad1.getOpacity() <= 0.6
											|| chickenBody.getBoundsInParent().intersects(lilyPad2.getBoundsInParent())
													&& lilyPad2.getOpacity() <= 0.6)))) {
				alive = false;
				pathTransitionCar1.pause();
				pathTransitionCar2.pause();
				pathTransitionCar3.pause();
				pathTransitionCar4.pause();
				pathTransitionLog.pause();
				pathTransitionLog2.pause();
				pathTransitionBee.pause();
				timelineLilyPad1.pause();
				timelineLilyPad2.pause();
				stop();
				chickenBody.setFill(Color.RED);
				chickenHead.setFill(Color.RED);
				chickenBeak.setFill(Color.RED);
				messageBox.setVisible(true);
				messageBox.setOpacity(0.7);

				if (numLives > 1) {
					numLives -= 1;
					livesMessage.setText("Number of Lives: " + numLives);
					deathMessage.setText("You died!");
					restartMessage.setText("Press 'R' to restart.\n" + numLives + " lives remaining.");
				} else {
					numLives -= 1;
					livesMessage.setText("Number of Lives: " + numLives);
					deathMessage.setText("You lost!");
					restartMessage.setX(335);
					restartMessage.setText("You failed cross roady.\n   " + numLives + " lives remaining.");
				}
			}
			if (chickenBody.getBoundsInParent().intersects(beehive.getBoundsInParent())) {
				pathTransitionCar1.pause();
				pathTransitionCar2.pause();
				pathTransitionCar3.pause();
				pathTransitionCar4.pause();
				pathTransitionLog.pause();
				pathTransitionLog2.pause();
				pathTransitionBee.pause();
				timelineLilyPad1.pause();
				timelineLilyPad2.pause();
				stop();
				chickenBody.setFill(Color.LIME);
				chickenHead.setFill(Color.LIME);
				chickenBeak.setFill(Color.LIME);
				messageBox.setVisible(true);
				messageBox.setOpacity(0.7);
				deathMessage.setText("YOU WIN!");
				restartMessage.setText("Road crossed with\n" + numLives + " lives remaining.");
				numLives = 0;
			}
		}

	}

	private class KeyHandler implements EventHandler<KeyEvent> {
		@Override
		public void handle(KeyEvent event) {

			if (event.getEventType().equals(KeyEvent.KEY_PRESSED)) {
				if (event.getCode() == KeyCode.W) {
					wPressed = true;
				}
				if (event.getCode() == KeyCode.S) {
					sPressed = true;
				}
				if (event.getCode() == KeyCode.A) {
					aPressed = true;
				}
				if (event.getCode() == KeyCode.D) {
					dPressed = true;
				}
			}
			if (event.getEventType().equals(KeyEvent.KEY_RELEASED)) {
				if (event.getCode() == KeyCode.W) {
					wPressed = false;
				}
				if (event.getCode() == KeyCode.S) {
					sPressed = false;
				}
				if (event.getCode() == KeyCode.A) {
					aPressed = false;
				}
				if (event.getCode() == KeyCode.D) {
					dPressed = false;
				}
				if (event.getCode() == KeyCode.R && !alive && numLives > 0) {
					t.start();
					chickenBody.setCenterX(400);
					chickenBody.setCenterY(750);
					chickenHead.setX(395);
					chickenHead.setY(745);
					chickenBeak.getPoints().clear();
					chickenBeakPoints[0] = 380.0;
					chickenBeakPoints[1] = 750.0;
					chickenBeakPoints[2] = 420.0;
					chickenBeakPoints[3] = 750.0;
					chickenBeakPoints[4] = 400.0;
					chickenBeakPoints[5] = 710.0;
					chickenBeak.getPoints().addAll(chickenBeakPoints);
					alive = true;
					deathMessage.setText(" ");
					restartMessage.setText(" ");
					messageBox.setVisible(false);
					chickenBody.setFill(Color.WHITE);
					chickenBeak.setFill(Color.YELLOW);
					pathTransitionCar1.play();
					pathTransitionCar2.play();
					pathTransitionCar3.play();
					pathTransitionCar4.play();
					pathTransitionLog.play();
					pathTransitionLog2.play();
					pathTransitionBee.play();
					timelineLilyPad1.play();
					timelineLilyPad2.play();
				}
			}

		}
	}
}
