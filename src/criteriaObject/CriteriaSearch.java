package criteriaObject;

public class CriteriaSearch {
    private String searchText;
    private String searchInLocation="";
    private String searchInDescription="";
    private String searchInTitle="";
    private String sortWay;

    public CriteriaSearch(String searchText, String[] searchBy, String sortWay) {
        if (searchText==null||searchText.equals("")){
            searchText="%%";
        }
        this.searchText = searchText;
        for (String u: searchBy) {
            if (u!=null){
                if (u.equals("title"))this.searchInTitle = searchText;
                if (u.equals("description"))this.searchInDescription = searchText;
                if (u.equals("location"))this.searchInLocation = searchText;
            }
        }
        this.sortWay = sortWay;
    }

    public String getSearchText() {
        if (searchText==null)
            searchText="";
        else searchText="%"+searchText+"%";
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getSearchInLocation() {
        if (!searchInLocation.equals(""))
            searchInLocation="%"+searchInLocation+"%";
        return searchInLocation;
    }

    public void setSearchInLocation(String searchInLocation) {
        this.searchInLocation = searchInLocation;
    }

    public String getSearchInDescription() {
        if (!searchInDescription.equals(""))
            searchInDescription="%"+searchInDescription+"%";
        return searchInDescription;
    }

    public void setSearchInDescription(String searchInDescription) {
        this.searchInDescription = searchInDescription;
    }

    public String getSearchInTitle() {
        if (!searchInTitle.equals(""))
            searchInTitle="%"+searchInTitle+"%";
        return searchInTitle;
    }

    public void setSearchInTitle(String searchInTitle) {
        this.searchInTitle = searchInTitle;
    }

    public String getSortWay() {
        if (sortWay==null)sortWay = "";
        return sortWay;
    }

    public void setSortWay(String sortWay) {
        this.sortWay = sortWay;
    }

}
