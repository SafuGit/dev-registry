package com.safu.dev_registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class DevRegistryApplication {

  public static void main(String[] args) {
    Dotenv dotenv = Dotenv.load();

    System.setProperty("DATASOURCE_URL", dotenv.get("DATASOURCE_URL"));
    System.setProperty("DATASOURCE_NAME", dotenv.get("DATASOURCE_NAME"));
    System.setProperty("DATASOURCE_USERNAME", dotenv.get("DATASOURCE_USERNAME"));
    System.setProperty("DATASOURCE_PASSWORD", dotenv.get("DATASOURCE_PASSWORD"));
    System.setProperty("JWT_SECRET", dotenv.get("JWT_SECRET"));

    SpringApplication.run(DevRegistryApplication.class, args);
  }

}
