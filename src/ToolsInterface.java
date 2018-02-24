import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class ToolsInterface extends Application {
    // Program Assets
    private Scene primaryScene;

    // Team Generator Assets
    private TableView<Player> playerTable;
    private ObservableList<Player> players = FXCollections.observableArrayList();

    // Steel Division Assets
    private String mapVariant = "";

    // Intro Parent
    private Parent createIntroContent() {
        Pane root = new Pane();
        root.setPrefSize(999, 563);

        GameCard siegeCard = new GameCard("file:Rainbow6.jpg",
                900, 300, 333, 488, "Rainbow Six: Siege");
        GameCard forHonorCard = new GameCard("file:ForHonor.jpg",
                350, 200, 333, 488, "For Honor");
        GameCard steelDivisionCard = new GameCard("file:SteelDivision.jpg",
                1500, 300, 333, 488, "Steel Division");

        Button exit = new Button("EXIT");
        exit.getStyleClass().addAll("gc-button", "gc-button:hover");

        HBox banners = new HBox();

        banners.getChildren().addAll(siegeCard, forHonorCard, steelDivisionCard);
        root.getChildren().addAll(banners);

        siegeCard.setOnMouseClicked(e -> primaryScene.setRoot(createSiegeContent()));
        forHonorCard.setOnMouseClicked(e -> primaryScene.setRoot(createForHonorContent()));
        steelDivisionCard.setOnMouseClicked(e -> primaryScene.setRoot(createSteelDivisionContent()));

        return root;
    }

    // Siege Parent
    private Parent createSiegeContent() {
        Pane root = new Pane();

        ImageView bgIV = new ImageView(new Image("file:Rainbow6.jpg"));
        Rectangle bg = new Rectangle(1000, 563);
        bg.setOpacity(0.4);
        bg.setFill(Color.GRAY);

        // Menus
        VBox menu = new VBox(10);
        menu.setTranslateX(50);
        menu.setTranslateY(150);
        HBox typeMenu = new HBox(10);
        VBox rosterMenu = new VBox(10);
        rosterMenu.setTranslateX(300);
        rosterMenu.setTranslateY(150);

        // Menu Assets
        PrgmBttn chooseStrat = new PrgmBttn("CHOOSE STRAT");
        PrgmBttn chooseMap = new PrgmBttn("CHOOSE MAP");

        // Type Menu Assets
        PrgmSlctnBttn attack = new PrgmSlctnBttn("ATTACK", "medium");
        attack.setNewSize(95, 50);
        PrgmSlctnBttn defend = new PrgmSlctnBttn("DEFEND", "medium");
        defend.setNewSize(95, 50);

        // Output Assets
        PrgmLabel nation = new PrgmLabel("NATION");
        nation.setVisible(false);
        PrgmLabel weapon = new PrgmLabel("WEAPON");
        weapon.setVisible(false);
        PrgmLabel loadout = new PrgmLabel("LOADOUT");
        loadout.setVisible(false);

        // Roster Menu Assets
        PrgmTxtFld playerName = new PrgmTxtFld("Player Name");
        PrgmBttn addPlayer = new PrgmBttn("+");
        PrgmBttn removePlayer = new PrgmBttn("-");
        PrgmBttn clearTable = new PrgmBttn("CLEAR");

        TableColumn<Player, String> playerList = new TableColumn<>("Players");
        playerList.setMinWidth(225);
        playerList.setCellValueFactory(new PropertyValueFactory<>("name"));
        playerList.getStyleClass().add("table-column");

        TableColumn<Player, String> teamColumn = new TableColumn<>("Team 1");
        teamColumn.setMinWidth(225);
        teamColumn.setCellValueFactory(new PropertyValueFactory<>("team"));
        teamColumn.getStyleClass().add("table-column");

        playerTable = new TableView<>();
        playerTable.setPrefSize(450, 300);
        playerTable.setItems(players);
        playerTable.getColumns().add(playerList);
        playerTable.getColumns().add(teamColumn);
        playerTable.setTranslateX(525);
        playerTable.setTranslateY(150);
        playerTable.getStyleClass().add("table-view");

        // Parent Assets
        PrgmBttn back = new PrgmBttn("BACK");
        back.setTranslateX(50);
        back.setTranslateY(450);

        typeMenu.getChildren().addAll(attack, defend);
        rosterMenu.getChildren().addAll(playerName, addPlayer, removePlayer, clearTable);
        menu.getChildren().addAll(typeMenu, chooseStrat, chooseMap, nation, weapon, loadout);
        root.getChildren().addAll(bgIV, bg, menu, rosterMenu, playerTable, back);

        attack.setOnMouseClicked(e -> {
            attack.setSelected();
            defend.setDefault();
        });
        defend.setOnMouseClicked(e -> {
            defend.setSelected();
            attack.setDefault();
        });
        chooseStrat.setOnMouseClicked(e -> {
            boolean side = true;
            nation.setVisible(true);
            weapon.setVisible(true);
            loadout.setVisible(true);
            chooseNation(nation);
            if (attack.getSelected()) {
                side = true;
            }
            if (defend.getSelected()) {
                side = false;
            }
            chooseWeapon(weapon, nation.getText(), side);
            chooseLoadout(loadout);

        });
        chooseMap.setOnMouseClicked(e -> {
            nation.setVisible(true);
            weapon.setVisible(false);
            loadout.setVisible(false);
            attack.setDefault();
            defend.setDefault();
            chooseMap(nation, "siege");
        });
        addPlayer.setOnMouseClicked(e -> addPlayer(playerName));
        removePlayer.setOnMouseClicked(e -> removePlayer());
        clearTable.setOnMouseClicked(e -> clearTable(playerName));
        back.setOnMouseClicked(e -> primaryScene.setRoot(createIntroContent()));
        root.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ENTER:
                    addPlayer(playerName);
                    break;
                case DELETE:
                    removePlayer();
                    break;
            }
        });

        return root;
    }

    // For Honor Parent
    private Parent createForHonorContent() {
        Pane root = new Pane();

        ImageView bgIV = new ImageView("file:ForHonor.jpg");
        Rectangle bg = new Rectangle(1000, 563);
        bg.setOpacity(0.4);
        bg.setFill(Color.GRAY);

        VBox menu = new VBox(10);
        menu.setTranslateX(50);
        menu.setTranslateY(150);
        HBox typeMenu = new HBox(10);

        PrgmBttn chooseHero = new PrgmBttn("CHOOSE HERO");
        PrgmSlctnBttn dlcHero = new PrgmSlctnBttn("DLC", "large");
        PrgmBttn chooseMap = new PrgmBttn("CHOOSE MAP");
        PrgmLabel output = new PrgmLabel();
        output.setVisible(false);

        PrgmBttn back = new PrgmBttn("BACK");
        back.setTranslateX(50);
        back.setTranslateY(450);

        typeMenu.getChildren().addAll(dlcHero);
        menu.getChildren().addAll(typeMenu, chooseHero, chooseMap, output);
        root.getChildren().addAll(bgIV, bg, menu, back);

        chooseHero.setOnMouseClicked(e -> {
            output.setVisible(true);
            chooseHero(output, dlcHero.getSelected());
        });
        dlcHero.setOnMouseClicked(e -> dlcHero.setSelected());
        chooseMap.setOnMouseClicked(e -> {
            chooseMap(output, "honor");
            dlcHero.setDefault();
            output.setVisible(true);
        });
        back.setOnMouseClicked(e -> primaryScene.setRoot(createIntroContent()));

        return root;
    }

    // Steel Division Parent
    private Parent createSteelDivisionContent() {
        Pane root = new Pane();

        ImageView bgIV = new ImageView("file:SteelDivision.jpg");
        Rectangle bg = new Rectangle(1000, 563);
        bg.setOpacity(0.4);
        bg.setFill(Color.GRAY);

        VBox menu = new VBox(10);
        menu.setTranslateX(50);
        menu.setTranslateY(150);
        HBox variantMenu = new HBox(10);

        PrgmBttn chooseDeck = new PrgmBttn("CHOOSE DECK");
        PrgmBttn chooseMap = new PrgmBttn("CHOOSE MAP");
        PrgmSlctnBttn oneVersusOne = new PrgmSlctnBttn("1V1", "small");
        oneVersusOne.setNewSize(42, 40);
        PrgmSlctnBttn twoVersusTwo = new PrgmSlctnBttn("2V2", "small");
        twoVersusTwo.setNewSize(42, 40);
        PrgmSlctnBttn threeVersusThree = new PrgmSlctnBttn("3V3", "small");
        threeVersusThree.setNewSize(42, 40);
        PrgmSlctnBttn fourVersusFour = new PrgmSlctnBttn("4V4", "small");
        fourVersusFour.setNewSize(42, 40);
        PrgmLabel output = new PrgmLabel();
        output.setVisible(false);
        ImageView outputIV = new ImageView();
        outputIV.setVisible(false);
        outputIV.setTranslateX(350);
        outputIV.setTranslateY(150);
        PrgmBttn back = new PrgmBttn("BACK");
        back.setTranslateX(50);
        back.setTranslateY(450);

        variantMenu.getChildren().addAll(oneVersusOne, twoVersusTwo, threeVersusThree, fourVersusFour);
        menu.getChildren().addAll(chooseDeck, chooseMap, variantMenu, output);
        root.getChildren().addAll(bgIV, bg, menu, outputIV, back);

        chooseDeck.setOnMouseClicked(e -> {
            output.setVisible(true);
            oneVersusOne.setDefault();
            twoVersusTwo.setDefault();
            threeVersusThree.setDefault();
            fourVersusFour.setDefault();
            outputIV.setVisible(false);
            chooseDeck(output);
        });
        oneVersusOne.setOnMouseClicked(e -> {
            oneVersusOne.setSelected();
            twoVersusTwo.setDefault();
            threeVersusThree.setDefault();
            fourVersusFour.setDefault();
            mapVariant = "1v1";
        });
        twoVersusTwo.setOnMouseClicked(e -> {
            oneVersusOne.setDefault();
            twoVersusTwo.setSelected();
            threeVersusThree.setDefault();
            fourVersusFour.setDefault();
            mapVariant = "2v2";
        });
        threeVersusThree.setOnMouseClicked(e -> {
            oneVersusOne.setDefault();
            twoVersusTwo.setDefault();
            threeVersusThree.setSelected();
            fourVersusFour.setDefault();
            mapVariant = "2v2";
        });
        fourVersusFour.setOnMouseClicked(e -> {
            oneVersusOne.setDefault();
            twoVersusTwo.setDefault();
            threeVersusThree.setDefault();
            fourVersusFour.setSelected();
            mapVariant = "4v4";
        });
        chooseMap.setOnMouseClicked(e -> {
            output.setVisible(true);
            chooseMap(output, "steel");
            outputIV.setVisible(true);
            displayMap(outputIV, mapVariant, output.getText());
        });
        back.setOnMouseClicked(e -> primaryScene.setRoot(createIntroContent()));

        return root;
    }

    // Program Methods
    private void chooseMap(PrgmLabel label, String game) {
        String[] mapArrayR6 = new String[]{"Bank", "Clubhouse", "Oregon", "Kafe", "Consulate", "Chalet", "Border",
                "Coastline", "Skyscraper", "Kanal", "Hereford", "University", "House", "Plane", "Theme Park", "Tower"};
        String[] mapArray4H = new String[]{"Citadel Gate", "Cathedral", "The Shard", "Village", "Forge",
                "Sentinel", "Sancuary Bridge", "Forest", "Tower Ruin", "Overwatch", "Temple Garden", "River Fort",
                "Canyon", "High Fort", "Shipyard", "Viking Village"};
        String[] mapArraySD = new String[]{"Bois de Limors", "Carpiquet", "Caumont L'Evente", "Cheux",
                "Colleville", "Colombelles", "Cote 112", "Merderet", "Mont Ormel", "Odon River", "Odon", "Omaha",
                "Pegasus Bridge", "Pointe du Hoc", "Saint-Mere-Eglise"};
        String map;
        switch (game) {
            case "siege":
                map = mapArrayR6[(int) (Math.random() * mapArrayR6.length)];
                break;
            case "honor":
                map = mapArray4H[(int) (Math.random() * mapArray4H.length)];
                break;
            default:
                map = mapArraySD[(int) (Math.random() * mapArraySD.length)];
                break;
        }
        label.setText(map);
    }

    // Team Generator Methods
    private void addPlayer(PrgmTxtFld playerName) {
        if (!playerName.getText().equals("")) {
            Player player = new Player(playerName.getText(), (int) (Math.random() * 2 + 1));
            playerTable.getItems().add(player);
            playerName.setText("");
        }
    }

    private void removePlayer() {
        ObservableList<Player> playerSelected = playerTable.getSelectionModel().getSelectedItems();
        playerSelected.forEach(players::remove);
    }

    private void clearTable(PrgmTxtFld playerName) {
        ObservableList<Player> allPlayers = playerTable.getItems();
        players.remove(0, allPlayers.size());
        playerName.setText("");
    }

    // Siege Methods
    private void chooseNation(PrgmLabel label) {
        String[] nations = new String[]{"SAS", "FBI", "GIGN", "GSG9", "SPETSNAZ"};
        String nation = nations[(int) (Math.random() * nations.length)];
        label.setText(nation);
    }

    private void chooseWeapon(PrgmLabel label, String nation, boolean side) {
        String[] atcWeapons1 = new String[]{"Rifle", "Shotgun", "Pistol", "Shield"};
        String[] atcWeapons2 = new String[]{"Rifle", "Shotgun", "Pistol"};
        String[] atcWeapons3 = new String[]{"DMR", "Rifle", "Shotgun", "Pistol", "Shield"};
        String[] atcWeapons4 = new String[]{"Rifle", "LMG", "Shotgun", "Pistol"};
        String[] defWeapons = new String[]{"SMG", "Shotgun", "Pistol"};
        String weapon;

        if (side) {
            switch (nation) {
                case "SAS":
                    weapon = atcWeapons1[(int) (Math.random() * atcWeapons1.length)];
                    label.setText(weapon);
                    break;
                case "FBI":
                    weapon = atcWeapons2[(int) (Math.random() * atcWeapons2.length)];
                    label.setText(weapon);
                    break;
                case "GIGN":
                    weapon = atcWeapons3[(int) (Math.random() * atcWeapons3.length)];
                    label.setText(weapon);
                    break;
                case "GSG9":
                    weapon = atcWeapons4[(int) (Math.random() * atcWeapons4.length)];
                    label.setText(weapon);
                    break;
                case "SPETSNAZ":
                    weapon = atcWeapons4[(int) (Math.random() * atcWeapons4.length)];
                    label.setText(weapon);
                    break;
            }
        } else {
            weapon = defWeapons[(int) (Math.random() * defWeapons.length)];
            label.setText(weapon);
        }
    }

    private void chooseLoadout(PrgmLabel label) {
        String[] loadouts = new String[]{"Lethal", "Tactical"};
        String loadout = loadouts[(int) (Math.random() * loadouts.length)];
        label.setText(loadout);
    }

    // For Honor Methods
    private void chooseHero(PrgmLabel label, boolean dlc) {
        String[] heroArray = new String[]{"Warden", "Raider", "Kensei", "Conquerer", "Warlord", "Shugoki",
                "Peacekeeper", "Berserker", "Orochi", "Lawbringer", "Valkyrie", "Nobushi"};
        String[] dlcArray = new String[]{"Centurion", "Highlander", "Shinobi", "Gladiator", "Shaman", "Aramusha"};
        String hero;
        if (!dlc) {
            hero = heroArray[(int) (Math.random() * heroArray.length)];
        } else {
            hero = dlcArray[(int) (Math.random() * dlcArray.length)];
        }
        label.setText(hero);
    }

    // Steel Division Methods
    private void chooseDeck(PrgmLabel label) {
        String[] deckArray = new String[]{"1st Airborne", "2nd Infantry", "3rd Armored", "4th Armored", "2nd SSB"};
        String deck = deckArray[(int) (Math.random() * deckArray.length)];
        label.setText(deck);
    }

    private void displayMap(ImageView iv, String type, String map) {
        Image tempImage = new Image("file:SteelDivisionImages/" + map + " " + type + ".jpg");
        iv.setPreserveRatio(true);
        iv.setFitWidth(600);
        iv.setImage(tempImage);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        primaryScene = new Scene(createIntroContent());
        primaryScene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                primaryScene.setRoot(createIntroContent());
            }
        });
        primaryScene.getStylesheets().add("ToolTheme.css");
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Game Tools");
        primaryStage.show();
    }
}
