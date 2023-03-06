package bll.validators;

public interface Validator<T> {

	/**
	 * Method that validates an object of type T
	 * @param t
	 * @return true if it is validated
	 */
	public boolean validate(T t);
}
