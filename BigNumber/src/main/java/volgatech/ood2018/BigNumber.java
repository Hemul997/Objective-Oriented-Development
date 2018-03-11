package volgatech.ood2018;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class BigNumber {
    final static int BIG_NUMBER_BASE = 10;
    final static char POSITIVE_NUMBER_SIGH = '+';
    final static char NEGATIVE_NUMBER_SIGH = '-';

    private List<Character> numberber;
    private char sign;

    public List<Character> getNumber() {
        return numberber;
    }

    public void setNumber(List<Character> numberber) {
        this.numberber = numberber;
    }

    BigNumber(String strNumber){
        if (strNumber.charAt(0) == '-') {
            this.numberber = strNumber.substring(1, strNumber.length() - 1)
                    .chars()
                    .mapToObj(e->(char)e)
                    .collect(Collectors.toList());
            this.sign = NEGATIVE_NUMBER_SIGH;
        } else {
            this.numberber = strNumber.chars()
                    .mapToObj(e->(char)e)
                    .collect(Collectors.toList());
            this.sign = POSITIVE_NUMBER_SIGH;
        }

    }

    BigNumber() {
        this.numberber = new ArrayList<>();
        this.sign = POSITIVE_NUMBER_SIGH;
    }


    public BigNumber add(BigNumber value) {
        BigNumber returnNumber = new BigNumber();
        List<Character> thisNumber = new ArrayList<>(this.getNumber());
        List<Character> otherNumber = new ArrayList<>(value.getNumber());
        Collections.reverse(thisNumber);
        Collections.reverse(otherNumber);
        List<Character> returnValue = new ArrayList<>();
        int number;
        int firstOperand, secondOperand;
        Boolean isDigit = FALSE;
        int maxSize = Math.max(thisNumber.size(), otherNumber.size()) + 1;
        for (int i = 0; i < maxSize; ++i) {
            firstOperand = getNumberValue(thisNumber, i);
            secondOperand = getNumberValue(otherNumber, i);
            number = firstOperand + secondOperand;
            if (isDigit) {
                isDigit = FALSE;
                ++number;
            }
            if (nextDigit(number)) {
                isDigit = TRUE;
                number -= BIG_NUMBER_BASE;
            }
            returnValue.add(Character.forDigit(number, BIG_NUMBER_BASE));
        }
        if (returnValue.get(maxSize - 1) == '0') {
            returnValue.remove(maxSize- 1);
        }
        Collections.reverse(returnValue);
        returnNumber.setNumber(returnValue);
        return returnNumber;
    }

    public char getSign() {
        return sign;
    }

    public void setSign(char sign) {
        if (sign == NEGATIVE_NUMBER_SIGH) {
            this.sign = NEGATIVE_NUMBER_SIGH;
        } else {
            this.sign = POSITIVE_NUMBER_SIGH;
        }

    }

    public BigNumber subtract(BigNumber value) {
        int number;
        int firstOperand, secondOperand;
        BigNumber returnNumber = new BigNumber();
        List<Character> subtractiveNumber = new ArrayList<>();
        List<Character> subtrahendNumber = new ArrayList<>();
        List<Character> returnValue = new ArrayList<>();
        switch (findMaxNumber(this.getNumber(), value.getNumber())) {
            case 0:
                returnValue.add('0');
                break;
            case 1:
                subtractiveNumber.addAll(this.getNumber());
                subtrahendNumber.addAll(value.getNumber());
                break;
            case 2:
                subtractiveNumber.addAll(value.getNumber());
                subtrahendNumber.addAll(this.getNumber());
                returnNumber.setSign(NEGATIVE_NUMBER_SIGH);
                break;
        }
        if (returnValue.isEmpty()) {
            Collections.reverse(subtractiveNumber);
            Collections.reverse(subtrahendNumber);

            Boolean getDigit = FALSE;
            for (int i = 0; i <= subtrahendNumber.size(); ++i) {
                if (i == subtrahendNumber.size() && !getDigit) {
                    break;//fix bug with 105 - 6
                }
                firstOperand = getNumberValue(subtractiveNumber, i);
                secondOperand = getNumberValue(subtrahendNumber, i);
                if (getDigit) {
                    --firstOperand;
                }
                if (firstOperand < secondOperand) {
                    firstOperand += BIG_NUMBER_BASE;
                    getDigit = TRUE;
                }
                number = firstOperand - secondOperand;
                returnValue.add(Character.forDigit(number, BIG_NUMBER_BASE));
            }
            Collections.reverse(returnValue);
            deleteRedundantZeros(returnValue);
        }
        returnNumber.setNumber(returnValue);
        return returnNumber;
    }

    public BigNumber multiply(BigNumber value) {
        BigNumber returnNumber = new BigNumber();
        if (this.isNegative() || value.isNegative()) {
            returnNumber.setSign(NEGATIVE_NUMBER_SIGH);
        }
        List<Character> thisNumber = new ArrayList<>(this.getNumber());
        List<Character> otherNumber = new ArrayList<>(value.getNumber());
        Collections.reverse(thisNumber);
        Collections.reverse(otherNumber);
        List<Character> returnValue = new ArrayList<>();
        int firstOperand, secondOperand;
        char[] numberber = new char[thisNumber.size() + otherNumber.size()];
        int length = thisNumber.size() + otherNumber.size();
        for (int i = 0; i < thisNumber.size(); ++i) {
            for (int j = 0; j < otherNumber.size() ; ++j) {
                firstOperand = getNumberValue(thisNumber, i);
                secondOperand = getNumberValue(otherNumber, j);
                numberber[i + j] += firstOperand * secondOperand;
            }
        }
        for (int i = 0; i < length - 1; ++i) {
            numberber[i + 1] += numberber[i] / BIG_NUMBER_BASE;
            numberber[i] %= BIG_NUMBER_BASE;
        }
        for (int numberb : numberber) {
            returnValue.add(Character.forDigit(numberb, BIG_NUMBER_BASE));
        }
        Collections.reverse(returnValue);
        deleteRedundantZeros(returnValue);
        returnNumber.setNumber(returnValue);
        return returnNumber;
    }

    //TODO
    public BigNumber divide(BigNumber value) {
        BigNumber returnValue = new BigNumber();
        return returnValue;
    }

    @Override
    public String toString() {
        return "BigNumber{" +
                "numberber=" + numberber.toString() +
                '}';
    }

    public void printValue() {
        if (isNegative())
            System.out.print(this.sign);
        this.numberber.forEach(System.out::print);
    }

    private boolean nextDigit(int number) {
        return (number >= BIG_NUMBER_BASE);
    }

    private int getNumberValue(List<Character> thisNumber, int index) {
        return thisNumber.size() <= index ? 0 : Character.getNumericValue(thisNumber.get(index));
    }

    private int findMaxNumber(List<Character> firstNumber, List<Character> secondNumber) {
        int numberber = 0;
        if (secondNumber.size() < firstNumber.size()) {
            numberber = 1;
        } else {
            if (secondNumber.size() > firstNumber.size())
                numberber = 2;
            else
                for (int i = 0; i < firstNumber.size(); ++i) {
                    if (firstNumber.get(i) > secondNumber.get(i)) {
                        numberber = 1;
                        break;
                    }
                    if (firstNumber.get(i) < secondNumber.get(i)) {
                        numberber = 2;
                        break;
                    }
                }
        }
        return numberber;
    }

    private boolean isNegative() {
        return (this.sign == NEGATIVE_NUMBER_SIGH);
    }

    private void deleteRedundantZeros(List<Character> numberber) {
        for (int i = 0 ; i < numberber.size(); ++i) {
            if (!numberber.get(i).equals('0')) {
                break;
            } else {
                numberber.remove(i);
                --i;
            }
        }
    }
}
