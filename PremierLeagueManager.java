//import com.jfoenix.controls.JFXButton;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.*;
import java.time.LocalDate;
import java.util.*;


public class PremierLeagueManager implements LeagueManager{
    private List<FootballClub> footballClubs = new ArrayList<FootballClub>();
    private List<Match> allMatches = new ArrayList<Match>();
    private static double xOffset = 0;
    private static double yOffset = 0;



    @Override
    public void addFootballClub(FootballClub footballClub){
        //need implementation
        for (FootballClub footballClub1 : footballClubs){
            if (footballClub1.getClubName().toLowerCase().contains(footballClub.getClubName().toLowerCase())){
                System.out.println("This club name is already exist");
                return;
            }
        }
        footballClubs.add(footballClub);


        /*testing display*/
        for (FootballClub footballClub1 : footballClubs){
            System.out.println(footballClub1.getClubName());
        }
    }

    @Override
    public void deleteFootballClub(String clubName){
        //need implementation
        if (footballClubs.isEmpty()){
            System.out.println("No clubs added yet!");
            return;
        }
        if (!footballClubs.removeIf(footballClub -> footballClub.getClubName().toLowerCase().contains(clubName.toLowerCase()))){
            System.out.println("No such club");
        }else{
            System.out.println("Successfully deleted!");
        }
    }

    @Override
    public void displayStatistics(String clubName){
        //need implementation
        if (footballClubs.isEmpty()){
            System.out.println("No clubs added yet!");
            return;
        }
        boolean checker = false;
        for (FootballClub footballClub : footballClubs){
            if (footballClub.getClubName().toLowerCase().contains(clubName.toLowerCase())){
                System.out.println(footballClub.toString());
                checker = true;
            }
        }
        if (!checker){
            System.out.println("No such Club");
        }
    }
    @Override
    public void displayLeagueTable(String choice){
        //need implementation
        if (footballClubs.isEmpty()){
            System.out.println("No clubs added yet!");
            return;
        }
        Collections.sort(footballClubs,Collections.reverseOrder());
       // System.out.println("Club Name           |"+" PL "+" W "+" D "+" L "+" G "+" P ");
        System.out.printf("%25s%4s%4s%4s%4s%6s%6s%n","Club Name","PL","W","D","L","G","P");
        switch (choice){
            case "S":
            case "s":
                for (FootballClub footballClub : footballClubs){
                    if (footballClub instanceof SchoolFootballClub){
                        System.out.printf("%25s%4d%4d%4d%4d%6d%6d%n",footballClub.getClubName(),
                                footballClub.getNumOfMatchPlayed(),footballClub.getNumOfWins(),footballClub.getNumOfDraws(),
                                footballClub.getNumOfDefeats(),footballClub.getGoalScored(),footballClub.getPoints());
                    }
                }

                break;
            case "U":
            case "u":
                for (FootballClub footballClub : footballClubs){
                    if (footballClub instanceof UniversityFootballClub){
                        System.out.printf("%25s%4d%4d%4d%4d%6d%6d%n",footballClub.getClubName(),
                                footballClub.getNumOfMatchPlayed(),footballClub.getNumOfWins(),footballClub.getNumOfDraws(),
                                footballClub.getNumOfDefeats(),footballClub.getGoalScored(),footballClub.getPoints());
                    }
                }

                break;
        }
    }

