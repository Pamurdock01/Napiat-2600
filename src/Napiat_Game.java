import java.awt.*;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

//To play NAPIAT 2600, press the run button below!
public class Napiat_Game {
    //Galaxy Variables:
    public static int subGal;
    public static int gal;
    public static int uraniumPrice;
    public static int producePrice;
    public static int waterPrice;
    public static int uraniumBase;
    public static int produceBase;
    public static int waterBase;
    public static int year;
    public static int pirateShip;
    public static int booty;
    public static int asteroids;

    //Game Variables:
    public static InputReader reader;
    public static Responder responder;
    public static Random rand;

    //Ship Variables:
    public static int coins;
    public static int guns;
    public static int greed;
    public static int hold;
    public static int uranium;
    public static int produce;
    public static int water;
    public static int shipShape;
    public static String rank;
    public static int level;
    public static int damage;

    //Warehouse and bank Variables:
    public static int wareHouseTotal;
    public static int wareHouseUranium;
    public static int wareHouseProduce;
    public static int wareHouseWater;
    public static int bankTotal;

    public static void main(String[] args) {
        reader = new InputReader();
        responder = new Responder();
        rand = new Random();
        subGal = 0;
        gal = 1;
        coins = 0;
        guns = 1;
        greed = 0;
        year = 2600;
        uraniumPrice = 1000;
        producePrice = 400;
        waterPrice = 100;
        uraniumBase = 1000;
        produceBase = 400;
        waterBase = 100;
        uranium = 0;
        produce = 0;
        water = 0;
        hold = 10;
        shipShape = 100;
        wareHouseTotal = 0;
        wareHouseUranium = 0;
        wareHouseProduce = 0;
        wareHouseWater = 0;
        bankTotal = 0;
        pirateShip = 20;
        booty = 0;
        rank = "Airman (level 1)";
        level = 1;
        damage = 0;
        asteroids = 0;

        System.out.println("Napiat!");
        System.out.println("Welcome to galaxy 1, Napiat");
        System.out.println("You command a rogue military galactic cargo vessel based in the Napiat galactic port.");
        System.out.println("Use your vessel to buy, sell, and transport cargo throughout the universe.");
        System.out.println("You may travel to galaxies 1 through 7 to buy and sell uranium, produce, and water.");
        System.out.println("Your ship's hold can carry 10 items at first, but you may have the opportunity to upgrade it!");
        System.out.println("Beware of pirates and robbers on your journeys!");
        System.out.println("You can buy guns to defend your ship, but remember, they take up some of your hold space!");
        System.out.println("Keep your ship in tip-top shape!");
        System.out.println("Upon ending the game you will be given a military rank based upon your successes.");
        System.out.println("Elite cargo vessel commanders can hope to become level 11 Generals.  Good Luck!");
        System.out.println("\nThe following commands will be necessary in your travels:");
        System.out.println("     bye - (end game)");
        System.out.println("     galaxy1/galaxy2/galaxy3/galaxy4/galaxy5/galaxy6/galaxy7 - (travel)");
        System.out.println("     g1/g2/g3/g4/g5/g6/g7 - (travel)");
        System.out.println("     sell - (sell cargo)");
        System.out.println("     buy - (buy cargo)");
        System.out.println("     warehouse - (add/remove from storage)");
        System.out.println("     bank - (deposit/withdraw from bank)");
        System.out.println("     prices - (show current galaxy prices)");
        System.out.println("     leave - (leave the warehouse or the bank)");
        System.out.println("     stats - (statistics)");
        System.out.println("     retire - (end game with ranking)");
        System.out.println("\nHere is a map of the galaxies and asteroid belts: ");
        System.out.println("\n          (1)          ");
        System.out.println("     (2)       (3)     ");
        System.out.println("(5)  ..............  (4)");
        System.out.println("   ....   (6)  ....    ");
        System.out.println();
        System.out.println("          (7)          ");
        System.out.println("\n----------------------------------------------------------");
        System.out.println("\nWould you like to begin the game with 'money' or 'guns'?");
        boolean finished = false;
        while (!finished) {
            HashSet input = reader.getInput();
            if (input.contains("bye")) {
                finished = true;
                System.out.println("\nYou have been dishonorably discharged from your duties----GAME OVER");
            } else if (input.contains("money") || input.contains("m")) {
                finished = true;
                coins += 1000;
                System.out.println("\nYou will begin with 1000 gold coins");
                System.out.println("*********************************************************");
                galaxy1();
            } else if (input.contains("guns") || input.contains("g")) {
                finished = true;
                guns += 1;
                System.out.println("\nYou will start the game with 2 guns");
                System.out.println("*********************************************************");
                galaxy1();
            } else {
                String response = responder.generateResponse(input);
                System.out.println(response);
            }
        }
    }

    //Galaxy methods:
    private static void galaxy1() {
        gal = 1;
        if (subGal == 0) {
            System.out.println("\nWELCOME TO GALAXY 1");
            int fire = rand.nextInt(10);
            if (fire == 0) {
                System.out.println("\n**************************************************************************************");
                System.out.println("*You have just learned that your warehouse burned and was rebuilt while you were away*");
                System.out.println("*All items in storage were destroyed.                                                *");
                System.out.println("**************************************************************************************");
                wareHouseUranium = 0;
                wareHouseProduce = 0;
                wareHouseWater = 0;
            }
            int upgrade = rand.nextInt(3);
            int robbery = rand.nextInt(5);
            if (upgrade == 0) {
                if (bankTotal + coins > 300000) {
                    System.out.println("\nWould you like to upgrade your ship for " + ((bankTotal + coins) / 3 + 1) + " coins?");
                    upgradeShip();
                } else {
                    System.out.println("\nWould you like to upgrade your ship for " + (coins / 2 + 1) + " coins?");
                    upgradeShip();
                }
            } else if (upgrade == 1) {
                System.out.println("\nWould you like to buy a new gun for " + (coins / 4 + 1) + " coins?");
                buyGun();
            }
            if (robbery == 0) {
                System.out.println("\n**************************************************************************************");
                System.out.println("*YOU HAVE BEEN BEATEN UP AND ROBBED!                                                  *");
                System.out.println("*The thieves have taken " + ((coins * 3) / 4) + " coins!                                      *");
                System.out.println("**************************************************************************************");
                coins -= (coins * 3) / 4;
            }
            int index = rand.nextInt(5) + 1;
            int index2 = rand.nextInt(5) + 1;
            int index3 = rand.nextInt(5) + 1;
            uraniumPrice = uraniumBase * index;
            producePrice = produceBase * index2;
            waterPrice = waterBase * index3;
            System.out.println("\nEntering Galaxy 1 market: ");
            System.out.println("_________________");
            System.out.println("Current Prices:");
            System.out.println("Uranium: " + uraniumPrice);
            System.out.println("Produce: " + producePrice);
            System.out.println("Water: " + waterPrice);
        }
        System.out.println("\nWhat would you like to do?");
        boolean finished = false;
        while (!finished) {
            HashSet input = reader.getInput();
            if (input.contains("bye")) {
                finished = true;
                System.out.println("\nYou have been dishonorably discharged from your duties----GAME OVER");
            } else if (input.contains("galaxy2") || input.contains("g2")) {
                System.out.print("\n\u000C");
                finished = true;
                year += 1;
                System.out.println("\nLeaving Galaxy 1");
                int pirates = rand.nextInt(3);
                int pGuns = guns / 2 + 1;
                if (year < 2610) {
                    pirateShip = 25;
                } else if (year < 2620) {
                    pirateShip = 35;
                } else if (year < 2625) {
                    pirateShip = 45;
                } else if (year < 2650) {
                    pirateShip = 50;
                } else if (year < 2675) {
                    pirateShip = 60;
                } else {
                    pirateShip = 70;
                }
                if (pirates == 0) {
                    System.out.println("\n_~ )_)_~");
                    System.out.println(")_))_))_)");
                    System.out.println("_!__!__!_");
                    System.out.println("\\______t/");
                    System.out.println("~~~~~~~~~~~~~");
                    System.out.println("**************************************************************************************");
                    System.out.println("\n*Pirates are attacking!                                                              ");
                    while (pirateShip > 0 && shipShape > 0) {
                        pirateShip -= guns * 3;
                        System.out.println("Pirate Ship Condition: " + pirateShip);
                        shipShape -= pGuns * 2;
                        if (shipShape > 0) {
                            System.out.println("Your Ship Condition:  " + shipShape);
                        } else {
                            System.out.println("Your Ship Condition:  0");
                        }
                    }
                    if (shipShape < 1 && pirateShip < 1) {
                        System.out.println("\n*You won the battle...barely.                                                        *");
                        System.out.println("**************************************************************************************");
                        shipShape = 2;
                        subGal = 0;
                        galaxy2();
                    } else if (pirateShip < 1) {
                        System.out.println("\n*You defeated the pirates!");
                        int randBooty = rand.nextInt(5);
                        if (randBooty == 0) {
                            booty = coins / 2 + 200;
                            coins += booty;
                        } else if (randBooty == 1) {
                            booty = coins / 3 + 1;
                            coins += booty;
                        } else if (randBooty == 2) {
                            booty = coins / 4 + 1;
                            coins += booty;
                        } else if (randBooty == 3) {
                            booty = coins / 5 + 1;
                            coins += booty;
                        } else {
                            booty = 1970;
                            coins += booty;
                        }
                        System.out.println("\n*You have gained " + booty + " coins.                                                    ");
                        System.out.println("**************************************************************************************");
                        subGal = 0;
                        galaxy2();
                    } else {
                        Toolkit.getDefaultToolkit().beep();
                        Toolkit.getDefaultToolkit().beep();
                        System.out.println("\n*You have lost the battle.                                                           *");
                        coins = 0;
                        System.out.println("*The pirates have taken all your coins.                                              *");
                        System.out.println("*Your ship has been sunk....GAME OVER                                                *");
                        System.out.println("**************************************************************************************");
                        finished = true;
                        stats();
                        System.out.println("\nWould you like to play again?");
                        playAgain();
                    }
                } else {
                    subGal = 0;
                    galaxy2();
                }
            } else if (input.contains("galaxy3") || input.contains("g3")) {
                System.out.print("\n\u000C");
                finished = true;
                year += 1;
                System.out.println("\nLeaving Galaxy 1");
                int pirates = rand.nextInt(2);
                int pGuns = guns / 2 + 1;
                if (year < 2610) {
                    pirateShip = 25;
                } else if (year < 2620) {
                    pirateShip = 35;
                } else if (year < 2625) {
                    pirateShip = 45;
                } else if (year < 2650) {
                    pirateShip = 50;
                } else if (year < 2675) {
                    pirateShip = 60;
                } else {
                    pirateShip = 70;
                }
                if (pirates == 0) {
                    System.out.println("\n_~ )_)_~");
                    System.out.println(")_))_))_)");
                    System.out.println("_!__!__!_");
                    System.out.println("\\______t/");
                    System.out.println("~~~~~~~~~~~~~");
                    System.out.println("**************************************************************************************");
                    System.out.println("\n*Pirates are attacking!                                                              ");
                    while (pirateShip > 0 && shipShape > 0) {
                        pirateShip -= guns * 3;
                        System.out.println("Pirate Ship Condition: " + pirateShip);
                        shipShape -= pGuns * 2;
                        if (shipShape > 0) {
                            System.out.println("Your Ship Condition:  " + shipShape);
                        } else {
                            System.out.println("Your Ship Condition:  0");
                        }
                    }
                    if (shipShape < 1 && pirateShip < 1) {
                        System.out.println("\n*You won the battle...barely.                                                        *");
                        System.out.println("**************************************************************************************");
                        shipShape = 2;
                        subGal = 0;
                        galaxy3();
                    } else if (pirateShip < 1) {
                        System.out.println("\nYou defeated the pirates!");
                        int randBooty = rand.nextInt(5);
                        if (randBooty == 0) {
                            booty = coins / 2 + 200;
                            coins += booty;
                        } else if (randBooty == 1) {
                            booty = coins / 3 + 1;
                            coins += booty;
                        } else if (randBooty == 2) {
                            booty = coins / 4 + 1;
                            coins += booty;
                        } else if (randBooty == 3) {
                            booty = coins / 5 + 1;
                            coins += booty;
                        } else {
                            booty = 1970;
                            coins += booty;
                        }
                        System.out.println("\n*You have gained " + booty + " coins.                                                    ");
                        System.out.println("**************************************************************************************");
                        subGal = 0;
                        galaxy3();
                    } else {
                        System.out.println("\n*You have lost the battle.                                                           *");
                        coins = 0;
                        System.out.println("*The pirates have taken all your coins.                                              *");
                        System.out.println("*Your ship has been sunk....GAME OVER                                                *");
                        System.out.println("**************************************************************************************");
                        finished = true;
                        stats();
                        System.out.println("\nWould you like to play again?");
                        playAgain();
                    }
                } else {
                    subGal = 0;
                    galaxy3();
                }
            } else if (input.contains("sell") || input.contains("s")) {
                System.out.println("\nWhat would you like to sell?");
                finished = true;
                sell();
            } else if (input.contains("buy") || input.contains("b")) {
                System.out.println("\nWhat do you want to buy?");
                finished = true;
                buy();
            } else if (input.contains("warehouse")) {
                finished = true;
                wareHouse();
            } else if (input.contains("bank")) {
                finished = true;
                bank();
            } else if (input.contains("stats") || input.contains("stat")) {
                stats();
            } else if (input.contains("prices")) {
                prices();
            } else if (input.contains("retire")) {
                System.out.println("\nEnjoy retirement!");
                finished = true;
                stats();
            } else if (input.contains("easter") || input.contains("egg")) {
                greed++;
                if (greed < 5) {
                    System.out.println("\nYou are sneaky.  Here are some extra coins for you");
                    coins += 3;
                } else {
                    System.out.println("\nYou are too greedy!");
                    int g = coins / 2;
                    coins -= g;
                }
            } else {
                String response = responder.generateResponse(input);
                System.out.println(response);
            }
        }
    }

