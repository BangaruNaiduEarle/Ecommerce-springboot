package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDto {

    public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	private String message;

	public ErrorDto(String message) {
		this.message = message;
	}
	 public ErrorDto() {
	}
	public static ErrorDtoBuilder builder() {
	        return new ErrorDtoBuilder();
	    }

	    public static class ErrorDtoBuilder {
	        private String message;

	        ErrorDtoBuilder() {
	        }

	        public ErrorDtoBuilder message(String message) {
	            this.message = message;
	            return this;
	        }

	        public ErrorDto build() {
	            ErrorDto errorDto = new ErrorDto();
	            errorDto.setMessage(message);
	            return errorDto;
	        }
	    }

		
	
}