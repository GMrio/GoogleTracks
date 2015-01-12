package googletracks.entities;

import googletracks.model.DadosJson;

import java.util.ArrayList;
import java.util.List;

public class DadosJsonVO {

	private List<DadosJson> crumbs = new ArrayList<DadosJson>();

	public List<DadosJson> getCrumbs() {
		return crumbs;
	}

	public void setCrumbs(List<DadosJson> crumbs) {
		this.crumbs = crumbs;
	}

	@Override
	public String toString() {
		return "DadosJsonVO [crumbs=" + crumbs + "]";
	}
	
	
	
	
}
