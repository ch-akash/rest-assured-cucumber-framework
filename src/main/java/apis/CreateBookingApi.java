package apis;

import http.BaseApi;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.request.CreateBookingRequest;

import java.util.Map;

import static constants.ApiPaths.CREATE_BOOKING;

public class CreateBookingApi extends BaseApi {

    public CreateBookingApi() {
        super();
        super.loggAllRequestData().loggAllResponseData();
        super.setContentType(ContentType.JSON);
    }

    public Response createNewBooking(Map<String, Object> createBookingPayload) {
        return getCreateBookingApiResponse(createBookingPayload);
    }

    public Response createNewBooking(CreateBookingRequest createBookingRequest) {
        return getCreateBookingApiResponse(createBookingRequest);
    }

    private Response getCreateBookingApiResponse(Object createBookingPayload) {
        super.setBasePath(CREATE_BOOKING.getApiPath());
        super.setRequestBody(createBookingPayload);
        return super.sendRequest(CREATE_BOOKING.getHttpMethodType());
    }
}
