import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class STHighscore{
	private JFrame frame = new JFrame();
	private JPanel panel = new JPanel();
	private JLabel[][] scorelist = new JLabel[2][10];
	private JLabel nameLabel = new JLabel("YOUR NAME:");
	private JTextField Name = new JTextField(20);
	private GridLayout grid = new GridLayout(11,2);
	private String[] names = new String[10];
	private int[] scores = new int[10];
	
	public STHighscore(){
		frame.setTitle("HIGH SCORES");
		frame.setSize(448,360);
		frame.setContentPane(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setLayout(grid);
		for(int i=0;i<10;i++){
			names[i]="NONE";
			scorelist[0][i]=new JLabel("NONE");
			scorelist[1][i]=new JLabel(""+0);
			scores[i]=0;
			panel.add(scorelist[0][i]);
			panel.add(scorelist[1][i]);
		}
		panel.add(nameLabel);
		panel.add(Name);
		frame.setVisible(true);
	}
	public boolean addScore(int score){
		for(int i=0;i<10;i++){
			if(score<scores[i]||scores[i]==0){
				System.out.println(""+i+"/"+score);
				for(int j=9;i<j;j--){
					names[j]=names[j-1];
					scores[j]=scores[j-1];
					scorelist[0][j].setText(names[j]);
					scorelist[1][j].setText(""+scores[j]);
				}
				names[i]=Name.getText();
				scores[i]=score;
				scorelist[0][i].setText(names[i]);
				scorelist[1][i].setText(""+scores[i]);
				frame.setVisible(true);
				return true;
			}
		}
		return false;
	}
}
