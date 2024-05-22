package constants;

import io.restassured.http.Method;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static io.restassured.http.Method.*;

/**
 * This enum holds API base paths for 'booking' APIs. We can have multiple
 * enums based on module of the application such as UserApiPaths, SearchApiPath,
 * OrderApiPaths etc. This way we can also keep the HTTP Method types of APIs associated
 * with their paths in same place.
 * <p>
 * Example:
 * User module that has all APIs with high level path - "{baseUrl}/api/v1/user" can have multiple
 * APIs such as "/user/login", "/user/logout" etc. These user APIs can be part of UserApiPaths enum
 * <p>
 * Similar for search module i.e. "{baseUrl}/api/v1/search", we can have APIs
 * like "/search/keyword", "/search/create" etc.These search APIs can be part of SearchApiPaths enum.
 **/

@AllArgsConstructor
@Getter
public enum ApiPaths {

    GET_BOOKING("/booking/{bookingId}", GET),
    GET_BOOKING_IDS("/booking", GET),
    CREATE_BOOKING("/booking", POST),
    DELETE_BOOKING("/booking/{bookingId}", DELETE),
    UPDATE_BOOKING("/booking/{bookingId}", PUT);

    /**
     * Access the API path and HTTP method type with Lombok {@link Getter} annotation.
     */
    private final String apiPath;
    private final Method httpMethodType;

}

