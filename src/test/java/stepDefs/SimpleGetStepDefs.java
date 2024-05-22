package stepDefs;

import apis.GetBookingApi;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import sharedContext.SharedStepContext;

import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class SimpleGetStepDefs {

    private GetBookingApi getBookingApi;
    private final SharedStepContext sharedStepContext;

    public SimpleGetStepDefs(SharedStepContext sharedStepContext) {
        this.sharedStepContext = sharedStepContext;
        this.getBookingApi = new GetBookingApi();
    }

    @Given("I prepare a simple HTTP GET request")
    public void iPrepareASimpleHTTPGETRequest() {
        this.getBookingApi = new GetBookingApi();
    }

    @When("I send the request to API")
    public void iSendTheRequestToAPI() {
        this.sharedStepContext.apiResponse = this.getBookingApi.getAllBookingIds();
    }

    @When("we retrieve the booking using booking id")
    public void weRetrieveTheBookingUsingBookingId() {
        this.sharedStepContext.apiResponse = this.getBookingApi.getBookingById(this.sharedStepContext.bookingId);
    }

    @SuppressWarnings("unchecked")
    @And("getBooking API response should have fields same as create request")
    public void getBookingAPIResponseShouldHaveFieldsSameAsCreateRequest() {
        var createBookingRequest = this.sharedStepContext.createBookingApiRequest;
        var bookingDates = (Map<String, String>) createBookingRequest.get("bookingdates");
        this.validateRetrievedBookingDataFromGetApi(
                (String) createBookingRequest.get("firstname"),
                (String) createBookingRequest.get("lastname"),
                (Boolean) createBookingRequest.get("depositpaid"),
                (String) createBookingRequest.get("additionalneeds"),
                (Integer) createBookingRequest.get("totalprice"),
                bookingDates.get("checkin"),
                bookingDates.get("checkout"),
                this.sharedStepContext.apiResponse);
    }

    private void validateRetrievedBookingDataFromGetApi(String firstName, String lastName, Boolean depositPaid,
                                                        String additionalNeeds, Integer totalPrice, String checkInDate,
                                                        String checkOutDate, Response getBookingByIdApiResponse) {
        getBookingByIdApiResponse
                .then().assertThat().statusCode(200)
                .and().body("firstname", is(equalTo(firstName)))
                .and().body("lastname", is(equalTo(lastName)))
                .and().body("totalprice", is(equalTo(totalPrice)))
                .and().body("depositpaid", is(equalTo(depositPaid)))
                .and().body("additionalneeds", is(equalTo(additionalNeeds)))
                .and().rootPath("bookingdates")
                .and().body("checkin", equalTo(checkInDate))
                .and().body("checkout", equalTo(checkOutDate))
                .and().detachRootPath("bookingdates");
    }
}
