package com.au.tenpin.service;

import com.au.tenpin.domain.Frame;
import java.util.List;

/**
 *
 * @author Mohammad Afzal Hossain
 */
public interface ScoreService {

    int calculateScore(List<Frame> frames);

    List<Frame> createFrames(String inputString);

    boolean isValidInput(String input);
}
