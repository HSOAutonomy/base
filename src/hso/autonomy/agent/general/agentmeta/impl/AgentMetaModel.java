/* Copyright 2008 - 2017 Hochschule Offenburg
 * For a list of authors see README.md
 * This software of HSOAutonomy is released under MIT License (see LICENSE).
 */
package hso.autonomy.agent.general.agentmeta.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import hso.autonomy.agent.general.agentmeta.IAgentMetaModel;
import hso.autonomy.agent.general.agentmeta.IBodyPartConfiguration;
import hso.autonomy.agent.general.agentmeta.ICompositeJointConfiguration;
import hso.autonomy.agent.general.agentmeta.IHingeJointConfiguration;
import hso.autonomy.agent.general.agentmeta.ISensorConfiguration;
import hso.autonomy.util.geometry.IPose3D;

/**
 * Agent meta model class
 *
 * @author Stefan Glaser
 */
public abstract class AgentMetaModel implements IAgentMetaModel
{
	protected final String modelName;

	protected final String sceneString;

	protected List<IBodyPartConfiguration> bodyPartConfig;

	protected final String bodyPartContainingCamera;

	protected final IPose3D cameraOffset;

	private final float soccerPositionKneeAngle;

	private final float soccerPositionHipAngle;

	private final float height;

	private final int goalPredictionTime;

	private final float cycleTime;

	private final float visionCycleTime;

	protected Collection<ISensorConfiguration> jointConfigs;

	protected String[] jointNames;

	protected String[] effectorNames;

	protected final Vector3D staticPivotPoint;

	private final float torsoZUpright;

	/**
	 * Constructor.
	 *
	 * @param modelName - the name of this model
	 * @param sceneString - the scene string
	 * @param staticPivotPoint - the static pivot-point related to this robot
	 * @param bodyPartContainingCamera - the name of the body part containing the
	 *        camera
	 */
	public AgentMetaModel(String modelName, String sceneString, Vector3D staticPivotPoint,
			String bodyPartContainingCamera, IPose3D cameraOffset, float soccerPositionKneeAngle,
			float soccerPositionHipAngle, float height, int goalPredictionTime, float cycleTime, float visionCycleTime,
			float torsoZUpright)
	{
		this.modelName = modelName;
		this.sceneString = sceneString;
		this.staticPivotPoint = staticPivotPoint;
		this.bodyPartContainingCamera = bodyPartContainingCamera;
		this.cameraOffset = cameraOffset;
		this.soccerPositionKneeAngle = soccerPositionKneeAngle;
		this.soccerPositionHipAngle = soccerPositionHipAngle;
		this.height = height;
		this.goalPredictionTime = goalPredictionTime;
		this.cycleTime = cycleTime;
		this.visionCycleTime = visionCycleTime;
		this.torsoZUpright = torsoZUpright;

		setBodyPartConfiguration(createBodyPartConfigs());
	}

	protected abstract List<IBodyPartConfiguration> createBodyPartConfigs();

	@Override
	public String getName()
	{
		return modelName;
	}

	@Override
	public String getSceneString()
	{
		return sceneString;
	}

	@Override
	public Vector3D getStaticPivotPoint()
	{
		return staticPivotPoint;
	}

	@Override
	public List<IBodyPartConfiguration> getBodyPartConfigurations()
	{
		return bodyPartConfig;
	}

	public void setBodyPartConfiguration(List<IBodyPartConfiguration> config)
	{
		bodyPartConfig = config;
	}

	@Override
	public String getNameOfCameraBodyPart()
	{
		return bodyPartContainingCamera;
	}

	@Override
	public IPose3D getCameraOffset()
	{
		return cameraOffset;
	}

	@Override
	public List<IBodyPartConfiguration> getChildBodyConfigurations(IBodyPartConfiguration bodyPart)
	{
		List<IBodyPartConfiguration> childBodies = new ArrayList<>();

		if (bodyPart != null) {
			String parentName = bodyPart.getName();

			for (int i = 0; i < bodyPartConfig.size(); i++) {
				if (parentName.equals(bodyPartConfig.get(i).getParent())) {
					childBodies.add(bodyPartConfig.get(i));
				}
			}
		}

		return childBodies;
	}

	@Override
	public IBodyPartConfiguration getRootBodyConfiguration()
	{
		for (int i = 0; i < bodyPartConfig.size(); i++) {
			if (bodyPartConfig.get(i).getParent() == null) {
				return bodyPartConfig.get(i);
			}
		}

		return null;
	}

	@Override
	public Collection<ISensorConfiguration> getAvailableJoints()
	{
		if (jointConfigs == null) {
			ArrayList<ISensorConfiguration> jointConfigsList = new ArrayList<>();

			// Extract joints
			ISensorConfiguration jointConfig;
			for (IBodyPartConfiguration config : bodyPartConfig) {
				jointConfig = config.getJointConfiguration();
				if (jointConfig != null) {
					if (jointConfig instanceof ICompositeJointConfiguration) {
						Collections.addAll(
								jointConfigsList, ((ICompositeJointConfiguration) jointConfig).getHjConfigurations());
					} else {
						jointConfigsList.add(jointConfig);
					}
				}
			}
			jointConfigs = Collections.unmodifiableCollection(jointConfigsList);

			jointNames = new String[jointConfigsList.size()];
			effectorNames = new String[jointConfigsList.size()];
			for (int i = 0; i < jointConfigsList.size(); i++) {
				ISensorConfiguration config = jointConfigsList.get(i);
				jointNames[i] = config.getName();
				effectorNames[i] = ((IHingeJointConfiguration) config).getEffectorName();
			}
		}

		return jointConfigs;
	}

	@Override
	public String[] getAvailableJointNames()
	{
		if (jointNames == null) {
			getAvailableJoints();
		}

		return jointNames;
	}

	@Override
	public String[] getAvailableEffectorNames()
	{
		if (effectorNames == null) {
			getAvailableJoints();
		}

		return effectorNames;
	}

	@Override
	public float getSoccerPositionKneeAngle()
	{
		return soccerPositionKneeAngle;
	}

	@Override
	public float getSoccerPositionHipAngle()
	{
		return soccerPositionHipAngle;
	}

	@Override
	public float getHeight()
	{
		return height;
	}

	@Override
	public int getGoalPredictionTime()
	{
		return goalPredictionTime;
	}

	@Override
	public float getCycleTime()
	{
		return cycleTime;
	}

	@Override
	public float getVisionCycleTime()
	{
		return visionCycleTime;
	}

	@Override
	public float getTorsoZUpright()
	{
		return torsoZUpright;
	}

	@Override
	public boolean hasFootForceSensors()
	{
		return false;
	}
}
