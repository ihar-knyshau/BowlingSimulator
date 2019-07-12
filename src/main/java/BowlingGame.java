public class BowlingGame {
    private static final String FRAMES_DELIMITER = "\\|";
    private static final byte FRAMES_NUMBER = 10;
    private String game;

    public BowlingGame(String game) {
        this.game = game;
    }

    public BowlingFrame[] splitIntoFrames() {
        String[] gameFrames = game.split(FRAMES_DELIMITER, FRAMES_NUMBER);
        BowlingFrame[] frames = new BowlingFrame[FRAMES_NUMBER];
        for (int i = 0; i < gameFrames.length; i++) {
            String result = gameFrames[i];
            BowlingFrame frame = i < gameFrames.length - 1
                    ? new RegularBowlingFrame(result)
                    : new FinalBowlingFrame(result);
            frames[i] = frame;
        }
        return frames;
    }

    public int calculateGameFrameScore(BowlingFrame[] frames) {
        int gameScore = 0;
        for (int i = 0; i < frames.length; i++) {
            int frameNumber = i + 1;
            int gameFrameScore = frames[i].getFrameScore();
            int additionalHits = frames[i].getFrameType().getAdditionalHits();
            while (additionalHits != 0) {
                for (int k = 0; k < frames[frameNumber].getHits().length && additionalHits != 0; k++) {
                    gameFrameScore += frames[frameNumber].getHits()[k].getHitScore();
                    additionalHits--;
                }
                frameNumber++;
            }
            gameScore += gameFrameScore;
        }
        return gameScore;
    }

    public static void main(String[] args) {
        BowlingGame bowlingGame = new BowlingGame("X|-/|-/|-/|-/|-/|-/|-/|-/|-/||X");
        BowlingFrame[] frames = bowlingGame.splitIntoFrames();
        System.out.println(bowlingGame.calculateGameFrameScore(frames));
    }
}
