/* Copyright 2008 - 2017 Hochschule Offenburg
 * For a list of authors see README.md
 * This software of HSOAutonomy is released under MIT License (see LICENSE).
 */
package hso.autonomy.agent.model.agentmodel;

import org.apache.commons.math3.geometry.euclidean.threed.Rotation;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

/**
 * SimSpark "GyroRate" sensor interface. Provides read-only access only.<br>
 * The GyroRate Sensor represents the orientation change of an body in the
 * 3-dimensional space, respective to the global (an other) coordinate system.
 * This orientation change is represented as quaternion in a {@link Rotation}
 * object. The perceived representation is Euler-angles given in the
 * gyro-vector.
 *
 * <p>
 * <strong>Note:</strong> Relative to the Nao-robot (center of it's torso), the
 * x-axis points to the right arm, while the y-axis is pointing forward and the
 * z-axis go's straight up through it's head.<br>
 * </p>
 *
 * @author Stefan Glaser, Srinivasa Ragavan
 */
public interface IGyroRate extends ISensor {
	/**
	 * Retrieve gyro values <br>
	 * where &lt;x, y, z&gt; is a vector containing the angle-change around the
	 * corresponding axis of the previous cycle in degrees per second <br>
	 *
	 * @return The change in orientation of the body with respect to the global
	 *         coordinate system of the previous cycle
	 */
	Vector3D getGyro();

	/**
	 * Retrieve the current change in orientation
	 *
	 * @return The current change in orientation
	 */
	Rotation getOrientationChange(float cycleTime);
}