    private static void galaxy2() {
        gal = 2;
        if (subGal == 0) {
            System.out.println("\nWELCOME TO GALAXY 2");
            int sale = rand.nextInt(15);
            int index = rand.nextInt(5) + 1;
            int index2 = rand.nextInt(5) + 1;
            int index3 = rand.nextInt(5) + 1;
            if (sale == 0) {
                System.out.println("\n*****************************");
                System.out.println("Uranium prices have dropped!");
                System.out.println("*****************************");
                uraniumPrice = 200;
                producePrice = produceBase * index2;
                waterPrice = waterBase * index3;
                System.out.println("\nEntering Galaxy 2 market: ");
                System.out.println("_________________");
                System.out.println("Current Prices:");
                System.out.println("Uranium: " + uraniumPrice);
                System.out.println("Produce: " + producePrice);
                System.out.println("Water: " + waterPrice);
            } else if (sale == 1) {
                System.out.println("\n*****************************");
                System.out.println("Produce prices have dropped!");
                System.out.println("*****************************");
                producePrice = 75;
                uraniumPrice = uraniumBase * index;
                waterPrice = waterBase * index3;
                System.out.println("\nEntering Galaxy 2 market: ");
                System.out.println("_________________");
                System.out.println("Current Prices:");
                System.out.println("Uranium: " + uraniumPrice);
                System.out.println("Produce: " + producePrice);
                System.out.println("Water: " + waterPrice);
            } else if (sale == 3) {
                System.out.println("\n*****************************");
                System.out.println("Water prices have dropped!");
                System.out.println("*****************************");
                waterPrice = 30;
                producePrice = produceBase * index2;
                uraniumPrice = uraniumBase * index;
                System.out.println("\nEntering Galaxy 2 market: ");
                System.out.println("_________________");
                System.out.println("Current Prices:");
                System.out.println("Uranium: " + uraniumPrice);
                System.out.println("Produce: " + producePrice);
                System.out.println("Water: " + waterPrice);
            } else {
                uraniumPrice = uraniumBase * index;
                producePrice = produceBase * index2;
                waterPrice = waterBase * index3;
                System.out.println("\nEntering Galaxy 2 market: ");
                System.out.println("_________________");
                System.out.println("Current Prices:");
                System.out.println("Uranium: " + uraniumPrice);
                System.out.println("Produce: " + producePrice);
                System.out.println("Water: " + waterPrice);
            }
        }
        System.out.println("\nWhat would you like to do?");
        boolean finished = false;
        while (!finished) {
            HashSet input = reader.getInput();
            if (input.contains("bye")) {
                finished = true;
                System.out.println("\nYou have been dishonorably discharged from your duties----GAME OVER");
            } else if (input.contains("galaxy1") || input.contains("g1")) {
                System.out.print("\n\u000C");
                finished = true;
                year += 1;
                System.out.println("\nLeaving Galaxy 2");
                int pirates = rand.nextInt(3);
                int pGuns = guns / 2 + 1;
                if (year < 2610) {
                    pirateShip = 25;
                } else if (year < 2620) {
                    pirateShip = 35;
                } else if (year < 2625) {
                    pirateShip = 45;
                } else if (year < 2650) {
                    pirateShip = 50;
                } else if (year < 2675) {
                    pirateShip = 60;
                } else {
                    pirateShip = 70;
                }
                if (pirates == 0) {
                    System.out.println("\n_~ )_)_~");
                    System.out.println(")_))_))_)");
                    System.out.println("_!__!__!_");
                    System.out.println("\\______t/");
                    System.out.println("~~~~~~~~~~~~~");
                    System.out.println("**************************************************************************************");
                    System.out.println("\n*Pirates are attacking!                                                              ");
                    while (pirateShip > 0 && shipShape > 0) {
                        pirateShip -= guns * 3;
                        System.out.println("Pirate Ship Condition: " + pirateShip);
                        shipShape -= pGuns * 2;
                        if (shipShape > 0) {
                            System.out.println("Your Ship Condition:  " + shipShape);
                        } else {
                            System.out.println("Your Ship Condition:  0");
                        }
                    }
                    if (shipShape < 1 && pirateShip < 1) {
                        System.out.println("\n*You won the battle...barely.                                                        *");
                        System.out.println("**************************************************************************************");
                        shipShape = 2;
                        subGal = 0;
                        galaxy1();
                    } else if (pirateShip < 1) {
                        System.out.println("\nYou defeated the pirates!");
                        int randBooty = rand.nextInt(5);
                        if (randBooty == 0) {
                            booty = coins / 2 + 200;
                            coins += booty;
                        } else if (randBooty == 1) {
                            booty = coins / 3 + 1;
                            coins += booty;
                        } else if (randBooty == 2) {
                            booty = coins / 4 + 1;
                            coins += booty;
                        } else if (randBooty == 3) {
                            booty = coins / 5 + 1;
                            coins += booty;
                        } else {
                            booty = 1970;
                            coins += booty;
                        }
                        System.out.println("\n*You have gained " + booty + " coins.                                                    ");
                        System.out.println("**************************************************************************************");
                        subGal = 0;
                        galaxy1();
                    } else {
                        System.out.println("\n*You have lost the battle.                                                           *");
                        coins = 0;
                        System.out.println("*The pirates have taken all your coins.                                              *");
                        System.out.println("*Your ship has been sunk....GAME OVER                                                *");
                        System.out.println("**************************************************************************************");
                        finished = true;
                        stats();
                        System.out.println("\nWould you like to play again?");
                        playAgain();
                    }
                } else if (pirates == 1 && uranium > 0) {
                    System.out.println("\n**************************************************************************************");
                    System.out.println("*The export of uranium has been outlawed in this galaxy!");
                    System.out.println("*Your uranium cargo has been seized!");
                    System.out.println("**************************************************************************************");
                    uranium = 0;
                    subGal = 0;
                    galaxy1();
                } else {
                    subGal = 0;
                    galaxy1();
                }
            } else if (input.contains("galaxy3") || input.contains("g3")) {
                System.out.print("\n\u000C");
                finished = true;
                year += 1;
                System.out.println("\nLeaving Galaxy 2");
                int pirates = rand.nextInt(2);
                int pGuns = guns / 2 + 1;
                if (year < 2610) {
                    pirateShip = 25;
                } else if (year < 2620) {
                    pirateShip = 35;
                } else if (year < 2625) {
                    pirateShip = 45;
                } else if (year < 2650) {
                    pirateShip = 50;
                } else if (year < 2675) {
                    pirateShip = 60;
                } else {
                    pirateShip = 70;
                }
                if (pirates == 0) {
                    System.out.println("\n_~ )_)_~");
                    System.out.println(")_))_))_)");
                    System.out.println("_!__!__!_");
                    System.out.println("\\______t/");
                    System.out.println("~~~~~~~~~~~~~");
                    System.out.println("**************************************************************************************");
                    System.out.println("*Pirates are attacking!                                                              ");
                    while (pirateShip > 0 && shipShape > 0) {
                        pirateShip -= guns * 3;
                        System.out.println("Pirate Ship Condition: " + pirateShip);
                        shipShape -= pGuns * 2;
                        if (shipShape > 0) {
                            System.out.println("Your Ship Condition:  " + shipShape);
                        } else {
                            System.out.println("Your Ship Condition:  0");
                        }
                    }
                    if (shipShape < 1 && pirateShip < 1) {
                        System.out.println("\n*You won the battle...barely.                                                        *");
                        System.out.println("**************************************************************************************");
                        shipShape = 2;
                        subGal = 0;
                        galaxy3();
                    } else if (pirateShip < 1) {
                        System.out.println("\nYou defeated the pirates!");
                        int randBooty = rand.nextInt(5);
                        if (randBooty == 0) {
                            booty = coins / 2 + 200;
                            coins += booty;
                        } else if (randBooty == 1) {
                            booty = coins / 3 + 1;
                            coins += booty;
                        } else if (randBooty == 2) {
                            booty = coins / 4 + 1;
                            coins += booty;
                        } else if (randBooty == 3) {
                            booty = coins / 5 + 1;
                            coins += booty;
                        } else {
                            booty = 1970;
                            coins += booty;
                        }
                        System.out.println("\n*You have gained " + booty + " coins.                                                    ");
                        System.out.println("**************************************************************************************");
                        subGal = 0;
                        galaxy3();
                    } else {
                        System.out.println("\n*You have lost the battle.                                                           *");
                        coins = 0;
                        System.out.println("*The pirates have taken all your coins.                                              *");
                        System.out.println("*Your ship has been sunk....GAME OVER                                                *");
                        System.out.println("**************************************************************************************");
                        finished = true;
                        stats();
                        System.out.println("\nWould you like to play again?");
                        playAgain();
                    }
                } else if (pirates == 1 && uranium > 0) {
                    System.out.println("\n**************************************************************************************");
                    System.out.println("*The export of uranium has been outlawed in this galaxy!");
                    System.out.println("*Your uranium cargo has been seized!");
                    System.out.println("**************************************************************************************");
                    uranium = 0;
                    subGal = 0;
                    galaxy3();
                } else {
                    subGal = 0;
                    galaxy3();
                }
            } else if (input.contains("galaxy5") || input.contains("g5")) {
                System.out.print("\n\u000C");
                finished = true;
                year += 1;
                System.out.println("\nLeaving Galaxy 2");
                int pirates = rand.nextInt(2);
                int pGuns = guns / 2 + 1;
                if (year < 2610) {
                    pirateShip = 25;
                } else if (year < 2620) {
                    pirateShip = 35;
                } else if (year < 2625) {
                    pirateShip = 45;
                } else if (year < 2650) {
                    pirateShip = 50;
                } else if (year < 2675) {
                    pirateShip = 60;
                } else {
                    pirateShip = 70;
                }
                if (pirates == 0) {
                    System.out.println("\n_~ )_)_~");
                    System.out.println(")_))_))_)");
                    System.out.println("_!__!__!_");
                    System.out.println("\\______t/");
                    System.out.println("~~~~~~~~~~~~~");
                    System.out.println("**************************************************************************************");
                    System.out.println("*Pirates are attacking!                                                              ");
                    while (pirateShip > 0 && shipShape > 0) {
                        pirateShip -= guns * 3;
                        System.out.println("Pirate Ship Condition: " + pirateShip);
                        shipShape -= pGuns * 2;
                        if (shipShape > 0) {
                            System.out.println("Your Ship Condition:  " + shipShape);
                        } else {
                            System.out.println("Your Ship Condition:  0");
                        }
                    }
                    if (shipShape < 1 && pirateShip < 1) {
                        System.out.println("\n*You won the battle...barely.                                                        *");
                        System.out.println("**************************************************************************************");
                        shipShape = 2;
                        subGal = 0;
                        galaxy5();
                    } else if (pirateShip < 1) {
                        System.out.println("\nYou defeated the pirates!");
                        int randBooty = rand.nextInt(5);
                        if (randBooty == 0) {
                            booty = coins / 2 + 200;
                            coins += booty;
                        } else if (randBooty == 1) {
                            booty = coins / 3 + 1;
                            coins += booty;
                        } else if (randBooty == 2) {
                            booty = coins / 4 + 1;
                            coins += booty;
                        } else if (randBooty == 3) {
                            booty = coins / 5 + 1;
                            coins += booty;
                        } else {
                            booty = 1970;
                            coins += booty;
                        }
                        System.out.println("\n*You have gained " + booty + " coins.                                                    ");
                        System.out.println("**************************************************************************************");
                        subGal = 0;
                        galaxy5();
                    } else {
                        System.out.println("\n*You have lost the battle.                                                           *");
                        coins = 0;
                        System.out.println("*The pirates have taken all your coins.                                              *");
                        System.out.println("*Your ship has been sunk....GAME OVER                                                *");
                        System.out.println("**************************************************************************************");
                        finished = true;
                        stats();
                        System.out.println("\nWould you like to play again?");
                        playAgain();
                    }
                } else if (pirates == 1 && uranium > 0) {
                    System.out.println("\n**************************************************************************************");
                    System.out.println("*The export of uranium has been outlawed in this galaxy!");
                    System.out.println("*Your uranium cargo has been seized!");
                    System.out.println("**************************************************************************************");
                    uranium = 0;
                    subGal = 0;
                    galaxy5();
                } else {
                    subGal = 0;
                    galaxy5();
                }
            } else if (input.contains("sell") || input.contains("s")) {
                System.out.println("\nWhat would you like to sell?");
                finished = true;
                sell();
            } else if (input.contains("buy") || input.contains("b")) {
                System.out.println("\nWhat do you want to buy?");
                finished = true;
                buy();
            } else if (input.contains("stats") || input.contains("stat")) {
                stats();
            } else if (input.contains("prices")) {
                prices();
            } else if (input.contains("retire")) {
                System.out.println("\nEnjoy retirement!");
                finished = true;
                stats();
            } else {
                String response = responder.generateResponse(input);
                System.out.println(response);
            }
        }
    }

