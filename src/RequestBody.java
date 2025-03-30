public class RequestBody 
{
     private String subCategory;
     public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getSkip() {
		return skip;
	}
	public void setSkip(int skip) {
		this.skip = skip;
	}
	public String getFilters() {
		return filters;
	}
	public void setFilters(String filters) {
		this.filters = filters;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getServerToken() {
		return serverToken;
	}
	public void setServerToken(String serverToken) {
		this.serverToken = serverToken;
	}
	private int    limit;
     private int    skip;
     //private List<String> location;
     private String filters;
     private String category;
     private String type;
     private String serverToken;
}
