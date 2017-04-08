/* Copyright 2008 - 2017 Hochschule Offenburg
 * For a list of authors see README.md
 * This software of HSOAutonomy is released under MIT License (see LICENSE).
 */
package hso.autonomy.agent.communication.action.impl;

import hso.autonomy.agent.communication.action.IEffector;

/**
 * Base class for all effectors
 *
 * @author Klaus Dorer
 */
public abstract class Effector implements IEffector
{
	private final String name;

	/**
	 * Constructor
	 *
	 * @param name Effector name
	 */
	public Effector(String name)
	{
		super();
		this.name = name;
	}

	/**
	 * Retrieve effector name
	 *
	 * @return Effector name
	 */
	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public void resetAfterAction()
	{
	}
}
