public class FrameResult {
    private byte frameScore;
    private FrameType frameType;

    public FrameResult(byte frameScore, FrameType frameType) {
        if (frameScore < 0 || frameScore > 10 || frameType == null) {
            throw new IllegalArgumentException();
        }
        this.frameScore = frameScore;
        this.frameType = frameType;
    }

    public byte getFrameScore() {
        return frameScore;
    }

    public FrameType getFrameType() {
        return frameType;
    }
}
