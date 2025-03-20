package com.skav.pokedex.service.interfaces;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface TranslationService {
    CompletableFuture<Map<String, Object>> shakespeareTranslate(String text);

    CompletableFuture<Map<String, Object>> yodaTranslate(String text);
}
