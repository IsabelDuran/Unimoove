package unimoove.trips;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import unimoove.api.trips.TripArrivalPlaceChangeRequest;
import unimoove.api.trips.TripCreationRequest;
import unimoove.api.trips.TripDepartureDateTimeChangeRequest;
import unimoove.api.trips.TripDeparturePlaceChangeRequest;
import unimoove.api.trips.TripNumberAvailableSeatsChangeRequest;
import unimoove.api.trips.TripPaginatedResponse;
import unimoove.api.trips.TripResponse;
import unimoove.api.trips.TripStatusChangeRequest;
import unimoove.api.utils.PaginationInfo;
import unimoove.cars.Car;
import unimoove.cars.CarsRepository;
import unimoove.reservations.Reservation;
import unimoove.users.User;
import unimoove.users.UsersRepository;
import unimoove.utils.SecurityUtils;

@Service
public class TripsServiceImp implements TripsService {
	private static final int STATUS_CANCELLED = 3;

	private TripsRepository tripsRepository;

	private UsersRepository usersRepository;

	private CarsRepository carsRepository;

	private TripMapper tripMapper;

	@Autowired
	public TripsServiceImp(TripsRepository tripsRepository, UsersRepository usersRepository,
			CarsRepository carsRepository, TripMapper tripMapper) {
		super();
		this.tripsRepository = tripsRepository;
		this.usersRepository = usersRepository;
		this.carsRepository = carsRepository;
		this.tripMapper = tripMapper;
	}

	@Override
	@Transactional
	public void addTrip(TripCreationRequest tripCreationRequest) {
		Trip trip = getTrip(tripCreationRequest);
		User user = getUser();
		if (tripCreationRequest.getIdCar() != null) {
			Car car = carsRepository.findById(tripCreationRequest.getIdCar()).orElse(null);
			if (car != null)
				trip.setCar(car);
		}
		trip.setUser(user);
		user.getTrips().add(tripsRepository.save(trip));

		tripsRepository.save(trip);
		usersRepository.save(user);

	}

	@Override
	@Transactional
	public void deleteTrip(Long idTrip) {
		Trip trip = tripsRepository.findById(idTrip).orElse(null);
		if (trip != null) {
			User user = getUser();
			user.getTrips().remove(trip);

			tripsRepository.delete(trip);
			usersRepository.save(user);
		}

	}

	@Override
	@Transactional
	public void modifyTripArrivalPlace(TripArrivalPlaceChangeRequest tripArrivalPlaceChangeRequest, Long idTrip) {
		Trip trip = tripsRepository.findById(idTrip).orElse(null);
		if (trip != null) {
			trip.setArrivalPlace(tripArrivalPlaceChangeRequest.getNewPlace());

			tripsRepository.save(trip);

		}
	}

	@Override
	@Transactional
	public void modifyTripDepartureDateTime(TripDepartureDateTimeChangeRequest tripDepartureDateTimeChangeRequest,
			Long idTrip) {
		Trip trip = tripsRepository.findById(idTrip).orElse(null);
		if (trip != null) {

			trip.setDepartureDateTime(tripDepartureDateTimeChangeRequest.getNewDepartureDateTime());

			tripsRepository.save(trip);
		}
	}

	@Override
	@Transactional
	public void modifyTripDeparturePlace(TripDeparturePlaceChangeRequest tripDeparturePlaceChangeRequest, Long idTrip) {
		Trip trip = tripsRepository.findById(idTrip).orElse(null);
		if (trip != null) {
			trip.setDeparturePlace(tripDeparturePlaceChangeRequest.getNewPlace());

			tripsRepository.save(trip);

		}
	}

	@Override
	@Transactional
	public void modifyTripNumberAvailableSeats(
			TripNumberAvailableSeatsChangeRequest tripNumberAvailableSeatsChangeRequest, Long idTrip) {
		Trip trip = tripsRepository.findById(idTrip).orElse(null);
		if (trip != null) {
			trip.setNumberAvailableSeats(tripNumberAvailableSeatsChangeRequest.getNewNumberAvailableSeats());

			tripsRepository.save(trip);
		}

	}

	@Override
	@Transactional
	public void modifyTripStatus(TripStatusChangeRequest tripStatusChangeRequest, Long idTrip) {
		Trip trip = tripsRepository.findById(idTrip).orElse(null);
		if (trip != null) {
			trip.setState(tripStatusChangeRequest.getNewStatus());
			Set<Reservation> reservations = trip.getReservations();

			for (Reservation reservation : reservations) {
				reservation.setStatus(STATUS_CANCELLED);
			}

			tripsRepository.save(trip);

		}

	}

	@Override
	@Transactional
	public TripPaginatedResponse searchTrips(String departurePlace, String arrivalPlace, String departureDateTime,
			Integer page, Integer size) {
		OffsetDateTime departureTime = OffsetDateTime.parse(departureDateTime);
		Page<Trip> matchedTrips = tripsRepository.searchTrips(departurePlace, arrivalPlace, departureTime,
				PageRequest.of(page, size));
		List<TripResponse> tripResponses = matchedTrips.map(trip -> tripMapper.tripToTripResponse(trip)).stream()
				.collect(Collectors.toList());

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setTotalElements(matchedTrips.getNumberOfElements());
		paginationInfo.setTotalPages(matchedTrips.getTotalPages());

		TripPaginatedResponse tripPaginatedResponse = new TripPaginatedResponse();
		tripPaginatedResponse.setPages(tripResponses);
		tripPaginatedResponse.setPaginationInfo(paginationInfo);

		return tripPaginatedResponse;
	}

	@Override
	@Transactional
	public TripPaginatedResponse obtainUserTrips(String username, Integer page, Integer size) {
		Page<Trip> matchedTrips = tripsRepository.searchUserTrip(
				usersRepository.findUserByUsernameWithTrips(username).getId(), PageRequest.of(page, size));
		List<TripResponse> tripResponses = matchedTrips.map(trip -> tripMapper.tripToTripResponse(trip)).stream()
				.collect(Collectors.toList());

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setTotalElements(matchedTrips.getNumberOfElements());
		paginationInfo.setTotalPages(matchedTrips.getTotalPages());

		TripPaginatedResponse tripPaginatedResponse = new TripPaginatedResponse();
		tripPaginatedResponse.setPages(tripResponses);
		tripPaginatedResponse.setPaginationInfo(paginationInfo);

		return tripPaginatedResponse;
	}

	private Trip getTrip(TripCreationRequest tripCreationRequest) {
		Trip trip = tripMapper.tripCreationRequestToTrip(tripCreationRequest);
		return trip;
	}

	private User getUser() {
		User user = usersRepository.findUserByUsername(SecurityUtils.currentUserUsername());
		if (user == null)
			throw new EntityNotFoundException("Usuario no encontrado");
		return user;
	}

}
