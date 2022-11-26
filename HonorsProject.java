//CSE205 Honors Project
//Name: Pradyoth Velagapudi
//StudentID: 1224389868
//Lecture time: 1:30 Tu/Th

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HonorsProject extends Application
{
    public static final int WINSIZE_X = 800, WINSIZE_Y = 800;
    private final String WINTITLE = "Cross Roady";
    private Alert gameStart;

    @Override
    public void start(Stage stage) throws Exception
    {
        GamePane rootPane = new GamePane();
        rootPane.setPrefSize(WINSIZE_X, WINSIZE_Y);
        Scene scene = new Scene(rootPane, WINSIZE_X, WINSIZE_Y);
        stage.setTitle(WINTITLE);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        gameStart = new Alert(AlertType.INFORMATION);
		gameStart.setContentText("WELCOME TO CROSS ROADY\nWhy did the chicken cross the road? To get to the honeycomb, of course! "
				+ "Use WASD to make your way to the beehive, and avoid the "
				+ "dangers along the way. Good luck!");
		gameStart.initModality(Modality.WINDOW_MODAL);
		gameStart.showAndWait();
    }

    /**
     * Technically this is not needed for JavaFX applications. Added just in case.
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        launch(args);
        
    }
}