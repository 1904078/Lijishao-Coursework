package chessgame;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
class Testchess {

	private static final char ChessboardLine='-';
	private static final char[][] Chessboard = new char[6][7];
	
	
	@BeforeEach
	void setUp() throws Exception {
		for(int i = 0;i < Chessboard.length;i++)
	    {
	       for(int j = 0;j < Chessboard[0].length;j++)
	    {
	    	Chessboard[i][j] = '-';
	    }
	    }
	}

	@Test
	void testCountTotalChess() {
				Chessboard[1][1]='X';
				Chessboard[1][2]='O';
				int actualChess=2;
				int totalChess=chess.CountTotalChess(Chessboard,ChessboardLine);
		assertEquals(actualChess,totalChess);	
		}
		void testSearchChessNumber() {
			char CurrentChess='X';
			Chessboard[1][1]='X';
			Chessboard[1][2]='X';
			Chessboard[1][3]='X';
			int chessNumber=2;
			int SearchessNumber=chess.SearchChessNumber(Chessboard, CurrentChess,1,1, 0, 1);
			assertEquals(chessNumber,SearchessNumber);	

		}
		void TestPlace() {
	    int column=1;
	    int rounds=1;
	    char chesspiece[]= {'X','O'};
		int actualPlacerow=chess.Place(column,rounds,Chessboard,ChessboardLine,chesspiece);
	    int placerow=0;
			assertEquals(placerow,actualPlacerow);	
		}
	void TestSearchChessNUmber()
	{   
		char CurrentChess='X';
		Chessboard[1][1]='X';
	    Chessboard[1][2]='X';
	    Chessboard[1][3]='X';
		int r=1;
		int c=1;
		int i=0;
		int j=1;
		int ActualNUmber=chess.SearchChessNumber(Chessboard,CurrentChess,r,c, i,j);
		int Number=2;
		assertEquals(Number,ActualNUmber);	
	}
 
	
	}


