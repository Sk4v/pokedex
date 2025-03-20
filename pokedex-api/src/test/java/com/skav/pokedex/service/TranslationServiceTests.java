package com.skav.pokedex.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skav.pokedex.service.interfaces.TranslationService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@EnableAsync
public class TranslationServiceTests {

    private static final Logger logger = LoggerFactory.getLogger(TranslationServiceTests.class);

    @Autowired
    private TranslationService translationService;

    //TODO add tests descriptions

    @Test
    void testShakespeareTranslate() throws ExecutionException, InterruptedException {
        String text = "Hello World!";
        logger.info("Test Shakespeare Translation for text: {}", text);
        Map<String, Object> result = translationService.shakespeareTranslate(text).get();
        logger.info("Result: {}", result);
        assertNotNull(result);
        //FIXME add assertions
    }

    @Test
    void testYodaTranslate() throws ExecutionException, InterruptedException {
        String text = "Hello World!";
        logger.info("Test Yoda Translation for text: {}", text);
        Map<String, Object> result = translationService.yodaTranslate(text).get();
        logger.info("Result: {}", result);
        assertNotNull(result);
        //FIXME add assertions
    }


}
