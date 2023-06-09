
import com.zemoso.springdemo.controller.CustomerController;
import com.zemoso.springdemo.entity.Customer;
import com.zemoso.springdemo.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @Mock
    private Model model;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListCustomers() {
        // Create a list of customers
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("John", "Doe", "john@gmail.com"));
        customers.add(new Customer("Jane", "Smith", "smith@gmail.com"));

        // Mock the behavior of customerService.getCustomers()
        when(customerService.getCustomers()).thenReturn(customers);

        // Call the method under test
        String viewName = customerController.listCustomers(model);

        // Verify that the customerService.getCustomers() method was called
        verify(customerService, times(1)).getCustomers();

        // Verify that the customers were added to the model
        verify(model, times(1)).addAttribute("customers", customers);

        // Verify the view name
        assertEquals("list-customers", viewName);
    }

    @Test
    void testShowFormForAdd(){
        // Call the method under test
        String viewName = customerController.showFormForAdd(model);

        // Verify that a new Customer object was added to the model
        verify(model, times(1)).addAttribute(eq("customer"), any(Customer.class));

        // Verify the view name
        assertEquals("customer-form", viewName);
    }

    @Test
    void testSaveCustomer() {
        // Create a new customer
        Customer customer = new Customer();
        customer.setId(1);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        // You can set other properties of the customer as needed for your test

        // Call the saveCustomer method
        String result = customerController.saveCustomer(customer);

        // Verify that the customerService.saveCustomer method is called once with the customer object
        verify(customerService, times(1)).saveCustomer(customer);

        // Assert the expected result, which is the redirect view name
        assertEquals("redirect:/customer/list", result);
    }

    @Test
    void testShowFormForUpdate() {
        // Create a sample customer ID for testing
        int customerId = 1;

        // Create a mock customer object
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        // You can set other properties of the customer as needed for your test

        // Configure the mock customerService to return the customer when getCustomer is called with the specified ID
        when(customerService.getCustomer(customerId)).thenReturn(customer);

        // Call the showFormForUpdate method
        String result = customerController.showFormForUpdate(customerId, model);

        // Verify that the customerService.getCustomer method is called once with the specified customer ID
        verify(customerService, times(1)).getCustomer(customerId);

        // Verify that the model attribute "customer" is set with the customer object
        verify(model, times(1)).addAttribute("customer", customer);

        // Assert the expected result, which is the view name
        assertEquals("customer-form", result);
    }

    @Test
    void testDeleteCustomer() {
        // Create a sample customer ID for testing
        int customerId = 1;

        // Call the deleteCustomer method
        String result = customerController.deleteCustomer(customerId);

        // Verify that the customerService.deleteCustomer method is called once with the specified customer ID
        verify(customerService, times(1)).deleteCustomer(customerId);

        // Assert the expected result, which is the redirect view name
        assertEquals("redirect:/customer/list", result);
    }

}
