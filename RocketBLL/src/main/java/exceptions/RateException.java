package exceptions;

import rocketDomain.RateDomainModel;

public class RateException extends Exception {
	RateDomainModel model = new RateDomainModel();
	//	TODO - RocketBLL RateException - RateDomainModel should be an attribute of RateException
	//	* Add RateRomainModel as an attribute
	//	* Create a constructor, passing in RateDomainModel
	//	* Create a getter (no setter, set value only in Constructor)
	private String error;
	public RateException(String error){
		super();
		this.error = error; 
		System.out.println(error);
	}
}
