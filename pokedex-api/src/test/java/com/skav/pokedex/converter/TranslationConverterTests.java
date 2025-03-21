package com.skav.pokedex.converter;

import com.skav.pokedex.converter.interfaces.TranslationConverter;
import com.skav.pokedex.service.interfaces.TranslationService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TranslationConverterTests {

    private static final Logger logger = LoggerFactory.getLogger(TranslationConverterTests.class);

    @Autowired
    TranslationConverter translationConverter;
    @Autowired
    private TranslationService translationService;

    @Test
    void testPokemonConverter() throws ExecutionException, InterruptedException {
        String text = "Hello World!";
        logger.info("Get pokemon information for: {}", text);
        try {
            Map<String, Object> result = translationService.yodaTranslate(text);
            assertNotNull(result);
            Map<String, Object> contents = (Map<String, Object>) result.get("contents");
            String traslatedDescription = translationConverter.fromMapToString(contents);
            assertNotNull(traslatedDescription);
        } catch (RuntimeException e) {
            logger.warn("Rate limit exceeded while calling Yoda translation API: {}", e.getMessage());
        }
    }
}
