package me.spike.springbootproviderpact;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {
    public Greeting greet() {
        return new Greeting("Hello World!!");
    }
}
