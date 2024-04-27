package application;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/*public class Main extends Application {
	Stage primaryStage, stage2, stage3,stage4;
	int[] batteries,lights;
	static int[] lcsResult;
	TextField textFields;
	 Button btnnext;
	@Override
	public void start(Stage primaryStage) {
		Label title = new Label("Welcome To MAX LED LIGHTING ");
		title.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 25));
		Image image1 = new Image("C:\\Users\\user\\Desktop\\Afnan\\test4\\p1.png");
		ImageView img = new ImageView(image1);
		img.setFitWidth(400);
		img.setFitHeight(400);
		Button btn1 = new Button("Get The Numbers From File");
		btn1.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 15));
		Button btn2 = new Button("Enter to write numbers ");
		btn2.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 15));
		Button btn3 = new Button("Next");
		btn3.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 15));
		btn3.setOnAction(e -> {
			primaryStage.close();
			showstage3();
		});
		
			    	 btn1.setOnAction(e -> {
			             FileChooser fileChooser = new FileChooser();
			             fileChooser.setTitle("Select File");
			             File selectedFile = fileChooser.showOpenDialog(primaryStage);

			             if (selectedFile != null) {
			                 try {
			                     int upper = readIntegerFromFile(selectedFile);
			                     // Use the instance variable instead of local variable
			                     batteries = buildArrayFrom1Tonum(upper);
			                     lights = readIntArrayFromFile(selectedFile);
			                     System.out.println("Batteries: " + java.util.Arrays.toString(batteries));
			                     System.out.println("Lights: " + java.util.Arrays.toString(lights));
			                 } catch (IOException e1) {
			                     e1.printStackTrace();
			                 }
			             }
			         });
			        
		
		
		VBox vBox = new VBox(40);
		vBox.getChildren().addAll(title, img, btn1, btn2, btn3);
		btn2.setOnAction(e -> {
			showstage2();

		});

		vBox.setStyle("-fx-background:RED");
		vBox.setAlignment(Pos.CENTER);
		Scene scene1 = new Scene(vBox, 800, 800);
		primaryStage.setScene(scene1);
		primaryStage.show();

	}

	private int readIntegerFromFile(File file) throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line = reader.readLine();
			return Integer.parseInt(line.trim());
		}
	}

	private int[] buildArrayFrom1Tonum(int n) {
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = i + 1;
		}
		return arr;
	}

	private int[] readIntArrayFromFile(File file) throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			// Skip the first line
			reader.readLine();

			// Read the second line and convert it to an array of integers
			String line = reader.readLine();
			String[] tokens = line.split(",");
			int[] array = new int[tokens.length];
			for (int i = 0; i < tokens.length; i++) {
				array[i] = Integer.parseInt(tokens[i]);
			}

			return array;
		}
	}

	private void createSmallTextFields(HBox smallTextFieldsHBox, int n, Label infoLabel) {
	    List<TextField> textFields = new ArrayList<>();

	    smallTextFieldsHBox.getChildren().removeIf(node -> node instanceof TextField);

	    for (int i = 0; i < n; i++) {
	        TextField smallTextField = new TextField();
	        smallTextField.setPromptText("Num " + (i + 1));
	        smallTextField.setMaxWidth(70); // Set a wider maximum width

	        final int index = i;
	        lights = new int[n];
	        smallTextField.textProperty().addListener((observable, oldValue, newValue) -> {
	            if (!newValue.isEmpty()) {
	                try {
	                    int enteredNumber = Integer.parseInt(newValue);

	                    if (enteredNumber <= 0 || enteredNumber > n) {
	                        smallTextField.setText(oldValue);
	                        showError(infoLabel,
	                                "Invalid input: Please enter a unique integer number between 1 and " + n + ".");
	                    } else if (textFields.stream().anyMatch(field ->
	                            field != smallTextField && !field.getText().isEmpty() &&
	                            Integer.parseInt(field.getText()) == enteredNumber)) {
	                        smallTextField.setText(oldValue);
	                        showError(infoLabel,
	                                "Duplicate input: Number " + enteredNumber + " has already been entered. Please enter a unique number.");
	                    } else {
	                        lights[index] = enteredNumber;  // Convert to integer for storage
	                    }
	                } catch (NumberFormatException e) {
	                    smallTextField.setText(oldValue);
	                    showError(infoLabel, "Invalid input: Please enter a valid integer number.");
	                }
	            }
	        });

	        textFields.add(smallTextField);
	        smallTextFieldsHBox.getChildren().add(smallTextField);
	    }
	    btnnext.setOnAction(e -> {
	        boolean allFieldsFilled = true;
	        for (TextField field : textFields) {
	            if (field.getText().isEmpty()) {
	                allFieldsFilled = false;
	                break;
	            }
	        }

	        System.out.println("allFieldsFilled: " + allFieldsFilled);

	        if (allFieldsFilled) {
	            showstage3();
	        } else {
	            showError(infoLabel, "Please fill in all the fields before go to next Stage");
	        }
	    });

	       
	

	    // Add a listener to check for empty fields when leaving the stage
	    smallTextFieldsHBox.parentProperty().addListener((observable, oldParent, newParent) -> {
	        if (newParent == null) {
	            // User is leaving the stage or submitting
	            if (textFields.stream().anyMatch(field -> field.getText().isEmpty())) {
	                showError(infoLabel, "Please fill in all the fields before proceeding.");
	                
	            }
	        }
	    });
	}






	private void showError(Label infoLabel, String message) {
		infoLabel.setText(message);
	}
	public void showstage2() {
	    stage2 = new Stage();
	    VBox mainVBox = new VBox(10);
	    mainVBox.setPadding(new Insets(10, 10, 10, 10));
	    HBox inputHBox = new HBox(10);
	    Label label = new Label("Enter a number (n):");
	    TextField nTextField = new TextField();
	    inputHBox.getChildren().addAll(label, nTextField);

	    HBox smallTextFieldsHBox = new HBox(5);
	    Label infoLabel = new Label("Empty boxes only accept unique integer numbers greater than zero.");
	    smallTextFieldsHBox.getChildren().add(infoLabel);

	    btnnext = new Button("Next");
	    mainVBox.getChildren().addAll(inputHBox, smallTextFieldsHBox, btnnext);
	   

	    nTextField.setOnAction(event -> {
	        try {
	            if (!nTextField.getText().isEmpty() && Integer.parseInt(nTextField.getText()) > 0) {
	                // Move the code inside this block
	                int n = Integer.parseInt(nTextField.getText());
	                batteries = buildArrayFrom1Tonum(n);
	                createSmallTextFields(smallTextFieldsHBox, n, infoLabel);
	            } else {
	                showError(infoLabel, "Please enter a valid number greater than zero.");
	            }
	        } catch (NumberFormatException ex) {
	            showError(infoLabel, "Please enter a valid number.");
	        }
	    });

	    Scene scene2 = new Scene(mainVBox, 800, 800);
	    stage2.setScene(scene2);
	    stage2.showAndWait();
	}

	public void showstage3() {
		stage3 = new Stage();
	Label titel2=new Label("Expected Result");
	titel2.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 25));
	Button btnnext=new Button("next");
	btnnext.setStyle("-fx-background-color:pink;");
	btnnext.setOnAction(e->{
		showStage4();
	});
	ScrollPane scrollPane = new ScrollPane();
    BorderPane paneb = new BorderPane();
    Pane pane = new Pane();
    paneb.setCenter(pane);
    paneb.setTop(titel2);
    paneb.setRight(btnnext);
    scrollPane.setContent(paneb);
    Scene scene = new Scene(scrollPane, 800, 400);
    
    // Create graphical representation based on LCS
    drawConnections(pane);

   stage3.setScene(scene);
  stage3.showAndWait();
	
}
	public void showStage4() {
		stage4=new Stage();
		 // Append zero at the beginning of both arrays
        int[] xWithZero = new int[batteries.length + 1];
        int[] yWithZero = new int[lights.length + 1];
        System.arraycopy(batteries, 0, xWithZero, 1, batteries.length);
       System.arraycopy(lights, 0, yWithZero, 1, lights.length);

        int[][] dpTable = calculateDPTable(xWithZero, yWithZero);
        char[][] trackingTable = calculateTrackingTable(xWithZero, yWithZero, dpTable);

        VBox dpVBox = createTableVBox("DP Table", xWithZero, yWithZero, dpTable);
        VBox trackingVBox = createTableVBox2("Tracking Table", xWithZero, yWithZero, trackingTable);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(new VBox(dpVBox, trackingVBox));

        Scene scene4 = new Scene(scrollPane, 800, 400);

        stage4.setScene(scene4);
        stage4.show();
    }

		 private int[][] calculateDPTable(int[] x, int[] y) {
		        int m = x.length;
		        int n = y.length;
		        int[][] dpTable = new int[m + 1][n + 1];
		     // Initialize the first row and column with zeros
	            for (int i = 0; i <= m; i++) {
	                dpTable[i][0] = 0;
	            }

	            for (int j = 0; j <= n; j++) {
	                dpTable[0][j] = 0;
	            }

		        // DP table creation
		        for (int i = 1; i <= m; i++) {
		            for (int j = 1; j <= n; j++) {
		                if (x[i - 1] == y[j - 1]) {
		                    dpTable[i][j] = dpTable[i - 1][j - 1] + 1;
		                } else {
		                    dpTable[i][j] = Math.max(dpTable[i - 1][j], dpTable[i][j - 1]);
		                }
		            }
		        }

		        return dpTable;
		    }
		

    private VBox createTableVBox(String tableName, int[] x, int[] y, int[][] table) {
        VBox vBox = new VBox(10);
        Label label = new Label(tableName);
        TextArea textArea = createTableTextArea(x, y, table);

        vBox.getChildren().addAll(label, textArea);
        return vBox;
    }

    private VBox createTableVBox2(String tableName, int[] x, int[] y, char[][] table) {
        VBox vBox = new VBox(10);
        Label label = new Label(tableName);
        TextArea textArea = createTrackingTableTextArea(x, y, table);

        vBox.getChildren().addAll(label, textArea);
        return vBox;
    }


    private char[][] calculateTrackingTable(int[] x, int[] y, int[][] dpTable) {
        int m = x.length;
        int n = y.length;
        char[][] trackingTable = new char[m][n];

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (x[i] == y[j]) {
                    trackingTable[i][j] = 'R'; // 'R' represents Right
                } else if (dpTable[i - 1][j] >= dpTable[i][j - 1]) {
                    trackingTable[i][j] = 'U'; // 'U' represents up
                } else {
                    trackingTable[i][j] = 'L'; // 'L' represents left
                }
            }
        }

        return trackingTable;
    }

private VBox createTableVBox(String tableName, int[] x, int[] y, char[][] table) {
    VBox vBox = new VBox(10);
    Label label = new Label(tableName);
    TextArea textArea = createTrackingTableTextArea(x, y, table);

    vBox.getChildren().addAll(label, textArea);
    return vBox;
}

private TextArea createTrackingTableTextArea(int[] x, int[] y, char[][] trackingTable) {
    TextArea textArea = new TextArea();
    StringBuilder content = new StringBuilder();

    int numRows = x.length;
    int numCols = y.length;

    // Header (first row)
    content.append("\t");
    for (int col : y) {
        content.append(col).append("\t");
    }
    content.append("\n");

    // Table body
    for (int row = 0; row < numRows; row++) {
        // First column (array x)
        content.append(x[row]).append("\t");

        for (int col = 0; col < numCols; col++) {
            content.append(trackingTable[row][col]).append("\t");
        }
        content.append("\n");
    }

    textArea.setText(content.toString());
    textArea.setEditable(false);

    return textArea;
}


private TextArea createTableTextArea(int[] x, int[] y, int[][] table) {
    TextArea textArea = new TextArea();
    StringBuilder content = new StringBuilder();

    int numRows = x.length;
    int numCols = y.length;

    // Header (first row)
    content.append("\t");
    for (int col : y) {
        content.append(col).append("\t");
    }
    content.append("\n");

    // Table body
    for (int row = 0; row < numRows; row++) {
        // First column (array x)
        content.append(x[row]).append("\t");

        for (int col = 0; col < numCols; col++) {
            content.append(table[row][col]).append("\t");
        }
        content.append("\n");
    }

    textArea.setText(content.toString());
    textArea.setEditable(false);

    return textArea;

    }

	
private void drawConnections(Pane pane) {
	lcsResult=calculateLCS(batteries,lights);
    double xOffsetBattery = 50;
    double xOffsetLED = 50;
    double yOffset = 100;

    Map<Integer, Double> batteryXMap = new HashMap<>();

    
    for (int i = 0; i < batteries.length; i++) {
        double batteryX = xOffsetBattery + i * 120;

        // Draw battery rectangle
        Rectangle battery = new Rectangle(batteryX, yOffset, 50, 30);
        battery.setFill(Color.GRAY);
        pane.getChildren().add(battery);

        // Draw battery label
        Label batteryLabel = new Label(Integer.toString(batteries[i]));
        batteryLabel.setLayoutX(batteryX + 20);
        batteryLabel.setLayoutY(yOffset - 15);
        pane.getChildren().add(batteryLabel);

        batteryXMap.put(batteries[i], batteryX + 25); // Store the center X-coordinate of the battery
    }

    // Draw all LEDs and connect only LEDs in the LCS result to their corresponding batteries
 // Inside the drawConnections method
    for (int i = 0; i < lights.length; i++) {
        double ledX = xOffsetLED + i * 120;
        double ledY = yOffset + 180;

        //ImageView ledImage = new ImageView(new Image("(\"C:\\\\Users\\\\user\\\\Desktop\\\\Afnan\\\\tester6\\\\393207678_1142477426720504_4159062909525850963_n.jpg"));
        ImageView ledImage = new ImageView(new Image("C:\\Users\\user\\Desktop\\Afnan\\test4\\src\\393207678_1142477426720504_4159062909525850963_n.jpg"));
        ImageView ledImage2 = new ImageView(new Image("C:\\Users\\user\\Desktop\\Afnan\\test4\\src\\410606711_272883355767079_2558702523097988620_n.jpg"));
        ledImage.setFitWidth(50);
        ledImage.setFitHeight(50);
        ledImage.setLayoutX(ledX - 15);
        ledImage.setLayoutY(ledY - 15);

        ledImage2.setFitWidth(50);
        ledImage2.setFitHeight(50);
        ledImage2.setLayoutX(ledX - 15);
        ledImage2.setLayoutY(ledY - 15);

      
        System.out.println("Light " + lights[i] + " Connection Valid: " + isConnectionValid(lights[i]));

        if (isConnectionValid(lights[i])) {
            double batteryX = batteryXMap.get(lights[i]);
            drawWire(pane, ledX, ledY, batteryX, yOffset + 40);
            ledImage.setBlendMode(BlendMode.OVERLAY);
            pane.getChildren().addAll(ledImage2);
        } else {
            ledImage2.setBlendMode(BlendMode.OVERLAY);
            pane.getChildren().addAll(ledImage2);
        }

        Label ledLabel = new Label(Integer.toString(lights[i]));
        ledLabel.setLayoutX(ledX - 5);
        ledLabel.setLayoutY(ledY - 20);

        if (isConnectionValid(lights[i])) {
            pane.getChildren().addAll(ledLabel);
        } else {
            pane.getChildren().addAll(ledImage, ledLabel);
        }

        ledLabel.toFront();
    }
}

private boolean isConnectionValid(int light) {
    // Check if the LED is in the LCS result array
    for (int lcsLight : lcsResult) {
        if (lcsLight == light) {
            return true;
        }
    }
    return false;
}

private void drawWire(Pane pane, double startX, double startY, double endX, double endY) {
    // Draw a Bezier curve connecting the LED to the battery
    double controlPointX = (startX + endX) / 2;
    Line wire1 = new Line(startX, startY, controlPointX, startY - 50);
    Line wire2 = new Line(controlPointX, startY - 50, controlPointX, endY + 50);
    Line wire3 = new Line(controlPointX, endY + 50, endX, endY);
    wire1.setStroke(Color.RED);
    wire2.setStroke(Color.RED);
    wire3.setStroke(Color.RED);
    wire1.setStrokeWidth(4.0);
    wire2.setStrokeWidth(4.0);
    wire3.setStrokeWidth(4.0);
    pane.getChildren().addAll(wire1, wire2, wire3);
}


private int[] calculateLCS(int[] x, int[] y) {
    int[][] dpTable = calculateDPTable(x,y);

    // LCS result calculation
    int m = x.length;
    int n = y.length;
    int index = dpTable[m][n];
    int[] result = new int[index];
    int i = m, j = n;

    while (i > 0 && j > 0) {
        if (x[i - 1] == y[j - 1]) {
            result[index - 1] = x[i - 1];
            i--;
            j--;
            index--;
        } else if (dpTable[i - 1][j] > dpTable[i][j - 1]) {
            i--;
        } else {
            j--;
        }
    }

    return result;
}



	public static void main(String[] args) {
		launch(args);
	}
}
*/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application {
	Stage primaryStage, stage2, stage3,stage4;
	int[] batteries,lights;
	static int[] lcsResult;
	TextField textFields;
	 Button btnnext;
	@Override
	public void start(Stage primaryStage) {
		Label title = new Label("Welcome To MAX LED LIGHTING ");
		title.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 25));
		Image image1 = new Image("C:\\Users\\user\\Desktop\\Afnan\\test4\\p1.png");
		ImageView img = new ImageView(image1);
		img.setFitWidth(400);
		img.setFitHeight(400);
		Button btn1 = new Button("Get The Numbers From File");
		btn1.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 15));
		Button btn2 = new Button("Enter to write numbers ");
		btn2.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 15));
		Button btn3 = new Button("Next");
		btn3.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 15));
		btn3.setOnAction(e -> {
			primaryStage.close();
			showstage3();
		});
		
			    	 btn1.setOnAction(e -> {
			             FileChooser fileChooser = new FileChooser();
			             fileChooser.setTitle("Select File");
			             File selectedFile = fileChooser.showOpenDialog(primaryStage);

			             if (selectedFile != null) {
			                 try {
			                     int upper = readIntegerFromFile(selectedFile);
			                     // Use the instance variable instead of local variable
			                     batteries = buildArrayFrom1Tonum(upper);
			                     lights = readIntArrayFromFile(selectedFile);
			                     System.out.println("Batteries: " + java.util.Arrays.toString(batteries));
			                     System.out.println("Lights: " + java.util.Arrays.toString(lights));
			                 } catch (IOException e1) {
			                     e1.printStackTrace();
			                 }
			             }
			         });
			        
		
		
		VBox vBox = new VBox(40);
		vBox.getChildren().addAll(title, img, btn1, btn2, btn3);
		btn2.setOnAction(e -> {
			showstage2();

		});

		vBox.setStyle("-fx-background:RED");
		vBox.setAlignment(Pos.CENTER);
		Scene scene1 = new Scene(vBox, 800, 800);
		primaryStage.setScene(scene1);
		primaryStage.show();

	}

	private int readIntegerFromFile(File file) throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line = reader.readLine();
			return Integer.parseInt(line.trim());
		}
	}

	private int[] buildArrayFrom1Tonum(int n) {
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = i + 1;
		}
		return arr;
	}

	private int[] readIntArrayFromFile(File file) throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			// Skip the first line
			reader.readLine();

			// Read the second line and convert it to an array of integers
			String line = reader.readLine();
			String[] tokens = line.split(",");
			int[] array = new int[tokens.length];
			for (int i = 0; i < tokens.length; i++) {
				array[i] = Integer.parseInt(tokens[i]);
			}

			return array;
		}
	}

	private void createSmallTextFields(HBox smallTextFieldsHBox, int n, Label infoLabel) {
	    List<TextField> textFields = new ArrayList<>();

	    smallTextFieldsHBox.getChildren().removeIf(node -> node instanceof TextField);

	    for (int i = 0; i < n; i++) {
	        TextField smallTextField = new TextField();
	        smallTextField.setPromptText("Num " + (i + 1));
	        smallTextField.setMaxWidth(70); // Set a wider maximum width

	        final int index = i;
	        lights = new int[n];
	        smallTextField.textProperty().addListener((observable, oldValue, newValue) -> {
	            if (!newValue.isEmpty()) {
	                try {
	                    int enteredNumber = Integer.parseInt(newValue);

	                    if (enteredNumber <= 0 || enteredNumber > n) {
	                        smallTextField.setText(oldValue);
	                        showError(infoLabel,
	                                "Invalid input: Please enter a unique integer number between 1 and " + n + ".");
	                    } else if (textFields.stream().anyMatch(field ->
	                            field != smallTextField && !field.getText().isEmpty() &&
	                            Integer.parseInt(field.getText()) == enteredNumber)) {
	                        smallTextField.setText(oldValue);
	                        showError(infoLabel,
	                                "Duplicate input: Number " + enteredNumber + " has already been entered. Please enter a unique number.");
	                    } else {
	                        lights[index] = enteredNumber;  // Convert to integer for storage
	                    }
	                } catch (NumberFormatException e) {
	                    smallTextField.setText(oldValue);
	                    showError(infoLabel, "Invalid input: Please enter a valid integer number.");
	                }
	            }
	        });

	        textFields.add(smallTextField);
	        smallTextFieldsHBox.getChildren().add(smallTextField);
	    }
	    btnnext.setOnAction(e -> {
	        boolean allFieldsFilled = true;
	        for (TextField field : textFields) {
	            if (field.getText().isEmpty()) {
	                allFieldsFilled = false;
	                break;
	            }
	        }

	        System.out.println("allFieldsFilled: " + allFieldsFilled);

	        if (allFieldsFilled) {
	            showstage3();
	        } else {
	            showError(infoLabel, "Please fill in all the fields before go to next Stage");
	        }
	    });

	       
	

	    // Add a listener to check for empty fields when leaving the stage
	    smallTextFieldsHBox.parentProperty().addListener((observable, oldParent, newParent) -> {
	        if (newParent == null) {
	            // User is leaving the stage or submitting
	            if (textFields.stream().anyMatch(field -> field.getText().isEmpty())) {
	                showError(infoLabel, "Please fill in all the fields before proceeding.");
	                
	            }
	        }
	    });
	}






	private void showError(Label infoLabel, String message) {
		infoLabel.setText(message);
	}
	public void showstage2() {
	    stage2 = new Stage();
	    VBox mainVBox = new VBox(10);
	    mainVBox.setPadding(new Insets(10, 10, 10, 10));
	    HBox inputHBox = new HBox(10);
	    Label label = new Label("Enter a number (n):");
	    TextField nTextField = new TextField();
	    inputHBox.getChildren().addAll(label, nTextField);

	    HBox smallTextFieldsHBox = new HBox(5);
	    Label infoLabel = new Label("Empty boxes only accept unique integer numbers greater than zero.");
	    smallTextFieldsHBox.getChildren().add(infoLabel);

	    btnnext = new Button("Next");
	    mainVBox.getChildren().addAll(inputHBox, smallTextFieldsHBox, btnnext);
	   

	    nTextField.setOnAction(event -> {
	        try {
	            if (!nTextField.getText().isEmpty() && Integer.parseInt(nTextField.getText()) > 0) {
	                // Move the code inside this block
	                int n = Integer.parseInt(nTextField.getText());
	                batteries = buildArrayFrom1Tonum(n);
	                createSmallTextFields(smallTextFieldsHBox, n, infoLabel);
	            } else {
	                showError(infoLabel, "Please enter a valid number greater than zero.");
	            }
	        } catch (NumberFormatException ex) {
	            showError(infoLabel, "Please enter a valid number.");
	        }
	    });

	    Scene scene2 = new Scene(mainVBox, 800, 800);
	    stage2.setScene(scene2);
	    stage2.showAndWait();
	}

	public void showstage3() {
		stage3 = new Stage();
	Label titel2=new Label("Expected Result");
	titel2.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 25));
	Button btnnext=new Button("next");
	btnnext.setStyle("-fx-background-color:pink;");
	btnnext.setOnAction(e->{
		showStage4();
	});
	ScrollPane scrollPane = new ScrollPane();
    BorderPane paneb = new BorderPane();
    Pane pane = new Pane();
    paneb.setCenter(pane);
    paneb.setTop(titel2);
    paneb.setRight(btnnext);
    scrollPane.setContent(paneb);
    Scene scene = new Scene(scrollPane, 800, 400);
    
    // Create graphical representation based on LCS
    drawConnections(pane);

   stage3.setScene(scene);
  stage3.showAndWait();
	
}
	public void showStage4() {
		stage4=new Stage();
		 // Append zero at the beginning of both arrays
		//int[] xWithZero = new int[batteries.length + 1];
		//int[] yWithZero = new int[lights.length + 1];
		//System.arraycopy(batteries, 0, xWithZero, 1, batteries.length);
		//System.arraycopy(lights, 0, yWithZero, 1, lights.length);

		//int[][] dpTable = calculateDPTable(xWithZero, yWithZero);
		//char[][] trackingTable = calculateTrackingTable(xWithZero, yWithZero, dpTable);
        //VBox dpVBox = createTableVBox("DP Table", xWithZero, yWithZero, dpTable);
        //VBox trackingVBox = createTableVBox2("Tracking Table", xWithZero, yWithZero, trackingTable);
		//VBox dpVBox = createTableVBox("DP Table", xWithZero, yWithZero, dpTable);
		//VBox trackingVBox = createTableVBox2("Tracking Table", xWithZero, yWithZero, trackingTable);
        ScrollPane scrollPane = new ScrollPane();
        //scrollPane.setContent(new VBox(dpVBox, trackingVBox));
        int[][] dpTable = calculateDPTable(batteries,lights);
        scrollPane.setContent(createTableTextArea(batteries,lights,dpTable));
        Scene scene4 = new Scene(scrollPane, 800, 400);

        stage4.setScene(scene4);
        stage4.show();
    }

	private static int[][] calculateDPTable(int[] x, int[] y) {
	    int m = x.length;
	    int n = y.length;
	    int[][] dpTable = new int[m][n];

	    // DP table creation
	    for (int i = 0; i < m; i++) {
	        for (int j = 0; j < n; j++) {
	            if (x[i] == y[j]) {
	                dpTable[i][j] = (i == 0 || j == 0) ? 1 : dpTable[i - 1][j - 1] + 1;
	            } else {
	                dpTable[i][j] = Math.max((i == 0) ? 0 : dpTable[i - 1][j], (j == 0) ? 0 : dpTable[i][j - 1]);
	            }
	        }
	    }

	    return dpTable;
	}


	
    private VBox createTableVBox(String tableName, int[] x, int[] y, int[][] table) {
        VBox vBox = new VBox(10);
        Label label = new Label(tableName);
        TextArea textArea = createTableTextArea(x, y, table);

        vBox.getChildren().addAll(label, textArea);
        return vBox;
    }

    private VBox createTableVBox2(String tableName, int[] x, int[] y, char[][] table) {
        VBox vBox = new VBox(10);
        Label label = new Label(tableName);
        TextArea textArea = createTrackingTableTextArea(x, y, table);

        vBox.getChildren().addAll(label, textArea);
        return vBox;
    }


    private char[][] calculateTrackingTable(int[] x, int[] y, int[][] dpTable) {
        int m = x.length;
        int n = y.length;
        char[][] trackingTable = new char[m][n];

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (x[i] == y[j]) {
                    trackingTable[i][j] = 'R'; // 'R' represents diagonal
                } else if (dpTable[i - 1][j] >= dpTable[i][j - 1]) {
                    trackingTable[i][j] = 'U'; // 'U' represents up
                } else {
                    trackingTable[i][j] = 'L'; // 'L' represents left
                }
            }
        }

        return trackingTable;
    }

