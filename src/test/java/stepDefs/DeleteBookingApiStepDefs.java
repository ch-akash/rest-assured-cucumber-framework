package stepDefs;

import apis.DeleteBookingApi;
import io.cucumber.java.en.And;
import sharedContext.SharedStepContext;

public class DeleteBookingApiStepDefs {

    private final DeleteBookingApi deleteBookingApi;
    private final SharedStepContext sharedStepContext;

    public DeleteBookingApiStepDefs(SharedStepContext sharedStepContext) {
        this.sharedStepContext = sharedStepContext;
        this.deleteBookingApi = new DeleteBookingApi();
    }

    @And("we send request to delete booking API")
    public void weSendRequestToDeleteBookingAPI() {
        var bookingId = this.sharedStepContext.bookingId;
        var username = System.getenv("RESTBOOKER_USERNAME");
        var password = System.getenv("RESTBOOKER_PASSWORD");
        this.sharedStepContext.apiResponse = this.deleteBookingApi.deleteBookingById(bookingId, username, password);
    }
}
