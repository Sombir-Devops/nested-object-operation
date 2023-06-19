package com.test;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JsonKeyFinder {
  private static Gson GSON = new Gson();

  public static void main(String[] args) {
    String jsonStr = "{\"a\":{\"b\":{\"c\":\"d\"}}}";
    JsonObject json = GSON.fromJson(jsonStr, JsonObject.class);
    // Test case 1
    String key = "a/b";
    Object value = retrieveValue(json, key);
    System.out.println("Value for key " + key + ": " + value);

    // Test case 2
    String key1 = "a/b/c";
    Object value1 = retrieveValue(json, key1);
    System.out.println("Value for key " + key1 + ": " + value1);

    // Test case 3
    String key2 = "a/b/c/d";
    Object value2 = retrieveValue(json, key2);
    System.out.println("Value for key " + key2 + ": " + value2);

    // Test case 4
    String key3 = "a/d";
    Object value3 = retrieveValue(json, key3);
    System.out.println("Value for key " + key3 + ": " + value3);

    // Test case 5
    String key4 = "a/b/c/d/e";
    Object value4 = retrieveValue(json, key4);
    System.out.println("Value for key " + key4 + ": " + value4);
  }

  public static Object retrieveValue(JsonObject json, String key) {
    String[] keyParts = key.split("/");
    JsonObject currentObj = json;

    for (int i = 0; i < keyParts.length; i++) {
      String keyPart = keyParts[i];
      if (currentObj.has(keyPart)) {
        JsonElement obj = currentObj.get(keyPart);
        if (obj.isJsonObject()) {
          currentObj = obj.getAsJsonObject();
        } else {
          if (i == keyParts.length - 1) {
            return obj;
          } else {
            return null;    // Key not found
          }
        }
      } else {
        return null; // Key not found
      }
    }

    return currentObj;
  }
}

