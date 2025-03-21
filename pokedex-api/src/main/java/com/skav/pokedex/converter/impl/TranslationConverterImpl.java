package com.skav.pokedex.converter.impl;

import com.skav.pokedex.converter.interfaces.TranslationConverter;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TranslationConverterImpl implements TranslationConverter {

    @Override
    public String fromMapToString(Map<String, Object> map) {
        Map<String, Object> contents = (Map<String, Object>) map.get("contents");
        return (String) contents.get("translated");
    }
}
