package unimoove.api.trips;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.ApiParam;
import unimoove.api.reservations.ReservationPaginatedResponse;
import unimoove.reservations.ReservationsService;
import unimoove.trips.TripsService;
import unimoove.users.UsersService;

@Controller
public class TripsApiController implements TripsApi {

	private static final Logger log = LoggerFactory.getLogger(TripsApiController.class);

	private final HttpServletRequest request;

	private TripsService tripsService;
	
	private ReservationsService reservationsService;

	@Autowired
	public TripsApiController(HttpServletRequest request, TripsService tripsService,
			ReservationsService reservationsService) {
		super();
		this.request = request;
		this.tripsService = tripsService;
		this.reservationsService = reservationsService;
	}

	public ResponseEntity<Void> addTrip(@ApiParam(value = "Trip to add") @Valid @RequestBody TripCreationRequest body) {
		tripsService.addTrip(body);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	public ResponseEntity<Void> deleteTrip(
			@ApiParam(value = "By passing in the appropriate trip ID, you can delete the trip.", required = true) @PathVariable("idTrip") Long idTrip) {
		tripsService.deleteTrip(idTrip);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	public ResponseEntity<Void> modifyTripArrivalPlace(
			@ApiParam(value = "", required = true) @PathVariable("idTrip") Long idTrip,
			@ApiParam(value = "The trip's new arrival place") @Valid @RequestBody TripArrivalPlaceChangeRequest body) {
		tripsService.modifyTripArrivalPlace(body, idTrip);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	public ResponseEntity<Void> modifyTripDepartureDateTime(
			@ApiParam(value = "", required = true) @PathVariable("idTrip") Long idTrip,
			@ApiParam(value = "The trip's new departure date and time") @Valid @RequestBody TripDepartureDateTimeChangeRequest body) {
		tripsService.modifyTripDepartureDateTime(body, idTrip);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	public ResponseEntity<Void> modifyTripDeparturePlace(
			@ApiParam(value = "", required = true) @PathVariable("idTrip") Long idTrip,
			@ApiParam(value = "The trip's new departure place") @Valid @RequestBody TripDeparturePlaceChangeRequest body) {
		tripsService.modifyTripDeparturePlace(body, idTrip);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	public ResponseEntity<Void> modifyTripNumberAvailableSeats(
			@ApiParam(value = "", required = true) @PathVariable("idTrip") Long idTrip,
			@ApiParam(value = "The trip's new number of available seats") @Valid @RequestBody TripNumberAvailableSeatsChangeRequest body) {
		tripsService.modifyTripNumberAvailableSeats(body, idTrip);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	public ResponseEntity<Void> modifyTripStatus(Long idTrip, @Valid TripStatusChangeRequest body) {
		tripsService.modifyTripStatus(body, idTrip);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	public ResponseEntity<TripPaginatedResponse> searchTrips(
			@ApiParam(value = "the departure place for the trip") @Valid @RequestParam(value = "departurePlace", required = false) String departurePlace,
			@ApiParam(value = "the arrival place for the trip") @Valid @RequestParam(value = "arrivalPlace", required = false) String arrivalPlace,
			@ApiParam(value = "the departure time for the trip") @Valid @RequestParam(value = "departureDateTime", required = false) String departureDateTime,
			@ApiParam(value = "the number of the page") @Valid @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@ApiParam(value = "the number of element per page") @Valid @RequestParam(value = "size", required = false, defaultValue = "25") Integer size) {
		
		return new ResponseEntity<TripPaginatedResponse>(tripsService.searchTrips(departurePlace, arrivalPlace, departureDateTime, page, size), HttpStatus.OK);
	}

	
	public ResponseEntity<ReservationPaginatedResponse> getTripReservations(Long idTrip, @Valid Integer page,
			@Valid Integer size) {
		return new ResponseEntity<ReservationPaginatedResponse>(reservationsService.getTripReservations(idTrip, page, size), HttpStatus.OK);
	}

}
