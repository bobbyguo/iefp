package com.demandforce.iefp.util.common;

import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ChangedResourceUtil {

	private static final Logger LOG = LoggerFactory.getLogger(ChangedResourceUtil.class);
	
	/**
	 * Calculate the initial value of Last Event ID
	 * 
	 * @param lastEventId
	 *            incoming value from client, possibly null
	 * @return initial value of Last Event ID
	 */
	public DateTime computeLastEventId(String lastEventId, long defaultSinceMilliseconds) {
		if (lastEventId != null) {
			try {
				// LOG.info("Using header Last-Event-Id:" + lastEventId);
				return ISODateTimeFormat.dateTimeParser().parseDateTime(lastEventId);
			} catch (Exception e) {
				// LOG.error("invalid header Last-Event-Id: " + lastEventId);
				return new DateTime(System.currentTimeMillis() - defaultSinceMilliseconds);
			}
		} else {
			LOG.info("no header Last-Event-Id detected in request. Using default of now() - " + defaultSinceMilliseconds + " milliseconds.");
			return new DateTime(System.currentTimeMillis() - defaultSinceMilliseconds);
		}
	}	
	
}