    private static void galaxy3() {
        gal = 3;
        if (subGal == 0) {
            System.out.println("\nWELCOME TO GALAXY 3");
            int sale = rand.nextInt(15);
            int index = rand.nextInt(5) + 1;
            int index2 = rand.nextInt(5) + 1;
            int index3 = rand.nextInt(5) + 1;
            if (sale == 0) {
                System.out.println("\n*****************************");
                System.out.println("Uranium prices have risen!");
                System.out.println("*****************************");
                uraniumPrice = 12000;
                producePrice = produceBase * index2;
                waterPrice = waterBase * index3;
                System.out.println("\nEntering Galaxy 3 market: ");
                System.out.println("_________________");
                System.out.println("Current Prices:");
                System.out.println("Uranium: " + uraniumPrice);
                System.out.println("Produce: " + producePrice);
                System.out.println("Water: " + waterPrice);
            } else if (sale == 1) {
                System.out.println("\n*****************************");
                System.out.println("Produce prices have risen!");
                System.out.println("*****************************");
                producePrice = 4500;
                uraniumPrice = uraniumBase * index;
                waterPrice = waterBase * index3;
                System.out.println("\nEntering Galaxy 3 market: ");
                System.out.println("_________________");
                System.out.println("Current Prices:");
                System.out.println("Uranium: " + uraniumPrice);
                System.out.println("Produce: " + producePrice);
                System.out.println("Water: " + waterPrice);
            } else if (sale == 3) {
                System.out.println("\n*****************************");
                System.out.println("Water prices have risen!");
                System.out.println("*****************************");
                waterPrice = 1900;
                producePrice = produceBase * index2;
                uraniumPrice = uraniumBase * index;
                System.out.println("\nEntering Galaxy 3 market: ");
                System.out.println("_________________");
                System.out.println("Current Prices:");
                System.out.println("Uranium: " + uraniumPrice);
                System.out.println("Produce: " + producePrice);
                System.out.println("Water: " + waterPrice);
            } else {
                uraniumPrice = uraniumBase * index;
                producePrice = produceBase * index2;
                waterPrice = waterBase * index3;
                System.out.println("\nEntering Galaxy 3 market: ");
                System.out.println("_________________");
                System.out.println("Current Prices:");
                System.out.println("Uranium: " + uraniumPrice);
                System.out.println("Produce: " + producePrice);
                System.out.println("Water: " + waterPrice);
            }
        }
        System.out.println("\nWhat would you like to do?");
        boolean finished = false;
        while (!finished) {
            HashSet input = reader.getInput();
            if (input.contains("bye")) {
                finished = true;
                System.out.println("\nYou have been dishonorably discharged from your duties----GAME OVER");
            } else if (input.contains("galaxy2") || input.contains("g2")) {
                System.out.print("\n\u000C");
                finished = true;
                year += 1;
                System.out.println("\nLeaving Galaxy 3");
                int pirates = rand.nextInt(3);
                int pGuns = guns / 2 + 1;
                if (year < 2610) {
                    pirateShip = 25;
                } else if (year < 2620) {
                    pirateShip = 35;
                } else if (year < 2625) {
                    pirateShip = 45;
                } else if (year < 2650) {
                    pirateShip = 50;
                } else if (year < 2675) {
                    pirateShip = 60;
                } else {
                    pirateShip = 70;
                }
                if (pirates == 0) {
                    System.out.println("\n_~ )_)_~");
                    System.out.println(")_))_))_)");
                    System.out.println("_!__!__!_");
                    System.out.println("\\______t/");
                    System.out.println("~~~~~~~~~~~~~");
                    System.out.println("**************************************************************************************");
                    System.out.println("\n*Pirates are attacking!                                                              ");

                    while (pirateShip > 0 && shipShape > 0) {
                        pirateShip -= guns * 3;
                        System.out.println("Pirate Ship Condition: " + pirateShip);
                        shipShape -= pGuns * 2;
                        if (shipShape > 0) {
                            System.out.println("Your Ship Condition:  " + shipShape);
                        } else {
                            System.out.println("Your Ship Condition:  0");
                        }
                    }
                    if (shipShape < 1 && pirateShip < 1) {
                        System.out.println("\n*You won the battle...barely.                                                        *");
                        System.out.println("**************************************************************************************");
                        shipShape = 2;
                        subGal = 0;
                        galaxy2();
                    } else if (pirateShip < 1) {
                        System.out.println("\nYou defeated the pirates!");
                        int randBooty = rand.nextInt(5);
                        if (randBooty == 0) {
                            booty = coins / 2 + 200;
                            coins += booty;
                        } else if (randBooty == 1) {
                            booty = coins / 3 + 1;
                            coins += booty;
                        } else if (randBooty == 2) {
                            booty = coins / 4 + 1;
                            coins += booty;
                        } else if (randBooty == 3) {
                            booty = coins / 5 + 1;
                            coins += booty;
                        } else {
                            booty = 1970;
                            coins += booty;
                        }
                        System.out.println("\n*You have gained " + booty + " coins.                                                    ");
                        System.out.println("**************************************************************************************");
                        subGal = 0;
                        galaxy2();
                    } else {
                        System.out.println("\n*You have lost the battle.                                                           *");
                        coins = 0;
                        System.out.println("*The pirates have taken all your coins.                                              *");
                        System.out.println("*Your ship has been sunk....GAME OVER                                                *");
                        System.out.println("**************************************************************************************");
                        finished = true;
                        stats();
                        System.out.println("\nWould you like to play again?");
                        playAgain();
                    }
                } else {
                    subGal = 0;
                    galaxy2();
                }
            } else if (input.contains("galaxy1") || input.contains("g1")) {
                System.out.print("\n\u000C");
                finished = true;
                year += 1;
                System.out.println("\nLeaving Galaxy 3");
                int pirates = rand.nextInt(2);
                int pGuns = guns / 2 + 1;
                if (year < 2610) {
                    pirateShip = 25;
                } else if (year < 2620) {
                    pirateShip = 35;
                } else if (year < 2625) {
                    pirateShip = 45;
                } else if (year < 2650) {
                    pirateShip = 50;
                } else if (year < 2675) {
                    pirateShip = 60;
                } else {
                    pirateShip = 70;
                }
                if (pirates == 0) {
                    System.out.println("\n_~ )_)_~");
                    System.out.println(")_))_))_)");
                    System.out.println("_!__!__!_");
                    System.out.println("\\______t/");
                    System.out.println("~~~~~~~~~~~~~");
                    System.out.println("**************************************************************************************");
                    System.out.println("\n*Pirates are attacking!                                                              ");
                    while (pirateShip > 0 && shipShape > 0) {
                        pirateShip -= guns * 3;
                        System.out.println("Pirate Ship Condition: " + pirateShip);
                        shipShape -= pGuns * 2;
                        if (shipShape > 0) {
                            System.out.println("Your Ship Condition:  " + shipShape);
                        } else {
                            System.out.println("Your Ship Condition:  0");
                        }
                    }
                    if (shipShape < 1 && pirateShip < 1) {
                        System.out.println("\n*You won the battle...barely.                                                        *");
                        System.out.println("**************************************************************************************");
                        shipShape = 2;
                        subGal = 0;
                        galaxy1();
                    } else if (pirateShip < 1) {
                        System.out.println("\nYou defeated the pirates!");
                        int randBooty = rand.nextInt(5);
                        if (randBooty == 0) {
                            booty = coins / 2 + 200;
                            coins += booty;
                        } else if (randBooty == 1) {
                            booty = coins / 3 + 1;
                            coins += booty;
                        } else if (randBooty == 2) {
                            booty = coins / 4 + 1;
                            coins += booty;
                        } else if (randBooty == 3) {
                            booty = coins / 5 + 1;
                            coins += booty;
                        } else {
                            booty = 1970;
                            coins += booty;
                        }
                        System.out.println("\n*You have gained " + booty + " coins.                                                    ");
                        System.out.println("**************************************************************************************");
                        subGal = 0;
                        galaxy1();
                    } else {
                        System.out.println("\n*You have lost the battle.                                                           *");
                        coins = 0;
                        System.out.println("*The pirates have taken all your coins.                                              *");
                        System.out.println("*Your ship has been sunk....GAME OVER                                                *");
                        System.out.println("**************************************************************************************");
                        finished = true;
                        stats();
                        System.out.println("\nWould you like to play again?");
                        playAgain();
                    }
                } else {
                    subGal = 0;
                    galaxy1();
                }
            } else if (input.contains("galaxy4") || input.contains("g4")) {
                finished = true;
                year += 1;
                System.out.print("\n\u000C");
                subGal = 0;
                galaxy4();
            } else if (input.contains("sell") || input.contains("s")) {
                System.out.println("\nWhat would you like to sell?");
                finished = true;
                sell();
            } else if (input.contains("buy") || input.contains("b")) {
                System.out.println("\nWhat do you want to buy?");
                finished = true;
                buy();
            } else if (input.contains("stats") || input.contains("stat")) {
                stats();
            } else if (input.contains("prices")) {
                prices();
            } else if (input.contains("retire")) {
                System.out.println("\nEnjoy retirement!");
                finished = true;
                stats();
            } else {
                String response = responder.generateResponse(input);
                System.out.println(response);
            }
        }
    }

