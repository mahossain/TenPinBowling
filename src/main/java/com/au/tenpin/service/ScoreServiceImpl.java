package com.au.tenpin.service;

import com.au.tenpin.domain.Frame;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Mohammad Afzal Hossain
 */
public class ScoreServiceImpl implements ScoreService {

    public static final int MAX_PIN_PER_FRAME = 10;

    private volatile static ScoreServiceImpl instance = null;

    private ScoreServiceImpl() {
    }

    public static ScoreService getInstance() {
        if (instance == null) {
            synchronized (ScoreServiceImpl.class) {
                if (instance == null) {
                    instance = new ScoreServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public int calculateScore(final List<Frame> frames) {
        for (int i = 0; i < frames.size(); i++) {
            Frame frame = frames.get(i);
            if (frame.isSparse()) {
                handleSparse(frame, frames, i);
            } else if (frame.isStrike()) {
                handleSpike(frame, frames, i);
            } else {
                frame.setScore(frame.getTotalPinDown());
            }
        }
        return frames.stream().map(frame -> frame.getScore()).reduce(0, (a, b) -> a + b);
    }

    @Override
    public List<Frame> createFrames(final String inputString) {
        List<Frame> frames = new ArrayList<>();
        List<Integer> attemps = new ArrayList<>();
        for (String numOfPins : inputString.split("\\s+")) {

            if (Integer.valueOf(numOfPins) == MAX_PIN_PER_FRAME) {
                Frame frame = new Frame(Integer.valueOf(numOfPins));
                frames.add(frame);
            } else {
                attemps.add(Integer.valueOf(numOfPins));
            }

            if (attemps.size() == 2) {
                Frame frame = new Frame(attemps.get(0), attemps.get(1));
                frames.add(frame);
                attemps.clear();
            }
        }
        return frames;
    }

    private void handleSparse(final Frame frame, List<Frame> frames, int currentIndex) {
        int nextFrameIndex = currentIndex + 1;
        if (nextFrameIndex < frames.size() && frames.get(nextFrameIndex) != null) {
            frame.setScore(frame.getTotalPinDown() + frames.get(nextFrameIndex).getPinForFirstBall());
        } else {
            frame.setScore(frame.getTotalPinDown());
        }
    }

    private void handleSpike(final Frame frame, final List<Frame> frames, final int currentIndex) {
        int firstBall = 0;
        int secondBall = 0;
        int nextFrameIndex = currentIndex + 1;
        if (nextFrameIndex < frames.size()) {
            Frame nextFrame = frames.get(nextFrameIndex);
            if (nextFrame.isStrike()) {
                firstBall = nextFrame.getPinForFirstBall();
                nextFrameIndex = nextFrameIndex + 1;
                if (nextFrameIndex < frames.size()) {
                    nextFrame = frames.get(nextFrameIndex);
                    secondBall = nextFrame.getPinForFirstBall();
                }
            } else {
                firstBall = nextFrame.getPinForFirstBall();
                secondBall = nextFrame.getPinForSecondBall();
            }
        }
        if (firstBall != 0 && secondBall != 0) {
            frame.setScore(frame.getTotalPinDown() + firstBall + secondBall);
        }
    }

    @Override
    public boolean isValidInput(final String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }

        final Matcher matcher = Pattern.compile("[0-9 ]+").matcher(input);

        if (matcher.matches()) {
            boolean outOfRangeNumber = Arrays.asList(input.split("\\s+")).stream().map(e -> Integer.valueOf(e)).filter(num -> num < 0 && num > 10).findAny().isPresent();
            if (outOfRangeNumber) {
                return false;
            }
        } else {
            return false;
        }

        return true;
    }
}
