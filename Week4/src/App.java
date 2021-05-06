import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.util.concurrent.ThreadLocalRandom;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) {
        //classWork(window);
        //assignment1(window);
        //assignment2(window);
        assignment3(window);
    }

    private void assignment3(Stage window) {

        window.setWidth(350);
        window.setHeight(400);
        window.setTitle("Tic-tac-toe");

        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(5));
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        int TICTACTOE_NUM = 3;

        Label currentTurnLabel = new Label("Current turn: ");
        GridPane.setConstraints(currentTurnLabel, 0, TICTACTOE_NUM+1);

        Label currentTurn = new Label("X");
        GridPane.setConstraints(currentTurn, 1, TICTACTOE_NUM+1);

        Button restartButton  = new Button("Restart");
        GridPane.setConstraints(restartButton, 2, TICTACTOE_NUM+1);
        restartButton.setVisible(false);

        restartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    assignment3(window);
                }
                catch (Exception e)
                {
                    System.out.print(e.getMessage());
                }
            }
        });

        gridPane.getChildren().addAll(currentTurn, currentTurnLabel, restartButton);

        Button[][] buttons = new Button[TICTACTOE_NUM][TICTACTOE_NUM];
        for (int x = 0; x < TICTACTOE_NUM; x++) {
            for (int y = 0; y < TICTACTOE_NUM; y++) {
                buttons[x][y] = new Button("_");
                buttons[x][y].setPrefSize(100, 100);
                buttons[x][y].setFont(new Font(30));
                GridPane.setConstraints(buttons[x][y], x, y);
                gridPane.getChildren().addAll(buttons[x][y]);
            }
        }

        for (int x = 0; x < TICTACTOE_NUM; x++) {
            for (int y = 0; y < TICTACTOE_NUM; y++) {
                //how come?
                int finalX = x;
                int finalY = y;

                final int[] values = new int[2];

                buttons[x][y].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {

                        if (buttons[finalX][finalY].getText().equals("_")) {
                            buttons[finalX][finalY].setText("X");
                        }

                        if (determineWin(buttons, "X", TICTACTOE_NUM)) {
                            currentTurnLabel.setText("");
                            currentTurn.setText("WIN!");

                            restartButton.setVisible(true);
                            return;
                        }

                        //AI starts

                        currentTurn.setText("O");

//                        Random random = new Random();
//                        try {
//                            TimeUnit.SECONDS.sleep(3);
//
//                        }catch (Exception e)
//                        {
//                            System.out.print(e.getMessage());
//                        }
                        while(true)
                        {
                            values[0] =  ThreadLocalRandom.current().nextInt(0, TICTACTOE_NUM);
                            values[1] =  ThreadLocalRandom.current().nextInt(0, TICTACTOE_NUM);

                            System.out.print(values[0] + "" + values[1]);

                            int index = 0;
                            for(int x = 0; x < TICTACTOE_NUM; x++)
                            {
                                for(int y = 0; y < TICTACTOE_NUM; y++)
                                {
                                    if (!buttons[x][y].getText().equals("_"))
                                    {
                                        index++;
                                    }

                                    if (index == 9) {
                                        currentTurnLabel.setText("");
                                        currentTurn.setText("tie!");
                                        restartButton.setVisible(true);
                                        return;
                                    }
                                }
                            }

                            if(buttons[values[0]][values[1]].getText().equals("_")) {
                                buttons[values[0]][values[1]].setText("O");
                                break;
                            }
                        }

                        if (determineWin(buttons, "O", TICTACTOE_NUM)) {

                            currentTurn.setText("lost...");

                            restartButton.setVisible(true);
                            return;
                        }

                        currentTurn.setText("X");




                    }
                });
            }
        }



        Scene scene = new Scene(gridPane);
        window.setScene(scene);

        window.show();

    }

    private boolean determineWin(Button[][] buttons, String side, int ticktacktoeNum) {
        //side is X or O

        String[][] strButtons = new String[ticktacktoeNum][ticktacktoeNum];

        for (int x = 0; x < ticktacktoeNum; x++) {
            for (int y = 0; y < ticktacktoeNum; y++) {
                strButtons[x][y] = buttons[x][y].getText();
            }
        }

        int index;
        //for col
        for (int x = 0; x < ticktacktoeNum; x++) {
            index = 0;

            for (int y = 0; y < ticktacktoeNum; y++) {
                if (strButtons[x][y].equals(side)) {
                    index++;

                    if (index == 3)
                        return true;
                } else
                    break;
            }

        }


        //for row
        for (int y = 0; y < ticktacktoeNum; y++) {
            index = 0;
            for (int x = 0; x < ticktacktoeNum; x++) {
                if (strButtons[x][y].equals(side)) {
                    index++;

                    if (index == 3)
                        return true;
                } else
                    break;
            }
        }

        //for dia, up to down
        for (int x = 0; x < ticktacktoeNum; x++) {
            index = 0;
            if (strButtons[x][x].equals(side)) {
                index++;

                if (index == 3)
                    return true;
            } else
                break;
        }


        index = 0;
        //for dia, down to up
        for (int x = 0; x < ticktacktoeNum; x++) {
            if (strButtons[x][ticktacktoeNum - (x + 1)].equals(side)) {
                index++;

                if (index == 3)
                    return true;
            } else
                break;
        }

        return false;
    }



    private void assignment2(Stage window)
    {
        window.setHeight(300);
        window.setWidth(420);
        window.setTitle("Car rental");

        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);
        gridPane.setHgap(8);

        Label daysRentedLabel = new Label("Number of days rented");
        GridPane.setConstraints(daysRentedLabel, 0, 0);

        TextField amountDaysRented = new TextField();
        GridPane.setConstraints(amountDaysRented, 1, 0);

        Label kmDrivenLabel = new Label("Number of kilometers driven");
        GridPane.setConstraints(kmDrivenLabel, 0, 1);

        TextField amountKMDriven = new TextField();
        GridPane.setConstraints(amountKMDriven, 1, 1);

        CheckBox isTankFilled = new CheckBox("Fuel tank not full when returned");
        GridPane.setConstraints(isTankFilled, 0, 3);

        Label litresLabel = new Label("Number of liters: ");
        GridPane.setConstraints(litresLabel, 0, 4);

        TextField amountOfLiters = new TextField();
        GridPane.setConstraints(amountOfLiters, 1, 4);

        Button calculateButton = new Button("Calculate payment");
        GridPane.setConstraints(calculateButton, 1, 5);

        Label amountDueLabel = new Label("Amount due: ");
        GridPane.setConstraints(amountDueLabel, 0, 6);

        Label amountDueOutput = new Label("");
        GridPane.setConstraints(amountDueOutput, 1, 6);

        calculateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try
                {
                    int daysRented = Integer.parseInt(amountDaysRented.getText());
                    int kmDriven = Integer.parseInt(amountKMDriven.getText());
                    boolean tankFull = isTankFilled.isSelected();
                    int liters = Integer.parseInt(amountOfLiters.getText());

                    double amountDue = (daysRented * 45) + (Math.max(0, ((daysRented * 100) - kmDriven) * 0.25));

                    if (tankFull)
                        amountDue += liters;

                    amountDueOutput.setText(String.format("%.2f", amountDue));


                }catch (Exception e)
                {
                    System.out.print(e.getMessage());
                }
            }
        });

        gridPane.getChildren().addAll(daysRentedLabel, amountDaysRented, kmDrivenLabel, amountKMDriven, isTankFilled, litresLabel, amountOfLiters, calculateButton, amountDueLabel, amountDueOutput);

        Scene scene = new Scene(gridPane);
        window.setScene(scene);

        window.show();
    }

    private void assignment1(Stage window)
    {
        window.setHeight(160);
        window.setWidth(300);
        window.setTitle("Currency converter");

        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);
        gridPane.setHgap(8);

        Label amountEuroLabel = new Label("Amount €: ");
        GridPane.setConstraints(amountEuroLabel, 0, 0);

        TextField amountInput = new TextField();
        amountInput.setPromptText("0");
        GridPane.setConstraints(amountInput, 1, 0);

        Button convertButton = new Button("Convert Euro to Dollar");
        GridPane.setConstraints(convertButton, 1, 1);

        Label amountDollarLabel = new Label("Amount $: ");
        GridPane.setConstraints(amountDollarLabel, 0, 3);

        Label amountOutput = new Label("");
        GridPane.setConstraints(amountOutput, 1, 3);

        convertButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try
                {
                    int euroAmount = Integer.parseInt(amountInput.getText());
                    amountOutput.setText(String.format("%.2f", euroAmount * 1.18));
                }
                catch(Exception e)
                {
                    System.out.print(e.getMessage());
                }
            }
        });

        gridPane.getChildren().addAll(amountEuroLabel, amountInput, convertButton, amountDollarLabel, amountOutput);

        Scene scene = new Scene(gridPane);
        window.setScene(scene);

        window.show();
        //window.close();
    }


    private void classWork(Stage window)
    {
        window.setHeight(250);
        window.setWidth(300);
        window.setTitle("My First App");

        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);
        gridPane.setHgap(8);

        Label userLabel = new Label("Username");
        GridPane.setConstraints(userLabel, 0, 0);

        TextField userInput = new TextField();
        userInput.setPromptText("username");
        GridPane.setConstraints(userInput, 1, 0);

        Label passwordLabel = new Label();
        passwordLabel.setText("Password:");
        GridPane.setConstraints(passwordLabel, 0, 1);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("password");
        GridPane.setConstraints(passwordField, 1, 1);

        Button loginButton = new Button("Log in");
        GridPane.setConstraints(loginButton, 1, 2);

        gridPane.getChildren().addAll(userLabel, userInput, passwordLabel, passwordField, loginButton);

        Scene scene = new Scene(gridPane);
        window.setScene(scene);

        //Anonymous in a class
        window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                windowEvent.consume();
            }
        });

        window.show();
        //window.close();
    }
}
