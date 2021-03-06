package unimoove.api.users;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
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
import unimoove.api.trips.TripPaginatedResponse;
import unimoove.cars.CarsService;
import unimoove.cars.MaxCarsPerUserReached;
import unimoove.reservations.ReservationsService;
import unimoove.trips.TripsService;
import unimoove.users.UniqueEmailException;
import unimoove.users.UniqueUsernameException;
import unimoove.users.UsersService;
import unimoove.utils.SecurityUtils;

@Controller
public class UsersApiController implements UsersApi {

	private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

	private UsersService userService;
	
	private CarsService carsService;
	
	private TripsService tripsService;
	
	private ReservationsService reservationsService;

	@Autowired
	public UsersApiController(UsersService userService, CarsService carsService, TripsService tripsService,
			ReservationsService reservationsService) {
		super();
		this.userService = userService;
		this.carsService = carsService;
		this.tripsService = tripsService;
		this.reservationsService = reservationsService;
	}

	public ResponseEntity<Void> addCar(@ApiParam(value = "", required = true) @PathVariable("username") String username,
			@ApiParam(value = "Car to add") @Valid @RequestBody CarCreationRequest body) throws MaxCarsPerUserReached {
		carsService.addCar(body, username);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	public ResponseEntity<Void> addUser(
			@ApiParam(value = "User to add") @Valid @RequestBody UserRegistrationRequest body)
			throws UniqueUsernameException {
		userService.registerUser(body);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	public ResponseEntity<Void> deleteCar(
			@ApiParam(value = "", required = true) @PathVariable("username") String username,
			@ApiParam(value = "By passing in the appropriate car plate, you can delete the car.", required = true) @PathVariable("plate") String plate) {
		carsService.deleteCar(plate, username);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	public ResponseEntity<Void> deleteUser(
			@ApiParam(value = "By passing in the appropriate username, you can delete the user.", required = true) @PathVariable("username") String username)
			throws IllegalArgumentException {
		userService.deleteUser(username);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	public ResponseEntity<UserResponse> getUser(
			@ApiParam(value = "By passing in the appropriate user id, you can get the user.", required = true) @PathVariable("idUser") Long idUser)
			throws EntityNotFoundException {
		return new ResponseEntity<UserResponse>(userService.getUserById(idUser), HttpStatus.OK);
	}

	public ResponseEntity<Void> modifyCarBrand(
			@ApiParam(value = "", required = true) @PathVariable("username") String username,
			@ApiParam(value = "By passing in the appropriate car plate, you can modify the car.", required = true) @PathVariable("plate") String plate,
			@ApiParam(value = "The car's new brand") @Valid @RequestBody CarBrandChangeRequest body) {
		carsService.modifyCarBrand(body, plate);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	public ResponseEntity<Void> modifyCarModel(
			@ApiParam(value = "", required = true) @PathVariable("username") String username,
			@ApiParam(value = "By passing in the appropriate car plate, you can modify the car.", required = true) @PathVariable("plate") String plate,
			@ApiParam(value = "The car's new model") @Valid @RequestBody CarModelChangeRequest body) {
		carsService.modifyCarModel(body, plate);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	public ResponseEntity<Void> modifyUserBirthdate(
			@ApiParam(value = "", required = true) @PathVariable("username") String username,
			@ApiParam(value = "The new user's birthdate") @Valid @RequestBody UserBirthdateChangeRequest body) {
		userService.modifyUserBirthdate(body, SecurityUtils.currentUserUsername());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	public ResponseEntity<Void> modifyUserEmail(
			@ApiParam(value = "", required = true) @PathVariable("username") String username,
			@ApiParam(value = "The new user's email") @Valid @RequestBody UserEmailChangeRequest body) throws UniqueEmailException {
		userService.modifyUserEmail(body, SecurityUtils.currentUserUsername());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	public ResponseEntity<Void> modifyUserGender(
			@ApiParam(value = "", required = true) @PathVariable("username") String username,
			@ApiParam(value = "The new user's gender") @Valid @RequestBody UserGenderChangeRequest body) {
		userService.modifyUserGender(body, SecurityUtils.currentUserUsername());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	public ResponseEntity<Void> modifyUserLastname(
			@ApiParam(value = "", required = true) @PathVariable("username") String username,
			@ApiParam(value = "The new user's lastname") @Valid @RequestBody UserLastnameChangeRequest body) {
		userService.modifyUserLastname(body, SecurityUtils.currentUserUsername());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	public ResponseEntity<Void> modifyUserName(
			@ApiParam(value = "", required = true) @PathVariable("username") String username,
			@ApiParam(value = "The new user's name") @Valid @RequestBody UserNameChangeRequest body) {
		userService.modifyUserName(body, SecurityUtils.currentUserUsername());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	public ResponseEntity<Void> modifyUserPassword(
			@ApiParam(value = "", required = true) @PathVariable("username") String username,
			@ApiParam(value = "The new user's password") @Valid @RequestBody UserPasswordChangeRequest body) {
		userService.modifyUserPassword(body, SecurityUtils.currentUserUsername());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	public ResponseEntity<Void> modifyUserRole(
			@ApiParam(value = "", required = true) @PathVariable("username") String username,
			@ApiParam(value = "The new user's role") @Valid @RequestBody UserRoleChangeRequest body) {
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	public ResponseEntity<Void> modifyUserUsername(
			@ApiParam(value = "", required = true) @PathVariable("username") String username,
			@ApiParam(value = "The new user's username") @Valid @RequestBody UserUsernameChangeRequest body) throws UniqueUsernameException {
		userService.modifyUserUsername(body, SecurityUtils.currentUserUsername());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	public ResponseEntity<ReservationPaginatedResponse> obtainReservations(
			@ApiParam(value = "", required = true) @PathVariable("username") String username,
			@ApiParam(value = "the number of the page") @Valid @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@ApiParam(value = "the number of element per page") @Valid @RequestParam(value = "size", required = false, defaultValue = "25") Integer size) {
		return new ResponseEntity<ReservationPaginatedResponse>(reservationsService.getUserReservations(username, page, size), HttpStatus.OK);
	}

	public ResponseEntity<TripPaginatedResponse> obtainTrips(
			@ApiParam(value = "", required = true) @PathVariable("username") String username,
			@ApiParam(value = "the number of the page") @Valid @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@ApiParam(value = "the number of element per page") @Valid @RequestParam(value = "size", required = false, defaultValue = "25") Integer size) {

		return new ResponseEntity<TripPaginatedResponse>(tripsService.obtainUserTrips(username, page, size), HttpStatus.OK);
	}

	public ResponseEntity<List<CarResponse>> searchCar(
			@ApiParam(value = "", required = true) @PathVariable("username") String username) {

		return new ResponseEntity<List<CarResponse>>(carsService.getCarsFromUser(username).stream().collect(Collectors.toList()), HttpStatus.OK);
	}

	public ResponseEntity<UserPaginatedResponse> searchUser(
			@ApiParam(value = "the username to be searched") @Valid @RequestParam(value = "username", required = true) String username,
			@ApiParam(value = "the number of the page") @Valid @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@ApiParam(value = "the number of element per page") @Valid @RequestParam(value = "size", required = false, defaultValue = "25") Integer size) {
		return new ResponseEntity<UserPaginatedResponse>(userService.searchUsersByUsername(username, page, size), HttpStatus.OK);
	}

}