    private static void galaxy4() {
        gal = 4;
        System.out.println("\nWELCOME TO GALAXY 4");
        System.out.println("_________________");
        System.out.println("\nWould you like us to make repairs on your ship for " + hold * 1000 * level + " coins?");
        boolean finished = false;
        while (!finished) {
            HashSet input = reader.getInput();
            if (input.contains("bye")) {
                finished = true;
                System.out.println("\nYou have been dishonorably discharged from your duties----GAME OVER");
            } else if (input.contains("yes")) {
                if (coins >= hold * 1000 * level) {
                    coins -= hold * 1000 * level;
                    if (shipShape > 64) {
                        System.out.println("\nYour ship has been returned to 100% condition");
                        shipShape = 100;
                        System.out.println("\nYou may travel to Galaxy 3 or Galaxy 6.  You may also check the 'map'");
                    } else {
                        System.out.println("\nYour ship has been partially repaired");
                        shipShape += 35;
                        System.out.println("\nYou may travel to Galaxy 3 or Galaxy 6.  You may also check the 'map'");
                    }
                } else {
                    System.out.println("\nSorry but you don't have enough coins to pay for the repairs");
                    System.out.println("Come back with more gold");
                    System.out.println("\nYou may travel to Galaxy 3 or Galaxy 6.  You may also check the 'map'");
                }
            } else if (input.contains("no")) {
                System.out.println("\nWhy would you come to Galaxy 4 if you are not in need of repairs?");
                System.out.println("Your activities here are suspicious");
                System.out.println("You have been fined " + coins / 3 + " coins");
                coins -= coins / 3;
                System.out.println("\nYou may travel to Galaxy 3 or Galaxy 6.  You may also check the 'map'");
            } else if (input.contains("galaxy2") || input.contains("g2")) {
                System.out.println("\nThere is not a direct route to Galaxy 2");
            } else if (input.contains("galaxy1") || input.contains("g1")) {
                System.out.println("\nThere is not a direct route to Galaxy 1");
            } else if (input.contains("map")) {
                map();
            } else if (input.contains("galaxy3") || input.contains("g3")) {
                System.out.print("\n\u000C");
                finished = true;
                year += 1;
                System.out.println("\nLeaving Galaxy 4");
                int pirates = rand.nextInt(2);
                int pGuns = guns / 2 + 1;
                if (year < 2610) {
                    pirateShip = 25;
                } else if (year < 2620) {
                    pirateShip = 35;
                } else if (year < 2625) {
                    pirateShip = 45;
                } else if (year < 2650) {
                    pirateShip = 50;
                } else if (year < 2675) {
                    pirateShip = 60;
                } else {
                    pirateShip = 70;
                }
                if (pirates == 0) {
                    System.out.println("\n_~ )_)_~");
                    System.out.println(")_))_))_)");
                    System.out.println("_!__!__!_");
                    System.out.println("\\______t/");
                    System.out.println("~~~~~~~~~~~~~");
                    System.out.println("**************************************************************************************");
                    System.out.println("\n*Pirates are attacking!                                                              ");
                    while (pirateShip > 0 && shipShape > 0) {
                        pirateShip -= guns * 3;
                        System.out.println("Pirate Ship Condition: " + pirateShip);
                        shipShape -= pGuns * 2;
                        if (shipShape > 0) {
                            System.out.println("Your Ship Condition:  " + shipShape);
                        } else {
                            System.out.println("Your Ship Condition:  0");
                        }
                    }
                    if (shipShape < 1 && pirateShip < 1) {
                        System.out.println("\n*You won the battle...barely.                                                        *");
                        System.out.println("**************************************************************************************");
                        System.out.println();
                        shipShape = 2;
                        galaxy3();
                    } else if (pirateShip < 1) {
                        System.out.println("\nYou defeated the pirates!");
                        int randBooty = rand.nextInt(5);
                        if (randBooty == 0) {
                            booty = coins / 2 + 200;
                            coins += booty;
                        } else if (randBooty == 1) {
                            booty = coins / 3 + 1;
                            coins += booty;
                        } else if (randBooty == 2) {
                            booty = coins / 4 + 1;
                            coins += booty;
                        } else if (randBooty == 3) {
                            booty = coins / 5 + 1;
                            coins += booty;
                        } else {
                            booty = 1970;
                            coins += booty;
                        }
                        System.out.println("\n*You have gained " + booty + " coins.                                                    ");
                        System.out.println("**************************************************************************************");
                        System.out.println();
                        galaxy3();
                    } else {
                        System.out.println("\n*You have lost the battle.                                                           *");
                        coins = 0;
                        System.out.println("\n*The pirates have taken all your coins.                                              *");
                        System.out.println("*Your ship has been sunk....GAME OVER                                                *");
                        System.out.println("**************************************************************************************");
                        System.out.println();
                        finished = true;
                        stats();
                        System.out.println();
                        System.out.println("\nWould you like to play again?");
                        playAgain();
                    }
                } else {
                    subGal = 0;
                    galaxy3();
                }
            } else if (input.contains("galaxy5") || input.contains("g5")) {
                System.out.println("\nThere is not a direct route to Galaxy 5");
            } else if (input.contains("galaxy6") || input.contains("g6")) {
                System.out.print("\n\u000C");
                finished = true;
                year += 1;
                System.out.println("\nLeaving Galaxy 4...entering the asteroid field");
                asteroids = rand.nextInt(10);
                if (hold == 40) {
                    if (asteroids == 0) {
                        damage = 0;
                    } else if (asteroids == 1) {
                        damage = 10;
                    } else if (asteroids == 2) {
                        damage = 15;
                    } else if (asteroids == 3) {
                        damage = 20;
                    } else if (asteroids == 4) {
                        damage = 25;
                    } else if (asteroids == 5) {
                        damage = 30;
                    } else if (asteroids == 6) {
                        damage = 35;
                    } else if (asteroids == 7) {
                        damage = 40;
                    } else if (asteroids == 8) {
                        damage = 45;
                    } else if (asteroids == 9) {
                        damage = 60;
                    }
                } else if (hold == 50) {
                    if (asteroids == 0) {
                        damage = 0;
                    } else if (asteroids == 1) {
                        damage = 15;
                    } else if (asteroids == 2) {
                        damage = 20;
                    } else if (asteroids == 3) {
                        damage = 25;
                    } else if (asteroids == 4) {
                        damage = 30;
                    } else if (asteroids == 5) {
                        damage = 35;
                    } else if (asteroids == 6) {
                        damage = 40;
                    } else if (asteroids == 7) {
                        damage = 45;
                    } else if (asteroids == 8) {
                        damage = 50;
                    } else if (asteroids == 9) {
                        damage = 55;
                    }
                } else if (hold == 60) {
                    if (asteroids == 0) {
                        damage = 0;
                    } else if (asteroids == 1) {
                        damage = 20;
                    } else if (asteroids == 2) {
                        damage = 25;
                    } else if (asteroids == 3) {
                        damage = 30;
                    } else if (asteroids == 4) {
                        damage = 35;
                    } else if (asteroids == 5) {
                        damage = 40;
                    } else if (asteroids == 6) {
                        damage = 45;
                    } else if (asteroids == 7) {
                        damage = 50;
                    } else if (asteroids == 8) {
                        damage = 55;
                    } else if (asteroids == 9) {
                        damage = 60;
                    }
                } else if (hold == 70) {
                    if (asteroids == 0) {
                        damage = 0;
                    } else if (asteroids == 1) {
                        damage = 25;
                    } else if (asteroids == 2) {
                        damage = 30;
                    } else if (asteroids == 3) {
                        damage = 35;
                    } else if (asteroids == 4) {
                        damage = 40;
                    } else if (asteroids == 5) {
                        damage = 45;
                    } else if (asteroids == 6) {
                        damage = 50;
                    } else if (asteroids == 7) {
                        damage = 55;
                    } else if (asteroids == 8) {
                        damage = 60;
                    } else if (asteroids == 9) {
                        damage = 65;
                    }
                } else if (hold > 79) {
                    if (asteroids == 0) {
                        damage = 0;
                    } else if (asteroids == 1) {
                        damage = 35;
                    } else if (asteroids == 2) {
                        damage = 40;
                    } else if (asteroids == 3) {
                        damage = 45;
                    } else if (asteroids == 4) {
                        damage = 50;
                    } else if (asteroids == 5) {
                        damage = 55;
                    } else if (asteroids == 6) {
                        damage = 60;
                    } else if (asteroids == 7) {
                        damage = 65;
                    } else if (asteroids == 8) {
                        damage = 70;
                    } else if (asteroids == 9) {
                        damage = 75;
                    }
                }
                subGal = 0;
                galaxy6();
            } else if (input.contains("stats") || input.contains("stat")) {
                stats();
            } else if (input.contains("retire")) {
                System.out.println("\nYou cannot retire in deep space.  You must make it back to an inner galaxy (1, 2, or 3)");
            } else {
                String response = responder.generateResponse(input);
                System.out.println(response);
            }
        }
    }

    private static void galaxy5() {
        gal = 5;
        if (subGal == 0) {
            int index = rand.nextInt(2) + 1;
            int index2 = rand.nextInt(2) + 1;
            int index3 = rand.nextInt(2) + 1;

            uraniumPrice = (uraniumBase * index) / 2;
            producePrice = produceBase * index2;
            waterPrice = waterBase * index3;
            System.out.println("\nEntering Galaxy 5 market: ");
            System.out.println("_________________");
            System.out.println("Current Prices:");
            System.out.println("Uranium: " + uraniumPrice);
            System.out.println("Produce: " + producePrice);
            System.out.println("Water: " + waterPrice);
        }
        System.out.println("\nYou may travel to galaxy 2 or galaxy 6.  You may also check the 'map'");
        System.out.println("\nWhat would you like to do?");
        boolean finished = false;
        while (!finished) {
            HashSet input = reader.getInput();
            if (input.contains("bye")) {
                finished = true;
                System.out.println("\nYou have been dishonorably discharged from your duties----GAME OVER");
            } else if (input.contains("map")) {
                map();
            } else if (input.contains("galaxy2") || input.contains("g2")) {
                System.out.print("\n\u000C");
                finished = true;
                year += 1;
                System.out.println("\nLeaving Galaxy 5");
                int pirates = rand.nextInt(3);
                int pGuns = guns / 2 + 1;
                if (year < 2610) {
                    pirateShip = 25;
                } else if (year < 2620) {
                    pirateShip = 35;
                } else if (year < 2625) {
                    pirateShip = 45;
                } else if (year < 2650) {
                    pirateShip = 50;
                } else if (year < 2675) {
                    pirateShip = 60;
                } else {
                    pirateShip = 70;
                }
                if (pirates == 0) {
                    System.out.println("\n_~ )_)_~");
                    System.out.println(")_))_))_)");
                    System.out.println("_!__!__!_");
                    System.out.println("\\______t/");
                    System.out.println("~~~~~~~~~~~~~");
                    System.out.println("**************************************************************************************");
                    System.out.println("\n*Pirates are attacking!                                                              ");
                    while (pirateShip > 0 && shipShape > 0) {
                        pirateShip -= guns * 3;
                        System.out.println("Pirate Ship Condition: " + pirateShip);
                        shipShape -= pGuns * 2;
                        if (shipShape > 0) {
                            System.out.println("Your Ship Condition:  " + shipShape);
                        } else {
                            System.out.println("Your Ship Condition:  0");
                        }
                    }
                    if (shipShape < 1 && pirateShip < 1) {
                        System.out.println("\n*You won the battle...barely.                                                        *");
                        System.out.println("**************************************************************************************");
                        System.out.println();
                        shipShape = 2;
                        subGal = 0;
                        galaxy2();
                    } else if (pirateShip < 1) {
                        System.out.println("\nYou defeated the pirates!");
                        int randBooty = rand.nextInt(5);
                        if (randBooty == 0) {
                            booty = coins / 2 + 200;
                            coins += booty;
                        } else if (randBooty == 1) {
                            booty = coins / 3 + 1;
                            coins += booty;
                        } else if (randBooty == 2) {
                            booty = coins / 4 + 1;
                            coins += booty;
                        } else if (randBooty == 3) {
                            booty = coins / 5 + 1;
                            coins += booty;
                        } else {
                            booty = 1970;
                            coins += booty;
                        }
                        System.out.println("\n*You have gained " + booty + " coins.                                                    ");
                        System.out.println("**************************************************************************************");
                        subGal = 0;
                        galaxy2();
                    } else {
                        System.out.println("\n*You have lost the battle.                                                           *");
                        coins = 0;
                        System.out.println("\n*The pirates have taken all your coins.                                              *");
                        System.out.println("*Your ship has been sunk....GAME OVER                                                *");
                        System.out.println("**************************************************************************************");
                        finished = true;
                        stats();
                        System.out.println("\nWould you like to play again?");
                        playAgain();
                    }
                } else {
                    subGal = 0;
                    galaxy2();
                }
            } else if (input.contains("galaxy3") || input.contains("g3")) {
                System.out.println("\nThere is not a direct route to Galaxy 3");
            } else if (input.contains("galaxy1") || input.contains("g1")) {
                System.out.println("\nThere is not a direct route to Galaxy 1");
            } else if (input.contains("galaxy4") || input.contains("g4")) {
                System.out.println("\nThere is not a direct route to Galaxy 4");
            } else if (input.contains("galaxy6") || input.contains("g6")) {
                System.out.print("\n\u000C");
                finished = true;
                year += 1;
                System.out.println("\nLeaving Galaxy 5...entering the asteroid field");
                asteroids = rand.nextInt(10);
                if (hold == 40) {
                    if (asteroids == 0) {
                        damage = 0;
                    } else if (asteroids == 1) {
                        damage = 10;
                    } else if (asteroids == 2) {
                        damage = 15;
                    } else if (asteroids == 3) {
                        damage = 20;
                    } else if (asteroids == 4) {
                        damage = 25;
                    } else if (asteroids == 5) {
                        damage = 30;
                    } else if (asteroids == 6) {
                        damage = 35;
                    } else if (asteroids == 7) {
                        damage = 40;
                    } else if (asteroids == 8) {
                        damage = 45;
                    } else if (asteroids == 9) {
                        damage = 60;
                    }
                } else if (hold == 50) {
                    if (asteroids == 0) {
                        damage = 0;
                    } else if (asteroids == 1) {
                        damage = 15;
                    } else if (asteroids == 2) {
                        damage = 20;
                    } else if (asteroids == 3) {
                        damage = 25;
                    } else if (asteroids == 4) {
                        damage = 30;
                    } else if (asteroids == 5) {
                        damage = 35;
                    } else if (asteroids == 6) {
                        damage = 40;
                    } else if (asteroids == 7) {
                        damage = 45;
                    } else if (asteroids == 8) {
                        damage = 50;
                    } else if (asteroids == 9) {
                        damage = 55;
                    }
                } else if (hold == 60) {
                    if (asteroids == 0) {
                        damage = 0;
                    } else if (asteroids == 1) {
                        damage = 20;
                    } else if (asteroids == 2) {
                        damage = 25;
                    } else if (asteroids == 3) {
                        damage = 30;
                    } else if (asteroids == 4) {
                        damage = 35;
                    } else if (asteroids == 5) {
                        damage = 40;
                    } else if (asteroids == 6) {
                        damage = 45;
                    } else if (asteroids == 7) {
                        damage = 50;
                    } else if (asteroids == 8) {
                        damage = 55;
                    } else if (asteroids == 9) {
                        damage = 60;
                    }
                } else if (hold == 70) {
                    if (asteroids == 0) {
                        damage = 0;
                    } else if (asteroids == 1) {
                        damage = 25;
                    } else if (asteroids == 2) {
                        damage = 30;
                    } else if (asteroids == 3) {
                        damage = 35;
                    } else if (asteroids == 4) {
                        damage = 40;
                    } else if (asteroids == 5) {
                        damage = 45;
                    } else if (asteroids == 6) {
                        damage = 50;
                    } else if (asteroids == 7) {
                        damage = 55;
                    } else if (asteroids == 8) {
                        damage = 60;
                    } else if (asteroids == 9) {
                        damage = 65;
                    }
                } else if (hold > 79) {
                    if (asteroids == 0) {
                        damage = 0;
                    } else if (asteroids == 1) {
                        damage = 35;
                    } else if (asteroids == 2) {
                        damage = 40;
                    } else if (asteroids == 3) {
                        damage = 45;
                    } else if (asteroids == 4) {
                        damage = 50;
                    } else if (asteroids == 5) {
                        damage = 55;
                    } else if (asteroids == 6) {
                        damage = 60;
                    } else if (asteroids == 7) {
                        damage = 65;
                    } else if (asteroids == 8) {
                        damage = 70;
                    } else if (asteroids == 9) {
                        damage = 75;
                    }
                }
                subGal = 0;
                galaxy6();
            } else if (input.contains("sell") || input.contains("s")) {
                System.out.println("\nWhat would you like to sell?");
                finished = true;
                sell();
            } else if (input.contains("buy") || input.contains("b")) {
                System.out.println("\nWhat do you want to buy?");
                finished = true;
                buy();
            } else if (input.contains("stats") || input.contains("stat")) {
                stats();
            } else if (input.contains("prices")) {
                prices();
            } else if (input.contains("retire")) {
                System.out.println("\nYou cannot retire in deep space.  You must make it back to an inner galaxy (1, 2, or 3)");
            } else {
                String response = responder.generateResponse(input);
                System.out.println(response);
            }
        }
    }

