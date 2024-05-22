package http;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static config.PropertyUtil.getConfig;

public abstract class BaseApi {

    private final RequestSpecification requestSpecification;


    public BaseApi() {
        var httpConfig = HttpClientConfig.httpClientConfig()
                                         .setParam("http.connection.timeout", getConfig().connectionTimeout())
                                         .setParam("http.socket.timeout", getConfig().socketTimeout());

        this.requestSpecification = RestAssured.given()
                                               .baseUri(getConfig().baseUrl())
                                               .filter(new AllureRestAssured())
                                               .config(RestAssured.config().httpClient(httpConfig));

    }

    protected void setRequestBody(Object object) {
        this.requestSpecification.body(object);
    }

    protected void setBasePath(String basePath) {
        this.requestSpecification.basePath(basePath);
    }

    protected void setContentType(ContentType contentType) {
        this.requestSpecification.contentType(contentType);
    }

    protected void setBasicAuth(String username, String password) {
        this.requestSpecification.auth().preemptive().basic(username, password);
    }

    protected void setPathParam(String paramName, Object paramValue) {
        this.requestSpecification.pathParam(paramName, paramValue);
    }

    public BaseApi loggAllRequestData() {
        this.requestSpecification.filter(new RequestLoggingFilter());
        return this;
    }

    public BaseApi loggAllSpecificRequestDetail(LogDetail logDetail) {
        this.requestSpecification.filter(new RequestLoggingFilter(logDetail));
        return this;
    }

    public BaseApi loggAllResponseData() {
        this.requestSpecification.filter(new ResponseLoggingFilter());
        return this;
    }

    public BaseApi loggAllSpecificResponseDetail(LogDetail logDetail) {
        this.requestSpecification.filter(new ResponseLoggingFilter(logDetail));
        return this;
    }

    protected void setRedirect(boolean shouldFollowRedirect) {
        this.requestSpecification.redirects().follow(shouldFollowRedirect)
                                 .urlEncodingEnabled(false);
    }

    protected Response sendRequest(Method methodType) {
        final RequestSpecification when = this.requestSpecification.when();
        return switch (methodType) {
            case GET -> when.get();
            case PUT -> when.put();
            case POST -> when.post();
            case DELETE -> when.delete();
            case PATCH -> when.patch();
            default -> throw new IllegalArgumentException("Input method type not supported");
        };
    }
}
