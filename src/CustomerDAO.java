import java.util.List;

public interface CustomerDAO {

        /**
         *
         * @param customer
         * @return
         */
        int insertCustomer(Customer customer);
        Customer getCustomerById(int id);
    //    List<Customer> getAllCustomers();
    //    void updateCustomer(Customer customer);
    //    void deleteCustomer(int id);
    }
    


