package volgatech.ood2018;

public class Main {
    public static void main(String[] args) throws Exception{
//        String firstOperand = "55555551";
//        String secondOperand = "55555555";
        //String secondOperand = "22";
        //String firstOperand = "-10101010101010101010101010101010101010";
        String firstOperand = "50000000001";
        String secondOperand = "15555555551";
        BigNumber firstNumber = new BigNumber(firstOperand);
        BigNumber secondNumber = new BigNumber(secondOperand);
        BigNumber sumNumbers = firstNumber.subtract(secondNumber);
        firstNumber.printValue();
        System.out.println();
        secondNumber.printValue();
        System.out.println();
        sumNumbers.printValue();
//        System.out.println(bigNumber.toString());
//        System.out.println(sumNumbers.toString());
    }
}
