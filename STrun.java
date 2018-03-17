import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Random;

public class STrun implements ActionListener{
	private JFrame frame = new JFrame();
	private JPanel panel = new JPanel();
	private JPanel panel2 = new JPanel();
	public STHighscore Highscores=new STHighscore();
	private JButton[] gridItem = new JButton[12];
	private ImageIcon[] images = new ImageIcon[12];
	private int[] greyPos={0,0};
	private GridLayout grid = new GridLayout(3,4);
	private JLabel moves=new JLabel("Moves: "+0);
	private JButton randomize = new JButton("Randomize order");
	private int count=0;
	
	public int getAddress(Icon image){
		for(int i=0;i<12;i++){
			if (gridItem[i].getIcon()==image){
				return i;
			}
		}
		return 0;
	}
	public void Randomize(){
		Random rand = new Random();
		for(int i=0;i<20;i++){
			int swap=rand.nextInt(12);
			gridItem[greyPos[0]+greyPos[1]*4].setIcon(gridItem[swap].getIcon());
			gridItem[swap].setIcon(images[0]);
			greyPos[0]=swap%4;
			greyPos[1]=(int)(swap/4);
		}
		gridItem[greyPos[0]+greyPos[1]*4].setIcon(gridItem[0].getIcon());
		gridItem[0].setIcon(images[0]);
		greyPos[0]=0;
		greyPos[1]=0;
		int inversions=0;
		for(int i=1;i<12;i++){
			int value=getAddress(gridItem[i].getIcon());
			for(int j=i+1;j<12;j++){
				if(getAddress(gridItem[i].getIcon())<value&&getAddress(gridItem[i].getIcon())!=0){
					inversions++;
				}
			}
		}
		
		if(inversions%2==1){
			for(int swap=1;swap<5;swap++){
				gridItem[greyPos[0]+greyPos[1]*4].setIcon(gridItem[swap].getIcon());
				gridItem[swap].setIcon(images[0]);
				greyPos[0]=swap%4;
				greyPos[1]=(int)(swap/4);
			}
		}
	}
	public STrun(){
		frame.setResizable(false);
		panel.setSize(448,360);
		frame.setTitle("Swingin' simpsons");
		frame.setSize(448,400);
		frame.setContentPane(panel2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setLayout(grid);
		for(int i=0;i<12;i++){			//do for each grid position
			images[i] = new ImageIcon("Assets/bart"+i+".jpg");
			gridItem[i] = new JButton(images[i]);				//add button to gridarray
			panel.add(gridItem[i]);								//add item to grid
			gridItem[i].setPreferredSize(new Dimension(112, 120));
			gridItem[i].addActionListener(this);				//add action listener
		}		
		panel2.add(panel);
		panel2.add(randomize);
		panel2.add(moves);
		randomize.addActionListener(this);
		Randomize();
		frame.setVisible(true);									//make frame appear
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==randomize){
			Randomize();
		}
		else
		{
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
				boolean complete=true;
				count++;
				moves.setText("Moves: "+count);
				for(i=0;i<12;i++){
					if(gridItem[i].getIcon()!=images[i]){
						complete=false;
						break;
					}
				}
				if(complete){
					Highscores.addScore(count);
					count=0;
					moves.setText("Moves: "+count);
					Randomize();
				}
			}
		}
		frame.setVisible(true);
	}
}
