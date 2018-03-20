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
	private JButton randomise = new JButton("randomise order");
	private int count=0;
	
	/**
	*gets the location in the array of ImageIcons of an imageicon
	*/
	public int getAddress(Icon image){//find address of icon
		for(int i=0;i<12;i++){
			if (images[i]==image){
				return i;	//return address
				
			}
		}
		return 0;//program should not reach here
	}
	/**
	*randomises value of the tiles to make the game a challenge
	*/
	public void randomise(){//randomise function
		count=0;
		moves.setText("Moves: "+count);
		Random rand = new Random();
		int swap;
		for(int i=0;i<30;i++){//replace random tile with grey tile 30 times
			swap=rand.nextInt(12);
			gridItem[greyPos[0]+greyPos[1]*4].setIcon(gridItem[swap].getIcon());
			gridItem[swap].setIcon(images[0]);
			greyPos[0]=swap%4;
			greyPos[1]=(int)(swap/4);
		}
		swap=rand.nextInt(4);
		gridItem[greyPos[0]+greyPos[1]*4].setIcon(gridItem[swap].getIcon());//move tile to first row
		gridItem[swap].setIcon(images[0]);
		greyPos[0]=swap;
		greyPos[1]=0;
		int inversions=0;
		for(int i=1;i<12;i++){//count number of inversions
			int value=getAddress(gridItem[i].getIcon());
			for(int j=i+1;j<12;j++){
				if(getAddress(gridItem[j].getIcon())<value&&getAddress(gridItem[j].getIcon())!=0){
					inversions++;
				}
			}
		}
		
		if(inversions%2==1){//if inversions is odd game is impossible so grey tile needs to be moved
			swap++;
			for(int i=swap+4;swap<i;swap++){//move grey down a level without disrupting order of rest of tiles
				gridItem[swap-1].setIcon(gridItem[swap].getIcon());
				gridItem[swap].setIcon(images[0]);
			}
			greyPos[1]=1;//grey is now on lower row
		}
	}
	/**
	*initialises main body of slidingTiles, sets up design of structure.
	*/
	public STrun(){
		frame.setResizable(false);
		panel.setSize(448,360);
		frame.setTitle("Swingin' simpsons");
		frame.setSize(448,450);
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
		panel2.add(randomise);
		panel2.add(moves);
		randomise.addActionListener(this);
		randomise();
		frame.setVisible(true);									//make frame appear
	}
	/**
	* gets the button pressed and randomises if randomise button pressed and slides 
	*if a button next to the grey square pressed
	*/
	public void actionPerformed(ActionEvent e){//check if button pressed
		if(e.getSource()==randomise){	//if randomise button pressed randomise
			randomise();
		}
		else
		{
			int[] pos={0,0};
			int i;
			for(i=0;i<12;i++){
				if(e.getSource()==gridItem[i]){//get position of tile pressed
					pos[0]=i%4;
					pos[1]=(int)(i/4);
					break;
				}
			}
			if ((greyPos[0]-pos[0])*(greyPos[0]-pos[0])+(greyPos[1]-pos[1])*(greyPos[1]-pos[1])==1/*if next to Grey*/){
				gridItem[greyPos[0]+greyPos[1]*4].setIcon(gridItem[i].getIcon());//swap grey and tile pressed
				gridItem[i].setIcon(images[0]);
				greyPos=pos;
				boolean complete=true;
				count++;
				moves.setText("Moves: "+count);
				for(i=0;i<12;i++){
					if(gridItem[i].getIcon()!=images[i]){//check if all icons are in correct positions
						complete=false;
						break;
					}
				}
				if(complete){//if complete add score to results
					Highscores.addScore(count);
					randomise();
				}
			}
		}
		frame.setVisible(true);//refresh frame
	}
}
