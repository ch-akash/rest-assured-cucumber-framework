package apis;

import http.BaseApi;
import io.restassured.response.Response;

import static constants.ApiPaths.GET_BOOKING;
import static constants.ApiPaths.GET_BOOKING_IDS;

public class GetBookingApi extends BaseApi {

    public GetBookingApi() {
        super();
        super.loggAllRequestData().loggAllResponseData();
    }

    public Response getAllBookingIds() {
        super.setBasePath(GET_BOOKING_IDS.getApiPath());
        return super.sendRequest(GET_BOOKING_IDS.getHttpMethodType());
    }

    public Response getBookingById(int bookingId) {
        super.setBasePath(GET_BOOKING.getApiPath());
        super.setPathParam("bookingId", bookingId);
        return super.sendRequest(GET_BOOKING.getHttpMethodType());
    }
}
