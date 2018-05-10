package testy;

//Program maj¹cy na celu naukê zmiany sceny.

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Apka extends Application{

	public static final int TILE_SIZE = 30;
	private Kwadrat kwadrat = new Kwadrat(40, 40, Color.RED);
	private Kwadrat kwadrat2 = new Kwadrat(100, 100, Color.BLUE);
	
	Canvas canvas = new Canvas(600, 400);
	private GraphicsContext g = canvas.getGraphicsContext2D();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Scene scene = new Scene(setContent(stage));
		
		
		
		scene.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.LEFT)
			{
				kwadrat.move(-10, 0);
			}
			else if(e.getCode() == KeyCode.RIGHT)
			{
				kwadrat.move(10, 0);
			}
			
			render();
		});
		
		
		stage.setScene(scene);
		stage.setTitle("taki test");
		stage.show();
		
		
		
	}
	
	public void change(Stage stage)
	{
		Scene scene2 = new Scene(setContent2());
		
		scene2.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.LEFT)
			{
				kwadrat2.move(-40, 0);
			}
			else if(e.getCode() == KeyCode.RIGHT)
			{
				kwadrat2.move(40, 0);
			}
			
			render2();
		});
		
		stage.setScene(scene2);
		stage.show();
	}
	
	public Parent setContent(Stage stage)
	{
		Pane pane = new Pane();
		Button button = new Button();
		button.setCursor(Cursor.HAND);
		button.setPrefSize(200, 50);
		button.setText("Zmiana sceny");
		button.setLayoutX(300);
		button.setLayoutY(200);
		button.setOnMouseClicked(e -> {
			change(stage);
		});
		pane.setPrefSize(600, 400);
		pane.setStyle("-fx-background-color: white");
		pane.getChildren().addAll(canvas);
		pane.getChildren().add(button);
		
		render();
		
		return pane;
	}
	
	public Parent setContent2()
	{
		Pane pane2 = new Pane();
		pane2.setPrefSize(600, 400);
		pane2.setStyle("-fx-background-color: red");
		pane2.getChildren().addAll(canvas);
		
		render2();
		
		return pane2;
	}
	
	public void render()
	{
		g.clearRect(0, 0, 600, 400);
		kwadrat.draw(g);
		g.setStroke(Color.BLACK);
		g.strokeText("Scena 1", 20, 20);
	}
	
	public void render2()
	{
		g.clearRect(0, 0, 600, 400);
		kwadrat2.draw(g);
		g.setStroke(Color.BLACK);
		g.strokeText("Scena 2", 20, 20);
	}

}

class Kwadrat {
	
	public int x, y;
	public Color color;
	
	Kwadrat(int x, int y, Color color)
	{
		this.x = x;
		this.y = y;
		this.color = color;
	}
	
	public void move(int dx, int dy)
	{
		this.x +=dx;
		this.y +=dy;
	}
	
	public void draw(GraphicsContext g)
	{
		g.setFill(color);
		g.fillRect(x, y, Apka.TILE_SIZE, Apka.TILE_SIZE);
		System.out.println("rysuje");
	}
	
}