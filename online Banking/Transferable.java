public interface Transferable {
    int transferAmount(String accountNumber, float amount, String operand);

    float getBalance(String accountNumber);

    boolean isActive(String accountNumber) throws AccountNotFoundException;

    boolean isBeneficiaryAccount(int userId, String accountNumber);

    boolean accountNumberExist(String accountNumber) throws AccountNotFoundException;
}
