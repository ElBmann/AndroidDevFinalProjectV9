package imonoko.androiddevfinalproject;


import android.view.View;

import java.util.Random;

import static java.lang.Math.min;


public class CeeLoModel
{
    int oneKind,twoKind,threeKind = 0;
    private Random diceRoll = new Random();
    private int [] roll;
    private int [] point;//.........................................................................the non-duplicate dice value when getting two of a kind
    private int [] gameScore;//.....................................................................the score for each match
    private boolean [] played;//....................................................................checks if both player went
    private int turn;
    private int round;
    private int recentWinner;//.....................................................................the player who just won the previous round
    Statistics CurrentStat;
    //Dice Roll

    public CeeLoModel(){
        recentWinner = 0;
        played = new boolean[2];
        roll = new int[3];
        point = new int[2];
        gameScore = new int[2];

        for (int pl = 0; pl < played.length; pl++)
            played[pl] = false;

        for (int p = 0; p < point.length; p++)
            point[p] = 0;

        for (int gs = 0; gs < gameScore.length; gs++)
            gameScore[gs] = 0;

        turn = 1;//..................................................................................Counter for turn
        round = 1;//..................................................................................Counter for round
    }
    public int play()
    {
        int currentTurn = turn;

        if(winRoundChecker()== 0) {//................................................................0 means no one won or lost
            if(turn == 1) // player 1 just went
            {
                turn = 2;
            }

            else // player 2 just went
            {
                turn = 1;
            }
        }
        return 0;
    }// End Play

    // This gets called within updateScore()
    // TODO: implement winMatcherChecker
    public int Round()
    {
        if (round >= 2 && winMatchChecker() > 0)
        {
            return winMatchChecker(); // game is over do not increment
        }

        round++;
        return 0;
    }

    // call this within GameActivity to check the current round and announce the start of the next round
    public int getRound()
    {
        return round;
    }

    private int singleRoll()
    {
        int rollValue = diceRoll.nextInt(6) + 1;// random number from 1 to 6
        return rollValue;
    }

    private int[] tripleRoll()
    {
        for (int r = 0; r < roll.length; r++)
        {
            int oneRoll = singleRoll();

            roll[r] = oneRoll;
        }

        return roll;
    }

    public void roll()
    {
        int[] rolls = tripleRoll(); // rolls 3 die

        for (int r = 0; r < rolls.length; r++)
        {
            roll[r] = rolls[r];
        }

        if(getCurrentPlayer() == 1)
            played[0] = true;

        if(getCurrentPlayer() == 2)
            played[1] = true;
    }

    public boolean needToReroll()
    {
        if (oneOfAKind()== true && twoOfAKind() == false && threeOfAKind() == false) // no "point" and no win
        {
            boolean hasOne = false;
            boolean hasTwo = false;
            boolean hasThree = false;
            boolean hasFour = false;
            boolean hasFive = false;
            boolean hasSix = false;

            for (int o = 0; o < roll.length; o++ ) // checks which of the following is present
            {
                if (roll[o] == 1)
                    hasOne = true;

                if (roll[o] == 2)
                    hasTwo = true;

                if (roll[o] == 3)
                    hasThree = true;

                if (roll[o] == 4)
                    hasFour = true;

                if (roll[o] == 5)
                    hasFive = true;

                if (roll[o] == 6)
                    hasSix = true;
            }

            if (hasOne && hasTwo && hasThree)
                return false; // instant loss, no need to reroll

            if (hasFour && hasFive && hasSix)
                return false; // instant loss, no need to reroll

            return true;
        }

        return false;
    }

    public boolean oneOfAKind(){//..................................................................If the dice are all unique Returns True

        if(roll[0]!=roll[1] && roll[0]!=roll[2] && roll[1]!=roll[2]){
            oneKind++;//............................................................................Counter 1 of a kind
            return true;
        }
        return false;
    }

