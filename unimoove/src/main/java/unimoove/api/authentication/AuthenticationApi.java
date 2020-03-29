/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.19-SNAPSHOT).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package unimoove.api.authentication;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import unimoove.authentication.UnsuccessfulLoginException;

@Api(value = "authentication", description = "the authentication API")
public interface AuthenticationApi {

    Logger log = LoggerFactory.getLogger(AuthenticationApi.class);
    

    @ApiOperation(value = "Authenticates a user into the system", nickname = "userLogin", notes = "Logs in a user into the system", response = LoginResponse.class, tags={ "Authentication", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Logged in successfully", response = LoginResponse.class),
        @ApiResponse(code = 400, message = "Invalid input, object invalid"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 409, message = "The request could not be completed because of a conflict") })
    @RequestMapping(value = "/authentication/login",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<LoginResponse> userLogin(@ApiParam(value = "The user's username and password"  )  @Valid @RequestBody LoginRequest body
) throws UnsuccessfulLoginException;

}
