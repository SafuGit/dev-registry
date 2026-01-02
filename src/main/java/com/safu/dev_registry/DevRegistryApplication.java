package com.safu.dev_registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvException;

@SpringBootApplication
public class DevRegistryApplication {

  public static void main(String[] args) {
    // Try loading .env locally, skip if not found (production safe)
    try {
      Dotenv dotenv = Dotenv.load();

      // Only set properties if they exist in .env
      setIfPresent("DATASOURCE_URL", dotenv.get("DATASOURCE_URL"));
      setIfPresent("DATASOURCE_NAME", dotenv.get("DATASOURCE_NAME"));
      setIfPresent("DATASOURCE_USERNAME", dotenv.get("DATASOURCE_USERNAME"));
      setIfPresent("DATASOURCE_PASSWORD", dotenv.get("DATASOURCE_PASSWORD"));
      setIfPresent("JWT_SECRET", dotenv.get("JWT_SECRET"));

      System.out.println(".env loaded successfully.");
    } catch (DotenvException e) {
      System.out.println(".env not found, skipping Dotenv loading.");
    }

    SpringApplication.run(DevRegistryApplication.class, args);
  }

  private static void setIfPresent(String key, String value) {
    if (value != null && !value.isBlank()) {
      System.setProperty(key, value);
    }
  }
}
