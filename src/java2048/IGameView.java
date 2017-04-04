package java2048;

public interface IGameView {
	public void setGameField(int numbers[]);

	public void setScore(int score);

	public void setTurn(int turn);

	public void attatchInputListener(IGameControllerFeedback feedback);
}
