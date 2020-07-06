# json-parser
In order to interact with specific APIs, which use json as a message format, i have created a minimalist Json parser in Java.  
This projects main goal was to teach myself more about parsing techniques and character encodings, I do not expect other people to find this particularly useful.  
However, any feedback is greatly appreciated.

You can use the `JsonFactory.getJsonObject(String json)` Method to receive a new JsonObject with the respective contents.
There are multiple Classes implementing the JsonElement Interface:
 - JsonList  
 represents an Array/List in a Json file.
 - JsonObject  
 represents an Object, mapping Strings to JsonElements.
 - JsonString  
 represents a String in a Json file.
 - JsonBool  
 represents a Bool in a Json file.
 - JsonNull  
 represents a Null in a Json file, wrapper class used to turn it into a valid String notation later on.
 - JsonNumber  
 represents a Number in a Json file.
   
   
Each JsonElement has a `formatJson()` Method to turn the Element into Json text notation.

__Known issues:__  
Escaped unicode characters with a value greater than 0xFFFF will not be correctly unescaped.
