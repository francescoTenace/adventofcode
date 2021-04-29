import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day8Part1 {

    private static final String FILE_PATH = "/home/tenax/Personal/input.txt";
    private static final String REGEX = ".+(?=[+\\-]\\d)";

    public static int parse(String code) {
        return Integer.parseInt(code.replaceAll(REGEX, ""));
    }

    public static int execute(List<String> codeInstructions) {
        Map<Integer, String> instructions = new HashMap<>();
        Set<Map<Integer, String>> repetitions = new HashSet<>();
        int accumulator = 0;
        int index = 0;

        for (String inst : codeInstructions) {
            instructions.put(index, inst);
        }

        index = 0;

        for (int i = 0; i < instructions.keySet().size(); i++) {
            String code = instructions.get(i);

            index++;

            Map<Integer, String> tempMap = new HashMap<>();
            tempMap.put(i, code);

            if (code.contains("nop"))
                continue;
            if (code.contains("jmp"))
                i += parse(code) - 1;
            if (code.contains("acc") && !repetitions.contains(tempMap))
                accumulator += parse(code);

            if (!repetitions.add(new HashMap<>(tempMap)))
                return accumulator;

            tempMap.clear();
        }

        return instructions.size();
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

        System.out.println(execute(entryList));
    }

}