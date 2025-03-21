package com.skav.pokedex.service.interfaces;

import java.util.Map;

public interface TranslationService {
    Map<String, Object> shakespeareTranslate(String text);

    Map<String, Object> yodaTranslate(String text);
}