    public boolean twoOfAKind(){//..................................................................If any of the dice are two of a kind Returns True
        if(roll[0]==roll[1] && roll[0]!= roll[2]){
            point[getCurrentPlayer( )-1] = roll[2];//...............................................current player's "point" is the non-duplicated die's value
            twoKind++;//.............................................................................Counter 2 of a kind
            return true;
        }
        else if(roll[0]==roll[2] && roll[0]!=roll[1]){
            point[getCurrentPlayer( )-1] = roll[1];//...............................................current player's "point" is the non-duplicated die's value
            twoKind++;
            return true;
        }
        else if(roll[1]==roll[2] && roll[1]!=roll[0]){
            point[getCurrentPlayer( )-1] = roll[0];//...............................................current player's "point" is the non-duplicated die's value
            twoKind++;
            return true;
        }else {
            return false;
        }
    }
    public boolean threeOfAKind(){//................................................................If the dice are all equal Returns True

        if(roll[0]==roll[1] && roll[0]==roll[2] && roll[1] == roll[2]){
            threeKind++;//..........................................................................Counter 3 Of a Kind
            return true;
        }
        return false;
    }

    public int showPoint( )//.......................................................................if got a twoOfAKind display point
    {
        if (twoOfAKind() == true)
            return point[getCurrentPlayer()-1];

        else
            return 0;
    }

    public int getCurrentPlayer( )//................................................................returns the current player- the player who is rolling the dice this turn
    {
        if(turn == 1)//.............................................................................It's player 1's turn
        {
            return 1;
        }

        else//......................................................................................It's player 2's turn
        {
            return 2;
        }
    }

    public int getOtherPlayer( )//..................................................................returns the opponent- the player who is not going this turn
    {
        if(turn == 1)//.............................................................................It's player 1's turn
        {
            return 2;
        }

        else//......................................................................................It's player 2's turn
        {
            return 1;
        }
    }

    /*0 == Nothing, 1 == Win, And 2 == Loss*/
// returns the corresponding int of the winner
//TODO: Needs a comparison to see who rolled first in case of a draw - Done through ComparePoint private method for twoOfAKind scenario
    public int winRoundChecker(){
        //..Checks if player won the Round

        // call the win checking methods
        if(oneOfAKind()== true)
        {
            boolean hasOne = false;
            boolean hasTwo = false;
            boolean hasThree = false;
            boolean hasFour = false;
            boolean hasFive = false;
            boolean hasSix = false;

            for (int o = 0; o < roll.length; o++ )//................................................checks which of the following is present
            {
                if (roll[o] == 1)
                    hasOne = true;

                if (roll[o] == 2)
                    hasTwo = true;

                if (roll[o] == 3)
                    hasThree = true;

                if (roll[o] == 4)
                    hasFour = true;

                if (roll[o] == 5)
                    hasFive = true;

                if (roll[o] == 6)
                    hasSix = true;
            }

            if(hasOne && hasTwo && hasThree){//........................................Checks for combo 123, is automatic loss
                return getOtherPlayer();// the other player wins
            }
            else if(hasFour && hasFive && hasSix){//........................................Checks for combo 456, is automatic win
                return getCurrentPlayer();
            }

        }//end if OneOfAKind
        /*Checks if one side of the die is 6 which is an automatic win*/
        if(twoOfAKind()==true){

            if(roll[0]==6 && roll[1]!=6 && roll[2]!=6 ) {//.........................................Checks if first dice = 6
                return getCurrentPlayer();

            }else if(roll[1]==6 && roll[0]!=6 && roll[2]!=6){//.....................................Checks if second dice = 6
                return getCurrentPlayer();

            }else if(roll[2]== 6 && roll[0]!=6 && roll[1]!=6){//....................................Checks if third dice = 6
                return getCurrentPlayer();
            }
            else //.................................................................................No instant winner, compare point instead
            {
                if ( played[0] == true && played[1]  == true) //both players went
                {
                    return comparePoint();
                }
            }

        }//end if twoOfAKind

        if(threeOfAKind()==true){//.................................................................Three of a kind auto win
            return getCurrentPlayer();
        }

        return 0;
    }

