package utils;

import java.util.Random;

/**
 * Utility class for generating random email addresses.
 */

public class EmailGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final String[] DOMAINS = {"example.com", "test.com", "mail.com", "domain.com"};

    public static void main(String[] args) {
        String randomEmail = generateRandomEmail();
        System.out.println("Random Email: " + randomEmail);
    }
    public static String generateRandomEmail() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        int length = 15;
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }

        sb.append("@");

        String domain = DOMAINS[random.nextInt(DOMAINS.length)];
        sb.append(domain);

        return sb.toString();
    }
}

