package sharedContext;

import io.restassured.response.Response;

import java.util.Map;

/**
 * This class holds objects that are shared between Cucumber step from different
 * step
 * definition classes. This class is injected in constructors of Cucumber step
 * definition classes.
 * Read more about Dependency Injection in Cucumber at
 * <a href="https://cucumber.io/docs/cucumber/state/">Here</a>
 * <p>
 * Read more about constructor based dependency injection in general
 * at <a href="https://www.vogella.com/tutorials/DependencyInjection/article.html">Here</a>
 **/
public class SharedStepContext {
    public Response apiResponse;
    public int bookingId;
    public Map<String, Object> createBookingApiRequest;
}
