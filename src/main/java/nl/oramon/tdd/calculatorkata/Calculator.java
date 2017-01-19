package nl.oramon.tdd.calculatorkata;


public class Calculator {

    public String calculate(String input) throws CalculationException {
        if (input == null || input.equals("")) {
            return "0";
        }

        if (input.matches("[0-9]+")) {
            return input;
        }

        if (input.contains("-")) {
            throw new CalculationException();
        }

        String separator = selectSeparator(input);
        input = filterInput(input, separator);
        return sumNumbers(input, separator);
    }

    private String filterInput(String input, String separator) {
        if (!separator.contains("\n")) {
            String[] tempSplittedInput = input.split("\n");
            if (tempSplittedInput.length > 1) {
                input = tempSplittedInput[1];
            }
        }
        return input;
    }

    private String sumNumbers(String input, String separator) {
        String[] splittedInput = input.split(separator);
        int result = 0;
        for (String s : splittedInput) {
            int tempNumber = Integer.valueOf(s);
            if (tempNumber > 1000) {
                continue;
            }
            result += tempNumber;
        }
        return String.valueOf(result);
    }

    private String selectSeparator(String input) {
        String result = "[,\n]";
        String[] splittedInput = input.split("\n");
        if (splittedInput.length > 0) {
            String separatorLine = splittedInput[0].trim();
            if (separatorLine.startsWith("//[") && separatorLine.endsWith("]")) {
                result = separatorLine.substring(3, separatorLine.length() - 1);
            } else if (separatorLine.startsWith("//")) {
                result = separatorLine.substring(2);
            }
        }
        return result;
    }

}