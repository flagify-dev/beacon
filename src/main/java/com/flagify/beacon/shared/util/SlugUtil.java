package com.flagify.beacon.shared.util;

public final class SlugUtil {
    private SlugUtil() {
        // private constructor to prevent instantiation
    }
    
    public static String generateSlug(String name) {
        return name.toLowerCase().replaceAll("[^a-z0-9]+", "-").replaceAll("^-|-$", "");
    }
}