    private static void galaxy6() {
        gal = 6;
        if (subGal == 0) {
            if (shipShape - damage < 1) {
                System.out.println("\nYour ship was struck by " + asteroids + " asteroid(s)");
                System.out.println("There were no survivors....GAME OVER");
                System.out.println("\nWould you like to play again?");
                playAgain();
            } else if (hold < 40) {
                System.out.println("\nYour ship was too small to survive the asteroid field");
                System.out.println("There were no survivors....GAME OVER");
                System.out.println("\nWould you like to play again?");
                playAgain();
            } else {
                System.out.println("WELCOME TO GALAXY 6");
                System.out.println("__________________");
                System.out.println("\nCongratulations!  Not many ships make it through the asteroid field.");
                if (asteroids == 0) {
                    System.out.println("\nIt was miraculous that you sustained no damage!");
                    System.out.println("Your ship is still in " + shipShape + "% condition.");
                } else {
                    System.out.println("\nYour ship was struck by " + asteroids + " asteroid(s) and sustained damage of " + damage + ".");
                    shipShape -= damage;
                }
            }
        }
        System.out.println("\nYou may travel to galaxy 4, galaxy 5, or galaxy 7.  You may also check the 'map'.");
        System.out.println("\nWhat would you like to do?");
        boolean finished = false;
        while (!finished) {
            HashSet input = reader.getInput();
            if (input.contains("bye")) {
                finished = true;
                System.out.println("\nYou have been dishonorably discharged from your duties----GAME OVER");
            } else if (input.contains("map")) {
                map();
            } else if (input.contains("galaxy2") || input.contains("g2")) {
                System.out.println("\nThere is not a direct route to Galaxy 2");
            } else if (input.contains("galaxy1") || input.contains("g1")) {
                System.out.println("\nThere is not a direct route to Galaxy 1");
            } else if (input.contains("galaxy3") || input.contains("g3")) {
                System.out.println("\nThere is not a direct route to Galaxy 2");
            } else if (input.contains("galaxy7") || input.contains("g7")) {
                finished = true;
                year += 1;
                int bandits = rand.nextInt(5);
                if (bandits == 0) {
                    if (shipShape - 20 > 0) {
                        System.out.println("\nAs you depart from the Galaxy 6 port you discover that the ship has been vandalized!");
                        System.out.println("Your ship conditioned has been reduced by 20 percent!");
                        shipShape -= 20;
                        subGal = 0;
                        galaxy7();
                    } else {
                        System.out.println("\nAs you depart from the Galaxy 6 port you discover that the ship has been vandalized!");
                        System.out.println("The damage was critical!");
                        shipShape -= 20;
                        System.out.println("GAME OVER");
                    }
                } else {
                    subGal = 0;
                    galaxy7();
                }
            } else if (input.contains("galaxy4") || input.contains("g4")) {
                System.out.print("\n\u000C");
                finished = true;
                year += 1;
                System.out.println("\nLeaving Galaxy 6...entering the asteroid field");
                asteroids = rand.nextInt(10);
                if (hold == 40) {
                    if (asteroids == 0) {
                        damage = 0;
                    } else if (asteroids == 1) {
                        damage = 10;
                    } else if (asteroids == 2) {
                        damage = 15;
                    } else if (asteroids == 3) {
                        damage = 20;
                    } else if (asteroids == 4) {
                        damage = 25;
                    } else if (asteroids == 5) {
                        damage = 30;
                    } else if (asteroids == 6) {
                        damage = 35;
                    } else if (asteroids == 7) {
                        damage = 40;
                    } else if (asteroids == 8) {
                        damage = 45;
                    } else if (asteroids == 9) {
                        damage = 60;
                    }
                } else if (hold == 50) {
                    if (asteroids == 0) {
                        damage = 0;
                    } else if (asteroids == 1) {
                        damage = 15;
                    } else if (asteroids == 2) {
                        damage = 20;
                    } else if (asteroids == 3) {
                        damage = 25;
                    } else if (asteroids == 4) {
                        damage = 30;
                    } else if (asteroids == 5) {
                        damage = 35;
                    } else if (asteroids == 6) {
                        damage = 40;
                    } else if (asteroids == 7) {
                        damage = 45;
                    } else if (asteroids == 8) {
                        damage = 50;
                    } else if (asteroids == 9) {
                        damage = 55;
                    }
                } else if (hold == 60) {
                    if (asteroids == 0) {
                        damage = 0;
                    } else if (asteroids == 1) {
                        damage = 20;
                    } else if (asteroids == 2) {
                        damage = 25;
                    } else if (asteroids == 3) {
                        damage = 30;
                    } else if (asteroids == 4) {
                        damage = 35;
                    } else if (asteroids == 5) {
                        damage = 40;
                    } else if (asteroids == 6) {
                        damage = 45;
                    } else if (asteroids == 7) {
                        damage = 50;
                    } else if (asteroids == 8) {
                        damage = 55;
                    } else if (asteroids == 9) {
                        damage = 60;
                    }
                } else if (hold == 70) {
                    if (asteroids == 0) {
                        damage = 0;
                    } else if (asteroids == 1) {
                        damage = 25;
                    } else if (asteroids == 2) {
                        damage = 30;
                    } else if (asteroids == 3) {
                        damage = 35;
                    } else if (asteroids == 4) {
                        damage = 40;
                    } else if (asteroids == 5) {
                        damage = 45;
                    } else if (asteroids == 6) {
                        damage = 50;
                    } else if (asteroids == 7) {
                        damage = 55;
                    } else if (asteroids == 8) {
                        damage = 60;
                    } else if (asteroids == 9) {
                        damage = 65;
                    }
                } else if (hold > 79) {
                    if (asteroids == 0) {
                        damage = 0;
                    } else if (asteroids == 1) {
                        damage = 35;
                    } else if (asteroids == 2) {
                        damage = 40;
                    } else if (asteroids == 3) {
                        damage = 45;
                    } else if (asteroids == 4) {
                        damage = 50;
                    } else if (asteroids == 5) {
                        damage = 55;
                    } else if (asteroids == 6) {
                        damage = 60;
                    } else if (asteroids == 7) {
                        damage = 65;
                    } else if (asteroids == 8) {
                        damage = 70;
                    } else if (asteroids == 9) {
                        damage = 75;
                    }
                }
                if (shipShape - damage < 1) {
                    System.out.println("\nYour ship was struck by " + asteroids + " asteroid(s)");
                    System.out.println("There were no survivors....GAME OVER");
                    System.out.println("\nWould you like to play again?");
                    playAgain();
                } else if (hold < 40) {
                    System.out.println("\nYour ship was too small to survive the asteroid field");
                    System.out.println("There were no survivors....GAME OVER");
                    System.out.println("\nWould you like to play again?");
                    playAgain();
                } else {
                    System.out.println("\nCongratulations!  Not many ships make it through the asteroid field.");
                    if (asteroids == 0) {
                        System.out.println("\nIt was miraculous that you sustained no damage!");
                        System.out.println("Your ship is still in " + shipShape + "% condition.");
                    } else {
                        System.out.println("\nYour ship was struck by " + asteroids + " asteroid(s) and sustained damage of " + damage + ".");
                        shipShape -= damage;
                    }
                    finished = true;
                    subGal = 0;
                    galaxy4();
                }
            } else if (input.contains("galaxy5") || input.contains("g5")) {
                System.out.print("\n\u000C");
                finished = true;
                year += 1;
                System.out.println("\nLeaving Galaxy 6...entering the asteroid field");
                asteroids = rand.nextInt(10);
                if (hold == 40) {
                    if (asteroids == 0) {
                        damage = 0;
                    } else if (asteroids == 1) {
                        damage = 10;
                    } else if (asteroids == 2) {
                        damage = 15;
                    } else if (asteroids == 3) {
                        damage = 20;
                    } else if (asteroids == 4) {
                        damage = 25;
                    } else if (asteroids == 5) {
                        damage = 30;
                    } else if (asteroids == 6) {
                        damage = 35;
                    } else if (asteroids == 7) {
                        damage = 40;
                    } else if (asteroids == 8) {
                        damage = 45;
                    } else if (asteroids == 9) {
                        damage = 60;
                    }
                } else if (hold == 50) {
                    if (asteroids == 0) {
                        damage = 0;
                    } else if (asteroids == 1) {
                        damage = 15;
                    } else if (asteroids == 2) {
                        damage = 20;
                    } else if (asteroids == 3) {
                        damage = 25;
                    } else if (asteroids == 4) {
                        damage = 30;
                    } else if (asteroids == 5) {
                        damage = 35;
                    } else if (asteroids == 6) {
                        damage = 40;
                    } else if (asteroids == 7) {
                        damage = 45;
                    } else if (asteroids == 8) {
                        damage = 50;
                    } else if (asteroids == 9) {
                        damage = 55;
                    }
                } else if (hold == 60) {
                    if (asteroids == 0) {
                        damage = 0;
                    } else if (asteroids == 1) {
                        damage = 20;
                    } else if (asteroids == 2) {
                        damage = 25;
                    } else if (asteroids == 3) {
                        damage = 30;
                    } else if (asteroids == 4) {
                        damage = 35;
                    } else if (asteroids == 5) {
                        damage = 40;
                    } else if (asteroids == 6) {
                        damage = 45;
                    } else if (asteroids == 7) {
                        damage = 50;
                    } else if (asteroids == 8) {
                        damage = 55;
                    } else if (asteroids == 9) {
                        damage = 60;
                    }
                } else if (hold == 70) {
                    if (asteroids == 0) {
                        damage = 0;
                    } else if (asteroids == 1) {
                        damage = 25;
                    } else if (asteroids == 2) {
                        damage = 30;
                    } else if (asteroids == 3) {
                        damage = 35;
                    } else if (asteroids == 4) {
                        damage = 40;
                    } else if (asteroids == 5) {
                        damage = 45;
                    } else if (asteroids == 6) {
                        damage = 50;
                    } else if (asteroids == 7) {
                        damage = 55;
                    } else if (asteroids == 8) {
                        damage = 60;
                    } else if (asteroids == 9) {
                        damage = 65;
                    }
                } else if (hold > 79) {
                    if (asteroids == 0) {
                        damage = 0;
                    } else if (asteroids == 1) {
                        damage = 35;
                    } else if (asteroids == 2) {
                        damage = 40;
                    } else if (asteroids == 3) {
                        damage = 45;
                    } else if (asteroids == 4) {
                        damage = 50;
                    } else if (asteroids == 5) {
                        damage = 55;
                    } else if (asteroids == 6) {
                        damage = 60;
                    } else if (asteroids == 7) {
                        damage = 65;
                    } else if (asteroids == 8) {
                        damage = 70;
                    } else if (asteroids == 9) {
                        damage = 75;
                    }
                }
                if (shipShape - damage < 1) {
                    System.out.println("\nYour ship was struck by " + asteroids + " asteroid(s)");
                    System.out.println("There were no survivors....GAME OVER");
                    System.out.println("\nWould you like to play again?");
                    playAgain();
                } else if (hold < 40) {
                    System.out.println("\nYour ship was too small to survive the asteroid field");
                    System.out.println("There were no survivors....GAME OVER");
                    System.out.println("\nWould you like to play again?");
                    finished = true;
                    playAgain();
                } else {
                    System.out.println("\nCongratulations!  Not many ships make it through the asteroid field.");
                    if (asteroids == 0) {
                        System.out.println("\nIt was miraculous that you sustained no damage!");
                        System.out.println("Your ship is still in " + shipShape + "% condition.");
                    } else {
                        System.out.println("\nYour ship was struck by " + asteroids + " asteroid(s) and sustained damage of " + damage + ".");
                        shipShape -= damage;
                    }
                    finished = true;
                    subGal = 0;
                    galaxy5();
                }
            } else if (input.contains("stats") || input.contains("stat")) {
                stats();
            } else if (input.contains("retire")) {
                System.out.println("\nYou cannot retire in deep space.  You must make it back to an inner galaxy (1, 2, or 3)");
            }
        }
    }