    @Override
    public void addPlayedMatch(Match match) {
        boolean opponentWin = false;
        boolean opponentDefeat = false;
        boolean homeChecker = false;
        boolean opponentChecker = false;
        String homeClubType = null;
        String opponentClubType = null;
        boolean clubChecker = false;
        for (FootballClub footballClub : footballClubs){
            if (footballClub.getClubName().toLowerCase().equals(match.getHomeTeam().toLowerCase())){
                if (footballClub instanceof SchoolFootballClub){
                    homeClubType = "scl";
                }else if (footballClub instanceof UniversityFootballClub){
                    homeClubType = "uni";
                }
                homeChecker = true;
                for (FootballClub footballClub1 : footballClubs){
                    if (footballClub1.getClubName().toLowerCase().equals(match.getOpponentTeam().toLowerCase())){
                        if (footballClub1 instanceof SchoolFootballClub){
                            opponentClubType = "scl";
                        }else if (footballClub1 instanceof UniversityFootballClub){
                            opponentClubType = "uni";
                        }

                        opponentChecker = true;
                    }
                }

                if (homeClubType.equals(opponentClubType)){
                    clubChecker = true;
                }

                if (!opponentChecker || !homeChecker || !clubChecker){
                    System.out.println("Something wrong check both clubs are registered in league");
                    return;
                }
                footballClub.setGoalScored(match.getHomeScore());
                footballClub.setNumOfMatchPlayed();
                if (match.getHomeScore() > match.getOpponentScore()){
                    footballClub.setNumOfWins();
                    footballClub.setPoints(3);
                    opponentDefeat = true;
                }else if (match.getHomeScore() < match.getOpponentScore()){
                    footballClub.setNumOfDefeats();
                    opponentWin = true;
                }else{
                    footballClub.setNumOfDraws();
                    footballClub.setPoints(1);
                }

            }

        }


        for (FootballClub footballClub : footballClubs){
            if (footballClub.getClubName().toLowerCase().equals(match.getOpponentTeam().toLowerCase())){
                opponentChecker = true;
                for(FootballClub footballClub1 : footballClubs){
                    if (footballClub1.getClubName().toLowerCase().equals(match.getHomeTeam().toLowerCase())){
                        homeChecker = true;
                    }
                }
                if (!opponentChecker || !homeChecker || !clubChecker){
                    System.out.println("Something wrong check both clubs are registered in league");
                    return;
                }

                footballClub.setGoalScored(match.getOpponentScore());
                footballClub.setNumOfMatchPlayed();

                if (opponentWin){
                    footballClub.setNumOfWins();
                    footballClub.setPoints(3);
                }else if (opponentDefeat){
                    footballClub.setNumOfDefeats();
                }else {
                    footballClub.setNumOfDraws();
                    footballClub.setPoints(1);
                }
            }
        }

        if (homeChecker && opponentChecker){
            allMatches.add(match);
            System.out.println("successfully added match");
        }

        /*just test*/
        for (Match match1 : allMatches){
            System.out.println(match1.toString());
        }





    }

    @Override
    public void saveToFile(){
        /*footballClubs to File*/
        try {
            FileOutputStream footballClubsFile = new FileOutputStream("football_clubs.txt");
            ObjectOutputStream footballClubObjects = new ObjectOutputStream(footballClubsFile);

            for (FootballClub footballClub: footballClubs){
                footballClubObjects.writeObject(footballClub);
            }
            System.out.println("Clubs data has been added to file");
        }catch (Exception e){
            System.err.println("Error Occurred!");
        }

        /*allMatches to File*/
        try {
            FileOutputStream matchesFile = new FileOutputStream("matches.txt");
            ObjectOutputStream matchObjects = new ObjectOutputStream(matchesFile);

            for (Match match : allMatches){
                matchObjects.writeObject(match);
            }
            System.out.println("Matches data has been added to File");

        }catch (Exception e){
            System.err.println("Error Occurred!");
        }

    }

