package com.example.csvreader;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerApi {

    @PostMapping("/api/customers")
    public String postCustomer(@RequestBody String jsonData) {
        // Your implementation here
        return "Data received: " + jsonData;
    }

    @GetMapping("/api/customers/{customerRef}")
    public String getCustomer(@PathVariable String customerRef) {
        // Your implementation here
        return "com.example.csvreader.Customer reference: " + customerRef;
    }
}
