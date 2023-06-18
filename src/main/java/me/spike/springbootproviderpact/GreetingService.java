package me.spike.springbootproviderpact;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {
    public Greeting greet(String firstName) {
        return new Greeting(firstName, null, "Hello World!!");
    }
}
