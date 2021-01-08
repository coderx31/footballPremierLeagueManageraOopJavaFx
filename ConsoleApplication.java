import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.util.List;
import java.util.Scanner;

public class ConsoleApplication extends Application{
    private static LeagueManager leagueManager = new PremierLeagueManager();

    public static void main(String[] args) {
       
       launch(args);
    }

    public static void addFootballClub(){
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        String clubType = null;
        String inputClub;

        /*getting inputs and validating */


        do {
            System.out.println("Please enter S to add School Football Club");
            System.out.println("Please enter U to add University Football club");
            System.out.print("Please enter your choice: ");
            inputClub = scanner.nextLine();
            if (inputClub.matches("^[(S|s)|(U|u)]$")){
                clubType = inputClub;
            }else{
                System.out.println("Invalid Input! Try Again..");
            }
        }while (!inputClub.matches("^[(S|s)|(U|u)]$"));

        String clubName = null;
        String fClubName = null;
        do {
            System.out.print("Please enter Club Name: ");
            clubName = scanner.nextLine();

            if (clubName.matches("^[a-zA-Z ]+$")){
                fClubName = clubName;
            }else{
                System.out.println("Invalid format! Try again..");
            }
        }while (!clubName.matches("^[a-zA-Z ]+$"));

        String number;
        String fANumber = null;
        System.out.println("Please enter Club's Address");
        do {
            System.out.print("Please enter Number: ");
            number = scanner.nextLine();
            if (number.matches("^\\d+(\\/)?[A-Za-z]?$")){
                fANumber = number;
            }else{
                System.out.println("Invalid Format! Try again.. (ex: 12 | 12/a)");
            }
        }while (!number.matches("^\\d+(\\/)?[A-Za-z]?$"));

        String road;
        String fARd = null;
        do {
            System.out.print("Please enter Road: ");
            road = scanner.nextLine();
            if (road.matches("^[a-zA-Z ,]+$")){
                fARd = road;
            }else{
                System.out.println("Invalid Format! Try again..");
            }
        }while (!road.matches("^[a-zA-Z ,]+$"));

        String town;
        String fAT = null;
        do {
            System.out.print("Please enter Town: ");
            town = scanner.nextLine();
            if (town.matches("^[a-zA-Z ]+\\d{0,2}?$")){
                fAT = town;
            }else {
                System.out.println("Invalid format! Try again..");
            }
        }while (!town.matches("^[a-zA-Z ]+\\d{0,2}?$"));

        int fPc = 0;
        String postalCode;
        do {
            System.out.print("Please enter Postal Code: ");
            postalCode = scanner.nextLine();
            if (postalCode.matches("^\\d{5}$")){
                fPc = Integer.parseInt(postalCode);
            }else{
                System.out.println("Invalid format! try again..");
            }
        }while (!postalCode.matches("^\\d{5}$"));

        String country;
        String fCountry = null;
        do {
            System.out.print("Please enter Country: ");
            country = scanner.nextLine();
            if (country.matches("^[a-zA-Z ]+$")){
                fCountry = country;
            }else{
                System.out.println("Invalid format! Try again..");
            }
        }while (!country.matches("^[a-zA-Z ]+$"));

        String mName;
        String fMName = null;
        do {
            System.out.print("Please enter School / Uni Name: ");
            mName = scanner.nextLine();
            if (mName.matches("^[a-zA-Z .']+$")){
                fMName = mName;
            }else{
                System.out.println("Invalid format! Try again");
            }
        }while (!mName.matches("^[a-zA-Z .']+$"));

        Address address = new Address(fANumber,fARd,fAT,fPc,fCountry);

        do {

            switch (clubType){
                case "S":
                case "s":
                    SchoolFootballClub schoolFootballClub = new SchoolFootballClub(fMName,fClubName,address);
                    leagueManager.addFootballClub(schoolFootballClub);
                    exit = true;
                    break;
                case "U":
                case "u":
                    UniversityFootballClub universityFootballClub = new UniversityFootballClub(fMName,fClubName,address);
                    leagueManager.addFootballClub(universityFootballClub);
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice try again");
                    exit = false;
            }

        }while (!exit);

    }

    public static void deleteFootballClub(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter Club Name: ");
        String clubName = scanner.nextLine();
        leagueManager.deleteFootballClub(clubName);
    }

    public static void displayStatistics(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter Club Name: ");
        String clubName = scanner.nextLine();
        leagueManager.displayStatistics(clubName);
    }

    public static void displayLeagueTable(){
        Scanner scanner = new Scanner(System.in);
        String input_choice = null;
        String choice = null;
        do {
            System.out.println("Please enter S to Display School league Table");
            System.out.println("Please enter U to Display University league Table");
            System.out.print("Please select your choice: ");
            input_choice = scanner.nextLine();
            if (input_choice.matches("^[(S|s)|(U|u)]$")){
                choice = input_choice;
            }else{
                System.out.println("Invalid choice! Try Again!");
            }
        }while (!input_choice.matches("^[(S|s)|(U|u)]$"));


        leagueManager.displayLeagueTable(choice);
    }

