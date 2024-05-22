package apis;

import http.BaseApi;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.request.CreateBookingRequest;

import java.util.Map;

import static constants.ApiPaths.UPDATE_BOOKING;

public class UpdateBookingApi extends BaseApi {

    public UpdateBookingApi() {
        super();
        super.loggAllSpecificRequestDetail(LogDetail.BODY).loggAllResponseData();
        super.setContentType(ContentType.JSON);
    }

    public Response updateBooking(Map<String, Object> createBookingPayload, int bookingId,
                                  String username, String password) {
        return getCreateBookingApiResponse(createBookingPayload, bookingId, username, password);
    }

    public Response updateBooking(CreateBookingRequest createBookingRequest, int bookingId,
                                  String username, String password) {
        return getCreateBookingApiResponse(createBookingRequest, bookingId, username, password);
    }

    private Response getCreateBookingApiResponse(Object createBookingPayload, int bookingId,
                                                 String username, String password) {
        super.setBasePath(UPDATE_BOOKING.getApiPath());
        super.setRequestBody(createBookingPayload);
        super.setPathParam("bookingId", bookingId);
        super.setBasicAuth(username, password);
        return super.sendRequest(UPDATE_BOOKING.getHttpMethodType());
    }
}
