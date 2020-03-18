/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.19-SNAPSHOT).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package unimoove.api.users;

import unimoove.api.reservations.ReservationPaginatedResponse;
import unimoove.api.trips.TripPaginatedResponse;
import unimoove.users.UniqueUsernameException;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Api(value = "users", description = "the users API")
public interface UsersApi {

    Logger log = LoggerFactory.getLogger(UsersApi.class);

    Optional<HttpServletRequest> getRequest();

    

    @ApiOperation(value = "Adds a car", nickname = "addCar", notes = "Adds a new car to the user", authorizations = {
        @Authorization(value = "ApiKeyAuth")    }, tags={ "Cars", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Car created"),
        @ApiResponse(code = 400, message = "invalid input, object invalid"),
        @ApiResponse(code = 401, message = "The requested page needs a username and a password") })
    @RequestMapping(value = "/users/{username}/cars",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Void> addCar(@ApiParam(value = "",required=true) @PathVariable("username") String username
,@ApiParam(value = "Car to add"  )  @Valid @RequestBody CarCreationRequest body
);


    @ApiOperation(value = "Registers a user", nickname = "addUser", notes = "Adds a user to the system", tags={ "Users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "user created"),
        @ApiResponse(code = 400, message = "invalid input, object invalid"),
        @ApiResponse(code = 409, message = "conflict") })
    @RequestMapping(value = "/users",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Void> addUser(@ApiParam(value = "User to add"  )  @Valid @RequestBody UserRegistrationRequest body
) throws UniqueUsernameException;


    @ApiOperation(value = "Deletes a car", nickname = "deleteCar", notes = "Deletes the car linked to a user", authorizations = {
        @Authorization(value = "ApiKeyAuth")    }, tags={ "Cars", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "car deleted succesfully"),
        @ApiResponse(code = 400, message = "bad input parameter"),
        @ApiResponse(code = 404, message = "car not found"),
        @ApiResponse(code = 401, message = "The requested page needs a username and a password") })
    @RequestMapping(value = "/users/{username}/cars/{plate}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteCar(@ApiParam(value = "",required=true) @PathVariable("username") String username
,@ApiParam(value = "By passing in the appropriate car plate, you can delete the car.",required=true) @PathVariable("plate") String plate
);


    @ApiOperation(value = "Deletes a user", nickname = "deleteUser", notes = "", authorizations = {
        @Authorization(value = "ApiKeyAuth")    }, tags={ "Users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "user deleted succesfully"),
        @ApiResponse(code = 400, message = "bad input parameter"),
        @ApiResponse(code = 404, message = "user not found"),
        @ApiResponse(code = 401, message = "The requested page needs a username and a password") })
    @RequestMapping(value = "/users/{username}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteUser(@ApiParam(value = "By passing in the appropriate username, you can delete the user.",required=true) @PathVariable("username") String username
);


    @ApiOperation(value = "Finds a user", nickname = "getUser", notes = "", response = UserResponse.class, authorizations = {
        @Authorization(value = "ApiKeyAuth")    }, tags={ "Users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "search results matching criteria", response = UserResponse.class),
        @ApiResponse(code = 400, message = "bad input parameter"),
        @ApiResponse(code = 404, message = "user not found"),
        @ApiResponse(code = 401, message = "The requested page needs a username and a password") })
    @RequestMapping(value = "/users/{username}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<UserResponse> getUser(@ApiParam(value = "By passing in the appropriate username, you can get the user.",required=true) @PathVariable("username") String username
);


    @ApiOperation(value = "Modifies the car's brand", nickname = "modifyCarBrand", notes = "", authorizations = {
        @Authorization(value = "ApiKeyAuth")    }, tags={ "Cars", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "bad input parameter"),
        @ApiResponse(code = 200, message = "operation completed successfully"),
        @ApiResponse(code = 401, message = "The requested page needs a username and a password") })
    @RequestMapping(value = "/users/{username}/cars/{plate}/brand",
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Void> modifyCarBrand(@ApiParam(value = "",required=true) @PathVariable("username") String username
,@ApiParam(value = "By passing in the appropriate car plate, you can modify the car.",required=true) @PathVariable("plate") String plate
,@ApiParam(value = "The car's new brand"  )  @Valid @RequestBody CarBrandChangeRequest body
);


    @ApiOperation(value = "Modifies the car's model", nickname = "modifyCarModel", notes = "", authorizations = {
        @Authorization(value = "ApiKeyAuth")    }, tags={ "Cars", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "bad input parameter"),
        @ApiResponse(code = 200, message = "operation completed successfully"),
        @ApiResponse(code = 401, message = "The requested page needs a username and a password") })
    @RequestMapping(value = "/users/{username}/cars/{plate}/model",
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Void> modifyCarModel(@ApiParam(value = "",required=true) @PathVariable("username") String username
,@ApiParam(value = "By passing in the appropriate car plate, you can modify the car.",required=true) @PathVariable("plate") String plate
,@ApiParam(value = "The car's new model"  )  @Valid @RequestBody CarModelChangeRequest body
);


    @ApiOperation(value = "Modifies the user's birthdate", nickname = "modifyUserBirthdate", notes = "The user username you want to modify", authorizations = {
        @Authorization(value = "ApiKeyAuth")    }, tags={ "Users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "bad input parameter"),
        @ApiResponse(code = 200, message = "operation completed successfully"),
        @ApiResponse(code = 401, message = "The requested page needs a username and a password") })
    @RequestMapping(value = "/users/{username}/birthdate",
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Void> modifyUserBirthdate(@ApiParam(value = "",required=true) @PathVariable("username") String username
,@ApiParam(value = "The new user's birthdate"  )  @Valid @RequestBody UserBirthdateChangeRequest body
);


    @ApiOperation(value = "Modifies the user's email", nickname = "modifyUserEmail", notes = "The user username you want to modify", authorizations = {
        @Authorization(value = "ApiKeyAuth")    }, tags={ "Users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "bad input parameter"),
        @ApiResponse(code = 200, message = "operation completed successfully"),
        @ApiResponse(code = 401, message = "The requested page needs a username and a password") })
    @RequestMapping(value = "/users/{username}/email",
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Void> modifyUserEmail(@ApiParam(value = "",required=true) @PathVariable("username") String username
,@ApiParam(value = "The new user's email"  )  @Valid @RequestBody UserEmailChangeRequest body
);


    @ApiOperation(value = "Modifies the user's gender", nickname = "modifyUserGender", notes = "The user username you want to modify", authorizations = {
        @Authorization(value = "ApiKeyAuth")    }, tags={ "Users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "bad input parameter"),
        @ApiResponse(code = 200, message = "operation completed successfully"),
        @ApiResponse(code = 401, message = "The requested page needs a username and a password") })
    @RequestMapping(value = "/users/{username}/gender",
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Void> modifyUserGender(@ApiParam(value = "",required=true) @PathVariable("username") String username
,@ApiParam(value = "The new user's gender"  )  @Valid @RequestBody UserGenderChangeRequest body
);


    @ApiOperation(value = "Modifies the user's lastname", nickname = "modifyUserLastname", notes = "The user username you want to modify", authorizations = {
        @Authorization(value = "ApiKeyAuth")    }, tags={ "Users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "bad input parameter"),
        @ApiResponse(code = 200, message = "operation completed successfully"),
        @ApiResponse(code = 401, message = "The requested page needs a username and a password") })
    @RequestMapping(value = "/users/{username}/lastname",
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Void> modifyUserLastname(@ApiParam(value = "",required=true) @PathVariable("username") String username
,@ApiParam(value = "The new user's lastname"  )  @Valid @RequestBody UserLastnameChangeRequest body
);


    @ApiOperation(value = "Modifies the user's name", nickname = "modifyUserName", notes = "The user username you want to modify", authorizations = {
        @Authorization(value = "ApiKeyAuth")    }, tags={ "Users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "bad input parameter"),
        @ApiResponse(code = 200, message = "operation completed successfully"),
        @ApiResponse(code = 401, message = "The requested page needs a username and a password") })
    @RequestMapping(value = "/users/{username}/name",
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Void> modifyUserName(@ApiParam(value = "",required=true) @PathVariable("username") String username
,@ApiParam(value = "The new user's name"  )  @Valid @RequestBody UserNameChangeRequest body
);


    @ApiOperation(value = "Modifies the user's password", nickname = "modifyUserPassword", notes = "The user username you want to modify", authorizations = {
        @Authorization(value = "ApiKeyAuth")    }, tags={ "Users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "bad input parameter"),
        @ApiResponse(code = 200, message = "operation completed successfully"),
        @ApiResponse(code = 401, message = "The requested page needs a username and a password") })
    @RequestMapping(value = "/users/{username}/password",
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Void> modifyUserPassword(@ApiParam(value = "",required=true) @PathVariable("username") String username
,@ApiParam(value = "The new user's password"  )  @Valid @RequestBody UserPasswordChangeRequest body
);


    @ApiOperation(value = "Modifies the user's role", nickname = "modifyUserRole", notes = "The user username you want to modify", authorizations = {
        @Authorization(value = "ApiKeyAuth")    }, tags={ "Users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "bad input parameter"),
        @ApiResponse(code = 200, message = "operation completed successfully"),
        @ApiResponse(code = 401, message = "The requested page needs a username and a password") })
    @RequestMapping(value = "/users/{username}/role",
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Void> modifyUserRole(@ApiParam(value = "",required=true) @PathVariable("username") String username
,@ApiParam(value = "The new user's role"  )  @Valid @RequestBody UserRoleChangeRequest body
);


    @ApiOperation(value = "Modifies the user's username", nickname = "modifyUserUsername", notes = "The user username you want to modify", authorizations = {
        @Authorization(value = "ApiKeyAuth")    }, tags={ "Users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "bad input parameter"),
        @ApiResponse(code = 200, message = "operation completed successfully"),
        @ApiResponse(code = 401, message = "The requested page needs a username and a password") })
    @RequestMapping(value = "/users/{username}/username",
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Void> modifyUserUsername(@ApiParam(value = "",required=true) @PathVariable("username") String username
,@ApiParam(value = "The new user's username"  )  @Valid @RequestBody UserUsernameChangeRequest body
);


    @ApiOperation(value = "Obtains the trips reserved by the user", nickname = "obtainReservations", notes = "", response = ReservationPaginatedResponse.class, authorizations = {
        @Authorization(value = "ApiKeyAuth")    }, tags={ "Reservations", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "The search was successfull", response = ReservationPaginatedResponse.class),
        @ApiResponse(code = 401, message = "The requested page needs a username and a password"),
        @ApiResponse(code = 500, message = "Internal server error") })
    @RequestMapping(value = "/users/{username}/reservations",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<ReservationPaginatedResponse> obtainReservations(@ApiParam(value = "",required=true) @PathVariable("username") String username
,@ApiParam(value = "the number of the page") @Valid @RequestParam(value = "page", required = false) Integer page
,@ApiParam(value = "the number of element per page") @Valid @RequestParam(value = "size", required = false) Integer size
);


    @ApiOperation(value = "Obtains the trips registered by the user", nickname = "obtainTrips", notes = "", response = TripPaginatedResponse.class, authorizations = {
        @Authorization(value = "ApiKeyAuth")    }, tags={ "Trips", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "The search was successfull", response = TripPaginatedResponse.class),
        @ApiResponse(code = 401, message = "The requested page needs a username and a password"),
        @ApiResponse(code = 500, message = "Internal server error") })
    @RequestMapping(value = "/users/{username}/trips",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<TripPaginatedResponse> obtainTrips(@ApiParam(value = "",required=true) @PathVariable("username") String username
,@ApiParam(value = "the number of the page") @Valid @RequestParam(value = "page", required = false) Integer page
,@ApiParam(value = "the number of element per page") @Valid @RequestParam(value = "size", required = false) Integer size
);


    @ApiOperation(value = "Get cars from user", nickname = "searchCar", notes = "Get cars from user", response = CarResponse.class, responseContainer = "List", authorizations = {
        @Authorization(value = "ApiKeyAuth")    }, tags={ "Cars", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "The search was successfull", response = CarResponse.class, responseContainer = "List"),
        @ApiResponse(code = 403, message = "Access denied"),
        @ApiResponse(code = 500, message = "Internal server error") })
    @RequestMapping(value = "/users/{username}/cars",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<CarResponse>> searchCar(@ApiParam(value = "",required=true) @PathVariable("username") String username
);


    @ApiOperation(value = "Searches for a user", nickname = "searchUser", notes = "Searches for a user. This operation is permited for both user and admin", response = UserPaginatedResponse.class, responseContainer = "List", authorizations = {
        @Authorization(value = "ApiKeyAuth")    }, tags={ "Users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "The search was successfull", response = UserPaginatedResponse.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "The requested page needs a username and a password"),
        @ApiResponse(code = 500, message = "Internal server error") })
    @RequestMapping(value = "/users",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<UserPaginatedResponse>> searchUser(@ApiParam(value = "the username to be searched") @Valid @RequestParam(value = "username", required = false) String username
,@ApiParam(value = "the number of the page") @Valid @RequestParam(value = "page", required = false) Integer page
,@ApiParam(value = "the number of element per page") @Valid @RequestParam(value = "size", required = false) Integer size
);

}
