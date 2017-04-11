/* Copyright 2008 - 2017 Hochschule Offenburg
 * For a list of authors see README.md
 * This software of HSOAutonomy is released under MIT License (see LICENSE).
 */
package hso.autonomy.agent.general.agentmeta.impl;

import java.util.Collection;
import java.util.List;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import hso.autonomy.agent.general.agentmeta.IAgentMetaModel;
import hso.autonomy.agent.general.agentmeta.IBodyPartConfiguration;
import hso.autonomy.agent.general.agentmeta.ISensorConfiguration;
import hso.autonomy.util.geometry.IPose3D;

public class AgentMetaModelDecorator implements IAgentMetaModel
{
	private final IAgentMetaModel decoratee;

	public AgentMetaModelDecorator(IAgentMetaModel decoratee)
	{
		this.decoratee = decoratee;
	}

	@Override
	public String getName()
	{
		return decoratee.getName();
	}

	@Override
	public String getSceneString()
	{
		return decoratee.getSceneString();
	}

	@Override
	public Vector3D getStaticPivotPoint()
	{
		return decoratee.getStaticPivotPoint();
	}

	@Override
	public List<IBodyPartConfiguration> getBodyPartConfigurations()
	{
		return decoratee.getBodyPartConfigurations();
	}

	@Override
	public String getNameOfCameraBodyPart()
	{
		return decoratee.getNameOfCameraBodyPart();
	}

	@Override
	public IPose3D getCameraOffset()
	{
		return decoratee.getCameraOffset();
	}

	@Override
	public Collection<ISensorConfiguration> getAvailableJoints()
	{
		return decoratee.getAvailableJoints();
	}

	@Override
	public List<IBodyPartConfiguration> getChildBodyConfigurations(IBodyPartConfiguration bodyPart)
	{
		return decoratee.getChildBodyConfigurations(bodyPart);
	}

	@Override
	public IBodyPartConfiguration getRootBodyConfiguration()
	{
		return decoratee.getRootBodyConfiguration();
	}

	@Override
	public String[] getAvailableJointNames()
	{
		return decoratee.getAvailableJointNames();
	}

	@Override
	public String[] getAvailableEffectorNames()
	{
		return decoratee.getAvailableEffectorNames();
	}

	@Override
	public float getSoccerPositionKneeAngle()
	{
		return decoratee.getSoccerPositionKneeAngle();
	}

	@Override
	public float getSoccerPositionHipAngle()
	{
		return decoratee.getSoccerPositionHipAngle();
	}

	@Override
	public float getHeight()
	{
		return decoratee.getHeight();
	}

	@Override
	public int getGoalPredictionTime()
	{
		return decoratee.getGoalPredictionTime();
	}

	@Override
	public float getCycleTime()
	{
		return decoratee.getCycleTime();
	}

	@Override
	public float getVisionCycleTime()
	{
		return decoratee.getVisionCycleTime();
	}

	@Override
	public float getTorsoZUpright()
	{
		return decoratee.getTorsoZUpright();
	}

	@Override
	public boolean hasFootForceSensors()
	{
		return decoratee.hasFootForceSensors();
	}
}
