package fr.rader.playerkiller;

public class Tick {

    private static final Main main = Main.getInstance();

    public static void tick() {
        main.getGuardianManager().tickGuardians();
    }
}
