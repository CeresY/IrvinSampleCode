package corejava.enumration;

public enum Color {
	RED("RED") {
		@Override
		public boolean isImpl() {
			return false;
		}
	},

	GREEN("GREEN") {
		@Override
		public boolean isImpl() {
			return false;
		}
	},

	BLUE("BLUE") {
		public boolean isRest() {
			return true;
		}

		@Override
		public boolean isImpl() {
			return false;
		}
	};

	String name;
	Color(String name) {
		this.name = name;
	}

	public boolean isRest() {
		return false;
	}

	public abstract boolean isImpl();

	public static void main(String[] args) {
		Color.valueOf("");
	}
}