    private static void galaxy7() {
        if (subGal == 0) {
            System.out.println("\nWELCOME TO GALAXY 7");
            System.out.println("_________________");
            int upgrade = rand.nextInt(4);
            if (upgrade == 0) {
                if (bankTotal + coins > 300000) {
                    System.out.println("\nWould you like to upgrade your ship for " + ((bankTotal + coins) / 3 + 1) + " coins?");
                    upgradeShip();
                } else {
                    System.out.println("\nWould you like to upgrade your ship for " + (coins / 2 + 1) + " coins?");
                    upgradeShip();
                }
            } else if (upgrade > 0) {
                System.out.println("\nWould you like to buy a new gun for " + (coins / 4 + 1) + " coins?");
                buyGun();
            }
            int index = rand.nextInt(5) + 5;
            int index2 = rand.nextInt(5) + 5;
            int index3 = rand.nextInt(5) + 5;
            uraniumPrice = uraniumBase * index;
            producePrice = produceBase * index2;
            waterPrice = waterBase * index3;
            System.out.println("\nEntering Galaxy 7 market:");
            System.out.println("_________________");
            System.out.println("Current Prices:");
            System.out.println("Uranium: " + uraniumPrice);
            System.out.println("Produce: " + producePrice);
            System.out.println("Water: " + waterPrice);
        }
        System.out.println("\nYou may travel to galaxy 6");
        System.out.println("\nWhat would you like to do?");
        boolean finished = false;
        while (!finished) {
            HashSet input = reader.getInput();
            if (input.contains("bye")) {
                finished = true;
                System.out.println("\nYou have been dishonorably discharged from your duties----GAME OVER");
            } else if (input.contains("galaxy2") || input.contains("g2")) {
                System.out.println("\nThere is not a direct route to Galaxy 2");
            } else if (input.contains("galaxy3") || input.contains("g3")) {
                System.out.println("\nThere is not a direct route to Galaxy 3");
            } else if (input.contains("galaxy1") || input.contains("g1")) {
                System.out.println("\nThere is not a direct route to Galaxy 1");
            } else if (input.contains("galaxy5") || input.contains("g5")) {
                System.out.println("\nThere is not a direct route to Galaxy 5");
            } else if (input.contains("galaxy4") || input.contains("g4")) {
                System.out.println("\nThere is not a direct route to Galaxy 4");
            } else if (input.contains("galaxy6") || input.contains("g6")) {
                finished = true;
                subGal = 1;
                galaxy6();
            } else if (input.contains("sell") || input.contains("s")) {
                System.out.println("\nWhat would you like to sell?");
                finished = true;
                sell();
            } else if (input.contains("buy") || input.contains("b")) {
                System.out.println("\nWhat do you want to buy?");
                finished = true;
                buy();
            } else if (input.contains("stats") || input.contains("stat")) {
                stats();
            } else if (input.contains("prices")) {
                prices();
            } else if (input.contains("retire")) {
                System.out.println("\nYou cannot retire in deep space.  You must make it back to an inner galaxy (1, 2, or 3)");

            } else {
                String response = responder.generateResponse(input);
                System.out.println(response);
            }
        }
    }

    //Generic galaxy methods:
    private static void upgradeShip() {
        boolean finished = false;
        while (!finished) {
            HashSet input = reader.getInput();
            if (input.contains("bye")) {
                finished = true;
                System.out.println("\nYou have been dishonorably discharged from your duties----GAME OVER");
            } else if (input.contains("yes")) {
                if (coins == 0) {
                    System.out.println("\nSorry, you have no coins");
                    finished = true;
                } else if (coins + bankTotal > 300000) {
                    if (coins < (bankTotal + coins) / 3 + 1) {
                        System.out.println("\nSorry, you don't have enough coins");
                        finished = true;
                    } else {
                        hold += 10;
                        shipShape = 100;
                        coins -= (bankTotal + coins) / 3 + 1;
                        System.out.println("\nUpgrade complete");
                        System.out.println("Your cargo size is now " + hold + ".");
                        finished = true;
                    }
                } else {
                    hold += 10;
                    shipShape = 100;
                    coins -= (coins / 2) + 1;
                    System.out.println("\nUpgrade complete");
                    System.out.println("Your cargo size is now " + hold + ".");
                    finished = true;
                }
            } else {
                finished = true;
            }
        }
    }

    private static void buyGun() {
        boolean finished = false;
        while (!finished) {
            HashSet input = reader.getInput();
            if (input.contains("yes")) {
                if (coins < 1) {
                    System.out.println("\nSorry, you don't have enough coins");
                    finished = true;
                } else if (uranium + produce + water + guns > hold - 1) {
                    System.out.println("\nSorry, you don't have enough free cargo space.");
                    finished = true;
                } else {
                    guns += 1;
                    coins -= (coins / 4) + 1;
                    System.out.println("\nUpgrade complete");
                    System.out.println("You now have " + guns + " guns.");
                    finished = true;
                }
            } else {
                finished = true;
            }
        }
    }

    private static void stats() {
        System.out.println("- - - - - - - - - -");
        System.out.println("     Year: " + year);
        System.out.println("You have travelled the galaxies for " + (year - 2600) + " years.");
        System.out.println("\nShip Condition: " + shipShape);
        System.out.println("Guns: " + guns);
        System.out.println("Hold: " + hold);
        System.out.println("\nWarehouse Uranium: " + wareHouseUranium);
        System.out.println("Warehouse Produce: " + wareHouseProduce);
        System.out.println("Warehouse Water: " + wareHouseWater);
        System.out.println("\nGold Coins: " + coins);
        System.out.println("Bank Account: " + bankTotal);
        System.out.println("\nUranium: " + uranium);
        System.out.println("Produce: " + produce);
        System.out.println("Water: " + water);
        int totalWorth = bankTotal + coins + guns * 1000 + hold * 1000 + uranium * 1000 + produce * 400 + water * 100 + wareHouseUranium * 1000 + wareHouseProduce * 400 + wareHouseWater * 100;
        System.out.println("- - - - - - - - - -");
        System.out.println("Total Worth: " + (bankTotal + coins + guns * 1000 + hold * 1000 + uranium * 1000 + produce * 400 + water * 100 + wareHouseUranium * 1000 + wareHouseProduce * 400 + wareHouseWater * 100));
        System.out.println("- - - - - - - - - -");
        if (totalWorth < 50000) {
            rank = "Airman (level 1)";
            level = 1;
        } else if (totalWorth < 100000) {
            rank = "Senior Airman (level 2)";
            level = 2;
        } else if (totalWorth < 150000) {
            rank = "Master Sergeant (level 3)";
            level = 3;
        } else if (totalWorth < 200000) {
            rank = "Captain (level 4)";
            level = 4;
        } else if (totalWorth < 300000) {
            rank = "Major (level 5)";
            level = 5;
        } else if (totalWorth < 400000) {
            rank = "Lieutenant Colonel (level 6)";
            level = 6;
        } else if (totalWorth < 550000) {
            rank = "Colonel (level 7)";
            level = 7;
        } else if (totalWorth < 700000) {
            rank = "Brigadier General * (level 8)";
            level = 8;
        } else if (totalWorth < 850000) {
            rank = "Major General ** (level 9)";
            level = 9;
        } else if (totalWorth < 1499999) {
            rank = "Lieutenant General *** (level 10)";
            level = 10;
        } else {
            rank = "GENERAL **** (level 11)";
            level = 11;
        }
        System.out.println("RANK: " + rank);
    }

    private static void playAgain() {
        boolean finished = false;
        while (!finished) {
            HashSet input = reader.getInput();
            if (input.contains("bye")) {
                finished = true;
                System.out.println("\nYou have been dishonorably discharged from your duties----GAME OVER");
            } else if (input.contains("no")) {
                System.out.println("\nGoodbye");
                System.out.print("\u000C");
                finished = true;
            } else if (input.contains("yes")) {
                System.out.print("\u000C");
                finished = true;
                String[] args = new String[0];
                main(args);
            } else {
                finished = true;
                String response = responder.generateResponse(input);
                System.out.println(response);
            }
        }
    }

    private static void prices() {
        System.out.println("\nCurrent Prices:");
        System.out.println("Uranium: " + uraniumPrice);
        System.out.println("Produce: " + producePrice);
        System.out.println("Water: " + waterPrice);
    }

    private static void map() {
        System.out.println("\n          (1)          ");
        System.out.println("     (2)       (3)     ");
        System.out.println("(5)  ..............  (4)");
        System.out.println("   ....   (6)  ....    ");
        System.out.println();
        System.out.println("          (7)          ");
    }

    //Bank methods:
    private static void bank() {
        int robbed = rand.nextInt(12);
        if (robbed == 0) {
            if (coins != 0) {
                System.out.println("\n**************************************************************************************");
                System.out.println("*You have been beaten and robbed!");
                System.out.println("*The criminals have taken " + ((coins * 3) / 4) + " coins.");
                System.out.println("**************************************************************************************");
                coins -= (coins * 3) / 4;
            }
        }
        System.out.println("\nWould you like to 'deposit' or 'withdraw' coins?");
        boolean finished = false;
        while (!finished) {
            HashSet input = reader.getInput();

            if (input.contains("bye")) {
                finished = true;
                System.out.println("\nYou have been dishonorably discharged from your duties----GAME OVER");
            } else if (input.contains("deposit")) {
                if (coins < 1) {
                    System.out.println("\nYou do not have any coins to deposit.");
                    finished = true;
                    subGal = 1;
                    galaxy1();
                } else {
                    finished = true;
                    System.out.println("\nHow much would you like to deposit?");
                    depositCoins();
                }
            } else if (input.contains("withdraw")) {
                if (bankTotal < 1) {
                    System.out.println("\nYou do not have any coins to withdraw.");
                    finished = true;
                    subGal = 1;
                    galaxy1();
                } else {
                    finished = true;
                    System.out.println("\nHow much would you like to withdraw?");
                    withdrawCoins();
                }
            } else if (input.contains("leave")) {
                finished = true;
                System.out.println("\nLeaving the bank");
                subGal = 1;
                galaxy1();
            } else {
                String response = responder.generateResponse(input);
                System.out.println(response);
            }
        }
    }

