package hooks;

import io.cucumber.java.*;

/**
 * Cucumber API Hook class. Cucumber hooks can be filtered using tags.
 * We can also access currently running scenario information in these hooks using
 * the {@link Scenario} class as parameter of these methods.
 * <p>
 * Note: Use Cucumber hooks only when you need to have them. The purpose of Gherkins and feature files
 * are to make a scenario easily understandable for humans. But cucumber hooks are hidden from feature files,
 * and you might not understand if there is a hook doing some prerequisite,setup,changes which will not be
 * visible Gherkins file at all.
 */

public class ApiHooks {

    /*
     * This will run before any scenario. We can also access scenario inside a hook.
     *
     * @param scenario parameter to access currently running scenario.
     */
//    @Before
//    public void beforeAnyApiTest(Scenario scenario) {
//        System.out.println("Current Scenario in run: ".concat(scenario.getName()));
//        System.out.println(scenario.getSourceTagNames());
//    }

    /**
     * This will run only before `@get-all-booking` tagged scenarios.
     *
     * @param scenario parameter to access currently running scenario.
     */
    @Before("@get-all-booking")
    public void beforeGetApi(Scenario scenario) {
        System.out.println("Before GET api run: ".concat(scenario.getName()));
        System.out.println(scenario.getSourceTagNames());
    }

    @Before("@create-booking")
    public void beforeCreateBookingApi(Scenario scenario) {
        System.out.println("Before Create Booking api run: ".concat(scenario.getName()));
        System.out.println(scenario.getSourceTagNames());
    }

    /*
    This will run before each step of a scenario. This may cause too much logging in your Allure
    reports.
     */
    @BeforeStep
    public void beforeEachStepForAnyApiTest(Scenario scenario) {
        System.out.println("Current Scenario in run: ".concat(scenario.getName()));
    }

    /*
     * This will run after any scenario.
     *
     * @param scenario parameter to access currently running scenario.
     */
    @After
    public void afterAnyApiTest(Scenario scenario) {
        System.out.println("After Scenario in run: ".concat(scenario.getName()));
    }

    /*
   This will run after each step of a scenario. This may cause too much logging in your Allure
   reports.
    */
    @AfterStep
    public void afterEachStepForAnyApiTest(Scenario scenario) {
        System.out.println("After each Scenario in run: ".concat(scenario.getName()));
    }

    /**
     * Static before and after all hooks. The method names should be exactly same as mentioned
     * in this example.
     * <p>
     * The before all and after all hooks can NOT access {@link Scenario} class as parameter.
     */
    @BeforeAll
    public static void before_all() {
        System.out.println("This will run before all the scenarios");
    }

    @AfterAll
    public static void after_all() {
        System.out.println("This will run after all the scenarios");
    }
}
