package bean;

public class Country {
	
	private String stateAbb;
	private int cCode;
	private String stateNme;
	
	public Country(String stateAbb, int cCode, String stateNme) {
		super();
		this.stateAbb = stateAbb;
		this.cCode = cCode;
		this.stateNme = stateNme;
	}
	public String getStateAbb() {
		return stateAbb;
	}
	public void setStateAbb(String stateAbb) {
		this.stateAbb = stateAbb;
	}
	public int getcCode() {
		return cCode;
	}
	public void setcCode(int cCode) {
		this.cCode = cCode;
	}
	public String getStateNme() {
		return stateNme;
	}
	public void setStateNme(String stateNme) {
		this.stateNme = stateNme;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cCode;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (cCode != other.cCode)
			return false;
		return true;
	}
	
	public String toString(){
		String ris ;
		ris = stateAbb+" "+cCode+" "+stateNme+" \n";
		return ris;
	}

}
