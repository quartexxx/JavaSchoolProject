package model;

import java.util.ArrayList;
import java.util.List;

/**
 * The FractionCalculatorModel class represents the model for a fraction
 * calculator. It keeps track of the calculation history.
 *
 * @author Remigiusz Kocialski
 * @version 1.0
 */
public class FractionCalculatorModel {

    // Private field to store the calculation history
    private final List<String> history;

    // Static instance for Singleton pattern
    private static FractionCalculatorModel instance;

    /**
     * Gets the instance of FractionCalculatorModel (Singleton pattern).
     *
     * @return The instance of FractionCalculatorModel.
     */
    public static FractionCalculatorModel getInstance() {
        if (instance == null) {
            instance = new FractionCalculatorModel();
        }

        return instance;
    }

    /**
     * Constructs a FractionCalculatorModel with an empty history.
     */
    public FractionCalculatorModel() {
        this.history = new ArrayList<>();
    }

    /**
     * Performs a fraction operation based on the given operation symbol.
     *
     * @param frac1 The first fraction.
     * @param operation The operation symbol (+, -, *, /).
     * @param frac2 The second fraction.
     * @return The result of the operation.
     * @throws InvalidValueException If the operation is invalid or other
     * calculation errors occur.
     */
    public Fraction performOperation(Fraction frac1, String operation, Fraction frac2) throws InvalidValueException {
        switch (operation) {
            case "+":
                return frac1.add(frac2);
            case "-":
                return frac1.sub(frac2);
            case "*":
                return frac1.mul(frac2);
            case "/":
                return frac1.div(frac2);
            default:
                throw new InvalidValueException("Invalid operation");
        }
    }

    /**
     * Gets a copy of the calculation history.
     *
     * @return The list containing the calculation history.
     */
    public List<String> getHistory() {
        return new ArrayList<>(history);
    }

    /**
     * Adds a new operation to the calculation history.
     *
     * @param operationStr The string representation of the operation.
     */
    public void addToHistory(String operationStr) {
        history.add(operationStr);
    }

    private int operationCounter;

    /**
     * Gets the total operation count.
     *
     * @return The total operation count.
     */
    public int getOperationCounter() {
        return operationCounter;
    }

    // Added method to set the operation counter
    /**
     * Sets the total operation count.
     *
     * @param count The new value for the operation counter.
     */
    public void setOperationCounter(int count) {
        operationCounter = count;
    }
}
