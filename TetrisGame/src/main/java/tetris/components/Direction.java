package tetris.components;

public enum Direction {
	UP(0, -1),
	RIGHT(1,0),
	DOWN(0,1),
	LEFT(-1,0),
	SPECIAL(-1, 1);
	
	int x, y;
	
	Direction(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Direction prev()
	{
		int nextIndex = ordinal() - 1;
		
		if(nextIndex == -1)
		{
			nextIndex = Direction.values().length - 2;
		}
		return Direction.values()[nextIndex];
	}
	
	public Direction next()
	{
		int nextIndex = ordinal() + 1;
		
		if(nextIndex == Direction.values().length-1)
		{
			nextIndex = 0;
		}
		return Direction.values()[nextIndex];
	}
}