    private int comparePoint()
    {
        if ( point[getOtherPlayer()-1] >= point [getCurrentPlayer()-1])//...........................the player who went first has greater or equal value then the person who went second
        {
            return getOtherPlayer();//..............................................................the player who went first won
        }

        else
        {
            return getCurrentPlayer();//............................................................the player who went afterwards won
        }
    }

    // increase the score counter of the winner
    // TODO: call this within GameActivity to progress the game
    public void updateScores()
    {
        int winner = winRoundChecker();//...........................................................get the identity of the winner, if any

        if(winner > 0)//............................................................................there was a winner
        {
            gameScore[winner-1] += 1;//.............................................................increment the score of the winner by one
            recentWinner = winner;//................................................................identify the winner
            Round();//..............................................................................check if there is a winner and increment the round counter
        }
    }

    // TODO: call this within GameActivity to display the scores in textviews for the players
    public int[] getScores()
    {
        return gameScore;
    }

    public int getRecentWinner()
    {
        return recentWinner;
    }

    // clears variables for next round
    public void resetForNextRound()
    {
        roll = new int[3];//........................................................................reset the roll
        point = new int[2];//.......................................................................reset point
        recentWinner = 0;
        GameActivity.dicePos1.setVisibility(View.INVISIBLE);
        GameActivity.dicePos2.setVisibility(View.INVISIBLE);
        GameActivity.dicePos3.setVisibility(View.INVISIBLE);

        for (int pl = 0; pl < played.length; pl++)
            played[pl] = false;
    }

    public String displayResult()//.................................................................Displays the Dice
    {

        String values = "";
        GameActivity.dicePos1.setVisibility(View.VISIBLE);
        GameActivity.dicePos2.setVisibility(View.VISIBLE);
        GameActivity.dicePos3.setVisibility(View.VISIBLE);

        /*Position one in tableGrid*/
        if(roll[0]==1){
            GameActivity.dicePos1.setImageResource(R.drawable.dice1);
        }
        else if(roll[0]==2){
            GameActivity.dicePos1.setImageResource(R.drawable.dice2);
        }
        else if(roll[0]==3){
            GameActivity.dicePos1.setImageResource(R.drawable.dice3);
        }
        else if(roll[0]==4){
            GameActivity.dicePos1.setImageResource(R.drawable.dice4);
        }
        else if(roll[0]==5){
            GameActivity.dicePos1.setImageResource(R.drawable.dice5);
        }
        else if(roll[0]==6){
            GameActivity.dicePos1.setImageResource(R.drawable.dice6);
        }
        /*Position 2 in tableGrid*/
        if(roll[1]==1){
        GameActivity.dicePos2.setImageResource(R.drawable.dice1);
    }
    else if(roll[1]==2){
        GameActivity.dicePos2.setImageResource(R.drawable.dice2);
    }
    else if(roll[1]==3){
        GameActivity.dicePos2.setImageResource(R.drawable.dice3);
    }
    else if(roll[1]==4){
        GameActivity.dicePos2.setImageResource(R.drawable.dice4);
    }
    else if(roll[1]==5){
        GameActivity.dicePos2.setImageResource(R.drawable.dice5);
    }
    else if(roll[1]==6){
        GameActivity.dicePos2.setImageResource(R.drawable.dice6);
    }
        /*Position 3 in tableGrid*/

        if(roll[2]==1){
            GameActivity.dicePos3.setImageResource(R.drawable.dice1);
        }
        else if(roll[2]==2){
            GameActivity.dicePos3.setImageResource(R.drawable.dice2);
        }
        else if(roll[2]==3){
            GameActivity.dicePos3.setImageResource(R.drawable.dice3);
        }
        else if(roll[2]==4){
            GameActivity.dicePos3.setImageResource(R.drawable.dice4);
        }
        else if(roll[2]==5){
            GameActivity.dicePos3.setImageResource(R.drawable.dice5);
        }
        else if(roll[2]==6){
            GameActivity.dicePos3.setImageResource(R.drawable.dice6);
        }

        for (int r = 0; r < roll.length; r++)
        {
            values += roll[r];
            if (r == 2)
            {
                values += "      ";
            }
            else
            {
                values += "   -  ";
            }

        }

        return values;
    }

