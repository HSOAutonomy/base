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
 * Implementation of the "CompositeEffector" effector, used to move robot joints
 *
 * @author Klaus Dorer
 */
public class CompositeEffector extends Effector
{
	private HingeEffector[] effectors;

	/**
	 * Instantiates a new UniversalEffector and initializes all fields to zero
	 *
	 * @param name Effector name
	 */
	public CompositeEffector(String name, HingeEffector[] effectors)
	{
		super(name);

		this.effectors = effectors;
	}

	/**
	 * Set axis movement speeds
	 */
	@Override
	public void setEffectorValues(float maxGain, float... values)
	{
		for (int i = 0; i < effectors.length; i++) {
			int position = i * 5;
			effectors[i].setEffectorValues(maxGain, values[position], values[position + 1], values[position + 2],
					values[position + 3], values[position + 4]);
		}
	}

	@Override
	public void resetAfterAction()
	{
		for (HingeEffector effector : effectors) {
			effector.resetAfterAction();
		}
	}

	public float getSpeed(int i)
	{
		return effectors[i].getSpeed();
	}

	public float getDesiredAngle(int i)
	{
		return effectors[i].getDesiredAngle();
	}

	public HingeEffector getEffector(int i)
	{
		return effectors[i];
	}

	public int size()
	{
		return effectors.length;
	}
}
