package frameworkUI;

public enum CrudOperation {
	Create, Read, Update, Delete;
	
	public String getName() {
		switch (this) {
		case Create: 
			return "Create";
		case Read: 
			return "Read";
		case Update: 
			return "Update";
		case Delete: 
			return "Delete";
		default: 
			return "";
		}
	}
}
