package lt.liudas.models;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;

	public JwtResponse(String jwttoken) {
		System.out.println(30);

		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}
}