package com.thrtec.linkshortener.util;

import org.apache.tomcat.util.codec.binary.Base64;

public class EncodeUtil {
    public static String base64UrlDecode(String input) {
        final var decoder = new Base64(true);
        final byte[] decodedBytes = decoder.decode(input);
        return new String(decodedBytes);
    }
}
