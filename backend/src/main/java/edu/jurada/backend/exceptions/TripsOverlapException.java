package edu.jurada.backend.exceptions;

public class TripsOverlapException extends RuntimeException {
	public TripsOverlapException() {
		super("Trips cannot overlap. That means they cannot share days");
	}
}
