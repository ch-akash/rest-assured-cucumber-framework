package sharedContext;

import io.restassured.response.Response;

import java.util.Map;

public class SharedStepContext {
    public Response apiResponse;
    public int bookingId;
    public Map<String, Object> createBookingApiRequest;
}
