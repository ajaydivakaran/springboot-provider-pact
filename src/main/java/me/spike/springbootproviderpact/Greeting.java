package me.spike.springbootproviderpact;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Greeting {
    public String firstName;
    public String lastName;
    public String greeting;
}
