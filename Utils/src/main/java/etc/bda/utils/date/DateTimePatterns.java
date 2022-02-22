package etc.bda.utils.date;

public enum DateTimePatterns {

	yyyyMMdd("yyyy-MM-dd"),
	ddMMYYYY("dd-MM-YYYY"),
	
	yyyyMMdd_HHmmss("yyyy-MM-dd HH:mm:ss"),
	ddMMyyyy_HHmmss("dd-MM-yyyy HH:mm:ss"),
	HHmmss("HH:mm:ss"),
	HHmmssSSS("HH:mm:ss.SSS"),
	yyyyMMdd_HHmmssSSS("yyyy-MM-dd HH:mm:ss.SSS"),
	yyyyMMdd_HHmmssSSS_Z("yyyy-MM-dd HH:mm:ss.SSS Z");
	
	private String pattern;
	
	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	private DateTimePatterns(String pattern) {
		this.pattern = pattern;
	}
}
