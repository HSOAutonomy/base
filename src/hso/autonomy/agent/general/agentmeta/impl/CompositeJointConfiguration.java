/* Copyright 2008 - 2017 Hochschule Offenburg
 * For a list of authors see README.md
 * This software of HSOAutonomy is released under MIT License (see LICENSE).
 */
package hso.autonomy.agent.general.agentmeta.impl;

import hso.autonomy.agent.general.agentmeta.ICompositeJointConfiguration;
import hso.autonomy.agent.general.agentmeta.IHingeJointConfiguration;
import hso.autonomy.agent.general.agentmeta.IJointToMotorMapper;

/**
 * Composite of hinge joints configuration
 *
 * @author Stefan Glaser
 */
public class CompositeJointConfiguration implements ICompositeJointConfiguration
{
	private final String name;

	private final IHingeJointConfiguration[] hjConfigurations;

	private final transient IJointToMotorMapper mapper;

	/**
	 *
	 * @param name the name of the universal joint
	 * @param mapper joint to motor mapper, null if not needed
	 * @param hjConfs the joint configurations
	 */
	public CompositeJointConfiguration(String name, IJointToMotorMapper mapper, IHingeJointConfiguration[] hjConfs)
	{
		this.name = name;
		this.mapper = mapper;
		this.hjConfigurations = hjConfs;
	}

	@Override
	public String getName()
	{
		return name;
	}

	/**
	 * @return the hjConfigurations
	 */
	@Override
	public IHingeJointConfiguration[] getHjConfigurations()
	{
		return hjConfigurations;
	}

	@Override
	public String getPerceptorName()
	{
		return name;
	}

	@Override
	public IJointToMotorMapper getJointToMotorMapper()
	{
		return mapper;
	}
}