private VBox createTableVBox(String tableName, int[] x, int[] y, char[][] table) {
    VBox vBox = new VBox(10);
    Label label = new Label(tableName);
    TextArea textArea = createTrackingTableTextArea(x, y, table);

    vBox.getChildren().addAll(label, textArea);
    return vBox;
}

private TextArea createTrackingTableTextArea(int[] x, int[] y, char[][] trackingTable) {
    TextArea textArea = new TextArea();
    StringBuilder content = new StringBuilder();

    int numRows = x.length;
    int numCols = y.length;

    // Header (first row)
    content.append("\t");
    for (int col : y) {
        content.append(col).append("\t");
    }
    content.append("\n");

    // Table body
    for (int row = 0; row < numRows; row++) {
        // First column (array x)
        content.append(x[row]).append("\t");

        for (int col = 0; col < numCols; col++) {
            content.append(trackingTable[row][col]).append("\t");
        }
        content.append("\n");
    }

    textArea.setText(content.toString());
    textArea.setEditable(false);

    return textArea;
}


private TextArea createTableTextArea(int[] x, int[] y, int[][] table) {
    TextArea textArea = new TextArea();
    StringBuilder content = new StringBuilder();

    int numRows = x.length;
    int numCols = y.length;

    // Header (first row)
    content.append("\t");
    for (int col : y) {
        content.append(col).append("\t");
    }
    content.append("\n");

    // Table body
    for (int row = 0; row < numRows; row++) {
        // First column (array x)
        content.append(x[row]).append("\t");

        for (int col = 0; col < numCols; col++) {
            content.append(table[row][col]).append("\t");
        }
        content.append("\n");
    }

    textArea.setText(content.toString());
    textArea.setEditable(false);

    return textArea;
}


	
private void drawConnections(Pane pane) {
	lcsResult=calculateLCS(batteries,lights);
    double xOffsetBattery = 50;
    double xOffsetLED = 50;
    double yOffset = 100;

    Map<Integer, Double> batteryXMap = new HashMap<>();

    
    for (int i = 0; i < batteries.length; i++) {
        double batteryX = xOffsetBattery + i * 120;

        // Draw battery rectangle
        Rectangle battery = new Rectangle(batteryX, yOffset, 50, 30);
        battery.setFill(Color.GRAY);
        pane.getChildren().add(battery);

        // Draw battery label
        Label batteryLabel = new Label(Integer.toString(batteries[i]));
        batteryLabel.setLayoutX(batteryX + 20);
        batteryLabel.setLayoutY(yOffset - 15);
        pane.getChildren().add(batteryLabel);

        batteryXMap.put(batteries[i], batteryX + 25); // Store the center X-coordinate of the battery
    }

    // Draw all LEDs and connect only LEDs in the LCS result to their corresponding batteries
 // Inside the drawConnections method
    for (int i = 0; i < lights.length; i++) {
        double ledX = xOffsetLED + i * 120;
        double ledY = yOffset + 180;

        //ImageView ledImage = new ImageView(new Image("(\"C:\\\\Users\\\\user\\\\Desktop\\\\Afnan\\\\tester6\\\\393207678_1142477426720504_4159062909525850963_n.jpg"));
        ImageView ledImage = new ImageView(new Image("C:\\Users\\user\\Desktop\\Afnan\\test4\\src\\393207678_1142477426720504_4159062909525850963_n.jpg"));
        ImageView ledImage2 = new ImageView(new Image("C:\\Users\\user\\Desktop\\Afnan\\test4\\src\\410606711_272883355767079_2558702523097988620_n.jpg"));
        ledImage.setFitWidth(50);
        ledImage.setFitHeight(50);
        ledImage.setLayoutX(ledX - 15);
        ledImage.setLayoutY(ledY - 15);

        ledImage2.setFitWidth(50);
        ledImage2.setFitHeight(50);
        ledImage2.setLayoutX(ledX - 15);
        ledImage2.setLayoutY(ledY - 15);

      
        System.out.println("Light " + lights[i] + " Connection Valid: " + isConnectionValid(lights[i]));

        if (isConnectionValid(lights[i])) {
            double batteryX = batteryXMap.get(lights[i]);
            drawWire(pane, ledX, ledY, batteryX, yOffset + 40);
            ledImage.setBlendMode(BlendMode.OVERLAY);
            pane.getChildren().addAll(ledImage2);
        } else {
            ledImage2.setBlendMode(BlendMode.OVERLAY);
            pane.getChildren().addAll(ledImage2);
        }

        Label ledLabel = new Label(Integer.toString(lights[i]));
        ledLabel.setLayoutX(ledX - 5);
        ledLabel.setLayoutY(ledY - 20);

        if (isConnectionValid(lights[i])) {
            pane.getChildren().addAll(ledLabel);
        } else {
            pane.getChildren().addAll(ledImage, ledLabel);
        }

        ledLabel.toFront();
    }
}

