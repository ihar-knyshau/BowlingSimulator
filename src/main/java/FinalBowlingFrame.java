import java.util.regex.Pattern;
import java.util.stream.Stream;

public class FinalBowlingFrame extends RegularBowlingFrame {
    private static final String FINAL_FRAME_STRIKE_REGEX = "X\\|\\|[X\\-1-9]{2}";
    private static final String FINAL_FRAME_SPARE_REGEX = "[\\-1-9]/\\|\\|[X\\-1-9]";
    private static final String FINAL_FRAME_REGULAR_REGEX = "[\\-1-9]{2}\\|\\|";

    public FinalBowlingFrame(String result) {
        frameType = calculateFrameType(result);
        hits = calculateFrameHits(result, frameType);
    }

    @Override
    public BowlingHit[] calculateFrameHits(String result, FrameType frameType) {
        String mainPart = result.substring(0, result.indexOf("||"));
        RegularBowlingFrame finalFrameFirstPart = new RegularBowlingFrame(mainPart);
        BowlingHit[] mainHits = finalFrameFirstPart.getHits();
        String additionalPart = result.substring(result.indexOf("||") + 2);
        if (finalFrameFirstPart.getFrameType() == FrameType.REGULAR) {
            return mainHits;
        }
        if (finalFrameFirstPart.getFrameType() == FrameType.SPARE) {
            byte firstScore = hitScore(additionalPart.charAt(0));
            BowlingHit[] additionalHit = new BowlingHit[]{new BowlingHit(firstScore)};
            return Stream.of(mainHits, additionalHit)
                    .flatMap(Stream::of)
                    .toArray(BowlingHit[]::new);
        }
        if (finalFrameFirstPart.getFrameType() == FrameType.STRIKE) {
            char firstHit = additionalPart.charAt(0);
            char secondHit = additionalPart.charAt(1);
            byte firstScore = hitScore(firstHit);
            byte secondScore = secondHit != '/' ? hitScore(secondHit) : (byte) (MAX_FRAME_SCORE - firstScore);
            BowlingHit[] additionalHits = {new BowlingHit(firstScore), new BowlingHit(secondScore)};
            return Stream.of(mainHits, additionalHits)
                    .flatMap(Stream::of)
                    .toArray(BowlingHit[]::new);
        }
        throw new IllegalArgumentException();
    }

    @Override
    public FrameType calculateFrameType(String result) {
        if (Pattern.matches(FINAL_FRAME_STRIKE_REGEX, result) ||
                Pattern.matches(FINAL_FRAME_SPARE_REGEX, result) ||
                Pattern.matches(FINAL_FRAME_REGULAR_REGEX, result)) {
            return FrameType.FINAL;
        }
        throw new IllegalArgumentException("Invalid final frame pattern");
    }

}
