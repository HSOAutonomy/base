/* Copyright 2008 - 2017 Hochschule Offenburg
 * For a list of authors see README.md
 * This software of HSOAutonomy is released under MIT License (see LICENSE).
 */
package hso.autonomy.agent.general.agentmeta;

import java.io.Serializable;

/**
 * Sensor configuration interface
 *
 * @author Stefan Glaser
 */
public interface ISensorConfiguration extends Serializable {
	/**
	 * Get the sensors name
	 *
	 * @return The sensor name
	 */
	String getName();

	/**
	 * Get the perceptor name
	 *
	 * @return Perceptor name
	 */
	String getPerceptorName();
}
