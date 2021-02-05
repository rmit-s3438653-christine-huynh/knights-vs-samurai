import controller.GameController;
import view.AppStart;


public class Main {

    public static void main(String[] args) {
        GameController gameController = new GameController();

        AppStart appStart = new AppStart(gameController);
    }
}
