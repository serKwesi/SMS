public class Customer {
    
        private String first_name;
        private String last_name;    
        private String email_address;    
        private String phone_number;    
        private String address_line;
        
        
    
        // Constructor
        public Customer(String first_name, String last_name, String email_address, String phone_number, String address_line) {
            this.first_name = first_name;
            this.last_name = last_name;
            this.email_address = email_address;
            this.phone_number = phone_number;
            this.address_line = address_line;
        }
    
        // Getters and setters
        public String getFirstName() {
            return first_name;
        }
        public void setFirstName(String first_name) {
            this.first_name = first_name;
        }
        
        
        public String getLastName() {
            return last_name;
        }
        public void setLastName(String last_name) {
            this.last_name = last_name;
        }
        
        
        public String getEmailAddress() {
            return email_address;
        }
        public void setEmailAddress(String email_address) {
            this.email_address = email_address;
        }
        
        
        public String getPhoneNumber() {
            return phone_number;
        }
        public void setPhoneNumber(String phone_number) {
            this.phone_number = phone_number;
        }
        public String getAddressLine() {
        return address_line;
        }
        public void setAddressLine(String town) {
        this.address_line = address_line;
        }

    }
    


