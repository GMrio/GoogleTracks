package googletracks.exception;

import java.util.ArrayList;
import java.util.List;

public class GoogleTracksError {
	private List<Errors> errors = new ArrayList<Errors>();
	public GoogleTracksError() {
		
	}
	public GoogleTracksError(List<Errors> errors) {
		super();
		this.errors = errors;
	}
	public List<Errors> getErrors() {
		return errors;
	}
	public void setErrors(List<Errors> errors) {
		this.errors = errors;
	}
	@Override
	public String toString() {
		return "GoogleTracksError [errors=" + errors + "]";
	}
	
}
