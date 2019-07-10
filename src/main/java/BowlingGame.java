import java.util.ArrayList;
import java.util.Collection;

public class BowlingGame {
    private String game;
    public BowlingGame(String game) {
        this.game = game;
    }

    public int play() {
        return sum;
    }

    public Collection<BowlingFrame> parseGame(String game) {
        String[] gameFrames = game.split("|", 10);
        Collection<BowlingFrame> frameResults = new ArrayList<>(10);
        for (int i = 0; i < gameFrames.length; i++) {
            frameResults.add(new BowlingFrame(gameFrames[i]));
        }
        return frameResults;
    }
}
