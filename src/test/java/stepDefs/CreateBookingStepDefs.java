package stepDefs;

import apis.CreateBookingApi;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import sharedContext.SharedStepContext;
import util.ApiRequestHelper;
import util.TestDataHelper;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

public class CreateBookingStepDefs {

    private CreateBookingApi createBookingApi;
    private final SharedStepContext sharedStepContext;
    private Map<String, Object> requestPayload;
    private Response createBookingApiResponse;

    public CreateBookingStepDefs(SharedStepContext sharedStepContext) {
        this.sharedStepContext = sharedStepContext;
        this.createBookingApi = new CreateBookingApi();
    }

    @Given("we have a valid request for create booking with following params")
    public void weHaveAValidRequestForCreateBookingWithFollowingParams(List<Map<String, String>> requestDataList) {
        var requestDataMap = requestDataList.getFirst();
        var dateFormatter = DateTimeFormatter.ISO_DATE;
        var checkInDate = TestDataHelper.getFutureDate(Integer.parseInt(requestDataMap.get("checkInPlusDays")),
                dateFormatter);
        var checkOutDate = TestDataHelper.getFutureDate(Integer.parseInt(requestDataMap.get("checkoutPlusDays")),
                dateFormatter);

        requestPayload = ApiRequestHelper.getCreateBookingApiRequest(
                requestDataMap.get("firstName"),
                requestDataMap.get("lastName"),
                Integer.parseInt(requestDataMap.get("totalPrice")),
                Boolean.parseBoolean(requestDataMap.get("depositPaid")),
                requestDataMap.get("additionalNeeds"),
                checkInDate, checkOutDate);
    }

    @Given("we have a valid request for create booking with following params as Map and total price {int}")
    public void weHaveAValidRequestForCreateBookingWithFollowingParamsAsMap(int totalPrice, Map<String, String> requestDataList) {
        var dateFormatter = DateTimeFormatter.ISO_DATE;
        var checkInDate = TestDataHelper.getFutureDate(Integer.parseInt(requestDataList.get("checkInPlusDays")),
                dateFormatter);
        var checkOutDate = TestDataHelper.getFutureDate(Integer.parseInt(requestDataList.get("checkoutPlusDays")),
                dateFormatter);

        requestPayload = ApiRequestHelper.getCreateBookingApiRequest(
                requestDataList.get("firstName"),
                requestDataList.get("lastName"),
                totalPrice,
                Boolean.parseBoolean(requestDataList.get("depositPaid")),
                requestDataList.get("additionalNeeds"),
                checkInDate, checkOutDate);

        this.sharedStepContext.createBookingApiRequest = requestPayload;
    }

    @When("we send request to create booking API")
    public void weSendRequestToCreateBookingAPI() {
        this.createBookingApiResponse = this.createBookingApi.createNewBooking(this.requestPayload);
        this.sharedStepContext.apiResponse = this.createBookingApiResponse;
    }

    @And("create booking API response has valid booking ID")
    public void createBookingAPIResponseHasValidBookingID() {
        this.createBookingApiResponse.then()
                                     .body("bookingid", is((greaterThan(0))));
    }

    @When("booking id has been saved in shared context")
    public void bookingIdHasBeenSavedInSharedContext() {
        this.sharedStepContext.bookingId = this.createBookingApiResponse.jsonPath().getInt("bookingid");
    }
}
