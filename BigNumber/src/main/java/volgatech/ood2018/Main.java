package volgatech.ood2018;

public class Main {
    public static void main(String[] args) {
//        String firstOperand = "55555551";
//        String secondOperand = "55555555";
        String firstOperand = "-10101010101010101010101010101010101010";
        String secondOperand = "22";
        BigNumber firstNumber = new BigNumber(firstOperand);
        BigNumber secondNumber = new BigNumber(secondOperand);
        BigNumber sumNumbers = firstNumber.multiply(secondNumber);

        firstNumber.printValue();
        System.out.println();
        secondNumber.printValue();
        System.out.println();
        sumNumbers.printValue();
//        System.out.println(bigNumber.toString());
//        System.out.println(sumNumbers.toString());
    }
}