    @Override
    public void loadFromFile(){
        File club_data = new File("football_clubs.txt");
        if (club_data.length() == 0){
            System.out.println("File is Empty");
            return;
        }

        /*football club objects to arrayList*/
        try {
            FileInputStream footballClubFile = new FileInputStream("football_clubs.txt");
            ObjectInputStream football_clubs = new ObjectInputStream(footballClubFile);

            for (;;){
                try {
                    footballClubs.add((FootballClub)football_clubs.readObject());
                }catch (Exception e){
                    break;
                }
            }
        }catch (Exception e){
            System.err.println("Error Occurred!");
        }
        System.out.println("Club data loaded");

        File match_data = new File("matches.txt");
        if (match_data.length() == 0){
            System.out.println("File is Empty");
            return;
        }
        /*match objects to arrayList*/

        try{
            FileInputStream matchesFile = new FileInputStream("matches.txt");
            ObjectInputStream matches = new ObjectInputStream(matchesFile);

            for (;;){
                try{
                    allMatches.add((Match)matches.readObject());
                }catch (Exception e){
                    break;
                }
            }
        }catch (Exception e){
            System.err.println("Error Occurred!");
        }

        System.out.println("Match data loaded");

    }

    /*getting and setting arrayLists*/
    public List<FootballClub> getFootballClubs(){
        return footballClubs;
    }

    public void setFootballClubs(FootballClub footballClub){
        footballClubs.add(footballClub);
    }

    public List<Match> getAllMatches(){
        return allMatches;
    }

    public void setAllMatches(Match match){
        allMatches.add(match);
    }


