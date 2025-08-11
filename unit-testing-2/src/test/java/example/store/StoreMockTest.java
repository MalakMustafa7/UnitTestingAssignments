package example.store;

import example.account.AccountManager;
import example.account.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;
import java.lang.reflect.AccessibleObject;

public class StoreMockTest {
     @Test
     void buy_ShouldDecreaseQuantity_WhenProductInStockAndPaymentSuccess() {
         //Arrange
         Customer customer = new Customer();
         Product product = new Product();
         product.setQuantity(4);
         product.setPrice(500);
         AccountManager accountManager = mock(AccountManager.class);
         when(accountManager.withdraw(eq(customer),anyInt())).thenReturn("success");
         Store store = new StoreImpl(accountManager);

         // Act
         store.buy(product,customer);

         //Assert
        assertThat(product.getQuantity()).isEqualTo(3);

     }
     @Test
     void buy_ShouldThrowPaymentFailure_WhenProductInStockAndPaymentFails(){
         //Arrange
         Customer customer = new Customer();
         Product product = new Product();
         product.setQuantity(4);
         product.setPrice(500);
         AccountManager  accountManager = mock(AccountManager.class);
         when(accountManager.withdraw(eq(customer),anyInt())).thenReturn("Failed");
         Store store = new StoreImpl(accountManager);

         //Act + Assert

         assertThatThrownBy(()->store.buy(product,customer))
                            .isInstanceOf(RuntimeException.class)
                             .hasMessageContaining("Payment failure: Failed");

         assertThat(product.getQuantity()).isEqualTo(4);

     }

    @Test
    void buy_ShouldThrowOutOfStock_WhenProductOutOfStock() {
        //Arrange
        Customer customer = new Customer();
        Product product = new Product();
        product.setQuantity(0);
        product.setPrice(500);
        AccountManager accountManager = mock(AccountManager.class);
        when(accountManager.withdraw(eq(customer), anyInt())).thenReturn("success");
        Store store = new StoreImpl(accountManager);

        //Act+Assert

        assertThatThrownBy(() -> store.buy(product, customer))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Product out of stock");
    }


    }
