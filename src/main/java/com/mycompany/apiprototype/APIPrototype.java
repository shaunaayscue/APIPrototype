package com.mycompany.apiprototype;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
class APIPrototype {

    public static void main(String[] args) {
        getName();
        System.exit(0);
    }

    /**
     * Determines the probability of a person's gender from a name and prints to
     * console.
     */
    public static void getName() {
        String personName = "John";

        try {
            String url = "https://api.genderize.io/?name=" + personName;
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String findGender = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(findGender);

            String count = root.findValue("count").asText();
            String name = root.findValue("name").asText();
            String gender = root.findValue("gender").asText();
            String probabilty = root.findValue("probability").asText();

            System.out.println("---------Gender of a Person's Name---------");
            System.out.println("count: " + count);
            System.out.println("name: " + name);
            System.out.println("gender: " + gender);
            System.out.println("probabilty: " + probabilty);

        } catch (JsonProcessingException ex) {
            System.out.println("Error in getName: " + ex.toString());
        }
    }
}
