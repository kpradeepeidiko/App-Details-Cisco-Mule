package testtrellobot;

import com.ciscospark.*;
import java.net.URI;

class Example {
  public static void main(String[] args) {
    // To obtain a developer access token, visit http://developer.ciscospark.com
    String accessToken = "MjQ2YzQwMDYtMDUyMy00MjE0LTliY2ItMWQxNWVkNjczOTFmMTlmNmQ1OWYtY2Qy";

    // Initialize the client
    Spark spark = Spark.builder()
      .baseUrl(URI.create("https://api.ciscospark.com/v1"))
      .accessToken(accessToken)
      .build();

    
    Room room = new Room();
    
    for(int i =0;spark.rooms().iterate().hasNext(); i++)
    {
    	System.out.println("i = "  + i);
    	System.out.println("room = " + spark.rooms());
    }
                		

    // Post a text message to the room
    //Message message = new Message();
    //message.setRoomId("5a9f59c0-71c8-11e7-8e03-1d7c7672da71");
    //message.setText("Hi all@1!");
    //spark.messages().post(message);
  }
}