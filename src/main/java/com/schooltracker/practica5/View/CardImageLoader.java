package com.schooltracker.practica5.View;

import javafx.scene.image.Image;

import java.io.InputStream;

public class CardImageLoader {
    private static final String FULL_CARD_DIR = "/Cartas/";
    private static final String MINIATURE_DIR = "/miniaturas/";

    public static Image loadFullCardImage(String cardNumber, String cardName) {
        return loadImage(FULL_CARD_DIR, cardNumber, cardName);
    }

    public static Image loadMiniatureImage(String cardNumber, String cardName) {
        return loadImage(MINIATURE_DIR, cardNumber, cardName);
    }

    private static Image loadImage(String folder, String cardNumber, String cardName) {
        if ((cardNumber == null || cardNumber.isBlank()) && (cardName == null || cardName.isBlank())) {
            return null;
        }

        String primaryFileName = buildPrimaryFileName(cardNumber, cardName);
        Image image = tryLoadFromStream(folder + primaryFileName);
        if (image != null) {
            return image;
        }

        String fallbackFileName = buildFallbackFileName(cardNumber, cardName);
        if (!fallbackFileName.equals(primaryFileName)) {
            image = tryLoadFromStream(folder + fallbackFileName);
            if (image != null) {
                return image;
            }
        }

        System.err.println("Image not found in " + folder + ": " + primaryFileName);
        return null;
    }

    private static String buildPrimaryFileName(String cardNumber, String cardName) {
        String prefix = (cardNumber != null && !cardNumber.isBlank()) ? cardNumber.trim() : "";
        String formattedName = (cardName != null ? cardName.trim() : "").replaceAll("\\s+", "_");

        if (!prefix.isEmpty()) {
            return prefix + "_" + formattedName + ".jpg";
        }
        return formattedName + ".jpg";
    }

    private static String buildFallbackFileName(String cardNumber, String cardName) {
        String prefix = (cardNumber != null && !cardNumber.isBlank()) ? cardNumber.trim() + "_" : "";
        String cleanedName = (cardName != null ? cardName.trim() : "")
                .replaceAll("[,:/\\\\?*\"<>|]", "")
                .replaceAll("\\s+", "_");
        return prefix + cleanedName + ".jpg";
    }

    private static Image tryLoadFromStream(String resourcePath) {
        try (InputStream stream = CardImageLoader.class.getResourceAsStream(resourcePath)) {
            if (stream != null) {
                return new Image(stream);
            }
        } catch (Exception e) {
            System.err.println("Error loading image resource " + resourcePath + ": " + e.getMessage());
        }
        return null;
    }
}