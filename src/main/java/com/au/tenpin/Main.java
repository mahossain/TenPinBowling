package com.au.tenpin;

import com.au.tenpin.domain.Frame;
import com.au.tenpin.service.ScoreService;
import com.au.tenpin.service.ScoreServiceImpl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mohammad Afzal Hossain
 */
public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        
        if (args != null && args.length > 0) {
            String input = args[0];
            final ScoreService scoreService = ScoreServiceImpl.getInstance();
            if (scoreService.isValidInput(input)) {
                final List<Frame> frames = scoreService.createFrames(input);
                int score = scoreService.calculateScore(frames);
                LOGGER.log(Level.INFO, "{0} -> {1}", new Object[]{input, score});
            } else {
                LOGGER.log(Level.SEVERE, "{0} contains invalid number", input);
            }
        } else {
            runDefault();
        }
    }

    private static void runDefault() {
        final String input_spare = "9 1 9 1";
        final String input_strike = "10 10 10 10 10 10 10 10 10 10 10 10";
        final String input_normal = "1 2 3 4";
        final String input_simple = "1 1 1 1 10 1 1";

        final List<String> inputList = new ArrayList<>(Arrays.asList(input_normal, input_spare, input_simple, input_strike));

        final ScoreService scoreService = ScoreServiceImpl.getInstance();

        inputList.stream().forEach((String input) -> {
            if (scoreService.isValidInput(input)) {
                final List<Frame> frames = scoreService.createFrames(input);
                int score = scoreService.calculateScore(frames);
                LOGGER.log(Level.INFO, "{0} -> {1}", new Object[]{input, score});
            } else {
                LOGGER.log(Level.SEVERE, "{0} contains invalid number", input);
            }
        });
    }
}
