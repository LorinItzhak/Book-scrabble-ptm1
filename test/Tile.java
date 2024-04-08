package test;

import java.util.Random;

public class Tile {
    private final char letter;
    private final int score;

    public Tile(char letter, int score){ 
        this.letter=letter;
        this.score=score;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + letter;
        result = prime * result + score;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Tile other = (Tile) obj;
        if (letter != other.letter)
            return false;
        if (score != other.score)
            return false;
        return true;
    }


    public static class Bag {

        private int[] letters; 
        private int[] initialLetters; 
        private int amountOfTiles;  
        private Tile[] tiles;
        private static Bag instance;

        public Bag() {

            this.amountOfTiles = 98;
            this.letters= new int[]{9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1};
            this.initialLetters= this.letters.clone();
            this.tiles=new Tile[26];
            int[] valueOfLetter = new int[]{1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};

            for(int i=0;i<26;i++){
                new Tile((char)('A'+i),valueOfLetter[i]);
            }

        }

        public Tile getRand(){

            if(amountOfTiles==0){return null;};

            Random rand= new Random();
            int randomIndex = rand.nextInt(tiles.length);
            Tile randonTile = tiles[randomIndex];

            while (letters[randomIndex] == 0) {
                randomIndex = rand.nextInt(tiles.length); // Generates a random integer between 0 and 26
            }

            letters[randomIndex]--;

            if(letters[randomIndex]==0){
                amountOfTiles--;
            }

            return tiles[randomIndex];


        }

        public Tile getTile(char letter) {

            int indexOfletter= (int)letter - ((int)'A');
            if(indexOfletter > 26 || indexOfletter > 0 || letters[indexOfletter] == 0 )
            {
              return null;
            }

            letters[indexOfletter]--;

             if (letters[indexOfletter] == 0) {
                    amountOfTiles--;
                }

             return tiles[indexOfletter];

        }


        public void put(Tile tile) {

            if(letters[((int) tile.letter)-((int)('A'))] < initialLetters[((int) tile.letter)-((int)'A')]){

                if(letters[((int) tile.letter)-((int)('A'))]==0){
                    amountOfTiles++;
                }

                letters[((int) tile.letter)-((int)('A'))]++;  

            }

        }



        public int size(){
            return amountOfTiles;
        }

        public int[] getQuantities(){
            return this.letters.clone();
        }


        public static Bag getBag(){
            if(instance==null){
                instance=new Bag();
            }
            return instance;

        }
    }
    
}
