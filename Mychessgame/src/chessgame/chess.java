package chessgame;
import java.util.*;		
public class chess {	
	private static Scanner stdin=new Scanner(System.in);
	    public static void main(String[] args)
		{
			char ChessboardLine='-';
			char[][] Chessboard=new char[6][7];
			char[] chesspiece= {'X','O'};
			int[] timesOfBlitz= {1,1};//every player can play once
			//Initialize chess board elements
			Initchessboard(Chessboard,ChessboardLine);
			//Draw chess board
			DressBoard(Chessboard);
			int totalChess=0;//The number of all pieces that have been placed
	//The number of rounds,even numbers represent player 1 and odd numbers represent player 2;
			int rounds=0;
			int column=-1;
			int placerow=-1;
			while(totalChess<6*7)
			{  
				//choose chess playing mode:B,T or 1-7
				int modeAscii=InputMode(rounds,Chessboard,ChessboardLine);
			    if(modeAscii==66)//B
			    {  
			    	if(timesOfBlitz[rounds%2]==1)
			    	{
			    	//Call instantaneous bomb function
			    	System.out.printf("Blitz please select column >");
			        column=Integer.parseInt(stdin.nextLine())-1;
			    	for( int row=0;row<6;row++)
			    	{
			    		if(Chessboard[row][column]!=ChessboardLine)//clear
			    		{   
			    			Chessboard[row][column]=ChessboardLine;
			    			totalChess--;	
			    		}		
			    	} 
			    	DressBoard(Chessboard);
			    	timesOfBlitz[rounds%2]--;
			    	rounds++;
			    	}
			    	else 
			    	{
			    		System.out.print("You only have one time\n");
			    	}
			    }
			    else if(modeAscii==84)//T
			    {
			    		
			    }
			    else if(modeAscii>=49&&modeAscii<56)//1-7
			    {
			    column=modeAscii-49;//Actual column
			  //Place chess piece
			    boolean ifCanPlace=IfCanPlacePiece(column,Chessboard,ChessboardLine);
			    if(ifCanPlace)
			    {
			      placerow=Place(column,rounds,Chessboard,ChessboardLine,chesspiece);
			      totalChess++;
				rounds++;
			    }
				//Judge if anyone wins
				if(judgeWinner(Chessboard,Chessboard[placerow][column],placerow,column))
				{
					System.out.printf("Congratulations,play %d wins",rounds%2+1);
					break;
				}
			    }  
	            if(totalChess==42)
	            {
	            	System.out.printf("The chessboard is full. This game is a draw\n");
	            	break;
	            }
				}		    
			  
			    }
			
	    public static int Place(int column,int rounds,char[][] Chessboard,char ChessboardLine,char[] chesspiece)
	    {
	    	int Placerow=0;
		//If ifCanPlace is true,it means It means that pieces can be placed
		//Judge which grid to put chess on. 
			for(int row=0;row<6;row++)//row
			{
			  if(Chessboard[row][column]==ChessboardLine)
				{
					Chessboard[row][column]=chesspiece[rounds%2];
					Placerow=row;
					break;
				}
					continue;
			}
			//update chess board
			DressBoard(Chessboard);	
		return Placerow;
	    }
	    public static int InputMode(int rounds,char[][] Chessboard,char ChessboardLine)
	    {  
	    	int i=rounds%2+1;//i==1:play1; i==2:play2
	    	int modeAscii=0;
		    System.out.printf("Player %d Select Column > ",i);
		    String mode=stdin.nextLine();//B,T or 1-7 
		   if(mode.length()>1)
		   {
			   System.out.printf("The column you enter is not right,please enter again\n");
			   modeAscii=InputMode(rounds,Chessboard,ChessboardLine);//If it is not B,T,or 1-7, input again
		   }
		   else
		   {   
			   char modechar=mode.charAt(0);
			   if(modechar==66||modechar==84||(modechar>=49&&modechar<=55))
		    {
			  modeAscii=Integer.valueOf(modechar); 
			
		    }
		   else
		   {
		    System.out.printf("The column you enter is not right,please enter again\n");
		    modeAscii=InputMode(rounds,Chessboard,ChessboardLine);//If it is not B,T,or 1-7, input again
		    }
		   }
		    return modeAscii;
	    }

	    public static void Initchessboard(char[][] Chessboard,char ChessboardLine)
	    {
	    	for(int i = 0;i < Chessboard.length;i++)
	    {
	       for(int j = 0;j < Chessboard[0].length;j++)
	    {
	    	Chessboard[i][j] = ChessboardLine;
	    }
	    }	
	    }
	    public static void DressBoard(char[][] Chessboard)
	    {
	       for(int i=1;i<=Chessboard[0].length;i++)
	      {
	        System.out.printf("%d ",i);
	      }
	    	System.out.printf("\n");
	    					
	    	for(int i = Chessboard.length - 1;i >= 0;i--)
	    	{
	    		for(int j = 0;j < Chessboard[0].length;j++)
	    		{
	    			System.out.printf("%s",Chessboard[i][j]);
	    			System.out.printf(" ");
	    		}
	    			System.out.printf("\n");
	    						
	    	}
	    }

	public static boolean judgeWinner(char[][] Chessboard,char CurrentChess,int row,int column)
	 {
		boolean ifWin=false;
		if(SearchChessNumber(Chessboard,CurrentChess,row,column,1,0)+SearchChessNumber(Chessboard,CurrentChess,row,column,-1,0)>=3
		||SearchChessNumber(Chessboard,CurrentChess,row,column,0,1)+SearchChessNumber(Chessboard,CurrentChess,row,column,0,-1)>=3
		||SearchChessNumber(Chessboard,CurrentChess,row,column,1,1)+SearchChessNumber(Chessboard,CurrentChess,row,column,-1,-1)>=3
		||SearchChessNumber(Chessboard,CurrentChess,row,column,1,-1)+SearchChessNumber(Chessboard,CurrentChess,row,column,-1,1)>=3) 
		{
		  ifWin=true;
		}		
		  return ifWin;
		}
	public static int SearchChessNumber(char[][] Chessboard,char CurrentChess,int r,int c,int i,int j)
	 {  
		  r+=i;
		  c+=j;
		  if(r<0||c<0||r>=6||c>=7||Chessboard[r][c]!=CurrentChess)
		  return 0;
		  return SearchChessNumber(Chessboard, CurrentChess,r,c, i, j)+1;
	}
	public static boolean IfCanPlacePiece(int column,char[][] Chessboard,char ChessboardLine)
	 {       
		boolean ifCanPlace =false;
		int row=0;
		for(row=0;row<6;row++)//row
	{
		if(Chessboard[row][column]==ChessboardLine)
		{
			ifCanPlace=true;
			break;
		}		
	}
		if(row==Chessboard.length) 
	{   	ifCanPlace= false;
			System.out.print("The column of you enter is full,please enter again\n");
			
	}
	    return ifCanPlace;
	}

	}



		



