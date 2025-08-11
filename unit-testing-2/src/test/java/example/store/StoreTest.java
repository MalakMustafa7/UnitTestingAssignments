package example.store;

import example.account.AccountManager;
import example.account.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StoreTest {

    @Test
    void buy_ShouldDecreaseQuantity_WhenProductInStockAndPaymentSuccess() {
        // Arrange
        AccountManager accountManager = new AlwaysSuccessAccountManager();
        Store store = new StoreImpl(accountManager);
        Product product = new Product();
        product.setQuantity(4);
        Customer customer = new Customer();

        // Act
        store.buy(product, customer);

        // Assert
        Assertions.assertEquals(3, product.getQuantity());
    }

    @Test
    void buy_ShouldThrowPaymentFailure_WhenProductInStockAndPaymentFails() {
        // Arrange
        AccountManager accountManager = new AlwaysFailedAccountManager();
        Store store = new StoreImpl(accountManager);
        Product product = new Product();
        product.setQuantity(4);
        Customer customer = new Customer();

        //Act + Assert
        try{
            store.buy(product, customer);
            Assertions.fail("Should throw exception");

        }catch (Exception ex){
            Assertions.assertEquals("Payment failure: Failed", ex.getMessage());
        }
        Assertions.assertEquals(4, product.getQuantity());
    }

    static class AlwaysSuccessAccountManager implements AccountManager {

        @Override
        public void deposit(Customer customer, int amount) {

        }

        @Override
        public String withdraw(Customer customer, int amount) {
            return "success";
        }
    }
    static class AlwaysFailedAccountManager implements AccountManager {

        @Override
        public void deposit(Customer customer, int amount) {

        }

        @Override
        public String withdraw(Customer customer, int amount) {
            return "Failed";
        }
    }

}
