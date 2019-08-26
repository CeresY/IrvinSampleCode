package util.log;

public enum LogLevel {
	info, warn, error;

	public static LogLevel valueOf(int id) {
		for (LogLevel i : values()) {
			if (i.ordinal() == id)
				return i;
		}

		return info;
	}
}
