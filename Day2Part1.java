package it.kineton.sky.ui;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Day2Part1 {

    private static final String FILE_PATH = "/home/frank/input.txt";

    private static boolean validatePassword(String password) {
        String[] policy = password.replaceAll("(?<=\\d(?=\\s)).+\\v?", "").split("-");

        int min = Integer.parseInt(policy[0]);
        int max = Integer.parseInt(policy[1]);

        char letter = password.replaceAll(".*?(\\w(?=:)).+", "$1").charAt(0);
        password = password.replaceAll(".*?(\\w+)$", "$1");

        int matchCount = 0;
        for (char c : password.toCharArray())
            matchCount += c == letter ? 1 : 0;

        return min <= matchCount && matchCount <= max;
    }

    public static void main(String[] args) {
        List<String> entryList = new ArrayList<>();

        if (new File(FILE_PATH).exists())
            try {
                Path path = FileSystems.getDefault().getPath(FILE_PATH);
                entryList = Files.readAllLines(path);
            } catch (IOException e) {
                e.printStackTrace();
            }

        long validPasswords = entryList.stream().filter(Entry::validatePassword).count();

        System.out.printf("There are %d valid passwords", validPasswords);


    }

}

