package volgatech.ood2018;

public class BigNumberController {
    private BigNumber firstArg, secondArg;
    private char operation;

    void setParams(String line) {

        this.firstArg = new BigNumber(parseArgs(line)[0]);
        this.secondArg = new BigNumber(parseArgs(line)[2]);
        this.operation = parseArgs(line)[1].charAt(0);
    }

    public String processResult() {
        StringBuilder builder = new StringBuilder();
        switch (operation) {
            case '+':
                builder.append(firstArg.add(secondArg).toString());
                break;
            case '-':
                builder.append(firstArg.subtract(secondArg).toString());
                break;
            case '*':
                builder.append(firstArg.multiply(secondArg).toString());
                break;
            case '/':
                try {
                    builder.append(firstArg.divide(secondArg).toString());
                } catch (Exception e) {
                    builder.append(e.getMessage());
                }
                break;
        }
        return builder.toString();
    }

    private String[] parseArgs(String line) {
        return line.split(" ");
    }
}