    public int winMatchChecker(){//.................................................................Checks if player won the match
        if (gameScore[getCurrentPlayer()-1] == 2)
            return getCurrentPlayer();

        else if(gameScore[getOtherPlayer()-1] == 2)
            return getOtherPlayer();

        else
            return 0;
     }

    public void newGame()//sets up for a new game
    {
        //resets all variables
        round = 1;
        turn = 1;
        for (int gs = 0; gs < gameScore.length; gs++)
            gameScore[gs] = 0;
        resetForNextRound();
    }

    public String displayRolls()
    {
        // get the row of the 2D array for the current player
        String diceValues = "";

        for (int r = 0; r < roll.length; r++)
        {
            diceValues +=  roll[r];
            if (r == 2)
            {
                diceValues += "  ";
            }
            else
            {
                diceValues += " - ";
            }
        }

        return diceValues;
    }

    public int winMethod()
    {
        int win;

        if (oneOfAKind())
            return 1;

        else if (twoOfAKind() && point[0] == point[1] && foundUniqueSix() == false) // if the two "points" are equal, the player who went first wins
            return 20;

        else if (twoOfAKind())
            return 2;

        else if (threeOfAKind())
            return 3;

        return 0;
    }

    public int rollForFirst()
    {
        int firstGo = singleRoll();

        if (getCurrentPlayer() == 1)
        {
            if (firstGo == 1)
            {
                GameActivity.dicePos1.setImageResource(R.drawable.dice1);
            }

            else if (firstGo == 2)
            {
                GameActivity.dicePos1.setImageResource(R.drawable.dice2);
            }

            else if (firstGo == 3)
            {
                GameActivity.dicePos1.setImageResource(R.drawable.dice3);
            }

            else if (firstGo == 4)
            {
                GameActivity.dicePos1.setImageResource(R.drawable.dice4);
            }

            else if (firstGo == 5)
            {
                GameActivity.dicePos1.setImageResource(R.drawable.dice5);
            }

            else if (firstGo == 6)
            {
                GameActivity.dicePos1.setImageResource(R.drawable.dice6);
            }

            GameActivity.dicePos1.setVisibility(View.VISIBLE);

        }

        else
        {
            if (firstGo == 1)
            {
                GameActivity.dicePos3.setImageResource(R.drawable.dice1);
            }

            else if (firstGo == 2)
            {
                GameActivity.dicePos3.setImageResource(R.drawable.dice2);
            }

            else if (firstGo == 3)
            {
                GameActivity.dicePos3.setImageResource(R.drawable.dice3);
            }

            else if (firstGo == 4)
            {
                GameActivity.dicePos3.setImageResource(R.drawable.dice4);
            }

            else if (firstGo == 5)
            {
                GameActivity.dicePos3.setImageResource(R.drawable.dice5);
            }

            else if (firstGo == 6)
            {
                GameActivity.dicePos3.setImageResource(R.drawable.dice6);
            }

            GameActivity.dicePos3.setVisibility(View.VISIBLE);
        }
        return firstGo;
    }


    public boolean foundUniqueSix() // detects if there is a two of a kind with one six
    {
        int hasSix = 0;

        if (twoOfAKind()) {
            for (int r = 0; r < roll.length; r++) {
                if (roll[r] == 6)
                    hasSix++;
            }
        }
        return hasSix == 1; // true if has exactly one six, else false
    }

    public void setActivePlayer(int player)
    {
        if (player == 1 || player == 2)
            turn = player;
    }
}