private boolean isConnectionValid(int light) {
    // Check if the LED is in the LCS result array
    for (int lcsLight : lcsResult) {
        if (lcsLight == light) {
            return true;
        }
    }
    return false;
}

private void drawWire(Pane pane, double startX, double startY, double endX, double endY) {
    // Draw a Bezier curve connecting the LED to the battery
    double controlPointX = (startX + endX) / 2;
    Line wire1 = new Line(startX, startY, controlPointX, startY - 50);
    Line wire2 = new Line(controlPointX, startY - 50, controlPointX, endY + 50);
    Line wire3 = new Line(controlPointX, endY + 50, endX, endY);
    wire1.setStroke(Color.RED);
    wire2.setStroke(Color.RED);
    wire3.setStroke(Color.RED);
    wire1.setStrokeWidth(4.0);
    wire2.setStrokeWidth(4.0);
    wire3.setStrokeWidth(4.0);
    pane.getChildren().addAll(wire1, wire2, wire3);
}


private int[] calculateLCS(int[] x, int[] y) {
    int[][] dpTable = calculateDPTable(x,y);

    // LCS result calculation
    int m = x.length;
    int n = y.length;
    int index = dpTable[m][n];
    int[] result = new int[index];
    int i = m, j = n;

    while (i > 0 && j > 0) {
        if (x[i - 1] == y[j - 1]) {
            result[index - 1] = x[i - 1];
            i--;
            j--;
            index--;
        } else if (dpTable[i - 1][j] > dpTable[i][j - 1]) {
            i--;
        } else {
            j--;
        }
    }

    return result;
}


	public static void main(String[] args) {
		launch(args);
	}
}
