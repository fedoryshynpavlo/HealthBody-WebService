package edu.softserveinc.healthbody.dto;

import java.util.List;

public class CriteriaDTO {

	private String name;
	private String metrics;
	private String getGoogle;
	private List<CompetitionDTO> competitions;
	
	public CriteriaDTO() { }
	
	public CriteriaDTO(final String name, final String metrics,
			final String getGoogle, final List<CompetitionDTO> competitions) {
		this.name = name;
		this.metrics = metrics;
		this.getGoogle = getGoogle;
		this.competitions = competitions;
	}

	public final String getName() {
		return name;
	}

	public final String getMetrics() {
		return metrics;
	}

	public final String getGetGoogle() {
		return getGoogle;
	}

	public final List<CompetitionDTO> getCompetitions() {
		return competitions;
	}
}