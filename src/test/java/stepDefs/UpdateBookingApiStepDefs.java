package stepDefs;

import apis.UpdateBookingApi;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import sharedContext.SharedStepContext;
import util.ApiRequestHelper;
import util.TestDataHelper;

import java.time.format.DateTimeFormatter;
import java.util.Map;

public class UpdateBookingApiStepDefs {

    private final UpdateBookingApi updateBookingApi;
    private final SharedStepContext sharedStepContext;
    private Map<String, Object> requestPayload;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_DATE;

    public UpdateBookingApiStepDefs(SharedStepContext sharedStepContext) {
        this.sharedStepContext = sharedStepContext;
        this.updateBookingApi = new UpdateBookingApi();
    }

    @When("we prepare request for update booking API")
    public void wePrepareRequestForUpdateBookingAPI(Map<String, String> requestMap) {
        var checkInDate = TestDataHelper.getFutureDate(Integer.parseInt(requestMap.get("checkInPlusDays")), this.dateFormatter);
        var checkOutDate = TestDataHelper.getFutureDate(Integer.parseInt(requestMap.get("checkoutPlusDays")), this.dateFormatter);
        var totalPrice = Integer.parseInt(requestMap.get("totalPrice"));
        this.requestPayload = ApiRequestHelper.getCreateBookingApiRequest(
                requestMap.get("firstName"),
                requestMap.get("lastName"),
                totalPrice,
                Boolean.parseBoolean(requestMap.get("depositPaid")),
                requestMap.get("additionalNeeds"),
                checkInDate, checkOutDate);
    }

    @And("we send request to update booking API")
    public void weSendRequestToUpdateBookingAPI() {
        var bookingId = this.sharedStepContext.bookingId;
        var username = System.getenv("RESTBOOKER_USERNAME");
        var password = System.getenv("RESTBOOKER_PASSWORD");
        this.sharedStepContext.apiResponse = this.updateBookingApi.updateBooking(this.requestPayload,
                bookingId, username, password);
    }
}
