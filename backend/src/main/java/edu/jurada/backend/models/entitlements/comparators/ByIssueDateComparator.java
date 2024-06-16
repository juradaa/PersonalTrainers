package edu.jurada.backend.models.entitlements.comparators;

import edu.jurada.backend.models.entitlements.Certification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;

public class ByIssueDateComparator implements Comparator<Certification> {

	Logger logger = LoggerFactory.getLogger(ByIssueDateComparator.class);
	@Override
	public int compare(Certification o1, Certification o2) {
		logger.trace("Comparing");
		return o1.getIssueDate().compareTo(o2.getIssueDate());
	}
}
