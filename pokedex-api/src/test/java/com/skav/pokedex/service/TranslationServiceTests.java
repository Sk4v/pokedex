package com.skav.pokedex.service;

import com.skav.pokedex.service.interfaces.TranslationService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TranslationServiceTests {

    private static final Logger logger = LoggerFactory.getLogger(TranslationServiceTests.class);

    @Autowired
    private TranslationService translationService;

    @Test
    void testShakespeareTranslate() throws ExecutionException, InterruptedException {
        String text = "Hello World!";
        logger.info("Test Shakespeare Translation for text: {}", text);

        try {
            Map<String, Object> result = translationService.shakespeareTranslate(text);
            assertNotNull(result);
            Map<String, Object> contents = (Map<String, Object>) result.get("contents");
            assertNotNull(contents.get("translated"));
            assertEquals(contents.get("text"), text);
            assertEquals(contents.get("translation"), "shakespeare");
        } catch (RuntimeException e) {
            logger.warn("Rate limit exceeded while calling Shakespeare translation API: {}", e.getMessage());
        }
    }

    @Test
    void testYodaTranslate() throws ExecutionException, InterruptedException {
        String text = "Hello World!";
        logger.info("Test Yoda Translation for text: {}", text);

        try {
            Map<String, Object> result = translationService.yodaTranslate(text);
            assertNotNull(result);
            Map<String, Object> contents = (Map<String, Object>) result.get("contents");
            assertNotNull(contents.get("translated"));
            assertEquals(contents.get("text"), text);
            assertEquals(contents.get("translation"), "yoda");
        } catch (RuntimeException e) {
            logger.warn("Rate limit exceeded while calling Yoda translation API: {}", e.getMessage());
        }
    }


}
