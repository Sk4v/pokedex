package com.skav.pokedex.service.impl;

import com.skav.pokedex.service.interfaces.TranslationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class TranslationServiceImpl extends AbstractService implements TranslationService {

    private static final Logger logger = LoggerFactory.getLogger(TranslationServiceImpl.class);

    @Value("${funtranslations.base.url}")
    private String translationsBaseUrl;

    @Value("${funtranslations.shakespeare}")
    private String shakespeare;

    @Value("${funtranslations.yoda}")
    private String yoda;

    @Override
    public Map<String, Object> shakespeareTranslate(String text) {
        URI uri = URI.create(String.format("%s/%s", translationsBaseUrl, shakespeare));
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("text", text);
        return mapResponseFromPostRequest(uri, requestBody);
    }

    @Override
    public Map<String, Object> yodaTranslate(String text) {
        URI uri = URI.create(String.format("%s/%s", translationsBaseUrl, yoda));
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("text", text);
        return mapResponseFromPostRequest(uri, requestBody);
    }
}
