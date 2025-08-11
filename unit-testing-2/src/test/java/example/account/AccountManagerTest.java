package example.account;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountManagerTest {
    private AccountManager accountManager;
    private Customer customer;

    @BeforeEach
    void setUp(){
        accountManager = new AccountManagerImpl();
        customer = new Customer();
    }

    @Test
    void givenCustomerWithSufficientBalance_whenWithdraw_thenSucceed() {
        // Arrange
        customer.setBalance(1000);

        // Act
        String result = accountManager.withdraw(customer, 500);

        // Assert
        assertThat(result).isEqualTo("success");
        assertThat(customer.getBalance()).isEqualTo(500);
    }

    @Test
    void  givenInsufficientBalanceAndCreditNotAllowed_whenWithdraw_thenFail(){
        // Arrange
        customer.setBalance(500);

        //Act
        String result = accountManager.withdraw(customer,700);

        // Assert
        assertThat(result).isEqualTo("insufficient account balance");
        assertThat(customer.getBalance()).isEqualTo(500);
    }

    @Test
    void givenExpectedBalanceGreaterThanMaxCreditAndCustomerIsNotVip_whenWithdraw_thenFail(){
        // Arrange
        customer.setBalance(500);
        customer.setCreditAllowed(true);
        customer.setVip(false);

        // Act
       String result= accountManager.withdraw(customer,2000);

        //Assert
        assertThat(result).isEqualTo("maximum credit exceeded");
        assertThat(customer.getBalance()).isEqualTo(500);

    }
    @Test
    void givenUnderMaxCreditAndNotVipCustomer_whenWithdraw_thenSucceed(){
        // Arrange
        customer.setBalance(500);
        customer.setCreditAllowed(true);
        customer.setVip(false);

        // Act
        String result= accountManager.withdraw(customer,1000);

        //Assert
        assertThat(result).isEqualTo("success");
        assertThat(customer.getBalance()).isEqualTo(-500);
    }

    @Test
    void givenExpectedBalanceGreaterThanMaxCreditAndCustomerIsVip_whenWithdraw_thenSucceed(){
        //Arrange
        customer.setBalance(500);
        customer.setCreditAllowed(true);
        customer.setVip(true);

        // Act
        String result= accountManager.withdraw(customer,2000);

        //Assert
        assertThat(result).isEqualTo("success");
        assertThat(customer.getBalance()).isEqualTo(-1500);

    }
    @Test
    void givenUnderMaxCreditAndVipCustomer_whenWithdraw_thenSucceed(){
        // Arrange
        customer.setBalance(500);
        customer.setCreditAllowed(true);
        customer.setVip(true);

        // Act
        String result= accountManager.withdraw(customer,1000);

        //Assert
        assertThat(result).isEqualTo("success");
        assertThat(customer.getBalance()).isEqualTo(-500);
    }

    @Test
    void givenPositiveAmount_whenDeposit_thenSucceed(){
       //Arrange
        int amount =1000;
        //Act
        accountManager.deposit(customer,amount);

        //Assert
        assertThat(customer.getBalance()).isEqualTo(1000);

    }


}
