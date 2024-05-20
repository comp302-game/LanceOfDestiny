package tr.edu.ku;
import java.io.Serializable;

public class Message implements Serializable{

	public int score;
	public int barriers;
	public int chances;
	public int mode;
	public Message(int mode, int score, int barriers, int chances) {
		this.score = score;
		this.barriers = barriers;
		this.chances = chances;
		this.mode = mode;
	}
}