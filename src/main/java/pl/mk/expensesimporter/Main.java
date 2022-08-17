package pl.mk.expensesimporter;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class Main {
    public static void main(String[] args) throws GeneralSecurityException, IOException {
        ExpensesImporter expensesImporter = new ExpensesImporter();
        expensesImporter.run();
    }
}
