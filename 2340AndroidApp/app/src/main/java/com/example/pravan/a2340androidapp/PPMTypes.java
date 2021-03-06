package model;

/**
 *
 * Enumeration that represents all types of reports.
 * @author Alok Tripathy
 *
 */
public enum PPMTypes {
	/**
	 * PPM type Virus.
	 */
	VIRUS("VIRUS"),

	/**
	 * PPM type Contaminant.
	 */
	CONTAMINANT("CONTAMINANT");

	/**String indicating contaminate. */
	private final String type;
	/**
	 * Creates a new type of Report for the enumeration.
	 * @param type - type of Report to create.
	 */
	PPMTypes(final String type) {
		this.type = type;
	}
	/**
	 * Getter for the type of Report.
	 * @return returns type of the PPM.
	 */
	public String getType() {
		return type;
	}
}
