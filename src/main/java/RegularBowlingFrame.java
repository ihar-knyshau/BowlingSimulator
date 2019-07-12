import java.util.regex.Pattern;

public class RegularBowlingFrame implements BowlingFrame {
    private static final String STRIKE_FRAME_REGEX = "X{1}";
    private static final String REGULAR_FRAME_REGEX = "[1-9-]{2}";
    private static final String SPARE_FRAME_REGEX = "[1-9-]/";

    FrameType frameType;
    BowlingHit[] hits;

    public RegularBowlingFrame() {
    }

    public RegularBowlingFrame(String result) {
        frameType = calculateFrameType(result);
        hits = calculateFrameHits(result, frameType);
    }

    @Override
    public FrameType calculateFrameType(String result) {
        if (Pattern.matches(STRIKE_FRAME_REGEX, result)) {
            return FrameType.STRIKE;
        }
        if (Pattern.matches(SPARE_FRAME_REGEX, result)) {
            return FrameType.SPARE;
        }
        if (Pattern.matches(REGULAR_FRAME_REGEX, result)) {
            return FrameType.REGULAR;
        }
        throw new IllegalArgumentException("Invalid regular frame pattern");
    }

    @Override
    public BowlingHit[] calculateFrameHits(String result, FrameType frameType) {
        if (frameType == FrameType.STRIKE) {
            return new BowlingHit[]{new BowlingHit(MAX_FRAME_SCORE)};
        }
        char firstHit = result.charAt(0);
        if (frameType == FrameType.REGULAR) {
            char secondHit = result.charAt(1);
            byte firstScore = hitScore(firstHit);
            byte secondScore = hitScore(secondHit);
            if (firstScore + secondScore >= MAX_FRAME_SCORE) {
                throw new IllegalArgumentException();
            }
            return new BowlingHit[]{new BowlingHit(firstScore), new BowlingHit(secondScore)};
        }
        if (frameType == FrameType.SPARE) {
            byte firstScore = hitScore(firstHit);
            byte secondScore = (byte) (MAX_FRAME_SCORE - firstScore);
            if (firstScore + secondScore > MAX_FRAME_SCORE) {
                throw new IllegalArgumentException();
            }
            return new BowlingHit[]{new BowlingHit(firstScore), new BowlingHit(secondScore)};
        }
        throw new IllegalArgumentException();
    }

    @Override
    public FrameType getFrameType() {
        return frameType;
    }

    @Override
    public BowlingHit[] getHits() {
        return hits;
    }

    @Override
    public int getFrameScore() {
        byte frameScore = 0;
        for (BowlingHit hit : hits) {
            frameScore += hit.getHitScore();
        }
        return frameScore;
    }

}
