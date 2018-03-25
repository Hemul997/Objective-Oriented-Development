package volgatech.ood2018.bignumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BigNumber {

    private final static int BIG_NUMBER_BASE = 10;

    private List<Character> value = new ArrayList<>();

    public BigNumber() {

    }

    public BigNumber(List<Character> value) {
        this.value = new ArrayList<>(value);
    }

    public BigNumber(String value) {
        this.value = value.chars()
                .mapToObj(e->(char)e)
                .collect(Collectors.toList());
        Collections.reverse(this.value);
        deleteRedundantZeros();
    }

    private int getNumberValue(List<Character> thisNumber, int index) {
        return thisNumber.size() <= index ? 0 : Character.getNumericValue(thisNumber.get(index));
    }

    private boolean nextDigit(int number) {
        return (number >= BIG_NUMBER_BASE);
    }

    public BigNumber add(BigNumber addNumber) {
        BigNumber returnNumber = new BigNumber();
        List<Character> returnValue = new ArrayList<>();

        int additionResult;
        int firstOperand, secondOperand;
        Boolean isDigit = false;
        int maxSize = Math.max(this.size(), addNumber.size()) + 1;
        for (int i = 0; i < maxSize; ++i) {
            firstOperand = getNumberValue(this.value, i);
            secondOperand = getNumberValue(addNumber.value, i);
            additionResult = firstOperand + secondOperand;
            if (isDigit) {
                isDigit = false;
                ++additionResult;
            }
            if (nextDigit(additionResult)) {
                isDigit = true;
                additionResult -= BIG_NUMBER_BASE;
            }
            returnValue.add(Character.forDigit(additionResult, BIG_NUMBER_BASE));
        }
        if (returnValue.get(maxSize - 1) == '0') {
            returnValue.remove(maxSize - 1);
        }

        returnNumber.setNumber(returnValue);
        return returnNumber;
    }

    public void setNumber(List<Character> number) {
        this.value.addAll(number);
    }

    public BigNumber subtract(BigNumber subNumber) {
        List<Character> returnValue = new ArrayList<>();

        setSameSize(subNumber);
        boolean isLastDigit = false;
        for (int i = 0; i < subNumber.size(); i++) {
            int firstOperand = getNumberValue(this.value, i);
            int secondOperand = getNumberValue(subNumber.value, i);
            int subResult = firstOperand - secondOperand;

            if (isLastDigit) {
                subResult--;
                isLastDigit = false;
            }

            if (subResult < 0) {
                isLastDigit = true;
                subResult += BIG_NUMBER_BASE;
            }
            returnValue.add(Character.forDigit(subResult, BIG_NUMBER_BASE));
        }

        if (isLastDigit) {
            returnValue.add('0');
        }

        BigNumber returnNumber = new BigNumber(returnValue);
        returnNumber.deleteRedundantZeros();
        return returnNumber;

    }

    public BigNumber multiply(BigNumber number) {
        BigNumber returnNumber = new BigNumber();
        List<Character> thisNumber = new ArrayList<>(this.value);
        List<Character> otherNumber = new ArrayList<>(number.value);
        List<Character> returnValue = new ArrayList<>();

        int firstOperand, secondOperand;
        char[] num = new char[thisNumber.size() + otherNumber.size()];
        int length = thisNumber.size() + otherNumber.size();
        for (int i = 0; i < thisNumber.size(); ++i) {
            for (int j = 0; j < otherNumber.size() ; ++j) {
                firstOperand = getNumberValue(thisNumber, i);
                secondOperand = getNumberValue(otherNumber, j);
                num[i + j] += firstOperand * secondOperand;
            }
        }
        for (int i = 0; i < length - 1; ++i) {
            num[i + 1] += num[i] / BIG_NUMBER_BASE;
            num[i] %= BIG_NUMBER_BASE;
        }
        for (char ch : num) {
            returnValue.add(Character.forDigit(ch, BIG_NUMBER_BASE));
        }

        returnNumber.setNumber(returnValue);
        returnNumber.deleteRedundantZeros();
        return returnNumber;
    }

    public BigNumber divide(BigNumber number) throws Exception {
        if (number.compareTo(new BigNumber("0")) == 0) {
            throw new Exception("Division by ZERO!!!");
        }

        if (this.size() < number.size()) {
            return new BigNumber("0");
        }

        List<Character> returnValue = new ArrayList<>();
        BigNumber thisNumber = new BigNumber(new ArrayList<>(this.value));

        int digitCount = 0;
        while (!thisNumber.value.isEmpty()) {
            BigNumber dividend = createNumberForDivision(thisNumber, number, returnValue, digitCount);
            dividend.deleteRedundantZeros();
            digitCount = 0;
            BigNumber multiplicationResult = new BigNumber("0");
            for (int divCoefficient = BIG_NUMBER_BASE - 1; divCoefficient >= 0; divCoefficient--) {
                multiplicationResult = number.multiply(new BigNumber(Integer.toString(divCoefficient)));
                if (multiplicationResult.compareTo(dividend) != 1) {
                    returnValue.add(Character.forDigit(divCoefficient, BIG_NUMBER_BASE));
                    break;
                }
            }
            BigNumber iterRes = dividend.subtract(multiplicationResult);

            if (iterRes.compareTo(new BigNumber("0")) != 0 && !thisNumber.value.isEmpty()) {
                digitCount = iterRes.size();
                for (int i = 0; i < iterRes.size(); i++) {
                    thisNumber.value.add(iterRes.value.get(i));
                }
            }
        }

        Collections.reverse(returnValue);
        BigNumber returnNumber = new BigNumber(returnValue);
        returnNumber.deleteRedundantZeros();
        return returnNumber;
    }

    private List<String> divideByNumbers() {
        List<String> numbers = new ArrayList<>();
        for (int i = 0; i < this.size(); i += 2) {
            String digit1 = "";
            String digit2 = "";

            if (i < this.size() - 1) {
                digit1 = this.value.get(i + 1).toString();
            }

            if (i < this.size()) {
                digit2 = this.value.get(i).toString();
            }

            String digits = digit1 + digit2;
            numbers.add(digits);
        }
        return numbers;
    }

    public BigNumber sqrt() throws IllegalArgumentException {
        List<Integer> sqrt = new ArrayList<>();
        // Делим число по 2 цифры начиная слева
        List<String> numbers = divideByNumbers();
        int iterCount = numbers.size() - 1;

        int initDefaultValue = Integer.parseInt(numbers.get(numbers.size() - 1));
        numbers.remove(numbers.size() - 1);

        int firstSqrtDigit = (int) Math.sqrt(initDefaultValue);
        sqrt.add(firstSqrtDigit);

        BigNumber defaultValue = new BigNumber(String.valueOf(initDefaultValue));
        BigNumber sqrtValue = new BigNumber(String.valueOf(firstSqrtDigit));
        // Здесь подсчитывается корень из большого числа
        // Сначала возводим текущее значение корня в квадрат
        BigNumber valueSqr = sqrtValue.multiply(sqrtValue);
        // Шаг по алгоритму, получаем первое число нового значения под корнем
        BigNumber firstValueForNumUnderRoot = defaultValue.subtract(valueSqr);
        for (int i = 0; i < iterCount; i++) {
            // Выносим следующее число к новому значению
            String newPartOfValueUnderRoot = "";
            BigNumber currValueUnderRoot = defaultValue;
            if (!numbers.isEmpty()) {
                // "Опускаем" следующую пару чисел к текущему числу под корнем
                newPartOfValueUnderRoot = String.valueOf(firstValueForNumUnderRoot) + String.valueOf(numbers.get(numbers.size() - 1));
                numbers.remove(numbers.size() - 1);
                // Создали число, которое будет "под корнем", из него вычитаем то, что выйдет при подборе
                currValueUnderRoot = new BigNumber(newPartOfValueUnderRoot);
            }

            // значение корня умножили на 2, по алгоритму
            sqrtValue = sqrtValue.multiply(new BigNumber("2"));
            int newDigit = 0;
            BigNumber subtrahend = new BigNumber("0");
            for (int digit = 9; digit >= 0; digit--) {
                // конструируем число, подбираем коэффициент
                subtrahend = new BigNumber(String.valueOf(sqrtValue) + String.valueOf(digit));
                BigNumber comparisonValue = subtrahend.multiply(new BigNumber(String.valueOf(digit)));
                if (comparisonValue.compareTo(currValueUnderRoot) <= 0) {
                    newDigit = digit;
                    sqrt.add(newDigit);
                    break;
                }
            }

            if (numbers.isEmpty()) {
                break;
            }

            BigNumber newDefaultValue = currValueUnderRoot.subtract(subtrahend.multiply(new BigNumber(String.valueOf(newDigit))));
            defaultValue = newDefaultValue;
            sqrtValue = calculateNewValue(sqrt);
        }

        String sqrtString = "";
        for (int i = 0; i < sqrt.size(); i++) {
            sqrtString += String.valueOf(sqrt.get(i));
        }

        return new BigNumber(sqrtString);
    }

    public BigNumber pow(int powNumber) {
        BigNumber number = new BigNumber(new ArrayList<>(this.value));
        BigNumber thisValue = new BigNumber(new ArrayList<>(this.value));
        BigNumber returnValue = new BigNumber();

        for (int i = 1; i < powNumber; ++i) {
            returnValue = number.multiply(thisValue);
            number = new BigNumber(new ArrayList<>(returnValue.value));
        }

        return returnValue;
    }

    private BigNumber calculateNewValue(List<Integer> numbers) {
        BigNumber number = new BigNumber("0");
        for (int i = 0; i < numbers.size(); i++) {
            number = number.multiply(new BigNumber("10")).add(new BigNumber(String.valueOf(numbers.get(i))));
        }
        return number;
    }

    private BigNumber createNumberForDivision(BigNumber mainDividend, BigNumber divider,
                                      List<Character> newNumberData, int digitCount) {
        String valueForDiv = "";
        for (int i = 0; i <= digitCount && mainDividend.size() != 0; i++) {
            valueForDiv += mainDividend.value.get(mainDividend.size() - 1).toString();
            mainDividend.value.remove(mainDividend.size() - 1);
        }

        BigNumber dividend = new BigNumber(valueForDiv);
        do {
            if (dividend.compareTo(divider) >= 0 || mainDividend.value.isEmpty()) {
                break;
            }
            if (digitCount >= 0) {
                newNumberData.add('0');
            }
            int lastElement = mainDividend.size() - 1;
            dividend.value.add(0, mainDividend.value.get(lastElement));
            mainDividend.value.remove(lastElement);
        } while (!mainDividend.value.isEmpty());

        return dividend;
    }

    @Override
    public String toString() {
        StringBuilder value = new StringBuilder();
        for (int i = this.value.size() - 1; i >= 0; i--) {
            value.append(this.value.get(i));
        }
        return value.toString();
    }

    private void deleteRedundantZeros() {
        for (int i = this.size() - 1; this.size() > 1 && i >= 0; i--) {
            if (value.get(i) != '0') {
                break;
            } else {
                value.remove(i);
            }
        }
    }

    public int size() {
        return value.size();
    }

    public int compareTo(BigNumber number) {
        int result = 0;
        if (this.size() > number.size()) {
            result = 1;
        } else if (this.size() < number.size()) {
            result = -1;
        } else {
            for (int i = number.size() - 1; i >= 0; i--) {
                if (this.value.get(i) > number.value.get(i)) {
                    result = 1;
                    break;
                } else if (this.value.get(i) < number.value.get(i)) {
                    result = -1;
                    break;
                }
            }
        }
        return result;
    }

    private void upSizeTo(int size) {
        if (this.size() < size) {
            while (this.size() != size) {
                this.value.add('0');
            }
        }
    }

    private void setSameSize(BigNumber number) {
        if (number.size() > this.size()) {
            this.upSizeTo(number.size());
        } else {
            number.upSizeTo(this.size());
        }
    }
}