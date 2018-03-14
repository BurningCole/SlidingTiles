import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class STrun implements ActionListener{
	private JFrame frame = new JFrame();
	private JPanel panel = new JPanel();
	public STHighscore Highscores=new STHighscore();
	private JButton[] gridItem = new JButton[12];
	private ImageIcon[] images = new ImageIcon[12];
	private int[] greyPos={0,0};
	private GridLayout grid = new GridLayout(3,4);
	private int count=0;
	public STrun(){
		frame.setTitle("Swingin' simpsons");
		frame.setSize(448,360);
		frame.setContentPane(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setLayout(grid);
		for(int i=0;i<12;i++){			//do for each grid position
			images[i] = new ImageIcon("Assets/bart"+i+".jpg");
			gridItem[i] = new JButton(images[i]);				//add button to gridarray
			panel.add(gridItem[i]);								//add item to grid
			gridItem[i].addActionListener(this);				//add action listener
		}
		frame.setVisible(true);									//make frame appear
	}
	public void actionPerformed(ActionEvent e){
		int[] pos={0,0};
		int i;
    	for(i=0;i<12;i++){
			if(e.getSource()==gridItem[i]){
				pos[0]=i%4;
				pos[1]=(int)(i/4);
				break;
			}
		}
		if ((greyPos[0]-pos[0])*(greyPos[0]-pos[0])+(greyPos[1]-pos[1])*(greyPos[1]-pos[1])==1/*next to Grey*/){
			gridItem[greyPos[0]+greyPos[1]*4].setIcon(gridItem[i].getIcon());
			gridItem[i].setIcon(images[0]);
			greyPos=pos;
			frame.setVisible(true);
			boolean complete=true;
			count++;
			for(i=0;i<12;i++){
				if(gridItem[i].getIcon()!=images[i]){
					complete=false;
					break;
				}
			}
			if(complete){
				System.out.println("done");
				Highscores.addScore(count);
			}
		}
		
	}
}
