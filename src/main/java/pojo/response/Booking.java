package pojo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import pojo.request.BookingDates;

@Data
public class Booking {

    @JsonProperty("firstname")
    private String firstName;

    @JsonProperty("additionalneeds")
    private String additionalNeeds;

    @JsonProperty("bookingdates")
    private BookingDates bookingDates;

    @JsonProperty("totalprice")
    private int totalPrice;

    @JsonProperty("depositpaid")
    private boolean depositPaid;

    @JsonProperty("lastname")
    private String lastName;
}