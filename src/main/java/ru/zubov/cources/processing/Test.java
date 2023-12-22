package ru.zubov.cources.processing;

public class Test {
    public static void main(String[] args) {
        System.out.println(lengthOfLastWord("luffy is still joyboy"));
    }

    private static int lengthOfLastWord(String s) {
        s = s.trim().replaceAll("  ", " ");
        String[] split = s.split(" ");
        return split[split.length - 1].length();
    }
}
