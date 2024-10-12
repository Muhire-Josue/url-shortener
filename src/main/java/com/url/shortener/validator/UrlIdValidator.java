package com.url.shortener.validator;

import com.url.shortener.exception.BadRequestException;

import java.util.regex.Pattern;

public class UrlIdValidator {

    // Pattern for alphanumeric URL IDs of exactly 6 characters
    private static final String URL_ID_REGEX = "^[a-zA-Z0-9]{6}$";
    private static final Pattern PATTERN = Pattern.compile(URL_ID_REGEX);

    public static void validateUrlId(String urlId) {
        if (urlId == null || urlId.length() != 6 || !PATTERN.matcher(urlId).matches()) {
            throw new BadRequestException("URL ID must be exactly 6 alphanumeric characters.");
        }
    }
}
