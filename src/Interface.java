public class Interface {
    private static Interface instance = null;

    private Interface() {
    }

    public static Interface getInstance() {
        if (instance == null) {
            instance = new Interface();
        }
        return instance;
    }
}
