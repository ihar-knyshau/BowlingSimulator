import java.util.regex.Pattern;

public class BowlingFrame {
    private FrameResult frameResult = null;
    private static final String STRIKE_FRAME_REGEX = "X{1}";
    private static final String REGULAR_FRAME_REGEX = "[1-9-]{2}";
    private static final String SPARE_FRAME_REGEX = "[1-9-]/";

    public BowlingFrame() {
    }

    public BowlingFrame(String result) {
        byte frameScore = calculateScore(result);
        FrameType frameType = calculateFrameType(result);
        frameResult = new FrameResult(frameScore, frameType);
    }

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
        throw new IllegalArgumentException();
    }

    public byte calculateScore(String result) {
        char firstSymbol = result.charAt(0);
        char secondSymbol = result.charAt(1);
        return (byte) (calculateDigit(firstSymbol) + calculateDigit(secondSymbol));
    }

    public byte calculateDigit(char a) {
        if (a == 'X' || a == '/')
            return 10;
        if (a == '-')
            return 0;
        return Byte.parseByte(String.valueOf(a));
    }

    public FrameResult getFrameResult() {
        return frameResult;
    }
}