    private static void depositCoins() {
        Scanner scanner = new Scanner(System.in);
        boolean continueLoop = true;
        do {
            try {
                int index = scanner.nextInt();
                if (index > coins) {
                    System.out.println("\nYou do not have that many coins.");
                    subGal = 1;
                    galaxy1();
                } else if (index < coins + 1) {
                    System.out.println("\nYour coins have been deposited");
                    coins -= index;
                    bankTotal += index;
                    subGal = 1;
                    galaxy1();
                } else {
                    subGal = 1;
                    galaxy1();
                }
                continueLoop = false;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("\nPlease enter a numeric value");
                scanner.nextLine();
            }
        } while (continueLoop);
    }

    private static void withdrawCoins() {
        Scanner scanner = new Scanner(System.in);
        boolean continueLoop = true;
        do {
            try {
                int index = scanner.nextInt();
                if (index > bankTotal) {
                    System.out.println("\nYou do not have that many coins.");
                    subGal = 1;
                    galaxy1();
                } else if (index < bankTotal + 1) {
                    System.out.println("Your coins are being transferred to the ship.");
                    coins += index;
                    bankTotal -= index;
                    int robbed = rand.nextInt(10);
                    if (robbed == 0) {
                        Toolkit.getDefaultToolkit().beep();
                        System.out.println("\n********");
                        System.out.println("You have been robbed and beaten!");
                        System.out.println("You have lost " + ((coins * 3) / 4) + "!");
                        coins -= (coins * 3) / 4;
                    }
                    subGal = 1;
                    galaxy1();
                } else {
                    subGal = 1;
                    galaxy1();
                }
                continueLoop = false;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("\nPlease enter a numeric value");
                scanner.nextLine();
            }
        } while (continueLoop);
    }

    //Warehouse methods:
    private static void wareHouse() {
        if (wareHouseTotal == 0) {
            System.out.println("\nYou have nothing in your warehouse!");
            wareHouseAdd();
        } else {
            System.out.println("\nWould you like to 'add' or 'remove' units?");
            boolean finished = false;
            while (!finished) {
                HashSet input = reader.getInput();

                if (input.contains("bye")) {
                    finished = true;
                    System.out.println("\nYou have been dishonorably discharged from your duties----GAME OVER");
                } else if (input.contains("add")) {
                    finished = true;
                    wareHouseAdd();

                } else if (input.contains("remove")) {
                    finished = true;
                    wareHouseRemove();
                } else if (input.contains("leave")) {
                    finished = true;
                    System.out.println("\nLeaving the warehouse");
                    subGal = 1;
                    galaxy1();
                } else {
                    String response = responder.generateResponse(input);
                    System.out.println(response);
                }
            }
        }
    }

    private static void wareHouseAdd() {
        boolean finished = false;
        System.out.println("\nWhat would you like to add?");
        while (!finished) {
            HashSet input = reader.getInput();
            if (input.contains("bye")) {
                finished = true;
                System.out.println("\nYou have been dishonorably discharged from your duties----GAME OVER");
            } else if (input.contains("uranium") || input.contains("u")) {
                if (uranium < 1) {
                    System.out.println("\nYou do not have any uranium.");
                    finished = true;
                    subGal = 1;
                    galaxy1();
                } else {
                    finished = true;
                    System.out.println("\nHow many units?");
                    addUranium();
                }
            } else if (input.contains("produce") || input.contains("p")) {
                if (produce < 1) {
                    System.out.println("\nYou do not have any produce.");
                    finished = true;
                    subGal = 1;
                    galaxy1();
                } else {
                    finished = true;
                    System.out.println("\nHow many units?");
                    addProduce();
                }
            } else if (input.contains("water") || input.contains("w")) {
                if (water < 1) {
                    System.out.println("\nYou do not have any water.");
                    finished = true;
                    subGal = 1;
                    galaxy1();
                } else {
                    finished = true;
                    System.out.println("\nHow many units?");
                    addWater();
                }
            } else if (input.contains("leave")) {
                finished = true;
                System.out.println("\nLeaving the warehouse");
                subGal = 1;
                galaxy1();
            } else {
                String response = responder.generateResponse(input);
                System.out.println(response);
            }
        }
    }

    private static void addUranium() {
        Scanner scanner = new Scanner(System.in);
        boolean continueLoop = true;
        do {
            try {
                int units = scanner.nextInt();
                if (units > uranium) {
                    System.out.println("\nYou don't have that much uranium!");
                    subGal = 1;
                    galaxy1();
                } else if (units < uranium + 1) {
                    System.out.println("\nUranium has been added to the warehouse");
                    uranium -= units;
                    wareHouseUranium += units;
                    wareHouseTotal += units;
                    subGal = 1;
                    galaxy1();
                } else {
                    subGal = 1;
                    galaxy1();
                }
                continueLoop = false;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("\nPlease enter a numeric value");
                scanner.nextLine();
            }
        } while (continueLoop);
    }

    private static void addProduce() {
        Scanner scanner = new Scanner(System.in);
        boolean continueLoop = true;
        do {
            try {
                int units = scanner.nextInt();
                if (units > produce) {
                    System.out.println("\nYou don't have that much produce!");
                    subGal = 1;
                    galaxy1();
                } else if (units < produce + 1) {
                    System.out.println("\nProduce has been added to the warehouse");
                    produce -= units;
                    wareHouseProduce += units;
                    wareHouseTotal += units;
                    subGal = 1;
                    galaxy1();
                } else {
                    subGal = 1;
                    galaxy1();
                }
                continueLoop = false;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("\nPlease enter a numeric value");
                scanner.nextLine();
            }
        } while (continueLoop);
    }

    private static void addWater() {
        Scanner scanner = new Scanner(System.in);
        boolean continueLoop = true;
        do {
            try {
                int units = scanner.nextInt();
                if (units > water) {
                    System.out.println("\nYou don't have that much water!");
                    subGal = 1;
                    galaxy1();
                } else if (units < water + 1) {
                    System.out.println("\nWater has been added to the warehouse");
                    water -= units;
                    wareHouseWater += units;
                    wareHouseTotal += units;
                    subGal = 1;
                    galaxy1();
                } else {
                    subGal = 1;
                    galaxy1();
                }
                continueLoop = false;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("\nPlease enter a numeric value");
                scanner.nextLine();
            }
        } while (continueLoop);
    }

    private static void wareHouseRemove() {
        boolean finished = false;
        System.out.println("\nWhat would you like to move to the ship?");
        while (!finished) {
            HashSet input = reader.getInput();
            if (input.contains("bye")) {
                finished = true;
                System.out.println("\nYou have been dishonorably discharged from your duties----GAME OVER");
            } else if (input.contains("uranium") || input.contains("u")) {
                if (wareHouseUranium < 1) {
                    System.out.println("\nYou do not have any uranium in the warehouse.");
                    finished = true;
                    subGal = 1;
                    galaxy1();
                } else {
                    finished = true;
                    System.out.println("\nHow many units?");
                    removeUranium();
                }
            } else if (input.contains("produce") || input.contains("p")) {
                if (wareHouseProduce < 1) {
                    System.out.println("\nYou do not have any produce.");
                    finished = true;
                    subGal = 1;
                    galaxy1();
                } else {
                    finished = true;
                    System.out.println("\nHow many units?");
                    removeProduce();
                }
            } else if (input.contains("water") || input.contains("w")) {
                if (wareHouseWater < 1) {
                    System.out.println("\nYou do not have any water.");
                    finished = true;
                    subGal = 1;
                    galaxy1();
                } else {
                    finished = true;
                    System.out.println("\nHow many units?");
                    removeWater();
                }
            } else if (input.contains("leave")) {
                finished = true;
                System.out.println("\nLeaving the warehouse");
                subGal = 1;
                galaxy1();
            } else {
                String response = responder.generateResponse(input);
                System.out.println(response);
            }
        }
    }

    private static void removeUranium() {
        Scanner scanner = new Scanner(System.in);
        boolean continueLoop = true;
        do {
            try {
                int units = scanner.nextInt();
                if (units > wareHouseUranium) {
                    System.out.println("\nYou don't have that much uranium!");
                    subGal = 1;
                    galaxy1();
                } else if (units < wareHouseUranium + 1) {
                    System.out.println("\nUranium has been transferred to the ship");
                    uranium += units;
                    wareHouseUranium -= units;
                    wareHouseTotal -= units;
                    subGal = 1;
                    galaxy1();
                } else {
                    subGal = 1;
                    galaxy1();
                }
                continueLoop = false;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("\nPlease enter a numeric value");
                scanner.nextLine();
            }
        } while (continueLoop);
    }

    private static void removeProduce() {
        Scanner scanner = new Scanner(System.in);
        boolean continueLoop = true;
        do {
            try {
                int units = scanner.nextInt();
                if (units > wareHouseProduce) {
                    System.out.println("\nYou don't have that much produce!");
                    subGal = 1;
                    galaxy1();
                } else if (units < wareHouseProduce + 1) {
                    System.out.println("\nProduce has been transferred to the ship");
                    produce += units;
                    wareHouseProduce -= units;
                    wareHouseTotal -= units;
                    subGal = 1;
                    galaxy1();
                } else {
                    subGal = 1;
                    galaxy1();
                }
                continueLoop = false;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("\nPlease enter a numeric value");
                scanner.nextLine();
            }
        } while (continueLoop);
    }

    private static void removeWater() {
        Scanner scanner = new Scanner(System.in);
        boolean continueLoop = true;
        do {
            try {
                int units = scanner.nextInt();
                if (units > wareHouseWater) {
                    System.out.println("\nYou don't have that much water!");
                    subGal = 1;
                    galaxy1();
                } else if (units < wareHouseProduce + 1) {
                    System.out.println("\nWater has been transferred to the ship");
                    water += units;
                    wareHouseWater -= units;
                    wareHouseTotal -= units;
                    subGal = 1;
                    galaxy1();
                } else {
                    subGal = 1;
                    galaxy1();
                }
                continueLoop = false;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("\nPlease enter a numeric value");
                scanner.nextLine();
            }
        } while (continueLoop);
    }

    //Buying methods (all galaxies):
    private static void buy() {
        boolean finished = false;
        while (!finished) {
            HashSet input = reader.getInput();
            if (input.contains("bye")) {
                finished = true;
                System.out.println("\nYou have been dishonorably discharged from your duties----GAME OVER");
            } else if (input.contains("uranium") || input.contains("u")) {
                if (coins < uraniumPrice) {
                    System.out.println("\nYou do not have enough gold");
                    finished = true;
                    subGal = 1;
                    if (gal == 1) {
                        galaxy1();
                    } else if (gal == 2) {
                        galaxy2();
                    } else if (gal == 3) {
                        galaxy3();
                    } else if (gal == 5) {
                        galaxy5();
                    } else if (gal == 7) {
                        galaxy7();
                    } else {
                        galaxy1();
                    }
                } else {
                    finished = true;
                    System.out.println("\nHow many units?");
                    buyUranium();
                }
            } else if (input.contains("produce") || input.contains("p")) {
                if (coins < producePrice) {
                    System.out.println("\nYou do not have enough gold");
                    finished = true;
                    subGal = 1;
                    if (gal == 1) {
                        galaxy1();
                    } else if (gal == 2) {
                        galaxy2();
                    } else if (gal == 3) {
                        galaxy3();
                    } else if (gal == 5) {
                        galaxy5();
                    } else if (gal == 7) {
                        galaxy7();
                    } else {
                        galaxy1();
                    }
                } else {
                    finished = true;
                    System.out.println("\nHow many units?");
                    buyProduce();
                }
            } else if (input.contains("water") || input.contains("w")) {
                if (coins < waterPrice) {
                    System.out.println("\nYou do not have enough gold");
                    finished = true;
                    subGal = 1;
                    if (gal == 1) {
                        galaxy1();
                    } else if (gal == 2) {
                        galaxy2();
                    } else if (gal == 3) {
                        galaxy3();
                    } else if (gal == 5) {
                        galaxy5();
                    } else if (gal == 7) {
                        galaxy7();
                    } else {
                        galaxy1();
                    }
                } else {
                    finished = true;
                    System.out.println("\nHow many units?");
                    buyWater();
                }
            } else if (input.contains("prices")) {
                prices();
            } else if (input.contains("nothing")) {
                subGal = 1;
                if (gal == 1) {
                    galaxy1();
                } else if (gal == 2) {
                    galaxy2();
                } else if (gal == 3) {
                    galaxy3();
                } else if (gal == 5) {
                    galaxy5();
                } else if (gal == 7) {
                    galaxy7();
                } else {
                    galaxy1();
                }
            } else {
                System.out.println("\nWhat would you like to buy?");
            }
        }
    }

