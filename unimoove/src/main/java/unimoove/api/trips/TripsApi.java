package unimoove.api.trips;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import unimoove.api.reservations.ReservationPaginatedResponse;

@Api(value = "trips", description = "the trips API")
public interface TripsApi {

	Logger log = LoggerFactory.getLogger(TripsApi.class);

	@ApiOperation(value = "Adds a trip", nickname = "addTrip", notes = "Adds a new trip to the system", authorizations = {
			@Authorization(value = "ApiKeyAuth") }, tags = { "Trips", })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Trip created"),
			@ApiResponse(code = 400, message = "invalid input, object invalid"),
			@ApiResponse(code = 401, message = "The requested page needs a username and a password") })
	@RequestMapping(value = "/trips", consumes = { "application/json" }, method = RequestMethod.POST)
	ResponseEntity<Void> addTrip(@ApiParam(value = "Trip to add") @Valid @RequestBody TripCreationRequest body);

	@ApiOperation(value = "Deletes a trip", nickname = "deleteTrip", notes = "", authorizations = {
			@Authorization(value = "ApiKeyAuth") }, tags = { "Trips", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "trip deleted succesfully"),
			@ApiResponse(code = 400, message = "bad input parameter"),
			@ApiResponse(code = 404, message = "trip not found"),
			@ApiResponse(code = 401, message = "The requested page needs a username and a password") })
	@RequestMapping(value = "/trips/{idTrip}", method = RequestMethod.DELETE)
	ResponseEntity<Void> deleteTrip(
			@ApiParam(value = "By passing in the appropriate trip ID, you can delete the trip.", required = true) @PathVariable("idTrip") Long idTrip);

	@ApiOperation(value = "Modifies the trip's arrival Place", nickname = "modifyTripArrivalPlace", notes = "The trip ID for the trip you want to modify", authorizations = {
			@Authorization(value = "ApiKeyAuth") }, tags = { "Trips", })
	@ApiResponses(value = { @ApiResponse(code = 400, message = "bad input parameter"),
			@ApiResponse(code = 200, message = "operation completed successfully"),
			@ApiResponse(code = 401, message = "The requested page needs a username and a password") })
	@RequestMapping(value = "/trips/{idTrip}/arrivalPlace", consumes = {
			"application/json" }, method = RequestMethod.PUT)
	ResponseEntity<Void> modifyTripArrivalPlace(
			@ApiParam(value = "", required = true) @PathVariable("idTrip") Long idTrip,
			@ApiParam(value = "The trip's new arrival place") @Valid @RequestBody TripArrivalPlaceChangeRequest body);

	@ApiOperation(value = "Modifies the trip's departure date and time", nickname = "modifyTripDepartureDateTime", notes = "The trip ID for the trip you want to modify", authorizations = {
			@Authorization(value = "ApiKeyAuth") }, tags = { "Trips", })
	@ApiResponses(value = { @ApiResponse(code = 400, message = "bad input parameter"),
			@ApiResponse(code = 200, message = "operation completed successfully"),
			@ApiResponse(code = 401, message = "The requested page needs a username and a password") })
	@RequestMapping(value = "/trips/{idTrip}/departureDateTime", consumes = {
			"application/json" }, method = RequestMethod.PUT)
	ResponseEntity<Void> modifyTripDepartureDateTime(
			@ApiParam(value = "", required = true) @PathVariable("idTrip") Long idTrip,
			@ApiParam(value = "The trip's new departure date and time") @Valid @RequestBody TripDepartureDateTimeChangeRequest body);

	@ApiOperation(value = "Modifies the trip's departurePlace", nickname = "modifyTripDeparturePlace", notes = "The trip ID for the trip you want to modify", authorizations = {
			@Authorization(value = "ApiKeyAuth") }, tags = { "Trips", })
	@ApiResponses(value = { @ApiResponse(code = 400, message = "bad input parameter"),
			@ApiResponse(code = 200, message = "operation completed successfully"),
			@ApiResponse(code = 401, message = "The requested page needs a username and a password") })
	@RequestMapping(value = "/trips/{idTrip}/departurePlace", consumes = {
			"application/json" }, method = RequestMethod.PUT)
	ResponseEntity<Void> modifyTripDeparturePlace(
			@ApiParam(value = "", required = true) @PathVariable("idTrip") Long idTrip,
			@ApiParam(value = "The trip's new departure place") @Valid @RequestBody TripDeparturePlaceChangeRequest body);

	@ApiOperation(value = "Modifies the trip's number of available seats", nickname = "modifyTripNumberAvailableSeats", notes = "The trip ID for the trip you want to modify", authorizations = {
			@Authorization(value = "ApiKeyAuth") }, tags = { "Trips", })
	@ApiResponses(value = { @ApiResponse(code = 400, message = "bad input parameter"),
			@ApiResponse(code = 200, message = "operation completed successfully"),
			@ApiResponse(code = 401, message = "The requested page needs a username and a password") })
	@RequestMapping(value = "/trips/{idTrip}/numberAvailableSeats", consumes = {
			"application/json" }, method = RequestMethod.PUT)
	ResponseEntity<Void> modifyTripNumberAvailableSeats(
			@ApiParam(value = "", required = true) @PathVariable("idTrip") Long idTrip,
			@ApiParam(value = "The trip's new number of available seats") @Valid @RequestBody TripNumberAvailableSeatsChangeRequest body);

	@ApiOperation(value = "Modifies the trip's status", nickname = "modifyTripStatus", notes = "The trip ID for the trip you want to modify", authorizations = {
			@Authorization(value = "ApiKeyAuth") }, tags = { "Trips", })
	@ApiResponses(value = { @ApiResponse(code = 400, message = "bad input parameter"),
			@ApiResponse(code = 200, message = "operation completed successfully"),
			@ApiResponse(code = 401, message = "The requested page needs a username and a password") })
	@RequestMapping(value = "/trips/{idTrip}/status", consumes = {
			"application/json" }, method = RequestMethod.PUT)
	ResponseEntity<Void> modifyTripStatus(
			@ApiParam(value = "", required = true) @PathVariable("idTrip") Long idTrip,
			@ApiParam(value = "The trip's new status") @Valid @RequestBody TripStatusChangeRequest body);
	
	@ApiOperation(value = "Searches for trips", nickname = "searchTrips", notes = "Searches for trips with the specified departure and arrival place and date.", response = TripPaginatedResponse.class, authorizations = {
			@Authorization(value = "ApiKeyAuth") }, tags = { "Trips", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "The search was successfull", response = TripPaginatedResponse.class),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/trips", produces = { "application/json" }, method = RequestMethod.GET)
	ResponseEntity<TripPaginatedResponse> searchTrips(
			@ApiParam(value = "the departure place for the trip") @Valid @RequestParam(value = "departurePlace", required = false) String departurePlace,
			@ApiParam(value = "the arrival place for the trip") @Valid @RequestParam(value = "arrivalPlace", required = false) String arrivalPlace,
			@ApiParam(value = "the departure time for the trip") @Valid @RequestParam(value = "departureDateTime", required = false) String departureDateTime,
			@ApiParam(value = "the number of the page") @Valid @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@ApiParam(value = "the number of element per page") @Valid @RequestParam(value = "size", required = false, defaultValue = "25") Integer size);

	@ApiOperation(value = "Gets all the reservations for a trip", nickname = "getTripReservations", notes = "Gets all the reservations for a trip.", response = ReservationPaginatedResponse.class, authorizations = {
			@Authorization(value = "ApiKeyAuth") }, tags = { "Trips", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "The search was successfull", response = ReservationPaginatedResponse.class),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 401, message = "The requested page needs a username and a password"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@RequestMapping(value = "/trips/{idTrip}/reservations", produces = { "application/json" }, method = RequestMethod.GET)
	ResponseEntity<ReservationPaginatedResponse> getTripReservations(
			@ApiParam(value = "", required = true) @PathVariable("idTrip") Long idTrip,
			@ApiParam(value = "the number of the page") @Valid @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@ApiParam(value = "the number of element per page") @Valid @RequestParam(value = "size", required = false, defaultValue = "25") Integer size);
	

}
