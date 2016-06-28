package com.sillysoft.lux.agent;//Created By: Brian Konzman on 6/14/2016 

import com.sillysoft.lux.Board;
import com.sillysoft.lux.Card;
import com.sillysoft.lux.Country;

public class TestBot extends SmartAgentBase  {

    private int ID;
    private Board board;
    private Country[] countries;

    private void evenPlacement(int i) {
        while(i > 0) {
            int countryWithLowestArmies = -1;
            int numberOfLowestArmies = Integer.MAX_VALUE;
            for (Country country : countries) {
                if (country.getOwner() == ID && country.getArmies() < numberOfLowestArmies) {
                    countryWithLowestArmies = country.getCode();
                    numberOfLowestArmies = country.getArmies();
                }
            }
            board.placeArmies(1, countryWithLowestArmies);
            i--;
        }
    }

    private Attack attackWeakestCountry() {
        Country easiestEnemy = board.getCountries()[getEasiestContToTake()];
        Country strongestCountry = null;
        for (Country c :
                easiestEnemy.getAdjoiningList()) {

            if (c.getOwner() == ID) {
                if (strongestCountry == null) {
                    strongestCountry = c;
                } else if (strongestCountry.getArmies() < c.getArmies()) {
                    strongestCountry = c;
                }
            }
        }
        if (strongestCountry != null) {
            return new Attack(strongestCountry.getCode(), easiestEnemy.getCode());
        }
        return null;
    }

    @Override
    public void setPrefs(int i, Board board) {
        this.ID = i;
        this.board = board;
        countries = board.getCountries();
    }

    @Override
    public int pickCountry() {
        int cc = 0;
        while (countries[cc].getOwner() != -1) {
            cc++;
        }
        System.out.println("picked country " + String.valueOf(cc));
        return cc;
    }

    @Override
    public void placeInitialArmies(int i) {
        System.out.println("place armies");
        evenPlacement(i);
    }

    @Override
    public void cardsPhase(Card[] cards) {
        System.out.println("card phase");
    }

    @Override
    public void placeArmies(int i) {
        System.out.println("placed armies");
        evenPlacement(i);
    }

    @Override
    public void attackPhase() {
        System.out.println("attack phase");
        return;
    }

    @Override
    public int moveArmiesIn(int i, int i1) {
        System.out.println("move armies in");
        return 0;
    }

    @Override
    public void fortifyPhase() {
        System.out.println("fortify");
        return;
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public float version() {
        return 0;
    }

    @Override
    public String description() {
        return "Testing";
    }

    @Override
    public String youWon() {
        return "nothing needs to go here";
    }

    @Override
    public String message(String s, Object o) {
        return "message";
    }

    class Attack {
        private int toCountry;
        private int fromCountry;

        public Attack(int toCountry, int fromCountry) {
            this.toCountry = toCountry;
            this.fromCountry = fromCountry;
        }

        public int getToCountry() {
            return toCountry;
        }

        public int getFromCountry() {
            return fromCountry;
        }
    }
}
