package util;

import java.util.HashMap;
import java.util.Map;

public class ApiRequestHelper {


    public static Map<String, Object> getCreateBookingApiRequest(String firstName, String lastName,
                                                                 int totalPrice, boolean depositPaid,
                                                                 String additionalNeeds,
                                                                 String checkInDate, String checkOutDate) {

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("firstname", firstName);
        requestBody.put("lastname", lastName);
        requestBody.put("totalprice", totalPrice);
        requestBody.put("depositpaid", depositPaid);
        requestBody.put("additionalneeds", additionalNeeds);

        //Create booking date map
        Map<String, Object> bookingDatesMap = new HashMap<>();
        bookingDatesMap.put("checkin", checkInDate);
        bookingDatesMap.put("checkout", checkOutDate);
        requestBody.put("bookingdates", bookingDatesMap);
        return requestBody;
    }
}
