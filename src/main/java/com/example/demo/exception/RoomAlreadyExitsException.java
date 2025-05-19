package com.example.demo.exception;

public class RoomAlreadyExitsException extends RoomNotFoundException{
	public RoomAlreadyExitsException(String message) {
		super(message);
	}
}
