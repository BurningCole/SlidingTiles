import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class STHighscore{
	private JFrame frame = new JFrame();//set up objects
	private JPanel panel = new JPanel();
	private JLabel[][] scorelist = new JLabel[2][10];
	private JLabel nameLabel = new JLabel("YOUR NAME:");
	private JTextField Name = new JTextField(20);
	private GridLayout grid = new GridLayout(11,2);
	private int[] scores = new int[10];
	
	public STHighscore(){
		frame.setTitle("HIGH SCORES");
		frame.setSize(448,360);//set size
		frame.setContentPane(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setLayout(grid);
		for(int i=0;i<10;i++){//write empty results
			scorelist[0][i]=new JLabel("NONE");
			scorelist[1][i]=new JLabel(""+0);
			scores[i]=0;
			panel.add(scorelist[0][i]);
			panel.add(scorelist[1][i]);
		}
		panel.add(nameLabel);//add name input 
		panel.add(Name);
		frame.setVisible(true);
	}
	public boolean addScore(int score){
		for(int i=0;i<10;i++){
			if(score<scores[i]||scores[i]==0){//if score goes in position i
				for(int j=9;i<j;j--){//move all scores below i down
					scores[j]=scores[j-1];
					scorelist[0][j].setText(scorelist[0][j-1].getText());
					scorelist[1][j].setText(""+scores[j]);
				}
				if (Name.getText().length()==0){//if no name given use player
					scorelist[0][i].setText("Player");
				}else{//use name given otherwise
					scorelist[0][i].setText(Name.getText());
				}
				scores[i]=score;//put score next to name
				scorelist[1][i].setText(""+scores[i]);
				frame.setVisible(true);//update frame
				return true;//returns if score got into highscores
			}
		}
		return false;//returns false if score not in high scores
	}
}
