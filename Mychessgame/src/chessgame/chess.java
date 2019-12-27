package chessgame;
import java.util.*;		

public class chess {
	private static Scanner stdin=new Scanner(System.in);
			public static void main(String[] args)
			{
			char ChessboardLine='-';
			char[][] Chessboard=new char[6][7];
			char[] chesspiece1= {'X','O'};
			//Initialize chess board elements
			Initchessboard(Chessboard,ChessboardLine);
			//Draw chess board
			DressBoard(Chessboard);
			//
			int totalChess=0;//The number of all pieces that have been placed
			while(totalChess<6*7)
			{  
				int column=InputColumn(totalChess);
				boolean ifCanPlace=IfCanPlacePiece(column,totalChess,Chessboard,ChessboardLine); 
				if(ifCanPlace)
				{
				//If ifCanPlace is true,it means It means that pieces can be placed
					//Judge which grid to put chess on. 
					int row=0;
					for(row=0;row<6;row++)//row
					{
						if(Chessboard[row][column]==ChessboardLine)
						{
							Chessboard[row][column]=chesspiece1[totalChess%2];
							break;
						}
						continue;
					}
					//update chess board
					DressBoard(Chessboard);
					//Judge if anyone wins
					if(judgeWinner(Chessboard,Chessboard[row][column],row,column))
					{
						System.out.printf("Congratulations,play %d wins",totalChess%2+1);
						break;
					}
					totalChess++;	
		            if(totalChess==42)
		            {
		            	System.out.printf("The chessboard is full. This game is a draw\n");
		            	break;
		            }
					
				}
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
		public  static int SearchChessNumber(char[][] Chessboard,char CurrentChess,int r,int c,int i,int j)
		{  
			r+=i;
			c+=j;
			if(r<0||c<0||r>=6||c>=7||Chessboard[r][c]!=CurrentChess)
			return 0;
			return SearchChessNumber(Chessboard, CurrentChess,r,c, i, j)+1;
		}
		public static boolean IfCanPlacePiece(int column,int totalChess,char[][] Chessboard,char ChessboardLine)
		{       boolean ifCanPlace =false;
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
						System.out.print("The column of you enter is full,"
								+ "please enter again\n");
					
					}
		        return ifCanPlace;
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
			public static int InputColumn(int totalChess)
			{   int column=0;
			    int i=totalChess%2;//i==0:play1; i==1:play2
				System.out.printf("Player %d Select Column :",i+1);
				//Index value is one less than the actual value
				int enterColumn=Integer.parseInt(stdin.nextLine());
			    column=enterColumn-1;//actual column
			    if(column<0||column>=7)
			    {
			    	System.out.printf("The column you enter is not right,"
			    			+ "please enter again\n");	
			    	column=InputColumn(totalChess);
			    }
				return column;
			}

		}

