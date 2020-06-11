package com.oppo.accolite.Exception;
import java.time.LocalDate;
import java.util.Date;
public class ErrorDetails {
    private LocalDate timestamp;
    private String message;
    private String details;

    public ErrorDetails(LocalDate timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

	

	public LocalDate getTimestamp() {
		return timestamp;
	}



	public void setTimestamp(LocalDate timestamp) {
		this.timestamp = timestamp;
	}



	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}


}