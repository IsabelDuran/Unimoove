package unimoove.reservations;

import org.springframework.stereotype.Service;

import unimoove.api.reservations.ReservationCreationRequest;
import unimoove.api.reservations.ReservationPaginatedResponse;

@Service
public interface ReservationsService {
	public void addReservation(ReservationCreationRequest reservationCreationRequest);
	public void deleteReservation(String idReservation);
	public ReservationPaginatedResponse getTripReservations(String idTrip, Integer page, Integer size);
	public ReservationPaginatedResponse getUserReservations(String username, Integer page, Integer size);
}