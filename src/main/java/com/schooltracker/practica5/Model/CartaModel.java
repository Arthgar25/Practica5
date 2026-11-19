package com.schooltracker.practica5.Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CartaModel {
    private CircularList<Carta> allCards;
    private String[] firstCardData; // Array to store info from the first member of the CSV

    public CartaModel(String csvFilePath) {
        this.allCards = new CircularList<>();
        this.firstCardData = null;
        loadCardsFromCSV(csvFilePath);
    }

    private void loadCardsFromCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;
            boolean isFirstDataRow = true; // Flag to capture only the first member

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Skip the CSV header row
                }

                String[] values = parseCsvLine(line);
                if (values.length >= 9) {
                    // Capture the first valid data row into the String array
                    if (isFirstDataRow) {
                        this.firstCardData = values;
                        isFirstDataRow = false;
                    }

                    Carta card = new Carta(
                            values[0].trim(),
                            values[1].trim(),
                            values[2].trim(),
                            values[3].trim(),
                            values[4].trim(),
                            values[5].trim(),
                            values[6].trim(),
                            values[7].trim(),
                            values[8].trim()
                    );
                    allCards.addLast(card);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Getter to retrieve the first member's info as a String array
    public String[] getFirstCardData() {
        return firstCardData;
    }

    private String[] parseCsvLine(String line) {
        List<String> tokens = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder sb = new StringBuilder();
        for (char c : line.toCharArray()) {
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                tokens.add(sb.toString().trim());
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }
        tokens.add(sb.toString().trim());
        return tokens.toArray(new String[0]);
    }

    public CircularList<Carta> showAllCards() {
        return allCards;
    }

    public CircularList<Carta> showByLevel(String level) {
        CircularList<Carta> result = new CircularList<>();
        if (allCards.isEmpty()) {
            return result;
        }
        Node<Carta> current = allCards.getTail().getNext();
        Node<Carta> start = current;
        do {
            Carta card = current.getData();
            if (card.getLevelRank().equalsIgnoreCase(level)) {
                result.addLast(card);
            }
            current = current.getNext();
        } while (current != start);
        return result;
    }

    public CircularList<Carta> showByAttribute(String attribute) {
        CircularList<Carta> result = new CircularList<>();
        if (allCards.isEmpty()) {
            return result;
        }
        Node<Carta> current = allCards.getTail().getNext();
        Node<Carta> start = current;
        do {
            Carta card = current.getData();
            if (card.getAttributeType().toLowerCase().contains(attribute.toLowerCase())) {
                result.addLast(card);
            }
            current = current.getNext();
        } while (current != start);
        return result;
    }

    public CircularList<Carta> showByAttackPoints(boolean lowToHigh) {
        List<Carta> tempList = new ArrayList<>();
        if (!allCards.isEmpty()) {
            Node<Carta> current = allCards.getTail().getNext();
            Node<Carta> start = current;
            do {
                tempList.add(current.getData());
                current = current.getNext();
            } while (current != start);
        }

        tempList.sort((c1, c2) -> {
            int atk1 = parseStat(c1.getAttack());
            int atk2 = parseStat(c2.getAttack());
            return lowToHigh ? Integer.compare(atk1, atk2) : Integer.compare(atk2, atk1);
        });

        CircularList<Carta> result = new CircularList<>();
        for (Carta card : tempList) {
            result.addLast(card);
        }
        return result;
    }

    public CircularList<Carta> showByDefensePoints(boolean lowToHigh) {
        List<Carta> tempList = new ArrayList<>();
        if (!allCards.isEmpty()) {
            Node<Carta> current = allCards.getTail().getNext();
            Node<Carta> start = current;
            do {
                tempList.add(current.getData());
                current = current.getNext();
            } while (current != start);
        }

        tempList.sort((c1, c2) -> {
            int def1 = parseStat(c1.getDefense());
            int def2 = parseStat(c2.getDefense());
            return lowToHigh ? Integer.compare(def1, def2) : Integer.compare(def2, def1);
        });

        CircularList<Carta> result = new CircularList<>();
        for (Carta card : tempList) {
            result.addLast(card);
        }
        return result;
    }

    private int parseStat(String stat) {
        try {
            return Integer.parseInt(stat.replaceAll("[^0-9]", ""));
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}