    private static void buyUranium() {
        Scanner scanner = new Scanner(System.in);
        boolean continueLoop = true;
        do {
            try {
                int units = scanner.nextInt();
                if (coins < units * uraniumPrice) {
                    System.out.println("\nYou need more gold!");
                    subGal = 1;
                    if (gal == 1) {
                        galaxy1();
                    } else if (gal == 2) {
                        galaxy2();
                    } else if (gal == 3) {
                        galaxy3();
                    } else if (gal == 5) {
                        galaxy5();
                    } else if (gal == 7) {
                        galaxy7();
                    } else {
                        galaxy1();
                    }
                } else {
                    if (units + uranium + produce + water + guns < hold + 1) {
                        System.out.println("\nYou have purchased " + units + " unit(s) for " + units * uraniumPrice);
                        coins -= units * uraniumPrice;
                        uranium += units;
                        subGal = 1;
                        if (gal == 1) {
                            galaxy1();
                        } else if (gal == 2) {
                            galaxy2();
                        } else if (gal == 3) {
                            galaxy3();
                        } else if (gal == 5) {
                            galaxy5();
                        } else if (gal == 7) {
                            galaxy7();
                        } else {
                            galaxy1();
                        }
                    } else {
                        System.out.println("\nThe hold isn't large enough!");
                        subGal = 1;
                        if (gal == 1) {
                            galaxy1();
                        } else if (gal == 2) {
                            galaxy2();
                        } else if (gal == 3) {
                            galaxy3();
                        } else if (gal == 5) {
                            galaxy5();
                        } else if (gal == 7) {
                            galaxy7();
                        } else {
                            galaxy1();
                        }
                    }
                }
                continueLoop = false;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("\nPlease enter a numeric value");
                scanner.nextLine();
            }
        } while (continueLoop);
    }

    private static void buyProduce() {
        Scanner scanner = new Scanner(System.in);
        boolean continueLoop = true;
        do {
            try {
                int units = scanner.nextInt();
                if (coins < units * producePrice) {
                    System.out.println("\nYou need more gold!");
                    subGal = 1;
                    if (gal == 1) {
                        galaxy1();
                    } else if (gal == 2) {
                        galaxy2();
                    } else if (gal == 3) {
                        galaxy3();
                    } else if (gal == 5) {
                        galaxy5();
                    } else if (gal == 7) {
                        galaxy7();
                    } else {
                        galaxy1();
                    }
                } else {
                    if (units + uranium + produce + water + guns < hold + 1) {
                        System.out.println("\nYou have purchased " + units + " unit(s) for " + units * producePrice);
                        coins -= units * producePrice;
                        produce += units;
                        subGal = 1;
                        if (gal == 1) {
                            galaxy1();
                        } else if (gal == 2) {
                            galaxy2();
                        } else if (gal == 3) {
                            galaxy3();
                        } else if (gal == 5) {
                            galaxy5();
                        } else if (gal == 7) {
                            galaxy7();
                        } else {
                            galaxy1();
                        }
                    } else {
                        System.out.println("\nThe hold isn't large enough!");
                        subGal = 1;
                        if (gal == 1) {
                            galaxy1();
                        } else if (gal == 2) {
                            galaxy2();
                        } else if (gal == 3) {
                            galaxy3();
                        } else if (gal == 5) {
                            galaxy5();
                        } else if (gal == 7) {
                            galaxy7();
                        } else {
                            galaxy1();
                        }
                    }
                }
                continueLoop = false;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("\nPlease enter a numeric value");
                scanner.nextLine();
            }
        } while (continueLoop);
    }

    private static void buyWater() {
        Scanner scanner = new Scanner(System.in);
        boolean continueLoop = true;
        do {
            try {
                int units = scanner.nextInt();
                if (coins < units * waterPrice) {
                    System.out.println("\nYou need more gold!");
                    subGal = 1;
                    if (gal == 1) {
                        galaxy1();
                    } else if (gal == 2) {
                        galaxy2();
                    } else if (gal == 3) {
                        galaxy3();
                    } else if (gal == 5) {
                        galaxy5();
                    } else if (gal == 7) {
                        galaxy7();
                    } else {
                        galaxy1();
                    }
                } else {
                    if (units + uranium + produce + water + guns < hold + 1) {
                        System.out.println("\nYou have purchased " + units + " unit(s) for " + units * waterPrice);
                        coins -= units * waterPrice;
                        water += units;
                        subGal = 1;
                        if (gal == 1) {
                            galaxy1();
                        } else if (gal == 2) {
                            galaxy2();
                        } else if (gal == 3) {
                            galaxy3();
                        } else if (gal == 5) {
                            galaxy5();
                        } else if (gal == 7) {
                            galaxy7();
                        } else {
                            galaxy1();
                        }
                    } else {
                        System.out.println("\nThe hold isn't large enough!");
                        subGal = 1;
                        if (gal == 1) {
                            galaxy1();
                        } else if (gal == 2) {
                            galaxy2();
                        } else if (gal == 3) {
                            galaxy3();
                        } else if (gal == 5) {
                            galaxy5();
                        } else if (gal == 7) {
                            galaxy7();
                        } else {
                            galaxy1();
                        }
                    }
                }
                continueLoop = false;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("\nPlease enter a numeric value");
                scanner.nextLine();
            }
        } while (continueLoop);
    }

    //Selling methods (all galaxies):
    private static void sell() {
        boolean finished = false;
        while (!finished) {
            HashSet input = reader.getInput();
            if (input.contains("bye")) {
                finished = true;
                System.out.println("\nYou have been dishonorably discharged from your duties----GAME OVER");
            } else if (input.contains("uranium") || input.contains("u")) {
                if (uranium < 1) {
                    System.out.println("\nYou do not have any uranium");
                    finished = true;
                    subGal = 1;
                    if (gal == 1) {
                        galaxy1();
                    } else if (gal == 2) {
                        galaxy2();
                    } else if (gal == 3) {
                        galaxy3();
                    } else if (gal == 5) {
                        galaxy5();
                    } else if (gal == 7) {
                        galaxy7();
                    } else {
                        galaxy1();
                    }
                } else {
                    finished = true;
                    System.out.println("\nHow many units would you like to sell?");
                    sellUranium();
                }
            } else if (input.contains("produce") || input.contains("p")) {
                if (produce < 1) {
                    System.out.println("\nYou do not have any produce");
                    finished = true;
                    subGal = 1;
                    if (gal == 1) {
                        galaxy1();
                    } else if (gal == 2) {
                        galaxy2();
                    } else if (gal == 3) {
                        galaxy3();
                    } else if (gal == 5) {
                        galaxy5();
                    } else if (gal == 7) {
                        galaxy7();
                    } else {
                        galaxy1();
                    }
                } else {
                    finished = true;
                    System.out.println("\nHow many units would you like to sell?");
                    sellProduce();
                }
            } else if (input.contains("water") || input.contains("w")) {
                if (water < 1) {
                    System.out.println("\nYou do not have any water");
                    finished = true;
                    subGal = 1;
                    if (gal == 1) {
                        galaxy1();
                    } else if (gal == 2) {
                        galaxy2();
                    } else if (gal == 3) {
                        galaxy3();
                    } else if (gal == 5) {
                        galaxy5();
                    } else if (gal == 7) {
                        galaxy7();
                    } else {
                        galaxy1();
                    }
                } else {
                    finished = true;
                    System.out.println("\nHow many units would you like to sell?");
                    sellWater();
                }
            } else if (input.contains("prices")) {
                prices();
            } else if (input.contains("nothing")) {
                subGal = 1;
                if (gal == 1) {
                    galaxy1();
                } else if (gal == 2) {
                    galaxy2();
                } else if (gal == 3) {
                    galaxy3();
                } else if (gal == 5) {
                    galaxy5();
                } else if (gal == 7) {
                    galaxy7();
                } else {
                    galaxy1();
                }
            } else {
                System.out.println("\nWhat would you like to sell?");
            }
        }
    }

    private static void sellUranium() {
        Scanner scanner = new Scanner(System.in);
        boolean continueLoop = true;
        do {
            try {
                int units = scanner.nextInt();
                if (units > uranium || units < 0) {
                    System.out.println("\nYou don't have that many units of Uranium!");
                    subGal = 1;
                    if (gal == 1) {
                        galaxy1();
                    } else if (gal == 2) {
                        galaxy2();
                    } else if (gal == 3) {
                        galaxy3();
                    } else if (gal == 5) {
                        galaxy5();
                    } else if (gal == 7) {
                        galaxy7();
                    } else {
                        galaxy1();
                    }
                } else {
                    System.out.println("\nYou have sold " + units + " unit(s) for " + units * uraniumPrice);
                    coins += units * uraniumPrice;
                    uranium -= units;
                    subGal = 1;
                    if (gal == 1) {
                        galaxy1();
                    } else if (gal == 2) {
                        galaxy2();
                    } else if (gal == 3) {
                        galaxy3();
                    } else if (gal == 5) {
                        galaxy5();
                    } else if (gal == 7) {
                        galaxy7();
                    } else {
                        galaxy1();
                    }
                }
                continueLoop = false;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("\nPlease enter a numeric value");
                scanner.nextLine();
            }
        } while (continueLoop);
    }

    private static void sellProduce() {
        Scanner scanner = new Scanner(System.in);
        boolean continueLoop = true;
        do {
            try {
                int units = scanner.nextInt();
                if (units > produce || units < 0) {
                    System.out.println("\nYou don't have that many units of produce!");
                    subGal = 1;
                    if (gal == 1) {
                        galaxy1();
                    } else if (gal == 2) {
                        galaxy2();
                    } else if (gal == 3) {
                        galaxy3();
                    } else if (gal == 5) {
                        galaxy5();
                    } else if (gal == 7) {
                        galaxy7();
                    } else {
                        galaxy1();
                    }
                } else {
                    System.out.println("\nYou have sold " + units + " unit(s) for " + units * producePrice);
                    coins += units * producePrice;
                    produce -= units;
                    subGal = 1;
                    if (gal == 1) {
                        galaxy1();
                    } else if (gal == 2) {
                        galaxy2();
                    } else if (gal == 3) {
                        galaxy3();
                    } else if (gal == 5) {
                        galaxy5();
                    } else if (gal == 7) {
                        galaxy7();
                    } else {
                        galaxy1();
                    }
                }
                continueLoop = false;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("\nPlease enter a numeric value");
                scanner.nextLine();
            }
        } while (continueLoop);
    }

    private static void sellWater() {
        Scanner scanner = new Scanner(System.in);
        boolean continueLoop = true;
        do {
            try {
                int units = scanner.nextInt();
                if (units > water || units < 0) {
                    System.out.println("\nYou don't have that many units of water!");
                    subGal = 1;
                    if (gal == 1) {
                        galaxy1();
                    } else if (gal == 2) {
                        galaxy2();
                    } else if (gal == 3) {
                        galaxy3();
                    } else if (gal == 5) {
                        galaxy5();
                    } else if (gal == 7) {
                        galaxy7();
                    } else {
                        galaxy1();
                    }
                } else {
                    System.out.println("\nYou have sold " + units + " unit(s) for " + units * waterPrice);
                    coins += units * waterPrice;
                    water -= units;
                    subGal = 1;
                    if (gal == 1) {
                        galaxy1();
                    } else if (gal == 2) {
                        galaxy2();
                    } else if (gal == 3) {
                        galaxy3();
                    } else if (gal == 5) {
                        galaxy5();
                    } else if (gal == 7) {
                        galaxy7();
                    } else {
                        galaxy1();
                    }
                }
                continueLoop = false;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("\nPlease enter a numeric value");
                scanner.nextLine();
            }
        } while (continueLoop);
    }
}
