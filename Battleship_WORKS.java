public class Battleship extends ConsoleProgram
{
    private static final int[] SHIP_LENGTHS = {2, 3, 3, 4, 5};
    public static final int UNSET = -1;
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    public void run()
    {
        // Copy over your Battleship class here
        // Then finish the game! Make sure your program is robust
        // it should handle incorrect user input and other edge cases
        System.out.println("=========================");
        System.out.println("Welcome to Battleship!");
        System.out.println("Made by David Li");
        readLine("=========================");
        Player player = new Player();
        
        playerChooseShips(player);
        System.out.println("=========================");
        opponentChooseShips(player);
        //player.printEnemyShips();
        System.out.println("=========================");
        System.out.println("Now you're gonna guess the enemy's ship locations.");
        System.out.println("And the computer is gonna guess your ship locations.");
        System.out.println("There are 17 ship locations in total.");
        System.out.println("Whoever first sinks all 5 of the opponent's ships (hits all the 17 ship locations) wins the game!");
        System.out.println("Best of luck!");
        System.out.println("=========================");
        readLine("Hit enter to start guessing.");
        askForGuess(player);
        System.out.println("=========================");
        System.out.println("Thanks for playing!!!");
    }
    public void askForGuess(Player player)
    {
        int totalPlayerHits = 0, totalEnemyHits = 0;
        int currentEnemySunkShips = 0;
        int currentPlayerSunkShips = 0;
        while(totalPlayerHits<17 && totalEnemyHits<17)
        {
            //Player starts guessing
            readLine("Hit enter for your turn");
            System.out.println("Enemy's grid:");
            player.printMyGuesses();
            System.out.println();
            System.out.println("It's your turn to guess.");
            String rowS;
            int rowI = UNSET, col = UNSET;
            boolean successfullyGuessed = false;
            while(successfullyGuessed == false)
            {
                while(true)
                {
                    rowS = readLine("Which row? (A-J) ");
                    if(!rowS.equals(""))
                    {
                        rowI = (int)Character.toUpperCase(rowS.charAt(0)) - 65;
                        if(rowI >= 0 && rowI <=9)
                        {
                            break;
                        }
                        System.out.println("Make sure your letter is between 'A' and 'J'!");
                        readLine("Try again!");
                        System.out.println();
                    }
                }
                while(true)
                {
                    col = readInt("Which column? (1-10) ") - 1;
                    if(col >= 0 && col <=9)
                    {
                        break;
                    }
                    System.out.println("Make sure your number is between 1 and 10!");
                    readLine("Try again!");
                    System.out.println();
                }
                if(!player.playerAlreadyGuessed(rowI,col))
                {
                    successfullyGuessed = true;
                }
                else
                {
                    System.out.println("You cannot guess the same location twice!");
                    System.out.println("Try again!");
                    System.out.println();
                }
            }
            player.recordPlayerGuess(rowI, col);
            int totalEnemySunkShips = player.enemySunkShips();
            if(player.opponentHasShip(rowI, col))
            {
                totalPlayerHits++;
                System.out.println("You got a hit!");
                if( totalEnemySunkShips > currentEnemySunkShips && totalEnemySunkShips == 1)
                {
                    System.out.println("Nice! You have sunk one of the enemy's ships!");
                    currentEnemySunkShips++;
                }
                else if ( totalEnemySunkShips > currentEnemySunkShips )
                {
                    System.out.println("Congrats on sinking another enemy's ship!");
                    currentEnemySunkShips++;
                }
            }
            else
            {
                System.out.println("Nope, that was a miss.");
            }
            player.printMyGuesses();
            System.out.println();
            if(currentEnemySunkShips == 0)
            {
                System.out.println("Come on! You haven't sunk any of enemy's ships yet!");
            }
            else if(currentEnemySunkShips == 1)
            {
                System.out.println("So far, you have sunk " + currentEnemySunkShips + " enemy ship.");
            }
            else
            {
                System.out.println("So far, you have sunk " + currentEnemySunkShips + " enemy ships.");
            }
            System.out.println("Total Hits = " + totalPlayerHits + " out of 17");
            //Player finishes guessing
            if(totalPlayerHits == 17)
            {
                System.out.println("=========================");
                System.out.println("Oh boy! You win!");
                break;
            }
            //The computer starts guessing
            readLine("Hit enter for the computer's turn");
            int row = UNSET;
            col = UNSET;
            successfullyGuessed = false;
            while(successfullyGuessed == false)
            {
                row = Randomizer.nextInt(0, 9);
                col = Randomizer.nextInt(0, 9);
                if(!player.enemyAlreadyGuessed(row,col))
                {
                    successfullyGuessed = true;
                }
            }
            player.recordOpponentGuess(row, col);
            int totalPlayerSunkShips = player.playerSunkShips();
            if(player.playerHasShip(row, col))
            {
                totalEnemyHits++;
                System.out.println("The computer guessed row " + (char)(row + 65) + " and column " + (col + 1));
                System.out.println("The computer got a hit.");
                if( totalPlayerSunkShips > currentPlayerSunkShips && totalPlayerSunkShips == 1)
                {
                    System.out.println("The enemy has sunk one of your ships.");
                    currentEnemySunkShips++;
                }
                else if ( totalEnemySunkShips > currentEnemySunkShips )
                {
                    System.out.println("The enemy has sunk another ship of yours.");
                    currentEnemySunkShips++;
                }
            }
            else
            {
                System.out.println("The computer guessed row " + (char)(row + 65) + " and column " + (col + 1));
                System.out.println("The computer missed.");
            }
            System.out.println("Your grid:");
            player.printOpponentGuesses();
            System.out.println();
            if(currentPlayerSunkShips == 0)
            {
                System.out.println("The enemy hasn't sunk any of your ships yet.");
            }
            else
            {
                System.out.println("So far, the enemy has sunk " + currentEnemySunkShips + " of your ships.");
            }
            System.out.println("Total Hits = " + totalEnemyHits + " out of 17");
            //The computer finishes guessing
            if(totalEnemyHits == 17)
            {
                System.out.println("=========================");
                System.out.println("Game over! The enemy wins!");
            }
        }
    }
    public void playerChooseShips(Player player)
    {
        readLine("First you need to choose the location of your ships.");
        for(int i=0; i<5; i++)
        {
            readLine("Hit enter to place the next ship.");
            //create ship
            Ship ship = new Ship(SHIP_LENGTHS[i]);
            //add ship
            boolean successfullyAdd = false;
            while(successfullyAdd == false)
            {
                System.out.println("Your current grid of ships:");
                player.printMyShips();
                System.out.println();
                System.out.println("Now you need to place a ship of length " + SHIP_LENGTHS[i]);
                String rowS,direction;
                int rowI, col, dir = UNSET;
                while(true)
                {
                    //set location
                    System.out.println();
                    while(true)
                    {
                        rowS = readLine("Which row? (A-J) ");
                        if(!rowS.equals(""))
                        {
                            rowI = (int)Character.toUpperCase(rowS.charAt(0)) - 65;
                            if(rowI >= 0 && rowI <=9)
                            {
                                break;
                            }
                            System.out.println("Make sure your letter is between 'A' and 'J'!");
                            readLine("Try again!");
                            System.out.println();
                        }
                    }
                    while(true)
                    {
                        col = readInt("Which column? (1-10) ") - 1;
                        if(col >= 0 && col <=9)
                        {
                            break;
                        }
                        System.out.println("Make sure your number is between 1 and 10!");
                        readLine("Try again!");
                        System.out.println();
                    }
                    //set direction
                    while(true)
                    {
                        direction = readLine("Horizontal or vertical? ");
                        if(!direction.equals(""))
                        {
                            if(Character.toLowerCase(direction.charAt(0))=='h' || Character.toLowerCase(direction.charAt(0))=='v')
                            {
                                break;
                            }
                            System.out.println("Make sure your first letter starts with either 'h' or 'v'!");
                            readLine("Try again!");
                            System.out.println();
                        }
                    }
                    dir = UNSET;
                    if( Character.toLowerCase(direction.charAt(0)) == 'h' )
                    {
                        dir = HORIZONTAL;
                        if(col <= 10 - ship.getLength())
                        {
                            break;
                        }
                    }
                    else
                    {
                        dir = VERTICAL;
                        if(rowI <= 10 - ship.getLength())
                        {
                            break;
                        }
                    }
                    System.out.println("There's not enough space for this ship!");
                    readLine("Try Again!");
                }
                
                if(dir == HORIZONTAL)
                {
                    successfullyAdd = true;
                    for(int j=col; j<col+ship.getLength(); j++)
                    {
                        if( player.playerHasShip(rowI,j) )
                        {
                            successfullyAdd = false;
                            System.out.println("You cannot overlap ship locations!");
                            readLine("Try again!");
                            break;
                        }
                    }
                    if(successfullyAdd == true)
                    {
                        player.chooseShipLocation(ship, rowI, col, dir);
                        System.out.println("This ship is succesfully placed.");
                    }
                }
                else
                {
                    successfullyAdd = true;
                    for(int j=rowI; j<rowI+ship.getLength(); j++)
                    {
                        if( player.playerHasShip(j,col) )
                        {
                            successfullyAdd = false;
                            System.out.println("You cannot overlap ship locations!");
                            readLine("Try again!");
                            break;
                        }
                    }
                    if(successfullyAdd == true)
                    {
                        player.chooseShipLocation(ship, rowI, col, dir);
                        System.out.println("This ship is succesfully placed.");
                    }
                }
            }
            System.out.println();
        }
        readLine("Hit enter to see your ship locations");
        System.out.println("Location of my ships");
        player.printMyShips();
        System.out.println("");
    }
    public void opponentChooseShips(Player player)
    {
        readLine("Hit enter for the enemy to choose their ship locations");
        for(int i=0; i<5; i++)
        {
            //create ship
            Ship ship = new Ship(SHIP_LENGTHS[i]);
            boolean successfullyAdd = false;
            //add ship
            while(successfullyAdd == false)
            {
                //set direction
                int dir = UNSET;
                if(Randomizer.nextBoolean()==true)
                {
                    dir = HORIZONTAL;
                }
                else
                {
                    dir = VERTICAL;
                }
                //set location
                int row = UNSET, col = UNSET;
                if(dir == HORIZONTAL)
                {
                    row = Randomizer.nextInt(0, 9);
                    col = Randomizer.nextInt(0, 10 - SHIP_LENGTHS[i]);
                    successfullyAdd = true;
                    for(int j=col; j<col+ship.getLength(); j++)
                    {
                        if( player.opponentHasShip(row,j) )
                        {
                            successfullyAdd = false;
                            break;
                        }
                    }
                    if(successfullyAdd == true)
                    {
                        player.chooseEnemyShipLocation(ship, row, col, dir);
                    }
                }
                else
                {
                    row = Randomizer.nextInt(0, 10 - SHIP_LENGTHS[i]);
                    col = Randomizer.nextInt(0,9);
                    successfullyAdd = true;
                    for(int j=row; j<row+ship.getLength(); j++)
                    {
                        if( player.opponentHasShip(j,col) )
                        {
                            successfullyAdd = false;
                            break;
                        }
                    }
                    if(successfullyAdd == true)
                    {
                        player.chooseEnemyShipLocation(ship, row, col, dir);
                    }
                }
            }
        }
        System.out.println("The enemy has placed their ships.");
    }
}
