
package com.example.customer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final List<Map<String, Object>> customers = List.of(
        Map.of("id", 101, "name", "Alex Johnson", "status", "ACTIVE"),
        Map.of("id", 102, "name", "Sam Taylor", "status", "ACTIVE")
    );

    @GetMapping
    public List<Map<String, Object>> getCustomers() {
        return customers;
    }

    @GetMapping("/{id}")
    public Map<String, Object> getCustomer(@PathVariable int id) {
        return customers.stream()
            .filter(customer -> customer.get("id").equals(id))
            .findFirst()
            .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    static class CustomerNotFoundException extends RuntimeException {
        CustomerNotFoundException(int id) {
            super("Customer not found: " + id);
        }
    }
}
