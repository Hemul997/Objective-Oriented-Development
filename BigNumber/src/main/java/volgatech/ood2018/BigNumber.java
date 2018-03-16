package volgatech.ood2018;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class BigNumber {
    final static int BIG_NUMBER_BASE = 10;
    final static char POSITIVE_NUMBER_SIGH = '+';
    final static char NEGATIVE_NUMBER_SIGH = '-';

    private List<Character> number;
    private char sign;

    public List<Character> getNumber() {
        return number;
    }

    public void setNumber(List<Character> number) {
        this.number = number;
    }

    public void setNumber(String strNumber) {
        this.number = strNumber.chars()
                .mapToObj(e->(char)e)
                .collect(Collectors.toList());
    }

    BigNumber(String strNumber){
        if (strNumber.charAt(0) == '-') {
            this.number = strNumber.substring(1, strNumber.length())
                    .chars()
                    .mapToObj(e->(char)e)
                    .collect(Collectors.toList());
            this.sign = NEGATIVE_NUMBER_SIGH;
        } else {
            this.number = strNumber.chars()
                    .mapToObj(e->(char)e)
                    .collect(Collectors.toList());
            this.sign = POSITIVE_NUMBER_SIGH;
        }

    }

    BigNumber() {
        this.number = new ArrayList<>();
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
    
    public int getCountDigits() {
        return this.number.size();
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
            case -1:
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
                if ((i == subtrahendNumber.size() && !getDigit) || (i == subtractiveNumber.size())) {
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
        char[] number = new char[thisNumber.size() + otherNumber.size()];
        int length = thisNumber.size() + otherNumber.size();
        for (int i = 0; i < thisNumber.size(); ++i) {
            for (int j = 0; j < otherNumber.size() ; ++j) {
                firstOperand = getNumberValue(thisNumber, i);
                secondOperand = getNumberValue(otherNumber, j);
                number[i + j] += firstOperand * secondOperand;
            }
        }
        for (int i = 0; i < length - 1; ++i) {
            number[i + 1] += number[i] / BIG_NUMBER_BASE;
            number[i] %= BIG_NUMBER_BASE;
        }
        for (char num : number) {
            returnValue.add(Character.forDigit(num, BIG_NUMBER_BASE));
        }
        Collections.reverse(returnValue);
        deleteRedundantZeros(returnValue);
        returnNumber.setNumber(returnValue);
        return returnNumber;
    }

//    //todo
//    public BigNumber divide(BigNumber value) throws Exception {
//
//        if (value.getNumber().get(0) == '0' && value.getCountDigits() == 1) {
//            throw new Exception("На ноль делить нельзя!");
//        }
//
//        if (this.getCountDigits() < value.getCountDigits()) {
//            return new BigNumber("0");
//        }
//
//
//    }

    @Override
    public String toString() {
        return "BigNumber{" +
                "number=" + number.toString() +
                '}';
    }

    public void printValue() {
        if (isNegative())
            System.out.print(this.sign);
        this.number.forEach(System.out::print);
    }

    private boolean nextDigit(int number) {
        return (number >= BIG_NUMBER_BASE);
    }

    private int getNumberValue(List<Character> thisNumber, int index) {
        return thisNumber.size() <= index ? 0 : Character.getNumericValue(thisNumber.get(index));
    }

    private int findMaxNumber(List<Character> firstNumber, List<Character> secondNumber) {
        int result = 0;
        if (secondNumber.size() < firstNumber.size()) {
            result = 1;
        } else {
            if (secondNumber.size() > firstNumber.size())
                result = -1;
            else
                for (int i = 0; i < firstNumber.size(); ++i) {
                    if (firstNumber.get(i) > secondNumber.get(i)) {
                        result = 1;
                        break;
                    }
                    if (firstNumber.get(i) < secondNumber.get(i)) {
                        result= -1;
                        break;
                    }
                }
        }
        return result;
    }

    private boolean isNegative() {
        return (this.sign == NEGATIVE_NUMBER_SIGH);
    }

    private void deleteRedundantZeros(List<Character> number) {
        for (int i = 0 ; i < number.size(); ++i) {
            if (!number.get(i).equals('0')) {
                break;
            } else {
                number.remove(i);
                --i;
            }
        }
    }
}
