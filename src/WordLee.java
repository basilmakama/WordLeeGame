
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import wordlee.lucenefirstapplication.LuceneTester;
import wordlee.lucenefirstapplication.Searcher;
import wordlee.lucenefirstapplication.Indexer;
import wordlee.lucenefirstapplication.LuceneConstants;
import wordlee.lucenefirstapplication.TextFileFilter;


;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




/**
 *
 * @author Basil
 */
public class WordLee {
    	private static WordLee instance = null;
        private final char[][] boardState;
        private final char[][] board2State;
        private final char[][] resetState, initialBoard;
        private final ArrayList<Integer> turns;
        private int turn, turnIndex, coordinateI, coordinateJ;
        private ArrayList<Character> letters;
        private ArrayList<Character> letters2;
        private final Map<Character, Integer> values;
        private boolean firstplay;
        private boolean doubleWord, tripleWord, letterReduction;
        public static ArrayList<Player> allplayers;
        String dataDir = "/Users/Basil/Lucene/Data"; 
	Searcher searcher;


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
		WordLeeFrame frame = new WordLeeFrame();
                System.out.println();

    }
    
    public WordLee(){
        
        boardState = new char[][]                                 {{0,0,6,0,2,0,0,4,0,0,2,0,6,0,0},
								   {0,2,0,0,0,6,0,0,0,6,0,0,0,2,0},
								   {6,0,0,0,0,0,2,0,2,0,0,0,0,0,6},
								   {0,0,0,7,0,0,0,0,0,0,0,7,0,0,0},
								   {5,0,0,0,0,0,3,0,3,0,0,0,0,0,5},
								   {0,3,0,0,0,5,0,0,0,5,0,0,0,3,0},
								   {0,0,5,0,3,0,0,0,0,0,3,0,5,0,0},
								   {4,0,0,0,0,0,0,1,0,0,0,0,0,0,4},
								   {0,0,5,0,3,0,0,0,0,0,3,0,5,0,0},
								   {0,3,0,0,0,5,0,0,0,5,0,0,0,3,0},
								   {5,0,0,0,0,0,3,0,3,0,0,0,0,0,5},
								   {0,0,0,7,0,0,0,0,0,0,0,7,0,0,0},
								   {6,0,0,0,0,0,2,0,2,0,0,0,0,0,6},
								   {0,2,0,0,0,6,0,0,0,6,0,0,0,2,0},
								   {0,0,6,0,2,0,0,4,0,0,2,0,6,0,0}};
        
        board2State = new char[][]                                 {{0,6,0,2,0,4,0,2,0,6,0},
								    {2,0,0,0,6,0,6,0,0,0,2},
								    {0,0,7,0,0,0,0,0,7,0,0},
								    {3,0,0,0,5,0,5,0,0,0,3},
								    {0,5,0,3,0,0,0,3,0,5,0},
								    {4,0,0,0,0,1,0,0,0,0,4},
								    {0,5,0,3,0,0,0,3,0,5,0},
								    {3,0,0,0,5,0,5,0,0,0,3},
								    {0,0,7,0,0,0,0,0,7,0,0},
								    {2,0,0,0,6,0,6,0,0,0,2},
								    {0,6,0,2,0,4,0,2,0,6,0}};

    char[] board1Letters = new char[]                       {'A','A','A','A','A','A','A','A','A','B','B','C','C','D','D',
							  'D','D','E','E','E','E','E','E','E','E','E','E','E','E','F',
							  'F','G','G','G','H','H','H','H','I','I','I','I','I','I','I','I','I',
							  'J','K','L','L','L','L','M','M','N','N','N','N','N','N','O',
							  'O','O','O','O','O','O','O','P','P','Q','R','R','R','R','R',
							  'R','S','S','S','S','S','T','T','T','T','T','T','U','U','U','U','U',
							  'V','V','W','W','X','Y','Y','Z','*','*'};
    
    char[] board2Letters = new char[]                       {'A','A','A','A','A','A','A','A','A','B','B','C','C','D','D',
							  'D','D','E','E','E','E','E','E','E','E','E','E','E','E','F',
							  'F','G','G','G','H','H','H','H','I','I','I','I','I','I','I','I',
							  'J','K','L','L','L','L','M','M','N','N','N','N','N','O',
							  'O','O','O','O','O','O','O','P','P','Q','R','R','R','R','R',
							  'R','S','S','S','S','S','T','T','T','T','T','T','U','U','U','U','U',
							  'V','V','W','W','X','Y','Y','Z','*','*'};
    
    resetState = new char[15][15];
		for(int i = 0; i < boardState.length; i++)
			for(int j = 0; j < boardState.length; j++)
				resetState[i][j] = boardState[i][j];
                
    initialBoard = new char[15][15];
		for(int i = 0; i < boardState.length; i++)
			for(int j = 0; j < boardState.length; j++)
				initialBoard[i][j] = boardState[i][j];
    
                values = new HashMap<>();
		values.put('A', 1);
		values.put('B', 5);
		values.put('C', 4);
		values.put('D', 4);
		values.put('E', 1);
		values.put('F', 5);
		values.put('G', 5);
		values.put('H', 2);
		values.put('I', 1);
		values.put('J', 8);
		values.put('K', 6);
		values.put('L', 4);
		values.put('M', 4);
		values.put('N', 1);
		values.put('O', 1);
		values.put('P', 5);
		values.put('Q', 10);
		values.put('R', 1);
		values.put('S', 2);
		values.put('T', 1);
		values.put('U', 2);
		values.put('V', 6);
		values.put('W', 5);
		values.put('X', 8);
		values.put('Y', 5);
		values.put('Z', 10);
                
                letters = new ArrayList<>();
                for (char i : board1Letters)
                    letters.add(i);
                
                letters2 = new ArrayList<>();
                for (char i : board2Letters)
                    letters2.add(i);
        
                // list containing all players for a current game
                allplayers = new ArrayList<>();
                
                turns = new ArrayList<>();
                
                turnIndex = 0;
                turn = 1;
                
                firstplay = true;
                
                coordinateI = -1;
                coordinateJ = -1;

    }
                
                public int getTurn(){
                    return turn;
                }
                
    


    public static WordLee getInstance() {
                        if(instance == null)
			instance = new WordLee();
                        return instance;    }
    
    public char getPosition(int i, int j) {
		return boardState[i][j];
	}
     public char getPosition2(int i, int j) {
		return board2State[i][j];
	}
     public int getPlayerCount() {
		return allplayers.size();
	}
     
     public ArrayList<Character> getCurrentPlayerHand(){
		return allplayers.get(turns.get(turnIndex)).getHand();
                
	}
     
     public String getPlayerName(int index) {
		return allplayers.get(index).getName();
	}
     
     public String getPresentName() {
		return allplayers.get(turns.get(turnIndex)).getName();
	}

	// get a player score based on index, used to show all scores
	public int getPlayerScore(int index) {
		return allplayers.get(index).getScore();
	}
        public void addPlayer(String name) {
		allplayers.add(new Player(name));
	}
        
        public void removePlayerLetter(char c) {
		ArrayList<Character> record = allplayers.get(turns.get(turnIndex)).playerHand;

		for(int i = 0; i < record.size(); i++) {
			if(Character.toLowerCase(record.get(i)) == (Character.toLowerCase(c))) {
				record.remove(i);
				break;
			}
		}
	}
        
        public void resetPresentPlayer() {
		allplayers.get(turns.get(turnIndex)).resetHand();
	}
        
        public void resetState() {
		if(turn == 1) firstplay = true;
		for(int i = 0; i < boardState.length; i++)
			for(int j = 0; j < boardState.length; j++)
				boardState[i][j] = resetState[i][j];
	}
        
        public boolean verifyWord(String word) {
                
                LuceneTester tester;
                try 
		{ 
			tester = new LuceneTester(); 
			tester.search(word);
                        if(word.equalsIgnoreCase(dataDir))
                            return true;
		} 
                
		catch (IOException e) 
		{ 
			e.printStackTrace(); 
		} 
		catch (ParseException e) 
		{ 
			e.printStackTrace(); 
		}
                return false;
	}
        
       /* private void search(String searchQuery) throws IOException, ParseException
	{ 
		searcher = new Searcher(indexDir); 
		long startTime = System.currentTimeMillis(); 
		TopDocs hits = searcher.search(searchQuery); 
		long endTime = System.currentTimeMillis(); 
		System.out.println(hits.totalHits + " documents found. Time :" + (endTime - startTime)); 
		for(ScoreDoc scoreDoc : hits.scoreDocs) 
		{ 
			Document doc = searcher.getDocument(scoreDoc); 
			System.out.println("File: " + doc.get(LuceneConstants.FILE_PATH)); 
		} 
		searcher.close(); 
	} */
        
      /* public boolean verifyWord(String word) {
		String line;
		
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("enable.txt")))) {			
			while((line = reader.readLine()) != null) {
				if(line.equalsIgnoreCase(word))
					return true;
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}*/
        
        public int calculateScore(char c, int i, int j) {
		switch (initialBoard[i][j]) {
			case 1:
				return values.get(Character.toUpperCase(c));
			case 2:
				return values.get(Character.toUpperCase(c)) * 2;
			case 3:
				return values.get(Character.toUpperCase(c)) * 3;
			case 4:
				return values.get(Character.toUpperCase(c));
			case 5:
				doubleWord = true;
				return values.get(Character.toUpperCase(c));
                        case 6:
				tripleWord = true;
				return values.get(Character.toUpperCase(c));
                        case 7:
                                letterReduction = true;
				return values.get(Character.toUpperCase(c));
			default:
				return values.get(Character.toUpperCase(c));
		}
	}
        
        public void sumScore(int score) {
		allplayers.get(turns.get(turnIndex)).score += score;
	}
        
        public boolean endTurn() {
		boolean completeTurn = false;
		
		// return false if no changes have been made
		if(coordinateI == -1 && coordinateJ == -1)
			return completeTurn;
		
		// check all words that have been made
		int i = coordinateI;
		int j = coordinateJ;
		int totalScore = 0;
		StringBuilder builder = new StringBuilder();
		
		// move marker horizontally to end of letters
		while(isLetter(i, j))
			i++;
		
		// move backwards to start of word, capturing all letters
		while(isLetter(--i, j)) {
			builder.append(boardState[i][j]);
			totalScore += calculateScore(boardState[i][j], i, j);
		}
		
		if(verifyWord(builder.reverse().toString())) {
			completeTurn = true;
			if(doubleWord && tripleWord) {
				sumScore(totalScore * 3 * 2);
				doubleWord = false;
				tripleWord = false;
			}
			else if(doubleWord) {
				sumScore(totalScore * 2);
				doubleWord = false;
			}
			else if(tripleWord) {
				sumScore(totalScore * 3);
				tripleWord = false;
			}
                        else if(letterReduction){
                                sumScore(totalScore -  values.get(Character.toUpperCase(0)));
				tripleWord = false;
                        }
			else
				sumScore(totalScore);
		}
		
		// clear builder
		builder = new StringBuilder();
		// reset values
		i = coordinateI;
		j = coordinateJ;
		totalScore = 0;
		
		// move marker vertically to end of letters
		while(isLetter(i, j))
			j++;
		
		// move vertically to start of word, capturing all letters
		while(isLetter(i, --j)) {
			builder.append(boardState[i][j]);
			totalScore += calculateScore(boardState[i][j], i, j);
		}
		
		if(verifyWord(builder.reverse().toString())) {
			completeTurn = true;
			if(doubleWord && tripleWord) {
				sumScore(totalScore * 3 * 2);
				doubleWord = false;
				tripleWord = false;
			}
			else if(doubleWord) {
				sumScore(totalScore * 2);
				doubleWord = false;
			}
			else if(tripleWord) {
				sumScore(totalScore * 3);
				tripleWord = false;
			}
                        else if(letterReduction){
                                sumScore(totalScore -  values.get(Character.toUpperCase(0)));
				tripleWord = false;
                        }
			else
				sumScore(totalScore);
		}
		
		// Reset lingering variables
		doubleWord = false;
		tripleWord = false;
		firstplay = false;
		coordinateI = -1;
		coordinateJ = -1;
		
		if(completeTurn)
			updateState();
		
		return completeTurn;
	}

        
        public void placeLetter(char letter, int i, int j) {
		boardState[i][j] = letter;
	}
        
        public boolean isValid(int i, int j) {
		// if there is currently a letter there, it is not valid
		if(isLetter(i, j))
			return false;
		
		// if it's not the first move
		if(!firstplay) {
			if(isLetter(i+1, j) || isLetter(i-1, j) || isLetter(i, j+1) || isLetter(i, j-1)) {
				coordinateI = i;
				coordinateJ = j;
				return true;
			}
		}
		// if it is the first turn
		else {
			// must start in the middle
			if(i == 7 && j == 7) {
				coordinateI = i;
				coordinateJ = j;
				firstplay = false;
				return true;
			}
		}
		
		return false;
	}
        
        public boolean isLetter(int i, int j) {
		boolean useLetter;
		try {
			useLetter = Character.isLetter(boardState[i][j]);
			return useLetter;
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}
        
        public void nextPlayer() {
		turnIndex = ++turnIndex % allplayers.size();
	}
        
        public void refillPlayerRack() {
		allplayers.get(turns.get(turnIndex)).fillHand();
	}

        
        	public void setUpPlayers(){
		// used to figure out turn order
		// get hand, get first drawn char and get player num
		// treemap is automatically sorted by the letter which determines the turn order
		TreeMap<String,Integer> turnsInfo = new TreeMap<>();
		int e = 0;
		for(Player i : allplayers) {
			i.fillHand();
			turnsInfo.put(i.getFirstLetter()+String.valueOf(e),e);
			e++;
		}
		// populate the turn list
		for(Map.Entry<String,Integer> i : turnsInfo.entrySet())
			turns.add(i.getValue());
	}
                
                public void updateState() {
		turn++;
		for(int i = 0; i < boardState.length; i++)
			for(int j = 0; j < boardState.length; j++)
				resetState[i][j] = boardState[i][j];
	}
    
public class Player {
		private final String pname;
		private int score;
		private ArrayList<Character> playerHand;
		private ArrayList<Character> oldletters;
		
		// CONSTRUCTOR
		public Player(String name) {
			
			pname = name;
			score = 0;
			playerHand = new ArrayList<>();
			oldletters = new ArrayList<>();
		}

		
		public char getFirstLetter() {
			return playerHand.get(0);
		}

		public ArrayList<Character> getHand() {
			return playerHand;
		}

		public String getName() {
			return pname;
		}

		public int getScore() {
			return score;
		}
		
		
		public void fillHand() {
			Random r = new Random();
			
			for(int i = 0; i < 7; i++){
				if(!letters.isEmpty() && playerHand.size() < 7){
					int e = r.nextInt(letters.size());
					playerHand.add(letters.get(e));
					letters.remove(e);
				}
			}
			oldletters.clear();
			oldletters.addAll(playerHand);
			
		}
                
                public void fillHand2() {
			Random r = new Random();
			
			for(int i = 0; i < 7; i++){
				if(!letters2.isEmpty() && playerHand.size() < 7){
					int e = r.nextInt(letters2.size());
					playerHand.add(letters2.get(e));
					letters2.remove(e);
				}
			}
			oldletters.clear();
			oldletters.addAll(playerHand);
			
		}
		public void resetHand() {
			playerHand.clear();
			playerHand.addAll(oldletters);
		}
	}

}

