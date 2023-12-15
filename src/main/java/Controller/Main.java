package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    @Override

    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/start/start-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Shift Scheduler");


        stage.setScene(scene);

        stage.show();
//        CLI cli = new CLI();
//        cli.run();
        /*String path = "/Users/reeceballard/Downloads/testSchedule.csv";
        String line = "";
        BufferedReader br = new BufferedReader(new FileReader(path));
        while ((line = br.readLine()) != null){
            String[] dayss = line.split(",");
            System.out.println(dayss[0] + dayss[1] + dayss[2]);
            //System.out.println(dayss[1]);
            }*/
       /* try {

            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        */

    }
    public static void main(String[] args) {
        launch(args);
    }
}
