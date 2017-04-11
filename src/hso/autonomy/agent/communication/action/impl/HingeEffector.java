/*******************************************************************************
 * Copyright 2008, 2012 Hochschule Offenburg
 * Klaus Dorer, Mathias Ehret, Stefan Glaser, Thomas Huber, Fabian Korak,
 * Simon Raffeiner, Srinivasa Ragavan, Thomas Rinklin,
 * Joachim Schilling, Ingo Schindler, Rajit Shahi, Bjoern Weiler
 *
 * This file is part of magmaOffenburg.
 *
 * magmaOffenburg is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * magmaOffenburg is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with magmaOffenburg. If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package hso.autonomy.agent.communication.action.impl;

/**
 * Implementation of the SimSpark "HingeJoint" effector, used to move robot
 * joints
 *
 * @author Klaus Dorer
 */
public class HingeEffector extends Effector
{
	/**
	 * the speed of the effector in degrees per cycle - zero means the angle is
	 * not changed
	 */
	private float speed;

	private float lastSpeed;

	/** the desired angle of the effector in degrees */
	private float desiredAngle;

	/**
	 * the speed we want to have when reaching desired angle (in degrees per
	 * cycle)
	 */
	private float speedAtDesiredAngle;

	/**
	 * the acceleration we want to have when reaching desired angle (in degrees
	 * per cycle per cycle)
	 */
	private float accelerationAtDesiredAngle;

	private float gain;

	/**
	 * Instantiates a new HingeJoint effector and initializes all fields to zero
	 *
	 * @param name Hinge Joint name
	 */
	public HingeEffector(String name)
	{
		super(name);

		speed = 0.0f;
		desiredAngle = 0.0f;
		lastSpeed = 0.0f;
		gain = 0.0f;
	}

	@Override
	public void setEffectorValues(float maxGain, float... values)
	{
		this.speed = values[0];
		this.desiredAngle = values[1];
		this.speedAtDesiredAngle = values[2];
		this.accelerationAtDesiredAngle = values[3];
		this.gain = values[4];
		if (gain > maxGain) {
			gain = maxGain;
		}
	}

	public boolean hasChanged()
	{
		return speed != lastSpeed;
	}

	@Override
	public void resetAfterAction()
	{
		lastSpeed = speed;
		speed = 0.0f;
	}

	public float getSpeed()
	{
		return speed;
	}

	/**
	 * @return the desired angular position of this effector in degrees
	 */
	public float getDesiredAngle()
	{
		return desiredAngle;
	}

	public float getGain()
	{
		return gain;
	}

	public float getSpeedAtDesiredAngle()
	{
		return speedAtDesiredAngle;
	}

	public float getAccelerationAtDesiredAngle()
	{
		return accelerationAtDesiredAngle;
	}
}
