package tetris.app;


//Kod zapo¿yczony z YT: https://www.youtube.com/watch?v=mi8nIed9460
//Poddany zmianom na potrzeby nauki i æwiczeñ

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.Stage;
import tetris.components.Direction;
import tetris.components.Piece;
import tetris.components.Tetromino;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class TetrisApp extends Application{
	
	public static final int TILE_SIZE = 40;
	public static final int GRID_WIDTH = 15;
	public static final int GRID_HEIGHT = 20;
	public String pkt = "0";
	public int punkty = 0;
	public double czas = 0.5;
	int licznik = 0;
	
	private double time;
	private GraphicsContext g;
	
	private int [][] grid = new int [GRID_WIDTH][GRID_HEIGHT];
	
	private List<Tetromino> orginal = new ArrayList<>();
	private List <Tetromino> tetrominos = new ArrayList<>();
	
	private Tetromino selected;
	
	private Parent createContent() {
		Pane root = new Pane();
		root.setPrefSize(GRID_WIDTH*TILE_SIZE, GRID_HEIGHT*TILE_SIZE);
//		root.setStyle("-fx-background-color: white;");
		
		Canvas canvas = new Canvas (GRID_WIDTH*TILE_SIZE, GRID_HEIGHT*TILE_SIZE);
		g = canvas.getGraphicsContext2D();
		
		g.setStroke(Color.BLACK);
		g.strokeText(pkt, 20, 20);
		
		root.getChildren().addAll(canvas);
		
		orginal.add(new Tetromino(Color.BLUE,
				new Piece(0, Direction.DOWN),
				new Piece(1, Direction.LEFT),
				new Piece (1, Direction.RIGHT),
				new Piece(2, Direction.RIGHT)
				));
		
		orginal.add(new Tetromino(Color.RED,
				new Piece(0, Direction.DOWN),
				new Piece(1, Direction.LEFT),
				new Piece (1, Direction.RIGHT),
				new Piece(1, Direction.DOWN)
				));
		
		orginal.add(new Tetromino(Color.YELLOW,
				new Piece(0, Direction.DOWN),
				new Piece(1, Direction.RIGHT),
				new Piece (1, Direction.DOWN),
				new Piece(2, Direction.DOWN)
				));
		
		orginal.add(new Tetromino(Color.ORANGE,
				new Piece(0, Direction.DOWN),
				new Piece(1, Direction.LEFT),
				new Piece (1, Direction.DOWN),
				new Piece(2, Direction.DOWN)
				));
		
		orginal.add(new Tetromino(Color.BROWN,
				new Piece(0, Direction.DOWN),
				new Piece(1, Direction.LEFT),
				new Piece (1, Direction.DOWN),
				new Piece(1, Direction.SPECIAL)
				));
		
		/*orginal.add(new Tetromino(Color.BLACK,
				new Piece(0, Direction.DOWN),
				new Piece(1, Direction.DOWN),
				new Piece (1, Direction.RIGHT),
				new Piece(1, Direction.SPECIAL)
				));*/
		
		
				
		
		spawn();
		
		AnimationTimer timer = new AnimationTimer()
				{
					
					@Override
					public void handle(long now) {
						time += 0.017;
						
						if (time >= czas)
						{
//							System.out.println("czas tyka");
							update();
							render();
							time = 0;
							
							if((punkty-licznik*10000)>10000)
							{
								czas -= 0.05;
								licznik++;
								System.out.println("zwiekszono predkosc");
							}
							
						}
						
					}
			
				};
				timer.start();
				
				
				return root;
	
	}
	
	private void update() {
		makeMove(p -> p.move(Direction.DOWN), p -> p.move(Direction.UP), true);
	}
	
	private void render() {
		g.clearRect(0, 0, GRID_WIDTH*TILE_SIZE, GRID_HEIGHT*TILE_SIZE);
		
		g.strokeText(pkt, 20, 20);
		tetrominos.forEach(p -> p.draw(g));
	}
	
	private void placePiece (Piece piece) {
		grid[piece.x][piece.y]++;
//		System.out.println("dodal pieca");
	}
	
	private void removePiece (Piece piece) {
		grid[piece.x][piece.y]--;
//		System.out.println("usunal pieca");
	}
	
	private boolean isOffScreen (Piece piece) {
		return piece.x <0 || piece.x >= GRID_WIDTH
				|| piece.y <0 || piece.y >= GRID_HEIGHT;
	}
	
	private void makeMove (Consumer<Tetromino> onSuccess, Consumer<Tetromino> onFail, boolean endMove)	
	{
		selected.pieces.forEach(this::removePiece);
		
		onSuccess.accept(selected);
		
		boolean offscreen = selected.pieces.stream().anyMatch(this::isOffScreen);
		
		if(!offscreen)
		{
			selected.pieces.forEach(this::placePiece);
		}
		
		else 
		{
			onFail.accept(selected);
			
			selected.pieces.forEach(this::placePiece);
			
			if(endMove)
			{
				sweep();
			}
			
			return;
		}
		
		if(!isValidState())
		{
			selected.pieces.forEach(this::removePiece);
			
			onFail.accept(selected);
			
			selected.pieces.forEach(this::placePiece);
			
			if(endMove)
			{
				sweep();
			}
		}
		
	}
	
	private boolean isValidState()
	{
		for (int y=0; y<GRID_HEIGHT; y++)
		{
			for (int x = 0; x<GRID_WIDTH; x++)
			{
				if(grid[x][y]>1)
				{
					return false;
				}
			}
		}
		
		return true;
	}
	
	private void sweep()
	{
		List<Integer> rows = sweepRows();
		rows.forEach(row -> {
			for(int x=0; x<GRID_WIDTH; x++)
			{
				for(Tetromino tetromino : tetrominos)
				{
					tetromino.detach(x, row);
				}
				
				grid[x][row]--;
			}
		});
		
		rows.forEach(row-> {
			tetrominos.stream().forEach(tetromino ->{
				tetromino.pieces.stream()
				.filter(piece ->piece.y < row).forEach(piece ->{
					removePiece(piece);
					piece.y++;
					placePiece(piece);
				});
			});
		});
		
		spawn();
	}
	
	private List<Integer> sweepRows()
	{
		List<Integer> rows = new ArrayList<>();
		
		outer:
			for(int y=0; y<GRID_HEIGHT; y++)
			{
				for (int x=0; x<GRID_WIDTH; x++)
				{
					if(grid[x][y]!=1)
					{
						continue outer;
					}
				}
				
				punkty += 1000;
				pkt = Integer.toString(punkty);
				rows.add(y);
				System.out.println("dodano wiersz");
			}
		return rows;
	}
	
	private void spawn()
	{
		Tetromino tetromino = orginal.get(new Random().nextInt(orginal.size())).copy();
		tetromino.move(GRID_WIDTH/2, 0);
		
		selected = tetromino;
		
		tetrominos.add(tetromino);
		tetromino.pieces.forEach(this::placePiece);
		
		if(!isValidState()) {
			System.out.println("Game over");
			System.exit(0);
		}
	}
	
	

	@Override
	public void start(Stage stage) throws Exception {
		Scene scene = new Scene(createContent());
		
		scene.setOnKeyPressed(e ->{
			if (e.getCode() == KeyCode.SPACE && selected.color != Color.BROWN)
			{
				
				makeMove (p -> p.rotate(), p -> p.rotateBack(), false);
			}
			else if (e.getCode() == KeyCode.LEFT)
			{
				makeMove (p -> p.move(Direction.LEFT),p -> p.move(Direction.RIGHT), false);
			}
			else if (e.getCode() == KeyCode.RIGHT)
			{
				makeMove (p -> p.move(Direction.RIGHT),p -> p.move(Direction.LEFT), false);
			}
			else if (e.getCode() == KeyCode.DOWN)
			{
				punkty++;
				pkt = Integer.toString(punkty);
				makeMove (p -> p.move(Direction.DOWN), p-> p.move(Direction.UP), true);
			}
			
			render();
		});
		
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);

	}

}
