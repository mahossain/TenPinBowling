package com.au.tenpin.domain;

/**
 *
 * @author Mohammad Afzal Hossain
 */
public class Frame {

    public static final int MAX_PIN_PER_FRAME = 10;
    private final int pinForFirstBall;
    private final int pinForSecondBall;
    private int score = 0;

    public Frame(int pinForFirstBall) {
        this.pinForFirstBall = pinForFirstBall;
        this.pinForSecondBall = 0;
    }

    public Frame(int pinForFirstBall, int pinForSecondBall) {
        this.pinForFirstBall = pinForFirstBall;
        this.pinForSecondBall = pinForSecondBall;
    }

    public int getPinForFirstBall() {
        return pinForFirstBall;
    }

    public int getPinForSecondBall() {
        return pinForSecondBall;
    }

    public int getTotalPinDown() {
        return pinForFirstBall + pinForSecondBall;
    }

    public boolean isStrike() {
        return getPinForFirstBall() == MAX_PIN_PER_FRAME;
    }

    public boolean isSparse() {
        return getPinForFirstBall() < MAX_PIN_PER_FRAME && getTotalPinDown() == MAX_PIN_PER_FRAME;
    }

    public int getScore() {
        return score;
    }

    public void setScore(final int score) {
        this.score += score;
    }
}