    @Override
    public void gui(){

        Stage mainStage = new Stage();
        /*Loading Custom Fonts*/
        FileInputStream poppinsBlack = null;
        try {
            poppinsBlack = new FileInputStream("assets/fonts/Poppins-Black.ttf");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Font.loadFont(poppinsBlack,18);
        AnchorPane bodyPane = new AnchorPane();
        bodyPane.setId("body-pane");

        AnchorPane headerPane = new AnchorPane();
        headerPane.setId("header-pane");
        headerPane.setLayoutY(0);
        headerPane.setLayoutX(0);
        FileInputStream logoFile = null;
        try {
            logoFile = new FileInputStream("assets/images/logo-design.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image logoImage = new Image(logoFile);
        ImageView logoView = new ImageView(logoImage);
        logoView.setLayoutX(0);
        logoView.setLayoutY(0);
        HBox btnHolder = new HBox(20);
        Label home = new Label("Home");
        home.setId("home");
        Label pointTable = new Label("Point Table");
        pointTable.setId("point-table");
        Label playMatch = new Label("Play Match");
        playMatch.setId("play-match");
        Label matches = new Label("Matches");
        matches.setId("matches");
        btnHolder.getChildren().addAll(home,pointTable,playMatch,matches);
        btnHolder.setLayoutY(40);
        btnHolder.setLayoutX(600);
        FileInputStream closeFile = null;
        try {
            closeFile = new FileInputStream("assets/images/close.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image closeImage = new Image(closeFile);
        ImageView closeView = new ImageView(closeImage);
        closeView.setId("close-view");
        closeView.setLayoutY(10);
        closeView.setLayoutX(970);
        closeView.setOnMouseClicked( e -> mainStage.close());
        headerPane.getChildren().addAll(logoView,btnHolder,closeView);
        AnchorPane contentPane = new AnchorPane();
        contentPane.setId("content-pane");
        contentPane.setLayoutX(0);
        contentPane.setLayoutY(100);
        AnchorPane textPane = new AnchorPane();
        textPane.setId("text-pane");
        Text description = new Text("The Premier League is the top tier of England's football pyramid," +"\n"+
                " with 20 teams battling it out for the honour of being crowned English champions.\n" +
                "\n" +
                "\n"+

                "Home to some of the most famous clubs, players, managers and stadiums"+"\n" +"in world football,"+
                " the Premier League is the most-watched league on the"+"\n"+" planet with one billion homes watching"+"\n" +
                " the action in 188 countries.");
        description.setId("description");
        description.setLayoutX(20);
        description.setLayoutY(150);
        textPane.getChildren().addAll(description);
        FileInputStream playerFile = null;
        try {
            playerFile = new FileInputStream("assets/images/mainPlayer.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image playerImage = new Image(playerFile);
        ImageView playerView = new ImageView(playerImage);
        playerView.setLayoutY(20);
        playerView.setLayoutX(550);
        //JFXButton playMatchBtn = new JFXButton("Play Match");
        Button playMatchBtn = new Button("Play Match");
	playMatchBtn.setId("matchBtn");
        playMatchBtn.setLayoutX(50);
        playMatchBtn.setLayoutY(400);
        contentPane.getChildren().addAll(textPane,playerView,playMatchBtn);

        bodyPane.getChildren().addAll(headerPane,contentPane);
        Scene bodyScene = new Scene(bodyPane,1000,600);
        bodyScene.setFill(Color.TRANSPARENT);
        bodyScene.getStylesheets().add("/CSS/bodyPaneStyle.css");
        home.setOnMouseClicked( e ->{
            bodyPane.getChildren().remove(contentPane);
            bodyPane.getChildren().add(contentPane);
        });


        /*point-table scene*/
        AnchorPane tablePane = new AnchorPane();
        ChoiceBox<String> sclSelector = new ChoiceBox<>();
        sclSelector.getItems().addAll("Points","Goals","Wins");
        sclSelector.setValue("Points");
        sclSelector.setLayoutX(20);
        sclSelector.setLayoutY(20);

        tablePane.setId("table-pane");
        TableView sclTable = new TableView();
        TableColumn<SchoolFootballClub, String> sclNameColumn = new TableColumn("Club Name");
        sclNameColumn.setCellValueFactory(new PropertyValueFactory<>("clubName"));

        TableColumn<SchoolFootballClub, Integer> sclPLColumn = new TableColumn("PL");
        sclPLColumn.setCellValueFactory(new PropertyValueFactory<>("numOfMatchPlayed"));

        TableColumn<SchoolFootballClub, Integer> sclWinColumn = new TableColumn<>("W");
        sclWinColumn.setCellValueFactory(new PropertyValueFactory<>("numOfWins"));

        TableColumn<SchoolFootballClub, Integer> sclDrColumn = new TableColumn<>("Dr");
        sclDrColumn.setCellValueFactory(new PropertyValueFactory<>("numOfDraws"));

        TableColumn<SchoolFootballClub, Integer> sclLColumn = new TableColumn<>("L");
        sclLColumn.setCellValueFactory(new PropertyValueFactory<>("numOfDefeats"));

        TableColumn<SchoolFootballClub, Integer> sclGColumn = new TableColumn<>("G");
        sclGColumn.setCellValueFactory(new PropertyValueFactory<>("goalScored"));

        TableColumn<SchoolFootballClub, Integer> sclPColumn = new TableColumn<>("P");
        sclPColumn.setCellValueFactory(new PropertyValueFactory<>("points"));

        sclSelector.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) ->{
            sclTable.setItems(getSchoolsClubs(newValue));
        });

        sclTable.setItems(getSchoolsClubs("Points"));
        sclTable.getColumns().addAll(sclNameColumn,sclPLColumn,sclWinColumn,sclDrColumn,sclLColumn,
                sclGColumn,sclPColumn);

        sclTable.setPrefSize(315,200);
        sclTable.setLayoutX(20);
        sclTable.setLayoutY(75);

        ChoiceBox<String> uniSelector = new ChoiceBox<>();
        uniSelector.getItems().addAll("Points","Goals","Wins");
        uniSelector.setValue("Points");
        uniSelector.setLayoutX(600);
        uniSelector.setLayoutY(200);




        TableView uniTable = new TableView();

        TableColumn<UniversityFootballClub, String> uniNameColumn = new TableColumn<>("Club Name");
        uniNameColumn.setCellValueFactory(new PropertyValueFactory<>("clubName"));

        TableColumn<UniversityFootballClub, Integer> uniPLColumn = new TableColumn<>("PL");
        uniPLColumn.setCellValueFactory(new PropertyValueFactory<>("numOfMatchPlayed"));

        TableColumn<UniversityFootballClub, Integer> uniWinColumn = new TableColumn<>("W");
        uniWinColumn.setCellValueFactory(new PropertyValueFactory<>("numOfWins"));

        TableColumn<UniversityFootballClub, Integer> uniDrColumn = new TableColumn<>("Dr");
        uniDrColumn.setCellValueFactory(new PropertyValueFactory<>("numOfDraws"));

        TableColumn<UniversityFootballClub, Integer> uniLColumn = new TableColumn<>("L");
        uniLColumn.setCellValueFactory(new PropertyValueFactory<>("numOfDefeats"));

        TableColumn<UniversityFootballClub, Integer> uniGColumn = new TableColumn<>("G");
        uniGColumn.setCellValueFactory(new PropertyValueFactory<>("goalScored"));

        TableColumn<UniversityFootballClub, Integer> uniPColumn = new TableColumn<>("P");
        uniPColumn.setCellValueFactory(new PropertyValueFactory<>("points"));

        uniSelector.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) ->{
            uniTable.setItems(getUniClubs(newValue));
        });

        uniTable.setItems(getUniClubs("Points"));
        uniTable.getColumns().addAll(uniNameColumn,uniPLColumn,uniWinColumn,uniDrColumn,
                uniLColumn,uniGColumn,uniPColumn);
        uniTable.setPrefSize(325,200);
        uniTable.setLayoutX(600);
        uniTable.setLayoutY(250);




        tablePane.getChildren().addAll(sclTable,uniTable,sclSelector,uniSelector);



        pointTable.setOnMouseClicked( e ->{
            bodyPane.getChildren().remove(contentPane);
            bodyPane.getChildren().remove(tablePane);
            tablePane.setLayoutY(100);
            tablePane.setLayoutX(0);
            bodyPane.getChildren().add(tablePane);
        });

        /*Play-match Scene*/
        AnchorPane playMatchPane = new AnchorPane();
        playMatchPane.setId("play-pane");
        AnchorPane resultView = new AnchorPane();
        resultView.setId("result-view");
        Label heading = new Label("Match Result");
        heading.setId("heading");
        heading.setLayoutX(130);
        heading.setLayoutY(20);
        Label homeTeam = new Label("Annites");
        homeTeam.setId("home-team");
        homeTeam.setLayoutX(50);
        homeTeam.setLayoutY(125);
        Label homeScore = new Label("05");
        homeScore.setId("home-score");
        homeScore.setLayoutX(75);
        homeScore.setLayoutY(150);
        Label opponentTeam = new Label("Devans");
        opponentTeam.setId("opponent-team");
        opponentTeam.setLayoutX(300);
        opponentTeam.setLayoutY(125);
        Label opponentScore = new Label("03");
        opponentScore.setId("opponent-score");
        opponentScore.setLayoutX(325);
        opponentScore.setLayoutY(150);
        Label matchDate = new Label("10/10/2020");
        matchDate.setId("match-date");
        matchDate.setLayoutY(200);
        matchDate.setLayoutX(175);
        HBox btnBox = new HBox(100);
        Button sclMatch = new Button("School vs School");
        sclMatch.setId("scl-match");
        sclMatch.setOnAction(e -> {
            sclMatch(homeTeam,opponentTeam,homeScore,opponentScore,matchDate);
            sclTable.refresh();
        });
        Button uniMatch = new Button("Uni vs Uni");
        uniMatch.setOnAction(e -> {
            uniMatch(homeTeam,opponentTeam,homeScore,opponentScore,matchDate);
            uniTable.refresh();

        });
        uniMatch.setId("uni-match");
        btnBox.getChildren().addAll(sclMatch,uniMatch);
        btnBox.setLayoutX(320);
        btnBox.setLayoutY(420);
        resultView.getChildren().addAll(heading,homeTeam,homeScore,opponentTeam,opponentScore,matchDate);
        resultView.setLayoutX(250);
        resultView.setLayoutY(100);
        playMatchPane.getChildren().addAll(resultView,btnBox);
        playMatch.setOnMouseClicked( e ->{
            bodyPane.getChildren().removeAll(contentPane,tablePane,playMatchPane);
            playMatchPane.setLayoutX(0);
            playMatchPane.setLayoutY(100);
            bodyPane.getChildren().addAll(playMatchPane);

        });

        playMatchBtn.setOnAction( e ->{
            bodyPane.getChildren().removeAll(contentPane,tablePane,playMatchPane);
            playMatchPane.setLayoutX(0);
            playMatchPane.setLayoutY(100);
            bodyPane.getChildren().addAll(playMatchPane);
        });

        /*match-pane*/
        AnchorPane matchPane = new AnchorPane();
        matchPane.setId("match-pane");
        FlowPane matchHolder = new FlowPane(10,10);
        HBox buttons = new HBox(30);
        Button displayAllMatches = new Button("Display Matches");
        displayAllMatches.setOnAction(e -> displayAllMatches(matchHolder));
        displayAllMatches.setId("display-match");
        DatePicker datePicker = new DatePicker();
        datePicker.setOnAction( e ->{
            Date date = new Date(datePicker.getValue().getDayOfMonth(),
                    datePicker.getValue().getMonthValue(),datePicker.getValue().getYear());
            selectMatchByDate(date,matchHolder);
        });
        buttons.getChildren().addAll(displayAllMatches,datePicker);
        buttons.setLayoutY(30);
        buttons.setLayoutX(350);



        ScrollPane scroll = new ScrollPane(matchHolder);
        scroll.setId("scroll-pane");
        scroll.setPrefSize(940,400);
        scroll.setLayoutX(30);
        scroll.setLayoutY(100);
        //matchHolder.prefWidthProperty().bind(Bindings.add(-5,scroll.widthProperty()));
        matchHolder.prefHeightProperty().bind(Bindings.add(-5,scroll.heightProperty()));
        matchPane.getChildren().addAll(buttons,matchHolder,scroll);
        matches.setOnMouseClicked( e ->{

            bodyPane.getChildren().removeAll(contentPane,tablePane,playMatchPane,matchPane);
            matchPane.setLayoutX(0);
            matchPane.setLayoutY(100);
            bodyPane.getChildren().addAll(matchPane);

        });
        mainStage.setScene(bodyScene);
        mainStage.initStyle(StageStyle.TRANSPARENT);
        mainStage.showAndWait();

        /*moving the pane*/
        bodyPane.setOnMousePressed(e ->{
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
        });

        bodyPane.setOnMouseDragged( e ->{
            mainStage.setX(e.getScreenX() - xOffset);
            mainStage.setY(e.getScreenY() - yOffset);
        });



    }


    /*get clubs into observable arraylist*/
    public ObservableList<SchoolFootballClub> getSchoolsClubs(String select){
        ObservableList<SchoolFootballClub> sclClubs = FXCollections.observableArrayList();
        for (FootballClub footballClub : footballClubs){
            if (footballClub instanceof SchoolFootballClub){
                sclClubs.add((SchoolFootballClub) footballClub);
            }
        }
        FXCollections.sort(sclClubs, new Comparator<SchoolFootballClub>() {
            @Override
            public int compare(SchoolFootballClub scl1, SchoolFootballClub scl2) {
                if (scl2.getPoints() == scl1.getPoints()){
                    return scl2.getGoalScored() - scl1.getGoalScored();
                }
                return scl2.getPoints() - scl1.getPoints();
            }
        });
        if (select.equals("Goals")){
            FXCollections.sort(sclClubs, new Comparator<SchoolFootballClub>() {
                @Override
                public int compare(SchoolFootballClub o1, SchoolFootballClub o2) {
                    return o2.getGoalScored() - o1.getGoalScored();
                }
            });
        }else if (select.equals("Wins")){
            FXCollections.sort(sclClubs, new Comparator<SchoolFootballClub>() {
                @Override
                public int compare(SchoolFootballClub o1, SchoolFootballClub o2) {
                    return o2.getNumOfWins() - o1.getNumOfWins();
                }
            });
        }
        //sclClubs.sorted();
        return sclClubs;
    }

    public ObservableList<UniversityFootballClub> getUniClubs(String select){
        ObservableList<UniversityFootballClub> uniClubs = FXCollections.observableArrayList();
        for (FootballClub footballClub : footballClubs){
            if (footballClub instanceof UniversityFootballClub){
                uniClubs.add((UniversityFootballClub) footballClub);
            }
        }
        FXCollections.sort(uniClubs, new Comparator<UniversityFootballClub>() {
            @Override
            public int compare(UniversityFootballClub uni1, UniversityFootballClub uni2) {
                if(uni2.getPoints() == uni1.getPoints()){
                    return uni2.getGoalScored() - uni1.getGoalScored();
                }else {
                    return uni2.getPoints() - uni1.getPoints();
                }
            }
        });
        if (select.equals("Goals")){
            FXCollections.sort(uniClubs, new Comparator<UniversityFootballClub>() {
                @Override
                public int compare(UniversityFootballClub o1, UniversityFootballClub o2) {
                    return o2.getGoalScored() - o1.getGoalScored();
                }
            });
        }else if (select.equals("Wins")){
            FXCollections.sort(uniClubs, new Comparator<UniversityFootballClub>() {
                @Override
                public int compare(UniversityFootballClub o1, UniversityFootballClub o2) {
                    return o2.getGoalScored() - o1.getGoalScored();
                }
            });
        }
        //uniClubs.sorted();
        return uniClubs;
    }

    public void sclMatch(Label home, Label opponent, Label hScore, Label oScore, Label mDate){
	if (footballClubs.size() < 2){
            System.out.println("Not Enough Teams");
            return;
        }
        List<SchoolFootballClub> sclClubs = new ArrayList<SchoolFootballClub>();
        for (FootballClub footballClub : footballClubs){
            if (footballClub instanceof SchoolFootballClub){
                sclClubs.add((SchoolFootballClub) footballClub);
            }
        }

        Random random = new Random();
        int homeSelect = random.nextInt(sclClubs.size() - 1);
        int opponentSelect = random.nextInt(sclClubs.size() - 1);

        if (homeSelect == opponentSelect){
            if (opponentSelect == sclClubs.size() - 1){
                opponentSelect -= 1;
            }else{
                opponentSelect += 1;
            }
        }

        String homeTeam = sclClubs.get(homeSelect).getClubName();
        String opponentTeam = sclClubs.get(opponentSelect).getClubName();

        int homeScore = random.nextInt(10);
        int opponentScore = random.nextInt(10);

        /*setting the current date into match*/
        LocalDate date = LocalDate.now();

        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();

        String matchDate =  day+"/"+month+"/"+year;

        /*update the UI*/
        home.setText(homeTeam);
        opponent.setText(opponentTeam);

        hScore.setText(String.valueOf(homeScore));
        oScore.setText(String.valueOf(opponentScore));

        mDate.setText(matchDate);


        Date matchDay = new Date(day,month,year);


        /*Creating the match object*/
        Match match = new Match(homeTeam,opponentTeam,homeScore,opponentScore,matchDay);

        /*added to the match*/
        addPlayedMatch(match);
    }

    public void uniMatch(Label home, Label opponent, Label hScore, Label oScore, Label mDate){
	if (footballClubs.size() < 2){
            System.out.println("Not Enough Teams");
            return;
        }
        ObservableList<UniversityFootballClub> uniClubs = FXCollections.observableArrayList();
        for (FootballClub footballClub : footballClubs){
           if (footballClub instanceof UniversityFootballClub){
               uniClubs.add((UniversityFootballClub) footballClub);
           }
        }

        /*random teams and their scores*/
        Random random = new Random();
        int homeSelect = random.nextInt(uniClubs.size() - 1);
        int opponentSelect = random.nextInt(uniClubs.size() - 1);

        if (homeSelect == opponentSelect){
            if (opponentSelect == uniClubs.size() - 1){
                opponentSelect -= 1;
            }else{
                opponentSelect += 1;
            }
        }

        String homeTeam = uniClubs.get(homeSelect).getClubName();
        String opponentTeam = uniClubs.get(opponentSelect).getClubName();

        int homeScore = random.nextInt(10);
        int opponentScore = random.nextInt(10);

        /*setting the current date into match*/
        LocalDate date = LocalDate.now();

        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();

        String matchDate =  day+"/"+month+"/"+year;

        /*update the UI*/
        home.setText(homeTeam);
        opponent.setText(opponentTeam);

        hScore.setText(String.valueOf(homeScore));
        oScore.setText(String.valueOf(opponentScore));

        mDate.setText(matchDate);


        Date matchDay = new Date(day,month,year);

        /*Creating the match object*/
        Match match = new Match(homeTeam,opponentTeam,homeScore,opponentScore,matchDay);

        /*added to the match*/
        addPlayedMatch(match);
    }



    /*Display all matches*/
    public void displayAllMatches(FlowPane flowPane){
        flowPane.getChildren().clear();
        Collections.sort(allMatches);

        for (Match match : allMatches){
            AnchorPane anchorPane = new AnchorPane();
            anchorPane.setId("match-holder");
            Label homeTeam = new Label(match.getHomeTeam());
            homeTeam.setId("team");
            homeTeam.setLayoutX(30);
            homeTeam.setLayoutY(10);
            Label opponentTeam = new Label(match.getOpponentTeam());
            opponentTeam.setId("team");
            opponentTeam.setLayoutX(250);
            opponentTeam.setLayoutY(10);
            Label homeScore = new Label(""+match.getHomeScore());
            homeScore.setId("score");
            homeScore.setLayoutY(60);
            homeScore.setLayoutX(130);
            Label opponentScore = new Label(""+match.getOpponentScore());
            opponentScore.setId("score");
            opponentScore.setLayoutX(330);
            opponentScore.setLayoutY(60);
            Label date = new Label(match.getDate().toString());
            date.setId("date");
            date.setLayoutY(130);
            date.setLayoutX(160);
            anchorPane.getChildren().addAll(homeTeam,opponentTeam,homeScore,opponentScore,date);
            flowPane.getChildren().addAll(anchorPane);
        }
    }

    public void selectMatchByDate(Date date,FlowPane flowPane){
        //need implementation
        flowPane.getChildren().clear();
        boolean matchChecker = false;
        for (Match match : allMatches){
            if (match.getDate().equals(date)){
                // need implementation
                AnchorPane anchorPane = new AnchorPane();
                anchorPane.setId("match-holder");
                Label homeTeam = new Label(match.getHomeTeam());
                homeTeam.setId("team");
                homeTeam.setLayoutX(30);
                homeTeam.setLayoutY(10);
                Label opponentTeam = new Label(match.getOpponentTeam());
                opponentTeam.setId("team");
                opponentTeam.setLayoutX(250);
                opponentTeam.setLayoutY(10);
                Label homeScore = new Label(""+match.getHomeScore());
                homeScore.setId("score");
                homeScore.setLayoutY(60);
                homeScore.setLayoutX(130);
                Label opponentScore = new Label(""+match.getOpponentScore());
                opponentScore.setId("score");
                opponentScore.setLayoutX(330);
                opponentScore.setLayoutY(60);
                Label mDate = new Label(match.getDate().toString());
                mDate.setId("date");
                mDate.setLayoutY(130);
                mDate.setLayoutX(160);
                anchorPane.getChildren().addAll(homeTeam,opponentTeam,homeScore,opponentScore,mDate);
                flowPane.getChildren().addAll(anchorPane);
                matchChecker = true;
            }
        }
        if (!matchChecker){
            System.out.println("No Match Found!");
        }
    }


}
