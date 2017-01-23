package com.au.tenpin.service;

import com.au.tenpin.domain.Frame;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mohammad Afzal Hossain
 */
public class ScoreServiceImplTest {

    public ScoreServiceImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void shouldReturnAnInstanceWhichIsNotNull() {
        ScoreService scoreService = ScoreServiceImpl.getInstance();
        assertNotNull(scoreService);
    }

    @Test
    public void shouldHandleSparseSituation() {
        final String input_spare = "9 1 9 1";
        final int EXPECTED_SCORE = 29;
        final ScoreService scoreService = ScoreServiceImpl.getInstance();
        final List<Frame> frames = scoreService.createFrames(input_spare);
        final int realScore = scoreService.calculateScore(frames);
        assertEquals(EXPECTED_SCORE, realScore);
    }

    @Test
    public void shouldHandleStrikeSituation() {
        final String input_strike = "10 10 10 10 10 10 10 10 10 10 10 10";
        final int EXPECTED_SCORE = 300;
        final ScoreService scoreService = ScoreServiceImpl.getInstance();
        final List<Frame> frames = scoreService.createFrames(input_strike);
        final int realScore = scoreService.calculateScore(frames);
        assertEquals(EXPECTED_SCORE, realScore);
    }

    @Test
    public void shouldHandleNonStrikeAndNonSparseSituation() {
        final String input_normal = "1 2 3 4";
        final int EXPECTED_SCORE = 10;
        final ScoreService scoreService = ScoreServiceImpl.getInstance();
        final List<Frame> frames = scoreService.createFrames(input_normal);
        final int realScore = scoreService.calculateScore(frames);
        assertEquals(EXPECTED_SCORE, realScore);
    }

    @Test
    public void shouldHandleMixSituationWithOneStrike() {
        final String mixinputWithOneStrike = "1 1 1 1 10 1 1";
        final int EXPECTED_SCORE = 18;
        final ScoreService scoreService = ScoreServiceImpl.getInstance();
        final List<Frame> frames = scoreService.createFrames(mixinputWithOneStrike);
        final int realScore = scoreService.calculateScore(frames);
        assertEquals(EXPECTED_SCORE, realScore);
    }

    @Test
    public void testCreateFrames() {
        final String input_spare = "9 1 9 1";
        final ScoreService scoreService = ScoreServiceImpl.getInstance();
        final List<Frame> frames = scoreService.createFrames(input_spare);
        assertEquals(2, frames.size());

        frames.stream().forEach(frame -> {
            assertTrue(frame.isSparse());
        });
    }

    @Test
    public void shouldHaveValidInputOnlyNumbers() {
        final String valid_input = "1 2 3 4 5 6 7 8 9  10";
        final ScoreService scoreService = ScoreServiceImpl.getInstance();
        final boolean result = scoreService.isValidInput(valid_input);
        assertTrue(result);
    }

    @Test
    public void shouldBeInvalidInputIfNullOrEmptySpaceFound() {
        final String nullAndEmptyString[] = new String[]{null, ""};
        final ScoreService scoreService = ScoreServiceImpl.getInstance();
        Arrays.asList(nullAndEmptyString).stream().forEach(input -> {
            final boolean result = scoreService.isValidInput(input);
            assertFalse(result);
        });
    }

    @Test
    public void shouldBeInvalidIfInputIsNotNumber() {
        final ScoreService scoreService = ScoreServiceImpl.getInstance();
        final boolean result = scoreService.isValidInput("five");
        assertFalse(result);
    }
}
