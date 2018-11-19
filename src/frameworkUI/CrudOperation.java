package frameworkUI;

public enum CrudOperation {
	Create, Read, ListAll, Update, Delete;
	
	public String getName() {
		switch (this) {
		case Create: 
			return "Create";
		case Read: 
			return "Read";
		case ListAll:
			return "List All";
		case Update: 
			return "Update";
		case Delete: 
			return "Delete";
		default: 
			return "";
		}
	}
}