    public static void addPlayedMatch(){
        Scanner scanner = new Scanner(System.in);

        /*getting inputs and validate them*/
        String homeTeam;
        String mHomeTeam = null;
        do {
            System.out.print("Please enter Home Team: ");
            homeTeam = scanner.nextLine();
            if (homeTeam.matches("^[a-zA-Z ]+$")){
                mHomeTeam = homeTeam;
            }else {
                System.out.println("Invalid format! Try Again..");
            }
        }while (!homeTeam.matches("^[a-zA-Z ]+$"));

        String opponentTeam;
        String mOpponentTeam = null;
        do {
            System.out.print("Please enter Opponent Team: ");
            opponentTeam = scanner.nextLine();
            if (opponentTeam.matches("^[a-zA-Z ]+$")){
                mOpponentTeam = opponentTeam;
            }else {
                System.out.println("Invalid format! Try Again..");
            }
        }while (!opponentTeam.matches("^[a-zA-Z ]+$"));

        String  homeScore;
        int mHomeScore = 0;
        do {
            System.out.print("Please enter Home Team Score: ");
            homeScore = scanner.nextLine();
            if (homeScore.matches("^\\d{1,2}$")){
                mHomeScore = Integer.parseInt(homeScore);
            }else{
                System.out.println("Invalid format! Try Again..");
            }
        }while (!homeScore.matches("^\\d{1,2}$"));

        String opponentScore;
        int mOpponentScore = 0;
        do {
            System.out.print("Please enter Opponent Team: ");
            opponentScore = scanner.nextLine();
            if (opponentScore.matches("^\\d{1,2}$")){
                mOpponentScore = Integer.parseInt(opponentScore);
            }else{
                System.out.println("Invalid format! Try again..");
            }
        }while (!opponentScore.matches("^\\d{1,2}$"));


        System.out.println("Please enter Date");
        String day;
        int mDay = 0;
        do {
            System.out.print("Please enter day: ");
            day = scanner.nextLine();
            if(day.matches("^(3[01]|[12][0-9]|[1-9])$")){
                mDay = Integer.parseInt(day);
            }else{
                System.out.println("Invalid format! Try again");
            }
        }while (!day.matches("^(3[01]|[12][0-9]|[1-9])$"));

        String month;
        int mMonth = 0;
        do {
            System.out.print("Please enter month: ");
            month = scanner.nextLine();
            if (month.matches("^(1[0-2]|[1-9])$")){
                mMonth = Integer.parseInt(month);
            }else{
                System.out.println("Invalid format! Try again..");
            }
        }while (!month.matches("^(1[0-2]|[1-9])$"));

        String year;
        int mYear = 0;
        do {
            System.out.print("Please enter year: ");
            year = scanner.nextLine();
            if (year.matches("^(202)\\d{1}$")){
                mYear = Integer.parseInt(year);
            }else {
                System.out.println("Invalid format! Try again..");
            }
        }while (!year.matches("^(202)\\d{1}$"));

        Date date = new Date(mDay,mMonth,mYear);

        Match match = new Match(mHomeTeam,mOpponentTeam,mHomeScore,mOpponentScore,date);
        leagueManager.addPlayedMatch(match);


    }

    public static void saveToFile(){
        // need implementation
        leagueManager.saveToFile();
    }

    public static void loadFromFile(){
        // need implementation
        leagueManager.loadFromFile();
    }

    public static void gui(){
        leagueManager.gui();
    }



    @Override
    public void start(Stage primaryStage) throws Exception {
        //menu goes in
        final Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        String select;
        loadFromFile();
        do {
            System.out.println("Premier League Manager");
            System.out.println("=================================");
            System.out.println("Please enter A to Add Football Club");
            System.out.println("Please enter D to Delete Football Club");
            System.out.println("Please enter S to Display Club Statistics");
            System.out.println("Please enter P to Display League Table");
            System.out.println("Please enter M to Add Played Match");
            System.out.println("Please enter G to Display GUI");
            System.out.println("Please enter Q to Exit");
            System.out.print("Please enter your choice: ");
            select = scanner.nextLine();
            switch (select){
                case "A":
                case "a" :
                    addFootballClub();
                    exit = false;
                    break;
                case "D":
                case "d":
                    deleteFootballClub();
                    exit = false;
                    break;
                case "S":
                case "s":
                    displayStatistics();
                    exit = false;
                    break;
                case "P":
                case "p":
                    displayLeagueTable();
                    exit = false;
                    break;
                case "M":
                case "m":
                    addPlayedMatch();
                    exit = false;
                    break;
                case "G":
                case "g":
                    gui();
                    exit = false;
                    break;
                case "Q":
                case "q":
                    saveToFile();
                    System.exit(0);
                    exit = true;
                default:
                    System.out.println("Invalid choice! please try again");
                    exit = false;

            }
        }while (!exit);

    }
}
